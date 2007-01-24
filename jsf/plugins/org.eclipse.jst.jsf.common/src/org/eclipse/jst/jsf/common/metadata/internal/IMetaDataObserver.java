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
package org.eclipse.jst.jsf.common.metadata.internal;

/**
 * Interface for notification when an {@link IMetaDataObservable} changes
 *
 */
public interface IMetaDataObserver {
	/**
	 * Callback to observer when an {@link IMetaDataObservable} throws an event
	 * @param IMetaDataChangeNotificationEvent
	 */
	public void notifyMetadataChanged(IMetaDataChangeNotificationEvent event);
}
