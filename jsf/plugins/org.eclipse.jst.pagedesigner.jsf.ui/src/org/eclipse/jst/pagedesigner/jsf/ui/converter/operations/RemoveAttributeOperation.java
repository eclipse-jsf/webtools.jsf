package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations;

import org.w3c.dom.Element;

public class RemoveAttributeOperation extends AbstractTransformOperation {

	private String attributeName;

	public RemoveAttributeOperation(String attributeName) {
		this.attributeName = attributeName;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.ITransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (curElement != null) {
			curElement.removeAttribute(attributeName);
		}
		return curElement;
	}

}
