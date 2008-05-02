/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.tests.tabbed.properties.sections;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldChangeListener;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ISupportTextValue;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringDialogField;
import org.eclipse.jst.pagedesigner.jsf.ui.attributegroup.DialogFieldFactory;
import org.eclipse.jst.pagedesigner.properties.BaseCustomSection;
import org.eclipse.jst.pagedesigner.properties.DesignerPropertyTool;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * Fake section for testing.... remove
 * Binds to "type" attribute if present on element
 *
 */
public class FakePropertySectionUsingDialogField extends BaseCustomSection {
//
//	private Text txt;
	private Attr typeAttr;
	private DialogField field;
	private TabbedPropertySheetWidgetFactory factory;
	private Composite outer;
	/**
	 * Constructor
	 */
	public FakePropertySectionUsingDialogField() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControls(final Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
	
		super.createControls(parent, tabbedPropertySheetPage);
		factory = tabbedPropertySheetPage.getWidgetFactory();

		outer = factory.createComposite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 3;
		gl.marginTop = 0;
		gl.marginBottom = 0;
		outer.setLayout(gl);

		field = new StringDialogField();
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		field.doFillIntoGrid(factory, outer, 3);
		((StringDialogField)field).getTextControl(factory, outer).setLayoutData(gd);
		field.setLabelText("TYPETESTDIALOGFIELD:");
		 
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Element node = DesignerPropertyTool.getElement(part, selection);
		if (node != null){
			typeAttr = node.getAttributeNode("type");
			if (typeAttr != null){
				DialogFieldFactory.setDialogFieldValue(field, typeAttr.getValue());
				field.setDialogFieldApplyListener(new IDialogFieldApplyListener(){

					public void dialogFieldApplied(DialogField field) {
						typeAttr.setValue(((ISupportTextValue)field).getText());
					}
				});
				field.setDialogFieldChangeListener(new IDialogFieldChangeListener(){

					public void dialogFieldChanged(DialogField field) {
							//
					}
				});				

			};
		}
	}

	@Override
	protected void notifyChanged(INodeNotifier notifier, int eventType,
			Object changedFeature, Object oldValue, Object newValue, int pos) {
		if (field != null && changedFeature.toString().equalsIgnoreCase("type") ){
			((ISupportTextValue)field).setTextWithoutUpdate((String)newValue);
		}
		
	}

}
