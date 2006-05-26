/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.core.internal.types;

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
