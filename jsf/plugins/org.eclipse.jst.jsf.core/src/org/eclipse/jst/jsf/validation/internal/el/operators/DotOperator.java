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
package org.eclipse.jst.jsf.validation.internal.el.operators;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.internal.types.LiteralType;
import org.eclipse.jst.jsf.common.internal.types.SignatureBasedType;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.symbol.internal.util.IObjectSymbolBasedValueType;
import org.eclipse.jst.jsf.designtime.resolver.ISymbolContextResolver;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Handles the operator 'dot' where dot(expr-a, id-b) == 'expr-a.id-b' in EL syntax
 * 
 * @author cbateman
 *
 */
public class DotOperator extends MemberAccessorOperator
{
    /**
     * @param diagnosticFactory 
     * @param file
     * @param resolver 
     */
    public DotOperator(final DiagnosticFactory diagnosticFactory, final IFile file, final ISymbolContextResolver resolver) 
    {
        super(file, diagnosticFactory, resolver);
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
	    Diagnostic diag = Diagnostic.OK_INSTANCE;
	    
        if (secondArg instanceof LiteralType)
        {
        	diag = validateNamedPropertyAccessorBase(firstArg, (LiteralType) secondArg);
        }

        return diag;
	}
	
    @Override
    protected String getOperatorName()
    {
        return Messages.getString("DotOperator.Name"); //$NON-NLS-1$
    }
}