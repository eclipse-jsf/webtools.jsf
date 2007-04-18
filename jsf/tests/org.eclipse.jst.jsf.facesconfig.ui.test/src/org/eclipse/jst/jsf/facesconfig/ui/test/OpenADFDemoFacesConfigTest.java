/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.test;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor;
import org.eclipse.jst.jsf.facesconfig.ui.test.util.TestUtil;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @author sfshi
 * 
 */
public class OpenADFDemoFacesConfigTest extends TestCase {

	IProject project;

	FacesConfigEditor editor;

	/**
	 * Create the ADF Demo project from zip file.
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com", "80");
		project = TestUtil.createProjectFromZip("adfDemoProject",
				"adfDemoProject.zip");
	}

	/**
	 * Close the editor without saving, then remove the project.
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.closeEditor(editor, false);
		project.close(null);
//		project.delete(IProject.FORCE | IProject.ALWAYS_DELETE_PROJECT_CONTENT,
//				new MockProgressMonitor());
	}

	/**
	 * Use FacesConfigEditor to open the faces-config.xml file.
	 * 
	 * @throws CoreException
	 */
	public void testOpenFacesConfigFile() throws CoreException, InterruptedException {
		IPath filePath = new Path("WebContent/WEB-INF/faces-config.xml");
		IFile facesConfigFile = project.getFile(filePath);
		assertNotNull(facesConfigFile);   
		assertTrue("The facesconfig file doesn't exists.", facesConfigFile
				.exists());
		IEditorInput fileInput = new FileEditorInput(facesConfigFile);
		editor = (FacesConfigEditor) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().openEditor(
						fileInput, FacesConfigEditor.EDITOR_ID);
		assertNotNull(editor);
		
		// wait for the editor to signal its pages have been loaded.
		// throw exception of wait is longer than
        editor.doPageLoad(60000);
	}
}
