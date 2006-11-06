/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#DialogCellEditor()
	 */
	public ActionOutcomeDialogCellEditor(PageflowElement element) {
		super();
		this.element = element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#DialogCellEditor(Composite parent)
	 */
	public ActionOutcomeDialogCellEditor(Composite parent,
			PageflowElement element) {
		super(parent);
		this.element = element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#DialogCellEditor(Composite parent, int style)
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
