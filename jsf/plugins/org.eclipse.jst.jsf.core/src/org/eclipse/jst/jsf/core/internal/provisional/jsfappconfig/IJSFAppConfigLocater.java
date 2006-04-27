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

/**
 * IJSFAppConfigLocater is the interface that Objects capable of locating
 * JSF application configuration resource files and creating
 * {@link IJSFAppConfigProvider} instances to add to a
 * {@link JSFAppConfigManager} instance must implement.
 * 
 * @author Ian Trimble - Oracle
 */
public interface IJSFAppConfigLocater {

	/**
	 * Locates and adds {@link IJSFAppConfigProvider} instances to the passed
	 * {@link JSFAppConfigManager} instance.
	 * 
	 * @param manager {@link JSFAppConfigManager} instance to which
	 * {@link IJSFAppConfigProvider} instances are added.
	 */
	public void locateProviders(JSFAppConfigManager manager);

}
