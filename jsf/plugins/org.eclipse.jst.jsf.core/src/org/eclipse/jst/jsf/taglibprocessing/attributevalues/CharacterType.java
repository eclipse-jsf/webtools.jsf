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
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.metadataprocessors.AbstractRootTypeDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidationMessage;
import org.eclipse.jst.jsf.metadataprocessors.features.ValidationMessage;

/**
 * Represents a single unicode character
 */
public class CharacterType extends AbstractRootTypeDescriptor implements
		IMetaDataEnabledFeature, IValidValues{

	private List _msgs;

	/**
	 * Constructor
	 */
	public CharacterType() {
		super();
	}

	public List<IValidationMessage> getValidationMessages() {
		if (_msgs == null){
			_msgs = new ArrayList<IValidationMessage>(1);
		}
		return _msgs;
	}

	public boolean isValidValue(String value) {
		if (value.length() != 1)
			getValidationMessages().add(new ValidationMessage(Messages.CharacterType_0));
			
		return getValidationMessages().size() == 0;
	}

}
