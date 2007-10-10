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

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
/*package*/ class ContainerMoveInAndOutRule extends DefaultMovementRule {
	private static final String[] HTML_CONTAINERS = { IHTMLConstants.TAG_TABLE };

	private static final String[] NONE_HTML_CONTAINERS = {};

	private static final String[] SPECIAL_HTML_CONTAINERS = {
			IJSFConstants.TAG_OUTPUTLINK, IJSFConstants.TAG_COMMANDLINK,
			IJSFConstants.TAG_FACET, IJSFConstants.TAG_VERBATIM };

	/**
	 * @param actionData
	 */
	public ContainerMoveInAndOutRule(ActionData actionData) {
		super(actionData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IMovementRule#canEnter(org.w3c.dom.Node)
	 */
	public boolean allowsMoveIn(Target target) {
		Node node = target.getNode();
		if (node.getLocalName() != null && //
				(Arrays.asList(HTML_CONTAINERS).contains(node.getLocalName()
						.toLowerCase())) || //
				Arrays.asList(NONE_HTML_CONTAINERS).contains(
						node.getLocalName()) || //
				Arrays.asList(SPECIAL_HTML_CONTAINERS).contains(
						node.getLocalName())) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IMovementRule#canMoveOut(org.eclipse.gef.EditPart)
	 */
	public boolean allowsMoveOut(Target target) {
		Node node = target.getNode();
		if (EditModelQuery.isDocument(node)) {
			return false;
		}

		if (_actionData.getActionType() == ActionData.INLINE_EDIT
				&& (IHTMLConstants.TAG_TD.equalsIgnoreCase(node.getLocalName()) || IHTMLConstants.TAG_TH
						.equalsIgnoreCase(node.getLocalName()))) {
			return false;
		}
		if (node.getLocalName() != null && //
				(Arrays.asList(HTML_CONTAINERS).contains(node.getLocalName()
						.toLowerCase())) || //
				Arrays.asList(NONE_HTML_CONTAINERS).contains(
						node.getLocalName())) {
			return false;
		}

		if (node.getLocalName() != null
				&& (Arrays.asList(
						RootContainerPositionRule.HTML_ROOT_CONTAINERS)
						.contains(node.getLocalName().toLowerCase()) || //
				Arrays.asList(JSFRootContainerPositionRule.JSF_ROOT_CONTAINERS)
						.contains(node.getLocalName()))) {
			if (!EditModelQuery.isChild(
					JSFRootContainerPositionRule.JSF_ROOT_CONTAINERS, node,
					false, true)
					&& //
					!EditModelQuery.isChild(
							RootContainerPositionRule.HTML_ROOT_CONTAINERS,
							node, true, true)) {
				return false;
			}
		}
		return true;
	}
}
