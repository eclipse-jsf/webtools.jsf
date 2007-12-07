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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.tabbed.ISection;

/**
 * QuickEdit Tab section when quick edit section info is not available.
 * 
 * Ideally we should not be displaying the tab in the absence of MD, but due to current 
 * issues with the tabbed-properties framework, it is easier to simply display a "null"
 * section. 
 *
 */
public class NullQuickEditTabGroupDescriptor extends
		QuickEditTabSectionsDescriptor {

	private List<ISection> nullQuickEditSection;
	
	/**
	 * Constructor
	 */
	public NullQuickEditTabGroupDescriptor() {
		super();
	}

	@Override
	public List<ISection> getSections() {
		return nullQuickEditSection;
	}
	
	@Override
	public void calculateSections() {
		nullQuickEditSection = new ArrayList<ISection>(1);
		nullQuickEditSection.add(new NullQuickEditTabSection());
	}
	
	
}
