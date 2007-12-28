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
package org.eclipse.jst.pagedesigner.properties.attrgroup;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroupPage;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ISupportTextValue;
import org.eclipse.jst.jsf.common.ui.internal.dialogs.CommonWizardDialog;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.single.AddSubNodeCommand;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.jst.pagedesigner.properties.internal.AttributeGroup;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class DialogUtil {
	/**
	 * Utility method, this method will popup a dialog for user to input
	 * attributes for initialize a sub element. In this method will create a
	 * command and execute it.
	 * 
	 * @param shell
	 *            parent shell for dialog
	 * @param parent
	 *            the parent element
	 * @param group
	 *            the AttributeGroup
	 * @return true if success, false if user canceled.
	 */
	public static boolean createSubElement(Shell shell,
			final IDOMElement parent, final AttributeGroup group) {
		group.setElementContext(parent, parent);//2ndParam seems to need to be parent most of the time... don't know why it WAS null... changed 12/19/07
		
		if (group.getDialogFields().length == 0){
			//don't show dialog
			createSubNode(group, parent, Collections.EMPTY_MAP);
			return true;
		}
		
		final DialogFieldGroupPage page = new DialogFieldGroupPage("", group); //$NON-NLS-1$
		page.setTitle(AttributeGroupMessages.getString(
				"DialogUtil.createTitle", group.getTagName())); //$NON-NLS-1$
		page
				.setDescription(AttributeGroupMessages
						.getString(
								"DialogUtil.createDescription", group.getTagName(), parent.getTagName())); //$NON-NLS-1$

		Wizard wizard = new Wizard() {
			public void addPages() {
				super.addPage(page);
			}

			public boolean performFinish() {
				DialogField[] fields = group.getDialogFields();
				Map map = new HashMap();
				for (int i = 0; i < fields.length; i++) {
					IPropertyPageDescriptor desc = group
							.getPropertyDescriptor(fields[i]);
					if (desc != null && fields[i] instanceof ISupportTextValue) {
						String value = ((ISupportTextValue) fields[i])
								.getText();
						if (value != null && value.length() > 0) {
							map.put(desc.getAttributeName(), value);
						}
					}
				}
				createSubNode(group, parent, map);
				return true;
			}
		};
		wizard.setWindowTitle(AttributeGroupMessages.getString(
				"DialogUtil.createTitle", group.getTagName())); //$NON-NLS-1$
		wizard.setDefaultPageImageDescriptor(PDPlugin.getDefault()
				.getImageDescriptor("newsuade_wiz.gif")); //$NON-NLS-1$
		CommonWizardDialog dialog = new CommonWizardDialog(shell, wizard);

		return dialog.open() == Window.OK;
	}

	private static void createSubNode(final AttributeGroup group, final IDOMElement parent, final Map mapOfAttributeValues) {
		AddSubNodeCommand addSubCommand = new AddSubNodeCommand(
				AttributeGroupMessages
						.getString(
								"DialogUtil.createCommandLabel", group.getTagName()), parent, group.getTagName(), group.getURI(), mapOfAttributeValues); //$NON-NLS-1$
		addSubCommand.execute();
		
	}

	/**
	 * Utility method, this method will popup a dialog for user to input
	 * attributes for initialize a sub element. In this method will create a
	 * command and execute it.
	 * 
	 * @param shell
	 *            parent shell for dialog
	 * @param parent
	 *            parent element
	 * @param uri
	 *            new ele's uri
	 * @param tagName
	 *            new ele's tag name
	 * @param attributes
	 *            an array of attribute names. The dialog will create a
	 *            dialogfield for each of them to allow user to input initial
	 *            value. If null, the system will try to use all attribute of
	 *            the element.
	 * @return true if success, false if user canceled.
	 */
	public static boolean createSubElement(Shell shell,
			final IDOMElement parent, final String uri, final String tagName,
			final String[] attributes) {
		
		final 	AttributeGroup group = new AttributeGroup(uri, tagName,
				attributes);

		return createSubElement(shell, parent, group);
	}
}
