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
package org.eclipse.jst.pagedesigner.commands;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.EditValidateUtil;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.jst.pagedesigner.editors.SimpleGraphicalEditor;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.utils.SelectionHelper;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.HTMLGraphicalViewer;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.wst.html.core.internal.format.HTMLFormatProcessorImpl;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This class is intended to be the base class for all designer GEF commands.
 * Basically, it will wrap the real command with common actions like handle
 * undo/redo, etc.
 * 
 * @author mengbo
 */
public abstract class DesignerCommand extends Command {
	private final IDOMModel _model;

	/**
	 * the graphical viewer
	 */
	protected IHTMLGraphicalViewer _viewer;

	private static final Logger _log = PDPlugin
			.getLogger(DesignerCommand.class);

	/**
	 * @param label
	 * @param viewer
	 */
	public DesignerCommand(String label, IHTMLGraphicalViewer viewer) {
		super(label);
		this._viewer = viewer;
		this._model = viewer.getModel();
	}

	/**
	 * @param label
	 * @param node
	 *            the node must be a node in the IHTMLGraphicalViewer.
	 */
	public DesignerCommand(String label, IDOMNode node) {
	    this(label, (IDOMDocument) node.getOwnerDocument());
	}

	/**
	 * @param label
	 * @param document
	 */
	public DesignerCommand(String label, IDOMDocument document)
	{
	    super(label);
        this._model = document.getModel();
        EditPart part = (EditPart) document.getAdapterFor(EditPart.class);
        if (part != null) {
            this._viewer = (IHTMLGraphicalViewer) part.getViewer();
        }
	}
	/**
	 * @return the GEF viewer this command is targeted for
	 * 
	 */
	public IHTMLGraphicalViewer getViewer() {
		return _viewer;
	}

	/**
	 * @return the DOM model that this command will effect
	 */
	public IDOMModel getModel() {
		return _model;
	}

	/**
	 * @return the dom document for the model this command will effect
	 */
	public IDOMDocument getDocument() {
		return getModel().getDocument();
	}

	/**
	 * executes the Command. This method should not be called if the Command is
	 * not executable.
	 */
	public final void execute() {
		boolean ok = prePreExecute();
		if (ok) {
			try {
				preExecute();
				doExecute();
				postExecute();
			} catch (Exception ex) {
				handleException(ex);
			} finally {
				postPostExecute();
			}
		}
	}

	/**
	 * child class can override.
	 * 
	 * @param ex
	 */
	protected void handleException(Exception ex) {
		ex.printStackTrace();
	}

	/**
	 * prePreExecute and postPostExecute is a pair. prePreExecute() SHOULD NOT
	 * throw any exception, if it throw any exception, it should catch itself
	 * and return false to indicate not continue.
	 * @return true if execution should continue, false if should not 
	 */
	protected boolean prePreExecute() {
		int position = -1;
		int length = -1;
		ISelection selection = getViewer().getSelection();
		if (selection != null) {
			if (getViewer().isInRangeMode()) {
				DesignRange range = (DesignRange) selection;
				if (range.isValid()) {
					IDOMPosition domPos = DOMPositionHelper.toDOMPosition(range
							.getStartPosition());
					IDOMPosition domEnd = DOMPositionHelper.toDOMPosition(range
							.getEndPosition());
					if (EditValidateUtil.validPosition(domPos)
							&& EditValidateUtil.validPosition(domEnd)) {
						position = EditModelQuery
								.getIndexedRegionLocation(domPos);
						int end = EditModelQuery
								.getIndexedRegionLocation(domEnd);
						if (end < position) {
							length = position - end;
							position = end;
						} else {
							length = end - position;
						}
					}
				}
			} else {
				Object object = ((IStructuredSelection) selection)
						.getFirstElement();
				if (object instanceof ElementEditPart) {
					Node node = ((ElementEditPart) object).getIDOMNode();
					position = EditModelQuery.getNodeStartIndex(node);
					length = EditModelQuery.getNodeLenth(node);
				}
			}
		}
		if (position >= 0 && length >= 0) {
			getModel().beginRecording(this, getLabel(), position, length);
		} else {
			getModel().beginRecording(this, getLabel());
		}
		//Bug 332479 - [WPE] Design page processing still takes place even when design page is hidden
		if (!htmlEditorIsInSourceOnlyMode()) {
			getViewer().startSelectionChange();
		}
		getModel().aboutToChangeModel();
		return true;
	}

	/**
	 * child class can override this method for any pre action.
	 */
	protected void preExecute() {
        // does nothing; children may wish to implement
	}

	/**
	 * child class should override this method for the real action.
	 */
	protected abstract void doExecute();

	/**
	 * child class can override this method for any post action. NOTE: if
	 * preExecute() or doExecute() throw exception, then this method will NOT be
	 * called.
	 */
	protected void postExecute() {
        // does nothing; child may wish to implement
	}

	/**
	 * if prePreExecute() return true, then this method will always be called
	 * even preExecute()/doExecute() and postExecute() fail.
	 */
	protected void postPostExecute() {
		getModel().changedModel();

		// after "changedModel()" is called, model will fire out batched events
		// about model change
		// and EditPart will be refreshed. Only at this time, could we use
		// EditPart to construct the
		// result selection.

		// enforce a validate, so things get layed out, thus all the figures
		// will be valid.

		//Bug 332479 - [WPE] Design page processing still takes place even when design page is hidden
		if (!htmlEditorIsInSourceOnlyMode()) {
			this.getViewer().getViewport().validate();
		}

		ISelection sel = getAfterCommandDesignerSelection();
		if (sel != null) {
			ITextSelection textSel = SelectionHelper
					.convertFromDesignSelectionToTextSelection(sel);
			if (textSel != null) {
				getModel().endRecording(this, textSel.getOffset(),
						textSel.getLength());
			} else {
				getModel().endRecording(this);
			}
		} else {
			getModel().endRecording(this);
		}

		//Bug 332479 - [WPE] Design page processing still takes place even when design page is hidden
		if (!htmlEditorIsInSourceOnlyMode()) {
			if (sel != null) {
				getViewer().setSelection(sel);
			} else {
				getViewer().deselectAll();
			}
			if (getViewer() != null) {
				getViewer().selectionChanged();
			}
		}
	}

	/**
	 * child class should override this method to provide the selection after
	 * command. This method is called after model changed. So at time of this
	 * call, the editpart should be valid. Default implementation.
	 * 
	 * @return the selection
	 */
	protected abstract ISelection getAfterCommandDesignerSelection();

	/**
	 * @param range
	 * @return a selection that contains the dom range or null if one doesn't exist
	 */
	protected ISelection toDesignRange(DOMRange range) {
		try {
			if (range == null) {
				return null;
			}
			IDOMPosition startPos = range.getStartPosition();
			DesignPosition start = DOMPositionHelper.toDesignPosition(startPos);
			if (range.isEmpty()) {
				return new DesignRange(start, start);
			}
            IDOMPosition endPos = range.getEndPosition();
            return new DesignRange(start, DOMPositionHelper
            		.toDesignPosition(endPos));
		} catch (Exception e) {
			// "Selection error"
			_log.error("Error.RangeModeCommand.SetSelection"); //$NON-NLS-1$
			return null;
		}

	}

	/**
	 * @param node
	 * @return a selection for the node
	 */
	protected IStructuredSelection toDesignSelection(Node node) {
		if (node instanceof INodeNotifier) {
			EditPart part = (EditPart) ((INodeNotifier) node)
					.getAdapterFor(EditPart.class);
			if (part != null) {
				return new StructuredSelection(part);
			}
		}
		return null;
	}

	//    
	// /**
	// * set selection to the specified node. Normally called by child class in
	// <code>setSelection()</code> implementation.
	// *
	// * @param node
	// */
	// protected final void setSelection(Node node)
	// {
	// EditPart part = (EditPart) ((INodeNotifier)
	// node).getAdapterFor(EditPart.class);
	//
	// StructuredSelection sel = new StructuredSelection(part);
	// getViewer().setSelection(sel);
	// }

	/**
	 * format the specified node in source code. Utility method that can be
	 * called by child classes
	 * 
	 * @param node
	 */
	public void formatNode(Node node) {
		// XXX: there should have some other way to get the FormatProcessor.
		// currently hardcoded to HTMLFormatProcessorImpl().
		new HTMLFormatProcessorImpl().formatNode(node);
	}

	/**
	 * Re-executes the Command. This method should only be called after
	 * <code>undo()</code> has been called.
	 */
	public void redo() {
		// this method should in fact never be called, because we have already
		// delegate undo
		// operations to source view.
		getModel().getUndoManager().redo();
	}

	/**
	 * Undoes the changes performed during <code>execute()</code>. This
	 * method should only be called after <code>execute</code> has been
	 * called, and only when <code>canUndo()</code> returns <code>true</code>.
	 * 
	 * @see #canUndo()
	 */
	public void undo() {
		// this method should in fact never be called, because we have already
		// delegate undo
		// operations to source view.
		getModel().getUndoManager().undo();
	}

	/**
	 * a utility method. NOTE: this method can ONLY be called BEFORE you change
	 * anything in the model.
	 * 
	 * @param ele
	 * @return the figure info for the  element
	 */
	public IFigure getFigureInfo(Element ele) {
		if (ele instanceof IDOMElement) {
			EditPart part = (EditPart) ((IDOMElement) ele)
					.getAdapterFor(EditPart.class);
			if (part instanceof GraphicalEditPart) {
				return ((GraphicalEditPart) part).getFigure();
			}
		}
		return null;
	}

	/**
	 * Returns true if HTMLEditor is in source-only mode.
	 * @return <code>true</code> if HTMLEditor is in source-only mode, else <code>false</code>.
	 */
	protected boolean htmlEditorIsInSourceOnlyMode() {
		boolean ret = false;
		if (_viewer instanceof HTMLGraphicalViewer) {
			IEditorPart parent = ((HTMLGraphicalViewer)_viewer).getParent();
			if (parent instanceof SimpleGraphicalEditor) {
				HTMLEditor htmlEditor = ((SimpleGraphicalEditor)parent).getHTMLEditor();
				if (htmlEditor != null) {
					ret = (htmlEditor.getDesignerMode() == HTMLEditor.MODE_SOURCE);
				}
			}
		}
		return ret;
	}

}
