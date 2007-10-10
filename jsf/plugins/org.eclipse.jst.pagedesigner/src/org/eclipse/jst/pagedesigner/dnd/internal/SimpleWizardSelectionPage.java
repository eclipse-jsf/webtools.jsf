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
package org.eclipse.jst.pagedesigner.dnd.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.jface.wizard.WizardSelectionPage;
import org.eclipse.jst.pagedesigner.dnd.FeedBackInfo;
import org.eclipse.jst.pagedesigner.dnd.ILocalDropHandler;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.w3c.dom.Node;

/**
 * UI wizard selection page. To let user select which ILocalDropHandler to use
 * to handle the drop.
 * 
 * @author mengbo
 */
public class SimpleWizardSelectionPage extends WizardSelectionPage implements
		ISelectionChangedListener {
	private Object _localData;

	private IHTMLGraphicalViewer _viewer;

	private boolean _updateWidget;

	private Node _widget;

	private IDOMPosition _position;

	/**
	 * for those handler that support wizard, then map to WizardNode, otherwise
	 * still map to the handler
	 */
	private Map _objToWizardNodeOrHandler = new HashMap();

	private Map _feedbackToHandlers;

	private Object _currentHandler = null; // IWizardNode or a

	// ILocalDropHandler that don't
	// useWizard.

	/**
	 * @param viewer 
	 * @param localData 
	 * @param handlers 
	 */
	public SimpleWizardSelectionPage(IHTMLGraphicalViewer viewer,
			Object localData, Map handlers) {
		super(Messages.getString("SimpleWizardSelectionPage.PageName")); //$NON-NLS-1$
		this.setTitle(Messages.getString("SimpleWizardSelectionPage.Title")); //$NON-NLS-1$
		this.setDescription(Messages
				.getString("SimpleWizardSelectionPage.Description")); //$NON-NLS-1$
		this
				.setMessage(Messages
						.getString("SimpleWizardSelectionPage.Message")); //$NON-NLS-1$

		this._viewer = viewer;
		this._localData = localData;
		this._feedbackToHandlers = handlers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.verticalSpacing = 10;
		container.setLayout(layout);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(container, SWT.NONE);
		label
				.setText(Messages
						.getString("SimpleWizardSelectionPage.Operation")); //$NON-NLS-1$
		GridData gd = new GridData();
		label.setLayoutData(gd);

		final ListViewer listViewer = new ListViewer(container);
		listViewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));

		listViewer.setContentProvider(new IStructuredContentProvider() {
			public Object[] getElements(Object inputElement) {
				return ((List) inputElement).toArray();
			}

			public void dispose() {
                // nothing to dispose
			}

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
                // no input changing
			}
		});
		listViewer.setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				return SimpleWizardSelectionPage.this.getText(element);
			}
		});
		listViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				selectionChanged(new SelectionChangedEvent(listViewer,
						listViewer.getSelection()));
				advanceToNextPage();
			}
		});
		listViewer.setSorter(new ViewerSorter());
		listViewer.setInput(getElements());
		listViewer.addSelectionChangedListener(this);
		Dialog.applyDialogFont(container);
		setControl(container);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection sel = event.getSelection();
		if (sel instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) sel).getFirstElement();
			if (obj != null) {
				_currentHandler = this._objToWizardNodeOrHandler.get(obj);
				if (_currentHandler == null) {
					_currentHandler = getWizardNodeOrHandler(obj);
					this._objToWizardNodeOrHandler.put(obj, _currentHandler);
				}

				if (_currentHandler instanceof IWizardNode) {
					this.setSelectedNode((IWizardNode) _currentHandler);
				} else {
					this.getWizard().getContainer().updateButtons();
				}
			}
		}
	}

	/**
	 * @param obj
	 * @return
	 */
	private Object getWizardNodeOrHandler(Object obj) {
		final ILocalDropHandler dropHandler = (ILocalDropHandler) _feedbackToHandlers
				.get(obj);
		if (dropHandler.useWizard(_localData, _viewer)) {
			return new IWizardNode() {
				IWizard _wizard = null;

				public void dispose() {
                    // nothing to dispose
				}

				public Point getExtent() {
					return null;
				}

				public IWizard getWizard() {
					if (_wizard == null) {
						if (_updateWidget) {
							_wizard = dropHandler.getWizard(_localData,
									_widget, _viewer);
						} else {
							_wizard = dropHandler.getWizard(_localData,
									_position, _viewer);
						}
					}
					return _wizard;
				}

				public boolean isContentCreated() {
					return _wizard != null;
				}
			};
		}
        return dropHandler;
	}

	/**
	 * could be have next page or just directly perform the action.
	 * 
	 */
	public void advanceToNextPage() {

		getContainer().showPage(getNextPage());
	}

	/**
	 * @param element
	 * @return the text
	 */
	protected String getText(Object element) {
		return ((FeedBackInfo) element).getDescription();
	}

	/**
	 * @return the elements
	 */
	protected List getElements() {
		return new ArrayList(this._feedbackToHandlers.keySet());
	}

	/**
	 * @param widget
	 */
	public void setWidget(Node widget) {
		_updateWidget = true;
		_widget = widget;
	}

	/**
	 * @param position
	 */
	public void setPosition(IDOMPosition position) {
		_updateWidget = false;
		_position = position;
	}

	/**
	 * @return the current handler
	 */
	public Object getCurrentHandler() {
		return _currentHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardSelectionPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage() {
		return getCurrentHandler() instanceof IWizardNode
				&& super.canFlipToNextPage();
	}
}
