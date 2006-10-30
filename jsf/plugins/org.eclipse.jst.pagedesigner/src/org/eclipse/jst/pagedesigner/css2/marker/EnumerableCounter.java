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
package org.eclipse.jst.pagedesigner.css2.marker;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mengbo
 */
public abstract class EnumerableCounter implements ICounter {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.list.ICounter#getNextObject(org.w3c.dom.Element,
	 *      java.lang.String)
	 */
	public Object getNextObject(Element element, int type) {
		int index = calculateIndex(element);
		return getNextString(index, type);
	}

	public int calculateIndex(Element element) {
		String tag = element.getLocalName();
		Node parent = element.getParentNode();
		NodeList children = parent.getChildNodes();
		int index = 1;
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child == element) {
				break;
			}
			if (child.getLocalName().equalsIgnoreCase(tag)) {
				index++;
			}
		}
		return index;
	}

	public abstract String getNextString(int index, int type);
}
