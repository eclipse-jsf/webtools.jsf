/*******************************************************************************
 * Copyright (c) 2007, 2021 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    Reto Weiss/Axon Ivy      Cache resolved types
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.util;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.types.TypeInfoCache;

/**
 * A class that does bean introspection on a JDT IType
 * 
 * This functionality is not meant to replace runtime bean 
 * introspection.  Rather, it is meant to provide a 
 * more "lightweight" (in terms of class loading as well as
 * error handling of bean instantiation out of context) way
 * to determine a bean's properties at design time.
 * 
 * This class may not be sub-classed by clients.
 * 
 * @author cbateman
 *
 */
public class JDTBeanIntrospector
{
  private final static IType[] EMPTY_SUPER_TYPES = new IType[0];
  private final static String GET_PREFIX = "get"; //$NON-NLS-1$
  private final static String SET_PREFIX = "set"; //$NON-NLS-1$
  private final static String IS_PREFIX = "is"; //$NON-NLS-1$
  private final static IMethod[] EMTPY_METHODS = new IMethod[0];

  private final IType type;
  private final JDTTypeResolver typeResolver;
  private IMethod[] methods;
  private final IType[] superTypes;

  /**
   * @param type
   * @return cached JDT bean introspector for the given type
   */
  public static JDTBeanIntrospector forType(IType type)
  {
    TypeInfoCache cache = TypeInfoCache.getInstance();
    JDTBeanIntrospector beanIntrospector = cache.getCachedBeanIntrospector(type);
    if (beanIntrospector != null)
    {
      return beanIntrospector;
    }
    IType[] superTypes = cache.getCachedSupertypes(type);
    if (superTypes == null)
    {
      superTypes = cache.cacheSupertypesFor(type);
    }
    beanIntrospector = new JDTBeanIntrospector(type, superTypes);
    cache.cacheBeanIntrospector(type, beanIntrospector);
    return beanIntrospector;
  }
  
  /**
   * @param type
   */
  private JDTBeanIntrospector(IType type, IType[] superTypes)
  {
    this.type = type;
    this.superTypes = superTypes;
    if (superTypes == null)
    {
      superTypes = EMPTY_SUPER_TYPES;
    }
      
    this.typeResolver = new JDTTypeResolver(type, superTypes);
  }

  /**
   * @return an map of all properties with the property names as keys and the
   *         values being JDTBeanProperty objects representing the properties.
   */
  public Map<String, JDTBeanProperty> getProperties()
  {
    final Map<String, JDTBeanPropertyWorkingCopy> propertiesWorkingCopy = new HashMap<String, JDTBeanPropertyWorkingCopy>();
    final IMethod[] mthds = methods();

    for (int i = 0; i < mthds.length; i++)
    {
      try
      {
        processPropertyMethod(mthds[i], propertiesWorkingCopy);
      }
      catch (JavaModelException jme)
      {
        // log and then proceed to next method
        JSFCommonPlugin.log(jme, "Error processing IMethod for bean property info"); //$NON-NLS-1$
      }
    }

    final Map<String, JDTBeanProperty> properties = new HashMap<String, JDTBeanProperty>();

    for (Entry<String, JDTBeanPropertyWorkingCopy> entry : propertiesWorkingCopy.entrySet())
    {
      final String key = entry.getKey();
      JDTBeanPropertyWorkingCopy wcopy = entry.getValue();
      properties.put(key, wcopy.toValueObject());
    }

    return properties;
  }

  private void processPropertyMethod(IMethod method, Map<String, JDTBeanPropertyWorkingCopy> properties)
          throws JavaModelException
  {
    // to be a bean method, it must not a constructor, must be public
    // and must not be static
    if (!method.isConstructor()
            && (Flags.isPublic(method.getFlags())
                    || type.isInterface())
            && !Flags.isStatic(method.getFlags()))
    {
      final String methodName = method.getElementName();
      final String returnType = method.getReturnType();

      // either starts with get or is boolean and starts with is

      // is access must start with 'is', have a boolean return type and no
      // parameters
      final boolean startsWithIs = methodName.startsWith(IS_PREFIX)
              && Signature.SIG_BOOLEAN.equals(returnType)
              && method.getNumberOfParameters() == 0
              && methodName.length() > IS_PREFIX.length();

      // get accessor must start with 'get', have no parameters and return
      // non-void
      final boolean startsWithGet = (methodName.startsWith(GET_PREFIX)
              && method.getNumberOfParameters() == 0)
              && !Signature.SIG_VOID.equals(returnType)
              && methodName.length() > GET_PREFIX.length();

      // mutator must start with 'set' and have one parameter and a void return
      // type
      final boolean startsWithSet = methodName.startsWith(SET_PREFIX)
              && method.getNumberOfParameters() == 1
              && Signature.SIG_VOID.equals(returnType)
              && methodName.length() > SET_PREFIX.length();

      if (startsWithGet || startsWithSet || startsWithIs)
      {
        final String propertyName = Introspector.decapitalize(methodName.substring(startsWithIs ? 2 : 3));

        JDTBeanPropertyWorkingCopy workingCopy = properties.get(propertyName);

        if (workingCopy == null)
        {
          workingCopy = new JDTBeanPropertyWorkingCopy(type, typeResolver, propertyName);
          properties.put(propertyName, workingCopy);
        }

        if (startsWithIs)
        {
          workingCopy.setIsGetter(method);
        }
        else if (startsWithGet)
        {
          workingCopy.setGetter(method);
        }
        else if (startsWithSet)
        {
          workingCopy.addSetter(method);
        }
      }
    }
  }

  /**
   * @return methods
   */
  public JDTBeanMethod[] getMethods()
  {
    List<JDTBeanMethod> beanMethods = new ArrayList<JDTBeanMethod>();
    for (IMethod method : methods())
    {
      JDTBeanMethod beanMethod = toBeanMethod(method);
      if (beanMethod != null)
      {
        beanMethods.add(beanMethod);
      }
    }
    return beanMethods.toArray(new JDTBeanMethod[beanMethods.size()]);
  }
  
  private JDTBeanMethod toBeanMethod(IMethod method)
  {
    try
    {
      String resMethodSigErased = typeResolver.resolveMethodEraseTypeParams(method.getSignature());
      String resMethodSigUnerased = typeResolver.resolveMethodKeepTypeParams(method.getSignature());
      return new JDTBeanMethod(method, resMethodSigErased, resMethodSigUnerased);
    }
    catch(JavaModelException jme)
    {
      // log and then proceed to next method
      JSFCommonPlugin.log(jme, "Error processing IMethod for bean method info"); //$NON-NLS-1$
      return null;
    }
  }
  
  /**
   * @return all methods for the type including inherited ones
   */
  private IMethod[] methods()
  {
    // type not resolved so don't proceed
    if (type == null)
    {
      return EMTPY_METHODS;
    }
    if (methods == null)
    {
      methods = allMethods();
    }
    return methods;
  }

  /**
   * @param typeHierarchy
   * @param type
   * @return all methods of the type and it's super types
   */
  private IMethod[] allMethods()
  {
    final List<IMethod> allMethods = new ArrayList<IMethod>();
    final IType[] closure = new IType[superTypes.length + 1];
    closure[0] = type;
    System.arraycopy(superTypes, 0, closure, 1, superTypes.length);

    for (IType t : closure)
    {
      try
      {
        allMethods.addAll(Arrays.asList(t.getMethods()));
      }
      catch (JavaModelException e)
      {
        JSFCommonPlugin.log(e, "Error getting super type information for bean"); //$NON-NLS-1$
      }
    }

    return allMethods.toArray(new IMethod[allMethods.size()]);
  }

  /**
   * @param simpleName simple type name
   * @return full qualified type name or null
   */
  public String resolveFullQualifiedTypeName(String simpleName)
  {
    IType resolvedType = typeResolver.resolveTypeName(simpleName);
    if (resolvedType == null)
    {
      return null;
    }
    return resolvedType.getFullyQualifiedName();
  }
}
