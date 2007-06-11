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

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.internal.types.BooleanLiteralType;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.LiteralType;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercer;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercionException;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.TypeTransformer;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;


/**
 * Super-class of all relational binary ops - "==", "!=", "<", ">", "<=", ">="
 * @author cbateman
 *
 */
/*package*/ abstract class RelationalBinaryOperator extends BinaryOperator 
{
    private final String        _jsfVersion;
    
    RelationalBinaryOperator(final DiagnosticFactory diagnosticFactory, final String jsfVersion)
    {
        super(diagnosticFactory);
        _jsfVersion = jsfVersion;
    }
    
    /**
     * @param firstArg
     * @param secondArg
     * @return the result of the operation
     */
    protected abstract boolean doRealOperation(Number firstArg, Number secondArg);

    /**
     * @param firstArg
     * @param secondArg
     * @return the result  of the operation
     */
    protected abstract boolean doRealOperation(String firstArg, String secondArg);

    /**
     * @return the operation's user readable name
     */
    protected abstract String getOperationName();
    
    /**
     * Performs a the operation, casting both args to BigDecimal first
     * 
     * @param firstArg
     * @param secondArg
     * @param numberType 
     * @return the result of the comparison or null if indeterminate
     */
    protected ValueType handleNumericComparison(ValueType firstArg, ValueType secondArg, Class  numberType)
    {
        try
        {
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));
            
            Number firstValue = null;
            if (firstArg instanceof LiteralType)
            {
                firstValue = ((LiteralType)firstArg).coerceToNumber(numberType);
            }
            
            Number secondValue = null;
            if (secondArg instanceof LiteralType)
            {
                secondValue = ((LiteralType)secondArg).coerceToNumber(numberType);
            }
            
            if (firstValue != null && secondValue != null)
            {
                boolean result = doRealOperation(firstValue, secondValue);
                
                return result ? BooleanLiteralType.TRUE : BooleanLiteralType.FALSE;
            }
            
            // if we get to here, we only know that both can be up cast to BigDecimal
            // and compared.  This will yield a boolean result
            // this value cannot be lhs
            return new ValueType(TypeConstants.TYPE_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
        }
        catch (TypeCoercionException tce)
        {
            // no valid coercion, so return null
            return null;
        }
    }
    
    /**
     * @param firstType
     * @param secondType
     * @param numberType
     * @return a diagnostic validating the relational comparison of firstType to secondType
     */
    protected Diagnostic validateNumericComparison(ValueType firstType, ValueType secondType, Class numberType)
    {
        try
        {
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(firstType.getSignature()));
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(secondType.getSignature()));
            
            Number firstValue = null;
            if (firstType instanceof LiteralType)
            {
                firstValue = ((LiteralType)firstType).coerceToNumber(numberType);
            }
            
            Number secondValue = null;
            if (secondType instanceof LiteralType)
            {
                secondValue = ((LiteralType)secondType).coerceToNumber(numberType);
            }
            
            if (firstValue != null && secondValue != null)
            {
                boolean result = doRealOperation(firstValue, secondValue);
                
                return _diagnosticFactory.
                    create_BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME
                        (getOperationName(), Boolean.toString(result));
            }
            
            // if we get to here, we only know that both can be up cast to BigDecimal
            // and compared.  This condition is okay
            return Diagnostic.OK_INSTANCE;
        }
        catch (TypeCoercionException tce)
        {
            // could not make numeric coercion for valid comparison
            return _diagnosticFactory.create_BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION(getOperationName());
        }
    }

    /**
     * @param firstType
     * @param secondType
     * @return the result of the operation
     */
    protected ValueType handleStringComparison(ValueType firstType, ValueType secondType)
    {
        String firstValue = null;
        
        if (firstType instanceof LiteralType)
        {
            firstValue = ((LiteralType)firstType).getLiteralValue();
        }
        
        String secondValue = null;
        if (secondType instanceof LiteralType)
        {
            secondValue = ((LiteralType)secondType).getLiteralValue();
        }
        
        if (firstValue != null && secondValue != null)
        {
            boolean newValue = doRealOperation(firstValue, secondValue);
            return newValue ? BooleanLiteralType.TRUE : BooleanLiteralType.FALSE;
        }
        
        // if don't have all literals, just return boolean type
        return new ValueType(TypeConstants.TYPE_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
    }

    /**
     * @param firstType
     * @param secondType
     * @return a diagnostic validating the string comparison of firstType to secondType
     */
    protected Diagnostic validateStringComparison(ValueType firstType, ValueType secondType)
    {
        String firstValue = null;
        
        if (firstType instanceof LiteralType)
        {
            firstValue = ((LiteralType)firstType).getLiteralValue();
        }
        
        String secondValue = null;
        if (secondType instanceof LiteralType)
        {
            secondValue = ((LiteralType)secondType).getLiteralValue();
        }
        
        if (firstValue != null && secondValue != null)
        {
            boolean newValue = doRealOperation(firstValue, secondValue);
            return _diagnosticFactory.
                create_BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME
                    (getOperationName(), Boolean.toString(newValue));
        }
        
        // if don't have all literals, just return boolean type
        return Diagnostic.OK_INSTANCE;
    }
    

    /**
     * @return the current JSF version string
     */
    protected final String getJsfVersion() {
        return _jsfVersion;
    }
    
    /**
     * @return true if the JSF version for this operator is JSF 1.1 or 1.0
     */
    protected final boolean isPreJSF12()
    {
        return IJSFCoreConstants.JSF_VERSION_1_1.equals(_jsfVersion) 
                || IJSFCoreConstants.JSF_VERSION_1_0.equals(_jsfVersion);
    }
    
    /**
     * @return true if the JSF version for this operator is JSF 1.2 or later
     */
    protected final boolean isJSF12OrLater()
    {
        return !isPreJSF12();
    }
}
