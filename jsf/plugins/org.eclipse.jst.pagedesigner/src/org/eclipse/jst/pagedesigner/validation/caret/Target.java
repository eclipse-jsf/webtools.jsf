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
package org.eclipse.jst.pagedesigner.validation.caret;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.text.Assert;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class Target {
	private EditPart _part;

	private Node _node;

	public Target(EditPart part) {
		_part = part;
		Assert.isTrue(part.getModel() instanceof Node);
		_node = (Node) part.getModel();
	}

	public Target(Node node) {
		_node = node;
		Assert.isTrue(node instanceof INodeNotifier);
		if (((INodeNotifier) node).getAdapterFor(EditPart.class) != null) {
			_part = (EditPart) ((INodeNotifier) node)
					.getAdapterFor(EditPart.class);
		}
	}

	/**
	 * @return Returns the _node.
	 */
	public Node getNode() {
		return _node;
	}

	/**
	 * @return Returns the _part.
	 */
	public EditPart getPart() {
		return _part;
	}

	public static EditPart resolvePart(Node node) {
		if (node instanceof INodeNotifier
				&& ((INodeNotifier) node).getAdapterFor(EditPart.class) != null) {
			return (EditPart) ((INodeNotifier) node)
					.getAdapterFor(EditPart.class);
		}
		return null;
	}

	public static Node resolveNode(EditPart part) {
		return (Node) part.getModel();
	}
}
