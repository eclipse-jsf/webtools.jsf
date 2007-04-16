package org.eclipse.jst.jsf.common.sets.constraint;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.sets.AxiomaticSet;

/**
 * A set constraint that is specific to members of the set.
 * @author cbateman
 *
 */
public class AbstractMemberConstraint extends AbstractSetConstraint {

    public Diagnostic isSatisfied(AxiomaticSet set) {
        return null;
    }
}
