/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.commands;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author mengbo
 * @version 1.5
 */
public class CommandResources {
	private static final String BUNDLE_NAME = "org.eclipse.jst.pagedesigner.commands.CommandResources"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private CommandResources() {
        // no local instantiation
	}

	/**
	 * @param key
	 * @return the resource value for key or  !key! if not foudnd
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
