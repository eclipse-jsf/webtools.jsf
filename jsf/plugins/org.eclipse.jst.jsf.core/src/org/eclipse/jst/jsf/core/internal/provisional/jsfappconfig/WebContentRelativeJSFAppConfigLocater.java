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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

/**
 * WebContentRelativeJSFAppConfigLocater is an abstract base class that attempts
 * to locate specified application configuration files relative to a web
 * content folder. Clients must override the getFilenames() method to return an
 * array of filenames that this locateProviders() method will attempt to
 * locate.
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class WebContentRelativeJSFAppConfigLocater implements IJSFAppConfigLocater {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.IJSFAppConfigLocater#locateProviders(org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.JSFAppConfigManager)
	 */
	public void locateProviders(JSFAppConfigManager manager) {
		IProject project = manager.getProject();
		IVirtualFolder webContentFolder = JSFAppConfigUtils.getWebContentFolder(project);
		if (webContentFolder != null) {
			String[] filenames = getFilenames();
			if (filenames != null && filenames.length > 0) {
				for (int i = 0; i < filenames.length; i++) {
					IVirtualResource appConfigResource = webContentFolder.findMember(filenames[i]);
					if (appConfigResource != null && appConfigResource.getType() == IVirtualResource.FILE) {
						IFile file = (IFile)appConfigResource.getUnderlyingResource();
						if (file != null && file.exists()) {
							ArtifactEditJSFAppConfigProvider provider = new ArtifactEditJSFAppConfigProvider(file);
							manager.addJSFAppConfigProvider(provider);
						}
					}
				}
			}
		}
	}

	/**
	 * Gets an array of Strings representing the filenames (relative to the web
	 * content folder) that locateProviders() will attempt to locate.
	 * 
	 * @return an array of Strings representing the filenames (relative to the
	 * web content folder) that locateProviders() will attempt to locate.
	 */
	protected abstract String[] getFilenames();

}
