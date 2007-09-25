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

/**
 * @author mengbo
 */

import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.w3c.dom.Document;

/**
 * 
 *
 */
public class XMLUtil {
	private static Logger _log = PDPlugin.getLogger(XMLUtil.class);

	/**
	 * Returns a DocumentBuilder capable of creating a DOM Document from input.
	 * 
	 * @return a new instance of a document builder or null if an exception
	 * occurs on creation
	 */
	public synchronized static DocumentBuilder getDocumentBuilder() {
		DocumentBuilder result = null;
		try {
			result = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// "Error in create documentBuilder"
			_log.info("XMLUtil.Error.0", e); //$NON-NLS-1$
		}
		return result;
	}

	/**
	 * Transforms a DOM document into a lightly-formatted UTF-8 represntation
	 * and outputs it to an outputstream
	 * 
	 * @param document
	 * @param ostream
	 * @throws IOException
	 */
	public static void serialize(Document document, OutputStream ostream)
			throws IOException {
		Source domSource = new DOMSource(document);
		try {
			Transformer serializer = TransformerFactory.newInstance()
					.newTransformer();
			try {
				serializer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
				serializer.setOutputProperty(
						"{http://xml.apache.org/xslt}indent-amount", "4"); //$NON-NLS-1$ //$NON-NLS-2$
				serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); //$NON-NLS-1$
			} catch (IllegalArgumentException e) {
				// "Error in object persistance:"
				_log.info("XMLUtil.Error.2", e); //$NON-NLS-1$
			}
			serializer.transform(domSource, new StreamResult(ostream));
		} catch (TransformerConfigurationException e) {
			// "Error in object persistance:"
			_log.info("XMLUtil.Error.2", e); //$NON-NLS-1$
			throw new IOException(e.getMessage());
		} catch (TransformerFactoryConfigurationError e) {
			// "Error in object persistance:"
			_log.info("XMLUtil.Error.2", e); //$NON-NLS-1$
			throw new IOException(e.getMessage());
		} catch (TransformerException e) {
			// "Error in object persistance:"
			_log.info("XMLUtil.Error.2", e); //$NON-NLS-1$
			throw new IOException(e.getMessage());
		}
	}
}
