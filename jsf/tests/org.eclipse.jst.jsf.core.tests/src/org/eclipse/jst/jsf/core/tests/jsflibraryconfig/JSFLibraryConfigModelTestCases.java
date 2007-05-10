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

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigDialogSettingData;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigModel;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfiglModelSource;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryInternalReference;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigModel.JSFLibraryConfigModelFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;

public class JSFLibraryConfigModelTestCases extends TestCase {
	final static private String QUALIFIEDNAME = "org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigProjectData";	
	private static final String PROJ_NAME = "_TEST_CFGLIBRARYMODEL_PROJECT";
	private JSFLibraryConfiglModelSource modelSrc = null;
	private IProject project;
	private int numCompLibs;	
	

	protected void setUp() throws Exception {
		super.setUp();
		
		JSFCoreUtilHelper.createJSFLibraryRegistry();		
		JSFLibraryRegistry libReg = JSFCoreUtilHelper.getJSFLibraryRegistryFromJSFLibraryHelper();
		
		/* number of components is changed in workspace when running the whole test suite.
		 * Recording the number from registry for testing.
		 */  
		numCompLibs = libReg.getNonImplJSFLibraries().size();
			
		String[] compLibs = new String[1];
		JSFLibrary lib = (JSFLibrary)libReg.getNonImplJSFLibraries().get(0);
		compLibs[0] = lib.getID() + ":" + "true";
		
		modelSrc = new JSFLibraryConfigDialogSettingData(true, compLibs);		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetJSFImplementationLibraries() {
		JSFLibraryConfigModel model = JSFLibraryConfigModelFactory.createInstance(modelSrc);

		Assert.assertNotNull(model);
		Assert.assertTrue(model.getJSFImplementationLibraries().size() == 2);		
	}

	public void testGetJSFComponentLibraries() {
		JSFLibraryConfigModel model = JSFLibraryConfigModelFactory.createInstance(modelSrc);
		
		Assert.assertNotNull(model);
		Assert.assertTrue(model.getJSFComponentLibraries().size() == numCompLibs);		
	}

	public void testGetCurrentJSFImplementationLibrarySelection() {
		testGetSavedJSFImplementationLibrary();
	}

	public void testGetCurrentJSFComponentLibrarySelection() {
		testGetSavedJSFComponentLibraries();
	}

	public void testGetSavedJSFImplementationLibrary() {
		JSFLibraryConfigModel model = JSFLibraryConfigModelFactory.createInstance(modelSrc);

		JSFLibraryRegistry libReg = JSFCoreUtilHelper.getJSFLibraryRegistryFromJSFLibraryHelper();
		JSFLibraryInternalReference libImplRef = model.getCurrentJSFImplementationLibrarySelection();
		Assert.assertNotNull(libImplRef);
		Assert.assertTrue(libReg.getDefaultImplementation().getID().equals(libImplRef.getID()));
	}

	public void testGetSavedJSFComponentLibraries() {
		JSFLibraryConfigModel model = JSFLibraryConfigModelFactory.createInstance(modelSrc);

		/*JSFLibraryRegistry libReg = */JSFCoreUtilHelper.getJSFLibraryRegistryFromJSFLibraryHelper();
		List lstCompRef = model.getCurrentJSFComponentLibrarySelection();
		
		Assert.assertTrue(lstCompRef.size() == 1);
		
		JSFLibraryInternalReference libCompRef = (JSFLibraryInternalReference) lstCompRef.get(0);  
		Assert.assertNotNull(libCompRef);
		Assert.assertTrue(libCompRef.isSelected());
		Assert.assertTrue(libCompRef.isCheckedToBeDeployed());
	}

/*	
  	public void testSetCurrentJSFImplementationLibrarySelection() {
		fail("Not yet implemented");
	}

	public void testSetCurrentJSFComponentLibrarySelection() {
		fail("Not yet implemented");
	}
*/
	public void testSaveData() {		
		try {
			project = JSFCoreUtilHelper.createWebProject(PROJ_NAME);
		} catch (Exception e1) {
			fail("Unable to create project for testing.");
		}
		
		JSFLibraryConfigModel model = JSFLibraryConfigModelFactory.createInstance(modelSrc);				
		model.saveData(project);
		
 		try {
			String content = ((IResource)project).getPersistentProperty(new QualifiedName(QUALIFIEDNAME, 
													JSFUtils.PP_JSF_IMPLEMENTATION_LIBRARIES));
			Assert.assertNotNull(content);
			Assert.assertTrue(content.length() > 1);
		} catch (CoreException e) {
			fail("Not expecting an exception.");
		}
	}

}
