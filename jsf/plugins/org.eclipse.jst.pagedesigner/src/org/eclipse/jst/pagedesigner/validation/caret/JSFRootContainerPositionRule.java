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
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationRequest;
import org.eclipse.jst.pagedesigner.utils.JSPUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Ensure the DnD of jsf component should be within f:view or f:subview.
 * 
 * @author mengbo
 */
public class JSFRootContainerPositionRule extends DefaultPositionRule {
	public static final String[] JSF_ROOT_CONTAINERS = { "view", "subview" };

	/**
	 * @param mediator
	 * @param actionData
	 */
	public JSFRootContainerPositionRule(IPositionMediator mediator,
			ActionData actionData) {
		super(mediator, actionData);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.validation.caret.IPositionRule#hasEditableArea(org.eclipse.jst.pagedesigner.validation.caret.Target)
	 */
	public boolean hasEditableArea(Target target) {
		Node node = target.getNode();
		if (hasBasicContainers(EditModelQuery.getDocumentNode(node))) {
			if (_actionData.getActionType() == ActionData.PALETTE_DND
					|| _actionData.getActionType() == ActionData.COMPONENT_MOVE) {
				if (_actionData.getData() instanceof ItemCreationRequest) {
					String uri = ((ItemCreationRequest) _actionData.getData())
							.getItemDescriptor().getURI();
					if (IJMTConstants.URI_JSF_HTML.equalsIgnoreCase(uri)
							|| IJMTConstants.URI_JSF_CORE.equalsIgnoreCase(uri)) {
						boolean result = EditModelQuery.isChild(
								JSF_ROOT_CONTAINERS, node, true, false);
						return result
								| EditModelQuery.getChild(node,
										JSF_ROOT_CONTAINERS, 3, false) != null;
					}
				} else if (_actionData.getData() instanceof List) {
					IDOMNode carriedNode = ((IDOMNode) Target
							.resolveNode((EditPart) ((List) _actionData
									.getData()).get(0)));
					IDOMModel model = carriedNode.getModel();
					String uri = JSPUtil.findURIForPrefix(model, carriedNode
							.getPrefix());
					if (IJMTConstants.URI_JSF_HTML.equalsIgnoreCase(uri)
							|| IJMTConstants.URI_JSF_CORE.equalsIgnoreCase(uri)) {
						return EditModelQuery.isChild(JSF_ROOT_CONTAINERS,
								node, true, false);
					}
				}
			} else if (_actionData.getActionType() == ActionData.DATABINDING_DND) {
				boolean result = EditModelQuery.isChild(JSF_ROOT_CONTAINERS,
						node, true, false);
				return result
						| EditModelQuery.getChild(node, JSF_ROOT_CONTAINERS, 3,
								false) != null;
			}
		}
		return super.isEditable(target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.validation.caret.IPositionRule#isEditable(org.eclipse.jst.pagedesigner.validation.caret.Target)
	 */
	public boolean isEditable(Target target) {
		boolean result = true;
		Node node = target.getNode();
		if (hasBasicContainers(EditModelQuery.getDocumentNode(node))) {
			if (_actionData.getActionType() == ActionData.PALETTE_DND
					|| _actionData.getActionType() == ActionData.COMPONENT_MOVE) {
				if (_actionData.getData() instanceof ItemCreationRequest) {
					String uri = ((ItemCreationRequest) _actionData.getData())
							.getItemDescriptor().getURI();
					if (IJMTConstants.URI_JSF_HTML.equalsIgnoreCase(uri)
							|| IJMTConstants.URI_JSF_CORE.equalsIgnoreCase(uri)) {
						result = EditModelQuery.isChild(JSF_ROOT_CONTAINERS,
								node, true, false);
					}
				} else if (_actionData.getData() instanceof List) {
					IDOMNode carriedNode = ((IDOMNode) Target
							.resolveNode((EditPart) ((List) _actionData
									.getData()).get(0)));
					IDOMModel model = carriedNode.getModel();
					String uri = JSPUtil.findURIForPrefix(model, carriedNode
							.getPrefix());
					if (IJMTConstants.URI_JSF_HTML.equalsIgnoreCase(uri)
							|| IJMTConstants.URI_JSF_CORE.equalsIgnoreCase(uri)) {
						result = EditModelQuery.isChild(JSF_ROOT_CONTAINERS,
								node, true, false);
					}
				}
			} else if (_actionData.getActionType() == ActionData.DATABINDING_DND) {
				result = EditModelQuery.isChild(JSF_ROOT_CONTAINERS, node,
						true, false);
			}
			return result;
		}
		return super.isEditable(target);
	}

	public static boolean isWithinkBasicContainer(Node node) {
		return EditModelQuery.isChild(JSF_ROOT_CONTAINERS, node, false, false);
	}

	public static Node getBasicContainer(Document document) {
		Node node = EditModelQuery.getChild(document, JSF_ROOT_CONTAINERS, 3,
				false);
		return node;
	}

	/**
	 * We need to see if body, view are there. and they should be at first or
	 * second level.
	 * 
	 * @param document
	 * @return
	 */
	public static boolean hasBasicContainers(Document document) {
		return getBasicContainer(document) != null;
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
			if (Arrays.asList(JSF_ROOT_CONTAINERS).contains(
					node.getLocalName().toLowerCase())) {
				return EditModelQuery.isChild(
						RootContainerPositionRule.HTML_ROOT_CONTAINERS, node,
						false, false)
						|| EditModelQuery.isChild(JSF_ROOT_CONTAINERS, node,
								false, false);
			}
		}
		return super.canReference(target, atRight);
	}
}
