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
package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.jsf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.ITransformOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.AbstractTransformOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.AppendChildElementOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.CopyAllAttributesOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.CreateAttributeOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.CreateElementOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.RenameAttributeOperation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PanelGridOperation extends AbstractTransformOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		Element tableElement = null;
		//create table element, copy all attributes, rename "styleClass" attribute to "class"
		tableElement = createElement("table");
		new CopyAllAttributesOperation().transform(srcElement, tableElement);
		new RenameAttributeOperation("styleClass", "class").transform(srcElement, tableElement);
		//get value of "columns" attribute
		int columns;
		try {
			columns = Integer.parseInt(srcElement.getAttribute("columns"));
		} catch(NumberFormatException nfe) {
			columns = 1;
		}
		if (columns < 1) {
			columns = 1;
		}
		//check for "header" facet and render appropriately
		Element headerFacetElement = getChildFacetByName(srcElement, "header");
		if (headerFacetElement != null) {
			Element tHeadElement = appendChildElement("thead", tableElement);
			Element trElement = appendChildElement("tr", tHeadElement);
			Element thElement = appendChildElement("th", trElement);
			String headerClass = srcElement.getAttribute("headerClass");
			if (headerClass != null && headerClass.length() > 0) {
				new CreateAttributeOperation("class", headerClass).transform(srcElement, thElement);
			}
			new CreateAttributeOperation("colspan", String.valueOf(columns)).transform(srcElement, thElement);
			tagConverterContext.addChild(headerFacetElement, new ConvertPosition(thElement, 0));
		}
		//check for "footer" facet and render appropriately
		Element footerFacetElement = getChildFacetByName(srcElement, "footer");
		if (footerFacetElement != null) {
			Element tFootElement = appendChildElement("tfoot", tableElement);
			Element trElement = appendChildElement("tr", tFootElement);
			Element tdElement = appendChildElement("td", trElement);
			String headerClass = srcElement.getAttribute("footerClass");
			if (headerClass != null && headerClass.length() > 0) {
				new CreateAttributeOperation("class", headerClass).transform(srcElement, tdElement);
			}
			new CreateAttributeOperation("colspan", String.valueOf(columns)).transform(srcElement, tdElement);
			tagConverterContext.addChild(footerFacetElement, new ConvertPosition(tdElement, 0));
		}
		//get rowClasses and columnClasses for subsequent processing
        List rowClasses = new ArrayList();
        String rowClassesAttribute = srcElement.getAttribute("rowClasses");
        if (rowClassesAttribute != null && rowClassesAttribute.length() > 0) {
            StringTokenizer tokenizer = new StringTokenizer(rowClassesAttribute, ", ");
            while (tokenizer.hasMoreTokens()) {
                rowClasses.add(tokenizer.nextToken());
            }
        }
        List columnClasses = new ArrayList();
        String columnClassAttribute = srcElement.getAttribute("columnClasses");
        if (columnClassAttribute != null) {
            StringTokenizer tokenizer = new StringTokenizer(columnClassAttribute, ", ");
            while (tokenizer.hasMoreTokens()) {
                columnClasses.add(tokenizer.nextToken());
            }
        }
		//render children using appropriate number of columns and appropriate classes
        Element tBodyElement = appendChildElement("tbody", tableElement);
        List childElements = getChildElementsSkipFacets(srcElement);
        Element trElement = null;
        int nextRow = 0;
        int curIndex = 0;
        Iterator itChildElements = childElements.iterator();
        while (itChildElements.hasNext()) {
        	int columnIndex = curIndex % columns;
        	if (columnIndex == 0) {
        		trElement = appendChildElement("tr", tBodyElement);
        		if (!rowClasses.isEmpty()) {
        			new CreateAttributeOperation("class", (String)rowClasses.get(nextRow)).transform(srcElement, trElement);
        			nextRow = (nextRow + 1) % rowClasses.size();
        		}
        	}
        	Element tdElement = appendChildElement("td", trElement);
        	if (columnIndex < columnClasses.size()) {
        		new CreateAttributeOperation("class", (String)columnClasses.get(columnIndex)).transform(srcElement, tdElement);
        	}
        	tagConverterContext.addChild((Element)itChildElements.next(), new ConvertPosition(tdElement, 0));
        	curIndex++;
        }
        return tableElement;
	}

	protected Element createElement(String tagName) {
		ITransformOperation operation = new CreateElementOperation(tagName);
		operation.setTagConverterContext(tagConverterContext);
		return operation.transform(null, null);
	}

	protected Element appendChildElement(String tagName, Element parentElement) {
		ITransformOperation operation = new AppendChildElementOperation(tagName);
		operation.setTagConverterContext(tagConverterContext);
		return operation.transform(null, parentElement);
	}

	protected Element getChildFacetByName(Element srcElement, String facetName) {
		Element element = null;
		NodeList childNodes = srcElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getLocalName().equalsIgnoreCase("facet")) {
				Element facetElement = (Element)childNode;
				String facetElementName = facetElement.getAttribute("name");
				if (facetElementName != null && facetElementName.equals(facetName)) {
					element = facetElement;
					break;
				}
			}
		}
		return element;
	}

	protected List getChildElementsSkipFacets(Element srcElement) {
		List childElementsList = new ArrayList();
		NodeList childNodes = srcElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				if (!childNode.getLocalName().equals("facet")) {
					childElementsList.add(childNode);
				}
			}
		}
		return childElementsList;
	}

}
