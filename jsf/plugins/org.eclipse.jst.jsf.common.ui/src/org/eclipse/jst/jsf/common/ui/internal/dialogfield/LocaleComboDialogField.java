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
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import java.util.Arrays;
import java.util.Locale;


/**
 * @author mengbo
 */
public class LocaleComboDialogField extends ComboDialogField {
	private static String[] items;
	static {
		Locale[] locales = Locale.getAvailableLocales();
		items = new String[locales.length + 1];
		for (int i = 0, n = locales.length; i < n; i++) {
			items[i] = locales[i].toString();
		}
		items[locales.length] = "";
		Arrays.sort(items);
	}

	/**
	 * @param flags
	 */
	public LocaleComboDialogField(int flags) {
		super(flags);
		setItems(items);
	}
}
