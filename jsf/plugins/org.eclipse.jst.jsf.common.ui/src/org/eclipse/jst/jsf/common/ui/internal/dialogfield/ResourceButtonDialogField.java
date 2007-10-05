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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.dialogs.CommonResourceDialog;
import org.eclipse.jst.jsf.common.ui.internal.utils.PathUtil;
import org.eclipse.jst.jsf.common.ui.internal.utils.WebrootUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

/**
 * This class provides a common resource choice DialogFiled. You must set a
 * no-null IProject instance to enable it working. You should give it a suffix
 * list if you want to choose files ended with specific file extensions. The
 * selection result will be a relative path based on the _referredFile's value
 * if it is set, or else based on the _project path.
 * 
 * The usage: Shell shell = new Shell(); IProject project = getProject();
 * ResourceButtonDialogField localeField = new
 * ResourceButtonDialogField(project);
 * localeField.setResourceDescription("image"); localeField.setSuffixs(new
 * String[]{"bmp","jpg","gif"});
 * localeField.setReferredFile(StructuredModelUtil.getFileFor(_element.getModel()));
 * 
 * @author mengbo
 */
public class ResourceButtonDialogField extends StringButtonDialogField {
	private IProject _project;

	private String[] _suffixs;

	private String _resourceDescription;

	private IFile _referredFile;

	private boolean _isWebPath = false;

	private IFolder _folder;

	private String _separator = "";

	/**
	 * @param project
	 */
	public ResourceButtonDialogField(IProject project) {
		this(null, project);
		setStringButtonAdapter(new IStringButtonAdapter() {
			public void changeControlPressed(DialogField field) {
				String oldValue = getText();
				String newValue = browseButtonPressed();
				if (newValue != null && !newValue.equals(oldValue)) {
					setText(newValue);
				}
			}
		});
	}

	/**
	 * @param adapter
	 * @param project
	 */
	public ResourceButtonDialogField(IStringButtonAdapter adapter,
			IProject project) {
		super(adapter);
		this._project = project;
	}

	/**
	 * @return the string
	 */
	protected String browseButtonPressed() {
		Shell shell = getShell();
		CommonResourceDialog dialog = null;
		int style = "".equals(_separator) ? SWT.NONE : SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL;
		if (_folder != null) {
			dialog = new CommonResourceDialog(shell, _folder.getProject(), style);
		} else {
			dialog = new CommonResourceDialog(shell, _project, style);
		}
		dialog.setTitle(JSFUICommonPlugin
				.getResourceString("DialogField.ResourceButton.SelectFile"));//$NON-NLS-1$
		dialog.setSuffixs(_suffixs);
		dialog.setResourceDescription(_resourceDescription);

		if (dialog.open() == Window.OK) {
			Object[] result = dialog.getResult();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
				String newValue = null;
				IPath path = ((IFile) result[i]).getLocation();

				IPath referredPath = null;
				if (_referredFile != null) {
					referredPath = _referredFile.getLocation();
				} else {
					referredPath = _project.getLocation();
				}
				if (this._isWebPath) {
					IFile selectedFile = ((IFile) result[i]);
					newValue = WebrootUtil.getWebPath(selectedFile
							.getFullPath());
				} else {
					newValue = PathUtil.convertToRelativePath(path.toString(),
							referredPath.toString());
				}
				buffer.append(newValue);
				buffer.append(_separator);
			}
			if (buffer.length() > 0) {
				return buffer.substring(0, buffer.length()
						- _separator.length());
			}

		}
		return null;
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

	/**
	 * @return Returns the relative folder.
	 */
	public IFolder getFolder() {
		return _folder;
	}

	/**
	 * @param folder
	 *            The relative folder to set.
	 */
	public void setFolder(IFolder folder) {
		this._folder = folder;
	}

	/**
	 * @return Returns the referredFile.
	 */
	public IFile getReferredFile() {
		return _referredFile;
	}

	/**
	 * @param referredFile
	 *            The referredFile to set.
	 */
	public void setReferredFile(IFile referredFile) {
		this._referredFile = referredFile;
	}

	/**
	 * @return Returns the resourceDescription.
	 */
	public String getResourceDescription() {
		return _resourceDescription;
	}

	/**
	 * @param resourceDescription
	 *            The resourceDescription to set.
	 */
	public void setResourceDescription(String resourceDescription) {
		this._resourceDescription = resourceDescription;
	}

	/**
	 * @return Returns the suffixs.
	 */
	public String[] getSuffixs() {
		return _suffixs;
	}

	/**
	 * @param suffixs
	 *            The suffixs to set.
	 */
	public void setSuffixs(String[] suffixs) {
		this._suffixs = suffixs;
	}

	/**
	 * set some special path to web path instead of relative path
	 * 
	 * @param isWebPath
	 */
	public void setWebPath(boolean isWebPath) {
		this._isWebPath = isWebPath;
	}

	/**
	 * @return the separator string
	 */
	public String getSeparator() {
		return _separator;
	}

	/**
	 * @param separator
	 */
	public void setSeparator(String separator) {
		this._separator = separator;
	}
}
