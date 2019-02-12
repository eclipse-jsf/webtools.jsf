/*******************************************************************************
 * Copyright (c) 2005, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.tests.jsflibraryregistry;

import java.util.Iterator;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;

@SuppressWarnings("deprecation")
public class JSFLibraryTestCases extends TestCase {
	private JSFLibrary jsfLib = null;
	
	// test data
	private String jsfLibID = "MyfacesID";
	private String jsfLibName = "MyfacesName";
	//private boolean jsfDeployDefault = true;
	private String[] archivefiles = {
			"faces-all-bogus.jar",
			"faces-api-bogus.jar", 
			"faces-impl-bogus.jar", 
			"tomahawk-bogus.jar"};
	
	public JSFLibraryTestCases(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();	
		
		jsfLib = JSFCoreUtilHelper.constructJSFLib(
				jsfLibID,
				jsfLibName,
				archivefiles,
				true); 		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		jsfLib = null;
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryImpl.getArchiveFiles()'
	 * Probably not needed...
	 */
    public void testgetArchiveFiles() {
		EList<?> el = jsfLib.getArchiveFiles();		
		Assert.assertNotNull(el);
		Assert.assertEquals(archivefiles.length, el.size());		
		Iterator<?> it = el.iterator();
		int i = 0;
		while(it.hasNext()) {
			Assert.assertEquals(archivefiles[i], ((ArchiveFile)it.next()).getName());
			i++;
		}
	}
	
	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryImpl.containsArchiveFile(String)'
	 */
	public void testcontainsArchiveFile() {
		String pathTestFiles = TestsPlugin.getInstallLocation().getPath() + "testfiles/";
		
		Assert.assertTrue( jsfLib.containsArchiveFile(pathTestFiles + archivefiles[2]) );		
		Assert.assertFalse( jsfLib.containsArchiveFile(pathTestFiles + "bogus.jar") );		
		Assert.assertFalse( jsfLib.containsArchiveFile(pathTestFiles + "sandbox.jar") );
		
	}

	public void testgetWorkingCopy() {
		JSFLibrary jsfLibWorkingCopy = jsfLib.getWorkingCopy();
		
		Assert.assertNotNull(jsfLibWorkingCopy);
		Assert.assertEquals(jsfLibWorkingCopy.getArchiveFiles().size(), 
				jsfLib.getArchiveFiles().size());
		
	}
	
	public void testupdateValue() {
		String[] archives = {
					"faces-all-bogus.jar",
					"faces-api-bogus.jar" };
		
		JSFLibrary jsfLibtmp = JSFCoreUtilHelper.constructJSFLib(
				"jsfLibTmp",
				"jsfLibTmpName",
				archives,
				true); 	
		
		jsfLibtmp.updateValues(jsfLib);
		
		Assert.assertEquals(jsfLibtmp.getID(), jsfLib.getID());
		Assert.assertEquals(jsfLibtmp.getName(), jsfLib.getName());
		Assert.assertEquals(jsfLibtmp.getArchiveFiles().size(), 
				jsfLib.getArchiveFiles().size());
		
	}
	
	/** 
	 * ArchiveFile.copyTo should be sufficient to cover this case.
	 * 
	public void testcopyTo() {		
	}
	 */
	
}	// end of JSFLibraryTestCases
