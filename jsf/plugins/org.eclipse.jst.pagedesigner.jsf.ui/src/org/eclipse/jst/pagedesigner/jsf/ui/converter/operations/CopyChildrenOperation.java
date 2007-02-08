package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations;

import org.w3c.dom.Element;

public class CopyChildrenOperation extends AbstractTransformOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (tagConverterContext != null) {
			tagConverterContext.copyChildren(srcElement, curElement);
		}
		return curElement;
	}

}
