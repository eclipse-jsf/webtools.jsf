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
package org.eclipse.jst.pagedesigner.css2.style;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.sse.core.internal.util.URIResolver;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
public class ImageStyleHelper {
	public static Image loadImage(String url, Element source) {
		if (source instanceof IDOMElement) {
			IDOMModel model = ((IDOMElement) source).getModel();
			URIResolver resolver = model.getResolver();
			if (resolver != null)
				url = resolver.getLocationByURI(url);
		}
		if (url != null) {
			return new Image(null, url);
		}
		return null;
	}
}
