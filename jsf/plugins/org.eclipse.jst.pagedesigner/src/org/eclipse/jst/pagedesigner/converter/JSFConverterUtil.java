/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.converter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/**
 * @author mengbo
 * @version 1.5
 */
public class JSFConverterUtil {
	private static List NamedBooleanList = Arrays.asList(new String[] {
			"disabled", "readonly", "ismap" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/**
	 * 
	 * @param source
	 * @param dest
	 * @param ignore
	 */
	public static void copyAllAttributes(Element source, Element dest,
			Set ignore) {
		NamedNodeMap attrs = source.getAttributes();
		for (int i = 0, size = attrs.getLength(); i < size; i++) {
			Attr attr = (Attr) attrs.item(i);
			if (ignore == null || !ignore.contains(attr.getName())) {
				if (NamedBooleanList.contains(attr.getName())
						&& "false".equalsIgnoreCase(attr.getValue())) { //$NON-NLS-1$
					continue;
				}
				dest.setAttribute(attr.getName(), attr.getValue());
			}
		}
	}

	/**
	 * copy a single attribute (if exist)
	 * 
	 * @param source
	 * @param srcattr
	 * @param dest
	 * @param destattr
	 */
	public static void copyAttribute(Element source, String srcattr,
			Element dest, String destattr) {
		Attr attr = source.getAttributeNode(srcattr);
		if (attr != null) {
			dest.setAttribute(destattr, attr.getValue());
		}
	}
}
