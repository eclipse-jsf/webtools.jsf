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
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.elementedit;

import org.eclipse.gef.EditPart;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.ITrinidadConstants;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.w3c.dom.Element;

/**
 * IElementEdit implementation for Trinidad tags that are potentially children
 * of the panelFormLayout tag, and therefore may need to refresh panelFormLayout
 * on model change.
 * 
 * @author Ian Trimble - Oracle
 */
public class PanelFormLayoutChildElementEdit extends
		DefaultTrinidadCoreElementEdit {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit#handleModelChange(org.w3c.dom.Element, org.eclipse.jst.pagedesigner.parts.ElementEditPart, boolean)
	 */
	@Override
	public boolean handleModelChange(Element ele, ElementEditPart part,
			boolean recursive) {
		boolean handled = false;
		EditPart parentPart = part.getParent();
		while (parentPart instanceof ElementEditPart) {
			ElementEditPart parentElementEditPart = (ElementEditPart)parentPart;
			TagIdentifier tagID = parentElementEditPart.getTagIdentifier();
			if (tagID != null) {
				if (ITrinidadConstants.TAG_IDENTIFIER_PANELFORMLAYOUT.isSameTagType(tagID)) {
					parentElementEditPart.refreshModelChange(recursive);
					handled = true;
					break;
				} else if (
						ITrinidadConstants.TAG_IDENTIFIER_GROUP.isSameTagType(tagID) ||
						IJSFConstants.TAG_IDENTIFIER_FACET.isSameTagType(tagID)) {
					parentPart = parentPart.getParent();
				} else {
					break;
				}
			}
		}
		return handled;
	}

}
