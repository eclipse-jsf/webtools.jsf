package org.eclipse.jst.jsf.context.symbol.internal.util;

import org.eclipse.jst.jsf.common.internal.types.MethodType;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol;

/**
 * 
 * @author cbateman
 *
 */
public class IMethodSymbolBasedType extends MethodType 
{
    private final IMethodSymbol _methodSymbol;
    
    /**
     * @param methodSymbol
     */
    public IMethodSymbolBasedType(IMethodSymbol  methodSymbol) 
    {
        super(methodSymbol.getName(), methodSymbol.getSignature());
        _methodSymbol = methodSymbol;
    }

    /**
     * @return the method symbol
     */
    public IMethodSymbol getSymbol()
    {
        return _methodSymbol;
    }
}
