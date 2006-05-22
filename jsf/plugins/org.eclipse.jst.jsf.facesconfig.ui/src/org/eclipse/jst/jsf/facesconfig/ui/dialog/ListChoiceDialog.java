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
package org.eclipse.jst.jsf.facesconfig.ui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jst.jsf.facesconfig.common.guiutils.SWTUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Zhi-peng Zhang
 * @version
 */
public class ListChoiceDialog extends Dialog {
	/** The mini width for the text control */
	private static final int TEXT_MINI_WIDTH = 350;

	/** The mini width for the table viewer control */
	private static final int TABLEVIEWER_MINI_WIDTH = 200;

	private Text inputText;

	private TableViewer tableViewer;

	private String[] items;

	private String result;
	
	private String labelString;

	public ListChoiceDialog(Shell parentShell, String[] items, String labelString) {
		super(parentShell);
		this.items = items;
		if(this.items == null)
		{
			this.items = new String[0];
		}
		this.labelString = labelString;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite composite = SWTUtils.createComposite(parent, 1);

		SWTUtils.createLabel(composite, labelString, 1);

		inputText = SWTUtils.createTextBox(composite, 1);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = TEXT_MINI_WIDTH;
		inputText.setLayoutData(gd);

		inputText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				result = inputText.getText();
			}
		});

		createTableViewer(composite);
		return composite;
	}

	/**
	 * @param composite
	 */
	private void createTableViewer(Composite composite) {
		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = TABLEVIEWER_MINI_WIDTH;
		tableViewer.getControl().setLayoutData(gd);
		tableViewer.add(items);
		tableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) event
								.getSelection();
						if (selection != null) {
							String text = (String) selection.getFirstElement();
							if (text != null && text.length() > 0) {
								result = text;
								inputText.setText(text);
							}
						}
					}
				});
	}
	
	public String getResult()
	{
		return result;
	}
}
