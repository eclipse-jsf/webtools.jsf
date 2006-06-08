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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the FacesConfigFactoryImpl
 * class. 
 * 
 */
public class FacesConfigElementsTest extends TestCase {
	IProject project = null;

	public FacesConfigElementsTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		project = WizardUtil.getTestProject();
	}

	public static Test suite() {
		return new TestSuite(FacesConfigElementsTest.class);
	}

	/*
	 * Test the applicaion element of faces-config.xml
	 * 
	 */
	public void testApplication() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList app = edit.getFacesConfig().getApplication();
				assertTrue(!app.isEmpty() && app.size() == 1);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Test the factory element of faces-config.xml
	 * 
	 */
	public void testFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList factory = edit.getFacesConfig().getFactory();
				assertTrue(!factory.isEmpty() && factory.size() == 1);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	/*
	 * Test the component element of faces-config.xml
	 * 
	 */
	public void testComponent() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList component = edit.getFacesConfig().getComponent();
				assertTrue(!component.isEmpty() && component.size() == 1);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Test the managed-bean element of faces-config.xml
	 * 
	 */
	public void testManagedBean() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList bean = edit.getFacesConfig().getManagedBean();
				assertTrue(!bean.isEmpty() && bean.size() == 1);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}


	/*
	 * Test the referenced-bean element of faces-config.xml
	 * 
	 */
	public void testReferencedBean() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList bean = edit.getFacesConfig().getReferencedBean();
				assertTrue(!bean.isEmpty() && bean.size() == 1);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}


	/*
	 * Test the render-kit element of faces-config.xml
	 * 
	 */
	public void testRenderKit() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList renderKit = edit.getFacesConfig().getRenderKit();
				assertTrue(!renderKit.isEmpty() && renderKit.size() == 1);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	/*
	 * Testing for the validator elment of the config file
	 */
	
	public void testValidator() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList validator = edit.getFacesConfig().getValidator();
				assertTrue(!validator.isEmpty() && validator.size() == 1);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	/*
	 * Testing for the converter elment of the config file
	 */
	
	public void testConverter() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList converter = edit.getFacesConfig().getConverter();
				assertTrue(!converter.isEmpty() && converter.size() == 1);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
}