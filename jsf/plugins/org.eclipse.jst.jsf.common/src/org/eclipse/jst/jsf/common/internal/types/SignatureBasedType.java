/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.common.internal.types;

/**
 * Defines a signature-based type.  Signatures must conform to the JVM
 * type signature format as defined in the JVM specs and in the JDT Signature 
 * class
 * 
 * @author cbateman
 *
 */
public interface SignatureBasedType 
{
    /**
     * @return the signature string
     */
    public String getSignature();
    
    /**
     * @return a version of the this type in CompositeType form
     */
    public CompositeType toCompositeType();
}
