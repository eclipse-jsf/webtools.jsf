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
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * RuntimeClasspathJSFAppConfigLocater attempts to locate application
 * configuration files in JAR files on the runtime classpath. The runtime
 * classpath includes the server runtime classpath and the JAR files that will
 * be deployed to the web application's /WEB-INF/lib folder.
 * 
 * @author Ian Trimble - Oracle
 */
public class RuntimeClasspathJSFAppConfigLocater extends AbstractJSFAppConfigLocater {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.AbstractJSFAppConfigLocater#startLocating()
	 */
	public void startLocating() {
		locateProviders();
		//TODO: add listener
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.AbstractJSFAppConfigLocater#stopLocating()
	 */
	public void stopLocating() {
		//TODO: remove listener
	}

	/**
	 * Locates application configuration resources specified in JAR files on
	 * the runtime classpath, and updates the set of
	 * {@link IJSFAppConfigProvider} instances accordingly.
	 */
	public void locateProviders() {
		try {
			List JARs = JSFAppConfigUtils.getConfigFileJARsFromClasspath(manager.getProject());
			Iterator itJARs = JARs.iterator();
			Set newConfigProviders = new LinkedHashSet();
			while (itJARs.hasNext()) {
				String JARFilename = (String)itJARs.next();
				JARFileJSFAppConfigProvider configProvider = new JARFileJSFAppConfigProvider(JARFilename);
				newConfigProviders.add(configProvider);
			}
			updateConfigProviders(newConfigProviders);
		} catch(CoreException ce) {
			//log error
			JSFCorePlugin.log(IStatus.ERROR, ce.getLocalizedMessage(), ce);
		} catch(IOException ioe) {
			//log error
			JSFCorePlugin.log(IStatus.ERROR, ioe.getLocalizedMessage(), ioe);
		}
	}

}
