/*******************************************************************************
 * Copyright (c) 2006, 2011 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.utils;

import java.io.File;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJarEntryResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Collection of helper methods to manage and convert links Originally part of
 * the LinksManager (com.ibm.iwt.parser.util)
 */
public final class PathUtil {
	private static final String FORWARD_SLASH = "/"; //$NON-NLS-1$

	private static final String RELATIVE_PATH_SIGNAL = IFileFolderConstants.DOT
			+ IFileFolderConstants.DOT + IFileFolderConstants.PATH_SEPARATOR;

	/**
	 * adjust relative path isside the absolute path
	 * @param path 
	 * @return the adjusted path
	 */
	public static String adjustPath(String path) {
		int i = 0;
		while ((i = path.indexOf(RELATIVE_PATH_SIGNAL)) > 0) {
			// split the string into two
			String part1 = path.substring(0, i - 1);
			String part2 = path
					.substring(i + RELATIVE_PATH_SIGNAL.length() - 1);
			// strip one path seg from part1
			int j = part1.lastIndexOf(FORWARD_SLASH);
			if (j == -1) {
				return "";//$NON-NLS-1$
			}
			part1 = part1.substring(0, j);
			path = part1 + part2;
		}
		return path;
	}

	/**
	 * Append trailing url slash if needed
	 * @param input 
	 * @return the string
	 */
	public static String appendTrailingURLSlash(String input) {
		// check to see already a slash
		if (!input.endsWith(FORWARD_SLASH)) {
			input += FORWARD_SLASH;
		}
		return input;
	}

	/**
	 * Convert to relative url based on base
	 * @param input 
	 * @param base 
	 * @return the string
	 */
	public static String convertToRelativePath(String input, String base) {
		// tokenize the strings
		StringTokenizer inputTokenizer = new StringTokenizer(input,
				FORWARD_SLASH);
		StringTokenizer baseTokenizer = new StringTokenizer(base, FORWARD_SLASH);
		String token1 = "", token2 = "";//$NON-NLS-2$//$NON-NLS-1$
		//
		// Go through until equls
		while (true) {
			if (!inputTokenizer.hasMoreTokens()
					|| !baseTokenizer.hasMoreTokens()) {
				break;
			}
			token1 = baseTokenizer.nextToken();
			token2 = inputTokenizer.nextToken();
			if (!token1.equals(token2)) {
				break;
			}
		}
		// now generate the backs
		String output = "";//$NON-NLS-1$
		while (baseTokenizer.hasMoreTokens()) {
			baseTokenizer.nextToken();
			output += RELATIVE_PATH_SIGNAL;
		}
		output += token2;
		// generate the rest
		while (inputTokenizer.hasMoreTokens()) {
			output = output + FORWARD_SLASH + inputTokenizer.nextToken();
		}
		return output;
	}

	/**
	 * @param projectName
	 * @param path
	 * @return the path in the project converted to a path relative to the
	 * web folder
	 */
	public static String convertToWebPath(String projectName, String path) {
		String name = ""; //$NON-NLS-1$
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectName);
		String webrootName = WebrootUtil.getWebContentContainer(project).getName();

		if (path.indexOf(webrootName) != -1) {
			name = projectName + IFileFolderConstants.PATH_SEPARATOR
					+ webrootName;
		} else {
			name = projectName;
		}
		int index = path.indexOf(projectName);

		return path.substring(index + name.length());
	}

	/**
	 * convert path relative to current active file to absolute path in
	 * filesystem
	 * 
	 * @param uri
	 *            the relative path
	 * @param curFile 
	 * @return absolute path in file system
	 */
	public static String convertToAbsolutePath(String uri, IFile curFile) {
		if (uri == null || uri.trim().equals("")) { //$NON-NLS-1$
			return uri;
		}
		String webroot = ""; //$NON-NLS-1$
		IFile jsp = curFile;
		try {
			if (jsp == null) {
				jsp = ((IFileEditorInput) getActivePage()
						.getActiveEditor().getEditorInput()).getFile();
			}
			if (jsp != null) {
				String webrootName = WebrootUtil.getWebContentContainer(
						jsp.getProject()).getName();
				webroot = jsp.getProject().getFolder(webrootName).getLocation()
						.toString();
			}
		} catch (NullPointerException e) {
			return uri;
		}
		if (uri.startsWith(IFileFolderConstants.PATH_SEPARATOR))
		{
			return webroot + uri;
		}
		if (jsp != null) {
			IContainer con = jsp.getParent();
			if (con != null) {
				IPath path = con.getLocation();
				if (path != null) {
					String aPath = path.toString() + File.separator + uri;
					aPath = aPath.replace('/', File.separatorChar);
					aPath = aPath.replace('\\', File.separatorChar);
					if (aPath.endsWith(File.separator)) {
						aPath += IFileFolderConstants.PATH_SEPARATOR;
					}
					File file = new File(aPath);
					if (file.exists() && file.isFile()) {
						return file.getAbsolutePath();
					}
                    return uri;
				}
			}
		}
		return uri;
	}

	/**
	 * Returns the active workbench window.
	 * 
	 * @return the active workbench window. this can be null but I've never seen
	 *         it.
	 */
	private static IWorkbenchWindow getActiveWorkbenchWindow() {
		if (PlatformUI.getWorkbench() == null) {
			return null;
		}
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	}

	/**
	 * Returns the active workbench page. Note that the active page may not be
	 * the one that the user perceives as active in some situations so this
	 * method of obtaining the activate page should only be used if no other
	 * method is available.
	 * 
	 * @return the active workbench page
	 */
	private static IWorkbenchPage getActivePage() {
		IWorkbenchWindow window = getActiveWorkbenchWindow();
		if (window == null) {
			return null;
		}
		return window.getActivePage();
	}
	/**
	 * @param javaProject
	 * @param parent
	 * @return the IPath for a a classpath object (?)
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
							int n = entries[i].getPath().matchingFirstSegments(
									elementPath);
							if (n > machings) {
								// Get package name
								machings = n;
								classPath = elementPath.removeFirstSegments(
										machings).removeLastSegments(1);
							}
						}

						// Not on the classpath?
						if (classPath == null) {
							return null;
						} else if (classPath.segmentCount() > 0) {
							IJavaElement element = javaProject
									.findElement(classPath);
							if (element != null) {
								IPath path = element.getPath();
								if (path != null) {
									IPath path1 = path
											.removeFirstSegments(machings);

									String fileName = ((IFile) parent)
											.getName();
									if (fileName != null) {
										result = path1.append(fileName);
									}
								}
							}

						} else {
							result = ((IFile) parent).getFullPath()
									.removeFirstSegments(machings);
						}
					} catch (Exception e) {
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
	
	private PathUtil()
	{
		// utility class, no instantiation
	}
}
