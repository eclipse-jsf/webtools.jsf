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
package org.eclipse.jst.jsf.facesconfig.tests.read;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the FacesConfigFactoryImpl
 * class. 
 * 
 */
public class ReadFacesConfigElementsTestCase extends TestCase {
	IProject project = null;

	public ReadFacesConfigElementsTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
        WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	public static Test suite() {
		return new TestSuite(ReadFacesConfigElementsTestCase.class);
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
			assertNotNull(edit.getFacesConfig());
			assertEquals(1, edit.getFacesConfig().getApplication().size());
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
			assertNotNull(edit.getFacesConfig());
			assertEquals(1, edit.getFacesConfig().getFactory().size());
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
			assertNotNull(edit.getFacesConfig());
			assertEquals(1, edit.getFacesConfig().getComponent().size());
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
			assertNotNull(edit.getFacesConfig());
			assertEquals(3, edit.getFacesConfig().getManagedBean().size());
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
			assertNotNull(edit.getFacesConfig());
			assertEquals(1, edit.getFacesConfig().getReferencedBean().size());
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
			assertNotNull(edit.getFacesConfig());
			assertEquals(1, edit.getFacesConfig().getRenderKit().size());
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
			assertNotNull(edit.getFacesConfig());
			assertEquals(1, edit.getFacesConfig().getValidator().size());
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
			assertNotNull(edit.getFacesConfig());
			assertEquals(2, edit.getFacesConfig().getConverter().size());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
    
    /**
     * Testing for the navigation rule list population
     */
    public void testNavigationRule()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = FacesConfigArtifactEdit
                    .getFacesConfigArtifactEditForRead(project);
            assertNotNull(edit.getFacesConfig());
            assertEquals(2, edit.getFacesConfig().getNavigationRule().size());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }        
    }

    /**
     * Testing for the lifecycle list population
     */
    public void testLifecycle()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = FacesConfigArtifactEdit
                    .getFacesConfigArtifactEditForRead(project);
            assertNotNull(edit.getFacesConfig());
            assertEquals(1, edit.getFacesConfig().getLifecycle().size());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }        
    }
}