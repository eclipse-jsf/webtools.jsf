package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AppendChildElementOperation extends AbstractTransformOperation {

	private String tagName;
	private boolean makeChildCurrent;

	public AppendChildElementOperation(String tagName) {
		this(tagName, true);
	}

	public AppendChildElementOperation(String tagName, boolean makeChildCurrent) {
		this.tagName = tagName;
		this.makeChildCurrent = makeChildCurrent;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		Element element = null;
		if (curElement != null && tagName != null && tagName.length() > 0) {
			Document document = curElement.getOwnerDocument();
			if (document != null) {
				Element childElement = document.createElement(tagName);
				curElement.appendChild(childElement);
				if (makeChildCurrent) {
					element = childElement;
				} else {
					element = curElement;
				}
			}
		}
		return element;
	}

}
