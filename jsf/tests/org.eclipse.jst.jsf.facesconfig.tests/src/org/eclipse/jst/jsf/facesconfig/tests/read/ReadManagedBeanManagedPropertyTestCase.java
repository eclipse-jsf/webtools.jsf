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
import org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntryType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the Managed-Property
 * of the Managed-Bean Element
 *
 */
public class ReadManagedBeanManagedPropertyTestCase extends
    BaseReadTestCase {

	public ReadManagedBeanManagedPropertyTestCase(String name) {
		super(name);
	}

    /*
     * Check to see there is at least one property that exists within attribute
     * 
     */
	public void testSingleManagedProperty() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            
            assertNotNull(getManagedProperty(
                    "managedBean1Property1", edit.getFacesConfig()));
            assertNotNull(getManagedProperty("managedBean1Property2"
                    , edit.getFacesConfig()));
            assertNotNull(getManagedProperty("managedBeanMapProperty"
                    , edit.getFacesConfig()));
            assertNotNull(getManagedProperty("managedBeanListProperty"
                    , edit.getFacesConfig()));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
    private ManagedPropertyType getManagedProperty(String property, FacesConfigType facesConfig)
    {
        ManagedBeanType bean =  (ManagedBeanType) FacesConfigModelUtil
            .findEObjectElementById(facesConfig.getManagedBean(), "managedBean1");
        assertNotNull(bean);
        return (ManagedPropertyType)
            FacesConfigModelUtil
                .findEObjectElementById(bean.getManagedProperty(), property);
    }
    
	/*
	 * Get the Description. It simply checks to see if there is at least
	 * one such item
	 * 
	 */
	
	public void testManagedBeanDescriptionGroup() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            
            ManagedPropertyType property = 
                getManagedProperty("managedBean1Property1"
                                   , edit.getFacesConfig());
            assertNotNull(property);
            
            DescriptionType descType =
                (DescriptionType) FacesConfigModelUtil
                    .findEObjectElementById(property.getDescription()
                                        , "managedBean1Property1_description");
            assertNotNull(descType);
            assertEquals("managed-property-description", descType.getTextContent());
            
            DisplayNameType displayName =
                (DisplayNameType) FacesConfigModelUtil
                .findEObjectElementById(property.getDisplayName()
                                        , "managedBean1Property1_displayName");
            assertNotNull(displayName);
            assertEquals("managed-property-display-name"
                         , displayName.getTextContent());
            
            IconType iconType =
                (IconType) FacesConfigModelUtil
                    .findEObjectElementById(property.getIcon()
                                        , "managedBean1Property1_icon");
            assertNotNull(iconType);
            assertEquals("managedBean1-property-small-icon"
                         , iconType.getSmallIcon().getTextContent());
            assertEquals("managedBean1-property-large-icon"
                         , iconType.getLargeIcon().getTextContent());

		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}


	/*
	 * Get both the Property-name and Property-class. It simply checks to see if
	 *  there is at least one each items and check if the names are identical
	 *  as given in the input xml file.
	 * 
	 */
	public void testValueManagedProperty() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());

            ManagedPropertyType property = 
                getManagedProperty("managedBean1Property1"
                        , edit.getFacesConfig());
            assertNotNull(property);
            
            assertEquals("bean-property-name"
                        , property.getPropertyName().getTextContent());
            assertEquals("bean-property-class"
                        , property.getPropertyClass().getTextContent()); 
            assertEquals("bean-propertyValue1"
                        , property.getValue().getTextContent());
            assertNull(property.getNullValue());
            assertNull(property.getMapEntries());
            assertNull(property.getListEntries());
        } finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
    
    public void testNullValueManagedProperty()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            ManagedPropertyType property = 
                getManagedProperty("managedBean1Property2"
                        , edit.getFacesConfig());
            assertNotNull(property);
            
            assertEquals("bean-property2-name"
                        , property.getPropertyName().getTextContent());
            assertEquals("bean-property2-class"
                        , property.getPropertyClass().getTextContent()); 
            assertNotNull(property.getNullValue());
            assertNull(property.getValue());
            assertNull(property.getMapEntries());
            assertNull(property.getListEntries());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    public void testMapValueManagedProperty()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            ManagedPropertyType property = 
                getManagedProperty("managedBeanMapProperty"
                        , edit.getFacesConfig());
            assertNotNull(property);
            
            assertEquals("map-bean-name"
                        , property.getPropertyName().getTextContent());
            assertEquals("map-bean-class"
                        , property.getPropertyClass().getTextContent()); 
            assertNotNull(property.getMapEntries());

            MapEntriesType mapEntries = property.getMapEntries();
            assertEquals("propertyMapEntryKey",
                         mapEntries.getKeyClass().getTextContent());
            assertEquals("propertyMapEntryValueClass",
                    mapEntries.getValueClass().getTextContent());

            MapEntryType mapEntry = 
                (MapEntryType) FacesConfigModelUtil
                    .findEObjectElementById(mapEntries.getMapEntry(), "mapPropertyEntryWithValue");
            assertNotNull(mapEntry);
            assertEquals("propertyMapKey1"
                         , mapEntry.getKey().getTextContent());
            assertEquals("propertyMapValue1"
                         , mapEntry.getValue().getTextContent());
            assertNull(mapEntry.getNullValue());
            mapEntry = 
                (MapEntryType) FacesConfigModelUtil
                    .findEObjectElementById(mapEntries.getMapEntry(), "mapPropertyEntryWithNullValue");
            assertNotNull(mapEntry);
            assertEquals("propertyMapKey2"
                        , mapEntry.getKey().getTextContent());
            assertNotNull(mapEntry.getNullValue());
            assertNull(mapEntry.getValue());
            
            assertNull(property.getNullValue());
            assertNull(property.getValue());
            assertNull(property.getListEntries());

        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    public void testListValueManagedProperty()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            ManagedPropertyType property = 
                getManagedProperty("managedBeanListProperty"
                        , edit.getFacesConfig());
            assertNotNull(property);

            assertEquals("list-bean-name"
                        , property.getPropertyName().getTextContent());
            assertEquals("list-bean-class"
                        , property.getPropertyClass().getTextContent()); 
            assertNotNull(property.getListEntries());

            ListEntriesType listEntries = property.getListEntries();
            assertEquals(1, listEntries.getValue().size());
            assertEquals(1, listEntries.getNullValue().size());

            ValueType valueType =
                (ValueType) FacesConfigModelUtil
                    .findEObjectElementById(listEntries.getValue()
                                          , "managedBeanListProperty_value");
            
            assertEquals("managedBeanListPropertyValue",
                    valueType.getTextContent());
            
            assertNull(property.getNullValue());
            assertNull(property.getValue());
            assertNull(property.getMapEntries());

        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}