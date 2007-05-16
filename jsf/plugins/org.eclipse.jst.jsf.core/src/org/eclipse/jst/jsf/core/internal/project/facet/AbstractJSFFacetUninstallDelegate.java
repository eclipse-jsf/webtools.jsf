/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.project.facet;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryConfigurationHelper;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * Abstract JSF Facet Uninstall Delegate for WTP faceted projects.
 *	
 *	<li> concrete class should remove JSF servlet, servlet-mappings and context-params
 *	<li> leaves JSF configuration files on disk
 *  <li> removes JSF classpath containers
 *  
 * @author Gerry Kessler - Oracle
 * @since M1
 */
public abstract class AbstractJSFFacetUninstallDelegate implements IDelegate {

	public void execute(IProject project, IProjectFacetVersion fv,
			Object config, IProgressMonitor monitor) throws CoreException {
		{

			if (monitor != null) {
				monitor.beginTask("", 1); //$NON-NLS-1$
			}

			try {

				// Remove JSF Libraries
				removeJSFLibaries(project, monitor);

				// remove servlet stuff from web.xml
				uninstallJSFReferencesFromWebApp(project, monitor);

				if (monitor != null) {
					monitor.worked(1);
				}
			} finally {
				if (monitor != null) {
					monitor.done();
				}
			}
		}
	}

	/**
	 * Removes JSF Lib CP Containers from project
	 * @param project
	 * @param monitor
	 */
	protected void removeJSFLibaries(IProject project, IProgressMonitor monitor) {
		 final IJavaProject jproj = JavaCore.create(project);
		 List keptEntries = new ArrayList();
		 try {
			IClasspathEntry[] entries = jproj.getRawClasspath();
			  keptEntries = new ArrayList();
			 for (int i=0;i<entries.length;i++){
				 IClasspathEntry entry = entries[i];
				 if (entry.getEntryKind() == IClasspathEntry.CPE_CONTAINER && 
						 ! entry.getPath().segment(0)
						 	.equals(JSFLibraryConfigurationHelper.JSF_LIBRARY_CP_CONTAINER_ID))
					 keptEntries.add(entry);
			 }
		} catch (JavaModelException e) {
			JSFCorePlugin.log(e, "Cannot get classpath entries to remove JSF Libraries for: "+project.getName()); //$NON-NLS-1$
		}
		 
		 if (keptEntries.size() > 0){
			 try {
				jproj.setRawClasspath((IClasspathEntry[])keptEntries.toArray(new IClasspathEntry[0]), monitor);
			} catch (JavaModelException e) {
				JSFCorePlugin.log(e, "Exception occured while removing JSF Libraries during JSF Facet uninstall"); //$NON-NLS-1$
			}
		 }
	
		
	}

	/**
	 * Uninstall JSF references: servlet, 
	 * @param project
	 * @param monitor
	 */
	protected abstract void uninstallJSFReferencesFromWebApp(IProject project,
			IProgressMonitor monitor);


}
