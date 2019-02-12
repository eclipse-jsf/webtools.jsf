/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.query;

import org.eclipse.jst.jsf.common.metadata.Trait;

/**
 * Visitor interface for Traits
 * <p>NOT to implemented by clients directly.   Clients should subclass AbstractTraitVisitor instead.
 * <p><b>Provisional API - subject to change</b></p>
 */
public interface ITraitVisitor extends IMetaDataVisitor {
	/**
	 * Visit the Trait.
	 * Implementer cannot assume ordering of trait visiting. 
	 * @param trait - must not be null
 	 */
	public void visit(final Trait trait);
	
	/**
	 * Signal that the the trait has been completely visited
	 * @param trait
	 */
	public void visitCompleted(final Trait trait);
}
