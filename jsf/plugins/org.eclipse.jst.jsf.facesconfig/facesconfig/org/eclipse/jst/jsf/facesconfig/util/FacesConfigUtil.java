/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;

/**
 * Utility class for faces-config model management
 */
public class FacesConfigUtil {

	/** Must match the id specified in plugin.xml. */
	public static final String FACES_CONFIG_CONTENT_TYPE_ID = "org.eclipse.jst.jsf.facesconfig.facesConfigFile";

	/**
	 * Returns true iff the specified file seems to be a Faces configuration
	 * file. Always returns false if the file is in a non-Faces project.
	 * 
	 * @param file
	 *            the IFile to check
	 * @return true iff the specified file seems to be a Faces configuration
	 *         file.
	 */
	public static boolean isFacesConfigFile(IFile file) {
		return isFacesConfigFile(file, true);
	}

	/**
	 * Returns true iff the specified file seems to be a Faces configuration
	 * file. If ignoreNonFacesProject is true, always returns false if the file
	 * is in a non-Faces project.
	 * 
	 * @param file
	 *            the IFile to check
	 * @param ignoreNonFacesProject
	 * @return true iff the specified file seems to be a Faces configuration
	 *         file.
	 */
	public static boolean isFacesConfigFile(IFile file, boolean ignoreNonFacesProject) {
		
		// Bail if it's not a .xml file.
		if (!"xml".equalsIgnoreCase(file.getFileExtension()))
			return false;

		// Bail if we can't determine the project. This shouldn't be possible
		// here, but...
		IProject project = file.getProject();
		if (project == null)
			return false;

		// If requested, bail if the project isn't a Faces project.
		// TODO: XN - temporarily disabled the following two lines to get rid 
		// of the dependency on jsf.util
		//if (ignoreNonFacesProject && !JsfProjectUtil.isJsfProject(project))
			//return false;

		try {
			IContentDescription contentDescription = file.getContentDescription();
			if (contentDescription != null) {
				IContentType contentType = contentDescription.getContentType();
				if (contentType != null)
					return FACES_CONFIG_CONTENT_TYPE_ID.equals(contentType.getId());
			}
		} catch (CoreException ignored) {
			// Empty block intended.
		}

		return false;
	}

}
