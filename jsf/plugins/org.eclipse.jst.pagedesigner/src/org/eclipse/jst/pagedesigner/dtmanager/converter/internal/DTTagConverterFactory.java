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
package org.eclipse.jst.pagedesigner.dtmanager.converter.internal;

import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.dtmanager.DTManager;
import org.eclipse.jst.pagedesigner.dtmanager.IDTInfo;
import org.w3c.dom.Element;

/**
 * Produces DTTagConverter instances.
 * 
 * @author Ian Trimble - Oracle
 */
public class DTTagConverterFactory implements IConverterFactory {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.IConverterFactory#createConverter(org.w3c.dom.Element, int)
	 */
	public ITagConverter createConverter(Element element, int mode) {
		ITagConverter tagConverter = null;
		IDTInfo dtInfo = DTManager.getInstance().getDTInfo(element);
		if (dtInfo != null) {
			tagConverter = new DTTagConverter(element);
			tagConverter.setMode(mode);
		}
		return tagConverter;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.IConverterFactory#getSupportedURI()
	 */
	public String getSupportedURI() {
		return null;
	}

}
