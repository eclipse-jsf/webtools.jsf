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

import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dnd.ILocalDropHandler;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class DropSelectionWizard extends Wizard {
	private final String INTIAL_DEFAULT_PAGE_IMAGE = "newsuade_wiz.gif"; //$NON-NLS-1$

	private Object _localData;

	private IHTMLGraphicalViewer _viewer;

	private Map _feedbackToHandlers;

	private boolean _updateWidget;

	private Node _widget;

	private IDOMPosition _position;

	private SimpleWizardSelectionPage _firstPage;

	private DropSelectionWizard(IHTMLGraphicalViewer viewer, Object localData,
			Map handlers) {
		this.setWindowTitle(Messages.getString("DropSelectionWizard.Title")); //$NON-NLS-1$
		ImageDescriptor desc = PDPlugin.getDefault().getImageDescriptor(
				INTIAL_DEFAULT_PAGE_IMAGE);
		setDefaultPageImageDescriptor(desc);
		this._viewer = viewer;
		this._localData = localData;
		this._feedbackToHandlers = handlers;

		_firstPage = new SimpleWizardSelectionPage(_viewer, _localData,
				_feedbackToHandlers);
	}

	/**
	 * @param viewer 
	 * @param localData 
	 * @param handlers 
	 * @param widget 
	 */
	public DropSelectionWizard(IHTMLGraphicalViewer viewer, Object localData,
			Map handlers, Node widget) {
		this(viewer, localData, handlers);
		_updateWidget = true;
		_widget = widget;

		_firstPage.setWidget(widget);
	}

	/**
	 * @param viewer
	 * @param localData
	 * @param handlers
	 * @param position
	 */
	public DropSelectionWizard(IHTMLGraphicalViewer viewer, Object localData,
			Map handlers, IDOMPosition position) {
		this(viewer, localData, handlers);
		_updateWidget = false;
		_position = position;

		_firstPage.setPosition(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		addPage(_firstPage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#needsPreviousAndNextButtons()
	 */
	public boolean needsPreviousAndNextButtons() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	public boolean canFinish() {
		if (getContainer().getCurrentPage() == _firstPage) {
			Object obj = _firstPage.getCurrentHandler();
			if (obj instanceof ILocalDropHandler)
            {
				return true;
            }
            return false;
		}
        return super.canFinish();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		if (getContainer().getCurrentPage() == _firstPage) {
			Object obj = _firstPage.getCurrentHandler();
			if (obj instanceof ILocalDropHandler) {
				ILocalDropHandler handler = (ILocalDropHandler) obj;
				if (_updateWidget) {
					handler.doUpdateWidget(_localData, _widget, _viewer);
				} else {
					handler.doInsertElements(_localData, _position, _viewer);
				}
				return true;
			}
		}
		return true;
	}
}
