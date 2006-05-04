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

import java.util.List;

/**
 * ContextParamSpecifiedJSFAppConfigLocater attempts to locate application
 * configuration files specified by the JSF CONFIG_FILES context parameter.
 * 
 * @author Ian Trimble - Oracle
 */
public class ContextParamSpecifiedJSFAppConfigLocater extends WebContentRelativeJSFAppConfigLocater {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.AbstractJSFAppConfigLocater#startLocating()
	 */
	public void startLocating() {
		locateProviders();
		//TODO: add adapter
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.AbstractJSFAppConfigLocater#stopLocating()
	 */
	public void stopLocating() {
		//TODO: remove adapter
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.WebContentRelativeJSFAppConfigLocater#getFilenames()
	 */
	protected List getFilenames() {
		return JSFAppConfigUtils.getConfigFilesFromContextParam(manager.getProject());
	}

}
