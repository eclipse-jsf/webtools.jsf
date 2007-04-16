package org.eclipse.jst.jsf.common.sets;

import java.util.HashSet;

/**
 * Implements a simple axiomatic set based on a hashset
 * @author cbateman
 *
 */
public class ConcreteAxiomaticSet extends HashSet implements AxiomaticSet {

    /**
     * serial version id
     */
    private static final long serialVersionUID = 7094728081135008203L;

    public AxiomaticSet intersect(AxiomaticSet set) {
        AxiomaticSet intersection = new ConcreteAxiomaticSet();
        AxiomaticSetUtil.intersect(intersection, this, set);
        return intersection;
    }

    public boolean isEquivalent(AxiomaticSet toSet) {
        return equals(toSet);
    }

    public AxiomaticSet union(AxiomaticSet set) {
       AxiomaticSet union = new ConcreteAxiomaticSet();
       AxiomaticSetUtil.union(union, this, set);
       return union;
    }

    public boolean isDisjoint(AxiomaticSet set) {
        return AxiomaticSetUtil.isDisjoint(this, set);
    }

    public Object getFirstElement() {
        return iterator().next();
    }

    public AxiomaticSet subtract(AxiomaticSet set) {
       return AxiomaticSetUtil.subtract(this, set);
    }

}
