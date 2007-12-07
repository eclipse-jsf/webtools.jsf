/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.properties;

import org.eclipse.jst.pagedesigner.meta.internal.CategoryNameComparator;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetSorter;

/**
 * In PropertySheetPage, the <code>setSorter</code> is protected. Creating
 * this class to make setSorter accessible to us.
 * 
 * @author mengbo
 * @version 1.5
 */
public class AttributePropertySheetPage extends PropertySheetPage {
//	TODO: add actions
//	public void makeContributions(IMenuManager menuManager,
//			IToolBarManager toolBarManager, IStatusLineManager statusLineManager) {
//		super.makeContributions(menuManager, toolBarManager, statusLineManager);
//		//add actions here
//			hide/show all categories
//			collapse
//			remove
//	}

	/**
	 * Use my sorter to sort the category name. Only override the
	 * compareCategories method.
	 * 
	 * @author mengbo
	 * @version 1.5
	 */
	private static class MySorter extends PropertySheetSorter {
		public int compareCategories(String categoryA, String categoryB) {
			return CategoryNameComparator.getInstance().compare(categoryA,
					categoryB);
		}
	}

	/**
	 * Constructor
	 */
	public AttributePropertySheetPage() {
		super();
		setSorter(new MySorter());
	}

//	public void createControl(Composite parent) {
//		super.createControl(parent);
////		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(),
////				PDPlugin.getResourceString("MyPropertySheetPage.help.id"));
//	}
}
