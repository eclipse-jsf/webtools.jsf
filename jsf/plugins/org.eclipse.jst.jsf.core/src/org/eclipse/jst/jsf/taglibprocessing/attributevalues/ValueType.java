/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 * @author Gerry Kessler - Oracle
 */
public class ValueType extends ValueBindingType implements IValidValues, IValidELValues {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IValidValues#isValidValue(java.lang.String)
	 */
	public boolean isValidValue(String value){
		//value can be a string or a value binding expression
		return true; 
	}
}
