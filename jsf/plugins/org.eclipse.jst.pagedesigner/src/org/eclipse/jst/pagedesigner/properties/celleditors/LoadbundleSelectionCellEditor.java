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
package org.eclipse.jst.pagedesigner.properties.celleditors;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;
import org.eclipse.jst.jsf.common.ui.internal.dialogs.ResourceOnClasspathDialog;
import org.eclipse.jst.pagedesigner.properties.DesignerPropertyTool;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author mengbo
 */
public class LoadbundleSelectionCellEditor extends EditableDialogCellEditor {
	private static final String[] PROPERTIES_FILES_SUFFIXS = new String[] { IFileFolderConstants.EXT_PROPERTIES };

	private String _value;

	private IProject _project;

	/**
	 * @param parent
	 * @param project 
	 */
	public LoadbundleSelectionCellEditor(Composite parent, IProject project) {
		super(parent);
		_project = project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
	 */
	protected Object openDialogBox(Control cellEditorWindow) {
		ResourceOnClasspathDialog dialog = new ResourceOnClasspathDialog(
				cellEditorWindow.getShell(), DesignerPropertyTool
						.getJavaProject(_project));
		dialog.setTitle(ResourceBoundle.getString("FileCellEditor.Title")); //$NON-NLS-1$
		dialog.setSuffixs(PROPERTIES_FILES_SUFFIXS);
		dialog.open();
		if (dialog.getResult() != null) {
			_value = (String) dialog.getResult()[0];
		}
		return _value;
	}

}
