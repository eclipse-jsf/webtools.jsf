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

import org.eclipse.jst.jsf.common.metadata.Entity;

/**
 * Visitor interface for Entities
 * <p>NOT to implemented by clients directly.   Clients should subclass AbstractEntityVisitor instead.
 * <p><b>Provisional API - subject to change</b></p>
 */
public interface IEntityVisitor extends IMetaDataVisitor {
	/**
	 * Visit the entity. 
	 * The entity and then it's children are visited
	 * @param entity - must not be NULL
	 */
	public void visit(final Entity entity);
	/**
	 * Signal that the entity and all it's children is now completely 'visited'.  
	 * The entity will call this method at the end of the accept method.
	 * @param entity - must not be NULL
	 */
	public void visitCompleted(Entity entity);
}
