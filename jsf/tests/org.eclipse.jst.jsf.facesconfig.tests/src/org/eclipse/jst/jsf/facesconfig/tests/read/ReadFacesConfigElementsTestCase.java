/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the FacesConfigFactoryImpl
 * class. 
 * 
 */
public class ReadFacesConfigElementsTestCase extends BaseReadTestCase {

	public ReadFacesConfigElementsTestCase(String name) {
		super(name);
	}
    
	/*
	 * Test the applicaion element of faces-config.xml
	 * 
	 */
	public final void testApplication() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
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
	public final void testFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
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
	public final void testComponent() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
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
	public final void testManagedBean() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
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
	public final void testReferencedBean() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
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
	public final void testRenderKit() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
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
	
	public final void testValidator() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
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
	
	public final void testConverter() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
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
    public final void testNavigationRule()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
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
    public final void testLifecycle()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            assertEquals(1, edit.getFacesConfig().getLifecycle().size());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }        
    }
}