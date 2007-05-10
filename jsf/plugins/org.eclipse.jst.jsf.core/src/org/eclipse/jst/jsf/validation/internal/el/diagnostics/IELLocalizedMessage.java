/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.internal.el.diagnostics;

/**
 * Allows certain elements of the message to be made public without exposing
 * the whole Message class
 * 
 * @author cbateman
 *
 */
public interface IELLocalizedMessage 
{
    /**
     * @return the unique error code for the message
     */
    public int getErrorCode();
    
    /**
     * @return the absolute offset where the problem starts
     */
    public int getOffset();
    
    /**
     * @return the number of characters starting from getOffset()
     * where the mark stretches
     */
    public int getLength();
}
