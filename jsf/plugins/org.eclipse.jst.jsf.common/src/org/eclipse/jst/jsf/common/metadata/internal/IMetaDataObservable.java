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
package org.eclipse.jst.jsf.common.metadata.internal;

/**
 * Allows {@link IMetaDataObserver}s to observe changes.
 * Experimental.
 *
 */
public interface IMetaDataObservable {
	/**
	 * Add the observer to the set of observers.  Has no effect if
	 * the same observer is already registered
	 * 
	 * @param observer
	 */
	public void addObserver(IMetaDataObserver observer);

	/**
	 * Remove the observer from the set of observers.  Has no effect
	 * if observer was not already registered.
	 * 
	 * @param observer must not be null
	 */
	public void removeObserver(IMetaDataObserver observer);
}
