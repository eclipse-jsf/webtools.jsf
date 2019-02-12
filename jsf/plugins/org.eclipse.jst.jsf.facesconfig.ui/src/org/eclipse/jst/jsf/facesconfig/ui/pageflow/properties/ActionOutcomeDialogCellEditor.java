/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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

import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * 
 * Dialog Cell Editor for Action's outcome browser.
 * 
 * @author Xiao-guang Zhang
 */
public class ActionOutcomeDialogCellEditor extends EditableDialogCellEditor {
	/** the source PFLink element */
	PageflowElement element;

	/**
	 * @param element
	 */
	public ActionOutcomeDialogCellEditor(PageflowElement element) {
		super();
		this.element = element;
	}

	/**
	 * @param parent
	 * @param element
	 */
	public ActionOutcomeDialogCellEditor(Composite parent,
			PageflowElement element) {
		super(parent);
		this.element = element;
	}


	/**
	 * @param parent
	 * @param style
	 * @param element
	 */
	public ActionOutcomeDialogCellEditor(Composite parent, int style,
			PageflowElement element) {
		super(parent, style);
		this.element = element;
	}

	/**
	 * get the previous JSP page, it can be null if the previous node is not
	 * PFPage, but PFAction.
	 * 
	 * @param element_
	 * @return
	 */
	private String getPreviousJSPPath(PageflowElement element_) {
		String jspPath = null;
		if (element_ instanceof PageflowLink) {
			PageflowNode source = ((PageflowLink) element_).getSource();

			if (source instanceof PageflowPage) {
				jspPath = ((PageflowPage) source).getPath();
			}
		}
		return jspPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
	 */
	protected Object openDialogBox(Control cellEditorWindow) {
		String outcome = getDefaultText().getText();

		String jspPathName = WebrootUtil.getProjectPath(element,
				getPreviousJSPPath(element));

		ActionOutcomeSelectionDialog selectionDialog = new ActionOutcomeSelectionDialog(
				getControl().getShell(), outcome, jspPathName);

		if (selectionDialog.open() == Window.OK) {
			outcome = selectionDialog.getSelectedAction();
		}

		return outcome;
	}
}
