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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;

/**
 * Meta-data processing type representing a Language Code type.
 * A Language code is defined as in html spec http://www.w3.org/TR/html4/types.html#h-6.8
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 */

public class LanguageCodeType extends EnumerationType implements IPossibleValues {
	private static final LanguageCodeMap _map = new LanguageCodeMap();
	
	@Override
	protected String getReturnType() {
		return "java.lang.String"; //$NON-NLS-1$
	}

	public List getPossibleValues() {
		List ret = new ArrayList();		
		for(Iterator it = _map.keySet().iterator();it.hasNext();){
			String key = (String)it.next();
			String value = _map.get(key);
			PossibleValue pv = new PossibleValue(key, value);
			ret.add(pv);
		}
		return ret;
	}

	public boolean isValidValue(String value) {
		if(!_map.containsKey(value)) {
			addNewValidationMessage(Messages.LanguageCodeType_1);
		}
		
		return getValidationMessages().isEmpty();
	}
	
	/**
	 * Defines language code map
	 *
	 */
	private static class LanguageCodeMap extends HashMap<String, String> {
		private static final long serialVersionUID = 1L;

		LanguageCodeMap() {
			put("af", Messages.LanguageCodeType_118); //$NON-NLS-1$
			put("ar", Messages.LanguageCodeType_0); //$NON-NLS-1$
			put("be", Messages.LanguageCodeType_7); //$NON-NLS-1$
			put("bg", Messages.LanguageCodeType_9); //$NON-NLS-1$
			put("br", Messages.LanguageCodeType_11); //$NON-NLS-1$
			put("ca", Messages.LanguageCodeType_13); //$NON-NLS-1$
			put("cs", Messages.LanguageCodeType_15); //$NON-NLS-1$
			put("da", Messages.LanguageCodeType_17); //$NON-NLS-1$
			put("de", Messages.LanguageCodeType_19); //$NON-NLS-1$
			put("el", Messages.LanguageCodeType_21); //$NON-NLS-1$
			put("en", Messages.LanguageCodeType_23); //$NON-NLS-1$
			put("es", Messages.LanguageCodeType_25); //$NON-NLS-1$
			put("et", Messages.LanguageCodeType_27); //$NON-NLS-1$
			put("eu", Messages.LanguageCodeType_29); //$NON-NLS-1$
			put("fa", Messages.LanguageCodeType_31); //$NON-NLS-1$
			put("fi", Messages.LanguageCodeType_33); //$NON-NLS-1$
			put("fo", Messages.LanguageCodeType_35); //$NON-NLS-1$
			put("fr", Messages.LanguageCodeType_37); //$NON-NLS-1$
			put("gd", Messages.LanguageCodeType_39); //$NON-NLS-1$
			put("he", Messages.LanguageCodeType_41); //$NON-NLS-1$
			put("hi", Messages.LanguageCodeType_43); //$NON-NLS-1$
			put("hr", Messages.LanguageCodeType_45); //$NON-NLS-1$
			put("hu", Messages.LanguageCodeType_47); //$NON-NLS-1$
			put("id", Messages.LanguageCodeType_49); //$NON-NLS-1$
			put("is", Messages.LanguageCodeType_51); //$NON-NLS-1$
			put("it", Messages.LanguageCodeType_53); //$NON-NLS-1$
			put("ja", Messages.LanguageCodeType_55); //$NON-NLS-1$
			put("ko", Messages.LanguageCodeType_57); //$NON-NLS-1$
			put("lt", Messages.LanguageCodeType_59); //$NON-NLS-1$
			put("lv", Messages.LanguageCodeType_61); //$NON-NLS-1$
			put("mk", Messages.LanguageCodeType_63); //$NON-NLS-1$
			put("ms", Messages.LanguageCodeType_65); //$NON-NLS-1$
			put("mt", Messages.LanguageCodeType_67); //$NON-NLS-1$
			put("nl", Messages.LanguageCodeType_69); //$NON-NLS-1$
			put("no", Messages.LanguageCodeType_71); //$NON-NLS-1$
			put("pl", Messages.LanguageCodeType_73); //$NON-NLS-1$
			put("pt", Messages.LanguageCodeType_75); //$NON-NLS-1$
			put("rm", Messages.LanguageCodeType_77); //$NON-NLS-1$
			put("ro", Messages.LanguageCodeType_79); //$NON-NLS-1$
			put("ru", Messages.LanguageCodeType_81); //$NON-NLS-1$
			put("sk", Messages.LanguageCodeType_83); //$NON-NLS-1$
			put("sl", Messages.LanguageCodeType_85); //$NON-NLS-1$
			put("sq", Messages.LanguageCodeType_87); //$NON-NLS-1$
			put("sr", Messages.LanguageCodeType_89); //$NON-NLS-1$
			put("sv", Messages.LanguageCodeType_91); //$NON-NLS-1$
			put("sx", Messages.LanguageCodeType_93); //$NON-NLS-1$
			put("sz", Messages.LanguageCodeType_95); //$NON-NLS-1$
			put("th", Messages.LanguageCodeType_97); //$NON-NLS-1$
			put("tn", Messages.LanguageCodeType_99); //$NON-NLS-1$
			put("tr", Messages.LanguageCodeType_101); //$NON-NLS-1$
			put("ts", Messages.LanguageCodeType_103); //$NON-NLS-1$
			put("uk", Messages.LanguageCodeType_105); //$NON-NLS-1$
			put("ur", Messages.LanguageCodeType_107); //$NON-NLS-1$
			put("vi", Messages.LanguageCodeType_109); //$NON-NLS-1$
			put("xh", Messages.LanguageCodeType_111); //$NON-NLS-1$
			put("yi", Messages.LanguageCodeType_113); //$NON-NLS-1$
			put("zh", Messages.LanguageCodeType_115); //$NON-NLS-1$
			put("zu", Messages.LanguageCodeType_117);			 //$NON-NLS-1$
		}
		
	}

}
