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
package org.eclipse.jst.pagedesigner.editors.pagedesigner;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.common.ui.internal.utils.ResourceUtils;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;

/**
 * @author mengbo
 */
public class PageDesignerResources extends ResourceUtils {
	private static Logger _log = PDPlugin
			.getLogger(PageDesignerResources.class);

	private static PageDesignerResources _resource; // singleton

	/**
	 * Empty Constructor.
	 */
	protected PageDesignerResources() {
		try {
			_resources = ResourceBundle
					.getBundle(IJMTConstants.PAGEDESIGNER_RESOURCE_BUNDLE_FILE);
			// NOTE: this throws a runtime "MissingResourceException".
		} catch (MissingResourceException ee) {
			// catch this and the error is reported in setBundle.
			// Log.Error.PageDesignerResources.Open=Error in getting source
			// bundle
			_log.error("Log.Error.PageDesignerResources.Open", ee); //$NON-NLS-1$
		}
		setBundle(_resources, IJMTConstants.PAGEDESIGNER_RESOURCE_BUNDLE_FILE);
	}

	/**
	 * @return the singleton
	 */
	public static PageDesignerResources getInstance() {
		if (_resource == null) {
			_resource = new PageDesignerResources();
		}
		return _resource;
	}
}
