package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.converter.IDOMFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class DTTagConverter implements
ITagConverter, ITagEditInfo, INodeAdapter, IDOMFactory {

	private Element hostElement;
	private Element resultElement;
	private Image visualImage;
	private IDOMDocument destDocument;
	private List childNodeList = Collections.EMPTY_LIST;
	private Map childVisualPositionMap = Collections.EMPTY_MAP;
	private boolean isMultiLevel = false;
	private boolean isVisualByHTML = true;
	private boolean isWidget = false;
	private int mode;
	private int minHeight;
	private int minWidth;
	private boolean needBorderDecorator = false;
	private boolean needTableDecorator = false;

	public DTTagConverter(Element hostElement) {
		this.hostElement = hostElement;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#convertRefresh(java.lang.Object)
	 */
	public void convertRefresh(Object context) {
		childNodeList = new ArrayList();
		childVisualPositionMap = new HashMap();
		resultElement = new DTHTMLOutputRenderer().render(new DTTagConverterContext(this));
		if (mode == IConverterFactory.MODE_DESIGNER) {
			new DTTagConverterDecorator().decorate(this);
		}
		if (resultElement instanceof INodeNotifier) {
			((INodeNotifier)resultElement).addAdapter(this);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#dispose()
	 */
	public void dispose() {
		//do nothing
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#getChildModeList()
	 */
	public List getChildModeList() {
		return childNodeList;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#getChildVisualPosition(org.w3c.dom.Node)
	 */
	public ConvertPosition getChildVisualPosition(Node childModel) {
		return (ConvertPosition)childVisualPositionMap.get(childModel);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#getHostElement()
	 */
	public Element getHostElement() {
		return hostElement;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#getNonVisualChildren()
	 */
	public List getNonVisualChildren() {
		return Collections.EMPTY_LIST;
	}

	public void setResultElement(Element resultElement) {
		this.resultElement = resultElement;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#getResultElement()
	 */
	public Element getResultElement() {
		return resultElement;
	}

	public void setVisualImage(Image visualImage) {
		this.visualImage = visualImage;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#getVisualImage()
	 */
	public Image getVisualImage() {
		return visualImage;
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

	public void setVisualByHTML(boolean isVisualByHTML) {
		this.isVisualByHTML = isVisualByHTML;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isVisualByHTML()
	 */
	public boolean isVisualByHTML() {
		return isVisualByHTML;
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

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#setDestDocument(org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument)
	 */
	public void setDestDocument(IDOMDocument document) {
		this.destDocument = destDocument;
	}

	public IDOMDocument getDestDocument() {
		IDOMDocument document = null;
		if (destDocument != null) {
			document = destDocument;
		} else {
			document = (IDOMDocument)hostElement.getOwnerDocument();
		}
		return document;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#setMode(int)
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getMode() {
		return mode;
	}

	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#getMinHeight()
	 */
	public int getMinHeight() {
		return minHeight;
	}

	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#getMinWidth()
	 */
	public int getMinWidth() {
		return minWidth;
	}

	public void setNeedBorderDecorator(boolean needBorderDecorator) {
		this.needBorderDecorator = needBorderDecorator;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#needBorderDecorator()
	 */
	public boolean needBorderDecorator() {
		return needBorderDecorator;
	}

	public void setNeedTableDecorator(boolean needTableDecorator) {
		this.needTableDecorator = needTableDecorator;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#needTableDecorator()
	 */
	public boolean needTableDecorator() {
		return needTableDecorator;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		if (type == ITagEditInfo.class) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#notifyChanged(org.eclipse.wst.sse.core.internal.provisional.INodeNotifier, int, java.lang.Object, java.lang.Object, java.lang.Object, int)
	 */
	public void notifyChanged(INodeNotifier notifier, int eventType,
			Object changedFeature, Object oldValue, Object newValue, int pos) {
		//do nothing
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.IDOMFactory#createElement(java.lang.String)
	 */
	public Element createElement(String tag) {
		return getDestDocument().createElement(tag);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.IDOMFactory#createText(java.lang.String)
	 */
	public Text createText(String content) {
		return getDestDocument().createTextNode(content);
	}

	public void addChild(Node childNode, ConvertPosition position) {
		childNodeList.add(childNode);
		childVisualPositionMap.put(childNode, position);
	}

	public void copyChildren(Element srcElement, Element destElement) {
		int index = 0;
		for (Node curNode = srcElement.getFirstChild(); curNode != null; curNode = curNode.getNextSibling()) {
			if (
					curNode.getNodeType() == Node.ELEMENT_NODE ||
					curNode.getNodeType() == Node.TEXT_NODE ||
					curNode.getNodeType() == Node.CDATA_SECTION_NODE) {
				addChild(curNode, new ConvertPosition(destElement, index++));
			}
		}
	}

}
