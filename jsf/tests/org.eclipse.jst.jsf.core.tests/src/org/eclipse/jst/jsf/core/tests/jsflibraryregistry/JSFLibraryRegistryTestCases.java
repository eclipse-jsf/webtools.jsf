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
package org.eclipse.jst.jsf.core.tests.jsflibraryregistry;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;

public class JSFLibraryRegistryTestCases extends TestCase {
	
	public JSFLibraryRegistryTestCases(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getDefaultImplementationID()'
	 */
	public void testGetDefaultImplementationID() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		Assert.assertEquals("", jsfLibRegistry.getDefaultImplementationID());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.setDefaultImplementationID(String)'
	 */
	public void testSetDefaultImplementationID() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		String updatedImplID = "myfaces_reg";
		jsfLibRegistry.setDefaultImplementationID(updatedImplID);
		Assert.assertEquals(updatedImplID, jsfLibRegistry.getDefaultImplementationID());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getJSFLibraries()'
	 */
	public void testGetJSFLibraries() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();

		String[] archivefiles = {
				"faces-all-bogu.jar",
				"faces-api-bogus.jar", 
				"faces-impl-bogus.jar", 
				"tomahawk-bogus.jar"};

		JSFLibrary implJSFLib = JSFCoreUtilHelper.constructJSFLib("impljsflib_id", 
				"impljsflib_name", 
				archivefiles, 
				true);
		JSFLibrary nonimplJSFLib = JSFCoreUtilHelper.constructJSFLib("nonimpljsflib_id",
				"nonimpljsflib_name",
				archivefiles,
				false);
				
		jsfLibRegistry.addJSFLibrary(implJSFLib);
		jsfLibRegistry.addJSFLibrary(nonimplJSFLib);
		
		Assert.assertEquals(2, jsfLibRegistry.getJSFLibraries().size());		
		Assert.assertEquals(1, jsfLibRegistry.getImplJSFLibraries().size());		
		Assert.assertEquals(1, jsfLibRegistry.getNonImplJSFLibraries().size());
		Assert.assertEquals(1, jsfLibRegistry.getJSFLibrariesByName("impljsflib_name").size());
		Assert.assertEquals(1, jsfLibRegistry.getJSFLibrariesByName("nonimpljsflib_name").size());
		Assert.assertNull(jsfLibRegistry.getJSFLibraryByID("nosuchlib_id"));
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getPluginProvidedJSFLibraries()'
	 */
	public void testGetPluginProvidedJSFLibraries() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary pluginLib = JSFCoreUtilHelper.constructJSFLib("plugin_provided", "testfiles/JSFLib", true, true);
		jsfLibRegistry.addJSFLibrary(pluginLib);
		JSFLibrary nonPluginLib = JSFCoreUtilHelper.constructJSFLib("non_plugin_provided", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(nonPluginLib);
		Assert.assertEquals(1, jsfLibRegistry.getPluginProvidedJSFLibraries().size());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getDefaultImplementation()'
	 */
	public void testGetDefaultImplementation() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary lib = JSFCoreUtilHelper.constructJSFLib("lib", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib);
		jsfLibRegistry.setDefaultImplementation(lib);
		Assert.assertEquals(lib, jsfLibRegistry.getDefaultImplementation());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.setDefaultImplementation(JSFLibrary)'
	 */
	public void testSetDefaultImplementation() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary lib = JSFCoreUtilHelper.constructJSFLib("lib", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib);
		jsfLibRegistry.setDefaultImplementation(lib);
		Assert.assertEquals(lib, jsfLibRegistry.getDefaultImplementation());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getJSFLibraryByID(String)'
	 */
	public void testGetJSFLibraryByID() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary lib = JSFCoreUtilHelper.constructJSFLib("lib", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib);
		String libID = "myNewID";
		lib.setID(libID);
		Assert.assertEquals(lib, jsfLibRegistry.getJSFLibraryByID(libID));
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getJSFLibrariesByName(String)'
	 */
	public void testGetJSFLibrariesByName() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		String lib1Name = "Sun RI v1.1";
		JSFLibrary lib1 = JSFCoreUtilHelper.constructJSFLib(lib1Name, "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib1);
		String lib2Name = "Another Sun RI v1.1";
		JSFLibrary lib2 = JSFCoreUtilHelper.constructJSFLib(lib2Name, "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib2);
		Assert.assertEquals(1, jsfLibRegistry.getJSFLibrariesByName(lib1Name).size());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getImplJSFLibraries()'
	 */
	public void testGetImplJSFLibraries() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary implLib = JSFCoreUtilHelper.constructJSFLib("impl_lib", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(implLib);
		JSFLibrary nonImplLib = JSFCoreUtilHelper.constructJSFLib("non_impl_lib", "testfiles/JSFLib", false, false);
		jsfLibRegistry.addJSFLibrary(nonImplLib);
		Assert.assertEquals(1, jsfLibRegistry.getImplJSFLibraries().size());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getNonImplJSFLibraries()'
	 */
	public void testGetNonImplJSFLibraries() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary implLib = JSFCoreUtilHelper.constructJSFLib("impl_lib", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(implLib);
		JSFLibrary nonImplLib = JSFCoreUtilHelper.constructJSFLib("non_impl_lib", "testfiles/JSFLib", false, false);
		jsfLibRegistry.addJSFLibrary(nonImplLib);
		Assert.assertEquals(1, jsfLibRegistry.getNonImplJSFLibraries().size());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getAllJSFLibraries()'
	 */
	public void testGetAllJSFLibraries() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary lib1 = JSFCoreUtilHelper.constructJSFLib("plugin_provided", "testfiles/JSFLib", true, true);
		jsfLibRegistry.addJSFLibrary(lib1);
		JSFLibrary lib2 = JSFCoreUtilHelper.constructJSFLib("non_plugin_provided", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib2);
		Assert.assertEquals(2, jsfLibRegistry.getAllJSFLibraries().size());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.addJSFLibrary(JSFLibrary)'
	 */
	public void testAddJSFLibrary() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary pluginLib = JSFCoreUtilHelper.constructJSFLib("plugin_provided", "testfiles/JSFLib", true, true);
		jsfLibRegistry.addJSFLibrary(pluginLib);
		Assert.assertEquals(1, jsfLibRegistry.getPluginProvidedJSFLibraries().size());
		Assert.assertEquals(0, jsfLibRegistry.getJSFLibraries().size());
		JSFLibrary nonPluginLib = JSFCoreUtilHelper.constructJSFLib("non_plugin_provided", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(nonPluginLib);
		Assert.assertEquals(1, jsfLibRegistry.getPluginProvidedJSFLibraries().size());
		Assert.assertEquals(1, jsfLibRegistry.getJSFLibraries().size());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.removeJSFLibrary(JSFLibrary)'
	 */
	public void testRemoveJSFLibrary() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary lib = JSFCoreUtilHelper.constructJSFLib("lib", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib);
		Assert.assertEquals(1, jsfLibRegistry.getJSFLibraries().size());
		jsfLibRegistry.removeJSFLibrary(lib);
		Assert.assertEquals(0, jsfLibRegistry.getJSFLibraries().size());
	}
	
}	// end of JSFLibraryRegistryTestCases
