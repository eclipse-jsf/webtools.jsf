/*******************************************************************************
 * Copyright (c) 2012 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Ian Trimble/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

/**
 * Meta-data processing type representing a String attribute value runtime type
 * that extends StringType to allow any String as a valid value while still
 * providing possible values and default values from metadata.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @author Ian Trimble - Oracle
 */
public class AnyStringType extends StringType {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.taglibprocessing.attributevalues.StringType#isValidValue(java.lang.String)
	 */
	public boolean isValidValue(String value) {
		boolean valid = true;
		if (value == null || value.length() < 1) {
			valid = false;
			addNewValidationMessage(Messages.AnyStringType_empty_value);
		}
		return valid;
	}

}
