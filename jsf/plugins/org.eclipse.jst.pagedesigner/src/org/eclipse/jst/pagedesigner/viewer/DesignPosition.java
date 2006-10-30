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
package org.eclipse.jst.pagedesigner.viewer;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author mengbo
 */
public class DesignPosition {
	public static final DesignPosition INVALID = new DesignPosition(null, -1);

	private EditPart _containerPart;

	int _offset;

	private Node _containerNode;

	/**
	 * @param part
	 * @param offset
	 */
	public DesignPosition(EditPart part, int offset) {
		_containerPart = part;
		_offset = offset;

		int[] a = new int[] { 0, 0 };
	}

	/**
	 * if _containerPart is null, means it is invalid
	 * 
	 * @return
	 */
	public EditPart getContainerPart() {
		return _containerPart;
	}

	public Node getContainerNode() {
		if (_containerPart != null) {
			return (Node) _containerPart.getModel();
		} else {
			return null;
		}
	}

	/**
	 * if offset < 0, means it is invalid.
	 * 
	 * @return
	 */
	public int getOffset() {
		return _offset;
	}

	public boolean isValid() {
		return (_containerPart != null) && (_offset >= 0);
	}

	/**
	 * This method should not be called when is text node.
	 * 
	 * @param forward
	 * @return
	 */
	public EditPart getSiblingEditPart(boolean forward) {
		if (!isValid()) {
			return null;
		}

		int index = forward ? (_offset) : (_offset - 1);
		List children = _containerPart.getChildren();

		if ((index >= children.size()) || (index < 0)) {
			return null;
		}

		return (EditPart) children.get(index);
	}

	/**
	 * factory method
	 * 
	 * @param part
	 * @return
	 */
	public static DesignPosition createPositionBeforePart(EditPart part) {
		EditPart parent = part.getParent();

		if (parent == null) {
			return new DesignPosition(part, 0);
		} else {
			return new DesignPosition(parent, parent.getChildren()
					.indexOf(part));
		}
	}

	/**
	 * factory method
	 * 
	 * @param part
	 * @return
	 */
	public static DesignPosition createPositionAfterPart(EditPart part) {
		EditPart parent = part.getParent();

		if (parent == null) {
			return new DesignPosition(part, part.getChildren().size());
		} else {
			return new DesignPosition(parent, parent.getChildren()
					.indexOf(part) + 1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof DesignPosition) {
			DesignPosition p = (DesignPosition) obj;

			return (p.getContainerPart() == this._containerPart)
					&& (p.getOffset() == this._offset);
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public StringBuffer debugDump(StringBuffer buffer) {
		try {
			buffer.append("DesignPosition: ").append(this._containerPart)
					.append(": ").append(this._offset).append("\n");

			if (this._containerPart.getModel() instanceof Text) {
				// skip
			} else {
				if (this._offset > 0) {
					buffer.append("after: ").append(
							this._containerPart.getChildren().get(
									this._offset - 1)).append("\n");
				}

				if (this._offset < (this._containerPart.getChildren().size() - 1)) {
					buffer.append("before: ")
							.append(
									this._containerPart.getChildren().get(
											this._offset)).append("\n");
				}
			}
		} catch (Exception e) {
		}

		return buffer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		return debugDump(buffer).toString();
	}
}
