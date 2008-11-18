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
package org.eclipse.jst.pagedesigner.preview;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.common.ui.internal.utils.ResourceUtils;
import org.eclipse.jst.pagedesigner.PDPlugin;

/**
 * @author mengbo
 */
public class PreviewResources extends ResourceUtils {
	/** Create the logger for this class */
	private static Logger _log = PDPlugin.getLogger(PreviewResources.class);

	private static PreviewResources _resource; // singleton

	private static final String BUNDLE = "org.eclipse.jst.pagedesigner.preview.PreviewResources"; //$NON-NLS-1$

	/**
	 * Empty Constructor.
	 * 
	 * @return WizardsResources
	 */

	public static PreviewResources getInstance() {
		if (_resource == null) {
			_resource = new PreviewResources();
		}
		return _resource;
	}

	/**
	 * The constructor create a resource bundle
	 */
	protected PreviewResources() {
		try {
			_resources = ResourceBundle.getBundle(BUNDLE);
			// NOTE: this throws a runtime "MissingResourceException".
		} catch (MissingResourceException ee) {
			_log
					.error(
							"Log.Error.PreviewResources.ResouceNotFound", BUNDLE, ee); //$NON-NLS-1$
		}
		setBundle(_resources, BUNDLE);
	}
}
