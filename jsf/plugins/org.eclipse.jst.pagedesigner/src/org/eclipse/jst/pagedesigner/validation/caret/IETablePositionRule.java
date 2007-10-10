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
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.EditValidateUtil;
import org.w3c.dom.Node;

/**
 * This rule constains the operation within a table: 1. The inputing position
 * can only be in 'td' 2. Table structure must be valid.
 * 
 * @author mengbo
 */
public class IETablePositionRule extends DefaultPositionRule {
	// We will introduce validation based on DtD later, this is not final
	// solution.
	private final String[] CONTAINER = { IHTMLConstants.TAG_THEAD,
			IHTMLConstants.TAG_TBODY, IHTMLConstants.TAG_TFOOT };

	/**
	 * @param actionData 
	 */
	public IETablePositionRule(ActionData actionData) {
		super(actionData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IPositionRule#hasEditableArea(org.eclipse.jst.pagedesigner.caret.Target)
	 */
	public boolean hasEditableArea(Target target) {
		if (EditModelQuery.isChild(IHTMLConstants.TAG_TABLE, target.getNode(),
				true)) {
			if (target.getPart() == null) {
				return false;
			}
			Node node = target.getNode();
			// The target must be in a valid table structure.
			String name = node.getLocalName();
			if (node.hasChildNodes()) {
				// for constrained container, depends on its children.
				if (name != null
						&& (IHTMLConstants.TAG_TABLE.equalsIgnoreCase(name) || //
								Arrays.asList(CONTAINER).contains(
										name.toLowerCase()) || //
						IHTMLConstants.TAG_TR.equalsIgnoreCase(name))) {
					List children = target.getPart().getChildren();
					for (int i = 0, n = children.size(); i < n; i++) {
						if (hasEditableArea(new Target((EditPart) children
								.get(i)))) {
							return true;
						}
					}
					return false;
				}
			} else {
				if (!isEditable(new Target(node))) {
					return false;
				}
			}
		}
		return super.hasEditableArea(target);
	}

	/**
	 * Used to valid the structure of table, later will use dtd to do that.
	 * @param container 
	 * @return true if the table is valid
	 */
	public boolean isInValidTable(Node container) {
		boolean result = false;
		try {
			if (EditValidateUtil.validNode(container)) {
				if (EditModelQuery.isText(container)) {
					container = container.getParentNode();
				}
				String name = container.getLocalName();
				if (EditModelQuery.isChild(IHTMLConstants.TAG_TABLE, container,
						true)) {
					List ancestors = EditModelQuery.getAncestors(container,
							IHTMLConstants.TAG_TABLE, true);
					int offset = ancestors.size();
					// remove 'table'
					Node temp = (Node) ancestors.remove(offset - 1);
					if (temp == container) {
						return true;
					}
					offset--;
					result = checkValidTrTd(ancestors, container);
					if (!result) {
						// thead->tr->td
						temp = (Node) ancestors.get(offset - 1);
						name = temp.getNodeName();
						if (Arrays.asList(CONTAINER).contains(
								name.toLowerCase())) {
							if (temp == container) {
								result = true;
							} else {
								// remove 'thead'
								ancestors.remove(offset - 1);
								offset--;
								result = checkValidTrTd(ancestors, container);
							}
						}
					}
				}
			}
			return result;
		} catch (Exception e) {
			// The exception means the structure is not a valid table, don't
			// need to report.
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.caret.IPositionRule#isEditable(org.eclipse.jst.pagedesigner.caret.Target)
	 */
	public boolean isEditable(Target target) {
		if (EditModelQuery.isChild(IHTMLConstants.TAG_TABLE, target.getNode(),
				false)) {
			if (isInValidTable(target.getNode())) {
				List ancestors = EditModelQuery.getAncestors(target.getNode(),
						IHTMLConstants.TAG_TABLE, true);
				if (ancestors.size() >= 3) {
					if (IHTMLConstants.TAG_TH
							.equalsIgnoreCase(((Node) ancestors.get(ancestors
									.size() - 3)).getNodeName())
							|| //
							IHTMLConstants.TAG_TD
									.equalsIgnoreCase(((Node) ancestors
											.get(ancestors.size() - 3))
											.getNodeName())) {
						return true;
					} else if (ancestors.size() >= 4 //
							&& (IHTMLConstants.TAG_TH
									.equalsIgnoreCase(((Node) ancestors
											.get(ancestors.size() - 4))
											.getNodeName()) || //
							IHTMLConstants.TAG_TD
									.equalsIgnoreCase(((Node) ancestors
											.get(ancestors.size() - 4))
											.getNodeName()))) {
						return true;
					}
				}
			}
			return false;
		}
		return super.isEditable(target);
	}

	private boolean checkValidTrTd(List ancestors, Node node) {
		int offset = ancestors.size();
		if (IHTMLConstants.TAG_TR.equalsIgnoreCase(((Node) ancestors
				.get(offset - 1)).getLocalName())) {
			if (ancestors.get(offset - 1) == node) {
				return true;
			} else if (IHTMLConstants.TAG_TH.equalsIgnoreCase(((Node) ancestors
					.get(offset - 2)).getLocalName())
					|| //
					IHTMLConstants.TAG_TD.equalsIgnoreCase(((Node) ancestors
							.get(offset - 2)).getLocalName())) {
				return true;
			}
		}
		return false;
	}
}
