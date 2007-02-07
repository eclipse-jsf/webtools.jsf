package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class MakeParentElementCurrentOperation extends AbstractTransformOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.ITransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		Element resultElement = null;
		if (curElement != null) {
			Node parentNode = curElement.getParentNode();
			while (parentNode != null && parentNode.getNodeType() != Node.DOCUMENT_NODE) {
				if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
					resultElement = (Element)parentNode;
					break;
				}
                parentNode = parentNode.getParentNode();
			}
		}
		return resultElement;
	}

}
