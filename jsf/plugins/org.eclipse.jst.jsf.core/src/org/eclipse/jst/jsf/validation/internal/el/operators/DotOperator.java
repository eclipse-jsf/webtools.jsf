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
package org.eclipse.jst.jsf.validation.internal.el.operators;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.internal.types.LiteralType;
import org.eclipse.jst.jsf.common.internal.types.SignatureBasedType;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.symbol.internal.util.IObjectSymbolBasedValueType;

/**
 * Handles the operator 'dot' where dot(expr-a, id-b) == 'expr-a.id-b' in EL syntax
 * 
 * @author cbateman
 *
 */
public class DotOperator extends MemberAccessorOperator
{
    /**
     * @param file
     */
    public DotOperator(IFile file) 
    {
        super(file);
    }

	protected SignatureBasedType handlePerformObjectSymbolValue(
			IObjectSymbolBasedValueType firstArg, ValueType secondArg) 
    {
        // the dot operator (unlike the bracket) can only treat firstArg as 
        // a named property accessor object
        // if we don't have a literal value with which to derive value-b, then
        // we can't get a property
        if (secondArg instanceof LiteralType)
        {
        	return handlePerformNamedPropertyAccessorBase(firstArg, (LiteralType)secondArg);
        }
        return null;
	}

	public Diagnostic validateObjectSymbolValue(IObjectSymbolBasedValueType firstArg,
															ValueType secondArg) 
	{
        if (secondArg instanceof LiteralType)
        {
        	return validateNamedPropertyAccessorBase(firstArg, (LiteralType) secondArg);
        }

        return Diagnostic.OK_INSTANCE;
	}
}