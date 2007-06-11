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
 * Represents the EL greater than operator -- ">"/gt
 * @author cbateman
 *
 */
/*package*/ class GreaterThanRelationalBinaryOperator extends
        LtGtRelationalBinaryOperator {

    GreaterThanRelationalBinaryOperator(final DiagnosticFactory diagnosticFactory, String jsfVersion) 
    {
        super(diagnosticFactory, jsfVersion);
    }

    protected boolean doRealOperation(Number firstArg, Number secondArg) 
    {
        return ((Comparable)firstArg).compareTo(secondArg) > 0;
    }

    protected boolean doRealOperation(String firstArg, String secondArg) 
    {
        return firstArg.compareTo(secondArg) > 0;
    }

    protected String getOperationName() 
    {
        return Messages.getString("GreaterThanRelationalBinaryOperator.OperationName"); //$NON-NLS-1$
    }
}
