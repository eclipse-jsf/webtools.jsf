package org.eclipse.jst.jsf.core.internal.types;

import org.eclipse.jdt.core.Signature;

/**
 * Represents a BooleanLiteral as defined by JSP.2.9
 * 
 * @author cbateman
 *
 */
public class BooleanLiteralType extends LiteralType 
{
    /**
     * The literal FALSE singleton
     */
    public final static BooleanLiteralType  FALSE = new BooleanLiteralType(false);
    /**
     * The literal TRUE singleton
     */
    public final static BooleanLiteralType  TRUE = new BooleanLiteralType(true);
    
    private final boolean       _literalValue;
    
    /**
     * @param literalValue
     */
    /*package*/BooleanLiteralType(boolean  literalValue)
    {
        super(Signature.SIG_BOOLEAN);
        _literalValue = literalValue;
    }
    
    public Number coerceToNumber(Class T) throws TypeCoercionException 
    {
        // illegal to coerce boolean to number per JSP.2.8.3 step 3
        throw new TypeCoercionException("Cannot coerce boolean to number");
    }

    public String getLiteralValue() 
    {
        return Boolean.toString(_literalValue);
    }

    public Boolean coerceToBoolean() throws TypeCoercionException 
    {
        return Boolean.valueOf(_literalValue);
    }

}
