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
package org.eclipse.jst.pagedesigner.properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StatusUtil;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public abstract class BaseCustomSection extends AbstractPropertySection {

	/**
	 * Status that all is OK
	 */
	protected static final Status OKSTATUS = new Status(IStatus.OK, PDPlugin
			.getPluginId(), 0, "", null); //$NON-NLS-1$

	private WPETabbedPropertySheetPage _propertySheetPage;

	private boolean _visible = false;

	/**
	 * the tag IDOMElement
	 */
	protected IDOMElement _element;

	/**
	 * the INodeAdapter used to receive notifications of model updates
	 */
	protected INodeAdapter _adapter = new INodeAdapter() {
		public boolean isAdapterForType(Object type) {
			return false;
		}

		public void notifyChanged(INodeNotifier notifier, int eventType,
				Object changedFeature, Object oldValue, Object newValue, int pos) {
			BaseCustomSection.this.notifyChanged(notifier, eventType,
					changedFeature, oldValue, newValue, pos);
		}
	};

	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		IDOMElement newEle = (IDOMElement) DesignerPropertyTool.getElement(
				part, selection);
		if (_element != newEle) {
			if (_element != null) {
				_element.removeAdapter(_adapter);
			}
			_element = newEle;
			if (_element != null) {
				_element.addAdapter(_adapter);
			}
		}
	}

	/**
	 * Method adapter will call when element has changed
	 * 
	 * @param notifier
	 * @param eventType
	 * @param changedFeature
	 * @param oldValue
	 * @param newValue
	 * @param pos
	 */
	protected abstract void notifyChanged(INodeNotifier notifier,
			int eventType, Object changedFeature, Object oldValue,
			Object newValue, int pos);

	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		_propertySheetPage = (WPETabbedPropertySheetPage) aTabbedPropertySheetPage;
	}

	/**
	 * for certain action performed in the section, may result big change in the
	 * model. e.g: change tag name will result in editpart recreate. In this
	 * case, we need do a total refresh.
	 * 
	 */
	protected void refreshPropertySheetPage() {
		if (_propertySheetPage != null) {
			IWorkbenchPart part = getPart();
			if (part != null) {
				ISelection sel = part.getSite().getSelectionProvider()
						.getSelection();
				_propertySheetPage.selectionChanged(part, sel);
			} else {
				// XXX: will else happen?
				System.out.println("AbstractCustomSection --> What to do?"); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Change selection
	 * @param node
	 */
	protected void gotoNode(Node node) {
		_propertySheetPage.internalChangeSelection(node, node);
	}

	/**
	 * @return IStatusLineManager to use for the property sheet page
	 */
	protected IStatusLineManager getStatusLineManager() {
		if (_propertySheetPage != null) {
			IActionBars bar = _propertySheetPage.getSite().getActionBars();
			if (bar != null) {
				return bar.getStatusLineManager();
			}
		}
		return null;
	}

	/**
	 * @param status to display on status line
	 */
	protected void applyStatus(IStatus[] status) {
		if (!_visible) {
			return;
		}

		IStatusLineManager statusLine = getStatusLineManager();
		if (statusLine == null) {
			return;
		}

		IStatus s;
		if (status == null || status.length == 0) {
			s = null;
		} else {
			s = StatusUtil.getMostSevere(status);
		}

		if (s == null || s.getSeverity() != IStatus.ERROR) {
			statusLine.setErrorMessage(null);
		} else {
			statusLine.setErrorMessage(s.getMessage());
		}
	}

	/**
	 * @param message
	 */
	protected void setErrorMessage(String message) {
		IStatusLineManager s = getStatusLineManager();
		if (s != null) {
			s.setErrorMessage(message);
		}
	}

	/**
	 * @param message
	 * @return Status
	 */
	protected Status createErrorStatus(String message) {
		return new Status(IStatus.ERROR, PDPlugin.getPluginId(), 0, message,
				null);
	}

	public void aboutToBeHidden() {
		applyStatus(null);
		_visible = false;
//		IDOMElement newEle = (IDOMElement) DesignerPropertyTool.getElement(
//				part, selection);
//		if (_element != newEle) {
			if (_element != null) {
				_element.removeAdapter(_adapter);
			}
//			_element = newEle;
//			if (_element != null) {
//				_element.addAdapter(_adapter);
//			}
//		}
		super.aboutToBeHidden();
	}

	public void aboutToBeShown() {
		super.aboutToBeShown();
//		IDOMElement newEle = (IDOMElement) DesignerPropertyTool.getElement(
//				part, selection);
//		if (_element != newEle) {
//			if (_element != null) {
//				_element.removeAdapter(_adapter);
//			}
//			_element = newEle;
			if (_element != null) {
				_element.addAdapter(_adapter);
			}
//		}
		_visible = true;
	}

	/**
	 * @return IProject for the WPE editing instance
	 */
	protected IProject getProject() {
		if (_propertySheetPage != null) {
			IEditorInput input = _propertySheetPage.getEditor()
					.getEditorInput();
			if (input instanceof IFileEditorInput) {
				return ((IFileEditorInput) input).getFile().getProject();
			}
		}
		return null;
	}

	public void dispose() {
		super.dispose();
		if (_element != null) {
			_element.removeAdapter(_adapter);
		}
	}

	/**
	 * @return IFile for WPE editing instance
	 */
	protected IFile getFile() {
		if (_propertySheetPage != null) {
			IEditorInput input = _propertySheetPage.getEditor()
					.getEditorInput();
			if (input instanceof IFileEditorInput) {
				return ((IFileEditorInput) input).getFile();
			}
		}
		return null;
	}
}
