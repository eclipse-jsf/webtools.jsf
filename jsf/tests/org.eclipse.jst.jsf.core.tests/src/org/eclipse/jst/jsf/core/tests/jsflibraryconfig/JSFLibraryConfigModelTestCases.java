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

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;

public class JSFLibraryConfigModelTestCases extends TestCase {
	final static private String QUALIFIEDNAME = "org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigProjectData";	
	private static final String PROJ_NAME = "_TEST_CFGLIBRARYMODEL_PROJECT";
	private static final String PROJ_NAME2 = "_TEST_CFGLIBRARYMODEL_PROJECT2";
//	private JSFLibraryConfiglModelSource modelSrc = null;
//	private JSFLibraryConfiglModelSource modelSrcWithServerSupplied = null;
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
		
//		modelSrc = new JSFLibraryConfigDialogSettingData(true, compLibs);	
//		modelSrcWithServerSupplied = new JSFLibraryConfigDialogSettingData(IMPLEMENTATION_TYPE.SERVER_SUPPLIED, true, compLibs);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//FIX ME!
//	public void testGetJSFImplementationLibraries() {
//		JSFLibraryConfigModel model = JSFLibraryConfigModelFactory.createInstance(modelSrc);
//
//		Assert.assertNotNull(model);
//		// we should have at least as many impl libraries as we have added
//		// NOTE: this test case has  interactions with test cases in the same suite
//		// since JSF libs exist on a workspace basis which is normally not cleared
//		// between each TestCase being run
//		Assert.assertTrue(model.getJSFImplementationLibraries().size() >= numCompLibs);		
//	}
//
//	public void testGetJSFComponentLibraries() {
//		JSFLibraryConfigModel model = JSFLibraryConfigModelFactory.createInstance(modelSrc);
//		
//		Assert.assertNotNull(model);
//		Assert.assertTrue(model.getJSFComponentLibraries().size() == numCompLibs);		
//	}
//
//	public void testGetCurrentJSFImplementationLibrarySelection() {
//		testGetSavedJSFImplementationLibrary();
//	}
//
//	public void testGetCurrentJSFComponentLibrarySelection() {
//		testGetSavedJSFComponentLibraries();
//	}
//
//	public void testGetSavedJSFImplementationLibrary() {
//		JSFLibraryConfigModel model = JSFLibraryConfigModelFactory.createInstance(modelSrc);
//
//		JSFLibraryRegistry libReg = JSFCoreUtilHelper.getJSFLibraryRegistryFromJSFLibraryHelper();
//		JSFLibraryInternalReference libImplRef = model.getCurrentJSFImplementationLibrarySelection();
//		Assert.assertNotNull(libImplRef);
//		Assert.assertTrue(libReg.getDefaultImplementation().getID().equals(libImplRef.getID()));
//	}
//
//	@SuppressWarnings("unchecked")
//    public void testGetSavedJSFComponentLibraries() {
//		JSFLibraryConfigModel model = JSFLibraryConfigModelFactory.createInstance(modelSrc);
//
//		/*JSFLibraryRegistry libReg = */JSFCoreUtilHelper.getJSFLibraryRegistryFromJSFLibraryHelper();
//		List lstCompRef = model.getCurrentJSFComponentLibrarySelection();
//		
//		Assert.assertTrue(lstCompRef.size() == 1);
//		
//		JSFLibraryInternalReference libCompRef = (JSFLibraryInternalReference) lstCompRef.get(0);  
//		Assert.assertNotNull(libCompRef);
//		Assert.assertTrue(libCompRef.isSelected());
//		Assert.assertTrue(libCompRef.isCheckedToBeDeployed());
//	}
//
///*	
//  	public void testSetCurrentJSFImplementationLibrarySelection() {
//		fail("Not yet implemented");
//	}
//
//	public void testSetCurrentJSFComponentLibrarySelection() {
//		fail("Not yet implemented");
//	}
//*/
//	public void testSaveData() {		
//		try {
//			project = JSFCoreUtilHelper.createWebProject(PROJ_NAME);
//		} catch (Exception e1) {
//			fail("Unable to create project for testing.");
//		}
//		
//		JSFLibraryConfigModel model = JSFLibraryConfigModelFactory.createInstance(modelSrc);				
//		model.saveData(project);
//		
// 		try {
//			String content = ((IResource)project).getPersistentProperty(new QualifiedName(QUALIFIEDNAME, 
//													JSFUtils.PP_JSF_IMPLEMENTATION_LIBRARIES));
//			Assert.assertNotNull(content);
//			Assert.assertTrue(content.length() > 1);
//			
//			
//			String type = ((IResource)project).getPersistentProperty(new QualifiedName(QUALIFIEDNAME, 
//					JSFUtils.PP_JSF_IMPLEMENTATION_TYPE));
//			
//			Assert.assertNotNull(type);
//			Assert.assertEquals(IMPLEMENTATION_TYPE.getStringValue(IMPLEMENTATION_TYPE.USER_SPECIFIED), type);
//		} catch (CoreException e) {
//			fail("Not expecting an exception.");
//		}
//	}
//	
//	public void testSaveDataWithServerSuppliedImplSelection() {		
//		try {
//			project = JSFCoreUtilHelper.createWebProject(PROJ_NAME2);
//		} catch (Exception e1) {
//			fail("Unable to create project for testing.");
//		}
//		
//		JSFLibraryConfigModel model = JSFLibraryConfigModelFactory.createInstance(modelSrcWithServerSupplied);				
//		model.saveData(project);
//		
// 		try {
//			String content = ((IResource)project).getPersistentProperty(new QualifiedName(QUALIFIEDNAME, 
//													JSFUtils.PP_JSF_IMPLEMENTATION_LIBRARIES));
//			Assert.assertNotNull(content);
//			Assert.assertTrue(content.length() > 1);
//			
//			String type = ((IResource)project).getPersistentProperty(new QualifiedName(QUALIFIEDNAME, 
//					JSFUtils.PP_JSF_IMPLEMENTATION_TYPE));
//			
//			Assert.assertNotNull(type);
//			Assert.assertEquals(IMPLEMENTATION_TYPE.getStringValue(IMPLEMENTATION_TYPE.SERVER_SUPPLIED), type);
//		} catch (CoreException e) {
//			fail("Not expecting an exception.");
//		}
//	}
//
//	public void testGetSavedImplementationType() {
//		JSFLibraryConfigModel model = JSFLibraryConfigModelFactory.createInstance(modelSrc);
//		Assert.assertNotNull(model.getImplementationType());		
//		Assert.assertTrue(model.getImplementationType() == IMPLEMENTATION_TYPE.USER_SPECIFIED);
//		
//		model = JSFLibraryConfigModelFactory.createInstance(modelSrcWithServerSupplied);
//		Assert.assertNotNull(model.getImplementationType());		
//		Assert.assertTrue(model.getImplementationType() == IMPLEMENTATION_TYPE.SERVER_SUPPLIED);
//		
//		model = JSFLibraryConfigModelFactory.createInstance(new JSFLibraryConfigDialogSettingData(IMPLEMENTATION_TYPE.UNKNOWN, true, null));
//		Assert.assertNotNull(model.getImplementationType());		
//		Assert.assertTrue(model.getImplementationType() == IMPLEMENTATION_TYPE.UNKNOWN);
//	}
}
