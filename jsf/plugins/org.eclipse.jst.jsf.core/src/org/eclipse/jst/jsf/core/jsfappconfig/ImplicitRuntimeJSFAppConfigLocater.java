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

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * ImplicitRuntimeJSFAppConfigLocater creates an {@link IJSFAppConfigProvider}
 * instance that provides an application configuration model that contains
 * implicit configuration elements that are specified as required to be
 * provided by a JSF implementation.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public class ImplicitRuntimeJSFAppConfigLocater extends AbstractJSFAppConfigLocater {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.AbstractJSFAppConfigLocater#startLocating()
	 */
	public void startLocating() {
		locateProviders();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.AbstractJSFAppConfigLocater#stopLocating()
	 */
	public void stopLocating() {
		//nothing to do
	}

	/**
	 * Adds an {@link IJSFAppConfigProvider} instance that provides an
	 * application configuration model that contains implicit configuration
	 * objects provided by a JSF implementation at runtime.
	 */
	public void locateProviders() {
		Set newConfigProviders = new LinkedHashSet();
		newConfigProviders.add(new ImplicitRuntimeJSFAppConfigProvider());
		updateConfigProviders(newConfigProviders);
	}

}
