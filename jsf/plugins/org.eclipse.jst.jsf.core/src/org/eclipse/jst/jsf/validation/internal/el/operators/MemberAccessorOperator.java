package org.eclipse.jst.jsf.validation.internal.el.operators;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.internal.types.LiteralType;
import org.eclipse.jst.jsf.common.internal.types.SignatureBasedType;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercer;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.util.IMethodSymbolBasedType;
import org.eclipse.jst.jsf.context.symbol.internal.util.IObjectSymbolBasedValueType;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.internal.provisional.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.internal.provisional.el.AbstractDTMethodResolver;
import org.eclipse.jst.jsf.designtime.internal.provisional.el.AbstractDTPropertyResolver;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Super-class for all operators whose function is to access members of an
 * EL object. i.e. the "." and "[]" operators
 * 
 * @author cbateman
 *
 */
public abstract class MemberAccessorOperator 
{
    /**
     * The source file for the EL expression in which this operator
     * is being evaluated.
     */
    protected final IFile         _file;

    // TODO: need to reconcile with BinaryOperator? performOperation must return
    // SignatureBasedType since it may return a method.  This can't happen
    // with other operators (besides eqiv [])
    /**
     * @param file 
     */
    protected MemberAccessorOperator(final IFile file)
    {
        _file = file;
    }

    /**
     * @param firstArg
     * @param secondArg
     * @return the result of validating the dot operation with these arguments.
     */
    public Diagnostic validate(ValueType firstArg, ValueType secondArg) 
    {
        if (!(firstArg instanceof IObjectSymbolBasedValueType))
        {
            throw new AssertionError("The first argument of the dot operator must always be a symbol resolvable value type");
        }
        
        if (TypeCoercer.typeIsNull(secondArg.getSignature()))
        {
            return DiagnosticFactory.create_BINARY_OP_DOT_WITH_VALUEB_NULL();
        }

        final IObjectSymbolBasedValueType firstArgSymbol =
            (IObjectSymbolBasedValueType) firstArg;
        
        if (secondArg instanceof LiteralType)
        {
            final IObjectSymbol curBaseSymbol = firstArgSymbol.getSymbol();

            final ISymbol nextSymbol = 
                getMemberSymbol(firstArgSymbol.getSymbol(), 
                        ((LiteralType)secondArg).getLiteralValueRaw());

            // if the x in x.y is an unconstrained map an it returns
            // a java.lang.Object, then return null.  We can't really say
            // anything meaningful about such a property anyway.
            // TODO: do we need to refine the type descriptor on such 
            // a property object to make this more precise?
            if (curBaseSymbol.supportsCoercion(TypeConstants.TYPE_MAP)
                    && nextSymbol instanceof IPropertySymbol
                    && TypeConstants.TYPE_JAVAOBJECT.equals(((IPropertySymbol)nextSymbol).getTypeDescriptor().getTypeSignature()))
            {
                // if we get a symbol back that's a generic object coming from a map
                // then stop validating; we can't tell anything for sure
                return Diagnostic.OK_INSTANCE;
            }

            if (nextSymbol == null)
            {
                return DiagnosticFactory.create_MEMBER_NOT_FOUND(((LiteralType)secondArg).getLiteralValue()
                        ,firstArgSymbol.getSymbol().getName());
            }
        }

        return Diagnostic.OK_INSTANCE;
    }
    
    /**
     * @param firstArg
     * @param secondArg
     * @return the resolved type for the operation or null if not computable
     */
    public SignatureBasedType performOperation(ValueType firstArg, ValueType secondArg) 
    {
        if (firstArg instanceof IObjectSymbolBasedValueType)
        {
            return handlePerformSymbolDotValue((IObjectSymbolBasedValueType)firstArg, secondArg);
        }

        return null;
    }

    /**
     * @param firstArg -- represents value-a (expr-a after step 1) in JSP.2.3.4
     * @param secondArg -- represents value-b (expr-b after step 3) in JSP.2.3.4
     * @return the new ValueType for this operation or null
     */
    protected SignatureBasedType handlePerformSymbolDotValue(IObjectSymbolBasedValueType firstArg
                                                  , ValueType secondArg)
    {
        if (secondArg instanceof LiteralType)
        {
            // per JSP.2.3.4, if value-b is null, then return null (not literal null)
            if (TypeCoercer.typeIsNull(secondArg.getSignature()))
            {
                return null;
            }

            ISymbol symbol = 
                getMemberSymbol(firstArg.getSymbol(), ((LiteralType)secondArg).getLiteralValueRaw());

            if (symbol instanceof IPropertySymbol)
            {
//                // if we get back a bounded property, then 
//                if (firstArg.getSymbol().supportsCoercion(TypeConstants.TYPE_MAP)
//                        && TypeConstants.TYPE_JAVAOBJECT.equals(((IPropertySymbol)symbol).getTypeDescriptor().getTypeSignature()))
//                {
//                    // TODO: another draw back of this is that assignability is lost
//                    // even if java type can't determined.  This cannot be a permanent
//                    // solution.
//                    return null;
//                }
                
                return new IObjectSymbolBasedValueType((IPropertySymbol)symbol);
            }
            else if (symbol instanceof IMethodSymbol)
            {
                return new IMethodSymbolBasedType((IMethodSymbol) symbol);
            }
            
            // fall-through and return null
        }

        // if we don't have a literal value with which to derive value-b, then
        // we can't get a property
        return null;
    }

    
    /**
     * @param symbol
     * @param name
     * @return the member symbol of 'symbol' corresponding to 'name' or
     * null if there is no such member
     */
    protected ISymbol getMemberSymbol(final IObjectSymbol symbol, final Object name)
    {
        ISymbol  memberSymbol = getPropertySymbol(symbol, name);

        if (memberSymbol != null)
        {
            return memberSymbol;
        }

        memberSymbol = getMethodSymbol(symbol, name);
        
        // otherwise, see if it's a valid method
        if (memberSymbol != null)
        {
            return memberSymbol;
        }
        
        // if not a property or method, then not a valid member
        return null;
    }
    
    /**
     * @param symbol
     * @param name
     * @return the property symbol called name relative to 'symbol' or null
     * if one doesn't exist
     */
    protected ISymbol getPropertySymbol(final ISymbol symbol, final Object name)
    {
        AbstractDTPropertyResolver resolver = getPropertyResolver();
        
        if (resolver != null)
        {
            return resolver.getProperty(symbol,name);
        }
        
        JSFCorePlugin.log("Error acquiring property resolver", new Throwable());
        return null;
    }

    /**
     * @param symbol
     * @param name
     * @return the method symbol on 'symbol' corresponding to
     * 'name' or null if no such member
     */
    protected IMethodSymbol getMethodSymbol(final IObjectSymbol symbol, final Object name)
    {
        AbstractDTMethodResolver resolver = getMethodResolver();
        
        if (resolver != null)
        {
            return resolver.getMethod(symbol, name);
        }
        
        JSFCorePlugin.log("Error acquiring property resolver", new Throwable());
        return null;

    }
    
    /**
     * @return the property resolver for the current source file
     */
    protected AbstractDTPropertyResolver  getPropertyResolver()
    {
        return
            DesignTimeApplicationManager.getInstance(_file.getProject())
                .getPropertyResolver();
    }
    
    /**
     * @return the method resolver for the current source file
     */
    protected AbstractDTMethodResolver getMethodResolver()
    {
        return
            DesignTimeApplicationManager.getInstance(_file.getProject())
                .getMethodResolver();
    }
}
