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

import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelManager;


/**
 * Abstract {@link IMetaDataQuery} that all implementers must extend.
 * <p>
 * Provides a default {@link IMetaDataQueryHelper} that can be overridden.
 */
public abstract class AbstractMetaDataQuery implements IMetaDataQuery{

	private final IMetaDataModelManager 		_manager;
	private final IMetaDataDomainContext 		_managerContext;
	private IMetaDataQueryHelper 				_helper;

	/**
	 * @param manager
	 * @param managerContext 
	 */
	public AbstractMetaDataQuery(final IMetaDataModelManager manager, final IMetaDataDomainContext managerContext) {
		_manager = manager;
		_managerContext = managerContext;
	}

	public IMetaDataQueryHelper getQueryHelper() {
		if (_helper == null) {
			_helper = new MetaDataQueryHelper(getManager(), getDomainContext());
		}
		return _helper;
	}
	
	public void setQueryHelper(final IMetaDataQueryHelper helper) {
		_helper = helper;
	}
	
	/**
	 * NOT API
	 * @return IMetaDataModelManager
	 */
	public final IMetaDataModelManager getManager() {
		return _manager;
	}

	/**
	 * NOT API
	 * @return IMetaDataModelManagerContext
	 */
	public final IMetaDataDomainContext getDomainContext() {
		return _managerContext;
	}
	
	/**
	 * Convenience method
	 * @return domain id
	 */
	protected final String getDomainId() {
		return getDomainContext().getDomainId();
	}

}
