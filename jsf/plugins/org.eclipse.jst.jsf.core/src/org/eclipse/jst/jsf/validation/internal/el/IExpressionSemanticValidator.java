/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.internal.el;

import org.eclipse.jst.jsf.common.internal.types.SignatureBasedType;

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
     * @return the value type of fully resolved expression
     * or null if not resolved (or could not be resolved)
     */
    public abstract SignatureBasedType getExpressionType();

}