/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.properties.internal;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidationMessage;

/**
 * Adapts IValidValues to ICellEditorValidator
 *
 */
public class EditorValidatorAdapter implements ICellEditorValidator, IInputValidator {

	private IValidValues _vvs;

	/**
	 * Constructor
	 * @param vvs
	 */
	public EditorValidatorAdapter(IValidValues vvs) {
		_vvs = vvs;
	}

	public String isValid(Object value) {
		//for now, if value is not a string, return true
		if (value instanceof String)
			return isValid((String)value);
		return null;
	}
	
	public String isValid(String value) {
		_vvs.getValidationMessages().clear();
		if (_vvs.isValidValue(value))
			return null;//is valid

		return ((IValidationMessage)_vvs.getValidationMessages().get(0)).getMessage();

	}

}
