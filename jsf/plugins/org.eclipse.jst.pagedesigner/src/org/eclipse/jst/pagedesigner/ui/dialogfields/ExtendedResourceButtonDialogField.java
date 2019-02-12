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
package org.eclipse.jst.pagedesigner.ui.dialogfields;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ResourceButtonDialogField;
import org.eclipse.jst.pagedesigner.utils.WebAppUtil;

/**
 * @author mengbo
 * @version 1.5
 */
public class ExtendedResourceButtonDialogField extends
		ResourceButtonDialogField {
	/**
	 * @param project
	 */
	public ExtendedResourceButtonDialogField(IProject project) {
		super(project);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.ResourceButtonDialogField#browseButtonPressed()
	 */
	protected String browseButtonPressed() {
		String url = super.browseButtonPressed();
		url = WebAppUtil.transformJSPURL(url, this.getReferredFile());
		return url;
	}
}
