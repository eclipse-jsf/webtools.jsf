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
 * Interface for notification when an {@link IMetaDataObservable} changes
 *
 */
public interface IMetaDataObserver {
	/**
	 * Callback to observer when an {@link IMetaDataObservable} throws an event
	 * @param event
	 */
	public void notifyMetadataChanged(IMetaDataChangeNotificationEvent event);
}
