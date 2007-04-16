/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.query;

import org.eclipse.jst.jsf.common.metadata.Trait;

/**
 * Visitor interface for Traits
 *
 */
public abstract interface ITraitVisitor extends IMetaDataVisitor {
	/**
	 * Visit the Trait
	 * @param trait
     * API: what is the responsibilit of the implementor? pre/post-conditions?
     * API: what is the order of the visitation (or should it not be assumed)
	 */
	abstract void visit(final Trait trait);
}
