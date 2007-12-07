/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation., and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;

/**
 * Meta-data processing type representing a TimeZone Code type.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 */

public class TimeZoneType extends EnumerationType implements IPossibleValues, IValidValues{
	private String[] _timezones;
	
	@Override
	protected String getReturnType() {
		return "java.lang.String"; //$NON-NLS-1$
	}

	public List getPossibleValues() {
		List ret = new ArrayList();		
		for(int i=0;i<getTimeZones().length;i++){
			PossibleValue pv = new PossibleValue(_timezones[i], _timezones[i]);
			ret.add(pv);
		}
		return ret;
	}

	private String[] getTimeZones() {
		if (_timezones == null)
			_timezones = TimeZone.getAvailableIDs();
		return _timezones;
	}

	public boolean isValidValue(String value) {
		for(int i=0;i<getTimeZones().length;i++){
			if (getTimeZones()[i].equals(value))
				return true;
		}
		return false;
	}

}
