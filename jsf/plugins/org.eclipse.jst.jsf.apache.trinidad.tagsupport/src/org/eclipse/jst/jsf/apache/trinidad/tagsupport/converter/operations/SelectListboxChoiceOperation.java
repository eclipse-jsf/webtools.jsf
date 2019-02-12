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

import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.apache.trinidad.tagsupport.model.SelectItem;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.model.SelectItemModel;
import org.w3c.dom.Element;

/**
 * ITransformOperation implementation specifically for "selectManyListbox",
 * "selectOneListbox", and "selectOneChoice" JSF Elements.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class SelectListboxChoiceOperation extends AbstractLabelAndHelpOperation {

	private boolean isMultiple;
	private boolean showSize;

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations.AbstractLabelAndHelpOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public Element transform(Element srcElement, Element curElement) {
		if (getParameters().length < 2) {
			getLog().error("Warning.TransformOperationFactory.TooFewParameters", getTransformOperationID()); //$NON-NLS-1$
			return null;
		} else {
			isMultiple = Boolean.parseBoolean(getParameters()[0]);
			showSize = Boolean.parseBoolean(getParameters()[1]);
		}
		return super.transform(srcElement, curElement);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations.AbstractLabelAndHelpOperation#appendControl(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	protected void appendControl(Element srcElement, Element parentElement) {
		Element selectElement = appendChildElement("select", parentElement); //$NON-NLS-1$
		if (isMultiple) {
			appendAttribute(selectElement, "multiple", ""); //$NON-NLS-1$ //$NON-NLS-2$
		}
		List<SelectItem> selectItems = SelectItemModel.getModel(srcElement);
		if (showSize) {
			int size;
			String attrSize = srcElement.getAttribute("size"); //$NON-NLS-1$
			if (attrSize != null && attrSize.length() > 0) {
				try {
					size = Integer.parseInt(attrSize);
				} catch(NumberFormatException nfe) {
					size = 1;
				}
			} else {
				size = 1;
			}
			size = Math.max(size, selectItems.size());
			appendAttribute(selectElement, "size", String.valueOf(size)); //$NON-NLS-1$
		}
		boolean isFirstSelectItem = !isMultiple;
		Iterator<SelectItem> itSelectItems = selectItems.iterator();
		while (itSelectItems.hasNext()) {
			SelectItem selectItem = itSelectItems.next();
			Element optionElement = appendChildElement("option", selectElement); //$NON-NLS-1$
			if (isFirstSelectItem) {
				appendAttribute(optionElement, "selected", ""); //$NON-NLS-1$ //$NON-NLS-2$
				isFirstSelectItem = false;
			}
			appendChildText(selectItem.getLabel(), optionElement);
		}
		//append common attributes
		appendAttribute(selectElement, "class", STYLECLASS_CONTROLELEMENT); //$NON-NLS-1$
		String contentStyle = calculateStyle(null, srcElement, "contentStyle"); //$NON-NLS-1$
		if (contentStyle != null) {
			appendAttribute(selectElement, "style", contentStyle); //$NON-NLS-1$
		}
	}

}
