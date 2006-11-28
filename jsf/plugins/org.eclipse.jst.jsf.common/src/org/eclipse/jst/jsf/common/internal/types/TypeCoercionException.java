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

package org.eclipse.jst.jsf.common.internal.types;

/**
 * @author cbateman
 *
 */
public class TypeCoercionException extends Exception 
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @see java.lang.Exception
     */
    public TypeCoercionException() {
        super();
    }

    /**
     * @param message
     * @param cause
     * @see java.lang.Exception
     */
    public TypeCoercionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @see java.lang.Exception
     */
    public TypeCoercionException(String message) {
        super(message);
    }

    /**
     * @param cause
     * @see java.lang.Exception
     */
    public TypeCoercionException(Throwable cause) {
        super(cause);
    }

}
