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
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;
import org.eclipse.jst.jsf.common.ui.internal.dialogs.ResourceOnClasspathDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author mengbo
 */
public class ClasspathResourceButtonDialogField extends StringButtonDialogField {
	private IProject _project;

	private static final String[] PROPERTIES_FILES_SUFFIXS = new String[] { IFileFolderConstants.EXT_PROPERTIES }; //$NON-NLS-1$

	public ClasspathResourceButtonDialogField(IProject project) {
		this(null, project);
		setStringButtonAdapter(new IStringButtonAdapter() {
			public void changeControlPressed(DialogField field) {
				browseButtonPressed();
			}
		});
	}

	public ClasspathResourceButtonDialogField(IStringButtonAdapter adapter,
			IProject project) {
		super(adapter);
		this._project = project;
	}

	private void browseButtonPressed() {
		Shell shell = getShell();
		ResourceOnClasspathDialog dialog = new ResourceOnClasspathDialog(shell,
				getJavaProject());
		dialog.setTitle(JSFUICommonPlugin
				.getResourceString("DialogField.ResourceButton.SelectFile"));//$NON-NLS-1$
		dialog.setSuffixs(PROPERTIES_FILES_SUFFIXS);
		if (dialog.open() == Window.OK) {
			String oldValue = getText();

			String newValue = (String) dialog.getResult()[0];
			if (oldValue != newValue) {
				setText(newValue);
			}
		}
	}

	private IJavaProject getJavaProject() {
		try {
			if (_project != null && _project.hasNature(JavaCore.NATURE_ID)) {
				return JavaCore.create(_project);
			}
            return null;
		} catch (CoreException e) {
			return null;
		}
	}

	/**
	 * @return Returns the project.
	 */
	public IProject getProject() {
		return _project;
	}

	/**
	 * @param project
	 *            The project to set.
	 */
	public void setProject(IProject project) {
		this._project = project;
	}
}
