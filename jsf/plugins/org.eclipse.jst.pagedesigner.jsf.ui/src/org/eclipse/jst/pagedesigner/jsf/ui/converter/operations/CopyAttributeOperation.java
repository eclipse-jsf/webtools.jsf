package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

public class CopyAttributeOperation extends AbstractTransformOperation {

	private String attributeName;
	private boolean create;
	private String newAttributeValue;

	public CopyAttributeOperation(String attributeName) {
		this(attributeName, false, null);
	}

	public CopyAttributeOperation(String attributeName, boolean create, String newAttributeValue) {
		this.attributeName = attributeName;
		this.create = create;
		this.newAttributeValue = newAttributeValue;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.ITransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (srcElement != null && curElement != null) {
			Attr attribute = srcElement.getAttributeNode(attributeName);
			if (attribute != null) {
				curElement.setAttribute(attributeName, attribute.getValue());
			} else if (create && newAttributeValue != null) {
				curElement.setAttribute(attributeName, newAttributeValue);
			}
		}
		return curElement;
	}

}
