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

import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;

/**
 * Quick Edit Tab Descriptor
 */
public class QuickEditTabDescriptor extends AbstractTabDescriptor {

	/**
	 * Quick Edit tab id
	 */
	public static final String TAB_ID = "jst.pagedesigner.tabbed.properties.quickedit"; //$NON-NLS-1$
	
	/**
	 * Constructor
	 */
	public QuickEditTabDescriptor(){
		super();
		getSectionDescriptors().add(new MDQuickEditTabSectionDescriptor());
	}
	
	public String getCategory() {
		return "quickedit"; //$NON-NLS-1$
	}

	public String getId() {
		return TAB_ID;
	}

	public String getLabel() {
		return Messages.QuickEditTabDescriptor_label;
	}

}
