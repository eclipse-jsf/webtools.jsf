package org.eclipse.jst.jsf.core.internal.types;

import org.eclipse.jdt.core.Signature;


/**
 * Encapsulates the runtime type or types of a JSF entity in a 
 * way that can be compared to other entities
 * 
 * @author cbateman
 *
 */
public class CompositeType 
{
    private final String[]  _signatures;
    private final int       _assignmentType;
    
    private boolean[]       _isTypeSignature; // = null lazily derived from signatures
                                              // on first access
    /**
     * @param signatureStrings
     * @param assignmentType 
     */
    public CompositeType(String[] signatureStrings, int  assignmentType)
    {
        if (signatureStrings == null
                || signatureStrings.length < 1)
        {
            throw new AssertionError("Must specify at least one signature string");
        }
        
        _signatures = new String[signatureStrings.length];
        System.arraycopy(signatureStrings, 0, _signatures, 0, _signatures.length);
        _assignmentType = assignmentType;
    }
    
    /**
     * Convenience constructor for most common case where composite only 
     * consistes of a single type signature
     * 
     * @param signatureString
     * @param assignmentType
     */
    public CompositeType(String signatureString, int assignmentType)
    {
        this(new String[]{signatureString}, assignmentType);
    }
    /**
     * @return the assignment type mask
     */
    public int getAssignmentTypeMask()
    {
        return _assignmentType;
    }
    
    /**
     * @return true if the composite type supports being on the LHS of an
     * assignment
     */
    public boolean isLHS()
    {
        return TypeUtil.matchesLHS(_assignmentType);
    }
    
    /**
     * @return true if the composite type supports being on the RHS of an
     * assignment
     */
    public boolean isRHS()
    {
        return TypeUtil.matchesRHS(_assignmentType);
    }

    /**
     * @return an array of booleans.  The value in each index of the array
     * is true if the corresponding position _signatures corresponds to a type
     * signature and false if it's a method signature
     */
    public boolean[] getIsTypeSignature()
    {
        return getTypeSignatureFlags();
    }
    
    /**
     * @return the type signatures.  Changes to the returned form do not
     * affect the internal values
     */
    public String[] getSignatures()
    {
        final String[] copy = new String[_signatures.length];
        System.arraycopy(_signatures, 0, copy, 0, _signatures.length);
        return copy;
    }
    
    public String toString()
    {
        final StringBuffer stringBuffer = new StringBuffer();
        
        for (int i = 0; i < _signatures.length; i++)
        {
            stringBuffer.append(_signatures[i]);
            stringBuffer.append(" | ");
        }
        
        return stringBuffer.toString();
    }
    
    /**
     * @return a version of to string with of the type signatures replaced
     * with their more Javaeseque names
     */
    public String toUserReadableString()
    {
        final StringBuffer stringBuffer = new StringBuffer();
        
        for (int i = 0; i < _signatures.length; i++)
        {
            final String signature = _signatures[i];
            
            if (getTypeSignatureFlags()[i])
            {
                stringBuffer.append(Signature.getSignatureSimpleName(signature));
            }
            
            if (i < _signatures.length -1)
            {
                stringBuffer.append(", ");
            }
        }
        return stringBuffer.toString();
    }
    
    private boolean[] getTypeSignatureFlags()
    {
        if (_isTypeSignature == null)
        {
            _isTypeSignature = new boolean[_signatures.length];
            
            for (int i = 0; i < _signatures.length; i++)
            {
                try
                {
                    Signature.getTypeSignatureKind(_signatures[i]);
                    
                    // if an exception wasn't thrown above, then it
                    // is some sort of type signature
                    _isTypeSignature[i] = true;
                }
                catch (IllegalArgumentException ae)
                {
                    // getTypeSignatureKind threw an exception, so
                    // this signature is a method
                    _isTypeSignature[i] = false;
                }
            }
        }
        
        return _isTypeSignature;
    }
}
