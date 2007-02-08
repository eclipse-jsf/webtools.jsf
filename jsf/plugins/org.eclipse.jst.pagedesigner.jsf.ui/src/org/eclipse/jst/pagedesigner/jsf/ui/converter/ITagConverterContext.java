package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public interface ITagConverterContext {

	public Element getHostElement();

	public void addChild(Node childNode, ConvertPosition position);

	public void copyChildren(Element srcElement, Element destElement);

}
