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

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditPart;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class Target {
	private EditPart _part;

	private Node _node;

	/**
	 * @param part
	 */
	public Target(EditPart part) {
		_part = part;
        
        if(part.getModel() instanceof Node)
        {
            _node = (Node) part.getModel();        
        }
        else if (part.getModel() instanceof ITagConverter)
        {
            _node = ((ITagConverter)part.getModel()).getHostElement();
        }
        else
        {
            Assert.isTrue(false);
        }
	}

	/**
	 * @param node
	 */
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

    /**
     * @return the target's tag wrapper or null if the target
     * is not an Element node
     */
    public TagIdentifier getTagWrapper()
    {
        if (_node instanceof Element)
        {
            return TagIdentifierFactory.createDocumentTagWrapper((Element)_node);
        }
        
        return null;
    }
    
	/**
	 * @param node
	 * @return the edit part for node or null if none found
	 */
	public static EditPart resolvePart(Node node) {
		if (node instanceof INodeNotifier
				&& ((INodeNotifier) node).getAdapterFor(EditPart.class) != null) {
			return (EditPart) ((INodeNotifier) node)
					.getAdapterFor(EditPart.class);
		}
		return null;
	}

	/**
	 * @param part
	 * @return the node for part
	 */
	public static Node resolveNode(EditPart part) {
		return (Node) part.getModel();
	}
}
