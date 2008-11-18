/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowElementEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowElementTreeEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowLinkEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.swt.graphics.Image;

/**
 * This class defines label provider for the property view's title. Tabbed
 * property view has a common area as a Title. Different decription and image
 * can be shown according to Different selection.
 * 
 */
public class PageflowSectionLabelProvider extends LabelProvider {

	private static final String PAGEFLOW_IMAGE_FILE = "facesconfig/FacesConfig_Pageflow16.gif"; //$NON-NLS-1$

	/**
	 * Default constructor
	 */
	public PageflowSectionLabelProvider() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see getImage(java.lang.Object)
	 */
	public Image getImage(Object object) {
		Image result = EditorPlugin.getDefault().getImage(
				PAGEFLOW_IMAGE_FILE);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see getText(java.lang.Object)
	 */
	public String getText(Object object) {
		String result = null;
		if (object instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) object;
			Object pageflow = structuredSelection.getFirstElement();
			if (pageflow instanceof PageflowElementEditPart
					|| pageflow instanceof PageflowLinkEditPart
					|| pageflow instanceof PageflowElementTreeEditPart) {

				if (((PageflowElement) ((AbstractEditPart) pageflow).getModel())
						.eClass() == PageflowPackage.eINSTANCE.getPageflow()) {
					result = PageflowMessages.Pageflow_Model_Items_Pageflow;
				} else if (((PageflowElement) ((AbstractEditPart) pageflow)
						.getModel()).eClass() == PageflowPackage.eINSTANCE
						.getPFPage()) {
					result = PageflowMessages.Pageflow_Model_Items_PFPage;
				} else if (((PageflowElement) ((AbstractEditPart) pageflow)
						.getModel()).eClass() == PageflowPackage.eINSTANCE
						.getPFLink()) {
					result = PageflowMessages.Pageflow_Model_Items_PFLink;
				}
			}
		}
		return result;
	}

}
