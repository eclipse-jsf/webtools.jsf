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
package org.eclipse.jst.jsf.core.tests.jsflibraryconfig;

import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigDialogSettingData;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JSFLibraryConfigDialogSettingDataTestCases extends TestCase {
	private JSFLibraryRegistry libReg = null;
	private boolean bDeployImplLib = false;
	private String[] compLibs = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		JSFCoreUtilHelper.createJSFLibraryRegistry();
		libReg = JSFCoreUtilHelper.getJSFLibraryRegistryFromJSFLibraryHelper();
		
		compLibs = new String[1];  // test data has one component library
		JSFLibrary lib = (JSFLibrary)libReg.getNonImplJSFLibraries().get(0);
		compLibs[0] = lib.getID() + ":" + "true";	// deployed
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	} 

	
	//FIX ME - Kill Me
//	public void testJSFLibraryConfigDialogSettingData() {
//		JSFLibraryConfigDialogSettingData data = new JSFLibraryConfigDialogSettingData(bDeployImplLib, null);
//		Assert.assertNotNull(data);
//		Assert.assertNotNull(data.getJSFImplementationLibrary());
//		Assert.assertTrue(data.getJSFComponentLibraries().size() == 0);
//	}
//
//	public void testGetJSFImplementationLibrary() {
//		JSFLibraryConfigDialogSettingData data = new JSFLibraryConfigDialogSettingData(bDeployImplLib, compLibs);
//		Assert.assertNotNull(data);
//		
//		JSFLibrary dftImplLib = libReg.getDefaultImplementation(); 
//		Assert.assertTrue( data.getJSFImplementationLibrary().getID().equals(dftImplLib.getID()));
//	}
//
//	public void testGetJSFComponentLibraries() {
//		JSFLibraryConfigDialogSettingData data = new JSFLibraryConfigDialogSettingData(bDeployImplLib, compLibs);
//		Assert.assertNotNull(data);
//	}

}
