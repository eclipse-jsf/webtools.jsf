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
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.BooleanLiteralType;
import org.eclipse.jst.jsf.common.internal.types.NullLiteralType;
import org.eclipse.jst.jsf.common.internal.types.StringLiteralType;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Represents the EL unary operator '-' on a ValueType 
 * as described by JSP.2.3.7
 * 
 * @author cbateman
 *
 */
/*package*/ class EmptyUnaryOperator extends UnaryOperator 
{
    private static final String EMPTY = "empty";

    EmptyUnaryOperator(DiagnosticFactory diagnosticFactory) 
    {
        super(diagnosticFactory);
    }

    public ValueType performOperation(ValueType type) 
    {
        if (type instanceof StringLiteralType)
        {
            if ("".equals(((StringLiteralType)type).getLiteralValue()))
            {
                // if the string is empty, operator always returns true
                return BooleanLiteralType.TRUE;
            }

            // if the string is empty, operator always returns true
            return BooleanLiteralType.FALSE;
        }

        // empty(null) is always true
        if (type instanceof NullLiteralType)
        {
            return BooleanLiteralType.TRUE;
        }
        
        // if the type is not string, array, Map, or Collection 
        // then empty is always false
        String testSig = type.getSignature();
        
        if (!TypeConstants.TYPE_STRING.equals(testSig)
                && !type.isInstanceOf(TypeConstants.TYPE_COLLECTION)
                && !type.isInstanceOf(TypeConstants.TYPE_MAP)
                && Signature.getTypeSignatureKind(testSig) != Signature.ARRAY_TYPE_SIGNATURE)
        {
            return BooleanLiteralType.FALSE;
        }
        
        // otherwise, return a generic boolean type
        return new ValueType(TypeConstants.TYPE_BOOLEAN, type.getAssignability());
    }

    public Diagnostic validate(ValueType type) 
    {
        // empty always validates to false unless:
        // the value is an empty string, array, map or collection
        // one thing we do is see if the thing is a literal.  If it is
        // we can warn that the value could be folded
        if (type instanceof StringLiteralType)
        {
            String condition;
            
            if ("".equals(((StringLiteralType)type).getLiteralValue()))
            {
                condition = "true";
            }
            else
            {
                condition = "false";
            }

            return _diagnosticFactory.
                create_UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID
                    (EMPTY,condition);
        }
        
        // empty(null) is always true
        if (type instanceof NullLiteralType)
        {
            return _diagnosticFactory.
            create_UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID
                (EMPTY,"true");
        }
        
        // if the type is not string, array, Map, or Collection warn that
        // this may be always false
        String testSig = type.getSignature();
        
        if (!TypeConstants.TYPE_STRING.equals(testSig)
                && !type.isInstanceOf(TypeConstants.TYPE_COLLECTION)
                && !type.isInstanceOf(TypeConstants.TYPE_MAP)
                && Signature.getTypeSignatureKind(testSig) != Signature.ARRAY_TYPE_SIGNATURE)
        {
            return _diagnosticFactory.create_UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE();
        }
        
        return Diagnostic.OK_INSTANCE;
    }
}
