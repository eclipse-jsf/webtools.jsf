package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

public class RenameAttributeOperation extends AbstractTransformOperation {

	private String oldAttributeName;
	private String newAttributeName;

	public RenameAttributeOperation(String oldAttributeName, String newAttributeName) {
		this.oldAttributeName = oldAttributeName;
		this.newAttributeName = newAttributeName;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (curElement != null) {
			Attr oldAttribute = curElement.getAttributeNode(oldAttributeName);
			if (oldAttribute != null) {
				curElement.setAttribute(newAttributeName, oldAttribute.getValue());
				curElement.removeAttribute(oldAttributeName);
			}
		}
		return curElement;
	}

}
