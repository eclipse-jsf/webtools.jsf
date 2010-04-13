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

import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;

/**
 * IJSFAppConfigProvider is the interface that Objects capable of providing
 * JSF application configuration models must implement.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @noimplement - should use {@link AbstractJSFAppConfigProvider} or {@link ArtifactEditJSFAppConfigProvider} if that config can be modified
 * @author Ian Trimble - Oracle
 * 
 */
public interface IJSFAppConfigProvider {

	/**
	 * Sets the {@link IJSFAppConfigLocater} instance that located this
	 * instance.
	 * 
	 * @param locater {@link IJSFAppConfigLocater} instance that located this
	 * instance.
	 */
	public void setJSFAppConfigLocater(IJSFAppConfigLocater locater);

	/**
	 * Gets the root element of the application configuration model for read
	 * access.
	 * 
	 * @return FacesConfigType instance, which is the root element of an
	 * application configuration model.
	 */
	public FacesConfigType getFacesConfigModel();

	/**
	 * Releases resources associated with acquiring the application
	 * configuration model (if any).
	 */
	public void releaseFacesConfigModel();

}
