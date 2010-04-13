/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.jsfappconfig;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;

/**
 * Abstract implementation of {@link IJSFAppConfigLocater} that provides common
 * locater functionality. {@link IJSFAppConfigLocater} implementations MUST
 * extend this class or provide similar functionality.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class AbstractJSFAppConfigLocater implements IJSFAppConfigLocater {

	/**
	 * {@link JSFAppConfigManager} instance to which this locater belongs.
	 * @deprecated - DO NOT USE
	 */
	protected JSFAppConfigManager manager = null;
	
	/**
	 * {@link IJSFAppConfigManager} instance to which this locater belongs.
	 */
	private IJSFAppConfigManager _manager;

	/**
	 * Set of known {@link IJSFAppConfigProvider} instances.
	 */
	protected Set configProviders = new LinkedHashSet();
	
	public void setJSFAppConfigManager(final IJSFAppConfigManager manager) {
		this._manager = manager;
	}
	
	public IJSFAppConfigManager getJSFAppConfigManager() {
		return _manager;
	}

	public abstract void startLocating();

	public abstract void stopLocating();

	public Set<IJSFAppConfigProvider> getJSFAppConfigProviders() {
		return configProviders;
	}

	/**
	 * Adds an {@link IJSFAppConfigProvider} instance to the set of known
	 * instances and notifies {@link JSFAppConfigManager} instance of the
	 * addition if successful.
	 * 
	 * @param configProvider {@link IJSFAppConfigProvider} instance to be
	 * added.
	 * @return true if instance was added, else false.
	 */
	protected boolean addConfigProvider(final IJSFAppConfigProvider configProvider) {
		final boolean added = configProviders.add(configProvider);
		if (added && getJSFAppConfigManager() != null) {
			configProvider.setJSFAppConfigLocater(this);
			getJSFAppConfigManager().notifyJSFAppConfigProvidersChangeListeners(
					configProvider,
					JSFAppConfigProvidersChangeEvent.ADDED);
		}
		return added;
	}

	/**
	 * Removes an {@link IJSFAppConfigProvider} instance from the set of known
	 * instances and notifies {@link JSFAppConfigManager} instance of the
	 * removal if successful.
	 * 
	 * @param configProvider {@link IJSFAppConfigProvider} instance to be
	 * removed.
	 * @return true if instance was removed, else false.
	 */
	protected boolean removeConfigProvider(final IJSFAppConfigProvider configProvider) {
		if (configProvider != null) {
			configProvider.releaseFacesConfigModel();
		}
		final boolean removed = configProviders.remove(configProvider);
		if (removed && getJSFAppConfigManager() != null) {
			getJSFAppConfigManager().notifyJSFAppConfigProvidersChangeListeners(
					configProvider,
					JSFAppConfigProvidersChangeEvent.REMOVED);
		}
		return removed;
	}

	/**
	 * Updates known set of {@link IJSFAppConfigProvider} instances by removing
	 * instances that are in the existing set but not in the passed set and
	 * adding instances that are in the passed set but not in the existing set.
	 * Instances that are in both the existing set and the passed set are left
	 * unchanged. The {@link JSFAppConfigManager} is notified of removals and
	 * additions, and no notification is sent for instances that are left
	 * unchanged.
	 * 
	 * @param newConfigProviders New set of {@link IJSFAppConfigProvider}
	 * instances. 
	 */
	protected void updateConfigProviders(final Set newConfigProviders) {
		if (newConfigProviders != null) {
			final LinkedHashSet oldConfigProviders = new LinkedHashSet();
			//iterate over existing set
			final Iterator itConfigProviders = configProviders.iterator();
			while (itConfigProviders.hasNext()) {
				final IJSFAppConfigProvider configProvider = (IJSFAppConfigProvider)itConfigProviders.next();
				//remove provider from new set if it is already in existing set
				if (!newConfigProviders.remove(configProvider)) {
					//stage removal of existing provider that is not in new set
					oldConfigProviders.add(configProvider);
				}
			}
			//remove providers that are not in new set from existing set
			final Iterator itOldConfigProviders = oldConfigProviders.iterator();
			while (itOldConfigProviders.hasNext()) {
				final IJSFAppConfigProvider configProvider = (IJSFAppConfigProvider)itOldConfigProviders.next();
				//call removeConfigProvider(...) method so manager's listeners are notified
				removeConfigProvider(configProvider);
			}
			//add providers that are still in new set to existing set
			final Iterator itNewConfigProviders = newConfigProviders.iterator();
			while (itNewConfigProviders.hasNext()) {
				final IJSFAppConfigProvider configProvider = (IJSFAppConfigProvider)itNewConfigProviders.next();
				//call addConfigProvider(...) method so manager's listeners are notified
				addConfigProvider(configProvider);
			}
		}
	}

	/**
	 * Removes all {@link IJSFAppConfigProvider} instances from the known set,
	 * sending notification of all removals to the {@link JSFAppConfigManager}
	 * instance.
	 */
	protected void removeAllConfigProviders() {
		updateConfigProviders(Collections.EMPTY_SET);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigLocater#dispose()
	 */
    public void dispose() {
        removeAllConfigProviders();
    }
}
