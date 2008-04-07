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
 * ITransformOperation implementation specifically for any "input..." JSF
 * Elements that are basically comprised of an inputText followed by an icon.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class InputTextWithIconOperation extends AbstractLabelAndHelpOperation {

	private String imageFilename;

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations.AbstractLabelAndHelpOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public Element transform(Element srcElement, Element curElement) {
		if (getParameters().length < 1) {
			getLog().error("Warning.TransformOperationFactory.TooFewParameters", getTransformOperationID()); //$NON-NLS-1$
			return null;
		} else {
			imageFilename = getParameters()[0];
		}
		return super.transform(srcElement, curElement);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations.AbstractLabelAndHelpOperation#appendControl(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	protected void appendControl(Element srcElement, Element parentElement) {
		//append input
		Element inputElement = appendChildElement("input", parentElement); //$NON-NLS-1$
		appendAttribute(inputElement, "size", //$NON-NLS-1$
				String.valueOf(getColumns(srcElement)));
		appendAttribute(inputElement, "type", "text"); //$NON-NLS-1$ //$NON-NLS-2$
		String value = srcElement.getAttribute("value"); //$NON-NLS-1$
		if (value != null) {
			appendAttribute(inputElement, "value", value); //$NON-NLS-1$
		}
		appendAttribute(inputElement, "style", //$NON-NLS-1$
				calculateStyle(STYLE_CONTROLELEMENT, srcElement, "contentStyle")); //$NON-NLS-1$

		//append anchor with child image
		Element aElement = appendChildElement("a", parentElement); //$NON-NLS-1$
		appendAttribute(aElement, "href", "#"); //$NON-NLS-1$ //$NON-NLS-2$
		Element imgElement = appendChildElement("img", aElement); //$NON-NLS-1$
		appendAttribute(imgElement, "align", "absmiddle"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(imgElement, "border", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(imgElement, "src", imageFilename); //$NON-NLS-1$
		appendAttribute(imgElement, "style", "margin-left:4px;"); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
