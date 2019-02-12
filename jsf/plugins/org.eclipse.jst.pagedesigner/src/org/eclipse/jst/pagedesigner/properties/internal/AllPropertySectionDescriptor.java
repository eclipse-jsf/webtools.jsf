/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
