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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/**
 * ArtifactEditJSFAppConfigProvider uses FacesConfigArtifactEdit to provide
 * the root element of an application configuration model.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public class ArtifactEditJSFAppConfigProvider extends AbstractJSFAppConfigProvider {

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
	 * Cached {@link FacesConfigType} instance.
	 */
	protected FacesConfigType facesConfig = null;

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
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigProvider#getFacesConfigModel()
	 */
	public FacesConfigType getFacesConfigModel() {
		if (facesConfig == null) {
			if (appConfigFile != null) {
				IProject project = appConfigFile.getProject();
				IPath appConfigFilePath = JSFAppConfigUtils.getWebContentFolderRelativePath(appConfigFile);
				if (appConfigFilePath != null) {
					facesConfigArtifactEdit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(project, appConfigFilePath.toString());
					if (facesConfigArtifactEdit != null) {
						facesConfig = facesConfigArtifactEdit.getFacesConfig();
						if (facesConfig != null) {
							jsfAppConfigLocater.getJSFAppConfigManager().addFacesConfigChangeAdapter(facesConfig);
						}
					}
				}
			}
		}
		return facesConfig;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigProvider#releaseFacesConfigModel()
	 */
	public void releaseFacesConfigModel() {
	    if (jsfAppConfigLocater != null)
	    {
	        jsfAppConfigLocater.getJSFAppConfigManager().removeFacesConfigChangeAdapter(facesConfig);
	    }
		if (facesConfigArtifactEdit != null) {
			facesConfigArtifactEdit.dispose();
			facesConfigArtifactEdit = null;
		}
		facesConfig = null;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object otherObject) {
		boolean equals = false;
		if (otherObject instanceof ArtifactEditJSFAppConfigProvider) {
			IFile otherAppConfigFile = ((ArtifactEditJSFAppConfigProvider)otherObject).appConfigFile;
			if (appConfigFile != null) {
				equals = appConfigFile.equals(otherAppConfigFile);
			} else {
				equals = otherAppConfigFile == null;
			}
		}
		return equals;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return appConfigFile != null ? appConfigFile.hashCode() : 0;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("ArtifactEditJSFAppConfigProvider["); //$NON-NLS-1$
		if (appConfigFile != null) {
			sb.append(appConfigFile.toString());
		} else {
			sb.append("null"); //$NON-NLS-1$
		}
		sb.append("]"); //$NON-NLS-1$
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() {
		releaseFacesConfigModel();
	}

}
