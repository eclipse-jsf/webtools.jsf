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
	/**
	 * a singleton that can be used as an invalid position
	 */
	public static final DesignPosition INVALID = new DesignPosition(null, -1);

	private EditPart _containerPart;

	int _offset;

	//private Node _containerNode;

	/**
	 * @param part
	 * @param offset
	 */
	public DesignPosition(EditPart part, int offset) {
		_containerPart = part;
		_offset = offset;
	}

	/**
	 * if _containerPart is null, means it is invalid
	 * 
	 * @return the container edit part
	 */
	public EditPart getContainerPart() {
		return _containerPart;
	}

	/**
	 * @return the container node
	 */
	public Node getContainerNode() {
		if (_containerPart != null) {
			return (Node) _containerPart.getModel();
		}
        return null;
	}

	/**
	 * if offset < 0, means it is invalid.
	 * 
	 * @return the offset
	 */
	public int getOffset() {
		return _offset;
	}

	/**
	 * @return true if the design position is valid
	 */
	public boolean isValid() {
		return (_containerPart != null) && (_offset >= 0);
	}

	/**
	 * This method should not be called when is text node.
	 * 
	 * @param forward
	 * @return the sibling part one to right in the tree if 
	 * forward == true, one to the left if forward == false.  May
	 * return null if position is invalid or there is no valid sibling.
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
	 * @return a design position one before part
	 */
	public static DesignPosition createPositionBeforePart(EditPart part) {
		EditPart parent = part.getParent();

		if (parent == null) {
			return new DesignPosition(part, 0);
		}
        return new DesignPosition(parent, parent.getChildren()
        		.indexOf(part));
	}

	/**
	 * factory method
	 * 
	 * @param part
	 * @return the design position for one after part
	 */
	public static DesignPosition createPositionAfterPart(EditPart part) {
		EditPart parent = part.getParent();

		if (parent == null) {
			return new DesignPosition(part, part.getChildren().size());
		}
        return new DesignPosition(parent, parent.getChildren()
        		.indexOf(part) + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
	    if (obj == this)
	    {
	        return true;
	    }
	    
		if (obj instanceof DesignPosition) {
			DesignPosition p = (DesignPosition) obj;

			return (p.getContainerPart() == this._containerPart)
					&& (p.getOffset() == this._offset);
		}

		return false;
	}

	
	@Override
    public int hashCode() {
	    return System.identityHashCode(_containerPart) ^ System.identityHashCode(Integer.valueOf(_offset));
    }

	/**
	 * @param buffer
	 * @return the buffer with the debug dump
	 */
	public StringBuffer debugDump(StringBuffer buffer) {
//		try {
			buffer.append("DesignPosition: ").append(this._containerPart) //$NON-NLS-1$
					.append(": ").append(this._offset).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$

			if (this._containerPart.getModel() instanceof Text) {
				// skip
			} else {
				if (this._offset > 0) {
					buffer.append("after: ").append( //$NON-NLS-1$
							this._containerPart.getChildren().get(
									this._offset - 1)).append("\n"); //$NON-NLS-1$
				}

				if (this._offset < (this._containerPart.getChildren().size() - 1)) {
					buffer.append("before: ") //$NON-NLS-1$
							.append(
									this._containerPart.getChildren().get(
											this._offset)).append("\n"); //$NON-NLS-1$
				}
			}
            //TODO: what was being caught here?
//		} catch (Exception e) {
//		}

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
