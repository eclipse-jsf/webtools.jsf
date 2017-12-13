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

import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the validator which is one of 
 * many items inside the root elemnt faces-config in the configuration
 * information hierarchy of the faces-config.xml file 
 *
 */
public class ReadValidatorTestCase extends BaseReadTestCase {

	public ReadValidatorTestCase(String name) {
		super(name);
	}

	/*
	 * The following method is used to test for the empty navigation rule. Since
	 * I am supplying a single faces-config.xml file as a testing file, I had to
	 * testcases fit in to it by controlling the conditions
	 * 
	 */
	public void testSingleValidator() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            assertNotNull(getValidator1(edit.getFacesConfig()));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    ValidatorType getValidator1(FacesConfigType facesConfig)
    {
        return (ValidatorType)
            FacesConfigModelUtil
                .findEObjectElementById(facesConfig.getValidator(), "validator1");
    }
    
	// Test for the Descirption
	public void testDescriptionGroup() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            ValidatorType validator1 = getValidator1(edit.getFacesConfig());
            assertNotNull(validator1);
            
            // test descriptioni
            {
                DescriptionType descType = 
                    (DescriptionType) FacesConfigModelUtil.findEObjectElementById
                    (validator1.getDescription(), "validator1_description");
                assertEquals("validator-description"
                             , descType.getTextContent());
            }
            // test displayname
            {
                DisplayNameType displayNameType = 
                    (DisplayNameType) FacesConfigModelUtil.findEObjectElementById
                        (validator1.getDisplayName(), "validator1_displayName");
                assertEquals("validator-display-name"
                             , displayNameType.getTextContent());            
            }
            // test icon
            {
                IconType iconType = 
                    (IconType) FacesConfigModelUtil.findEObjectElementById
                        (validator1.getIcon(), "validator1_icon");
                assertEquals("validator-small-icon"
                             , iconType.getSmallIcon().getTextContent());
                assertEquals("validator-large-icon"
                        , iconType.getLargeIcon().getTextContent());
            }
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testValidatorSingleValueProperties() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            
            ValidatorType validator1 = getValidator1(edit.getFacesConfig());
            assertNotNull(validator1);
            
            assertEquals("validator-Id", validator1.getValidatorId().getTextContent());
            assertEquals("validator-class", validator1.getValidatorClass().getTextContent());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	
	
	/**
	 * Test the attribute sub-property of validators.
     * Note: the attribute itself is tested in ReadAttributeValidatorTestCase
     * this test case just tests the presence of the attribute
	 */
	public void testAttribute() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            
			ValidatorType validator = getValidator1(edit.getFacesConfig());
            assertEquals(1, validator.getAttribute().size());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/**
	 * Test the  property sub-property of validators
     * Note: the attribute itself is tested in ReadPropertyValidatorTestCase
     * this test case just tests for the presence of the property
	 */
	public void testProperty() {
		FacesConfigArtifactEdit edit = null;
		try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
    
            ValidatorType validator = getValidator1(edit.getFacesConfig());
            assertEquals(1, validator.getProperty().size());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
}
