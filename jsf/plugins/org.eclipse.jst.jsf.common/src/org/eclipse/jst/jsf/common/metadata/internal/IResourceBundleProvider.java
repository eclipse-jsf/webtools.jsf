/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ResourceBundle;

/**
 * Provides the resource bundle from the plugin that defined an item of metadata.
 */
public interface IResourceBundleProvider {
	/**
	 * @return ResourceBundle - implementers should eat exceptions and return null whenever resourceBundle cannot be returned
	 */
	public ResourceBundle getResourceBundle() throws IOException, MalformedURLException;
}
