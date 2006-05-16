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
package org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Abstract implementation of {@link IJSFAppConfigLocater} that provides common
 * locater functionality. {@link IJSFAppConfigLocater} implementations should
 * extend this class or provide similar functionality.
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class AbstractJSFAppConfigLocater implements IJSFAppConfigLocater {

	/**
	 * {@link JSFAppConfigManager} instance to which this locater belongs.
	 */
	protected JSFAppConfigManager manager = null;

	/**
	 * Set of known {@link IJSFAppConfigProvider} instances.
	 */
	protected Set configProviders = new LinkedHashSet();

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.IJSFAppConfigLocater#setJSFAppConfigManager(org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.JSFAppConfigManager)
	 */
	public void setJSFAppConfigManager(JSFAppConfigManager manager) {
		this.manager = manager;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.IJSFAppConfigLocater#getJSFAppConfigManager()
	 */
	public JSFAppConfigManager getJSFAppConfigManager() {
		return manager;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.IJSFAppConfigLocater#startLocating()
	 */
	public abstract void startLocating();

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.IJSFAppConfigLocater#stopLocating()
	 */
	public abstract void stopLocating();

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.IJSFAppConfigLocater#getJSFAppConfigProviders()
	 */
	public Set getJSFAppConfigProviders() {
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
	protected boolean addConfigProvider(IJSFAppConfigProvider configProvider) {
		boolean added = configProviders.add(configProvider);
		if (added && manager != null) {
			configProvider.setJSFAppConfigLocater(this);
			manager.notifyJSFAppConfigProvidersChangeListeners(
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
	protected boolean removeConfigProvider(IJSFAppConfigProvider configProvider) {
		if (configProvider != null) {
			configProvider.releaseFacesConfigModel();
		}
		boolean removed = configProviders.remove(configProvider);
		if (removed && manager != null) {
			manager.notifyJSFAppConfigProvidersChangeListeners(
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
	protected void updateConfigProviders(Set newConfigProviders) {
		if (newConfigProviders != null) {
			LinkedHashSet oldConfigProviders = new LinkedHashSet();
			//iterate over existing set
			Iterator itConfigProviders = configProviders.iterator();
			while (itConfigProviders.hasNext()) {
				IJSFAppConfigProvider configProvider = (IJSFAppConfigProvider)itConfigProviders.next();
				//remove provider from new set if it is already in existing set
				if (!newConfigProviders.remove(configProvider)) {
					//stage removal of existing provider that is not in new set
					oldConfigProviders.add(configProvider);
				}
			}
			//remove providers that are not in new set from existing set
			Iterator itOldConfigProviders = oldConfigProviders.iterator();
			while (itOldConfigProviders.hasNext()) {
				IJSFAppConfigProvider configProvider = (IJSFAppConfigProvider)itOldConfigProviders.next();
				//call removeConfigProvider(...) method so manager's listeners are notified
				removeConfigProvider(configProvider);
			}
			//add providers that are still in new set to existing set
			Iterator itNewConfigProviders = newConfigProviders.iterator();
			while (itNewConfigProviders.hasNext()) {
				IJSFAppConfigProvider configProvider = (IJSFAppConfigProvider)itNewConfigProviders.next();
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
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() {
		removeAllConfigProviders();
	}

}
