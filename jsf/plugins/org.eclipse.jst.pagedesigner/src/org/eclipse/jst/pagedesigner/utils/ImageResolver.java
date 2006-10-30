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
package org.eclipse.jst.pagedesigner.utils;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.sse.core.internal.util.URIResolver;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class ImageResolver {
	/**
	 * 
	 * @param element
	 * @param attrName
	 * @return
	 */
	static String getResolvedURL(Element element, String attrName) {
		URIResolver resolver = null;
		if (element instanceof IDOMNode) {
			resolver = ((IDOMNode) element).getModel().getResolver();
		}
		if (null == resolver) {
			return null;
		}
		String src = DOMUtil.getAttributeIgnoreCase(element, attrName);
		if (src != null && src.length() > 0) {
			return resolver.getLocationByURI(src);
		}
		return null;
	}

	/**
	 * given the element and an attribute name identifying the src of the image,
	 * create a image.
	 * 
	 * @param element
	 * @param attrName
	 * @return
	 */
	public static Image initializeImage(Element element, String attrName) {
		try {
			String url = getResolvedURL(element, attrName);
			if (url == null)
				return null;
			return new Image(null, url);
		} catch (Throwable ex) {
			// skip exception
			// ex.printStackTrace();
			return null;
		}
	}
}
