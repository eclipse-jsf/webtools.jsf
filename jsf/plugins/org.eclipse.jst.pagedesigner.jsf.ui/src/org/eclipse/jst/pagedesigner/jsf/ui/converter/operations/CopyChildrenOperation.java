package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations;

import org.eclipse.jst.pagedesigner.jsf.ui.converter.DTTagConverter;
import org.w3c.dom.Element;

public class CopyChildrenOperation extends AbstractTransformOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		DTTagConverter tagConverter = getTagConverter();
		if (tagConverter != null) {
			tagConverter.doCopyChildren(srcElement, curElement);
		}
		return curElement;
	}

}
