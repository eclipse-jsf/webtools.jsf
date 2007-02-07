package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations;

import org.w3c.dom.Element;

public class CreateAttributeOperation extends AbstractTransformOperation {

	private String attributeName;
	private String attributeValue;

	public CreateAttributeOperation(String attributeName, String attributeValue) {
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.ITransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (curElement != null) {
			curElement.setAttribute(attributeName, attributeValue);
		}
		return curElement;
	}

}
