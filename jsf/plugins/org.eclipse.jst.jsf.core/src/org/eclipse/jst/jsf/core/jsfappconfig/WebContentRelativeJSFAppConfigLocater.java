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

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery.DefaultVirtualComponentQuery;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

/**
 * WebContentRelativeJSFAppConfigLocater is an abstract base class that
 * attempts to locate specified application configuration files relative to a
 * web content folder. Subclasses must override the getFilenames() method to
 * return a list of filenames that the locateProviders() method will attempt to
 * locate, and call locateProviders().
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class WebContentRelativeJSFAppConfigLocater extends AbstractJSFAppConfigLocater {

	/**
	 * Locates application configuration resources specified by the filenames
	 * (relative to the web content folder) returned by getFilenames(), and
	 * updates the set of {@link IJSFAppConfigProvider} instances accordingly.
	 */
	public void locateProviders() {
		IProject project = getJSFAppConfigManager().getProject();
		IVirtualFolder webContentFolder = new DefaultVirtualComponentQuery().getWebContentFolder(project);
		if (webContentFolder != null) {
			List filenames = getFilenames();
			Iterator itFilenames = filenames.iterator();
			Set newConfigProviders = new LinkedHashSet();
			while (itFilenames.hasNext()) {
				String filename = (String)itFilenames.next();
				IVirtualResource appConfigResource = webContentFolder.findMember(filename);
				if (appConfigResource != null && appConfigResource.getType() == IVirtualResource.FILE) {
					IFile file = (IFile)appConfigResource.getUnderlyingResource();
					if (file != null && file.exists()) {
						ArtifactEditJSFAppConfigProvider configProvider = new ArtifactEditJSFAppConfigProvider(file);
						newConfigProviders.add(configProvider);
					}
				}
			}
			updateConfigProviders(newConfigProviders);
		}
	}

	/**
	 * Gets a list of Strings representing the filenames (relative to the web
	 * content folder) that locateProviders() will attempt to locate.
	 * 
	 * @return A list of Strings representing the filenames (relative to the
	 * web content folder) that locateProviders() will attempt to locate.
	 */
	protected abstract List getFilenames();

}
