/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.query.internal;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor;

/**
 * Provides the necessary hierarchical visitor interface methods to 
 * provide conditional navigation of the entity hierarchy 
 *
 */
public interface IHierarchicalEntityVisitor extends IEntityVisitor {
	/**
	 * @param entity
	 * @return true if children should be traversed 
	 */
	boolean visitEnter(Entity entity); 
	/**
	 * @param entity
	 * @return true when coming out of a branch
	 */
	boolean visitLeave(Entity entity);
	

}
