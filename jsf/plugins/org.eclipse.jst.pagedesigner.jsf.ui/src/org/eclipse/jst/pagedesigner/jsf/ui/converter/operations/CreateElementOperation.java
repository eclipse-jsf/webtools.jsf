package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateElementOperation extends AbstractTransformOperation {

	private String tagName;

	public CreateElementOperation(String tagName) {
		this.tagName = tagName;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.ITransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		Element element = null;
		if (curElement != null && tagName != null && tagName.length() > 0) {
			Document document = curElement.getOwnerDocument();
			if (document != null) {
				element = document.createElement(tagName);
			}
		}
		return element;
	}

}
