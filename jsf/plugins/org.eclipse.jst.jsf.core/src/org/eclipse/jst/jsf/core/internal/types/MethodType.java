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
 * Signature type for method bindings in an EL expression
 * 
 * @author cbateman
 *
 */
public class MethodType implements SignatureBasedType 
{
    private final String        _methodName;
    private final String        _signature;

    /**
     * @param methodName
     * @param signature
     */
    public MethodType(final String methodName, final String signature)
    {
        _methodName = methodName;
        _signature = signature;
    }

    /**
     * @see org.eclipse.jst.jsf.core.internal.types.SignatureBasedType#getSignature()
     */
    public String getSignature() 
    {
        return _signature;
    }

    /**
     * @return the method name signature
     */
    public String getMethodName() 
    {
        return _methodName;
    }

    public CompositeType toCompositeType() 
    {
        return new CompositeType(_signature, IAssignable.ASSIGNMENT_TYPE_NONE);
    }
}
