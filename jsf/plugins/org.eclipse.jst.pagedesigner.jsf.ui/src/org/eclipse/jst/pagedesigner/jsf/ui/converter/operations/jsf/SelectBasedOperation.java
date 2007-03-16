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

import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.CopyAllAttributesOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.RenameAttributeOperation;
import org.w3c.dom.Element;

/**
 * Abstract ITransformOperation implementation intended as super class for HTML
 * "select"-based "selectXXX" JSF (HTML) Elements. 
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class SelectBasedOperation extends AbstractTransformOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		Element selectElement = createElement("select");
		new CopyAllAttributesOperation().transform(srcElement, selectElement);
		new RenameAttributeOperation("id", "name").transform(srcElement, selectElement);
		new RenameAttributeOperation("styleClass", "style").transform(srcElement, selectElement);
		handleMultipleAndSizeAttributes(srcElement, selectElement);
		List selectItemList = getChildElements(srcElement, "selectItem");
		Iterator itSelectItemList = selectItemList.iterator();
		while (itSelectItemList.hasNext()) {
			Element selectItem = (Element)itSelectItemList.next();
			Element optionElement = appendChildElement("option", selectElement);
			optionElement.setAttribute("value", selectItem.getAttribute("itemValue"));
			String label = getSelectItemLabel(selectItem);
			appendChildText(label, optionElement);
		}
		return selectElement;
	}

	/**
	 * Subclasses override this in order to set the "multiple" and "size"
	 * attributes of the "select" Element.
	 * 
	 * @param srcElement Source Element instance.
	 * @param selectElement "select" Element instance.
	 */
	protected abstract void handleMultipleAndSizeAttributes(Element srcElement, Element selectElement);

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
