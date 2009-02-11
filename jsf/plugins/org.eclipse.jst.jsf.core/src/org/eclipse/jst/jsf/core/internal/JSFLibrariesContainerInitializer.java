/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryConfigurationHelper;

/**
 * Initialize JSF Libraries as classpath containers
 */
@SuppressWarnings("deprecation")
public class JSFLibrariesContainerInitializer extends
		ClasspathContainerInitializer {

	private static final String MISSING_LIBRARY = Messages.JSFLibrariesContainerInitializer_missing_library;

	/**
	 * Constructor
	 */
	public JSFLibrariesContainerInitializer() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#initialize(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public void initialize(IPath containerPath, IJavaProject project) throws CoreException {
		if (isJSFLibraryContainer(containerPath)) {
			String libId= containerPath.lastSegment();
						
			JSFLibrary ref= JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().getJSFLibraryByID(libId);
			if (ref != null) {
				JSFLibraryClasspathContainer container= new JSFLibraryClasspathContainer(ref);
				JavaCore.setClasspathContainer(containerPath, new IJavaProject[] { project }, 	new IClasspathContainer[] { container }, null);
			}
		}
	}
	
	private boolean isJSFLibraryContainer(IPath path) {
		return path != null && path.segmentCount() == 2 && JSFLibraryConfigurationHelper.JSF_LIBRARY_CP_CONTAINER_ID.equals(path.segment(0));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#canUpdateClasspathContainer(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public boolean canUpdateClasspathContainer(IPath containerPath, IJavaProject project) {
		return isJSFLibraryContainer(containerPath); 
	}

	/**
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getDescription(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public String getDescription(IPath containerPath, IJavaProject project) {
		if (isJSFLibraryContainer(containerPath)) {
			String id = containerPath.lastSegment();
			JSFLibrary libref = JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().getJSFLibraryByID(id);
			String displayText = id;

			if (libref == null){
				displayText = displayText + " " + MISSING_LIBRARY; //$NON-NLS-1$
			}
			
			return displayText;
		}
		return super.getDescription(containerPath, project);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getComparisonID(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public Object getComparisonID(IPath containerPath, IJavaProject project) {
		return containerPath;
	}

}
