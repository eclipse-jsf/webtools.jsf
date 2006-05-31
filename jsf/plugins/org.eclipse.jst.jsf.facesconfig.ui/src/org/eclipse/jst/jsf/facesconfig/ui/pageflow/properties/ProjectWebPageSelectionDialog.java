/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jsf.facesconfig.common.dialogs.CommonResourceDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author hmeng
 */

public class ProjectWebPageSelectionDialog extends CommonResourceDialog {

	public ProjectWebPageSelectionDialog(Shell parentShell, IProject project,
			int style) {
		super(parentShell, project, style);
		init();
	}

	public ProjectWebPageSelectionDialog(Shell parentShell, IProject project) {
		super(parentShell, project);
		init();
	}

	public ProjectWebPageSelectionDialog(Shell parentShell, IFolder folder,
			int style) {
		super(parentShell, folder, style);
		init();
	}

	private void init() {
		setResourceDescription("Web page");
		setSuffixs(new String[] { "jsp", "jspx", "jsf", "html", "htm" });
	}

	public String getResultFilePath() {
		String result = "";
		IFile selectedFile = (IFile) getResult()[0];
		IPath path = selectedFile.getProjectRelativePath();
		path = path.removeFirstSegments(1);
		result = "/" + path.toString();
		return result;
	}
}
