/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.meta.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author mengbo
 * @version 1.5
 */
public class LocaleFallback {
	private static Map map = new HashMap();

	/**
	 * @param locale
	 * @return the list of local values
	 */
	public static synchronized String[] fallBack(Locale locale) {
		if (map.get(locale) != null) {
			return (String[]) map.get(locale);
		}
		// first compute the fallback locales according to the input locale
		List inputLocaleList = calculateLocaleNames(locale);
		String[] ins = reverseList(inputLocaleList);
		// then compute the fallback locales accroding to the system default
		// locale
		int defsSize = 0;
		String[] defs = null;
		if (!locale.equals(Locale.getDefault())) {
			List defLocaleList = calculateLocaleNames(Locale.getDefault());
			defs = reverseList(defLocaleList);
			defsSize = defs.length;
		}

		int insSize = ins.length;
		int size = insSize + defsSize;

		String[] options = new String[size + 1];
		for (int i = 0; i < size; i++) {
			if (i < insSize) {
				options[i] = ins[i];
			} else {
				options[i] = defs[i - insSize];
			}
		}
		// last add blank string in order to search the base file
		options[size] = "";
		map.put(locale, options);

		return options;

	}

	private static List calculateLocaleNames(Locale locale) {
		List list = new ArrayList();
		StringBuffer item;

		String language = locale.getLanguage();
		String country = locale.getCountry();
		String variant = locale.getVariant();

		int languageLength = language.length();
		int countryLength = country.length();
		int variantLength = variant.length();

		if (languageLength == 0 && countryLength == 0 && variantLength == 0) {
			// The locale is "", "", "".
			return list;
		}
        item = new StringBuffer();
        item = item.append('_').append(language);
        list.add(item.toString());

		if (countryLength == 0 && variantLength == 0) {
			return list;
		}
        item.append('_').append(country);
        list.add(item.toString());

		if (variantLength == 0) {
			return list;
		}
        item.append('_').append(variantLength);
        list.add(item.toString());

		return list;
	}

	private static String[] reverseList(List list) {
		int size = list.size();
		String[] vals = new String[size];
		for (int i = 0; i < size; i++) {
			vals[i] = (String) list.get(size - 1 - i);
		}
		return vals;
	}
}
