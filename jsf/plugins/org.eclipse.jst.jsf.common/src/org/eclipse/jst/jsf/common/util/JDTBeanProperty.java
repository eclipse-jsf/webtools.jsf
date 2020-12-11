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

import java.util.List;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.Signature;

/**
 * Represents a single bean property backed by JDT data
 * 
 * This class may not be sub-classed by clients.
 * 
 * @author cbateman
 *
 */
public class JDTBeanProperty
{
  private final IType type;
  private final String typeSignature;
  private final List<String> typeParameterSignatures;
  private final IMethod getter;
  private final IMethod setter;
  private final String propertyName;

  JDTBeanProperty(String propertyName, IType type, String typeSignature, List<String> typeParameterSignatures, IMethod getter, IMethod setter)
  {
    this.propertyName = propertyName;
    this.type = type;
    this.typeSignature = typeSignature;
    this.typeParameterSignatures = typeParameterSignatures;
    this.getter = getter;
    this.setter = setter;
  }

  /**
   * @return true if property can be read
   */
  public boolean isReadable()
  {
    return getter != null;
  }

  /**
   * @return true if property can be written
   */
  public boolean isWritable()
  {
    return setter != null;
  }

  /**
   * @return JDT getter method
   */
  public IMethod getGetter()
  {
    return getter;
  }

  /**
   * @return JDT setter metod
   */
  public IMethod getSetter()
  {
    return setter;
  }

  /**
   * @return the IType for this property's type or null if it cannot determined.
   *         Note that null does not necessarily indicate an error since some
   *         types like arrays of things do not have corresponding JDT IType's
   *         If typeSignature represents an array, the base element IType is
   *         returned if possible
   */
  public IType getType()
  {
    return type;
  }

  /**
   * @return the number of array nesting levels in typeSignature. Returns 0 if
   *         not an array.
   */
  public int getArrayCount()
  {
    final String sig = getTypeSignature();
    if (sig == null)
      return 0;
    return Signature.getArrayCount(sig);
  }

  /**
   * @return true if property is an enum type, false otherwise or if cannot be
   *         resolved
   */
  public boolean isEnumType()
  {
    return TypeUtil.isEnumType(getType());
  }

  /**
   * Fully equivalent to:
   * 
   * getTypeSignature(true)
   * 
   * @return the fully resolved (if possible) type signature for the property or
   *         null if unable to determine.
   * 
   *         NOTE: this is the "type erasure" signature, so any type parameters
   *         will be removed and only the raw type signature will be returned.
   */
  public String getTypeSignature()
  {
    return typeSignature;
  }

  /**
   * For example, if this property was formed from: List<String>
   * getListOfStrings() then the list would consist of the signature
   * "Ljava.lang.String;". All nested type paramters are resolved
   * 
   * @return a list of type signatures (fully resolved if possible) of this
   *         property's bounding type parameters.
   */
  public List<String> getTypeParameterSignatures()
  {
    return typeParameterSignatures;
  }
  
  @Override
  public String toString()
  {    
    return "JDTBeanProperty [name="+propertyName+ //$NON-NLS-1$
           " typeSignature="+typeSignature+ //$NON-NLS-1$
           " typeParameterSignatures "+typeParameterSignatures+ //$NON-NLS-1$
           "]";  //$NON-NLS-1$
  }
}
