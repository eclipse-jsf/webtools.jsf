/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;


import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

/**
 * Meta-data processing type representing a "value" attribute 
 * that implements IValidValues and IValidELValues
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 */
public class ValueType extends ValueBindingType implements IValidValues, IValidELValues {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidValues#isValidValue(java.lang.String)
	 */
	public boolean isValidValue(String value){
		//value can be a string or a value binding expression
		return true; 
	}
}
