/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.utils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJarEntryResource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;

/**
 * @author mengbo
 */
public class JavaUtil {
	/**
	 * 
	 * @param javaProject
	 * @param parent
	 * @return the path in javaProject or new Path("") if not found on a class path
	 * @author mengbo
	 */
	public static IPath getPathOnClasspath(IJavaProject javaProject,
			Object parent) {
		IPath result = null;
		if (javaProject == null || parent == null) {
			return new Path(""); //$NON-NLS-1$
		}
		IClasspathEntry[] entries = javaProject.readRawClasspath();
		IPath classPath = null;
		if (parent instanceof IResource) {
			if (((javaProject != null) && !javaProject
					.isOnClasspath((IResource) parent))) {
				return new Path(""); //$NON-NLS-1$
			}
			if (parent instanceof IFile) {
				IPath elementPath = ((IFile) parent).getFullPath();
				if (((IFile) parent).getFileExtension().equalsIgnoreCase(
						IFileFolderConstants.EXT_PROPERTIES)) {
					int machings = 0;
					try {
						for (int i = 0; i < entries.length; i++) {
							// Determine whether on this classentry's path
							machings = entries[i].getPath()
									.matchingFirstSegments(elementPath);
							if (machings > 0) {
								// Get package name
								classPath = elementPath.removeFirstSegments(
										machings).removeLastSegments(1);
								break;
							}
						}
						// Not on the classpath?
						if (classPath == null) {
							return null;
						} else if (classPath.segmentCount() > 0)
							result = javaProject.findElement(classPath)
									.getPath().removeFirstSegments(machings)
									.append(((IFile) parent).getName());
						else
							result = ((IFile) parent).getFullPath()
									.removeFirstSegments(machings);
					} catch (Exception e) {
						// Error.DesignerPropertyTool.NatureQuerying = Error in
						// project java nature querying
						PDPlugin.getLogger(JavaUtil.class).error(
								"Error.DesignerPropertyTool.NatureQuerying", e); //$NON-NLS-1$
						return null;
					}
				}
			}
		} else if (parent instanceof IJarEntryResource) {
			IPath elementPath = ((IJarEntryResource) parent).getFullPath();
			if (elementPath.getFileExtension().equalsIgnoreCase(
					IFileFolderConstants.EXT_PROPERTIES)) {
				result = elementPath;
			}
		}
		if (result != null) {
			return result;
		}
		return new Path(""); //$NON-NLS-1$
	}

}
