/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
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

package org.eclipse.jst.jsf.common.metadata.tests;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.jst.jsf.common.metadata.internal.PluginRelativeStandardMetaDataSourceFileLocator;

/**
 * Simple class extending PluginRelativeStandardMetaDataSourceFileLocator to test locator extension on ext-pt.
 *
 */
public class MyMDLocator extends PluginRelativeStandardMetaDataSourceFileLocator {
	public MyMDLocator(){
		super();
	}
	
	@Override
	public InputStream getInputStream() throws IOException {
		this.getFileInfo().toString();//doing this just to get code coverage
		return super.getInputStream();
	}
}
