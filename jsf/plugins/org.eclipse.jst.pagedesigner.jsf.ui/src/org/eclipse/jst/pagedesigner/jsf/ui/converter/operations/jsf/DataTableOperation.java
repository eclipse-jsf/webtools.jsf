/*******************************************************************************
 * Copyright (c) 2005, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.jsf;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.TransformOperationFactory;
import org.w3c.dom.Element;

/**
 * ITransformOperation implementation specifically for the "dataTable" JSF
 * (HTML) Element. 
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class DataTableOperation extends AbstractTransformOperation {

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
		//build thead
		buildHeaderOrFooter(srcElement, tableElement, true);
		//build tbody
		buildBody(srcElement, tableElement);
		//build tfoot
		buildHeaderOrFooter(srcElement, tableElement, false);
		return tableElement;
	}

	private void buildHeaderOrFooter(Element srcElement, Element tableElement, boolean isHeader) {
		//setup vars depending on whether we are building thead or tfoot
		String facetName = isHeader ? "header" : "footer"; //$NON-NLS-1$ //$NON-NLS-2$
		String headerOrFooterTagName = isHeader ? "thead" : "tfoot"; //$NON-NLS-1$ //$NON-NLS-2$
		String thOrTdTagName = isHeader ? "th" : "td"; //$NON-NLS-1$ //$NON-NLS-2$
		String headerClassOrFooterClassAttrName = isHeader ? "headerClass" : "footerClass"; //$NON-NLS-1$ //$NON-NLS-2$

		//look for facet ("header" or "footer")
		Element facetElement = getChildFacetByName(srcElement, facetName);

		//get list of child "column" elements
		List columnElementList = getChildElements(srcElement, "column"); //$NON-NLS-1$

		//look for facet ("header" or "footer") on child "column" elements
		boolean hasColumnFacet = false;
		Iterator itColumnElementList = columnElementList.iterator();
		while (itColumnElementList.hasNext()) {
			Element columnElement = (Element)itColumnElementList.next();
			Element columnFacet = getChildFacetByName(columnElement, facetName);
			if (columnFacet != null) {
				hasColumnFacet = true;
				break;
			}
		}

		//test if we even need to build header or footer
		if (facetElement == null && !hasColumnFacet) {
			return;
		}

		//append "thead" or "tfoot" Element
		Element headerOrFooterElement = appendChildElement(headerOrFooterTagName, tableElement);

		//if facetElement exists, build appropriate thead or tfoot children
		if (facetElement != null) {
			//append "tr" Element
			Element trElement = appendChildElement("tr", headerOrFooterElement); //$NON-NLS-1$
			//append "th" or "td" Element
			Element thOrTdElement = appendChildElement(thOrTdTagName, trElement);
			//set "class" attribute
			String headerClassOrFooterClassAttribute = srcElement.getAttribute(headerClassOrFooterClassAttrName);
			if (headerClassOrFooterClassAttribute != null && headerClassOrFooterClassAttribute.length() > 0) {
				ITransformOperation operation =
					TransformOperationFactory.getInstance().getTransformOperation(
							TransformOperationFactory.OP_CreateAttributeOperation,
							new String[]{"class", headerClassOrFooterClassAttribute}); //$NON-NLS-1$
				operation.transform(srcElement, thOrTdElement);
			}
			//set "colspan" attribute
			if (columnElementList.size() > 0) {
				ITransformOperation operation =
					TransformOperationFactory.getInstance().getTransformOperation(
							TransformOperationFactory.OP_CreateAttributeOperation,
							new String[]{"colspan", String.valueOf(columnElementList.size())}); //$NON-NLS-1$
				operation.transform(srcElement, thOrTdElement);
			}
			//add facet Element as child (to be processed further)
			tagConverterContext.addChild(facetElement, new ConvertPosition(thOrTdElement, 0));
		}

		//if any child column has "header" or "footer" facet, build "tr" element
		if (hasColumnFacet) {
			//append "tr" Element
			Element trElement = appendChildElement("tr", headerOrFooterElement); //$NON-NLS-1$
			//iterate through columnElementList
			itColumnElementList = columnElementList.iterator();
			while (itColumnElementList.hasNext()) {
				Element columnElement = (Element)itColumnElementList.next();
				//get "header" or "footer" facet of column
				Element columnFacet = getChildFacetByName(columnElement, facetName);
				//append "th" or "td" Element
				Element thOrTdElement = appendChildElement(thOrTdTagName, trElement);
				//set "class" attribute
				String headerClassOrFooterClassAttribute = srcElement.getAttribute(headerClassOrFooterClassAttrName);
				if (headerClassOrFooterClassAttribute != null && headerClassOrFooterClassAttribute.length() > 0) {
					ITransformOperation operation =
						TransformOperationFactory.getInstance().getTransformOperation(
								TransformOperationFactory.OP_CreateAttributeOperation,
								new String[]{"class", headerClassOrFooterClassAttribute}); //$NON-NLS-1$
					operation.transform(srcElement, thOrTdElement);
				}
				//if facet exists, add facet Element as child (to be processed further)
				if (columnFacet != null) {
					tagConverterContext.addChild(columnFacet, new ConvertPosition(thOrTdElement, 0));
				}
			}
		}
	}

	private void buildBody(Element srcElement, Element tableElement) {
		//append "tbody" element
		Element tbodyElement = appendChildElement("tbody", tableElement); //$NON-NLS-1$
		//append "tr" element
		Element trElement = appendChildElement("tr", tbodyElement); //$NON-NLS-1$
		//parse "rowClasses" attribute and set "class" attribute
		String rowClassesAttribute = srcElement.getAttribute("rowClasses"); //$NON-NLS-1$
		if (rowClassesAttribute != null && rowClassesAttribute.length() > 0) {
			StringTokenizer tokenizer = new StringTokenizer(rowClassesAttribute, ", "); //$NON-NLS-1$
			if (tokenizer.hasMoreTokens()) {
				ITransformOperation operation =
					TransformOperationFactory.getInstance().getTransformOperation(
							TransformOperationFactory.OP_CreateAttributeOperation,
							new String[]{"class", tokenizer.nextToken()}); //$NON-NLS-1$
				operation.transform(srcElement, trElement);
			}
		}
		//add child columns (to be processed further)
		List columnElementList = getChildElements(srcElement, "column"); //$NON-NLS-1$
		Iterator itColumnElementList = columnElementList.iterator();
		int index = 0;
		while (itColumnElementList.hasNext()) {
			Element columnElement = (Element)itColumnElementList.next();
			tagConverterContext.addChild(columnElement, new ConvertPosition(trElement, index++));
		}
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
			if (facet.getAttribute("name").equals(facetName)) { //$NON-NLS-1$
				element = facet;
				break;
			}
		}
		return element;
	}

}
