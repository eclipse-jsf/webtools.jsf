package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DTTagConverterContext implements ITagConverterContext {

	private DTTagConverter tagConverter;

	public DTTagConverterContext(DTTagConverter tagConverter) {
		this.tagConverter = tagConverter;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterContext#getHostElement()
	 */
	public Element getHostElement() {
		Element hostElement = null;
		if (tagConverter != null) {
			hostElement = tagConverter.getHostElement();
		}
		return hostElement;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterContext#addChild(org.w3c.dom.Node, org.eclipse.jst.pagedesigner.converter.ConvertPosition)
	 */
	public void addChild(Node childNode, ConvertPosition position) {
		if (tagConverter != null) {
			tagConverter.addChild(childNode, position);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterContext#copyChildren(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public void copyChildren(Element srcElement, Element destElement) {
		if (tagConverter != null) {
			tagConverter.copyChildren(srcElement, destElement);
		}
	}

}
