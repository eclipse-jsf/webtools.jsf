/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadPropertyValidatorTestCase extends BaseReadTestCase {

    public ReadPropertyValidatorTestCase(String name) {
        super(name);
    }

    public void testProperty()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            PropertyType property1 = getProperty1(edit.getFacesConfig());
            assertNotNull(property1);
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }

    }
    
    private ValidatorType getValidator1(FacesConfigType facesConfigType)
    {
        return (ValidatorType) FacesConfigModelUtil
            .findEObjectElementById
               (facesConfigType.getValidator(), "validator1");
    }
    
    private PropertyType getProperty1(FacesConfigType facesConfigType)
    {
        ValidatorType validator1 = getValidator1(facesConfigType);
        assertNotNull(validator1);
        return (PropertyType) FacesConfigModelUtil
           .findEObjectElementById
               (validator1.getProperty(), "validator1_property");
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
            
            PropertyType propertyType = 
                 getProperty1(edit.getFacesConfig());
            assertNotNull(propertyType);
            
            DescriptionType descType =
                (DescriptionType)FacesConfigModelUtil.findEObjectElementById
                    (propertyType.getDescription()
                     ,"validator1_property_description");
            assertEquals("validator-property-description"
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
    
            PropertyType propertyType = 
                getProperty1(edit.getFacesConfig());
            assertNotNull(propertyType);
    
            final DisplayNameType displayNameType =
                (DisplayNameType)FacesConfigModelUtil.findEObjectElementById
                    (propertyType.getDisplayName()
                            ,"validator1_property_displayName");
            assertEquals("validator-property-display-name"
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
            
            PropertyType propertyType = getProperty1(edit.getFacesConfig());
            assertNotNull(propertyType);
            
            IconType iconType =
                (IconType) FacesConfigModelUtil
                    .findEObjectElementById
                        (propertyType.getIcon(), "validator1_property_icon");
            assertNotNull(iconType);
            
            assertEquals("validator-property-small-icon",
                         iconType.getSmallIcon().getTextContent());
            assertEquals("validator-property-large-icon",
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
    public void testPropertyNameAndClass() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            PropertyType property1 = getProperty1(edit.getFacesConfig());
            assertNotNull(property1);
            
            assertEquals("validator-property-name"
                    ,property1.getPropertyName().getTextContent());
            assertEquals("validator-property-class"
                    ,property1.getPropertyClass().getTextContent());
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
            
            PropertyType propertyType = 
                getProperty1(edit.getFacesConfig());
            assertNotNull(propertyType);
            
            assertEquals("validator property suggested value"
                    ,propertyType.getSuggestedValue().getTextContent());
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
            
            PropertyType propertyType = 
                getProperty1(edit.getFacesConfig());
            assertNotNull(propertyType);
            
            assertEquals("validator property default value"
                    , propertyType.getDefaultValue().getTextContent());
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
//    public void testPropertyExtension() {
//        FacesConfigArtifactEdit edit = null;
//        try {
//            edit = FacesConfigArtifactEdit
//                    .getFacesConfigArtifactEditForRead(project);
//            if (edit.getFacesConfig() != null) {
//                EList comp = edit.getFacesConfig().getComponent();
//                assertTrue(!comp.isEmpty());
//                for (int i = 0; i < comp.size(); i++) {
//                    ComponentType compType = (ComponentType) comp
//                            .get(i);
//                    assertTrue(!compType.getAttribute().isEmpty());
//                    
//                    EList attr = compType.getAttribute();
//                    for (int k = 0; k < attr.size(); k++) {
//                        AttributeType attrType = (AttributeType) attr.get(k);
//                        EList ext= attrType.getAttributeExtension();
//                        assertTrue(ext.size()!=0);
//                        System.out.println("The size of attribute-extension is >>?? " + ext.size() );
//                    }
//                }
//            }
//        } finally {
//            if (edit != null) {
//                edit.dispose();
//            }
//        }
//    }

}
