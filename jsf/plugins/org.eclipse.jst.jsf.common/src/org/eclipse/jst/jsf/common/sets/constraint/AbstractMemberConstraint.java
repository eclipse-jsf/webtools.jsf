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
 * A set constraint that is specific to members of the set.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author cbateman
 *
 */
public class AbstractMemberConstraint extends AbstractSetConstraint {

    public Diagnostic isSatisfied(AxiomaticSet set) {
        return null;
    }
}
