/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

/**
 * Provides class loading by using the plugin's classloader that defined an item of metadata
 */
public interface IClassLoaderProvider {
	/**
	 * @param className
	 * @return Class - implementers should eat exceptions and return null whenever class cannot be returned
	 */
	public Class loadClass(String className);
}
