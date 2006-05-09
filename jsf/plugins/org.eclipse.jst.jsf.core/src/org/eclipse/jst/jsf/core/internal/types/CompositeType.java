package org.eclipse.jst.jsf.core.internal.types;


/**
 * Encapsulates the runtime type or types of a JSF entity in a 
 * way that can be compared to other entities
 * 
 * @author cbateman
 *
 */
public class CompositeType 
{
    /**
     * Type is lhs: it can be assigned to
     */
    public static int  ASSIGNMENT_TYPE_LHS = 0x1;
    
    /**
     * Type is rhs: it can be assigned from
     */
    public static int  ASSIGNMENT_TYPE_RHS = 0x2;
    
    private static boolean matchesLHS(int assignmentType)
    {
        return (assignmentType & ASSIGNMENT_TYPE_LHS) != 0;
    }
    
    private static boolean matchesRHS(int assignmentType)
    {
        return (assignmentType & ASSIGNMENT_TYPE_RHS) != 0;
    }
    
    private final String[]  _signatures;
    private final int       _assignmentType;
    
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
     * @return true if the composite type supports being on the LHS of an
     * assignment
     */
    public boolean isLHS()
    {
        return matchesLHS(_assignmentType);
    }
    
    /**
     * @return true if the composite type supports being on the RHS of an
     * assignment
     */
    public boolean isRHS()
    {
        return matchesRHS(_assignmentType);
    }

    
}
