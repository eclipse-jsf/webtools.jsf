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

import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the Converter which is one of 
 * many items inside the root elemnt faces-config in the configuration
 * information hierarchy of the faces-config.xml file 
 *
 */
public class ReadConverterTestCase extends BaseReadTestCase {
	public ReadConverterTestCase(String name) {
		super(name);
	}

	/*
	 * Check to see if this element exists. There sould be at least one
	 * of such kind listed in the file.
	 */
	public void testSingleConverter() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            assertNotNull(getConverter1(edit.getFacesConfig()));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    ConverterType getConverter1(FacesConfigType facesConfig)
    {
        return
            (ConverterType) FacesConfigModelUtil.
                findEObjectElementById
                    (facesConfig.getConverter(), "converter1");
    }
    
    private ConverterType getConverter2(FacesConfigType facesConfig)
    {
        
        return
        (ConverterType) FacesConfigModelUtil.
            findEObjectElementById
                (facesConfig.getConverter(), "converter2");
    }
    
    // Test for the Descirption
    public void testDescriptionGroup() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            ConverterType converter1 = getConverter1(edit.getFacesConfig());
            assertNotNull(converter1);
            
            // test descriptioni
            {
                DescriptionType descType = 
                    (DescriptionType) FacesConfigModelUtil.findEObjectElementById
                    (converter1.getDescription(), "converter1Description");
                assertEquals("converter-description"
                             , descType.getTextContent());
            }
            // test displayname
            {
                DisplayNameType displayNameType = 
                    (DisplayNameType) FacesConfigModelUtil.findEObjectElementById
                        (converter1.getDisplayName(), "converter1DisplayName");
                assertEquals("converter-display-name"
                             , displayNameType.getTextContent());            
            }
            // test icon
            {
                IconType iconType = 
                    (IconType) FacesConfigModelUtil.findEObjectElementById
                        (converter1.getIcon(), "converter1Icon");
                assertEquals("converter-small-icon"
                             , iconType.getSmallIcon().getTextContent());
                assertEquals("converter-large-icon"
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
            
            // we need two different converters because converter-for-class
            // and converter-id are mutually exclusive
            final ConverterType converter1 = getConverter1(edit.getFacesConfig());
            assertNotNull(converter1);
            
            assertEquals("converter-for-class"
                        , converter1.getConverterForClass().getTextContent());
            assertEquals("converter-class"
                    , converter1.getConverterClass().getTextContent());
            
            final ConverterType converter2 = getConverter2(edit.getFacesConfig());
            assertNotNull(converter2);
            
            assertEquals("converter2-id"
                    , converter2.getConverterId().getTextContent());
            assertEquals("converter2-class"
                , converter2.getConverterClass().getTextContent());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /**
     * Test the attribute sub-property of validators.
     * Note: the attribute itself is tested in ReadAttributeConverterTestCase
     * this test case just tests the presence of the attribute
     */
    public void testAttribute() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            ConverterType converter1 = getConverter1(edit.getFacesConfig());
            assertEquals(1, converter1.getAttribute().size());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

    /**
     * Test the  property sub-property of validators
     * Note: the attribute itself is tested in ReadPropertyConverterTestCase
     * this test case just tests for the presence of the property
     */
    public void testProperty() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
    
            ConverterType converter1 = getConverter1(edit.getFacesConfig());
            assertEquals(1, converter1.getProperty().size());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}