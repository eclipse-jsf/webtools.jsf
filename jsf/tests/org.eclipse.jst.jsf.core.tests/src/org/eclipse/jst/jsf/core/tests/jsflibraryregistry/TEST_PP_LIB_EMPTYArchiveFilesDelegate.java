/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.core.tests.jsflibraryregistry;

import org.eclipse.jst.jsf.core.jsflibraryregistry.PluginProvidedJSFLibraryArchiveFilesDelegate;

@SuppressWarnings("deprecation")
public class TEST_PP_LIB_EMPTYArchiveFilesDelegate extends
		PluginProvidedJSFLibraryArchiveFilesDelegate {

	public TEST_PP_LIB_EMPTYArchiveFilesDelegate() {
		super();
	}

	@Override
	public void getArchiveFiles() {
	}

}
