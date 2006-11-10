/*******************************************************************************
 * Copyright (c) 2002, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Jens Lukowski/Innoopract - initial renaming/restructuring
 * 	 Gerry Kessler/Oracle - code borrowed and repurposed for JSF subproject
 *
 *******************************************************************************/
package org.eclipse.jst.jsf.contentmodel.annotation.internal;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationSourceFileLocator;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.ICMAnnotationFileParser;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Default implementation of ICMAnnotationFileParser that will parse xml files conforming to 
 * http://org.eclipse.jst.jsf.contentmodel.annotations/grammarAnnotationSchema.   
 * 
 * @author Gerry Kessler - Oracle
 * 
 * @see http://org.eclipse.jst.jsf.contentmodel.annotations/grammarAnnotationSchema
 */
public class CMAnnotationFileParser implements ICMAnnotationFileParser {
	
	public static final String TAG_ID_ANNOTATIONS = "grammar-annotations"; //$NON-NLS-1$
	public static final String TAG_ID_ELEMENT = "cm-element"; //$NON-NLS-1$
	public static final String TAG_ID_ATTRIBUTE = "cm-attribute"; //$NON-NLS-1$
	public static final String TAG_ID_PROPERTY = "property"; //$NON-NLS-1$
	public static final String TAG_ID_PROPERTY_VALUE = "value"; //$NON-NLS-1$

	/**
	 * This method is called to parse an annotation file and store the contents
	 * into an annotationMap via the advisor
	 */
	private void parse(ICMAnnotationAdvisor advisor, InputStream input) throws Exception {
		ClassLoader prevClassLoader = Thread.currentThread().getContextClassLoader();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			SAXParser parser = factory.newSAXParser();
			parser.parse(new InputSource(input), new CMAnnotationMapContentHandler(advisor));
		} finally {
			Thread.currentThread().setContextClassLoader(prevClassLoader);
			if (input != null){
				try {
					input.close();
				} catch (IOException e) {
                    JSFCommonPlugin.log(e, "error closing annotation file");
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.ICMAnnotationFileParser#parse(org.eclipse.jst.jsf.contentmodel.annotation.internal.ICMAnnotationAdvisor, org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationSourceFileLocator)
	 */
	public void parse(ICMAnnotationAdvisor advisor, CMAnnotationSourceFileLocator locator)
			throws Exception {
		
		InputStream inputStream = locator.getAnnotationSourceInputStream();
		parse(advisor, inputStream);
	}

	/**
	 * Default content handler for annotation files following grammar-annotations schema
	 * 
	 * @author Gerry Kessler - Oracle
	 */
	protected class CMAnnotationMapContentHandler extends DefaultHandler {
		private ICMAnnotationAdvisor advisor;
		private String elementName;
		private String attributeName;
		private String currentPropertyName;
		private String currentPropertyValue;
		private StringBuffer propertyValueBuffer;

		private boolean isInCMAttribute = false;

		private boolean doCaptureNodeText;

		/**
		 * Constructor
		 * @param advisor
		 */
		public CMAnnotationMapContentHandler(ICMAnnotationAdvisor advisor) {
			this.advisor = advisor;
		}

		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			if (localName.equals(TAG_ID_ANNOTATIONS)) {
				int attributesLength = attributes.getLength();
				for (int i = 0; i < attributesLength; i++) {
					String attributeName_ = attributes.getLocalName(i);
					String attributeValue = attributes.getValue(i);
					if (attributeName_.equals("caseSensitive")) { //$NON-NLS-1$					
						if (attributeValue.trim().equals("false")) {//$NON-NLS-1$
							advisor.setCaseSensitive(false);
						}
					}
				}
			} else if (localName.equals(TAG_ID_ELEMENT)) {
				isInCMAttribute = false;
				elementName = attributes.getValue("name"); //$NON-NLS-1$
			} else if (localName.equals(TAG_ID_ATTRIBUTE)) {
				isInCMAttribute = true;
				attributeName = attributes.getValue("name"); //$NON-NLS-1$
			} else if (localName.equals(TAG_ID_PROPERTY)) {
				currentPropertyName = attributes.getValue("name"); //$NON-NLS-1$					
			} else if (localName.equals(TAG_ID_PROPERTY_VALUE)) {
				propertyValueBuffer = new StringBuffer();
				doCaptureNodeText = true;
			}
		}

		public void endElement(String uri, String localName, String qName) throws SAXException {
			if (currentPropertyName != null && localName.equals(TAG_ID_PROPERTY_VALUE)) {
				doCaptureNodeText = false;
				currentPropertyValue = propertyValueBuffer.toString();
				if (currentPropertyValue != null) {					
					if (isInCMAttribute)
						advisor.addAttributeAnnotation(elementName, attributeName,
								currentPropertyName, currentPropertyValue);
					else
						advisor.addElementAnnotation(elementName, currentPropertyName,
								currentPropertyValue);
				}
			}

			if (localName.equals(TAG_ID_ELEMENT)) {
				elementName = null;
			} else if (localName.equals(TAG_ID_ATTRIBUTE)) {
				isInCMAttribute = false;
				attributeName = null;
			} else if (localName.equals(TAG_ID_PROPERTY)) {
				currentPropertyName = null;
			} else if (localName.equals(TAG_ID_PROPERTY_VALUE)) {
				currentPropertyValue = null;
			}
		}

		public void characters(char[] ch, int start, int length) {
			if (doCaptureNodeText)
				propertyValueBuffer.append(ch, start, length);
		}

	}
}
