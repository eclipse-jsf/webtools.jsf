/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.properties.internal;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;

/**
 * Quick Edit section descriptor that uses the quickEditSection trait meta data for the tag entity of the selection
 * to determine the sections to display
 *
 */
public class MDQuickEditTabSectionDescriptor extends AbstractSectionDescriptor {
	
	/**
	 * Default constructor
	 */
	public MDQuickEditTabSectionDescriptor() {
		super();
	}
	
	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		return true;// we will always display tab, but will show text that no quick edit properties are available when they aren't
	}
	
	public ISection getSectionClass() {
		return new  QuickEditTabSection();
	}

	public String getTargetTab() {
		return QuickEditTabDescriptor.TAB_ID;
	}

	public String getId() {
		return "mdQuickEditTabSections"; //$NON-NLS-1$
	}
	
}
