/**
 * Confidential Property of Sybase, Inc.
 * (c) Copyright Sybase, Inc. 2004-2006.
 * All rights reserved.
 * 
 */

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Dialog Cell Editor for Page path browser.
 * 
 * @author Xiao-guang Zhang
 */
public class PagePathDialogCellEditor extends EditableDialogCellEditor {
	PageflowPage element;

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#DialogCellEditor()
	 */
	public PagePathDialogCellEditor() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#DialogCellEditor(Composite parent)
	 */
	public PagePathDialogCellEditor(Composite parent, PageflowPage element) {
		super(parent);
		this.element = element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#DialogCellEditor(Composite parent, int style)
	 */
	public PagePathDialogCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DialogCellEditor#openDialogBox(Control)
	 */
	protected Object openDialogBox(Control cellEditorWindow) {
		// FIXME: remove the get the project path and the webroot path later.
		String jsfSelection = "";
		if (getDefaultText() != null && getDefaultText().getText().length() > 0) {
			jsfSelection = getDefaultText().getText();
			// jsfSelection =
			// PageflowModelManager.getProjectPath(getDefaultLabel().getText());
		}
		return jsfSelection;
	}

}
