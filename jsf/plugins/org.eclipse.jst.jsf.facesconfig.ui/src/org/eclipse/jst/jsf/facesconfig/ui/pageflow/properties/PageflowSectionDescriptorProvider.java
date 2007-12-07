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

import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptorProvider;

/**
 * this class defines all section descriptors for pageflow. Similar with
 * PropertyDescriptors, this class defines all sections used by pageflow through
 * SectionDescriptor.
 * 
 */
public class PageflowSectionDescriptorProvider implements
		ISectionDescriptorProvider {
	/**
	 * Default constructor
	 */
	public PageflowSectionDescriptorProvider() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ISectionDescriptorProvider#getSectionDescriptors()
	 */
	public ISectionDescriptor[] getSectionDescriptors() {
		ISectionDescriptor[] descriptors = new ISectionDescriptor[] {
				new LinkSectionDescriptor(), new PageSectionDescriptor(),
				new EditorSectionDescriptor(),
				new GeneralViewSectionDescriptor() };
		return descriptors;
	}

}
