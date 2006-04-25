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

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PFLinkEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.section.LinkSection;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowValidation;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.wst.common.ui.properties.internal.provisional.ISection;
import org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor;
import org.eclipse.wst.common.ui.properties.internal.provisional.ITypeMapper;

/**
 * @author jchoi
 * @version
 */
public class LinkSectionDescriptor implements ISectionDescriptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#getId()
	 */
	public String getId() {
		return ITabbedPropertiesConstants.LINK_SECTION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#getFilter()
	 */
	public ITypeMapper getFilter() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#getInputTypes()
	 */
	public List getInputTypes() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#getSectionClass()
	 */
	public ISection getSectionClass() {
		return new LinkSection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#getTargetTab()
	 */
	public String getTargetTab() {
		return ITabbedPropertiesConstants.ATTRIBUTE_TAB_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#appliesTo(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		Object object = null;
		if (selection instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) selection;
			object = structuredSelection.getFirstElement();
			if (object instanceof PFLinkEditPart) {
				PFLink pfLink = (PFLink) ((PFLinkEditPart) object).getModel();
				if (PageflowValidation.getInstance().isValidLinkForProperty(
						pfLink)) {

					return true;
				}

			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#getAfterSection()
	 */
	public String getAfterSection() {
		return ITabbedPropertiesConstants.TOP_SECTION;
	}

	public int getEnablesFor() {
		// TODO Auto-generated method stub
		return ENABLES_FOR_ANY;
	}

}
