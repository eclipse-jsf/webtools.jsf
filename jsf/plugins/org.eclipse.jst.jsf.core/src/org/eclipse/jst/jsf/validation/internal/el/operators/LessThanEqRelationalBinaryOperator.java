/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.validation.internal.el.operators;

import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;


/**
 * Encapsulates the EL less-than-equal operator -- "<="/le
 * 
 * @author cbateman
 *
 */
/*package*/ class LessThanEqRelationalBinaryOperator extends
        LtGtRelationalBinaryOperator 
{
    LessThanEqRelationalBinaryOperator(final DiagnosticFactory diagnosticFactory, String jsfVersion) {
        super(diagnosticFactory, jsfVersion);
    }

    protected boolean doRealOperation(Number firstArg, Number secondArg) 
    {
        return ((Comparable)firstArg).compareTo(secondArg) <= 0;
    }

    protected boolean doRealOperation(String firstArg, String secondArg) 
    {
        return firstArg.compareTo(secondArg) <= 0;
    }

    protected String getOperationName() 
    {
        return Messages.getString("LessThanEqRelationalBinaryOperator.OperationName"); //$NON-NLS-1$
    }
}
