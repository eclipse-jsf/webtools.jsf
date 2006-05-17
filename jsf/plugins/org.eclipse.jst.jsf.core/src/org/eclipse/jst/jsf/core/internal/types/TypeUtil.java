package org.eclipse.jst.jsf.core.internal.types;

/**
 * Package utility class
 * @author cbateman
 *
 */
/*package*/final class TypeUtil 
{
    /**
     * @param assignmentType
     * @return true if assignmentType has lhs flag set
     */
    public static boolean matchesLHS(int assignmentType)
    {
        return (assignmentType & IAssignable.ASSIGNMENT_TYPE_LHS) != 0;
    }
    
    /**
     * @param assignmentType
     * @return true if assignmentType has rhs flag set
     */
    public  static boolean matchesRHS(int assignmentType)
    {
        return (assignmentType & IAssignable.ASSIGNMENT_TYPE_RHS) != 0;
    }

    private TypeUtil() {/*not instantiable*/}
}
