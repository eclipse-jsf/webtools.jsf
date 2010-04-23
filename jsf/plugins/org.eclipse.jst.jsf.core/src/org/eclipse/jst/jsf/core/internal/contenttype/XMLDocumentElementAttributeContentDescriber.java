/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.contenttype;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.wst.sse.core.utils.StringUtils;
import org.eclipse.wst.xml.core.internal.parser.XMLTokenizer;
import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;

/**
 * Matches the first tag of the content using attribute/value regex pairs.
 * <br/>
 * <br/>
 * Example:<br/>
 * &lt;describer class="XMLDocumentElementAttributeContentDescriber"&gt;<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;parameter name="xmlns:ns1" value="http://example.com/ns1" /&gt;<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;parameter name="xmlns:.*" value="http://example.com/ns2" /&gt;<br/>
 * &lt;/describer&gt;
 */
public final class XMLDocumentElementAttributeContentDescriber
	implements ITextContentDescriber, IExecutableExtension {

	private static class AttributeValuePatternPair {
		Pattern attribute;
		Pattern value;

		public AttributeValuePatternPair(Pattern attribute, Pattern value) {
			super();
			this.attribute = attribute;
			this.value = value;
		}

		public String toString() {
			return attribute + " : " + value; //$NON-NLS-1$
		}
	}

	private static final String ATTRIBUTE_NAME = "name"; //$NON-NLS-1$
	private static final String ATTRIBUTE_VALUE = "value"; //$NON-NLS-1$

	// TODO: support all of the options of the standard HTML content describer?
	private final static QualifiedName[] SUPPORTED_OPTIONS = {IContentDescription.BYTE_ORDER_MARK};

	/*
	 * Reads from from the stream. Note that the stream will not be repositioned when the method
	 * returns. Copied from org.eclipse.core.internal.content.Util
	 */
	private static byte[] getByteOrderMark(InputStream input) throws IOException {
		int first = input.read();
		if (first == 0xEF) {
			// look for the UTF-8 Byte Order Mark (BOM)
			int second = input.read();
			int third = input.read();
			if (second == 0xBB && third == 0xBF)
				return IContentDescription.BOM_UTF_8;
		}
		else if (first == 0xFE) {
			// look for the UTF-16 BOM
			if (input.read() == 0xFF)
				return IContentDescription.BOM_UTF_16BE;
		}
		else if (first == 0xFF) {
			if (input.read() == 0xFE)
				return IContentDescription.BOM_UTF_16LE;
		}
		return null;
	}

	private AttributeValuePatternPair[] fPairs = null;

	/**
	 * Create an instance.
	 */
	public XMLDocumentElementAttributeContentDescriber() {
		super();
	}

	private int checkCriteria(Reader contents) throws IOException {
		if (fPairs == null || fPairs.length == 0)
			return INVALID;

		String attributeName = null;
		String attributeValue = null;

		XMLTokenizer tokenizer = new XMLTokenizer(contents);

		String token = tokenizer.primGetNextToken();
		while (token != null &&
				!DOMRegionContext.XML_TAG_CLOSE.equals(token) &&
				!DOMRegionContext.XML_EMPTY_TAG_CLOSE.equals(token)) {

			if (DOMRegionContext.XML_TAG_ATTRIBUTE_NAME.equals(token)) {
				attributeName = tokenizer.yytext();
			}
			else if (DOMRegionContext.XML_TAG_ATTRIBUTE_VALUE.equals(token)) {
				attributeValue = StringUtils.strip(tokenizer.yytext());
				for (int i = 0; i < fPairs.length; i++) {
					boolean nameMatched = fPairs[i].attribute.matcher(attributeName).matches();
					if(nameMatched) {
						boolean valueMatched = fPairs[i].value.matcher(attributeValue).matches();
						if(valueMatched) {
							return VALID;
						}
					}
				}
			}
			token = tokenizer.primGetNextToken();
		}
		return INVALID;
	}

	public int describe(InputStream contents, IContentDescription description) throws IOException {
		contents.reset();
		byte[] bom = getByteOrderMark(contents);
		if (bom != null && description != null)
			description.setProperty(IContentDescription.BYTE_ORDER_MARK, bom);

		Object value =
			description != null ? description.getProperty(IContentDescription.CHARSET) : null;
		String charsetName = null;
		if (value != null) {
			charsetName = value.toString();
		}
		else {
			if (Arrays.equals(IContentDescription.BOM_UTF_16BE, bom)) {
				charsetName = "UTF-16BE"; //$NON-NLS-1$
			}
			else if (Arrays.equals(IContentDescription.BOM_UTF_16LE, bom)) {
				charsetName = "UTF-16LE"; //$NON-NLS-1$
			}
			else {
				charsetName = "UTF-8"; //$NON-NLS-1$
			}
		}
		contents.reset();
		// Check to see if we matched our criteria.
		return checkCriteria(new InputStreamReader(contents, Charset.forName(charsetName)));
	}

	public int describe(Reader contents, IContentDescription description) throws IOException {
		contents.reset();
		// Check to see if we matched our criteria.
		return checkCriteria(contents);
	}

	public QualifiedName[] getSupportedOptions() {
		return SUPPORTED_OPTIONS;
	}

	@SuppressWarnings("unchecked")
	public void setInitializationData(
			final IConfigurationElement config,
			final String propertyName,
			final Object data) throws CoreException {

		if (data instanceof String) {
			// TODO: why is this here? (imported verbatim from bug attachment)
		}
		else if (data instanceof Hashtable) {
			IConfigurationElement describerElement = config.getChildren("describer")[0]; //$NON-NLS-1$
			IConfigurationElement[] params = describerElement.getChildren("parameter"); //$NON-NLS-1$
			List<AttributeValuePatternPair> pairs = new ArrayList<AttributeValuePatternPair>();
			for (int i = 0; i < params.length; i++) {
				String rawName = params[i].getAttribute(ATTRIBUTE_NAME);
				String rawValue = params[i].getAttribute(ATTRIBUTE_VALUE);
				if (rawName == null || rawValue == null)
					continue;
				Pattern attributeName = Pattern.compile(rawName);
				Pattern attributeValue = Pattern.compile(rawValue);
				pairs.add(new AttributeValuePatternPair(attributeName, attributeValue));
			}
			fPairs = pairs.toArray(new AttributeValuePatternPair[pairs.size()]);
		}

		if (fPairs == null || fPairs.length == 0) {
			throw new CoreException(
					new Status(IStatus.ERROR, JSFCorePlugin.PLUGIN_ID, 0,
							"Attributes not specified", null)); //$NON-NLS-1$
		}
	}
}