/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.jem;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.internal.proxy.core.IBeanProxy;
import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
import org.eclipse.jem.internal.proxy.core.IFieldProxy;
import org.eclipse.jem.internal.proxy.core.IIntegerBeanProxy;
import org.eclipse.jem.internal.proxy.core.IMethodProxy;
import org.eclipse.jem.internal.proxy.core.IStringBeanProxy;
import org.eclipse.jem.internal.proxy.core.ThrowableProxy;
import org.eclipse.jst.jsf.common.util.TypeUtil;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * Convenience methods for using bean proxies
 * 
 * @author cbateman
 * 
 */
public final class BeanProxyUtil
{

    /**
     * A convenience wrapper for manipulating JEM proxy types
     * 
     */
    public static class BeanProxyWrapper
    {
        private final static IBeanProxy[] NO_ARGS = new IBeanProxy[0];
        private final static IBeanTypeProxy[] NO_ARG_TYPES = new IBeanTypeProxy[0];

        private final IBeanTypeProxy _typeProxy;
        private final IProject       _project;
        private IBeanProxy _instance;

        /**
         * @param project 
         * @param typeProxy
         */
        public BeanProxyWrapper(final IProject project, final IBeanTypeProxy typeProxy)
        {
            super();
            _typeProxy = typeProxy;
            _project = project;
        }

        /**
         * Initialize a proxied instance of the type referred to by typeProxy.
         * 
         * @throws ProxyException
         */
        public void init() throws ProxyException
        {
            if (_instance == null)
            {
                try
                {
                    _instance = _typeProxy.newInstance();
                }
                catch (final ThrowableProxy e)
                {
                    throw new ProxyException(e);
                }
                catch (final NoClassDefFoundError ndfe)
                {
                    throw new ProxyException(ndfe);
                }
                catch (final ExceptionInInitializerError e)
                {
                    throw new ProxyException(e);
                }
            }
        }

        /**
         * Re-initialize the proxied instance of typeProxy.
         * 
         * @throws ProxyException
         */
        public void reinit() throws ProxyException
        {
            _instance = null;
            init();
        }

        /**
         * @return the instance proxy
         */
        public IBeanProxy getInstance()
        {
            return _instance;
        }

        /**
         * <p>
         * Call the method called methodName on the proxied instance. If
         * args.length is 0 then the zero-argument method is found and called.
         * </p>
         * 
         * @param methodName
         * @param args
         * @param argTypes
         * @return the result of calling the method or null if there is no such
         *         method.
         * @throws ProxyException
         */
        public IBeanProxy call(final String methodName,
                final IBeanProxy[] args, final IBeanTypeProxy[] argTypes)
                throws ProxyException
        {
            try
            {
                final IMethodProxy method = getMethodProxy(methodName, argTypes);
                    /*(argTypes.length == 0) ? _typeProxy
                        .getMethodProxy(methodName) : _typeProxy
                        .getMethodProxy(methodName, argTypes);*/

                if (method != null)
                {
                    method.setAccessible(true);
                    return method.invoke(_instance, args);
                }
            }
            catch (final ThrowableProxy tp)
            {
                throw new ProxyException(tp);
            }
            catch (final NoClassDefFoundError ndfe)
            {
                throw new ProxyException(ndfe);
            }

            return null;
        }

        /**
         * Convenience method for call(methodName, new {@link IBeanProxy}[0],
         * new {@link IBeanTypeProxy}[0])
         * 
         * @param methodName
         * @return the proxied return value
         * @throws ProxyException
         */
        public IBeanProxy call(final String methodName) throws ProxyException
        {
            return call(methodName, NO_ARGS, NO_ARG_TYPES);
        }

        /**
         * <p>
         * Calls the zero-argument method called 'methodName' on the proxied
         * instance and if it results in a String return value, returns it. If
         * the method does not return a String value, then null is returned.
         * </p>
         * 
         * @param methodName
         * @return the string value or null.
         * @throws ProxyException
         */
        public String callStringMethod(final String methodName)
                throws ProxyException
        {
            final IBeanProxy result = call(methodName);

            if (result instanceof IStringBeanProxy)
            {
                return ((IStringBeanProxy) result).stringValue();
            }

            return null;
        }

        /**
         * <p>
         * Calls the zero-argument method called 'methodName' on the proxied
         * instance and if it results in an Integer value, returns it. If the
         * method does not return an integer value, then null is returned.
         * </p>
         * 
         * @param methodName
         * @return the integer value or null.
         * @throws ProxyException
         */
        public Integer callIntMethod(final String methodName)
                throws ProxyException
        {
            final IBeanProxy result = call(methodName, NO_ARGS, NO_ARG_TYPES);

            if (result instanceof IIntegerBeanProxy)
            {
                return Integer.valueOf(((IIntegerBeanProxy) result).intValue());
            }

            return null;
        }

        /**
         * Use the typeProxy for the proxied instance to try to acquire the
         * field called fieldName of type String. Note that this won't find
         * private fields on supertypes.
         * 
         * Equivalent to getStringFieldValue(fieldName, _typeProxy);
         * 
         * @param fieldName
         * @return the String value of fieldName on the proxied instance or
         *         null.
         * @throws ProxyException
         */
        public String getStringFieldValue(final String fieldName)
                throws ProxyException
        {
            return getStringFieldValue(fieldName, _typeProxy);
        }

        /**
         * Use the provided typeProxy to acquire the field proxy for the field
         * proxy called fieldName. Normally, you would use the type proxy of the
         * instance bean, however there are cases such as acquiring the value a
         * private field on a supertype where you need the type proxy for the
         * super type.
         * 
         * @param fieldName
         * @param typeProxy
         * @return the string value or null.
         * @throws ProxyException
         */
        public String getStringFieldValue(final String fieldName,
                final IBeanTypeProxy typeProxy) throws ProxyException
        {
            final IBeanProxy value = getFieldValue(fieldName, typeProxy);

            if (value instanceof IStringBeanProxy)
            {
                return ((IStringBeanProxy) value).stringValue();
            }

            return null;
        }

        /**
         * @param fieldName
         * @param typeProxy
         * @return the declared field value on the proxied instance called
         *         fieldName or null.
         * @throws ProxyException
         */
        public IBeanProxy getFieldValue(final String fieldName,
                final IBeanTypeProxy typeProxy) throws ProxyException
        {
            // avoid having JEM log a warning if we can prove the call to find
            // the field will fail
            if (!hasField(fieldName))
            {
                return null;
            }

            try
            {
                final IFieldProxy fieldProxy = typeProxy
                        .getDeclaredFieldProxy(fieldName);

                if (fieldProxy != null)
                {
                    fieldProxy.setAccessible(true);
                    return fieldProxy.get(_instance);
                }
            }
            catch (final ThrowableProxy e)
            {
                throw new ProxyException(e);
            }
            catch (final NoClassDefFoundError ndfe)
            {
                throw new ProxyException(ndfe);
            }
            
            return null;
        }

        /**
         * Same as {@link #getFieldValue(String, IBeanTypeProxy)} except it will
         * climb the parent hierarchy looking for the first field called
         * fieldName.
         * 
         * @param fieldName
         * @param typeProxy
         * @return the proxied value or null
         * @throws ProxyException
         */
        public IBeanProxy getFieldValueIncludeParents(final String fieldName,
                final IBeanTypeProxy typeProxy) throws ProxyException
        {
            IBeanTypeProxy curType = typeProxy;

            PARENT_LOOP: while (curType != null)
            {
                final IBeanProxy field = getFieldValue(fieldName, curType);
                if (field != null)
                {
                    return field;
                }

                try
                {
                    IBeanTypeProxy oldType = curType;
                    curType = curType.getSuperBeanTypeProxy();
                    // avoid infinite loop: if the parent of curType can't 
                    // be resolved, JEM returns the same type, so curType
                    // never becomes null
                    if (oldType == curType)
                    {
                        break PARENT_LOOP;
                    }
                }
                catch (final NullPointerException npe)
                {
                    // suppress npe caused by getSuperBeanTypeProxy
                    // not doing a null check on getSuperType()
                    curType = null;
                }
            }

            // have got to the top of hierarchy and not found the field
            return null;
        }

        /**
         * @return the proxied instance
         */
        public final IBeanProxy getBeanProxy()
        {
            return _instance;
        }

        private IMethodProxy getMethodProxy(final String methodName, final IBeanTypeProxy[] argTypes)
        {
            IBeanTypeProxy curType = _typeProxy;

            PARENT_LOOP: while (curType != null)
            {
                final IMethodProxy[] declaredMethods = curType.getDeclaredMethods();
                if (declaredMethods != null)
                {
                    final IMethodProxy foundMethod = findMethodInList(methodName, argTypes, declaredMethods);
                    if (foundMethod != null)
                    {
                        return foundMethod;
                    }
                }

                // avoid infinite loop: if the parent of curType can't 
                // be resolved, JEM returns the same type, so curType
                // never becomes null
                IBeanTypeProxy oldType = curType;
                curType = _typeProxy.getSuperBeanTypeProxy();
                if (oldType == curType)
                {
                    break PARENT_LOOP;
                }
            }
            return null;
        }

        private IMethodProxy findMethodInList(final String methodName, final IBeanTypeProxy[] argTypes,
                final IMethodProxy[] listOfMethods)
        {
            METHODS_LOOP: for (final IMethodProxy methodProxy : listOfMethods)
            {
                if (methodName.equals(methodProxy.getName()))
                {
                    final IBeanTypeProxy[] parameterTypes = 
                        methodProxy.getParameterTypes();
                    if (argTypes.length == parameterTypes.length)
                    {
                        for (int i = 0; i < argTypes.length; i++)
                        {
                            if (!argTypes[i].getTypeName().equals(parameterTypes[i].getTypeName()))
                            {
                                // if we find a parameter type mismatch, then
                                // skip this method; it's not it
                                continue METHODS_LOOP;
                            }
                        }
                        // if we get to here, we have a method with right name
                        // and parameters
                        return methodProxy;
                    }
                }
            }
            return null;
        }

        private boolean hasField(final String fieldName)
        {
            final IType type = lazilyCalculateType();
            if (type != null)
            {
                final IField field = type.getField(fieldName);
                return field.exists();
            }
            return false;
        }

        private IType _type;
        private boolean _checkedType;
        private IType lazilyCalculateType()
        {
            if (!_checkedType)
            {
                _checkedType = true;
                final String typeName = _typeProxy.getTypeName();
                final IJavaProject javaProject = JavaCore.create(_project);
                if (typeName != null && typeName.startsWith("L")) //$NON-NLS-1$
                {
                    _type = TypeUtil.resolveType(javaProject, typeName);
                }
                else
                {
                    try 
                    {
                        _type = javaProject.findType(typeName);
                    } 
                    catch (JavaModelException e) 
                    {
                        JSFCorePlugin.log(e, "While loading type: "+typeName); //$NON-NLS-1$
                    }
                }
            }
            return _type;
        }

//        private Map<String, List<IMethod>>   _methods;
//        
//        private Map<String, List<IMethod>> lazilyCalculateMethods(final IType type)
//        {
//            if (_methods == null)
//            {
//                _methods = new HashMap<String, List<IMethod>>();
//                final JDTBeanIntrospector introspector = new JDTBeanIntrospector(type);
//                final IMethod[] methods = introspector.getAllMethods();
//                
//                for (final IMethod method : methods)
//                {
//                    List<IMethod> byName = _methods.get(method.getElementName());
//                    if (byName == null)
//                    {
//                        byName = new ArrayList<IMethod>();
//                        _methods.put(method.getElementName(), byName);
//                    }
//                    
//                    try {
//                        if (method.exists()
//                                && Flags.isPublic(method.getFlags()))
//                        {
//                            byName.add(method);
//                        }
//                    } catch (JavaModelException e) {
//                        JSFCorePlugin.log(e, "While getting flags on method: "+method.getElementName());
//                    }
//                }
//            }
//            return _methods;
//        }
        
    }

    /**
     * Checked exception the wraps problems thrown by JEM proxying into a single
     * exception
     * 
     */
    public static class ProxyException extends Exception
    {
        private static final long serialVersionUID = -1526057761795574331L;

        /**
         * @param message
         * @param cause
         */
        public ProxyException(final String message, final Throwable cause)
        {
            super(message, cause);
        }

        /**
         * @param cause
         */
        public ProxyException(final Throwable cause)
        {
            super(cause);
        }

    }
}
