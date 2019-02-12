/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
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


package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.facesconfig.ui.dialog.ListChoiceDialog;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * The cell editor used to locate an existing pageflow page node in current
 * pageflow.
 * 
 * @author hmeng
 * 
 */
public class ExistingPagePathDialogCellEditor extends EditableDialogCellEditor {
	Pageflow pageflow;

	/**
	 * 
	 */
	public ExistingPagePathDialogCellEditor() {
		super();
	}

	/**
	 * @param parent
	 * @param style
	 */
	public ExistingPagePathDialogCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param parent
	 * @param pageflow
	 */
	public ExistingPagePathDialogCellEditor(Composite parent, Pageflow pageflow) {
		super(parent);
		this.pageflow = pageflow;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#openDialogBox(Control)
	 */
	protected Object openDialogBox(Control cellEditorWindow) {
		String jsfSelection = ""; //$NON-NLS-1$
		if (getDefaultText() != null && getDefaultText().getText().length() > 0) {
			jsfSelection = getDefaultText().getText();
		}
		ListChoiceDialog dialog = new ListChoiceDialog(cellEditorWindow
				.getShell(), getPagePaths(),
				PageflowMessages.ExistingPagePathDialogCellEdito_LabelText);

		if (dialog.open() == Window.OK) {
			jsfSelection = dialog.getResult();
		}
		return jsfSelection;
	}

	/**
	 * Collect page paths in current pageflow.
	 * 
	 * @return
	 */
	private String[] getPagePaths() {
		List result = new ArrayList();
		List list = pageflow.getNodes();
		for (int i = 0; i < list.size(); i++) {
			String path = ((PageflowPage) list.get(i)).getPath();
			if (path != null && path.trim().length() > 0) {
				result.add(path);
			}
		}
		String[] r = (String[]) result.toArray(new String[result.size()]);
		Arrays.sort(r);
		return r;
	}

}
