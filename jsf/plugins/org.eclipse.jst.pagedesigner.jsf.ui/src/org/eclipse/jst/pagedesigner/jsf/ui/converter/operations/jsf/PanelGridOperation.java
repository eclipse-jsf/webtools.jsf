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
import org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.TransformOperationFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * ITransformOperation implementation specifically for the "panelGrid" JSF
 * (HTML) Element. 
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class PanelGridOperation extends AbstractTransformOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		Element tableElement = null;
		//create table element, copy all attributes, rename "styleClass" attribute to "class"
		tableElement = createElement("table"); //$NON-NLS-1$
		ITransformOperation operation =
			TransformOperationFactory.getInstance().getTransformOperation(
					TransformOperationFactory.OP_CopyAllAttributesOperation,
					new String[]{});
		operation.transform(srcElement, tableElement);
		operation =
			TransformOperationFactory.getInstance().getTransformOperation(
					TransformOperationFactory.OP_RenameAttributeOperation,
					new String[]{"styleClass", "class"}); //$NON-NLS-1$ //$NON-NLS-2$
		operation.transform(srcElement, tableElement);
		//get value of "columns" attribute
		int columns;
		try {
			columns = Integer.parseInt(srcElement.getAttribute("columns")); //$NON-NLS-1$
		} catch(NumberFormatException nfe) {
			columns = 1;
		}
		if (columns < 1) {
			columns = 1;
		}
		//check for "header" facet and render appropriately
		Element headerFacetElement = getChildFacetByName(srcElement, "header"); //$NON-NLS-1$
		if (headerFacetElement != null) {
			Element tHeadElement = appendChildElement("thead", tableElement); //$NON-NLS-1$
			Element trElement = appendChildElement("tr", tHeadElement); //$NON-NLS-1$
			Element thElement = appendChildElement("th", trElement); //$NON-NLS-1$
			String headerClass = srcElement.getAttribute("headerClass"); //$NON-NLS-1$
			if (headerClass != null && headerClass.length() > 0) {
				operation =
					TransformOperationFactory.getInstance().getTransformOperation(
							TransformOperationFactory.OP_CreateAttributeOperation,
							new String[]{"class", headerClass}); //$NON-NLS-1$
				operation.transform(srcElement, thElement);
			}
			operation =
				TransformOperationFactory.getInstance().getTransformOperation(
						TransformOperationFactory.OP_CreateAttributeOperation,
						new String[]{"colspan", String.valueOf(columns)}); //$NON-NLS-1$
			operation.transform(srcElement, thElement);
			tagConverterContext.addChild(headerFacetElement, new ConvertPosition(thElement, 0));
		}
		//check for "footer" facet and render appropriately
		Element footerFacetElement = getChildFacetByName(srcElement, "footer"); //$NON-NLS-1$
		if (footerFacetElement != null) {
			Element tFootElement = appendChildElement("tfoot", tableElement); //$NON-NLS-1$
			Element trElement = appendChildElement("tr", tFootElement); //$NON-NLS-1$
			Element tdElement = appendChildElement("td", trElement); //$NON-NLS-1$
			String footerClass = srcElement.getAttribute("footerClass"); //$NON-NLS-1$
			if (footerClass != null && footerClass.length() > 0) {
				operation =
					TransformOperationFactory.getInstance().getTransformOperation(
							TransformOperationFactory.OP_CreateAttributeOperation,
							new String[]{"class", footerClass}); //$NON-NLS-1$
				operation.transform(srcElement, tdElement);
			}
			operation =
				TransformOperationFactory.getInstance().getTransformOperation(
						TransformOperationFactory.OP_CreateAttributeOperation,
						new String[]{"colspan", String.valueOf(columns)}); //$NON-NLS-1$
			operation.transform(srcElement, tdElement);
			tagConverterContext.addChild(footerFacetElement, new ConvertPosition(tdElement, 0));
		}
		//get rowClasses and columnClasses for subsequent processing
        List rowClasses = new ArrayList();
        String rowClassesAttribute = srcElement.getAttribute("rowClasses"); //$NON-NLS-1$
        if (rowClassesAttribute != null && rowClassesAttribute.length() > 0) {
            StringTokenizer tokenizer = new StringTokenizer(rowClassesAttribute, ", "); //$NON-NLS-1$
            while (tokenizer.hasMoreTokens()) {
                rowClasses.add(tokenizer.nextToken());
            }
        }
        List columnClasses = new ArrayList();
        String columnClassAttribute = srcElement.getAttribute("columnClasses"); //$NON-NLS-1$
        if (columnClassAttribute != null) {
            StringTokenizer tokenizer = new StringTokenizer(columnClassAttribute, ", "); //$NON-NLS-1$
            while (tokenizer.hasMoreTokens()) {
                columnClasses.add(tokenizer.nextToken());
            }
        }
		//render children using appropriate number of columns and appropriate classes
        Element tBodyElement = appendChildElement("tbody", tableElement); //$NON-NLS-1$
        List childElements = getChildElementsSkipFacets(srcElement);
        Element trElement = null;
        int nextRow = 0;
        int curIndex = 0;
        Iterator itChildElements = childElements.iterator();
        while (itChildElements.hasNext()) {
        	int columnIndex = curIndex % columns;
        	if (columnIndex == 0) {
        		trElement = appendChildElement("tr", tBodyElement); //$NON-NLS-1$
        		if (!rowClasses.isEmpty()) {
    				operation =
    					TransformOperationFactory.getInstance().getTransformOperation(
    							TransformOperationFactory.OP_CreateAttributeOperation,
    							new String[]{"class", (String)rowClasses.get(nextRow)}); //$NON-NLS-1$
    				operation.transform(srcElement, trElement);
        			nextRow = (nextRow + 1) % rowClasses.size();
        		}
        	}
        	Element tdElement = appendChildElement("td", trElement); //$NON-NLS-1$
        	if (columnIndex < columnClasses.size()) {
				operation =
					TransformOperationFactory.getInstance().getTransformOperation(
							TransformOperationFactory.OP_CreateAttributeOperation,
							new String[]{"class", (String)columnClasses.get(columnIndex)}); //$NON-NLS-1$
				operation.transform(srcElement, tdElement);
        	}
        	tagConverterContext.addChild((Element)itChildElements.next(), new ConvertPosition(tdElement, 0));
        	curIndex++;
        }
        return tableElement;
	}

	/**
	 * Gets a child Element of the specified parent Element that has the node
	 * name "facet" and the specified value of the "name" attribute.
	 * 
	 * @param srcElement Parent Element instance.
	 * @param facetName Name of the facet Element for which to search.
	 * @return Child Element that is a facet with the specified name.
	 */
	private Element getChildFacetByName(Element srcElement, String facetName) {
		Element element = null;
		List facets = getChildElements(srcElement, "facet"); //$NON-NLS-1$
		Iterator itFacets = facets.iterator();
		while (itFacets.hasNext()) {
			Element facet = (Element)itFacets.next();
			String facetAttrName = facet.getAttribute("name"); //$NON-NLS-1$
			if (facetAttrName != null && facetAttrName.equals(facetName)) {
				element = facet;
				break;
			}
		}
		return element;
	}

	/**
	 * Gets a list of child Elements of the specified parent Element, skipping
	 * any "facet" Elements.
	 * 
	 * @param srcElement Parent Element instance.
	 * @return List of child Elements of the specified parent Element that does
	 * not include any child "facet" Elements.
	 */
	private List getChildElementsSkipFacets(Element srcElement) {
		List childElementsList = new ArrayList();
		NodeList childNodes = srcElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				if (!childNode.getLocalName().equals("facet")) { //$NON-NLS-1$
					childElementsList.add(childNode);
				}
			}
		}
		return childElementsList;
	}

}
