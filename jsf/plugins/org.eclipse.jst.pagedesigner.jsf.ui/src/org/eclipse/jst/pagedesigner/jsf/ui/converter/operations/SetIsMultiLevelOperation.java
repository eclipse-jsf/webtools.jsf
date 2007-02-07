package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations;

import org.eclipse.jst.pagedesigner.jsf.ui.converter.DTTagConverter;
import org.w3c.dom.Element;

public class SetIsMultiLevelOperation extends AbstractTransformOperation {

	private boolean isMultiLevel;

	public SetIsMultiLevelOperation(boolean isMultiLevel) {
		this.isMultiLevel = isMultiLevel;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		DTTagConverter tagConverter = getTagConverter();
		if (tagConverter != null) {
			tagConverter.setMultiLevel(isMultiLevel);
		}
		return curElement;
	}

}
