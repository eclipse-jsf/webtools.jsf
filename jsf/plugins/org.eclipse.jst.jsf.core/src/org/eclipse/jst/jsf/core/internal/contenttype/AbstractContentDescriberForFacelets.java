/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Oracle - copy and modify ContentDescriberForHTML
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.contenttype;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescriber;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;
import org.eclipse.wst.html.core.internal.contenttype.HTMLResourceEncodingDetector;
import org.eclipse.wst.sse.core.internal.encoding.EncodingMemento;
import org.eclipse.wst.sse.core.internal.encoding.IContentDescriptionExtended;
import org.eclipse.wst.sse.core.internal.encoding.IResourceCharsetDetector;
import org.eclipse.wst.sse.core.utils.StringUtils;
import org.eclipse.wst.xml.core.internal.parser.XMLTokenizer;
import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;

/**
 * Copied from ContentDescriberForHTML, since that class is final but we need to add description
 * criteria.
 */

public abstract class AbstractContentDescriberForFacelets implements ITextContentDescriber {

	/**
	 * Get the patterns to match against value of namespace attributes (of the document element).
	 * @return Array of Pattern instances to match against value of namespace attributes (of the
	 * document element).
	 */
	protected abstract Pattern[] getNSValuePatterns();

	final private static QualifiedName[] SUPPORTED_OPTIONS = {IContentDescription.CHARSET, IContentDescription.BYTE_ORDER_MARK, IContentDescriptionExtended.DETECTED_CHARSET, IContentDescriptionExtended.UNSUPPORTED_CHARSET, IContentDescriptionExtended.APPROPRIATE_DEFAULT};

	public int describe(InputStream contents, IContentDescription description) throws IOException {
		int result = IContentDescriber.INDETERMINATE;
		byte[] bom = null;
		if (description != null) {
			calculateSupportedOptions(contents, description);
			Object value = description.getProperty(IContentDescription.BYTE_ORDER_MARK);
			if (value instanceof byte[]) {
				bom = (byte[])value;
			}
		} else {
			contents.reset();
			bom = getByteOrderMark(contents);
		}
		Object value =
				(description != null ? description.getProperty(IContentDescription.CHARSET) : null);
		String charsetName = null;
		if (value != null) {
			charsetName = value.toString();
		} else {
			if (Arrays.equals(IContentDescription.BOM_UTF_16BE, bom)) {
				charsetName = "UTF-16BE"; //$NON-NLS-1$
			} else if (Arrays.equals(IContentDescription.BOM_UTF_16LE, bom)) {
				charsetName = "UTF-16LE"; //$NON-NLS-1$
			} else {
				charsetName = "UTF-8"; //$NON-NLS-1$
			}
		}
		contents.reset();
		result = checkCriteria(new InputStreamReader(contents, Charset.forName(charsetName)));
		return result;
	}

	public int describe(Reader contents, IContentDescription description) throws IOException {
		int result = IContentDescriber.INDETERMINATE;
		if (description != null) {
			calculateSupportedOptions(contents, description);
		}
		contents.reset();
		result = checkCriteria(contents);
		return result;
	}

	public QualifiedName[] getSupportedOptions() {
		return SUPPORTED_OPTIONS;
	}

	private void calculateSupportedOptions(InputStream contents, IContentDescription description) throws IOException {
		if (isRelevent(description)) {
			IResourceCharsetDetector detector = getDetector();
			detector.set(contents);
			handleCalculations(description, detector);
		}
	}

	private void calculateSupportedOptions(Reader contents, IContentDescription description) throws IOException {
		if (isRelevent(description)) {
			IResourceCharsetDetector detector = getDetector();
			detector.set(contents);
			handleCalculations(description, detector);
		}
	}

	private IResourceCharsetDetector getDetector() {
		return new HTMLResourceEncodingDetector();
	}

	private void handleCalculations(IContentDescription description, IResourceCharsetDetector detector) throws IOException {
		EncodingMemento encodingMemento = ((HTMLResourceEncodingDetector) detector).getEncodingMemento();
		Object detectedByteOrderMark = encodingMemento.getUnicodeBOM();
		if (detectedByteOrderMark != null) {
			Object existingByteOrderMark = description.getProperty(IContentDescription.BYTE_ORDER_MARK);
			if (!detectedByteOrderMark.equals(existingByteOrderMark)) {
				description.setProperty(IContentDescription.BYTE_ORDER_MARK, detectedByteOrderMark);
			}
		}

		if (!encodingMemento.isValid()) {
			description.setProperty(IContentDescriptionExtended.UNSUPPORTED_CHARSET, encodingMemento.getInvalidEncoding());
			description.setProperty(IContentDescriptionExtended.APPROPRIATE_DEFAULT, encodingMemento.getAppropriateDefault());
		}

		Object detectedCharset = encodingMemento.getDetectedCharsetName();
		Object javaCharset = encodingMemento.getJavaCharsetName();

		if (detectedCharset != null) {
			description.setProperty(IContentDescriptionExtended.DETECTED_CHARSET, detectedCharset);
		}

		if (javaCharset != null) {
			Object existingCharset = description.getProperty(IContentDescription.CHARSET);
			if (!javaCharset.equals(existingCharset)) {
				Object defaultCharset = detector.getSpecDefaultEncoding();
				if (defaultCharset != null) {
					if (!defaultCharset.equals(javaCharset)) {
						description.setProperty(IContentDescription.CHARSET, javaCharset);
					}
				} else {
					description.setProperty(IContentDescription.CHARSET, javaCharset);
				}
			}
		}

	}

	private boolean isRelevent(IContentDescription description) {
		boolean result = false;
		if (description != null) {
			if (description.isRequested(IContentDescription.BYTE_ORDER_MARK)) {
				result = true;
			} else if (description.isRequested(IContentDescription.CHARSET)) {
				result = true;
			} else if (description.isRequested(IContentDescriptionExtended.APPROPRIATE_DEFAULT)) {
				result = true;
			} else if (description.isRequested(IContentDescriptionExtended.DETECTED_CHARSET)) {
				result = true;
			} else if (description.isRequested(IContentDescriptionExtended.UNSUPPORTED_CHARSET)) {
				result = true;
			}
		}
		return result;
	}

	private int checkCriteria(Reader contents) throws IOException {
		final Pattern[] nsValuePatterns = getNSValuePatterns();
		if (nsValuePatterns == null || nsValuePatterns.length == 0) {
			return INVALID;
		}
		final Pattern nsNamePattern = Pattern.compile("xmlns:.*"); //$NON-NLS-1$

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
				for (int i = 0; i < nsValuePatterns.length; i++) {
					if (nsNamePattern.matcher(attributeName).matches()) {
						if (nsValuePatterns[i].matcher(attributeValue).matches()) {
							return VALID;
						}
					}
				}
			}
			token = tokenizer.primGetNextToken();
		}
		return INVALID;
	}

	private static byte[] getByteOrderMark(InputStream contents) throws IOException {
		int first = contents.read();
		if (first == 0xEF) {
			// look for the UTF-8 Byte Order Mark (BOM)
			int second = contents.read();
			int third = contents.read();
			if (second == 0xBB && third == 0xBF)
				return IContentDescription.BOM_UTF_8;
		}
		else if (first == 0xFE) {
			// look for the UTF-16 BOM
			if (contents.read() == 0xFF)
				return IContentDescription.BOM_UTF_16BE;
		}
		else if (first == 0xFF) {
			if (contents.read() == 0xFE)
				return IContentDescription.BOM_UTF_16LE;
		}
		return null;
	}

}
