/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jst.jsf.facesconfig.common.logging.Logger;
import org.eclipse.jst.jsf.facesconfig.common.utils.ResourceUtils;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;

/**
 * Resource binding for resource editor
 * 
 * @author jchoi
 * @version 1.0
 */
public class PropertyResources extends ResourceUtils {
	/** resource file */
	private static final String RESOURCE_BUNDLE = "org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.PropertyResources";

	/** resource instance */
	private static PropertyResources resource; // singleton

	/** log instance */
	private static final Logger log = EditorPlugin
			.getLogger(PropertyResources.class);

	/**
	 * Empty Constructor.
	 */
	protected PropertyResources() {
		try {
			resources = ResourceBundle.getBundle(RESOURCE_BUNDLE);
			// NOTE: this throws a runtime "MissingResourceException".
		} catch (MissingResourceException ee) {
			// catch this and the error is reported in setBundle.
			// Pageflow.PageflowResources.Error.invalidResourceBundle = The
			// specified resource file is not a valid resourece bundle file.
			log.error("resource.MessageResource.Error.invalidResourceBundle",
					ee);
		}
		setBundle(resources, RESOURCE_BUNDLE);
	}

	/**
	 * return the singleton instance of pageflow resource bundler.
	 * 
	 * @return - the singleton instance of pageflow resource bundler.
	 */
	public static PropertyResources getInstance() {
		if (resource == null) {
			resource = new PropertyResources();
		}
		return resource;
	}
}
