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

import java.util.List;

import org.w3c.dom.Element;

/**
 * ITransformOperation implementation specifically for the "selectManyListbox"
 * JSF (HTML) Element. 
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class SelectManyListboxOperation extends SelectBasedOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.jsf.SelectBasedOperation#handleMultipleAndSizeAttributes(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	protected void handleMultipleAndSizeAttributes(Element srcElement, Element selectElement) {
		selectElement.setAttribute("multiple", "multiple");

		String size = srcElement.getAttribute("size");
		if (size == null || size.length() < 1) {
			List selectItems = getChildElements(srcElement, "selectItem");
			if (selectItems.size() > 0) {
				size = String.valueOf(selectItems.size());
			} else {
				size = "3";
			}
		}
		selectElement.setAttribute("size", size);
	}

}
