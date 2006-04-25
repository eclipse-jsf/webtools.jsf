/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jst.jsf.facesconfig.common.logging.Logger;
import org.eclipse.jst.jsf.facesconfig.common.utils.ResourceUtils;

/**
 * Resource binding for pageflow
 * @deprecated use #EditorMessages instead.
 */
public class EditorResources extends ResourceUtils {
	/** resource file */
	private static final String RESOURCE_BUNDLE = "org.eclipse.jst.jsf.facesconfig.ui.EditorResources";

	/** resource instance */
	private static EditorResources resource; // singleton

	/** log instance */
	private static final Logger log = EditorPlugin
			.getLogger(EditorResources.class);

	/**
	 * Empty Constructor.
	 */
	protected EditorResources() {
		try {
			resources = ResourceBundle.getBundle(RESOURCE_BUNDLE);
			// NOTE: this throws a runtime "MissingResourceException".
		} catch (MissingResourceException ee) {
			// catch this and the error is reported in setBundle.
			// Pageflow.PageflowResources.Error.invalidResourceBundle = The
			// specified resource file is not a valid resourece bundle file.
			log.error("Pageflow.PageflowResources.Error.invalidResourceBundle",
					ee);
		}
		setBundle(resources, RESOURCE_BUNDLE);
	}

	/**
	 * return the singleton instance of pageflow resource bundler.
	 * 
	 * @return - the singleton instance of pageflow resource bundler.
	 */
	public static EditorResources getInstance() {
		if (resource == null) {
			resource = new EditorResources();
		}
		return resource;
	}
}
