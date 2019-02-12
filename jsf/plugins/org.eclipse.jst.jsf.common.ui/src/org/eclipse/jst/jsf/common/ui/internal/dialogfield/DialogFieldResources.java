/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.common.ui.internal.utils.ResourceUtils;

/**
 * Resource bundle class for datastore wizard and it's pages
 * 
 * @author mengbo
 */
/*package*/ class DialogFieldResources extends ResourceUtils {
	/** Create the logger for this class */
	private static Logger _log = JSFUICommonPlugin
			.getLogger(DialogFieldResources.class);

	private static DialogFieldResources _resource; // singleton

	private static final String BUNDLE = DialogFieldResources.class.getName();

	/**
	 * Empty Constructor.
	 * 
	 * @return WizardsResources
	 */

	public static DialogFieldResources getInstance() {
		if (_resource == null) {
			_resource = new DialogFieldResources();
		}
		return _resource;
	}

	/**
	 * The constructor create a resource bundle
	 */
	protected DialogFieldResources() {
		try {
			_resources = ResourceBundle.getBundle(BUNDLE);
			// NOTE: this throws a runtime "MissingResourceException".
		} catch (MissingResourceException ee) {
			// Wizards.WizardResource.Error.ResourceNotFound = Unable to locate
			// resource.
			_log.error("Wizards.WizardResource.Error.ResourceNotFound", ee); //$NON-NLS-1$
		}
		setBundle(_resources, BUNDLE);
	}
}
