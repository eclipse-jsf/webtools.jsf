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
import org.eclipse.jst.jsf.facesconfig.ui.EditorResources;
import org.eclipse.jst.jsf.facesconfig.ui.IconResources;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowLinkEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowElementEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowElementTreeEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.swt.graphics.Image;

/**
 * This class defines label provider for the property view's title. Tabbed
 * property view has a common area as a Title. Different decription and image
 * can be shown according to Different selection.
 * 
 */
public class PageflowSectionLabelProvider extends LabelProvider {
	/** prefix string for resource bundle */
	private static final String PAGEFLOW_MODEL_ITEMS_PREFIX = "Pageflow.Model.Items.";

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
				IconResources.getString("Pageflow.pageflow.small"));

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
				String className = ((PageflowElement) ((AbstractEditPart) pageflow)
						.getModel()).eClass().getName();
				result = EditorResources.getInstance().getString(
						PAGEFLOW_MODEL_ITEMS_PREFIX + className);
			}
		}
		return result;
	}

}
