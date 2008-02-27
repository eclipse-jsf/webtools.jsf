/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.TransformOperationFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * ITransformOperation implementation specifically for the "panelTabbed" JSF
 * Element.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class PanelTabbedOperation extends AbstractTransformOperation {

	private static final int SEP_POS_BETWEEN = -1;
	private static final int SEP_POS_START = 0;
	private static final int SEP_POS_END = 1;

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public Element transform(Element srcElement, Element curElement) {
		//create outer span element and set style and class attributes
		Element spanElement = createElement("span"); //$NON-NLS-1$
		ITransformOperation operation =
			TransformOperationFactory.getInstance().getTransformOperation(
					TransformOperationFactory.OP_CopyAttributeWithRenameOperation,
					new String[]{"styleClass", "class"}); //$NON-NLS-1$  //$NON-NLS-2$
		operation.transform(srcElement, spanElement);
		operation =
			TransformOperationFactory.getInstance().getTransformOperation(
					TransformOperationFactory.OP_CopyAttributeWithRenameOperation,
					new String[]{"inlineStyle", "style"}); //$NON-NLS-1$  //$NON-NLS-2$
		operation.transform(srcElement, spanElement);

		//get child showDetailItem elements
		@SuppressWarnings("unchecked")
		List<Node> showDetailItems = getChildElements(srcElement, "showDetailItem"); //$NON-NLS-1$
		if (showDetailItems.size() > 0) {

			//determine tabs position ("both", "above", or "below" - default "both")
			String tabsPosition = srcElement.getAttribute("position"); //$NON-NLS-1$
			if (tabsPosition == null ||
					!(tabsPosition.equalsIgnoreCase("above") || //$NON-NLS-1$
					tabsPosition.equalsIgnoreCase("below"))) { //$NON-NLS-1$
				tabsPosition = "both"; //$NON-NLS-1$
			}

			//write tabs "above" if specified
			if ("above".equalsIgnoreCase(tabsPosition) || //$NON-NLS-1$
					"both".equalsIgnoreCase(tabsPosition)) { //$NON-NLS-1$
				appendTabs(showDetailItems, spanElement, true);
			}

			//append div for children
			Element divElement = appendChildElement("div", spanElement); //$NON-NLS-1$

			//copy "disclosed" showDetailItem's children
			int disclosedItem = calculateDisclosedShowDetailItem(showDetailItems);
			int curItem = 0;
			Iterator<Node> itItems = showDetailItems.iterator();
			while (itItems.hasNext()) {
				Node nodeItem = itItems.next();
				if (disclosedItem == curItem) {
					if (nodeItem instanceof Element) {
						Element elemItem = (Element)nodeItem;
						tagConverterContext.copyChildren(elemItem, divElement);
					}
				}
				curItem++;
			}

			//write tabs "below" if specified
			if ("below".equalsIgnoreCase(tabsPosition) || //$NON-NLS-1$
					"both".equalsIgnoreCase(tabsPosition)) { //$NON-NLS-1$
				appendTabs(showDetailItems, spanElement, false);
			}
		}
			
		return spanElement;
	}

	private void appendTabs(List<Node> showDetailItems, Element spanElement, boolean above) {
		Element tableElement = appendChildElement("table", spanElement); //$NON-NLS-1$
		String tableStyle;
		if (above) {
			tableStyle = "background-color:#e9e8e8;border-color:#99cc99;text-align:center;border-style:solid;padding:2px 0px;margin:4px 0px;border-width:1px 0px 0px;"; //$NON-NLS-1$
		} else {
			tableStyle = "background-color:#e9e8e8;border-color:#99cc99;text-align:center;border-style:solid;padding:2px 0px;margin:4px 0px;border-width:0px 0px 1px;"; //$NON-NLS-1$
		}
		appendAttribute(tableElement, "style", tableStyle); //$NON-NLS-1$
		appendAttribute(tableElement, "cellpadding", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "cellspacing", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "border", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "width", "100%"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "summary", ""); //$NON-NLS-1$ //$NON-NLS-2$
		Element trElement = appendChildElement("tr", tableElement);

		//append first separator
		appendSeparatorTD(trElement, SEP_POS_START);

		int disclosedItem = calculateDisclosedShowDetailItem(showDetailItems);
		int curItem = 0;

		//iterate over showDetailItem elements
		Iterator<Node> itItems = showDetailItems.iterator();
		while (itItems.hasNext()) {
			Node nodeItem = itItems.next();
			if (nodeItem instanceof Element) {
				Element elemItem = (Element)nodeItem;
				appendShowDetailItemTD(trElement, elemItem, disclosedItem == curItem);
				if (curItem < showDetailItems.size() - 1) {
					appendSeparatorTD(trElement);
				}
				curItem++;
			}
		}

		//append last separator
		appendSeparatorTD(trElement, SEP_POS_END);
	}

	private void appendAttribute(
			Element element, String attributeName, String attributeValue) {
		ITransformOperation operation =
			TransformOperationFactory.getInstance().getTransformOperation(
					TransformOperationFactory.OP_CreateAttributeOperation,
					new String[]{attributeName, attributeValue});
		operation.transform(null, element);
	}

	private void appendSeparatorTD(Element trElement, int sepPosition) {
		Element tdElement = appendChildElement("td", trElement); //$NON-NLS-1$
		if (sepPosition == SEP_POS_START) {
			appendAttribute(tdElement, "style", "width:0%;"); //$NON-NLS-1$ //$NON-NLS-2$
		} else if (sepPosition == SEP_POS_END) {
			appendAttribute(tdElement, "style", "width:100%;"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		Element bElement = appendChildElement("b", tdElement); //$NON-NLS-1$
		appendAttribute(bElement, "style", "margin-left:0px;"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private void appendSeparatorTD(Element trElement) {
		appendSeparatorTD(trElement, SEP_POS_BETWEEN);
	}

	private void appendShowDetailItemTD(Element trElement, Element showDetailItem, boolean isDisclosed) {
		boolean isDisabled = false;
		String attrShowDetailItemDisabled = showDetailItem.getAttribute("disabled"); //$NON-NLS-1$
		if (Boolean.TRUE.toString().equalsIgnoreCase(attrShowDetailItemDisabled)) {
			isDisabled = true;
		}
		Element tdElement = appendChildElement("td", trElement); //$NON-NLS-1$
		appendAttribute(tdElement, "height", "1"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tdElement, "nowrap", "nowrap"); //$NON-NLS-1$ //$NON-NLS-2$
		String tdStyle;
		if (isDisclosed && !isDisabled) {
			tdStyle = "font-family:Arial,Helvetica,Geneva,sans-serif;font-size:10pt;font-weight:bold;padding:0px 8px;"; //$NON-NLS-1$
		} else {
			tdStyle = "font-family:Arial,Helvetica,Geneva,sans-serif;font-size:10pt;font-weight:normal;padding:0px 8px;"; //$NON-NLS-1$
		}
		appendAttribute(tdElement, "style", tdStyle); //$NON-NLS-1$
		Element aElement = appendChildElement("a", tdElement); //$NON-NLS-1$
		String aStyle;
		if (isDisabled) {
			appendAttribute(aElement, "name", "name"); //$NON-NLS-1$ //$NON-NLS-2$
			aStyle = "color:#999999;"; //$NON-NLS-1$
		} else {
			appendAttribute(aElement, "href", "#"); //$NON-NLS-1$ //$NON-NLS-2$
			if (isDisclosed) {
				aStyle = "color:#669966;text-decoration:none;"; //$NON-NLS-1$
			} else {
				aStyle = "color:#003333;"; //$NON-NLS-1$
			}
		}
		String attrShowDetailItemInlineStyle = showDetailItem.getAttribute("inlineStyle"); //$NON-NLS-1$
		if (attrShowDetailItemInlineStyle != null &&
				attrShowDetailItemInlineStyle.length() > 0) {
			aStyle = aStyle + attrShowDetailItemInlineStyle;
		}
		if (aStyle.length() > 0) {
			appendAttribute(aElement, "style", aStyle); //$NON-NLS-1$
		}
		ITransformOperation operation =
			TransformOperationFactory.getInstance().getTransformOperation(
					TransformOperationFactory.OP_CopyAttributeWithRenameOperation,
					new String[]{"styleClass", "class"}); //$NON-NLS-1$ //$NON-NLS-2$
		operation.transform(showDetailItem, aElement);
		String attrShowDetailItemTextAndAccessKey = showDetailItem.getAttribute("textAndAccessKey"); //$NON-NLS-1$
		if (attrShowDetailItemTextAndAccessKey != null &&
				attrShowDetailItemTextAndAccessKey.length() > 0) {
			appendChildText(attrShowDetailItemTextAndAccessKey, aElement);
		} else {
			String attrShowDetailItemText = showDetailItem.getAttribute("text"); //$NON-NLS-1$
			if (attrShowDetailItemText != null && attrShowDetailItemText.length() > 0) {
				appendChildText(attrShowDetailItemText, aElement);
			}
		}
	}

	private int calculateDisclosedShowDetailItem(List<Node> showDetailItems) {
		int disclosedItem = -1;
		int curItem = 0;
		Iterator<Node> itItems = showDetailItems.iterator();
		while (itItems.hasNext()) {
			Node item = itItems.next();
			if (item instanceof Element) {
				Element elemItem = (Element)item;
				String attrDisclosedVal = elemItem.getAttribute("disclosed"); //$NON-NLS-1$
				if (Boolean.TRUE.toString().equalsIgnoreCase(attrDisclosedVal)) {
					disclosedItem = curItem;
					break;
				}
			}
			curItem++;
		}
		//if none explicitly disclosed, consider first non-disabled tab "disclosed"
		if (disclosedItem == -1) {
			curItem = 0;
			itItems = showDetailItems.iterator();
			while (itItems.hasNext()) {
				Node item = itItems.next();
				if (item instanceof Element) {
					Element elemItem = (Element)item;
					String attrDisabledVal = elemItem.getAttribute("disabled"); //$NON-NLS-1$
					if (!(Boolean.TRUE.toString().equalsIgnoreCase(attrDisabledVal))) {
						disclosedItem = curItem;
						break;
					}
				}
				curItem++;
			}
		}
		//if none explicitly disclosed and all disabled, consider first tab "disclosed"
		if (disclosedItem == -1) {
			disclosedItem = 0;
		}
		return disclosedItem;
	}

}
