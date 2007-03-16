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

import org.w3c.dom.Element;

/**
 * ITransformOperation implementation specifically for the "selectManyMenu"
 * JSF (HTML) Element. 
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class SelectManyMenuOperation extends SelectBasedOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.jsf.SelectBasedOperation#handleMultipleAndSizeAttributes(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	protected void handleMultipleAndSizeAttributes(Element srcElement, Element selectElement) {
		selectElement.setAttribute("multiple", "multiple");
		selectElement.setAttribute("size", "1");
	}

}
