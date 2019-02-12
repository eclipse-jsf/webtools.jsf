/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.test;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor;
import org.eclipse.jst.jsf.facesconfig.ui.test.util.TestUtil;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * The base class for test cases, other test cases could extends this. In this
 * test case, an empty jsf project will be created and the default faces config
 * file will be opened with FacesConfig Editor.
 * 
 * @author sfshi
 * 
 */
public abstract class FacesConfigEditorTest extends TestCase {
	public IProject project;

	public FacesConfigEditor editor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com", "80");
		
		project = TestUtil.createProjectFromZip("emptyjsfproject",
				"emptyjsfproject.zip");
		IPerspectiveRegistry reg = PlatformUI.getWorkbench()
				.getPerspectiveRegistry();

		IPerspectiveDescriptor j2eePersp = reg
				.findPerspectiveWithId("org.eclipse.jst.j2ee.J2EEPerspective");
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.setPerspective(j2eePersp);
		openEditor();
		
	}

	protected void openEditor() throws PartInitException, InterruptedException {
		editor = (FacesConfigEditor) openWithEditor("WebContent/WEB-INF/faces-config.xml");

		// wait for pages to load for up to 30s
        editor.doPageLoad(60000);
	}

	protected IEditorPart openWithEditor(String name) throws PartInitException {
		IPath filePath = new Path(name);
		IFile facesConfigFile = project.getFile(filePath);
		assertNotNull(facesConfigFile);
		assertTrue("The facesconfig file doesn't exists.", facesConfigFile
				.exists());
		IEditorInput fileInput = new FileEditorInput(facesConfigFile);
		IEditorPart editor1 = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().openEditor(
						fileInput, FacesConfigEditor.EDITOR_ID);
		assertNotNull(editor1);

		return editor1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		closeEditor();
		TestUtil.removeResource(project);
//		JSFTestUtil.safeDelete(project, 10, 200);
	}
	
	protected void closeEditor() throws Exception {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
			.closeEditor(editor, false);
		
		editor = null;
	}
}
