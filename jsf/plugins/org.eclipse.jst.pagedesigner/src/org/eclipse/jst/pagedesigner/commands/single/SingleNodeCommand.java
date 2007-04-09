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
package org.eclipse.jst.pagedesigner.commands.single;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.DOMRangeHelper;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * This is the super class for those commands that change attribute or tag name
 * of a single element.
 * 
 * This base class helps handles selection. As after the command, the editpart
 * may totally change, so it tried to remember the selection before command and
 * then restore it after the command.
 * 
 * So the limitation to child class of this command is that: the command should
 * only change a single element node. It could remove the node and replace with
 * a new one, but should not touch other nodes.
 * 
 * @author mengbo
 */
public abstract class SingleNodeCommand extends DesignerCommand {
	List _structuredSelectedNodes = null;

	DOMRange _rangeSelection = null;

	IDOMElement _originalElement;

	IDOMElement _replacement;

	/**
	 * @param label
	 * @param node
	 */
	public SingleNodeCommand(String label, IDOMElement node) {
		super(label, node);
		_originalElement = node;
	}

	protected void preExecute() {
		super.preExecute();
		// remember current selection
		ISelection selection = getViewer().getSelection();
		if (selection instanceof IStructuredSelection) {
			Object[] array = ((IStructuredSelection) selection).toArray();
			_structuredSelectedNodes = new ArrayList();
			if (array != null) {
				for (int i = 0; i < array.length; i++) {
					EditPart part = (EditPart) array[i];
					_structuredSelectedNodes.add(part.getModel());
				}
			}
		} else if (selection instanceof DesignRange) {
			DesignRange range = (DesignRange) selection;
			_rangeSelection = DOMRangeHelper.toDOMRange(range);
		}
	}

	/**
	 * this method is to be called by child class in the doExecute() method.
	 * Telling the super class that the original element will be replaced by the
	 * specified element.
	 * 
	 * @param ele
	 */
	protected void setReplacedElement(IDOMElement ele) {
		_replacement = ele;
	}

	/**
	 * @return the replacement element
	 */
	protected IDOMElement getReplacedElment() {
		return _replacement;
	}

	/**
	 * @return the original element
	 */
	protected IDOMElement getOriginalElement() {
		return _originalElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
	 */
	protected final ISelection getAfterCommandDesignerSelection() {
		if (_structuredSelectedNodes != null) {
			// handle replacement fire.
			if (_replacement != null && _replacement != _originalElement) {
				int index = _structuredSelectedNodes.indexOf(_originalElement);
				if (index >= 0) {
					_structuredSelectedNodes.set(index, _replacement);
				}
			}

			// as the editpart may have been refreshed, so recreated the
			// selection
			List parts = new ArrayList();
			for (int i = 0, size = _structuredSelectedNodes.size(); i < size; i++) {
				Object obj = _structuredSelectedNodes.get(i);
				if (obj instanceof INodeNotifier) {
					EditPart part = (EditPart) ((INodeNotifier) obj)
							.getAdapterFor(EditPart.class);
					if (part != null)
						parts.add(part);
				}
			}
			StructuredSelection sel = new StructuredSelection(parts);
			return sel;
		} else if (_rangeSelection != null) {
			DOMRange newrange = handleReplacement(_rangeSelection,
					_originalElement, _replacement);
			return DOMRangeHelper.toDesignRange(newrange);
		} else {
			return null;
		}
	}

	private DOMRange handleReplacement(DOMRange selection,
			IDOMElement original, IDOMElement replacement) {
		if (replacement == null || replacement == original)
			return selection;
		return DOMRangeHelper.handleReplacement(selection, original,
				replacement);
	}
}
