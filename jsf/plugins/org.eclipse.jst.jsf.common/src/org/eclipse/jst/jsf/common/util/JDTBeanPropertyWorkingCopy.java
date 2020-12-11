/*******************************************************************************
 * Copyright (c) 2001, 2021 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *     Reto Weiss/Axon Ivy	Cache resolved types
 *******************************************************************************/
package org.eclipse.jst.jsf.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;

/**
 * A writable version of the JDTBeanProperty object
 * 
 * This class may not be sub-classed by clients
 * 
 * @author cbateman
 *
 */
public class JDTBeanPropertyWorkingCopy
{
  private final List<IMethod> setters = new ArrayList<IMethod>();
  private IMethod isGetter;
  private IMethod getter;
  private final IType type;
  private final JDTTypeResolver typeResolver;
  private final String propertyName;

  /**
   * @param type
   * @param typeResolver
   * @param propertyName 
   */
  public JDTBeanPropertyWorkingCopy(IType type, JDTTypeResolver typeResolver, String propertyName)
  {
    this.type = type;
    this.typeResolver = typeResolver;
    this.propertyName = propertyName;
  }

  /**
   * @param getter
   */
  public void setGetter(IMethod getter)
  {
    this.getter = getter;
  }

  /**
   * @param isGetter
   */
  public void setIsGetter(IMethod isGetter)
  {
    this.isGetter = isGetter;
  }

  /**
   * @param setter
   */
  public void addSetter(IMethod setter)
  {
    if (setter != null
            && setter.getNumberOfParameters() == 1)
    {
      setters.add(setter);
    }
  }

  /**
   * @return the bean properties spawned from this working copy Normally, there
   *         is only one property in the array, however, since this working copy
   *         represents all properties with the same name, there could be
   *         multiple properties since setters can be overloaded by name and
   *         could result in zero or one readable properties plus zero or more
   *         write-only properties with the same name. I can't see anywhere in
   *         the spec that covers this boundary case
   */
  public JDTBeanProperty toValueObject()
  {
    IMethod choosenGetter = isGetter != null ? isGetter : getter;
    IMethod choosenSetter = chooseSetter(choosenGetter);
    String unresolvedTypeSig = getUnresolvedType(choosenGetter, choosenSetter);
    if (unresolvedTypeSig == null)
    {
      return new JDTBeanProperty(propertyName, null, null, Collections.EMPTY_LIST, choosenGetter, choosenSetter);
    }
    String resolvedTypeSig = typeResolver.resolveEraseTypeParams(unresolvedTypeSig);
    IType resolvedType = resolveType(resolvedTypeSig);
    List<String> parameterizedTypeSignatures = getParameterizedTypeSignature(unresolvedTypeSig);
    return new JDTBeanProperty(
            propertyName,
            resolvedType,
            resolvedTypeSig,
            parameterizedTypeSignatures,
            choosenGetter,
            choosenSetter);
  }

  private IMethod chooseSetter(IMethod choosenGetter)
  {
    if (choosenGetter != null)
    {
      return determineMatchedSetter(choosenGetter);
    }
    else if (! setters.isEmpty())
    {
      return setters.get(0);
    }
    return null;
  }

  private IMethod determineMatchedSetter(IMethod choosenGetter)
  {
    if (setters.isEmpty())
    {
      return null;
    }
  
    final String getterSig = typeResolver.resolveEraseTypeParams(getUnresolvedType(choosenGetter, null));
    if (getterSig == null)
    {
      return null;
    }
    for (final Iterator it = setters.iterator(); it.hasNext();)
    {
      final IMethod setter = (IMethod) it.next();
      assert (setter.getNumberOfParameters() == 1);
      final String paramSig = typeResolver.resolveEraseTypeParams(setter.getParameterTypes()[0]);

      if (paramSig.equals(getterSig))
      {
        return setter;
      }
    }
    return null;
  }

  private static String getUnresolvedType(IMethod choosenGetter, IMethod choosenSetter)
  {
    try
    {
      if (choosenGetter != null)
      {
        return choosenGetter.getReturnType();
      }
      return choosenSetter.getParameterTypes()[0];
    }
    catch (JavaModelException jme)
    {
      JSFCommonPlugin.log(jme, "Error resolving bean property type signature"); //$NON-NLS-1$
      return null;
    }
  }

  private IType resolveType(String resolvedTypeSig)
  {
    final String typeSig = Signature.getElementType(resolvedTypeSig);
    return TypeUtil.resolveType(type, typeSig);
  }

  private List<String> getParameterizedTypeSignature(String unresolvedTypeSignature)
  {
    List<String> typeSig = new ArrayList<String>();
    for (String typeArg : Signature.getTypeArguments(unresolvedTypeSignature))
    {
      typeSig.add(typeResolver.resolveKeepTypeParams(typeArg));
    }
    return typeSig;
  }
}
