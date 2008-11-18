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
package org.eclipse.jst.pagedesigner.ui.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jst.jsf.common.ui.internal.guiutils.SWTUtils;
import org.eclipse.jst.pagedesigner.editors.pagedesigner.PageDesignerResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.part.DrillDownComposite;

/**
 * This is a base dialog that uses TreeViewer to show selections, subclass needs
 * to provide IContentProvider, ILabelProvider and ViewerFilter for the
 * TreeViewer. Subclass needs to implement isValidSelection(), which valids the
 * selection, and findInputElement() which provides the root element of the
 * tree. Besides, subclass might need to implement getResult() to return a
 * customized result.
 * 
 * @author mengbo
 */
abstract class TreeViewerSelectionDialog extends SelectionDialog {
	// = "Select a file"
	private static final String DEFAULT_TITLE = PageDesignerResources
			.getInstance().getString("TreeViewerSelectionDialog.Title"); //$NON-NLS-1$

	/** Used to tag the image type */
	static final int STYLE_NONE = 0;

	static final int STYLE_INFORMATION = 1;

	static final int STYLE_ERROR = 2;

	static final int STYLE_WARNING = 3;

	/** Sizi of the TreeViewer composite */
	private static final int SIZING_SELECTION_PANE_HEIGHT = 300;

	private static final int SIZING_SELECTION_PANE_WIDTH = 320;

	private String _title = DEFAULT_TITLE;

	// the seleciton on the treeviewer.
	private static Object _selection;

	// providers
	private ITreeContentProvider _contentProvider;

	private ILabelProvider _labelProvider;

	private ViewerFilter _filter;

	/** The validation image */
	private Label _statusImage;

	/** The validation message */
	private Label _statusLabel;

	private String _statusMessage;

	// private IJavaProject _project;
	/** The selection tree */
	private TreeViewer _treeViewer;

	/**
	 * @param parentShell
	 * @param statusMessage 
	 */
	public TreeViewerSelectionDialog(Shell parentShell, String statusMessage) {
		super(parentShell);
		_statusMessage = statusMessage;
		// ? need SWT.RESIZE
		setShellStyle(getShellStyle());
	}

	/**
	 * Returns a new drill down viewer for this dialog.
	 * @param parent 
	 * 
	 */
	protected void createTreeViewer(Composite parent) {
		// Create drill down
		DrillDownComposite drillDown = new DrillDownComposite(parent,
				SWT.BORDER);
		GridData spec = new GridData(GridData.VERTICAL_ALIGN_FILL
				| GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL);
		spec.widthHint = SIZING_SELECTION_PANE_WIDTH;
		spec.heightHint = SIZING_SELECTION_PANE_HEIGHT;
		drillDown.setLayoutData(spec);
		_treeViewer = new TreeViewer(drillDown, SWT.NONE);
		drillDown.setChildTree(_treeViewer);
	}

	private void setTreeViewerProviders() {
		_treeViewer.setContentProvider(_contentProvider);
		_treeViewer.setLabelProvider(_labelProvider);
		_treeViewer.setSorter(new ViewerSorter());
		_treeViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						_selection = getSelectedElement((IStructuredSelection) event
								.getSelection());
						updateStatus();
					}
				});
		_treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					Object item = ((IStructuredSelection) selection)
							.getFirstElement();
					if (_treeViewer.getExpandedState(item)) {
						_treeViewer.collapseToLevel(item, 1);
					} else {
						_treeViewer.expandToLevel(item, 1);
					}
				}
			}
		});
		_treeViewer.setInput(findInputElement());

		if (_filter != null) {
			// Assert.isLegal(_contentProvider instanceof
			// StandardJavaElementContentProvider);
			_treeViewer.addFilter(_filter);
		}

	}

	/**
	 * Creates the contents of the composite.
	 * @param parent 
	 */
	public void createTreeViewerComposite(Composite parent) {
		Composite treeViewerComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		treeViewerComposite.setLayout(layout);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		treeViewerComposite.setLayoutData(gridData);
		Label label = new Label(treeViewerComposite, SWT.WRAP);
		label.setText(_title);
		label.setFont(treeViewerComposite.getFont());
		createTreeViewer(treeViewerComposite);
		Dialog.applyDialogFont(treeViewerComposite);
	}

	/**
	 * Sets the selected existing container.
	 * 
	 * @param selection -
	 *            the current selected container.
	 */
	public void setSelectedElement(Object selection) {
		// Expand to and select the specified container
		if (_selection != null) {
			_treeViewer.expandToLevel(_selection, 1);
		}
		List itemsToExpand = new ArrayList();
		Object parent = _contentProvider.getParent(selection);
		if (parent == null) {
			return;
		}
		while (parent != null) {
			itemsToExpand.add(0, parent);
			parent = _contentProvider.getParent(parent);
		}
		_treeViewer.setExpandedElements(itemsToExpand.toArray());
		_treeViewer.setSelection(new StructuredSelection(selection), true);
	}

	/*
	 * (non-Javadoc) Method declared on Dialog.
	 */
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		area.setLayout(gridLayout);

		// Container treeviewer composite
		createTreeViewerComposite(area);

		_statusImage = SWTUtils.createLabelImage(area,
				getMessageImage(STYLE_ERROR), 1, null);
		_statusLabel = SWTUtils.createLabel(area, "", 1); //$NON-NLS-1$
		// Link to model
		setTreeViewerProviders();

		return dialogArea;
	}

	private Object getSelectedElement(IStructuredSelection selection) {
		return selection.getFirstElement();
	}

	/**
	 * @param provider
	 *            The _contentProvider to set.
	 */
	public void setContentProvider(ITreeContentProvider provider) {
		_contentProvider = provider;
	}

	/**
	 * @param provider
	 *            The _labelProvider to set.
	 */
	public void setLabelProvider(ILabelProvider provider) {
		_labelProvider = provider;
	}

	/**
	 * @param filter
	 *            The _filter to set.
	 */
	public void setFilter(ViewerFilter filter) {
		this._filter = filter;
	}


	/**
	 * Update the status message
	 */
	private void updateStatus() {
		if (isValidSelection(_selection)) {
			_statusImage.setImage(getMessageImage(STYLE_NONE));
			_statusLabel.setText(""); //$NON-NLS-1$
			getOkButton().setEnabled(true);
		} else {
			_statusImage.setImage(getMessageImage(STYLE_ERROR));
			_statusLabel.setText(_statusMessage);
			getOkButton().setEnabled(false);
		}
	}

	/**
	 * Get the different message according the message type.
	 * 
	 * @param imageType 
	 * 
	 * @return Image - the message image
	 */
	protected Image getMessageImage(int imageType) {
		switch (imageType) {
		case STYLE_ERROR:
			return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
		case STYLE_WARNING:
			return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
		case STYLE_INFORMATION:
			return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO);
		default:
			return null;
		}
	}

	/**
	 * The <code>ContainerSelectionDialog</code> implementation of this
	 * <code>Dialog</code> method builds a list of the selected resource
	 * containers for later retrieval by the client and closes this dialog.
	 */
	protected void okPressed() {
		List chosenContainerPathList = new ArrayList();
		if (_selection != null) {
			chosenContainerPathList.add(_selection);
		}
		setResult(chosenContainerPathList);
		super.okPressed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Control control = super.createContents(parent);
		if (_selection != null) {
			this.setSelectedElement(_selection);
		}
		return control;
	}

	/**
	 * @param selection
	 * @return true if the selection is valid
	 */
	protected abstract boolean isValidSelection(Object selection);

	/**
	 * @return the input element
	 */
	protected abstract Object findInputElement();
}
