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

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Ensure the DnD of jsf component should be within f:view or f:subview.
 * 
 * @author mengbo
 */
public class JSFRootContainerPositionRule extends DefaultPositionRule {
    
    private final static int  DEFAULT_MAX_DEPTH_SEARCH = 3;
    
	/**
	 * TODO: this is not enough because it ignores the uri and keys on only
	 * the name of the tag.
	 */
	/*package*/ static final String[] JSF_ROOT_CONTAINERS = { "view", "subview" }; //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * @param actionData
	 */
	public JSFRootContainerPositionRule(ActionData actionData) {
		super(actionData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.validation.caret.IPositionRule#hasEditableArea(org.eclipse.jst.pagedesigner.validation.caret.Target)
	 */
	public boolean hasEditableArea(Target target) {
		Node node = target.getNode();
		if (hasBasicContainers(EditModelQuery.getDocumentNode(node),DEFAULT_MAX_DEPTH_SEARCH)) {
            ActionData actionData = getActionData();
			if (actionData instanceof DropActionData) {
                DropActionData dropActionData = (DropActionData) actionData;
                TagIdentifier tagId = 
                     (TagIdentifier) dropActionData.getDropData().getTagIdentifiers().get(0);
                final String uri  = tagId.getUri();
				if (ITLDConstants.URI_JSF_HTML.equalsIgnoreCase(uri)
						|| ITLDConstants.URI_JSF_CORE.equalsIgnoreCase(uri)) {
					boolean result = EditModelQuery.isChild(
							JSF_ROOT_CONTAINERS, node, true, false);
					return result
							|| EditModelQuery.getChild(node,
									JSF_ROOT_CONTAINERS, 3, false) != null;
				}
			} else if (getActionData().getActionType() == ActionData.DATABINDING_DND) {
				boolean result = EditModelQuery.isChild(JSF_ROOT_CONTAINERS,
						node, true, false);
				return result
						|| EditModelQuery.getChild(node, JSF_ROOT_CONTAINERS, 3,
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
		if (hasBasicContainers(EditModelQuery.getDocumentNode(node), DEFAULT_MAX_DEPTH_SEARCH)) {
            ActionData actionData = getActionData();
            if (actionData instanceof DropActionData) {
                DropActionData dropActionData = (DropActionData) actionData;
                TagIdentifier tagId = 
                     (TagIdentifier) dropActionData.getDropData().getTagIdentifiers().get(0);
                final String uri  = tagId.getUri();

				if (ITLDConstants.URI_JSF_HTML.equalsIgnoreCase(uri)
						|| ITLDConstants.URI_JSF_CORE.equalsIgnoreCase(uri)) {
					result = EditModelQuery.isChild(JSF_ROOT_CONTAINERS,
							node, true, false);
				}
			} else if (getActionData().getActionType() == ActionData.DATABINDING_DND) {
				result = EditModelQuery.isChild(JSF_ROOT_CONTAINERS, node,
						true, false);
			}
			return result;
		}
		return super.isEditable(target);
	}


	/**
	 * @param document
	 * @param maxDepth 
	 * @return the closest tag called "view" or "subview" to the root of document
	 * search to a maximum tag depth of maxDepth
	 */
	public static Node getBasicContainer(Document document, int maxDepth) {
		Node node = EditModelQuery.getChild(document, JSF_ROOT_CONTAINERS, maxDepth,
				false);
		return node;
	}

	/**
	 * We need to see if body, view are there. and they should be at first or
	 * second level.
	 * 
	 * @param document
	 * @param maxDepth 
	 * @return true if document has a view and subview limited to a maximum search
	 * depth of maxDepth
	 */
	public static boolean hasBasicContainers(Document document, int maxDepth) {
		return getBasicContainer(document, maxDepth) != null;
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
            // if the local name of the target is in the list of JSF root
            // containers
			if (Arrays.asList(JSF_ROOT_CONTAINERS).contains(
					node.getLocalName().toLowerCase())) {
                
                // verify that either the target is 
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
