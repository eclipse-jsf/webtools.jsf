/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.dtmanager.converter.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.eclipse.jst.pagedesigner.converter.IDOMFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo;
import org.eclipse.jst.pagedesigner.editors.palette.TagImageManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Design-Time metadata-driven tag converter.
 * 
 * @author Ian Trimble - Oracle
 */
public class DTTagConverter implements
	ITagConverter, ITagEditInfo, INodeAdapter, IDOMFactory {

	private Element hostElement;
	private Element resultElement;
	private Image visualImage;
	private ImageDescriptor visualImageDescriptor;
	private IDOMDocument destDocument;
	private List childNodeList = Collections.EMPTY_LIST;
	private NodeConvertPositionMap childVisualPositionMap;
	private List nonVisualChildElementList = Collections.EMPTY_LIST;
	private boolean isMultiLevel = false;
	private boolean isVisualByHTML = true;
	private boolean isWidget = false;
	private int mode;
	private int minHeight;
	private int minWidth;
	private boolean needBorderDecorator = false;
	private boolean needTableDecorator = false;

	/**
	 * Constructs an instance for the specified source Element.
	 * 
	 * @param hostElement Source Element instance.
	 */
	public DTTagConverter(Element hostElement) {
		this.hostElement = hostElement;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#convertRefresh(java.lang.Object)
	 */
	public void convertRefresh(Object context) {
		childNodeList = new ArrayList();
		childVisualPositionMap = new NodeConvertPositionMap();
		nonVisualChildElementList = new ArrayList();
		resultElement = new DTHTMLOutputRenderer().render(new DTTagConverterContext(this));
		new DTTagConverterDecorator().decorate(this);
		if (resultElement instanceof INodeNotifier) {
			((INodeNotifier)resultElement).addAdapter(this);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#dispose()
	 */
	public void dispose() {
		visualImage = null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#getChildModeList()
	 */
	public List getChildModeList() {
		//need to reset the NodeConvertPositionMap at this time
		childVisualPositionMap.reset();
		return childNodeList;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#getChildVisualPosition(org.w3c.dom.Node)
	 */
	public ConvertPosition getChildVisualPosition(Node childModel) {
		return childVisualPositionMap.get(childModel);
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
		return nonVisualChildElementList;
	}

	/**
	 * Sets the result Element instance; allows decorators to set an "unknown
	 * tag" Element when tag conversion has not produced a result.
	 * 
	 * @param resultElement Result Element instance to be set.
	 */
	public void setResultElement(Element resultElement) {
		this.resultElement = resultElement;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#getResultElement()
	 */
	public Element getResultElement() {
		return resultElement;
	}

	/**
	 * Sets the visual Image instance.
	 * 
	 * @param visualImage Visual Image instance.
	 */
	public void setVisualImage(Image visualImage) {
		this.visualImage = visualImage;
	}

	/**
	 * Sets the visual ImageDescriptor instance.
	 * 
	 * @param imageDescriptor Visual ImageDescriptor instance.
	 */
	public void setVisualImageDescriptor(ImageDescriptor imageDescriptor) {
		this.visualImageDescriptor = imageDescriptor;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#getVisualImage()
	 */
	public Image getVisualImage() {
		if (visualImage == null && visualImageDescriptor != null) {
			visualImage = TagImageManager.getOrCreateImage(visualImageDescriptor);
		}
		return visualImage;
	}

	/**
	 * Sets the "isMultiLevel" flag; allows decorators to manipulate this
	 * setting.
	 * 
	 * @param isMultiLevel Sets the "isMultiLevel" flag to true or false.
	 */
	public void setMultiLevel(boolean isMultiLevel) {
		this.isMultiLevel = isMultiLevel;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isMultiLevel()
	 */
	public boolean isMultiLevel() {
		return isMultiLevel;
	}

	/**
	 * Sets the "isVisualByHTML" flag; allows decorators to manipulate this
	 * setting.
	 * 
	 * @param isVisualByHTML Sets the "isVisualByHTML" flag to true or false.
	 */
	public void setVisualByHTML(boolean isVisualByHTML) {
		this.isVisualByHTML = isVisualByHTML;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isVisualByHTML()
	 */
	public boolean isVisualByHTML() {
		return isVisualByHTML;
	}

	/**
	 * Sets the "isWidget" flag; allows decorators to manipulate this setting.
	 * 
	 * @param isWidget Sets the "isWidget" flag to true or false.
	 */
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
	public void setDestDocument(IDOMDocument destDocument) {
		this.destDocument = destDocument;
	}

	/**
	 * Gets the IDOMDocument instance on which new Nodes are created.
	 * 
	 * @return IDOMDocument instance.
	 */
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

	/**
	 * Gets this instance's "mode", as set by setMode(int mode).
	 * 
	 * @return This instance's "mode".
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * Sets the desired minimum height of the visual representation; allows
	 * decorators to manipulate this setting.
	 * 
	 * @param minHeight The desired minimum height of the visual
	 * representation.
	 */
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#getMinHeight()
	 */
	public int getMinHeight() {
		return minHeight;
	}

	/**
	 * Sets the desired minimum width of the visual representation; allows
	 * decorators to manipulate this setting.
	 * 
	 * @param minWidth The desired minimum width of the visual
	 * representation.
	 */
	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#getMinWidth()
	 */
	public int getMinWidth() {
		return minWidth;
	}

	/**
	 * Sets the "needBorderDecorator" flag; allows decorators to manipulate
	 * this setting.
	 * 
	 * @param needBorderDecorator Sets the "needBorderDecorator" flag to true
	 * or false.
	 */
	public void setNeedBorderDecorator(boolean needBorderDecorator) {
		this.needBorderDecorator = needBorderDecorator;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#needBorderDecorator()
	 */
	public boolean needBorderDecorator() {
		return needBorderDecorator;
	}

	/**
	 * Sets the "needTableDecorator" flag; allows decorators to manipulate
	 * this setting.
	 * 
	 * @param needTableDecorator Sets the "needTableDecorator" flag to true
	 * or false.
	 */
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

	/**
	 * Adds a child Node to the collection of Nodes requiring subsequent tag
	 * conversion.
	 * 
	 * @param childNode Node instance to be added.
	 * @param position ConvertPosition instance describing indexed position
	 * relative to another Node in the Document.
	 */
	public void addChild(Node childNode, ConvertPosition position) {
		childNodeList.add(childNode);
		childVisualPositionMap.put(childNode, position);
	}

	/**
	 * Adds all child Nodes to the collection of Nodes requiring subsequent tag
	 * conversion.
	 * 
	 * @param srcElement Source Element for which all child Nodes should be
	 * added.
	 * @param destElement Element to which added Nodes are relative.
	 */
	public void copyChildren(Element srcElement, Element destElement) {
		int index = 0;
		NodeList childNodes = srcElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node curNode = childNodes.item(i); 
			if (
					curNode.getNodeType() == Node.ELEMENT_NODE ||
					curNode.getNodeType() == Node.TEXT_NODE ||
					curNode.getNodeType() == Node.CDATA_SECTION_NODE) {
				addChild(curNode, new ConvertPosition(destElement, index++));
			}
		}
	}

	/**
	 * Adds a non-visual child Element to the collection of non-visual
	 * children (subsequently retrieved via a call to "getNonVisualChildren".
	 * 
	 * @param childElement Child Element to be added.
	 */
	public void addNonVisualChildElement(Element childElement) {
		nonVisualChildElementList.add(childElement);
	}

	/**
	 * Supports multiple ConvertPosition instances associated with a single
	 * Node instance.
	 */
	private class NodeConvertPositionMap {

		private Map mapOfConvertPositions = new HashMap();
		private Map mapOfIndexes = new HashMap();

		/**
		 * Puts a ConvertPosition instance for the specified Node instance into
		 * the map.
		 * 
		 * @param node Node instance for which the ConvertPosition is to be
		 * applied.
		 * @param convertPosition ConvertPosition instance for the specified
		 * Node instance.
		 */
		public void put(Node node, ConvertPosition convertPosition) {
			List convertPositions = (List)mapOfConvertPositions.get(node);
			if (convertPositions == null) {
				convertPositions = new ArrayList();
				mapOfConvertPositions.put(node, convertPositions);
			}
			convertPositions.add(convertPosition);
		}

		/**
		 * Gets the current ConvertPosition instance for the specified Node
		 * instance. Note that each time this is called without a reset having
		 * occurred, the next ConvertPosition instance mapped to the Node
		 * instance is returned.
		 * 
		 * @param node Node instance for which a ConvertPosition instance is
		 * being requested.
		 * @return the current ConvertPosition instance for the specified Node
		 * instance.
		 */
		public ConvertPosition get(Node node) {
			ConvertPosition convertPosition = null;
			List convertPositions = (List)mapOfConvertPositions.get(node);
			if (convertPositions != null) {
				int index;
				Object obj = mapOfIndexes.get(node);
				if (obj != null) {
					index = ((Integer)obj).intValue();
				} else {
					index = 0;
				}
				convertPosition = (ConvertPosition)convertPositions.get(index);
				mapOfIndexes.put(node, new Integer(++index));
			}
			return convertPosition;
		}

		/**
		 * Resets the concept of "current" ConvertPosition instance for all
		 * Node instances. Typically called when the List of child Nodes is
		 * retrieved.
		 */
		public void reset() {
			mapOfIndexes.clear();
		}

	}

}
