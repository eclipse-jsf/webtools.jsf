/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.set.constraint;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.sets.AxiomaticSet;
import org.eclipse.jst.jsf.common.sets.constraint.AbstractSetConstraint;

/**
 * A set constraint that is satisfied if a test set contains all of the specified
 * mustBeMembers.
 * 
 * <p><b>Provisional API - subject to change</b></p>
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
            0, Messages.MemberConstraint_Failure, complement.toArray());
    }

}
