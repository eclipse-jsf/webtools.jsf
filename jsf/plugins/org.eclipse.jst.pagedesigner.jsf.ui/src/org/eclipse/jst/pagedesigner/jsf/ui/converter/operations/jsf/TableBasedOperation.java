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

import org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.TransformOperationFactory;
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
		Element tableElement = createElement("table"); //$NON-NLS-1$
		ITransformOperation operation =
			TransformOperationFactory.getInstance().getTransformOperation(
					TransformOperationFactory.OP_CopyAttributeOperation,
					new String[]{"styleClass"}); //$NON-NLS-1$
		operation.transform(srcElement, tableElement);
		operation =
			TransformOperationFactory.getInstance().getTransformOperation(
					TransformOperationFactory.OP_RenameAttributeOperation,
					new String[]{"styleClass", "class"}); //$NON-NLS-1$ //$NON-NLS-2$
		operation.transform(srcElement, tableElement);
		operation =
			TransformOperationFactory.getInstance().getTransformOperation(
					TransformOperationFactory.OP_CopyAttributeOperation,
					new String[]{"style"}); //$NON-NLS-1$
		operation.transform(srcElement, tableElement);
		operation =
			TransformOperationFactory.getInstance().getTransformOperation(
					TransformOperationFactory.OP_CopyAttributeOperation,
					new String[]{"border"}); //$NON-NLS-1$
		operation.transform(srcElement, tableElement);
		boolean layoutHorizontal = true;
		if ("pageDirection".equalsIgnoreCase(srcElement.getAttribute("layout"))) { //$NON-NLS-1$ //$NON-NLS-2$
			layoutHorizontal = false;
		}
		Element itemContainer;
		if (layoutHorizontal) {
			itemContainer = appendChildElement("tr", tableElement); //$NON-NLS-1$
		} else {
			itemContainer = tableElement;
		}
		boolean isDisabled = Boolean.TRUE.toString().equalsIgnoreCase(srcElement.getAttribute("disabled")); //$NON-NLS-1$
		boolean isReadOnly = Boolean.TRUE.toString().equalsIgnoreCase(srcElement.getAttribute("readonly")); //$NON-NLS-1$
		List selectItemList = getChildElements(srcElement, "selectItem"); //$NON-NLS-1$
		Iterator itSelectItemList = selectItemList.iterator();
		while (itSelectItemList.hasNext()) {
			Element selectItem = (Element) itSelectItemList.next();
			Element labelElement = createElement("label"); //$NON-NLS-1$
			Element inputElement = appendChildElement("input", labelElement); //$NON-NLS-1$
			inputElement.setAttribute("type", getInputType()); //$NON-NLS-1$
			if (isDisabled || Boolean.TRUE.toString().equalsIgnoreCase(selectItem.getAttribute("itemDisabled"))) { //$NON-NLS-1$
				inputElement.setAttribute("disabled", "disabled"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (isReadOnly) {
				inputElement.setAttribute("readonly", "readonly"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			String selectItemID = selectItem.getAttribute("id"); //$NON-NLS-1$
			if (selectItemID != null && selectItemID.length() > 0) {
				inputElement.setAttribute("id", selectItemID); //$NON-NLS-1$
			}
			String selectItemValue = selectItem.getAttribute("value"); //$NON-NLS-1$
			if (selectItemValue != null && selectItemValue.length() > 0) {
				inputElement.setAttribute("value", selectItemValue); //$NON-NLS-1$
			}
			String label = getSelectItemLabel(selectItem);
			appendChildText(label, labelElement);
			if (layoutHorizontal) {
				Element tdElement = appendChildElement("td", itemContainer); //$NON-NLS-1$
				tdElement.appendChild(labelElement);
			} else {
				Element trElement = appendChildElement("tr", itemContainer); //$NON-NLS-1$
				Element tdElement = appendChildElement("td", trElement); //$NON-NLS-1$
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
		String attribute = selectItem.getAttribute("itemLabel"); //$NON-NLS-1$
		if (attribute != null && attribute.length() > 0) {
			return attribute;
		}
		attribute = selectItem.getAttribute("value"); //$NON-NLS-1$
		if (attribute != null && attribute.length() > 0) {
			return attribute;
		}
		attribute = selectItem.getAttribute("itemDescription"); //$NON-NLS-1$
		if (attribute != null && attribute.length() > 0) {
			return attribute;
		}
		attribute = selectItem.getAttribute("itemValue"); //$NON-NLS-1$
		if (attribute != null && attribute.length() > 0) {
			return attribute;
		}
		return "selectItem"; //$NON-NLS-1$
	}

}
