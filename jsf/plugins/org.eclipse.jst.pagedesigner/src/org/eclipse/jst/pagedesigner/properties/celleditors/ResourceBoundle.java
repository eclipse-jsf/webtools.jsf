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
package org.eclipse.jst.pagedesigner.properties.celleditors;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author mengbo
 * @version 1.5
 */
public class ResourceBoundle {
	private static final String BUNDLE_NAME = "org.eclipse.jst.pagedesigner.properties.celleditors.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private ResourceBoundle() {
        // no external instantiation
	}

	/**
	 * @param key
	 * @return the value for the key or !!key!!
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
