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

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * RuntimeClasspathJSFAppConfigLocater attempts to locate application
 * configuration files in JAR files on the runtime classpath. The runtime
 * classpath includes the server runtime classpath and the JAR files that will
 * be deployed to the web application's .../WEB-INF/lib folder.
 * 
 * @author Ian Trimble - Oracle
 */
public class RuntimeClasspathJSFAppConfigLocater implements IJSFAppConfigLocater {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.IJSFAppConfigLocater#locateProviders(org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.JSFAppConfigManager)
	 */
	public void locateProviders(JSFAppConfigManager manager) {
		IProject project = manager.getProject();
		try {
			String[] JARs = JSFAppConfigUtils.getConfigFileJARsFromClasspath(project);
			if (JARs != null && JARs.length > 0) {
				for (int i = 0; i < JARs.length; i++) {
					JARFileJSFAppConfigProvider provider = new JARFileJSFAppConfigProvider(JARs[i]);
					manager.addJSFAppConfigProvider(provider);
				}
			}
		} catch(CoreException ce) {
			//log error
			JSFCorePlugin.log(IStatus.ERROR, ce.getLocalizedMessage(), ce);
		} catch(IOException ioe) {
			//log error
			JSFCorePlugin.log(IStatus.ERROR, ioe.getLocalizedMessage(), ioe);
		}
	}

}
