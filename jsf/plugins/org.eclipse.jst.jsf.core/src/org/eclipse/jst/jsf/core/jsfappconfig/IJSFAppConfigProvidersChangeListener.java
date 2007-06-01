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

/**
 * Interface that an Object wishing to be notified about changes to the Set of
 * IJSFAppConfigProvider instances implements.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle.
 */
public interface IJSFAppConfigProvidersChangeListener {

	/**
	 * Callback method indicating a change in the Set of JSFAppConfigProvider
	 * instances.
	 * 
	 * @param event JSFAppConfigProvidersChangeEvent instance.
	 */
	public void changedJSFAppConfigProviders(JSFAppConfigProvidersChangeEvent event);

}
