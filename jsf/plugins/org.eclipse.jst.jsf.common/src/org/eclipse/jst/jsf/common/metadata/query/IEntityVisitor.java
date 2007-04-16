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

import org.eclipse.jst.jsf.common.metadata.Entity;

/**
 * Visitor interface for Entities
 * API: should we force extension through an abstract class?
 */
public abstract interface IEntityVisitor extends IMetaDataVisitor {
	/**
	 * Visit the entity
	 * @param entity
	 */
	public void visit(final Entity entity);
	/**
	 * Signal that the last visited entity is now completely 'visited'.  
	 * Needed to signal that all children have also been visited.
     * API: what is the contract here for implementers of this interface?
	 */
	public void visitCompleted();
}
