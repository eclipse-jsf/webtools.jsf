/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.dtmanager.internal.provisional;

import org.eclipse.jst.pagedesigner.utils.CMUtil;
import org.w3c.dom.Element;

/**
 * DTManager is the top-level entry point for design-time (DT) services, such
 * as tag converters and other design-time information.
 * 
 * @author Ian Trimble - Oracle
 */
public class DTManager {

	private IDTInfoFactory dtInfoFactory;

	/**
	 * Gets an IDTInfo instance for the specified Element.
	 * 
	 * @param element Element instance for which to locate and return IDTInfo
	 * instance.
	 * @return An IDTInfo instance for the specified Element.
	 */
	public IDTInfo getDTInfo(Element element) {
		IDTInfo dtInfo = null;
		String nsURI = CMUtil.getElementNamespaceURI(element);
		IDTInfoFactory dtInfoFactory = getDTInfoFactory(nsURI);
		if (dtInfoFactory != null) {
			dtInfo = dtInfoFactory.getDTInfo(element);
		}
		return dtInfo;
	}

	/**
	 * Gets an IDTInfoFactory instance for the specified namespace URI.
	 * 
	 * @param nsURI Namespace URI.
	 * @return An IDTInfoFactory instance for the specified namespace URI.
	 */
	protected IDTInfoFactory getDTInfoFactory(String nsURI) {
		if (dtInfoFactory == null) {
			dtInfoFactory = new DefaultDTInfoFactory();
		}
		return dtInfoFactory;
	}

}
