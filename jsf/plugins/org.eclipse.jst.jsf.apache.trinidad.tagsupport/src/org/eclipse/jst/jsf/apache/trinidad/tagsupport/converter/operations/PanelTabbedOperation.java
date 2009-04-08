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

import org.eclipse.jst.jsf.apache.trinidad.tagsupport.ITrinidadConstants;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.Messages;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.TrinidadUtils;
import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation;
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
public class PanelTabbedOperation extends AbstractTrinidadTransformOperation {

	private static final int SEP_POS_BETWEEN = -1;
	private static final int SEP_POS_START = 0;
	private static final int SEP_POS_END = 1;

	private static final String STYLECLASS_TABLE_ABOVE = "af_panelTabbed_orientation-top"; //$NON-NLS-1$
	private static final String STYLECLASS_TABLE_BELOW = "af_panelTabbed_orientation-bottom"; //$NON-NLS-1$
	private static final String STYLECLASS_SEPARATOR_START = "af_panelTabbed_cell-start"; //$NON-NLS-1$
	private static final String STYLECLASS_SEPARATOR_END = "af_panelTabbed_cell-end"; //$NON-NLS-1$
	private static final String STYLECLASS_CELL = "af_panelTabbed_tab-selected"; //$NON-NLS-1$
	private static final String STYLECLASS_CELL_NOTDISCLOSED_OR_DISABLED = "af_panelTabbed_tab"; //$NON-NLS-1$
	
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
		List<Node> showDetailItems = getChildElements(
				srcElement, "showDetailItem"); //$NON-NLS-1$
		if (showDetailItems.size() > 0) {

			//determine tabs position ("both", "above", or "below" - default "both")
			String tabsPosition = srcElement.getAttribute("position"); //$NON-NLS-1$
			if (tabsPosition == null ||
					!(tabsPosition.equalsIgnoreCase("above") || //$NON-NLS-1$
					tabsPosition.equalsIgnoreCase("below"))) { //$NON-NLS-1$
				tabsPosition = "both"; //$NON-NLS-1$
			}

			//need to track where showDetailItem is in relation to "tabs"
			int showDetailItemConvertPosition = 0;

			//write tabs "above" if specified
			if ("above".equalsIgnoreCase(tabsPosition) || //$NON-NLS-1$
					"both".equalsIgnoreCase(tabsPosition)) { //$NON-NLS-1$
				appendTabs(srcElement, showDetailItems, spanElement, true);
				showDetailItemConvertPosition++;
			}

			//copy current child showDetailItem
			int currentEditorItem =
				getCurrentShowDetailItem(srcElement, showDetailItems);
			int curItem = 0;
			Iterator<Node> itItems = showDetailItems.iterator();
			while (itItems.hasNext()) {
				Node nodeItem = itItems.next();
				if (currentEditorItem == curItem) {
					if (nodeItem instanceof Element) {
						Element elemItem = (Element)nodeItem;
						tagConverterContext.addChild(
								elemItem,
								new ConvertPosition(
										spanElement,
										showDetailItemConvertPosition));
						break;
					}
				}
				curItem++;
			}

			//write tabs "below" if specified
			if ("below".equalsIgnoreCase(tabsPosition) || //$NON-NLS-1$
					"both".equalsIgnoreCase(tabsPosition)) { //$NON-NLS-1$
				appendTabs(srcElement, showDetailItems, spanElement, false);
			}
		} else {
			appendAttribute(
					spanElement,
					"style", //$NON-NLS-1$
					ITrinidadConstants.STYLE_EMPTYELEMENT);
			appendChildText(
					Messages.PanelTabbedOperation_EmptyPanelTabbedTag,
					spanElement);
		}
			
		return spanElement;
	}

	private void appendTabs(Element srcElement, List<Node> showDetailItems, Element spanElement, boolean above) {
		Element tableElement = appendChildElement("table", spanElement); //$NON-NLS-1$
		String tableStyleClass;
		if (above) {
			tableStyleClass = STYLECLASS_TABLE_ABOVE;
		} else {
			tableStyleClass = STYLECLASS_TABLE_BELOW;
		}
		appendAttribute(tableElement, "class", tableStyleClass); //$NON-NLS-1$
		appendAttribute(tableElement, "cellpadding", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "cellspacing", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "border", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "width", "100%"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "summary", ""); //$NON-NLS-1$ //$NON-NLS-2$
		Element trElement = appendChildElement("tr", tableElement); //$NON-NLS-1$

		//append first separator
		appendSeparatorTD(trElement, SEP_POS_START);

		int currentItem = getCurrentShowDetailItem(srcElement, showDetailItems);
		int disclosedItem = calculateDisclosedShowDetailItem(showDetailItems);
		int curItem = 0;

		//iterate over showDetailItem elements
		Iterator<Node> itItems = showDetailItems.iterator();
		while (itItems.hasNext()) {
			Node nodeItem = itItems.next();
			if (nodeItem instanceof Element) {
				Element elemItem = (Element)nodeItem;
				appendShowDetailItemTD(
						trElement,
						elemItem,
						currentItem == curItem,
						disclosedItem == curItem);
				if (curItem < showDetailItems.size() - 1) {
					appendSeparatorTD(trElement);
				}
				curItem++;
			}
		}

		//append last separator
		appendSeparatorTD(trElement, SEP_POS_END);
	}

	private void appendSeparatorTD(Element trElement, int sepPosition) {
		Element tdElement = appendChildElement("td", trElement); //$NON-NLS-1$
		if (sepPosition == SEP_POS_START) {
			appendAttribute(tdElement, "class", STYLECLASS_SEPARATOR_START); //$NON-NLS-1$
		} else if (sepPosition == SEP_POS_END) {
			appendAttribute(tdElement, "class", STYLECLASS_SEPARATOR_END); //$NON-NLS-1$
		}
		Element bElement = appendChildElement("b", tdElement); //$NON-NLS-1$
		appendAttribute(bElement, "style", "margin-left:0px;"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private void appendSeparatorTD(Element trElement) {
		appendSeparatorTD(trElement, SEP_POS_BETWEEN);
	}

	private void appendShowDetailItemTD(Element trElement, Element showDetailItem, boolean isCurrent, boolean isDisclosed) {
		boolean isDisabled = false;
		String attrShowDetailItemDisabled = showDetailItem.getAttribute("disabled"); //$NON-NLS-1$
		if (Boolean.TRUE.toString().equalsIgnoreCase(attrShowDetailItemDisabled)) {
			isDisabled = true;
		}
		Element tdElement = appendChildElement("td", trElement); //$NON-NLS-1$
		appendAttribute(tdElement, "height", "1"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tdElement, "nowrap", "nowrap"); //$NON-NLS-1$ //$NON-NLS-2$
		String tdStyleClass;
		if (isDisclosed && !isDisabled) {
			tdStyleClass = STYLECLASS_CELL;
		} else {
			tdStyleClass = STYLECLASS_CELL_NOTDISCLOSED_OR_DISABLED;
		}
		appendAttribute(tdElement, "class", tdStyleClass); //$NON-NLS-1$
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
		if (isCurrent) {
			aStyle += "border:1px solid #99cc99;padding:2px;"; //$NON-NLS-1$
		}
		String attrShowDetailItemInlineStyle = showDetailItem.getAttribute("inlineStyle"); //$NON-NLS-1$
		if (attrShowDetailItemInlineStyle != null &&
				attrShowDetailItemInlineStyle.length() > 0) {
			aStyle += attrShowDetailItemInlineStyle;
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

	private int getCurrentShowDetailItem(
			Element srcElement, List<Node> showDetailItems) {
		int disclosedItem = TrinidadUtils.getCurrentChildIndex(srcElement);
		if (disclosedItem == -1) {
			disclosedItem = calculateDisclosedShowDetailItem(showDetailItems);
			TrinidadUtils.setCurrentChildIndex(srcElement, disclosedItem);
		}
		return disclosedItem;
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
				if (Boolean.TRUE.toString().equalsIgnoreCase(
						attrDisclosedVal)) {
					disclosedItem = curItem;
					break;
				}
			}
			curItem++;
		}
		//if none explicitly disclosed, consider first non-disabled tab disclosed
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
		//if none explicitly disclosed and all disabled, consider first tab disclosed
		if (disclosedItem == -1) {
			disclosedItem = 0;
		}
		return disclosedItem;
	}

}
