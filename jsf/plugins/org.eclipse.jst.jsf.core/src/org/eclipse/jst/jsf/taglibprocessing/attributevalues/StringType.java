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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;

/**
 * Meta-data processing type representing a String attribute value runtime type
 * that implements IPossibleValues, IValidValues and IDefaultValues
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 */
public class StringType extends EnumerationType implements IValidValues,
		IPossibleValues, IDefaultValue {
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.taglibprocessing.attributevalues.EnumerationType#getReturnType()
	 */
	protected String getReturnType(){ return "java.lang.String";} //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidValues#isValidValue(java.lang.String)
	 */
	public boolean isValidValue(String value) {		
		List vals = getMDValidValues();
		if (vals.isEmpty())
			return true;
		
		//if the value is empty but there is a default value, consider it valid		
		if (getDefaultValue() != null  && (value == null || value.trim().equals(""))) //$NON-NLS-1$
			return true;
		
		if(! vals.contains(value)){
			addNewValidationMessage(Messages.StringType_invalid_value);
		}
		return getValidationMessages().isEmpty();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues#getPossibleValues()
	 */
	public List getPossibleValues() {
		List pvs = getMDPossibleValues();
		if (pvs.isEmpty())
			return EMPTY_LIST;
		
		List pdvs = getMDPossibleValuesForDisplay();
		
		//if there are no display vals or the meta-data list sizes are different, use the values list for display also
		if (pdvs.isEmpty() || pvs.size() != pdvs.size())
			pdvs = pvs;
	
		ImageDescriptor icon = getImage();
		
		String defaultValue = getDefaultValue();
		List ret = new ArrayList(pvs.size());
		for(int i=0;i<pvs.size();i++){
			PossibleValue pv = new PossibleValue((String)pvs.get(i), (String)pdvs.get(i));
			pv.setIcon(icon);
			pv.setIsDefault(((String)pvs.get(i)).equals(defaultValue));
			ret.add(pv);
		}
		return ret;
	}

}
