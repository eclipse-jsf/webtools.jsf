package org.eclipse.jst.jsf.core.internal.types;

import org.eclipse.jdt.core.Signature;

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
    public static boolean matchesRHS(int assignmentType)
    {
        return (assignmentType & IAssignable.ASSIGNMENT_TYPE_RHS) != 0;
    }

    /**
     * @param signature
     * @return true if the signature is a method signature
     */
    public static boolean isMethodSignature(final String signature)
    {
        // method signature must start with a "("
        return signature.charAt(0) == Signature.C_PARAM_START;
    }
    private TypeUtil() {/*not instantiable*/}
}
