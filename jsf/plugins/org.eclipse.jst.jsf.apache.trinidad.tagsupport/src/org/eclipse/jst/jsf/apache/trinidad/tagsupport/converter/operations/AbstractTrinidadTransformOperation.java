/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.TransformOperationFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Extends AbstractTransformOperation to supply extra convenience methods.
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class AbstractTrinidadTransformOperation extends AbstractTransformOperation {

	/**
	 * Appends the specified attribute with the specified value to the specified
	 * Element instance.
	 * 
	 * @param element Element instance to append attribute to.
	 * @param attributeName Name of attribute to be appended.
	 * @param attributeValue Value of attribute to be appended.
	 */
	protected void appendAttribute(
			Element element, String attributeName, String attributeValue) {
		ITransformOperation operation =
			TransformOperationFactory.getInstance().getTransformOperation(
					TransformOperationFactory.OP_CreateAttributeOperation,
					new String[]{attributeName, attributeValue});
		operation.transform(null, element);
	}

	/**
	 * Gets a child Element of the specified parent Element that has the node
	 * name "facet" and the specified value of the "name" attribute.
	 * 
	 * @param srcElement Parent Element instance.
	 * @param facetName Name of the facet Element for which to search.
	 * @return Child Element that is a facet with the specified name.
	 */
	protected Element getChildFacetByName(Element srcElement, String facetName) {
		Element element = null;
		@SuppressWarnings("unchecked")
		List facets = getChildElements(srcElement, "facet"); //$NON-NLS-1$
		@SuppressWarnings("unchecked")
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
	protected List<Element> getChildElementsSkipFacets(Element srcElement) {
		List<Element> childElementsList = new ArrayList<Element>();
		NodeList childNodes = srcElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				if (!childNode.getLocalName().equals("facet")) { //$NON-NLS-1$
					childElementsList.add((Element)childNode);
				}
			}
		}
		return childElementsList;
	}

	/**
	 * Returns a List of child Node instances that will be added by the
	 * "CopyChildrenOperation" ITransformOperation. This can be useful in
	 * determining if any Node instances will, in fact, be copied. 
	 * 
	 * @param srcElement Source Element instance to query for child Node
	 * instances.
	 * @return A List of child Node instances that will be added by the
	 * "CopyChildrenOperation" ITransformOperation.
	 */
	protected List<Node> getCopyChildrenNodes(Element srcElement) {
		List<Node> children = new ArrayList<Node>();
		if (srcElement != null) {
			NodeList childNodes = srcElement.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node childNode = childNodes.item(i);
				short childNodeType = childNode.getNodeType();
				if (childNodeType == Node.ELEMENT_NODE ||
						childNodeType == Node.TEXT_NODE ||
						childNodeType == Node.CDATA_SECTION_NODE) {
					children.add(childNode);
				}
			}
		}
		return children;
	}

}
