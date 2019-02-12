/*******************************************************************************
 * Copyright (c) 2005, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.jsfappconfig;

import java.util.Set;

import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;

/**
 * IJSFAppConfigLocater is the interface that Objects capable of locating
 * and providing JSF application configuration resources must implement.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @noimplement - users must extend {@link AbstractJSFAppConfigLocater}
 * @author Ian Trimble - Oracle
 */
public interface IJSFAppConfigLocater {

	/**
	 * Sets the {@link IJSFAppConfigManager} instance to which this locater
	 * belongs.
	 * 
	 * @param manager {@link IJSFAppConfigManager} instance to be set.
	 */
	public void setJSFAppConfigManager(IJSFAppConfigManager manager);

	/**
	 * Gets the {@link IJSFAppConfigManager} instance to which this locater
	 * belongs.
	 * 
	 * @return {@link IJSFAppConfigManager} instance to which this locater
	 * belongs.
	 */
	public IJSFAppConfigManager getJSFAppConfigManager();

	/**
	 * Starts locating JSF application configuration resources.
	 */
	public void startLocating();

	/**
	 * Stops locating JSF application configuration resources.
	 */
	public void stopLocating();

	/**
	 * Disposes of the instance.
	 */
    public void dispose();

	/**
	 * Gets the set of {@link IJSFAppConfigProvider} instances that this
	 * locater has located.
	 * 
	 * @return set of {@link IJSFAppConfigProvider} instances.
	 */
	public Set<IJSFAppConfigProvider> getJSFAppConfigProviders();

}
