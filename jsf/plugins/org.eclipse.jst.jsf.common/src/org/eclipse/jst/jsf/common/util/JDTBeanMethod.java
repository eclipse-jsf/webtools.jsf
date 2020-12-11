/*******************************************************************************
 * Copyright (c) 2020, 2021 Axon Ivy.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Reto Weiss/Axon Ivy      Cache resolved types
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.util;


import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;

/**
 * Represents a single bean method backed by JDT data
 * @author rwei
 * @since 27.11.2020
 */
public class JDTBeanMethod
{
  private final IMethod method;
  private final String resolvedMethodSignatureErased;
  private final String resolvedMethodSignatureUnerased;

  JDTBeanMethod(IMethod method, String resolvedMethodSignatureErased, String resolvedMethodSignatureUnerased)
  {
    this.method = method;
    this.resolvedMethodSignatureErased = resolvedMethodSignatureErased;
    this.resolvedMethodSignatureUnerased = resolvedMethodSignatureUnerased;
  }
  
  /**
   * @return type that declares this method
   */
  public IType getDeclaringType()
  {
    return method.getDeclaringType();
  }

  /**
   * @return jdt method that is behind this bean method
   */
  public IMethod getMethod()
  {
    return method;
  }

  /**
   * @return method name
   */
  public String getElementName()
  {
    return method.getElementName();
  }

  /**
   * @return true if method is a constructor
   * @throws JavaModelException
   */
  public boolean isConstructor() throws JavaModelException
  {
    return method.isConstructor();
  }

  /**
   * @return flags
   * @throws JavaModelException
   */
  public int getFlags() throws JavaModelException
  {
    return method.getFlags();
  }

  /**
   * @return method signature resolved (e.g. L signatures only no unresolved signatures with Q) and erased (no type parameters) 
   */
  public String getResolvedSignatureErased()
  {
    return resolvedMethodSignatureErased;
  }

  /**
   * @return parameters types unresolved (e.g. may contain Q signatures) and unerased (with type parameters)
   */
  public String[] getUnresolvedParameterTypesUnerased()
  {
    return method.getParameterTypes();
  }
  
  /**
   * @return paramters types resolved (e.g. L signatures only no unresolved signatures with Q) and unerased (with type parameters)
   */
  public String[] getResolvedParameterTypesUnerased()
  {
    return Signature.getParameterTypes(resolvedMethodSignatureUnerased);
  }

  /**
   * @return returns type resolved (e.g. L signatures only no unresolved signatures with Q) and unerased (with type parameters)
   */
  public String getResolvedReturnTypeUnerased() 
  {
    return Signature.getReturnType(resolvedMethodSignatureUnerased);
  }
  
  /**
   * @return return type unresolved (e.g Q signatures) and unerased (with type parameters)
   * @throws JavaModelException
   */
  public String getUnresolvedReturnTypeUnerased() throws JavaModelException
  {
    return method.getReturnType();
  }
  
  @Override
  public String toString()
  {
    return "JDTBeanMethod [name="+getElementName()+ //$NON-NLS-1$
            "erasedSignature="+resolvedMethodSignatureErased+ //$NON-NLS-1$
            "unerasedSignature="+resolvedMethodSignatureUnerased+ //$NON-NLS-1$
            "]"; //$NON-NLS-1$
  }
}
