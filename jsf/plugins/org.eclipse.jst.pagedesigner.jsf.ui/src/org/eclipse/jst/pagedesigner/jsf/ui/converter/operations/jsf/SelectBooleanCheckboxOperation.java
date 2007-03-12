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

import org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional.ITransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.CopyAllAttributesOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.CreateAttributeOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.CreateElementOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.RenameAttributeOperation;
import org.w3c.dom.Element;

/**
 * ITransformOperation implementation specifically for the
 * "selectBooleanCheckbox" JSF (HTML) Element. 
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class SelectBooleanCheckboxOperation extends AbstractTransformOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		Element inputElement = null;
		//create input element, copy all attributes, rename "styleClass" attribute to "class"
		inputElement = createElement("input");
		new CopyAllAttributesOperation().transform(srcElement, inputElement);
		new RenameAttributeOperation("styleClass", "class").transform(srcElement, inputElement);
		//set "type" attribute to "checkbox"
		new CreateAttributeOperation("type", "checkbox").transform(srcElement, inputElement);
		//test "value" attribute value == true and set "checked" attribute appropriately
		String valueAttr = srcElement.getAttribute("value");
		if (valueAttr != null && valueAttr.equalsIgnoreCase(Boolean.TRUE.toString())) {
			new CreateAttributeOperation("checked", "checked").transform(srcElement, inputElement);
		}
		return inputElement;
	}

	/**
	 * Creates a new Element.
	 * 
	 * @param tagName Name of Element to be created.
	 * @return New Element instance.
	 */
	protected Element createElement(String tagName) {
		ITransformOperation operation = new CreateElementOperation(tagName);
		operation.setTagConverterContext(tagConverterContext);
		return operation.transform(null, null);
	}

}
