/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.sets;

import java.util.HashSet;

/**
 * Implements a simple axiomatic set based on a hashset
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
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
