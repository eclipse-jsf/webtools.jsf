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

import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.apache.trinidad.tagsupport.model.SelectItem;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.model.SelectItemModel;
import org.w3c.dom.Element;

/**
 * ITransformOperation implementation specifically for "selectManyCheckbox" and
 * "selectOneRadio" JSF Elements.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class SelectManyCheckboxOneRadioOperation extends AbstractLabelAndHelpOperation {

	private String inputType;

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations.AbstractLabelAndHelpOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public Element transform(Element srcElement, Element curElement) {
		if (getParameters().length < 1) {
			getLog().error("Warning.TransformOperationFactory.TooFewParameters", getTransformOperationID()); //$NON-NLS-1$
			return null;
		} else {
			inputType = getParameters()[0];
		}
		return super.transform(srcElement, curElement);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations.AbstractLabelAndHelpOperation#appendControl(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	protected void appendControl(Element srcElement, Element parentElement) {
		Element spanElement = appendChildElement("span", parentElement); //$NON-NLS-1$
		String layout = srcElement.getAttribute("layout"); //$NON-NLS-1$
		List<SelectItem> selectItems = SelectItemModel.getModel(srcElement);
		Iterator<SelectItem> itSelectItems = selectItems.iterator();
		while (itSelectItems.hasNext()) {
			SelectItem selectItem = itSelectItems.next();
			Element inputElement = appendChildElement("input", spanElement); //$NON-NLS-1$
			appendAttribute(inputElement, "type", inputType); //$NON-NLS-1$
			Element labelElement = appendChildElement("label", spanElement); //$NON-NLS-1$
			appendChildText(selectItem.getLabel(), labelElement);
			if (layout == null || !layout.equals("horizontal")) { //$NON-NLS-1$
				if (itSelectItems.hasNext()) {
					appendChildElement("br", spanElement); //$NON-NLS-1$
				}
			}
		}
		appendAttribute(spanElement, "style", //$NON-NLS-1$
				calculateStyle(STYLE_CONTROLELEMENT, srcElement, "contentStyle")); //$NON-NLS-1$
	}

}
