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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.section;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.DialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.IDialogFieldChangeListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;

/**
 * This is base section, which support GEF command stack to redo/undo setting
 * properties.
 * 
 * @author jchoi, Xiao-guang Zhang
 */
public abstract class AbstractEditPartSection extends AbstractPropertySection {

	IDialogFieldChangeListener changeListener = new IDialogFieldChangeListener() {
		public void dialogFieldChanged(DialogField field) {
			validate();
		}
	};

	/** property source of edit part */
	private IPropertySource propertySource;

	/** EditPart instance */
	private EditPart editPart;

	/** map for property registry to store a map between the property id and name */
	private Map propertyRegistry;

	/** GEF command stack */
	private CommandStack commandStack;

	/** command stack listener to refresh the section */
	private CommandStackListener commandStackListener;

	/**
	 * 
	 */
	public AbstractEditPartSection() {
		super();
	}

	/**
	 * refresh the whole section
	 * 
	 */
	public void refreshData() {
		super.refresh();
	}

	/**
	 * TODO: this method seems unused
	 */
	public abstract void validate();

	/**
	 * dispose the command stack listener
	 */
	public void dispose() {
		super.dispose();
		if (getCommandStack() != null) {
			getCommandStack().removeCommandStackListener(
					getCommandStackListener());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		if (part != null) {
			if (part.getAdapter(CommandStack.class) != null) {
				setCommandStack((CommandStack) part
						.getAdapter(CommandStack.class));
			}

			if (selection instanceof StructuredSelection) {
				StructuredSelection structuredSelection = (StructuredSelection) selection;
				if (structuredSelection.getFirstElement() instanceof EditPart) {
					editPart = (EditPart) structuredSelection.getFirstElement();
				}

				if (editPart != null
						&& ((IAdaptable) editPart)
								.getAdapter(IPropertySource.class) != null) {
					propertySource = (IPropertySource) ((IAdaptable) editPart)
							.getAdapter(IPropertySource.class);
				}

			}
		}

		super.setInput(part, selection);
	}

	/**
	 * get the commandstacklistener, if not existed, create a new one.
	 * 
	 * @return
	 */
	private CommandStackListener getCommandStackListener() {
		if (commandStackListener == null) {
			commandStackListener = new CommandStackListener() {
				public void commandStackChanged(EventObject e) {
					refreshData();
				}
			};
		}
		return commandStackListener;
	}

	/**
	 * set a <code>CommandStack</code> forthis section, and add a
	 * <code>CommandStackListener</code> for this commandstack
	 * 
	 * @param stack
	 */
	public void setCommandStack(CommandStack stack) {
		commandStack = stack;
		stack.addCommandStackListener(getCommandStackListener());
	}

	/**
	 * get the <code>CommandStack</code>
	 * 
	 * @return
	 */
	public CommandStack getCommandStack() {
		return commandStack;
	}

	/**
	 * get the property source registered for current editpart
	 * 
	 * @return
	 */
	public IPropertySource getPropertySource() {
		return propertySource;
	}

	/**
	 * get the property's EMF featureID based on property name.
	 * 
	 * @param propertyName
	 * @return
	 */
	protected Object getFeatureID(String propertyName) {
		if (editPart != null && !(editPart.getModel() instanceof EObject)) {
			return null;
		}

		Object propertyID = getPropertyRegistry().get(propertyName);
		if (propertyID == null) {

			EClass cls = ((EObject) editPart.getModel()).eClass();
			for (Iterator it = cls.getEAllAttributes().iterator(); it.hasNext();) {
				EAttribute attr = (EAttribute) it.next();
				if (attr.getName().equalsIgnoreCase(propertyName)) {
					propertyID = Integer.toString(attr.getFeatureID());
					getPropertyRegistry().put(propertyName, propertyID);
					break;
				}
			}
		}
		return propertyID;
	}

	/**
	 * get the property registry
	 * 
	 * @return
	 */
	protected Map getPropertyRegistry() {
		if (propertyRegistry == null) {
			propertyRegistry = new HashMap();
		}
		return propertyRegistry;
	}

	/**
	 * get the input edit part
	 * 
	 * @return
	 */
	public Object getInput() {
		return editPart;
	}

	/**
	 * set the value of property
	 * 
	 * @param propertyName
	 * @param propertyValue
	 */
	public void setValue(String propertyName, Object propertyValue) {
		if (propertyName != null && propertyValue != null
				&& getPropertySource() != null) {
			SetPropertyValueCommand setCommand = new SetPropertyValueCommand(
					propertyName);
			setCommand.setTarget(getPropertySource());
			setCommand.setPropertyId(getFeatureID(propertyName));
			setCommand.setPropertyValue(propertyValue);
			if (getCommandStack() != null) {
				getCommandStack().execute(setCommand);
			} else {
				setCommand.execute();
			}
		}
	}
}
