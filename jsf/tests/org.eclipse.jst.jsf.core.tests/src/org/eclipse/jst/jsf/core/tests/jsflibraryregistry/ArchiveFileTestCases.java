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

import java.io.File;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ArchiveFileTestCases extends TestCase { 
	private String path4TestFiles;
	
	// Test Data
	private String[] archivefiles = {
			"faces-all-bogus.jar",
			"faces-api-bogus.jar", 
			"faces-impl-bogus.jar", 
			"tomahawk-bogus.jar"};
	
	public ArchiveFileTestCases(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();		
		path4TestFiles = TestsPlugin.getInstallLocation().getPath() + "testfiles/";		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testArchiveFile() {		
		JSFLibrary jsfLib = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibrary();		
		jsfLib.setID("Myfaces");
		jsfLib.setName("Myfaces");
		
		ArchiveFile archiveFile = null;	
		String testData;
		int subStrEnd = path4TestFiles.length() - 1;
		
		for (int i = 0; i < archivefiles.length; i++) {
			testData = path4TestFiles + archivefiles[i];						
			archiveFile = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();	
			archiveFile.setRelativeToWorkspace(false);
			archiveFile.setSourceLocation(testData);
			
			Assert.assertEquals(testData, archiveFile.getSourceLocation());

			Assert.assertEquals(path4TestFiles.substring(0, subStrEnd), archiveFile.getPath());
			
			Assert.assertEquals(archivefiles[i], archiveFile.getName());
			
			Assert.assertTrue(archiveFile.exists());
			
			archiveFile.setJSFLibrary(jsfLib);
			
			Assert.assertEquals(jsfLib, archiveFile.getJSFLibrary());			
		}
		
		// test number of jar files in the library.
		Assert.assertEquals(archivefiles.length, jsfLib.getArchiveFiles().size());
	}

	public void testsetSourceLocation() {
		ArchiveFile archiveFile = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();
		
		Assert.assertNotNull(archiveFile);
		
		// Not relative To workspace
		archiveFile.setRelativeToWorkspace(false);		
		String testData = "c:\\test\\archivefiles";
		archiveFile.setSourceLocation(testData);
		Assert.assertEquals(testData, archiveFile.getSourceLocation());
		
		// Relative to workspace
		archiveFile.setRelativeToWorkspace(true);		
		IWorkspaceRoot wsRoot = getWorkspaceRoot();
		IPath wsRootPath = wsRoot.getLocation();		
		testData =  "archivefiles";
		archiveFile.setSourceLocation(wsRootPath.toOSString() + File.separator + testData);
		Assert.assertEquals(testData, archiveFile.getSourceLocation());
		
	}
	
	public void testgetPath() {		
		ArchiveFile archiveFile = null;	
		String testData;
		//int subStrEnd = path4TestFiles.length() - 1;
		
		// Branch test - not-relative to workspace
		testData = path4TestFiles + archivefiles[archivefiles.length-1];
		archiveFile = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();	
		archiveFile.setRelativeToWorkspace(false);
		archiveFile.setSourceLocation(testData);
//		Assert.assertTrue(path4TestFiles.contains(archiveFile.getPath()));
		
		// Branch test - relative to workspace
		archiveFile.setRelativeToWorkspace(true);		
		IWorkspaceRoot wsRoot = getWorkspaceRoot();
		IPath wsRootPath = wsRoot.getLocation();		
		testData =  "test.jar";
		archiveFile.setSourceLocation(wsRootPath.toOSString() + File.separator + testData);
		IPath arPath = new Path(archiveFile.getPath());		
		Assert.assertEquals(wsRootPath, arPath);
	
	}
	
	public void testgetName() {
		ArchiveFile archiveFile = null;	
		String testData;
		//int subStrEnd = path4TestFiles.length() - 1;
		
		// Branch test - not-relative to workspace
		testData = path4TestFiles + archivefiles[archivefiles.length-1];
		archiveFile = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();	
		archiveFile.setRelativeToWorkspace(false);
		archiveFile.setSourceLocation(testData);
		Assert.assertEquals(archivefiles[archivefiles.length-1], archiveFile.getName());
		
		// Branch test - relative to workspace
		archiveFile.setRelativeToWorkspace(true);		
		IWorkspaceRoot wsRoot = getWorkspaceRoot();
		IPath wsRootPath = wsRoot.getLocation();		
		testData =  "test.jar";
		archiveFile.setSourceLocation(wsRootPath.toOSString() + File.separator + testData);		
		Assert.assertEquals(testData, archiveFile.getName());
		
	}
	
	public void testexists() {
		ArchiveFile archiveFile = null;	
		String testData;
		//int subStrEnd = path4TestFiles.length() - 1;
		
		testData = path4TestFiles + archivefiles[archivefiles.length-1];
		archiveFile = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();	
		archiveFile.setRelativeToWorkspace(false);
		archiveFile.setSourceLocation(testData);
		
		Assert.assertTrue(archiveFile.exists());

		archiveFile.setRelativeToWorkspace(true);		
		IWorkspaceRoot wsRoot = getWorkspaceRoot();
		IPath wsRootPath = wsRoot.getLocation();		
		testData =  "test.jar";
		archiveFile.setSourceLocation(wsRootPath.toOSString() + File.separator + testData);		
		Assert.assertFalse(archiveFile.exists());		
		
	}
	
	public void testequals() {
		ArchiveFile archiveFile_01 = null;
		ArchiveFile archiveFile_02 = null;
		String testData;
		//int subStrEnd = path4TestFiles.length() - 1;
		
		testData = path4TestFiles + archivefiles[archivefiles.length-1];
		archiveFile_01 = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();	
		archiveFile_01.setRelativeToWorkspace(false);
		archiveFile_01.setSourceLocation(testData);
		
		archiveFile_02 = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();	
		archiveFile_02.setRelativeToWorkspace(false);
		archiveFile_02.setSourceLocation(testData);		
		
		Assert.assertEquals(archiveFile_01, archiveFile_02);

		archiveFile_01.setRelativeToWorkspace(true);		
		IWorkspaceRoot wsRoot = getWorkspaceRoot();
		IPath wsRootPath = wsRoot.getLocation();		
		testData =  "test.jar";
		archiveFile_01.setSourceLocation(wsRootPath.toOSString() + File.separator + testData);		
		Assert.assertFalse(archiveFile_01.equals(archiveFile_02));		
	}
	
	public void testcopyTo() {
		ArchiveFile archiveFile = null;	
		String testData;
		//int subStrEnd = path4TestFiles.length() - 1;
		
		testData = path4TestFiles + archivefiles[0];						
		
		archiveFile = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();	
		archiveFile.setRelativeToWorkspace(false);
		archiveFile.setSourceLocation(testData);
		
		IWorkspaceRoot wsRoot = getWorkspaceRoot();
		IPath wsRootPath = wsRoot.getLocation();		
		
		boolean copied = archiveFile.copyTo(wsRootPath.toOSString());
		
		Assert.assertTrue(copied);		
		File fileCopied = new File(wsRootPath.toOSString() + File.separator + archivefiles[0]);		
		Assert.assertTrue(fileCopied.exists());
				
		Assert.assertFalse(archiveFile.copyTo(wsRootPath.toOSString()));
		
		// cleanup test data
		fileCopied.deleteOnExit();
	}
	
	private IWorkspaceRoot getWorkspaceRoot() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();		
		return (workspace != null) ? workspace.getRoot() : null;		
	} 
	
}	// end of ArchiveFileTestCases
