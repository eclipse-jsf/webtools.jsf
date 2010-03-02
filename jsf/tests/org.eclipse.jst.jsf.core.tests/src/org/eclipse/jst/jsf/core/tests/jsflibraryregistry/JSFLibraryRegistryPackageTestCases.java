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

import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryPackageImpl;

@SuppressWarnings("deprecation")
public class JSFLibraryRegistryPackageTestCases extends TestCase {

	public JSFLibraryRegistryPackageTestCases(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testObjectCreation() {
		JSFLibraryRegistryPackage jlrp = JSFLibraryRegistryPackageImpl.init();
		
		Assert.assertNotNull(jlrp);
		
		// test some getters that initialized in the Class satic init  methods
		Assert.assertNotNull(jlrp.getJSFLibrary_ID());
		Assert.assertNotNull(jlrp.getName());
		Assert.assertNotNull(jlrp.getJSFLibraryRegistry());
		Assert.assertNotNull(jlrp.getArchiveFile());
		Assert.assertNotNull(jlrp.getJSFLibrary());
		Assert.assertNotNull(jlrp.getPluginProvidedJSFLibrary());
		Assert.assertNotNull(jlrp.getJSFVersion());
	
	}
	
}
