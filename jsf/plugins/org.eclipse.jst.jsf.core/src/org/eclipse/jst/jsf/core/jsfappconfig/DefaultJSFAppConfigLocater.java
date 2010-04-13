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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery.DefaultVirtualComponentQuery;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

/**
 * DefaultJSFAppConfigLocater attempts to locate the default application
 * configuration file, located at "/WEB-INF/faces-config.xml".
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public class DefaultJSFAppConfigLocater extends WebContentRelativeJSFAppConfigLocater 
	implements IResourceChangeListener {

	/**
	 * Web content folder-relative name of the default application
	 * configuration resource file.
	 */
	public static final String DEF_APPCONFIGRESOURCE_FILENAME = "WEB-INF/faces-config.xml"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.AbstractJSFAppConfigLocater#startLocating()
	 */
	public void startLocating() {
		locateProviders();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.addResourceChangeListener(this, IResourceChangeEvent.POST_BUILD);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.AbstractJSFAppConfigLocater#stopLocating()
	 */
	public void stopLocating() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.removeResourceChangeListener(this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.WebContentRelativeJSFAppConfigLocater#getFilenames()
	 */
	protected List getFilenames() {
		List filenames = new ArrayList();
		filenames.add(DEF_APPCONFIGRESOURCE_FILENAME);
		return filenames;
	}

	/**
	 * Responds to resource change events.
	 * 
	 * @param event IResourceChangeEvent instance.
	 */
	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		try {
			delta.accept(new ResourceDeltaVisitor());
		} catch (CoreException cex) {
			JSFCorePlugin.log(
					IStatus.WARNING,
					cex.getLocalizedMessage(),
					cex);
		}
	}

	/**
	 * ResourceDeltaVisitor is used to visit an IResourceDelta instance to
	 * discover if the default application configuration resource file has
	 * been added or removed.
	 * 
	 * @author Ian Trimble - Oracle
	 */
	class ResourceDeltaVisitor implements IResourceDeltaVisitor {

		/**
		 * Cached IPath instance for default configuration resource file.
		 */
		protected IPath defConfigPath = null;

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) {
			boolean visitChildren = false;
			initDefConfigPath();
			if (defConfigPath != null) {
				IPath deltaPath = delta.getFullPath();
				if (deltaPath != null) {
					if (defConfigPath.equals(deltaPath)) {
						locateProviders();
					} else if (deltaPath.isPrefixOf(defConfigPath)) {
						visitChildren = true;
					}
				}
			} else {
				locateProviders();
			}
			return visitChildren;
		}

		/**
		 * Determines IPath instance for default configuration resource file
		 * and caches in class member.
		 */
		protected void initDefConfigPath() {
			if (defConfigPath == null) {
				IProject project = getJSFAppConfigManager().getProject();
				if (project != null) {
					IVirtualFolder webContentFolder = new DefaultVirtualComponentQuery().getWebContentFolder(project);
					if (webContentFolder != null) {
						IVirtualResource defConfigFile = webContentFolder.findMember(DEF_APPCONFIGRESOURCE_FILENAME);
						if (defConfigFile != null) {
							defConfigPath = defConfigFile.getWorkspaceRelativePath();
						}
					}
				}
			}
		}

	}

}
