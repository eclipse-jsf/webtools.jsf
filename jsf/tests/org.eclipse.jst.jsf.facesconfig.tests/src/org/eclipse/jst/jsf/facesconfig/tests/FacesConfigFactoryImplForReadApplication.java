/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
/*
 * This Junit class is used to test the FacesConfigFactoryImpl
 * class. 
 * 
 */
public class FacesConfigFactoryImplForReadApplication extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadApplication(String name) {
		super(name);
	}

	
	
	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 * The following method is used to test for the empty navigation rule. Since
	 * I am supplying a single faces-config.xml file as a testing file, I had to
	 * testcases fit in to it by controlling the conditions
	 * 
	 */
	public void testSingleApplication() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList app = edit.getFacesConfig().getApplication();
				assertTrue(!app.isEmpty());
				assertTrue(app.size()!=0);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}


	/*
	 * Testing for action-listener
	 * 
	 */
	public void testActionListener() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList app = edit.getFacesConfig().getApplication();
				assertTrue(app.size() == 1);
				ApplicationType appType = (ApplicationType) app.get(0);
				EList actionListener = appType.getActionListener();
				assertTrue(!actionListener.isEmpty());
				assertEquals(1, actionListener.size());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for default-render-kit-id
	 * 
	 */
	public void testRenderKitId() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList app = edit.getFacesConfig().getApplication();
				assertTrue(app.size() == 1);
				ApplicationType appType = (ApplicationType) app.get(0);
				EList renderKit = appType.getDefaultRenderKitId();
				assertTrue(!renderKit.isEmpty());
				assertEquals(1, renderKit.size());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for a single entry of message-bundle
	 * 
	 */
	public void testMessageBundle() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList app = edit.getFacesConfig().getApplication();
				assertTrue(app.size() == 1);
				ApplicationType appType = (ApplicationType) app.get(0);
				EList messageBundle = appType.getMessageBundle();
				assertTrue(!messageBundle.isEmpty());
				assertEquals(1, messageBundle.size());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for a single entry of navigation-handler
	 * 
	 */
	public void testNavigationHandler() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList app = edit.getFacesConfig().getApplication();
				assertTrue(app.size() == 1);
				ApplicationType appType = (ApplicationType) app.get(0);
				EList navigationHandler = appType.getNavigationHandler();
				assertTrue(!navigationHandler.isEmpty());
				assertEquals(1, navigationHandler.size());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for a single entry of view-handler
	 * 
	 */
	public void testViewHandler() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList app = edit.getFacesConfig().getApplication();
				assertTrue(app.size() == 1);
				ApplicationType appType = (ApplicationType) app.get(0);
				EList viewHandler = appType.getViewHandler();
				assertTrue(!viewHandler.isEmpty());
				assertEquals(1, viewHandler.size());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for a single entry of state-manager
	 * 
	 */
	public void testStateManager() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList app = edit.getFacesConfig().getApplication();
				assertTrue(app.size() == 1);
				ApplicationType appType = (ApplicationType) app.get(0);
				EList stateManager = appType.getStateManager();
				assertTrue(!stateManager.isEmpty());
				assertEquals(1, stateManager.size());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
		

	/*
	 * Testing for the variable-resolver
	 */

	public void testSingleVariableResolver() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList app = edit.getFacesConfig().getApplication();
				assertTrue(app.size() == 1);
				for (int i = 0; i < app.size(); i++) {
					ApplicationType appType = (ApplicationType) app.get(i);
					EList variableResolver = appType.getVariableResolver();
					assertTrue(!variableResolver.isEmpty());
				}

			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for the property-resolver
	 */
	public void testSinglePropertyResolver() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList app = edit.getFacesConfig().getApplication();
				assertTrue(app.size() == 1);
				for (int i = 0; i < app.size(); i++) {
					ApplicationType appType = (ApplicationType) app.get(i);
					EList propertyResolver = appType.getVariableResolver();
					assertTrue(!propertyResolver.isEmpty());
				}

			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for the local-config within an application
	 */
	public void testLocalConfig() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList app = edit.getFacesConfig().getApplication();
				assertTrue(app.size() == 1);
				for (int i = 0; i < app.size(); i++) {
					ApplicationType appType = (ApplicationType) app.get(i);
					EList localConfig = appType.getLocaleConfig();
					assertTrue(!localConfig.isEmpty());
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
}