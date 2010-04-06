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

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWTException;
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
	 * @return the new image
	 */
	public static Image initializeImage(Element element, String attrName) {
		String url = getResolvedURL(element, attrName);
		if (url == null) {
			return null;
		}
		//Bug 307801 - [WPE] WPE does not render Images with URL encoding in their path in the preview pane
		try {
			url = URLDecoder.decode(url, "UTF-8"); //$NON-NLS-1$
		} catch(UnsupportedEncodingException uee) {
			//we tried to decode using recommended encoding, we failed
		} catch(IllegalArgumentException iae) {
			//we tried to decode using recommended encoding, we failed
		}
		Image img = null;
		int colonIndex = url.indexOf(":"); //$NON-NLS-1$
		int slashIndex = url.indexOf("/"); //$NON-NLS-1$
		if (colonIndex != -1 && (slashIndex != -1 && colonIndex < slashIndex)) {
			//the url seems to have a protocol, so try to load it as a URL
			try {
				URL urlObj = new URL(url);
				ImageDescriptor imgDesc = ImageDescriptor.createFromURL(urlObj);
				img = imgDesc.createImage(false);
			} catch(MalformedURLException mfe) {
				//attempt to load as a file
				try {
					img = new Image(null, url);
				} catch(SWTException se) {
					//img remains null on return
				}
			} catch(SWTException se) {
				//img remains null on return
			}
		} else {
			//no protocol, so load it as a file
			try {
				img = new Image(null, url);
			} catch(SWTException se) {
				//img remains null on return
			}
		}
		return img;
	}
}
