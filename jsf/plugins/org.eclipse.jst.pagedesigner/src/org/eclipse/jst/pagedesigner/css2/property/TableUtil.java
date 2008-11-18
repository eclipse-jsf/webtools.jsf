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
package org.eclipse.jst.pagedesigner.css2.property;

import org.eclipse.jst.pagedesigner.utils.HTMLUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableUtil {
	static final String TOP = "top"; //$NON-NLS-1$

	static final String BOTTOM = "bottom"; //$NON-NLS-1$

	static final String LEFT = "left"; //$NON-NLS-1$

	static final String RIGHT = "right"; //$NON-NLS-1$

	/**
	 * @param edge
	 * @param frame
	 * @return true if there is a match
	 */
	public static boolean matchFrame(String edge, String frame) {
		if ("above".equalsIgnoreCase(frame)) { //$NON-NLS-1$
			return TOP.equalsIgnoreCase(edge);
		} else if ("below".equalsIgnoreCase(frame)) { //$NON-NLS-1$
			return BOTTOM.equalsIgnoreCase(edge);
		} else if ("hsides".equalsIgnoreCase(frame)) { //$NON-NLS-1$
			return TOP.equalsIgnoreCase(edge) || BOTTOM.equalsIgnoreCase(edge);
		} else if ("vsides".equalsIgnoreCase(frame)) { //$NON-NLS-1$
			return LEFT.equalsIgnoreCase(edge) || RIGHT.equalsIgnoreCase(edge);
		} else if ("lhs".equalsIgnoreCase(frame)) { //$NON-NLS-1$
			return LEFT.equalsIgnoreCase(edge);
		} else if ("rhs".equalsIgnoreCase(frame)) { //$NON-NLS-1$
			return RIGHT.equalsIgnoreCase(edge);
		} else if ("box".equalsIgnoreCase(frame) //$NON-NLS-1$
				|| "border".equalsIgnoreCase(frame)) { //$NON-NLS-1$
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param edge 
	 * @param rules
	 * @return true if rules match
	 */
	public static boolean matchRules(String edge, String rules) {
		// TODO: "groups" not supported.

		if ("rows".equalsIgnoreCase(rules)) { //$NON-NLS-1$
			return TOP.equalsIgnoreCase(edge) || BOTTOM.equalsIgnoreCase(edge);
		} else if ("cols".equalsIgnoreCase(rules)) { //$NON-NLS-1$
			return LEFT.equalsIgnoreCase(edge) || RIGHT.equalsIgnoreCase(edge);
		} else if ("all".equalsIgnoreCase(rules)) { //$NON-NLS-1$
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @param element
	 * @return true if cell is empty
	 * see http://www.w3.org/TR/CSS21/tables.html#empty-cells
	 */
	public static boolean isEmptyCell(Element element) {
		NodeList children = element.getChildNodes();
		if (children.getLength() == 0) {
			return true;
		}
		// we only do simple checking here. When the element has one child
		// and is text and is whitespace only, then we also treat as empty cell
		if (children.getLength() == 1) {
			Node child = children.item(0);
			if (child instanceof Text) {
				String text = child.getNodeValue();
				if (HTMLUtil.isHTMLWhitespaceString(text)) {
					return true;
				}
			}
		}

		return false;
	}
}
