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
package org.eclipse.jst.jsf.facesconfig.ui.dialog;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jst.jsf.facesconfig.common.guiutils.SWTUtils;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Bryan Yang
 * 
 */
public class CommonListDialog extends Dialog implements
		ISelectionChangedListener, ISelectionProvider {
	private static final int MIN_DIALOG_WIDTH = 300;

	private Text inputText;

	private StructuredViewer structuredViewer;

	private String value;

	private IFacesConfigPage page;

	private Object input;

	private String label;
	
	private String caption;

	/** The mini width for the text control */
	private static final int TEXT_MINI_WIDTH = 100;

	protected CommonListDialog(Shell parentShell, IFacesConfigPage page,
			Object input, String caption, String label) {
		super(parentShell);
		this.page = page;
		this.input = input;
		this.label = label;
		this.caption = caption;
		parentShell.setText(caption);
	}
	
	/*
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(caption);
	}


	/*
	 * @see org.eclipse.jface.window.Window#getInitialSize()
	 */
	protected Point getInitialSize() {
		Point shellSize = super.getInitialSize();
		return new Point(Math.max(
				convertHorizontalDLUsToPixels(MIN_DIALOG_WIDTH), shellSize.x),
				shellSize.y);
	}

	/*
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite composite = SWTUtils.createComposite(parent, 1);

		SWTUtils.createLabel(composite, label, 1); //$NON-NLS-1$

		inputText = SWTUtils.createTextBox(composite, 1);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = TEXT_MINI_WIDTH;
		inputText.setLayoutData(gd);

		structuredViewer = createViewer(composite);
		structuredViewer.addSelectionChangedListener(this);

		return composite;
	}

	/**
	 * Create the structured viewer, set up content & label provider for it.
	 * Defaultly create a tableviewer.
	 * 
	 * @param parent
	 * @param toolkit
	 * @return
	 */
	protected StructuredViewer createViewer(Composite parent) {

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 200;
		gd.widthHint = 150;
		parent.setLayoutData(gd);
		GridLayout layout = new GridLayout();
		parent.setLayout(layout);

		TableViewer tableViewer = new TableViewer(parent, SWT.SINGLE
				| SWT.H_SCROLL | SWT.V_SCROLL);
		gd = new GridData(GridData.FILL_BOTH);
		tableViewer.getControl().setLayoutData(gd);

		tableViewer.setContentProvider(new AdapterFactoryContentProvider(
				getAdapterFactory()));
		tableViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getAdapterFactory()));
		configViewer(tableViewer);
		tableViewer.setInput(input);

		return tableViewer;
	}

	/**
	 * set the structuredViewer's input
	 * 
	 * @param input
	 */
	private void setViewerInput(Object input) {
		structuredViewer.setInput(input);
		this.input = input;
	}

	/**
	 * get the input object of this section.
	 */
	public Object getInput() {
		return input;
	}

	/**
	 * set input object for this section.
	 */
	public void setInput(Object newInput) {
		input = newInput;
		setViewerInput(input);
	}

	/**
	 * Config the viewer, such as set a filter and so on. Sub classes should
	 * override this method to add filter.
	 * 
	 * @param structuredViewer
	 */
	protected void configViewer(StructuredViewer structuredViewer_) {
        // do nothing; sub-classes should override
	}

     //TODO: why bother with this interface?
	public void selectionChanged(SelectionChangedEvent event) {
		// selectionChanged not handled

	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
        // do nothing; not handling setSelection
	}

	public ISelection getSelection() {
		return structuredViewer.getSelection();
	}

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		// do nothing; not handling setSelection
	}

	public void setSelection(ISelection selection) {
        // do nothing; not handling change in selection
	}

	/**
	 * the convenient method to get the AdapterFactory instance of the editor;
	 * 
	 * @return the AdapterFactory instance.
	 */
	public AdapterFactory getAdapterFactory() {
		return (AdapterFactory) page.getEditor().getAdapter(
				AdapterFactory.class);
	}

	/**
	 * the convenient method to get the EditingDomain instance of the editor;
	 * 
	 * @return the EditingDomain instance.
	 */
	public EditingDomain getEditingDomain() {
		return (EditingDomain) page.getEditor().getAdapter(EditingDomain.class);
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
		refresh();
	}
	
	private void refresh()
	{
	   inputText.setText(value)	;
	}

}
