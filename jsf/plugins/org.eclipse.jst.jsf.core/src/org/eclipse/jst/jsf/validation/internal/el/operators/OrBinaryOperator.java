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

package org.eclipse.jst.jsf.validation.internal.el.operators;

import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/** 
 * Encapsulates the 'and'/'&&' boolean-AND operator
 * Based on JSP.2.3.6.1
 * 
 * @author cbateman
 *
 */
/*package*/class OrBinaryOperator extends LogicalBinaryOperator 
{
    OrBinaryOperator(DiagnosticFactory diagnosticFactory) 
    {
        super(diagnosticFactory);
    }

    protected boolean doRealOperation(Boolean firstArg, Boolean secondArg) 
    {
        return (firstArg.booleanValue() || secondArg.booleanValue());
    }

    protected String readableOperatorName()
    {
        return "logical-OR"; //$NON-NLS-1$
    }

    protected boolean shortCircuitValue() 
    {
        // OR short-circuits on true
        return true;
    }
}
