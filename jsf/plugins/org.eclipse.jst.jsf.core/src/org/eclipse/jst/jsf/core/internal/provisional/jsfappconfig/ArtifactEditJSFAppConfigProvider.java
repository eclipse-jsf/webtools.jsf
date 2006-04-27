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
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/**
 * ArtifactEditJSFAppConfigProvider uses FacesConfigArtifactEdit to provide
 * the root element of an application configuration model.
 * 
 * @author Ian Trimble - Oracle
 */
public class ArtifactEditJSFAppConfigProvider implements IJSFAppConfigProvider {

	/**
	 * IFile instance that represents an application configuration resource
	 * file.
	 */
	protected IFile appConfigFile = null;

	/**
	 * FacesConfigArtifactEdit instance used to get the application
	 * configuration model.
	 */
	protected FacesConfigArtifactEdit facesConfigArtifactEdit = null;

	/**
	 * Creates an instance, storing the passed IFile instance for subsequent
	 * processing.
	 * 
	 * @param appConfigFile IFile instance that represents an application
	 * configuration resource file
	 */
	public ArtifactEditJSFAppConfigProvider(IFile appConfigFile) {
		this.appConfigFile = appConfigFile;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.IJSFAppConfigProvider#getFacesConfigModel(boolean)
	 */
	public FacesConfigType getFacesConfigModel(boolean forWrite) throws InvalidWriteAccessModeException {
		FacesConfigType facesConfig = null;
		if (appConfigFile != null) {
			IProject project = appConfigFile.getProject();
			IPath appConfigFilePath = JSFAppConfigUtils.getWebContentFolderRelativePath(appConfigFile);
			if (appConfigFilePath != null) {
				if (forWrite) {
					if (!allowsWrite()) {
						throw new InvalidWriteAccessModeException(Messages.JSFAppConfigManager_InvalidWriteAccess);
					}
					facesConfigArtifactEdit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(project, appConfigFilePath.toString());
				} else {
					facesConfigArtifactEdit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(project, appConfigFilePath.toString());
				}
				if (facesConfigArtifactEdit != null) {
					facesConfig = facesConfigArtifactEdit.getFacesConfig();
				}
			}
		}
		return facesConfig;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.IJSFAppConfigProvider#releaseFacesConfigModel()
	 */
	public void releaseFacesConfigModel() {
		if (facesConfigArtifactEdit != null) {
			facesConfigArtifactEdit.dispose();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.IJSFAppConfigProvider#allowsWrite()
	 */
	public boolean allowsWrite() {
		return true;
	}

}
