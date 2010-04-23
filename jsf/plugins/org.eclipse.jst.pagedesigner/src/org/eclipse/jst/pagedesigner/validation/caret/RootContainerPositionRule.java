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

import java.util.Arrays;

import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * This rule deal with containers 'body', 'head', 'view', 'subview'.
 * 
 * @author mengbo
 */
public class RootContainerPositionRule extends DefaultPositionRule {
	static final String[] HTML_ROOT_CONTAINERS = { IHTMLConstants.TAG_BODY, IHTMLConstants.TAG_HEAD };

	/**
	 * @param actionData
	 */
	public RootContainerPositionRule(ActionData actionData) {
		super(actionData);
	}

	/**
	 * 1. If anyone of the three containers exists, the target should be in the
	 * container. 2. If none of the containers exists, then target will be not
	 * restricted.
	 * 
	 * @see org.eclipse.jst.pagedesigner.validation.caret.IPositionRule#hasEditableArea(org.eclipse.jst.pagedesigner.validation.caret.Target)
	 */
	public boolean hasEditableArea(Target target) {
		EditPart part = target.getPart();
		if (part == null) {
			return false;
		}
		Node node = target.getNode();
		if (hasBasicContainers(EditModelQuery.getDocumentNode(node))) {
			return true;// isWithinBasicContainer(node);
		}
		return super.hasEditableArea(target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IPositionRule#isEditable(org.eclipse.gef.EditPart)
	 */
	public boolean isEditable(Target target) {
		Node node = target.getNode();
		if (hasBasicContainers(EditModelQuery.getDocumentNode(node))) {
			boolean result = isWithinBasicContainer(node);
			return result;
		}
		return super.isEditable(target);
	}

	/**
	 * We need to see if body, view are there. and they should be at first or
	 * second level.
	 * 
	 * @param document
	 * @return true if the document has basic containers
	 */
	public static boolean hasBasicContainers(Document document) {
		return getBasicContainer(document) != null;

	}

	private static boolean isWithinBasicContainer(Node node) {
		return EditModelQuery.isChild(HTML_ROOT_CONTAINERS, node, true, false);
	}

	/**
	 * @param document
	 * @return the basic root container for the document or null
	 */
	public static Node getBasicContainer(Document document) {
		Node node = EditModelQuery.getChild(document, HTML_ROOT_CONTAINERS, 2,
				false);
		if (node == null) {
			node = EditModelQuery.getChild(document, HTML_ROOT_CONTAINERS, 2,
					true);
		}
		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.validation.caret.IPositionRule#canReference(org.eclipse.jst.pagedesigner.validation.caret.Target,
	 *      boolean)
	 */
	public boolean canReference(Target target, boolean atRight) {
		Node node = target.getNode();
		if (node.getLocalName() != null) {
			if (Arrays.asList(HTML_ROOT_CONTAINERS).contains(
					node.getLocalName().toLowerCase())) {
				return EditModelQuery.isChild(
						JSFRootContainerPositionRule.JSF_ROOT_CONTAINERS, node,
						false, false);
			}
		}
		return true;
	}
}
