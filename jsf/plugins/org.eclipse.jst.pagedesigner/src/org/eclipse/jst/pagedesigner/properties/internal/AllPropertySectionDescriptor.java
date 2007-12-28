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

import org.eclipse.jst.pagedesigner.properties.AllPropertySection;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;

/**
 * SectionDescriptor for the WPE Attributes tab
 */
public class AllPropertySectionDescriptor extends AbstractSectionDescriptor {
	/**
	 * Id for the WPE AllPropertySectionDescriptor
	 */
	public static final String ID = "AllPropertySectionDescriptor"; //$NON-NLS-1$

	private ISection section;

	public String getId() {
		return ID;
	}

	public ISection getSectionClass() {
		if (section == null){
			section = new AllPropertySection();
		}
		return section;
	}

	public String getTargetTab() {
		return AttributesTabDescriptor.TAB_ID;
	}

}
