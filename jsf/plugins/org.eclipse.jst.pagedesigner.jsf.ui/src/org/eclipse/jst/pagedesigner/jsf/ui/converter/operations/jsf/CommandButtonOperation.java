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

import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.CopyAllAttributesOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.CreateAttributeOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.RenameAttributeOperation;
import org.w3c.dom.Element;

/**
 * ITransformOperation implementation specifically for the "commandButton" JSF
 * (HTML) Element. 
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class CommandButtonOperation extends AbstractTransformOperation {

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
		//get "image" attribute from srcElement
		String imageAttr = srcElement.getAttribute("image");
		String type = "submit"; //("submit" is default)
		if (imageAttr != null && imageAttr.length() > 0) {
			//"image" attribute exists
			type = "image";
			new RenameAttributeOperation("image", "src").transform(srcElement, inputElement);
		} else {
			//"image" attribute not present
			String typeAttr = srcElement.getAttribute("type");
			if (typeAttr != null && typeAttr.length() > 0) {
				type = typeAttr;
			}
		}
		//overwrite copied "type" attribute
		new CreateAttributeOperation("type", type).transform(srcElement, inputElement);
		return inputElement;
	}

}
