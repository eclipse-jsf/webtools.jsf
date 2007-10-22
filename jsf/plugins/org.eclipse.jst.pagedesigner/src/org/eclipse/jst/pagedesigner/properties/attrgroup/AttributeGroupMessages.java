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
package org.eclipse.jst.pagedesigner.properties.attrgroup;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author mengbo
 * @version 1.5
 */
public class AttributeGroupMessages {
	private static final String BUNDLE_NAME = "org.eclipse.jst.pagedesigner.properties.attrgroup.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private AttributeGroupMessages() {
        // no external instantiation
	}

	/**
	 * @param key
	 * @return the value for key or !!key!! if not found
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	/**
	 * Build a formated string from the resource bundle.
	 * 
	 * @param key
	 *            the key into the resource bundle that has the formated string.
	 * @param arg0
	 *            the first argument.
	 * @return the formated string with the argument inline.
	 */
	public static String getString(String key, Object arg0) {
		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(getString(key));
		return formatter.format(args);
	}

	/**
	 * Build a formated string from the resource bundle.
	 * 
	 * @param key
	 *            the key into the resource bundle that has the formated string.
	 * @param arg0
	 *            the first argument.
	 * @param arg1
	 *            the second argument.
	 * @return the formated string with the argument inline.
	 */
	public static String getString(String key, Object arg0, Object arg1) {
		Object[] args = new Object[2];
		args[0] = arg0;
		args[1] = arg1;

		MessageFormat formatter = new MessageFormat(getString(key));
		return formatter.format(args);
	}
}
