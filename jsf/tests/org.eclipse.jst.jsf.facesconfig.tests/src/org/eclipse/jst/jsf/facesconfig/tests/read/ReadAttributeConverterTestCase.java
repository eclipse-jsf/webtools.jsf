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

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test for the existence of all
 * items with in attribute of the Converter Element
 *
 */
public class ReadAttributeConverterTestCase extends BaseReadTestCase {

	public ReadAttributeConverterTestCase(String name) {
		super(name);
	}

    /*
     * checks to see if there exists an atribute in the first place
     * 
     */
    public void testSingleAttribute() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            AttributeType attribute1 = getAttribute1(edit.getFacesConfig());
            assertNotNull(attribute1);
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

    private AttributeType getAttribute1(FacesConfigType facesConfig)
    {
        ConverterType converter1 = 
            (ConverterType) FacesConfigModelUtil
                .findEObjectElementById(facesConfig.getConverter(), "converter1");
        assertNotNull(converter1);
        
        return (AttributeType)
            FacesConfigModelUtil
                .findEObjectElementById(converter1.getAttribute()
                                        , "converter1Attribute");
    }
    

    /*
     * This is to test the description child inside of Attribute
     * 
     */

    public void testDescription() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            AttributeType attributeType = 
                 getAttribute1(edit.getFacesConfig());
            assertNotNull(attributeType);
            
            DescriptionType descType =
                (DescriptionType)FacesConfigModelUtil.findEObjectElementById
                    (attributeType.getDescription()
                     ,"converter1_attribute_description");
            assertEquals("converter-attribute-description"
                         , descType.getTextContent().trim());
        } 
        finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /*
     * A simple test to check if the Display Name is present 
     * within the faces-config.xml file
     */
    
    public void testDisplayName() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
    
            AttributeType attributeType = 
                getAttribute1(edit.getFacesConfig());
            assertNotNull(attributeType);
    
            final DisplayNameType displayNameType =
                (DisplayNameType)FacesConfigModelUtil.findEObjectElementById
                    (attributeType.getDisplayName()
                            ,"converter1_attribute_display-name");
            assertEquals("converter-attribute-display-name"
                 , displayNameType.getTextContent().trim());        
        } 
        finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /*
     * Checks  to see if there is an icon defined 
     * 
     */
    public void testIcon() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            AttributeType attrType = getAttribute1(edit.getFacesConfig());
            assertNotNull(attrType);
            
            IconType iconType =
                (IconType) FacesConfigModelUtil
                    .findEObjectElementById
                        (attrType.getIcon(), "converter1_attribute_icon");
            assertNotNull(iconType);
            
            assertEquals("converter1-attribute-small-icon",
                         iconType.getSmallIcon().getTextContent());
            assertEquals("converter1-attribute-large-icon",
                         iconType.getLargeIcon().getTextContent());
        }  finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /*
     * This one tests for the existence of two items.
     * They are the required items by all renderers
     * They are : attribute-name and attribute-class.
     * It thought it was better to put them together instead of
     * writing single -separate methods for each of them.
     *Simply, extract the names and check if same the one
     *in faces-config.xml 
     */
    public void testAttributeNameAndClass() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            AttributeType attribute1 = getAttribute1(edit.getFacesConfig());
            assertNotNull(attribute1);
            
            assertEquals("converter1-attribute-name"
                    ,attribute1.getAttributeName().getTextContent());
            assertEquals("converter1-attribute-class"
                    ,attribute1.getAttributeClass().getTextContent());
        }  finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /*
     * Checks for the item suggested-value within attribute
     * 
     */
    public void testSuggestedValue() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            AttributeType attributeType = 
                getAttribute1(edit.getFacesConfig());
            
            assertEquals("converter1-attribute-suggested-value"
                    ,attributeType.getSuggestedValue().getTextContent());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    public void testDefaultValue()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            AttributeType attributeType = 
                getAttribute1(edit.getFacesConfig());
            
            assertEquals("converter1-default-value"
                    , attributeType.getDefaultValue().getTextContent());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /**
     * Checks for the item attribute-extension within attribute
     * TODO: not currently supported
     */ 
    public void testAttributeExtension() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            if (edit.getFacesConfig() != null) {
                EList comp = edit.getFacesConfig().getComponent();
                assertTrue(!comp.isEmpty());
                for (int i = 0; i < comp.size(); i++) {
                    ComponentType compType = (ComponentType) comp
                            .get(i);
                    assertTrue(!compType.getAttribute().isEmpty());
                    
                    EList attr = compType.getAttribute();
                    for (int k = 0; k < attr.size(); k++) {
                        AttributeType attrType = (AttributeType) attr.get(k);
                        EList ext= attrType.getAttributeExtension();
                        assertTrue(ext.size()!=0);
                    }
                }
            }
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}
