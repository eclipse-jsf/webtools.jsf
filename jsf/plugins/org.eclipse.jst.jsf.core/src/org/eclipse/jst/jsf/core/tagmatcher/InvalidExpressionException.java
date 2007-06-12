/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tagmatcher;

/**
 * Indicates an exception trapped while compiling or evaluating an
 * expression.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author cbateman
 *
 */
public class InvalidExpressionException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1445871263234840885L;

    /**
     * @see RuntimeException
     */
    public InvalidExpressionException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public InvalidExpressionException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public InvalidExpressionException(Throwable cause) {
        super(cause);
    }
    
    
    
    
}
