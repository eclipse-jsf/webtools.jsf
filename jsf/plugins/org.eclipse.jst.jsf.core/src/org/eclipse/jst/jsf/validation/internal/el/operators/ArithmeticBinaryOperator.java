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

import java.math.BigDecimal;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Super for all arithmetic binary operators -- +, -, *,/,%
 * 
 * @author cbateman
 *
 */
/*package*/ abstract class ArithmeticBinaryOperator extends BinaryOperator 
{
    ArithmeticBinaryOperator(DiagnosticFactory diagnosticFactory) 
    {
        super(diagnosticFactory);
    }
    
    public abstract ValueType performOperation(ValueType firstArg, ValueType secondArg); 
    public abstract Diagnostic validate(ValueType firstArg, ValueType secondArg);
    
    /**
     * @param firstArg
     * @param secondArg
     * @return the result of firstArg op secondArg
     */
    protected abstract Long doRealOperation(Long firstArg, Long secondArg);
    
    /**
     * @param firstArg
     * @param secondArg
     * @return the result of firstArg op secondArg
     */
    protected abstract Double doRealOperation(Double firstArg, Double secondArg);

    /**
     * @param firstArg
     * @param secondArg
     * @return the result of firstArg op secondArg
     */
    protected abstract BigDecimal doRealOperation(BigDecimal firstArg, BigDecimal secondArg);
    
    /**
     * @return human-readable name of the operator
     */
    protected abstract String getOperatorName();

}
