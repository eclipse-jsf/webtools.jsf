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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;

/**
 * @author mengbo
 */
public class StructuredModelUtil {

	/**
	 * this method is copied from ModelManagerImpl of wtp. Because it is
	 * internal.
	 * 
	 * @param model
	 * @return null if can't get file.
	 */
	// TODO: replace (or supplement) this is a "model info" association to the
	// IFile that created the model
	public static IFile getFileFor(IStructuredModel model) {
		if (model == null)
			return null;
		String path = model.getBaseLocation();
		if (path == null || path.length() == 0) {
			Object id = model.getId();
			if (id == null)
				return null;
			path = id.toString();
		}
		// TODO needs rework for linked resources
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		try {
			IFile file = root.getFile(new Path(path));
			// IFile file = root.getFileForLocation(new Path(path));
			return file;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 
	 * @param model
	 * @return null if can't find project for the model
	 */
	public static IProject getProjectFor(IStructuredModel model) {
		IProject project = null;

		IFile file = getFileFor(model);
		if (file != null) {
			project = file.getProject();
		}
		return project;
	}
}
