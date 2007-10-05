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

	private static final String BUNDLE = DialogFieldResources.class.getName(); //$NON-NLS-1$

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
