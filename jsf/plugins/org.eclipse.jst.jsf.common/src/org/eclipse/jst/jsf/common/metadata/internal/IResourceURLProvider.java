/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.common.metadata.internal;

import java.net.URL;

/**
 * Provides absolute URLs from resource path, which is relative to the plug-in
 * with the metadata that specified the resource path.
 *  
 * @author Ian Trimble - Oracle
 */
public interface IResourceURLProvider {

	/**
	 * Get absolute URL for resource path, which is relative to the plug-in that
	 * specified the resource metadata.
	 * 
	 * @param resourcePath Path to resource, relative to metadata plug-in.
	 * @return Absolute URL for resource path.
	 */
	public URL getResourceURL(String resourcePath);

}
