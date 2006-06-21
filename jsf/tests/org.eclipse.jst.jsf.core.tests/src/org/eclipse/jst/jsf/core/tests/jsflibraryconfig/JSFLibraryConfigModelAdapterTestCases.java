/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.tests.jsflibraryconfig;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigModelAdapter;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryDecorator;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JSFLibraryConfigModelAdapterTestCases extends TestCase {
	private static final String PROJ_NAME = "_TEST_CFGMODELADAPTER_PROJECT";
	private IProject project;
	private JSFLibrary jsfLib; 
	
	public JSFLibraryConfigModelAdapterTestCases(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();	
		
		JSFCoreUtilHelper.createJSFLibraryRegistry();
		project = JSFCoreUtilHelper.createWebProject(PROJ_NAME);
		jsfLib = JSFCorePlugin.getDefault().getJSFLibraryRegistry().getDefaultImplementation();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		jsfLib = null;
		project = null;
	}
	
	public void testJSFLibraryConfigModelAdapter() {
		JSFLibraryConfigModelAdapter cfgModelAdapter = 
			new JSFLibraryConfigModelAdapter(project);
		
		Assert.assertNotNull(cfgModelAdapter);
		//Assert.assertTrue(cfgModelAdapter.getJSFImplementationLibraries().size() == 2);
		//Assert.assertTrue(cfgModelAdapter.getJSFComponentLibraries().size() == 1);
		
		// Test saving settings 
		// and then reconstruct model from persistent property
		JSFLibraryDecorator jsfLibDctr = new JSFLibraryDecorator(jsfLib, true, true);
		cfgModelAdapter.updateJSFImplementationLibrary(jsfLibDctr);
		cfgModelAdapter.saveData();
		
		Assert.assertEquals(jsfLibDctr.getID(), cfgModelAdapter.getSavedJSFImplementationLibrary().getID());
		Assert.assertTrue(cfgModelAdapter.getSavedJSFImplementationLibrary().isCheckedToBeDeployed());
		Assert.assertTrue(cfgModelAdapter.getSavedJSFImplementationLibrary().isSelected());
				
	}
	
}
