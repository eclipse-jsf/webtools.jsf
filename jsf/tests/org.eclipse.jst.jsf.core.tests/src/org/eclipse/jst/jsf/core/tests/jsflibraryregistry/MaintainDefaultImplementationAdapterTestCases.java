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

import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;

@SuppressWarnings("deprecation")
public class MaintainDefaultImplementationAdapterTestCases extends TestCase {

	public MaintainDefaultImplementationAdapterTestCases(String name) {
		super(name);
	}

	protected JSFLibraryRegistry getPreparedJSFLibraryRegistry() {
		//get registry from JSFCorePlugin
		JSFLibraryRegistry registry = JSFCoreUtilHelper.getJSFLibraryRegistryFromJSFLibraryHelper();
		//clear registry (loaded and plugin-provided libs may have crept in)
		registry.getJSFLibraries().clear();
		registry.getPluginProvidedJSFLibraries().clear();
		//ensure default implementation is null
		registry.setDefaultImplementation(null);
		return registry;
	}

	public void testAddingFirstImplSetsNewDefaultImpl() {
		//get prepared registry
		JSFLibraryRegistry registry = getPreparedJSFLibraryRegistry();
		//create and add impl
		JSFLibrary impl1 = JSFCoreUtilHelper.constructJSFLib("impl1", "testfiles/JSFLib", true, false);
		registry.addJSFLibrary(impl1);
		//test
		Assert.assertEquals(impl1, registry.getDefaultImplementation());
	}

	public void testRemovingDefaultImplSetsNewDefaultImpl() {
		//get prepared registry
		JSFLibraryRegistry registry = getPreparedJSFLibraryRegistry();
		//create and add impl
		JSFLibrary impl1 = JSFCoreUtilHelper.constructJSFLib("impl1", "testfiles/JSFLib", true, false);
		registry.addJSFLibrary(impl1);
		//create and add impl
		JSFLibrary impl2 = JSFCoreUtilHelper.constructJSFLib("impl2", "testfiles/JSFLib", true, false);
		registry.addJSFLibrary(impl2);
		//test
		Assert.assertEquals(impl1, registry.getDefaultImplementation());
		//remove default impl
		registry.removeJSFLibrary(impl1);
		//test
		Assert.assertEquals(impl2, registry.getDefaultImplementation());
	}

	public void testRemovingLastImplNullsDefaultImpl() {
		//get prepared registry
		JSFLibraryRegistry registry = getPreparedJSFLibraryRegistry();
		//create and add impl
		JSFLibrary impl1 = JSFCoreUtilHelper.constructJSFLib("impl1", "testfiles/JSFLib", true, false);
		registry.addJSFLibrary(impl1);
		//test
		Assert.assertEquals(impl1, registry.getDefaultImplementation());
		//remove default impl
		registry.removeJSFLibrary(impl1);
		//test
		Assert.assertNull(registry.getDefaultImplementation());
	}

	public void testChangingLibToImplSetsNewDefaultImpl() {
		//get prepared registry
		JSFLibraryRegistry registry = getPreparedJSFLibraryRegistry();
		//create and add lib
		JSFLibrary lib1 = JSFCoreUtilHelper.constructJSFLib("lib1", "testfiles/JSFLib", false, false);
		registry.addJSFLibrary(lib1);
		//test
		Assert.assertNull(registry.getDefaultImplementation());
		//make lib an impl
		lib1.setImplementation(true);
		//test
		Assert.assertEquals(lib1, registry.getDefaultImplementation());
	}

	public void testChangingImplToLibSetsNewDefaultImpl() {
		//get prepared registry
		JSFLibraryRegistry registry = getPreparedJSFLibraryRegistry();
		//create and add impl
		JSFLibrary impl1 = JSFCoreUtilHelper.constructJSFLib("impl1", "testfiles/JSFLib", true, false);
		registry.addJSFLibrary(impl1);
		//create and add impl
		JSFLibrary impl2 = JSFCoreUtilHelper.constructJSFLib("impl2", "testfiles/JSFLib", true, false);
		registry.addJSFLibrary(impl2);
		//test
		Assert.assertEquals(impl1, registry.getDefaultImplementation());
		//make default impl a lib
		impl1.setImplementation(false);
		//test
		Assert.assertEquals(impl2, registry.getDefaultImplementation());
	}

	public void testChangingLastImplToLibNullsDefaultImpl() {
		//get prepared registry
		JSFLibraryRegistry registry = getPreparedJSFLibraryRegistry();
		//create and add impl
		JSFLibrary impl1 = JSFCoreUtilHelper.constructJSFLib("impl1", "testfiles/JSFLib", true, false);
		registry.addJSFLibrary(impl1);
		//test
		Assert.assertEquals(impl1, registry.getDefaultImplementation());
		//make default impl a lib
		impl1.setImplementation(false);
		//test
		Assert.assertNull(registry.getDefaultImplementation());
	}
}
