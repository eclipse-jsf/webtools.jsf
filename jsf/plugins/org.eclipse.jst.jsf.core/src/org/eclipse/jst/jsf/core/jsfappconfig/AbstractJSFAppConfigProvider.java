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
 * Abstract implementation of {@link IJSFAppConfigProvider} that provides
 * common provider functionality. {@link IJSFAppConfigProvider} instances
 * should extend this class or provide similar functionality.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class AbstractJSFAppConfigProvider implements IJSFAppConfigProvider {

	/**
	 * {@link IJSFAppConfigLocater} instance that located this instance.
	 */
	protected IJSFAppConfigLocater jsfAppConfigLocater = null;

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigProvider#setJSFAppConfigLocater(org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigLocater)
	 */
	public void setJSFAppConfigLocater(IJSFAppConfigLocater locater) {
		this.jsfAppConfigLocater = locater;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigProvider#getFacesConfigModel()
	 */
	public abstract FacesConfigType getFacesConfigModel();

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigProvider#releaseFacesConfigModel()
	 */
	public abstract void releaseFacesConfigModel();

}
