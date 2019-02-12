/**
 * Copyright (c) 2008, 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations;

import org.w3c.dom.Element;

/**
 * ITransformOperation implementation specifically for the "inputText" JSF
 * Element.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class InputTextOperation extends AbstractLabelAndHelpOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations.AbstractLabelAndHelpOperation#appendControl(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	protected void appendControl(Element srcElement, Element parentElement) {
		int rows = getRows(srcElement);
		Element controlElement;
		if (rows < 2) {
			controlElement = appendChildElement("input", parentElement); //$NON-NLS-1$
			appendAttribute(controlElement, "size", //$NON-NLS-1$
					String.valueOf(getColumns(srcElement)));
			appendAttribute(controlElement, "type", getType(srcElement)); //$NON-NLS-1$
			String value = srcElement.getAttribute("value"); //$NON-NLS-1$
			if (value != null) {
				appendAttribute(controlElement, "value", value); //$NON-NLS-1$
			}
		} else {
			controlElement = appendChildElement("textarea", parentElement); //$NON-NLS-1$
			appendAttribute(controlElement, "cols", //$NON-NLS-1$
					String.valueOf(getColumns(srcElement)));
			appendAttribute(controlElement, "rows", //$NON-NLS-1$
					String.valueOf(getRows(srcElement)));
			appendAttribute(controlElement, "wrap", getWrap(srcElement)); //$NON-NLS-1$
			String value = srcElement.getAttribute("value"); //$NON-NLS-1$
			if (value != null) {
				appendChildText(value, controlElement);
			}
		}
		//append common attributes
		appendAttribute(controlElement, "class", STYLECLASS_CONTROLELEMENT); //$NON-NLS-1$
		String contentStyle = calculateStyle(null, srcElement, "contentStyle"); //$NON-NLS-1$
		if (contentStyle != null) {
			appendAttribute(controlElement, "style", contentStyle); //$NON-NLS-1$
		}
	}

	private int getRows(Element srcElement) {
		int rows = 1;
		if (srcElement != null) {
			String rowsValue = srcElement.getAttribute("rows"); //$NON-NLS-1$
			if (rowsValue != null && rowsValue.length() > 0) {
				try {
					rows = Integer.parseInt(rowsValue);
				} catch(NumberFormatException nfe) {
					//ignore; default value will be returned
				}
			}
		}
		return rows;
	}

	private String getWrap(Element srcElement) {
		String wrap = "soft"; //$NON-NLS-1$
		if (srcElement != null) {
			String wrapValue = srcElement.getAttribute("wrap"); //$NON-NLS-1$
			if (wrapValue != null && wrapValue.length() > 0) {
				wrap = wrapValue;
			}
		}
		return wrap;
	}

	private String getType(Element srcElement) {
		String type = "text"; //$NON-NLS-1$
		if (srcElement != null) {
			String secret = srcElement.getAttribute("secret"); //$NON-NLS-1$
			if (Boolean.valueOf(secret)) {
				type = "password"; //$NON-NLS-1$
			}
		}
		return type;
	}

}
