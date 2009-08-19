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
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.LiteralType;
import org.eclipse.jst.jsf.common.internal.types.SignatureBasedType;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercer;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercionException;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.TypeTransformer;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.util.IObjectSymbolBasedValueType;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.el.AbstractDTPropertyResolver;
import org.eclipse.jst.jsf.designtime.resolver.ISymbolContextResolver;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Handles the operator 'bracket' where bracket(expr-a, id-b) == 'expr-a[id-b]' in EL syntax
 * 
 * @author cbateman
 *
 */
public class BracketOperator extends MemberAccessorOperator
{
    private static final String OPERATOR_NAME_ARRAY_ACCESSOR = "array ('[]') accessor"; //$NON-NLS-1$

    /**
     * @param diagnosticFactory 
     * @param file 
     * @param resolver 
     */
    public BracketOperator(final DiagnosticFactory diagnosticFactory, final IFile file, final ISymbolContextResolver resolver)
    {
        super(file, diagnosticFactory, resolver);
    }

	protected SignatureBasedType handlePerformObjectSymbolValue(
			IObjectSymbolBasedValueType firstArg, ValueType secondArg)
	{
        // per JSP.2.3.4 step 5.2, if value-a is a list or array access
        if (firstArg.isInstanceOf(TypeConstants.TYPE_LIST)
                || Signature.getArrayCount(firstArg.getSignature()) > 0)
        {
            return handlePerformNumericPropertyAccessorBase(firstArg, secondArg);
        }

        // per JSP.2.3.4 step 5, if value-a is a map or if it is not 
        // a list or array (and therefore a bean), treat it as named property accessed object
        // if firstArg is a map then we must treat the access like a map.get(secondArg)

        // if we don't have a literal value with which to derive value-b, then
        // we can't get a property
        if (secondArg instanceof LiteralType)
        {
        	return handlePerformNamedPropertyAccessorBase(firstArg, (LiteralType)secondArg);
        }

        return null;
	}
	
	protected Diagnostic validateObjectSymbolValue(IObjectSymbolBasedValueType firstArg,
			ValueType secondArg) 
	{
        // per JSP.2.3.4 step 5.2, if value-a is a list or array access
        if (firstArg.isInstanceOf(TypeConstants.TYPE_LIST)
                || firstArg.getSymbol().getTypeDescriptor().isArray())
        {
            return validateNumericPropertyAccessorBase(firstArg, secondArg);
        }

        // per JSP.2.3.4 step 5, if value-a is a map or if it is not 
        // a list or array (and therefore a bean), treat it as named property accessed object
        // if firstArg is a map then we must treat the access like a map.get(secondArg)
		if (secondArg instanceof LiteralType)
		{
			return validateNamedPropertyAccessorBase(firstArg, (LiteralType) secondArg);
		}
        // otherwise, there's nothing we can guarantee
        return Diagnostic.OK_INSTANCE;
	}
	
	private Diagnostic validateNumericPropertyAccessorBase(IObjectSymbolBasedValueType firstArg, 
															ValueType secondArg)
	{
        try
        {
        	// secondArg must successfully coerce to integer
        	TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));
        	
        	if (secondArg instanceof LiteralType)
        	{
                // this will throw a TypeCoercionExceptino if it won't
                // coerce
                Integer integerValue = 
                    (Integer) ((LiteralType)secondArg).coerceToNumber(Integer.class);

        		if (integerValue.intValue() < 0)
                {
        		    return _diagnosticFactory.create_POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS(integerValue);
                }
        	}
            else
            {
                // if the argument is a non-literal string, we can't verify
                // that the coercion to integer won't throw an exception
                // at runtime
                if (TypeCoercer.typeIsString(secondArg.getSignature()))
                {
                    return _diagnosticFactory.create_UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED(OPERATOR_NAME_ARRAY_ACCESSOR);
                }
            }
            
            // TODO: attempt to detect ArrayIndexOutOfBoundsException
            return Diagnostic.OK_INSTANCE;
        }
        catch (TypeCoercionException e)
        {
        	return _diagnosticFactory.create_BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION(OPERATOR_NAME_ARRAY_ACCESSOR);
        }
	}
    
    private SignatureBasedType handlePerformNumericPropertyAccessorBase(IObjectSymbolBasedValueType firstArg, 
                                                            ValueType secondArg)
    {
        AbstractDTPropertyResolver propResolver = getPropertyResolver();
        int index = 0;
        if (secondArg instanceof LiteralType)
        {
            try {
                index = ((LiteralType)secondArg).coerceToNumber(Integer.class).intValue();
            } catch (TypeCoercionException e) {
                // suppress, just use index = 0
                // this maybe should be an assertion...
            }
        }
        
        final ISymbol symbol = 
            propResolver.getProperty(firstArg.getSymbol(), index);
        
        if (symbol instanceof IObjectSymbol)
        {
            return IObjectSymbolBasedValueType.getInstance(symbol);
        }
        
        // if can't be resolved, return null
        return null;
    }

    @Override
    protected String getOperatorName()
    {
        return Messages.getString("BracketOperator.Name"); //$NON-NLS-1$
    }

    /**
     * @return the property resolver for the current source file
     */
    private AbstractDTPropertyResolver getPropertyResolver()
    {
        final DesignTimeApplicationManager manager =
            DesignTimeApplicationManager.getInstance(_file.getProject());

        if (manager != null)
        {
            return manager.getPropertyResolver();
        }

        return null;
    }
}
