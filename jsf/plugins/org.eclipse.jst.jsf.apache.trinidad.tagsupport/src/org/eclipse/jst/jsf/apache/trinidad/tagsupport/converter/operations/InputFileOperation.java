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

import org.w3c.dom.Element;

/**
 * ITransformOperation implementation specifically for the "inputFile" JSF
 * Element.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class InputFileOperation extends AbstractLabelAndHelpOperation {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations.AbstractLabelAndHelpOperation#appendControl(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	protected void appendControl(Element srcElement, Element parentElement) {
		Element controlElement = appendChildElement("input", parentElement); //$NON-NLS-1$
		appendAttribute(controlElement, "size", //$NON-NLS-1$
				String.valueOf(getColumns(srcElement)));
		appendAttribute(controlElement, "type", "file"); //$NON-NLS-1$ //$NON-NLS-2$
		String value = srcElement.getAttribute("value"); //$NON-NLS-1$
		if (value != null) {
			appendAttribute(controlElement, "value", value); //$NON-NLS-1$
		}
		//append common attributes
		appendAttribute(controlElement, "class", STYLECLASS_CONTROLELEMENT); //$NON-NLS-1$
		String contentStyle = calculateStyle(null, srcElement, "contentStyle"); //$NON-NLS-1$
		if (contentStyle != null) {
			appendAttribute(controlElement, "style", contentStyle); //$NON-NLS-1$
		}
	}

}
