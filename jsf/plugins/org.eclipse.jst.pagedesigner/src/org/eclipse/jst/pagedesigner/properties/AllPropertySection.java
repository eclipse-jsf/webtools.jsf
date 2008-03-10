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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * Mainly copied from AdvancedPropertySection. But extend it to allow setting
 * PropertySourceProvider.
 * 
 * @author mengbo
 */
public class AllPropertySection extends AbstractPropertySection {
	// FIXME: workaround the eclipse properties view limitation of sorting
	// category.
	private AttributePropertySheetPage page;

	/**
	 * IPropertySourceProvider for this section
	 */
	protected IPropertySourceProvider _provider;

	/**
	 * selected tag IDOMElement 
	 */
	protected IDOMElement _element;

	/**
	 * The INodeAdapter to use for notification of model change
	 */
	protected INodeAdapter _adapter = new INodeAdapter() {
		public boolean isAdapterForType(Object type) {
			return false;
		}

		public void notifyChanged(INodeNotifier notifier, int eventType,
				Object changedFeature, Object oldValue, Object newValue, int pos) {
			refresh();
		}
	};

	/**
	 * Constructor
	 */
	public AllPropertySection() {
		this.setPropertySourceProvider(new AttributePropertySourceProvider());
	}

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		
	
		Composite composite = getWidgetFactory()
				.createFlatFormComposite(parent);
		page = new AttributePropertySheetPage();
		page.init(tabbedPropertySheetPage.getSite());

		if (_provider != null) {
			page.setPropertySourceProvider(_provider);
		}

		page.createControl(composite);
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100, 0);
		data.height = 100;
		data.width = 100;
		page.getControl().setLayoutData(data);
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		IDOMElement newEle = (IDOMElement) DesignerPropertyTool.getElement(
				part, selection);

		if (_element != newEle && page != null) {
			super.setInput(part, selection);
			page.selectionChanged(part, selection);
			if (_element != null) {
				_element.removeAdapter(_adapter);
			}
			_element = newEle;
			if (_element != null) {
				_element.addAdapter(_adapter);
			}
		}
	}

	@Override
	public void dispose() {
		super.dispose();

		if (_element != null) {
			_element.removeAdapter(_adapter);
		}
		if (page != null) {
			page.dispose();
			page = null;
		}
	}

	@Override
	public void refresh() {
		if (page != null)
			page.refresh();
	}

	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	/**
	 * Set provider into tabbedPropertiesPage
	 * @param provider
	 */
	public void setPropertySourceProvider(IPropertySourceProvider provider) {
		_provider = provider;
		if (page != null){
			page.setPropertySourceProvider(_provider);

		}
	}

}
