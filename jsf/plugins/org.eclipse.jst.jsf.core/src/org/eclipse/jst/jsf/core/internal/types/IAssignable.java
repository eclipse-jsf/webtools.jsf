package org.eclipse.jst.jsf.core.internal.types;

/**
 * Defines the lhs/rhs rules for a type
 * @author cbateman
 *
 */
public interface IAssignable 
{
    /**
     * Type is none: it cannot be assigned to.  method binding.
     */
    public static int  ASSIGNMENT_TYPE_NONE = 0x0;
    /**
     * Type is lhs: it can be assigned to
     */
    public static int  ASSIGNMENT_TYPE_LHS = 0x1;
    
    /**
     * Type is rhs: it can be assigned from
     */
    public static int  ASSIGNMENT_TYPE_RHS = 0x2;
    
    /**
     * @return the assigment mask
     */
    public int  getAssignability();
    
    /**
     * @return true if ASSIGNMENT_TYPE_LHS is set in getAssignability
     */
    public boolean isLHS();
    /**
     * @return true if ASSIGNMENT_TYPE_RHS is set in getAssignability
     */
    public boolean isRHS();
}
