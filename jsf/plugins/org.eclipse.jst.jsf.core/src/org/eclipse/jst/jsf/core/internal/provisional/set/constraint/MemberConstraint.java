package org.eclipse.jst.jsf.core.internal.provisional.set.constraint;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.sets.internal.provisional.AxiomaticSet;
import org.eclipse.jst.jsf.common.sets.internal.provisional.constraint.AbstractSetConstraint;

/**
 * A set constraints that satisfies if a test set contains all of the specified
 * mustBeMembers.
 * 
 * @author cbateman
 *
 */
public class MemberConstraint extends AbstractSetConstraint 
{
    private final AxiomaticSet      _mustBeMembers;
    
    /**
     * @param mustBeMembers -- the set of objects that must occur in the
     * input set for the constraint to satisfy.  
     */
    public MemberConstraint(AxiomaticSet  mustBeMembers)
    {
        _mustBeMembers = mustBeMembers;
    }
    
    /**
     * Members of set must be comparable with thos in mustBeMembers using equals()
     * @param set 
     * @return whether or not the constraint is satisfied.  If not satisfied, the
     * diagnostic's data array will contain the objects that are missing.
     */
    public Diagnostic isSatisfied(AxiomaticSet set) 
    {
        // the constraint is satisfied iff _mustBeMembers is a subset
        // of set.  In other words, if every member of _mustBeMembers
        // is also in set.
        
        // we  want to take _mustBeMembers - set.  If the result is empty, then
        // all of the required elements are members of set.  If the set is non-empty,
        // this tells us what is missing
        final AxiomaticSet complement = _mustBeMembers.subtract(set);
        
        if (complement.isEmpty())
        {
            return Diagnostic.OK_INSTANCE;
        }

        return new BasicDiagnostic(Diagnostic.ERROR, this.getClass().getName(),
            0, "Failed membership constraint", complement.toArray());
    }

}
