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
package org.eclipse.jst.jsf.common.sets.constraint;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.sets.AxiomaticSet;

/**
 * Super-class of all axiomatic set constraints.
 * 
 * A set constraint applies one or more pre-conditions on a set
 * and returns a diagnostic that flags whether or not the set
 * satisfies that constraint or not.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractSetConstraint 
{
    /**
     * The following post-conditions must hold:
     * 
     * 1) if the set satisfies the constraint with no caveats, 
     * the returned diagnostic must in turn satisfy 
     * Diagnostic.getSeverity() == Diagnostic.OK
     * 
     * 2) if the set does not satisfy the constraint, the returned diagnostic
     * must satisfy Diagnostic.getSeverity() == Diagnostic.ERROR
     * 
     * 3) if the set strictly satisfies the constraint but the constraint includes
     * parameters that can only be heuristically applied, the constraint may choose
     * to return a diagnostic where Diagnostic.getSeverity() == Diagnostic.WARNING
     * 
     * The diagnostic returned should _never_ have any other severity but OK, ERROR or WARNING
     * 
     * Algorithmically:
     * 
     * Constraint is satisfied can be tested by Diagnostic.getSeverity() != ERROR
     * Constraint is not satisfied can be tested by Diagnostic.getSeverity() == ERROR
     * 
     * @param set
     * @return a diagnostic object that contains information about whether
     * or not the constraint is satisfied for set. 
     * <b>MUST NOT RETURN NULL</b>
     */
    public abstract Diagnostic isSatisfied(AxiomaticSet set);
    
    /**
     * Convenience method
     * 
     * @param set
     * @return true if the constraint is satisfied for set
     */
    public final boolean passesConstraint(AxiomaticSet set)
    {
        return isSatisfied(set).getSeverity() != Diagnostic.ERROR;
    }
    
    /**
     * Convenience method
     * 
     * @param set
     * @return true if the constraint is <b>not</b> satisfied for set
     */
    public final boolean failsConstraint(AxiomaticSet set)
    {
        return !passesConstraint(set);
    }
}
