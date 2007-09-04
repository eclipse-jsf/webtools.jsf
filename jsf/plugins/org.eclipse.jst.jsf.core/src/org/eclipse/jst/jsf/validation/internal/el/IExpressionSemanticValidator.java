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
package org.eclipse.jst.jsf.validation.internal.el;

import java.util.List;

import org.eclipse.jst.jsf.common.internal.types.SignatureBasedType;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Exposes certain parts of the ASTSemanticValidator without exposing the whole class
 * Primarily this was done for JUnit testing
 * 
 * @author cbateman
 *
 */
public interface IExpressionSemanticValidator {

    /**
     * Performs the semantic validatino
     */
    public abstract void validate();

    /**
     * @return the list of generated message (may be empty if validate has not been called).
     */
    public abstract List<IMessage> getMessages();

    /**
     * @return the value type of fully resolved expression
     * or null if not resolved (or could not be resolved)
     */
    public abstract SignatureBasedType getExpressionType();

}