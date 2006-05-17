package org.eclipse.jst.jsf.core.internal.types;

import org.eclipse.jdt.core.Signature;

/**
 * @author cbateman
 *
 */
public class ValueType implements SignatureBasedType, IAssignable
{
    private final String        _signature;
    private final int           _assignmentMask;

    /**
     * Construct a new ValueType object with the given
     * signature
     * 
     * @param signature
     * @param assignmentMask 
     */
    public ValueType(final String signature, final int assignmentMask) 
    {
        if (signature == null)
        {
            throw new AssertionError("signature can never be null");
        }
    
        _signature = signature;
        _assignmentMask = assignmentMask;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.core.internal.types.SignatureBasedType#getSignature()
     */
    public String getSignature() 
    {
        return _signature;
    }

    public int getAssignability() {
        return _assignmentMask;
    }

    public boolean isLHS() {
        return TypeUtil.matchesLHS(_assignmentMask);
    }

    public boolean isRHS() {
        return TypeUtil.matchesRHS(_assignmentMask);
    }
    
    public String toString()
    {
        return Signature.getSignatureSimpleName
            (TypeTransformer.transformBoxPrimitives(_signature));
    }
}
