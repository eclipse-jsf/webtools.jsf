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

import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.FacetType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test for Component with in faces-config
 * root Element.
 *
 */
public class ReadComponentTestCase extends BaseReadTestCase {

	public ReadComponentTestCase(String name) {
		super(name);
	}

	/*
	 * assert there is at least one Component listed
	 */
	public void testSingleComponent() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            assertNotNull(getComponent1(edit.getFacesConfig()));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    private ComponentType getComponent1(FacesConfigType facesConfig)
    {
        return (ComponentType) FacesConfigModelUtil
            .findEObjectElementById(facesConfig.getComponent(), "component1");
    }
    
	// Test for the Descirption
	public void testNonEmptyDescription() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());

            final ComponentType component1 = 
                getComponent1(edit.getFacesConfig());
            assertNotNull(component1);
            
            final DescriptionType descriptionType =
                (DescriptionType) FacesConfigModelUtil.
                findEObjectElementById
                    (component1.getDescription(), "componentDescription1");
            assertNotNull(descriptionType);
            assertEquals("my component", descriptionType.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 *Check for the Display name 
	 * 
	 */
	public void testDisplayName() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());

            final ComponentType component1 = 
                getComponent1(edit.getFacesConfig());
            assertNotNull(component1);
            
            final DisplayNameType displayNameType =
                (DisplayNameType) FacesConfigModelUtil.
                findEObjectElementById
                    (component1.getDisplayName(), "componentDisplayName1");
            assertNotNull(displayNameType);
            assertEquals("My component", displayNameType.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	
	/*
	 * get the icon of a compnent
	 * 
	 */
	public void testNonEmptyIcon() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            
            final ComponentType component1 = 
                getComponent1(edit.getFacesConfig());
            assertNotNull(component1);
            
            final IconType iconType =
                (IconType) FacesConfigModelUtil.
                    findEObjectElementById
                        (component1.getIcon(), "componentIcon1");
            assertNotNull(iconType);
            
            assertEquals("small-icon", 
                         iconType.getSmallIcon().getTextContent().trim());
            assertEquals("large-icon",
                         iconType.getLargeIcon().getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    /**
	 * check to see there is the Component-type and the Component-class
	 */
	public void testNonEmptyComponentTypeAndClass() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
            assertNotNull (edit.getFacesConfig());
            ComponentType component1 = getComponent1(edit.getFacesConfig());
			assertNotNull(component1);
            
			assertEquals("component-type", component1.getComponentType().getTextContent());
			assertEquals("ComponentClass", component1.getComponentClass().getTextContent());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	
	/**
	 * check for the attribute part of the Component
     * @see ReadAttributeComponentTestCase for the full test of the
     * component attributes 
	 */
	public void testAttribute() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull (edit.getFacesConfig());
			ComponentType component1 = getComponent1(edit.getFacesConfig());
			
            // full testing of attribute is in ReadAttributeComponentTestCase
            assertEquals(1, component1.getAttribute().size());
            assertEquals("componentAttribute1", 
                    ((AttributeType)component1.getAttribute().get(0)).getId());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    /**
     * check to see there is property listed in the input file
     * @see ReadPropertyComponentTestCase for full test of component
     * property
     */
	public void testProperty() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            
            ComponentType component1 = getComponent1(edit.getFacesConfig());
            assertNotNull(component1);
            
            assertEquals(1, component1.getProperty().size());
            assertEquals("componentProperty1", ((PropertyType)component1.getProperty().get(0)).getId());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
    
    public void testFacet()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            ComponentType component1 = getComponent1(edit.getFacesConfig());
            assertNotNull(component1);
            
            assertEquals(1, component1.getFacet().size());
            assertEquals("componentFacet1", ((FacetType)component1.getFacet().get(0)).getId());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
	/*
	 * Check for hte Component-Extension inside Component
	 */

	public void testComponentExtension() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            ComponentType component1 = getComponent1(edit.getFacesConfig());
            assertNotNull(component1);
            
            ComponentExtensionType extType = 
                (ComponentExtensionType) FacesConfigModelUtil
                .findEObjectElementById(component1.getComponentExtension()
                                        , "componentExtension1");
            assertNotNull(extType);
            //assertEquals("component-extension", extType.getAny().get(0));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
}