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
package org.eclipse.jst.pagedesigner.properties.internal;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ISupportTextValue;
import org.eclipse.jst.pagedesigner.commands.single.ChangeAttributeCommand;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.jst.pagedesigner.properties.BaseCustomSection;
import org.eclipse.jst.pagedesigner.properties.attrgroup.AttributeGroupMessages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;

/**
 * This is a section for a list of attribute dialog fields.
 * 
 * @author mengbo
 * @version 1.5
 */
public class AttributeGroupSection extends BaseCustomSection {
	private static final Object KEY_ATTR = "KEY_ATTR"; //$NON-NLS-1$
	private IDialogFieldApplyListener _fieldApplyListener = new IDialogFieldApplyListener() {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldApplyListener#dialogFieldApplied(org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField)
		 */
		public void dialogFieldApplied(DialogField field) {
			Object attr = field.getAttachedData(KEY_ATTR);
			if (attr instanceof IPropertyPageDescriptor && _element != null) {
				ISupportTextValue textValue = (ISupportTextValue) field;
				ChangeAttributeCommand c = new ChangeAttributeCommand(
						AttributeGroupMessages
								.getString("AttributeGroupSection.changeAttribute"), _element, ((IPropertyPageDescriptor) attr).getAttributeName(), textValue.getText()); //$NON-NLS-1$
				c.execute();
			}
		}
	};

	private AttributeGroup _group;

	/**
	 * Constructor.  Create the section with a default AttributeGroup. In default
	 * AttributeGroup, there is no relationship between fields.
	 * 
	 * @param tagEntity
	 * @param attrNames
	 */
	public AttributeGroupSection(Entity tagEntity, List<String> attrNames) {
		this(new AttributeGroup(tagEntity, attrNames));
	}

	/**
	 * Constructor.  In case the group is not a default group (e.g. you may add some
	 * customized relationship between the fields).
	 * 
	 * @param group
	 */
	public AttributeGroupSection(AttributeGroup group) {
		_group = group;
		_group.setDefaultApplyListener(_fieldApplyListener);
//		_group.initialize();
	}

	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		_group.setElementContext(_element, _element);
	}

	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		TabbedPropertySheetWidgetFactory factory = aTabbedPropertySheetPage
				.getWidgetFactory();
		_group.layoutDialogFields(factory, parent);
	}

	protected void notifyChanged(INodeNotifier notifier, int eventType,
			Object changedFeature, Object oldValue, Object newValue, int pos) {
		if (_group != null) {
			_group.refreshData();
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		_group.reset();
	}
	
	/**
	 * NOT API - for JUnit testing only
	 * @return @link{AttributeGroup}
	 */
	public AttributeGroup getAttributeGroup() {		
		return _group;
	}
}
