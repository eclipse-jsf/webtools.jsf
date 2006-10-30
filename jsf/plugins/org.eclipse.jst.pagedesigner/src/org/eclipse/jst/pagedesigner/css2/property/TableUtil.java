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
	static final String TOP = "top";

	static final String BOTTOM = "bottom";

	static final String LEFT = "left";

	static final String RIGHT = "right";

	public static boolean matchFrame(String edge, String frame) {
		if ("above".equalsIgnoreCase(frame)) {
			return TOP.equalsIgnoreCase(edge);
		} else if ("below".equalsIgnoreCase(frame)) {
			return BOTTOM.equalsIgnoreCase(edge);
		} else if ("hsides".equalsIgnoreCase(frame)) {
			return TOP.equalsIgnoreCase(edge) || BOTTOM.equalsIgnoreCase(edge);
		} else if ("vsides".equalsIgnoreCase(frame)) {
			return LEFT.equalsIgnoreCase(edge) || RIGHT.equalsIgnoreCase(edge);
		} else if ("lhs".equalsIgnoreCase(frame)) {
			return LEFT.equalsIgnoreCase(edge);
		} else if ("rhs".equalsIgnoreCase(frame)) {
			return RIGHT.equalsIgnoreCase(edge);
		} else if ("box".equalsIgnoreCase(frame)
				|| "border".equalsIgnoreCase(frame)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param string
	 * @param rules
	 * @return
	 */
	public static boolean matchRules(String edge, String rules) {
		// TODO: "groups" not supported.

		if ("rows".equalsIgnoreCase(rules)) {
			return TOP.equalsIgnoreCase(edge) || BOTTOM.equalsIgnoreCase(edge);
		} else if ("cols".equalsIgnoreCase(rules)) {
			return LEFT.equalsIgnoreCase(edge) || RIGHT.equalsIgnoreCase(edge);
		} else if ("all".equalsIgnoreCase(rules)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @param element
	 * @return
	 * @see http://www.w3.org/TR/CSS21/tables.html#empty-cells
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
