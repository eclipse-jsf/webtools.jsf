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
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


/*
* This Junit class is used to test the FacesConfigFactoryImpl
* class. 
* 
*/
public class FacesConfigFactoryTest extends TestCase {
	IProject project = null;

	public FacesConfigFactoryTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		project = WizardUtil.getTestProject();
	}

	public static Test suite() {
		return new TestSuite(FacesConfigFactoryTest.class);
	}

	/*
	 * Test the applicaion element of faces-config.xml
	 * 
	 */
	public void testEmptyFactory() {

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
	 * Test the application-factory element of Factory in faces-config.xml
	 * 
	 */
	public void testApplicationFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList appFactory = edit.getFacesConfig().getFactory();
				FactoryType factoryType = (FactoryType)appFactory.get(0);
				assertTrue(!factoryType.equals(null)); 	
				System.out.println("application Factory class : " + factoryType.getClass());
				assertEquals("class org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl",
					factoryType.getClass().toString());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	/*
	 * Test the  faces-context-factory element of Factory in faces-config.xml
	 * PROBLEM PROBLEM I DO NOT KNOW WHY
	 */
	public void testFacesContextFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				//EList appFactory = edit.getFacesConfig().getFactory();
	// NOT DOING AS OF HERE SO COMEBACK AND SO SOME ASSERTS.
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}


	/*
	 * Test the application-factory element of Factory in faces-config.xml
	 * 
	 */
	public void testLifeCycleFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				//EList appFactory = edit.getFacesConfig().getFactory();
				//FactoryType factoryType = (FactoryType)appFactory.get(0);
				/*LifecycleFactoryType item = (LifecycleFactoryType)appFactory.get(0);
				String name = item.getValue();
				assertEquals("lifecycle",name);
				assertTrue(!name.equals(null));*/
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	

}