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
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.PropertyResources;
import org.eclipse.ui.views.properties.IPropertySource;

public class SetPropertyValueCommand extends Command {
	protected static PropertyResources resource = PropertyResources
			.getInstance();

	protected Object propertyValue;

	protected Object propertyID;

	protected Object undoValue;

	protected boolean resetOnUndo;

	protected IPropertySource target;

	public SetPropertyValueCommand() {
		super(""); //$NON-NLS-1$
	}

	public SetPropertyValueCommand(String propLabel) {
		super(MessageFormat.format(
				resource.getString("property.Command.SetValue"),
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

	public IPropertySource getTarget() {
		return target;
	}

	public void setTarget(IPropertySource aTarget) {
		target = aTarget;
	}

	public void redo() {
		execute();
	}

	public void setPropertyId(Object pName) {
		propertyID = pName;
	}

	public void setPropertyValue(Object val) {
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
