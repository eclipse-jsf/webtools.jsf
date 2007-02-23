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
package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.dom.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.ResolveAttributeValue;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.TagDecorateInfo;
import org.eclipse.jst.pagedesigner.dtmanager.internal.provisional.DTManager;
import org.eclipse.jst.pagedesigner.dtmanager.internal.provisional.IDTInfo;
import org.eclipse.jst.pagedesigner.jsf.ui.util.JSFUIPluginResourcesUtil;
import org.eclipse.jst.pagedesigner.preview.PageExpressionContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * ITagConverterDecorator implementation for DTTagConverter.
 * 
 * @author Ian Trimble - Oracle
 */
public class DTTagConverterDecorator implements ITagConverterDecorator {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterDecorator#decorate(org.eclipse.jst.pagedesigner.converter.ITagConverter)
	 */
	public void decorate(ITagConverter tagConverter) {
		if (!(tagConverter instanceof DTTagConverter)) {
			throw new IllegalArgumentException(JSFUIPluginResourcesUtil.getInstance().getString("Error.DTTagConverterDecorator.NotDTTagConverterInstance"));
		}
		DTTagConverter dtTagConverter = (DTTagConverter)tagConverter;
		if (tagConverter.getResultElement() == null) {
			createUnknownTagRepresentation(dtTagConverter);
		}

		if (dtTagConverter.getMode() == IConverterFactory.MODE_DESIGNER) {
			if (!decorateFromDTInfo(dtTagConverter, "vpd-decorate-design")) {
				//decorateForDesignMode(dtTagConverter);
			}
		} else if (dtTagConverter.getMode() == IConverterFactory.MODE_PREVIEW) {
			if (!decorateFromDTInfo(dtTagConverter, "vpd-decorate-preview")) {
				//decorateForPreviewMode(dtTagConverter);
			}
		}
	}

	/**
	 * Performs decoration of the specified DTTagConverter instance from
	 * IDTInfo (metadata) for the specified (by ID) TagDecorateInfo.
	 * 
	 * @param dtTagConverter DTTagConverter instance.
	 * @param tagDecorateInfoID ID of the TagDecorateInfo to be located in
	 * metadata.
	 * @return true if successfully processed, else false.
	 */
	protected boolean decorateFromDTInfo(DTTagConverter dtTagConverter, String tagDecorateInfoID) {
		boolean processed = false;
		Element srcElement = dtTagConverter.getHostElement();
		DTManager dtManager = new DTManager();
		IDTInfo dtInfo = dtManager.getDTInfo(srcElement);
		if (dtInfo != null) {
			TagDecorateInfo tdInfo = dtInfo.getTagDecorateInfo(tagDecorateInfoID);
			if (tdInfo != null) {
				dtTagConverter.setMultiLevel(tdInfo.isMultiLevel());
				dtTagConverter.setNeedBorderDecorator(tdInfo.isNeedBorderDecorator());
				dtTagConverter.setNeedTableDecorator(tdInfo.isNeedTableDecorator());
				if (tdInfo.isResolveChildText()) {
					resolveChildText(dtTagConverter.getResultElement());
				}
				if (tdInfo.isSetNonVisualChildElements()) {
					setNonVisualChildElements(dtTagConverter, srcElement);
				}
				dtTagConverter.setWidget(tdInfo.isWidget());
				dtTagConverter.setMinHeight(tdInfo.getMinHeight());
				dtTagConverter.setMinWidth(tdInfo.getMinWidth());
				ResolveAttributeValue resAttrValue = tdInfo.getResolveAttributeValue();
				if (resAttrValue != null) {
					String attributeName = resAttrValue.getAttributeName();
					if (attributeName != null && attributeName.length() > 0) {
						resolveAttributeValue(dtTagConverter.getResultElement(), attributeName);
					}
				}
				processed = true;
			}
		}
		return processed;
	}

	/**
	 * Performs decoration of the specified DTTagConverter instance for the
	 * Visual Page Designer's "Design" mode.
	 * 
	 * @param dtTagConverter DTTagConverter instance.
	 */
	protected void decorateForDesignMode(DTTagConverter dtTagConverter) {
		Element srcElement = dtTagConverter.getHostElement();

		TagIdentifier srcTagIdentifier =
			TagIdentifierFactory.createDocumentTagWrapper(srcElement);

		if (IJSFConstants.TAG_IDENTIFIER_VIEW.isSameTagType(srcTagIdentifier)) {
			dtTagConverter.setNeedBorderDecorator(true);
		} else if (IJSFConstants.TAG_IDENTIFIER_FACET.isSameTagType(srcTagIdentifier)) {
			dtTagConverter.setMinWidth(10);
			dtTagConverter.setMinHeight(10);
			dtTagConverter.setMultiLevel(true);
			dtTagConverter.setNeedBorderDecorator(true);
		} else if (IJSFConstants.TAG_IDENTIFIER_VERBATIM.isSameTagType(srcTagIdentifier)) {
			dtTagConverter.setMinWidth(10);
			dtTagConverter.setMinHeight(10);
			dtTagConverter.setMultiLevel(true);
			dtTagConverter.setNeedBorderDecorator(true);
		} else if (IJSFConstants.TAG_IDENTIFIER_FORM.isSameTagType(srcTagIdentifier)) {
			dtTagConverter.setNeedBorderDecorator(true);
        } else if (IJSFConstants.TAG_IDENTIFIER_INPUTTEXT.isSameTagType(srcTagIdentifier)) {
			dtTagConverter.setMultiLevel(true);
			dtTagConverter.setWidget(true);
			setNonVisualChildElements(dtTagConverter, srcElement);
        } else if (IJSFConstants.TAG_IDENTIFIER_INPUTSECRET.isSameTagType(srcTagIdentifier)) {
			dtTagConverter.setMultiLevel(true);
			dtTagConverter.setWidget(true);
			setNonVisualChildElements(dtTagConverter, srcElement);
        } else if (IJSFConstants.TAG_IDENTIFIER_INPUTTEXTAREA.isSameTagType(srcTagIdentifier)) {
			dtTagConverter.setMultiLevel(true);
			dtTagConverter.setWidget(true);
			setNonVisualChildElements(dtTagConverter, srcElement);
        } else if (IJSFConstants.TAG_IDENTIFIER_OUTPUTTEXT.isSameTagType(srcTagIdentifier)) {
			dtTagConverter.setNeedBorderDecorator(true);
			dtTagConverter.setMultiLevel(true);
			dtTagConverter.setWidget(true);
			setNonVisualChildElements(dtTagConverter, srcElement);
        } else if (IJSFConstants.TAG_IDENTIFIER_OUTPUTLABEL.isSameTagType(srcTagIdentifier)) {
			dtTagConverter.setNeedBorderDecorator(true);
			dtTagConverter.setMultiLevel(true);
			dtTagConverter.setWidget(true);
			setNonVisualChildElements(dtTagConverter, srcElement);
        } else if (IJSFConstants.TAG_IDENTIFIER_GRAPHICIMAGE.isSameTagType(srcTagIdentifier)) {
        	dtTagConverter.setMultiLevel(true);
        	dtTagConverter.setWidget(true);
        	resolveAttributeValue(dtTagConverter.getResultElement(), "src");
		} else if (IJSFConstants.TAG_IDENTIFIER_PANEL_GRID.isSameTagType(srcTagIdentifier)) {
			dtTagConverter.setMultiLevel(true);
			dtTagConverter.setNeedBorderDecorator(true);
			dtTagConverter.setNeedTableDecorator(true);
		}
	}

	/**
	 * Performs decoration of the specified DTTagConverter instance for the
	 * Visual Page Designer's "Preview" mode.
	 * 
	 * @param dtTagConverter DTTagConverter instance.
	 */
	protected void decorateForPreviewMode(DTTagConverter dtTagConverter) {
		Element srcElement = dtTagConverter.getHostElement();

		TagIdentifier srcTagIdentifier =
			TagIdentifierFactory.createDocumentTagWrapper(srcElement);

		if (IJSFConstants.TAG_IDENTIFIER_INPUTTEXTAREA.isSameTagType(srcTagIdentifier)) {
			resolveChildText(dtTagConverter.getResultElement());
		} else if (IJSFConstants.TAG_IDENTIFIER_OUTPUTTEXT.isSameTagType(srcTagIdentifier)) {
			resolveChildText(dtTagConverter.getResultElement());
		} else if (IJSFConstants.TAG_IDENTIFIER_OUTPUTLABEL.isSameTagType(srcTagIdentifier)) {
			resolveChildText(dtTagConverter.getResultElement());
        } else if (IJSFConstants.TAG_IDENTIFIER_GRAPHICIMAGE.isSameTagType(srcTagIdentifier)) {
        	resolveAttributeValue(dtTagConverter.getResultElement(), "src");
		}
	}

	/**
	 * Creates a visual representation result Element for an unknown tag.
	 * 
	 * @param dtTagConverter DTTagConverter instance.
	 */
	protected void createUnknownTagRepresentation(DTTagConverter dtTagConverter) {
		Element element = dtTagConverter.createElement("span");
		element.setAttribute("style", "color:red;font-weight:bold;");
		Text text = dtTagConverter.createText("<" + dtTagConverter.getHostElement().getTagName() + "/>");
		element.appendChild(text);
		dtTagConverter.setResultElement(element);
		dtTagConverter.setWidget(true);
	}

	/**
	 * Adds child Elements of the specified source Element to the specified
	 * DTTagConverter instance's collection of non-visual children.
	 * 
	 * @param dtTagConverter DTTagConverter instance.
	 * @param srcElement Source Element for which child Elements are to be
	 * added.
	 */
	protected void setNonVisualChildElements(DTTagConverter dtTagConverter, Element srcElement) {
		NodeList childNodes = srcElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node curNode = childNodes.item(i);
			if (curNode.getNodeType() == Node.ELEMENT_NODE) {
				dtTagConverter.addNonVisualChildElement((Element)curNode);
			}
		}
	}

	/**
	 * Performs simple EL resolution for the child Text Node of the specified
	 * source Element instance.
	 * 
	 * @param srcElement Source Element for which child Text Node EL resolution
	 * is to be performed.
	 */
	protected void resolveChildText(Element srcElement) {
		if (srcElement != null) {
			NodeList childNodes = srcElement.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node childNode = childNodes.item(i);
				if (childNode.getNodeType() == Node.TEXT_NODE) {
					Text textNode = (Text)childNode;
					String textNodeValue = textNode.getNodeValue();
					try {
						String newTextNodeValue = (String)PageExpressionContext.getCurrent().evaluateExpression(textNodeValue, String.class, null);
						if (!textNodeValue.equals(newTextNodeValue)) {
							textNode.setNodeValue(newTextNodeValue);
						}
					} catch(Exception ex) {
						//ignore - could not resolve, do not change existing value
					}
				}
			}
		}
	}

	/**
	 * Performs simple EL resolution for the value of the specified attribute
	 * of the specified Element.
	 * 
	 * @param srcElement Source Element instance.
	 * @param attributeName Name of attribute for which the value should be
	 * resolved.
	 */
	protected void resolveAttributeValue(Element srcElement, String attributeName) {
		if (srcElement != null) {
			String oldAttributeValue = srcElement.getAttribute(attributeName);
			if (oldAttributeValue != null && oldAttributeValue.length() > 0) {
				try {
					String newAttributeValue = (String)PageExpressionContext.getCurrent().evaluateExpression(oldAttributeValue, String.class, null);
					if (!oldAttributeValue.equals(newAttributeValue)) {
						srcElement.setAttribute(attributeName, newAttributeValue);
					}
				} catch(Exception ex) {
					//ignore - could not resolve, do not change existing value
				}
			}
		}
	}

}
