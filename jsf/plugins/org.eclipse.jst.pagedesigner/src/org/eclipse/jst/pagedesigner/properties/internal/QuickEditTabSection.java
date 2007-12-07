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

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.properties.WPETabbedPropertySheetPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Using the tag entity's QuickEditTabSections meta-data, this section reacts to changes in selection,
 * and will dynamically create a QuickEditTabGroup.   The QuickEditTabGroup is cached and reused.
 * <br><br>
 * This section delegates construction to the sections discovered thru meta data.   
 * Because this section is not disposed of until the tab is disposed, this section will enforce the expected section lifecycle
 * on the sections loaded from meta data.   This occurs during setInput.  But will pass on all section lifecycle events as 
 * this section receives them.
 * <br><br>
 * The lifecycle that this section enforces on it's child sections in the setInput call on this section are (in order):
 * 	<li>createControls
 * 	<li>setInput
 *  <li>aboutToBeShown
 *  <li>refresh
 *  
 * When tab section lifecycle events occur to this section, they are passed on to all child sections also. 
 * 	
 */
public class QuickEditTabSection extends AbstractPropertySection {
	
	private QuickEditTabManager manager;
	private Composite _composite;
	private Composite _qeGroupComposite;
	private WPETabbedPropertySheetPage _tabbedPropertySheetPage;

	private QuickEditTabManager getTabManager() {
		if (manager == null) {
			manager = _tabbedPropertySheetPage.getTabManager();
		}
		return manager;
	}

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		_composite = parent;
		_tabbedPropertySheetPage = (WPETabbedPropertySheetPage)tabbedPropertySheetPage;
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		if (getTabManager() != null){
			aboutToBeHidden();
			createOrResetQuickEditGroupComposite();//disposes of old and recreates new topComp
			getTabManager().selectionChanged(part, selection);
			for (ISection section : getSections()){
				section.createControls(_qeGroupComposite, _tabbedPropertySheetPage);
				section.setInput(part, selection);
			}
			_composite.getParent().layout(true, true);
			
			aboutToBeShown();
			refresh();
		}			
	}

	@Override
	public void aboutToBeHidden() {
		super.aboutToBeHidden();
		for (ISection section : getSections()){
			section.aboutToBeHidden();
		}
	}

	@Override
	public void aboutToBeShown() {
		super.aboutToBeShown();
		for (ISection section : getSections()){
			section.aboutToBeShown();
		}
	}
	
	@Override
	public void refresh() {
		super.refresh();
		for (ISection section : getSections()){
			section.refresh();
		}
	}

	private void createOrResetQuickEditGroupComposite() {
		if (_qeGroupComposite != null && !_qeGroupComposite.isDisposed()){
			//dispose of current sections
			disposeCurrentQuickEditTabSections();
			_qeGroupComposite.dispose();
		}			
		
		_qeGroupComposite = _tabbedPropertySheetPage.getWidgetFactory().createComposite(_composite, SWT.NO_FOCUS);
		QuickEditTabLayout layout = new QuickEditTabLayout();
		_qeGroupComposite.setLayout(layout);
		
	}

	private void disposeCurrentQuickEditTabSections() {		
		for (ISection section : getSections()){
			section.dispose();
		}
	}

	private List<ISection> getSections(){
		if (getTabManager().getCurrentTabGroupDescriptor() != null)
			return getTabManager().getCurrentTabGroupDescriptor().getSections();
		
		return Collections.EMPTY_LIST;
	}
	
	public void dispose() {
		super.dispose();
		disposeCurrentQuickEditTabSections();
		manager.dispose();
		manager = null;
		_composite = null;
		_qeGroupComposite = null;
		_tabbedPropertySheetPage = null;
	}	

	public boolean shouldUseExtraSpace() {
		return false;
	}

	private class QuickEditTabLayout extends Layout {

		// allow for adjustments
		private static final int MARGIN = 0;
		private static final int SPACING = 0;

		// cache
		Point[] sizes;
		int maxWidth, totalHeight;

		protected Point computeSize(Composite composite, int wHint, int hHint,
				boolean flushCache) {

			Control children[] = composite.getChildren();
			if (flushCache || sizes == null || sizes.length != children.length) {
				initialize(children);
			}

			int width = wHint, height = hHint;
			if (wHint == SWT.DEFAULT)
				width = maxWidth;

			if (hHint == SWT.DEFAULT)
				height = totalHeight;

			return new Point(width + 2 * MARGIN, height + 2 * MARGIN);
		}

		protected void layout(Composite composite, boolean flushCache) {
			Control children[] = composite.getChildren();
			if (flushCache || sizes == null || sizes.length != children.length) {
				initialize(children);
			}
			Rectangle rect = composite.getClientArea();
			int x = MARGIN, y = MARGIN;
			int width = Math.max(rect.width - 2 * MARGIN, maxWidth);
//			System.out.println("--- Comp id: "+composite.toString()+ "[#Children: "+ composite.getChildren().length +"] -------");
			for (int i = 0; i < children.length; i++) {
				int height = sizes[i].y;
				children[i].setBounds(x, y, width, height);
				y += height + SPACING;
//				System.out.println("h="+height+", y="+y);
			}
		    composite.setRedraw(true);
		}

		void initialize(Control children[]) {
			maxWidth = 0;
			totalHeight = 0;
			sizes = new Point[children.length];
			for (int i = 0; i < children.length; i++) {
				sizes[i] = children[i].computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
				maxWidth = Math.max(maxWidth, sizes[i].x);
				totalHeight += sizes[i].y;
			}
			totalHeight += (children.length - 1) * SPACING;
		}
	}
}
