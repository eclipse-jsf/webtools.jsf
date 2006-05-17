package org.eclipse.jst.jsf.core.internal.types;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.jdt.core.Signature;

/**
 * Represents an FloatLiteral as defined by JSP.2.9
 * @author cbateman
 *
 */
public class FloatLiteralType extends NumericTypeLiteral 
{
    private final double     _literalValue;
    
    /**
     * @param literalValue
     */
    public FloatLiteralType(double literalValue)
    {
        // according to the notes to JSP.2.9, bullet 5, float literals are doubles
        super(Signature.SIG_DOUBLE);
        _literalValue = literalValue;
    }
    
    protected Number getBoxedValue() 
    {
        return new Double(_literalValue);
    }

    public Number coerceToNumber(Class T) throws TypeCoercionException 
    {
        if (T == BigInteger.class)
        {
            return new BigDecimal(_literalValue).toBigInteger();
        }
        else if (T == BigDecimal.class)
        {
            return new BigDecimal(_literalValue);
        }
        
        Number commonCoercion = super.coerceToNumber(T);

        if (commonCoercion == null)
        {
            throw new IllegalArgumentException("Not a target numeric type: "+T);
        }

        return commonCoercion;
    }

}
