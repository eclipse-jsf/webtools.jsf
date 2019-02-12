/*******************************************************************************
 * Copyright (c) 2006, 2008 Oracle Corporation.
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

import java.math.BigDecimal;

import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Represents the EL arithmetic subtraction binary operator
 * 
 * @author cbateman
 *
 */
/*package*/  class SubtractArithmeticBinaryOperator extends
        NoDivArithmeticBinaryOperator 
{

    private static final String SUBTRACTION = "subtraction"; //$NON-NLS-1$

    SubtractArithmeticBinaryOperator(DiagnosticFactory diagnosticFactory) {
        super(diagnosticFactory);
    }

    protected Long doRealOperation(Long firstArg, Long secondArg) 
    {
        return Long.valueOf(firstArg.longValue() - secondArg.longValue());
    }

    protected Double doRealOperation(Double firstArg, Double secondArg) 
    {
        return new Double(firstArg.doubleValue() - secondArg.doubleValue());
    }

    protected BigDecimal doRealOperation(BigDecimal firstArg,
            BigDecimal secondArg) 
    {
        return firstArg.subtract(secondArg);
    }

    protected String getOperatorName() 
    {
        return SUBTRACTION;
    }

}
