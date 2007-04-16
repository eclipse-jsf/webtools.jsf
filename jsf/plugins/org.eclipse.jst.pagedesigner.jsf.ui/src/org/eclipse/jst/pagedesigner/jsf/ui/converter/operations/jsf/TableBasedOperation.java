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
package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.jsf;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.CopyAttributeOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.RenameAttributeOperation;
import org.w3c.dom.Element;

/**
 * Abstract ITransformOperation implementation intended as super class for HTML
 * "table"-based "selectXXX" JSF (HTML) Elements. 
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class TableBasedOperation extends AbstractTransformOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		Element tableElement = createElement("table");
		new CopyAttributeOperation("styleClass").transform(srcElement, tableElement);
		new RenameAttributeOperation("styleClass", "class").transform(srcElement, tableElement);
		new CopyAttributeOperation("style").transform(srcElement, tableElement);
		new CopyAttributeOperation("border").transform(srcElement, tableElement);
		boolean layoutHorizontal = true;
		if ("pageDirection".equalsIgnoreCase(srcElement.getAttribute("layout"))) {
			layoutHorizontal = false;
		}
		Element itemContainer;
		if (layoutHorizontal) {
			itemContainer = appendChildElement("tr", tableElement);
		} else {
			itemContainer = tableElement;
		}
		boolean isDisabled = Boolean.TRUE.toString().equalsIgnoreCase(srcElement.getAttribute("disabled"));
		boolean isReadOnly = Boolean.TRUE.toString().equalsIgnoreCase(srcElement.getAttribute("readonly"));
		List selectItemList = getChildElements(srcElement, "selectItem");
		Iterator itSelectItemList = selectItemList.iterator();
		while (itSelectItemList.hasNext()) {
			Element selectItem = (Element) itSelectItemList.next();
			Element labelElement = createElement("label");
			Element inputElement = appendChildElement("input", labelElement);
			inputElement.setAttribute("type", getInputType());
			if (isDisabled || Boolean.TRUE.toString().equalsIgnoreCase(selectItem.getAttribute("itemDisabled"))) {
				inputElement.setAttribute("disabled", "disabled");
			}
			if (isReadOnly) {
				inputElement.setAttribute("readonly", "readonly");
			}
			String selectItemID = selectItem.getAttribute("id");
			if (selectItemID != null && selectItemID.length() > 0) {
				inputElement.setAttribute("id", selectItemID);
			}
			String selectItemValue = selectItem.getAttribute("value");
			if (selectItemValue != null && selectItemValue.length() > 0) {
				inputElement.setAttribute("value", selectItemValue);
			}
			String label = getSelectItemLabel(selectItem);
			appendChildText(label, labelElement);
			if (layoutHorizontal) {
				Element tdElement = appendChildElement("td", itemContainer);
				tdElement.appendChild(labelElement);
			} else {
				Element trElement = appendChildElement("tr", itemContainer);
				Element tdElement = appendChildElement("td", trElement);
				tdElement.appendChild(labelElement);
			}
		}
		return tableElement;
	}

	/**
	 * Subclasses override this in order to return the "type" attribute of
	 * child "input" Elements ("checkbox" or "radio").
	 * 
	 * @return the "type" attribute of child "input" Elements.
	 */
	protected abstract String getInputType();

	/**
	 * Attempts to get a label for the selectItem Element instance.
	 * 
	 * @param selectItem "selectItem" source Element instance.
	 * @return Label for the specified selectItem Element instance.
	 */
	private String getSelectItemLabel(Element selectItem) {
		String attribute = selectItem.getAttribute("itemLabel");
		if (attribute != null && attribute.length() > 0) {
			return attribute;
		}
		attribute = selectItem.getAttribute("value");
		if (attribute != null && attribute.length() > 0) {
			return attribute;
		}
		attribute = selectItem.getAttribute("itemDescription");
		if (attribute != null && attribute.length() > 0) {
			return attribute;
		}
		attribute = selectItem.getAttribute("itemValue");
		if (attribute != null && attribute.length() > 0) {
			return attribute;
		}
		return "selectItem";
	}

}
