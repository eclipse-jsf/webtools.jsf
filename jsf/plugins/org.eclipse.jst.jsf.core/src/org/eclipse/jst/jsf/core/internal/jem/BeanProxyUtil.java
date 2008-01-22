package org.eclipse.jst.jsf.core.internal.jem;

import org.eclipse.jem.internal.proxy.core.IBeanProxy;
import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
import org.eclipse.jem.internal.proxy.core.IFieldProxy;
import org.eclipse.jem.internal.proxy.core.IIntegerBeanProxy;
import org.eclipse.jem.internal.proxy.core.IMethodProxy;
import org.eclipse.jem.internal.proxy.core.IStringBeanProxy;
import org.eclipse.jem.internal.proxy.core.ThrowableProxy;

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
        private IBeanProxy _instance;

        /**
         * @param typeProxy
         */
        public BeanProxyWrapper(final IBeanTypeProxy typeProxy)
        {
            super();
            _typeProxy = typeProxy;
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
                final IMethodProxy method = (argTypes.length == 0) ? _typeProxy
                        .getMethodProxy(methodName) : _typeProxy
                        .getMethodProxy(methodName, argTypes);

                if (method != null)
                {
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

            while (curType != null)
            {
                final IBeanProxy field = getFieldValue(fieldName, curType);
                if (field != null)
                {
                    return field;
                }

                try
                {
                    curType = curType.getSuperBeanTypeProxy();
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
