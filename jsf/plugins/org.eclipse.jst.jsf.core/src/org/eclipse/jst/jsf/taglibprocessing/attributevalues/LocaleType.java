/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation., and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *	  Vadim Dmitriev
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;
import org.eclipse.jst.jsf.metadataprocessors.features.ValidationMessage;

/**
 * Meta-data processing type representing a Locale Code type.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 */

public class LocaleType extends MultiSignatureEnumerationType implements IPossibleValues, IValidValues, IValidELValues{
	private List<IPossibleValue> _pvs;
	@Override
	protected String[] getReturnTypes() {
		return new String[]{"java.util.Locale", "java.lang.String"}; //$NON-NLS-1$ //$NON-NLS-2$
	}

	public List getPossibleValues() {
		if (_pvs == null){
			_pvs = new ArrayList();	
			Locale[]_locales = getLocales();
			for(int i=0;i<_locales.length;i++){
				PossibleValue pv = new PossibleValue(_locales[i].toString(), _locales[i].getDisplayName());
				_pvs.add(pv);
			}
			Collections.sort(_pvs, new Comparator(){
				public int compare(Object o1, Object o2) {
					PossibleValue pv1 = (PossibleValue)o1;
					PossibleValue pv2 = (PossibleValue)o2;
					return (pv1.getDisplayValue().compareTo(pv2.getDisplayValue()));
				}
			
			});
		}
		return _pvs;
	}

	private Locale[] getLocales() {
		return Locale.getAvailableLocales();
	}

	public boolean isValidValue(String value) {
		Locale[]_locales = getLocales();
		for(int i=0;i<_locales.length;i++){
			Locale local = getLocales()[i];
			if (local.toString().equals(value))
				return true;
		}
		getValidationMessages().add(new ValidationMessage(Messages.LocaleType_1));
		return false;
	}

}
