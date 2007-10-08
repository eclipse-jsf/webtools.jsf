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

import java.text.MessageFormat;

import org.eclipse.gef.commands.Command;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.PropertyMessages;
import org.eclipse.ui.views.properties.IPropertySource;

/*package*/ class SetPropertyValueCommand extends Command {

	private Object propertyValue;

	private Object propertyID;

	private Object undoValue;

	private boolean resetOnUndo;

	private IPropertySource target;

	/**
	 * @param propLabel
	 */
	public SetPropertyValueCommand(String propLabel) {
		super(MessageFormat.format(
				PropertyMessages.property_Command_SetValue,
				new Object[] { propLabel }).trim());
	}

	public boolean canExecute() {
		return true;
	}

	public void execute() {
		boolean wasPropertySet = getTarget().isPropertySet(propertyID);
		undoValue = getTarget().getPropertyValue(propertyID);
		if (undoValue instanceof IPropertySource) {
			undoValue = ((IPropertySource) undoValue).getEditableValue();
		}
		if (propertyValue instanceof IPropertySource) {
			propertyValue = ((IPropertySource) propertyValue)
					.getEditableValue();
		}
		getTarget().setPropertyValue(propertyID, propertyValue);
		resetOnUndo = wasPropertySet != getTarget().isPropertySet(propertyID);
		if (resetOnUndo) {
			undoValue = null;
		}
	}

	/**
	 * @return the target
	 */
	private IPropertySource getTarget() {
		return target;
	}

	void setTarget(IPropertySource aTarget) {
		target = aTarget;
	}

	public void redo() {
		execute();
	}

	void setPropertyId(Object pName) {
		propertyID = pName;
	}

	void setPropertyValue(Object val) {
		propertyValue = val;
	}

	public void undo() {
		if (resetOnUndo) {
			getTarget().resetPropertyValue(propertyID);
		} else {
			getTarget().setPropertyValue(propertyID, undoValue);
		}
	}
}
