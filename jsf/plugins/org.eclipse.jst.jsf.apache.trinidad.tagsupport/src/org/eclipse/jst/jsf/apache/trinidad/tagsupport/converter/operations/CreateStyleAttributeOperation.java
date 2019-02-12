/**
 * Copyright (c) 2008 Oracle Corporation and others.
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
 * ITransformOperation implementation to handle merging of an Element's optional
 * default style with a specified "inlineStyle" as the current Element's "style"
 * attribute.
 * 
 * @author Ian Trimble - Oracle
 */
public class CreateStyleAttributeOperation extends AbstractTrinidadTransformOperation {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public Element transform(Element srcElement, Element curElement) {
		String styleValue = null;
		if (getParameters().length > 0) {
			styleValue = getParameters()[0];
		}
		String inlineStyle = srcElement.getAttribute("inlineStyle"); //$NON-NLS-1$
		if (inlineStyle != null && inlineStyle.length() > 0) {
			if (styleValue == null) {
				styleValue = inlineStyle;
			} else {
				if (styleValue.length() > 0 && !styleValue.endsWith(";")) { //$NON-NLS-1$
					styleValue += ";"; //$NON-NLS-1$
				}
				styleValue += inlineStyle;
			}
		}
		if (styleValue != null && curElement != null) {
			curElement.setAttribute("style", styleValue); //$NON-NLS-1$
		}
		return curElement;
	}

}
