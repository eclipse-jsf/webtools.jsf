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

import org.eclipse.core.resources.IProject;

/**
 * ContextParamSpecifiedJSFAppConfigLocater attempts to locate application
 * configuration files specified by the JSF CONFIG_FILES context parameter.
 * 
 * @author Ian Trimble - Oracle
 */
public class ContextParamSpecifiedJSFAppConfigLocater extends WebContentRelativeJSFAppConfigLocater {

	/**
	 * IProject instance obtained from the JSFAppConfigManager instance.
	 */
	protected IProject project = null;

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.IJSFAppConfigLocater#locateProviders(org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.JSFAppConfigManager)
	 */
	public void locateProviders(JSFAppConfigManager manager) {
		project = manager.getProject();
		super.locateProviders(manager);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.WebContentRelativeJSFAppConfigLocater#getFilenames()
	 */
	protected String[] getFilenames() {
		return JSFAppConfigUtils.getConfigFilesFromContextParam(project);
	}

}
