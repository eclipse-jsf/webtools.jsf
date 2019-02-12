/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query.internal;

import org.eclipse.jst.jsf.common.metadata.Entity;

/**
 * Matches entities
 */
public interface IEntityPredicateMatcher extends IPredicateMatcher<Entity> {
	/**
	 * Signals to matcher that you are moving down the hierarchy by one 
	 */
	public void pushLevel();
	/**
	 * Signals to matcher that you are moving up the hierarchy by one
	 */
	public void popLevel();
	
	/**
	 * @return the deepest level in the hierarchy that is being matched
	 */
	public int getMaxLevel();
	
	/**
	 * @return current level being matched in the hierarchy
	 */
	public int getCurrentLevel();
}
