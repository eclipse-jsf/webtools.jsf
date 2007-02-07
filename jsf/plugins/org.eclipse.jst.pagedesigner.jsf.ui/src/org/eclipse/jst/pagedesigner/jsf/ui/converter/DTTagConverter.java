package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DTTagConverter extends AbstractTagConverter {

	private boolean isMultiLevel = false;
	private boolean isWidget = false;

	public DTTagConverter(Element host) {
		super(host);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
	 */
	protected Element doConvertRefresh() {
		IOutputRenderer renderer = new DTHTMLOutputRenderer();
		return renderer.render(this);
	}

	public void setMultiLevel(boolean isMultiLevel) {
		this.isMultiLevel = isMultiLevel;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isMultiLevel()
	 */
	public boolean isMultiLevel() {
		return isMultiLevel;
	}

	public void setWidget(boolean isWidget) {
		this.isWidget = isWidget;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isWidget()
	 */
	public boolean isWidget() {
		return isWidget;
	}

	/**
	 * Allows public access to non-public method of AbstractTagConverter.
	 * 
	 * @param srcElement Source Element instance.
	 * @param destElement Destination Element instance.
	 */
	public void doCopyChildren(Element srcElement, Element destElement) {
		copyChildren(srcElement, destElement);
	}

	/**
	 * Allows public access to non-public method of AbstractTagConverter.
	 * 
	 * @param childNode Child Node instance.
	 * @param position ConvertPosition instance describing child node's
	 * position.
	 */
	public void doAddChild(Node childNode, ConvertPosition position) {
		addChild(childNode, position);
	}

}
