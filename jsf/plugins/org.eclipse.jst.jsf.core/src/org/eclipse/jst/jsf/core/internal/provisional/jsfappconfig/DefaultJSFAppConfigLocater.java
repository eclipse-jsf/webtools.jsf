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
 * DefaultJSFAppConfigLocater attempts to locate the default application
 * configuration file, located at ".../WEB-INF/faces-config.xml".
 * 
 * @author Ian Trimble - Oracle
 */
public class DefaultJSFAppConfigLocater extends WebContentRelativeJSFAppConfigLocater {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.IJSFAppConfigLocater#locateProviders(org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.JSFAppConfigManager)
	 */
	public void locateProviders(JSFAppConfigManager manager) {
		super.locateProviders(manager);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.WebContentRelativeJSFAppConfigLocater#getFilenames()
	 */
	protected String[] getFilenames() {
		return new String[] {"WEB-INF/faces-config.xml"}; //$NON-NLS-1$
	}

}
