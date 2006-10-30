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
package org.eclipse.jst.pagedesigner.meta.internal;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jst.pagedesigner.meta.ICMRegistry;
import org.eclipse.jst.pagedesigner.meta.IElementDescriptor;
import org.xml.sax.SAXException;

/**
 * @author mengbo
 * @version 1.5
 */
public class SimpleCMRegistry implements ICMRegistry {
	String _uri;

	Map _map = new HashMap();

	/**
	 * @throws IOException
	 * @throws SAXException
	 * @throws FactoryConfigurationError
	 * @throws ParserConfigurationException
	 * 
	 */
	public SimpleCMRegistry(String uri, URL cmFileUrl)
			throws ParserConfigurationException, FactoryConfigurationError,
			SAXException, IOException {
		this._uri = uri;
		ElementDescReader reader = new ElementDescReader(cmFileUrl);
		reader.readElements(_map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.ICMRegistry#getSupportedURI()
	 */
	public String getSupportedURI() {
		return _uri;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.ICMRegistry#getElementDescriptor(java.lang.String,
	 *      java.lang.String)
	 */
	public IElementDescriptor getElementDescriptor(String uri, String tagname) {
		if (uri.equals(_uri)) {
			return (IElementDescriptor) _map.get(tagname);
		}
		return null;
	}

}
