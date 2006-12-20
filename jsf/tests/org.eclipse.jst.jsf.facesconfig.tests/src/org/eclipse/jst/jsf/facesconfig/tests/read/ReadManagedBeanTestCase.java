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
import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntryType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the managed-bean which is one of 
 * many items inside the root elemnt faces-config in the configuration
 * information hierarchy of the faces-config.xml file 
 *
 */
public class ReadManagedBeanTestCase extends TestCase {
	IProject project = null;

	public ReadManagedBeanTestCase(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 *Test to see if there is at least one managed-bean.
	 *This should be specified in the file for reading (faces-config)
	 */
	public void testManagedBean() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			assertNotNull(edit.getFacesConfig());
            assertNotNull(getManagedBean("managedBean1", edit.getFacesConfig()));
            assertNotNull(getManagedBean("mapBean1", edit.getFacesConfig()));
            assertNotNull(getManagedBean("listBean1", edit.getFacesConfig()));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    private ManagedBeanType getManagedBean(String name, FacesConfigType facesConfig)
    {
        return (ManagedBeanType) FacesConfigModelUtil
            .findEObjectElementById(facesConfig.getManagedBean(), name);
    }
    
	// Test for the Descirption
	public void testDescriptionGroup() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			assertNotNull(edit.getFacesConfig());
            
            final ManagedBeanType managedBean1 = 
                getManagedBean("managedBean1", edit.getFacesConfig());
            
            DescriptionType descType =
                (DescriptionType) FacesConfigModelUtil
                    .findEObjectElementById(managedBean1.getDescription()
                                        , "managedBean1Description");
            assertNotNull(descType);
            assertEquals("managed-bean-description", descType.getTextContent());
            
            DisplayNameType displayName =
                (DisplayNameType) FacesConfigModelUtil
                .findEObjectElementById(managedBean1.getDisplayName()
                                        , "managedBean1DisplayName");
            assertNotNull(displayName);
            assertEquals("managed-bean-display-name"
                         , displayName.getTextContent());
            
            IconType iconType =
                (IconType) FacesConfigModelUtil
                    .findEObjectElementById(managedBean1.getIcon()
                                        , "managedBean1Icon");
            assertNotNull(iconType);
            assertEquals("managedBean1-small-icon"
                         , iconType.getSmallIcon().getTextContent());
            assertEquals("managedBean1-large-icon"
                         , iconType.getLargeIcon().getTextContent());
                
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}


	/*
	 * Check for a managed-bean-class
	 */
	public void testSingleValuedProperties() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			assertNotNull(edit.getFacesConfig());
            
            {
                final ManagedBeanType managedBean1 = 
                    getManagedBean("managedBean1", edit.getFacesConfig());
                
                assertEquals("managed-bean-name",
                             managedBean1.getManagedBeanName().getTextContent());
                assertEquals("managed-bean-class",
                             managedBean1.getManagedBeanClass().getTextContent());
                assertEquals("request",
                             managedBean1.getManagedBeanScope().getTextContent());
            }
            
            {
                final ManagedBeanType mapBean1 = 
                    getManagedBean("mapBean1", edit.getFacesConfig());
                
                assertEquals("mapBean1",
                        mapBean1.getManagedBeanName().getTextContent());
                assertEquals("mapBean1-class",
                        mapBean1.getManagedBeanClass().getTextContent());
                assertEquals("mapBean1-scope",
                        mapBean1.getManagedBeanScope().getTextContent());
            }
            
            {
                final ManagedBeanType listBean1 = 
                    getManagedBean("listBean1", edit.getFacesConfig());
                
                assertEquals("listBean1",
                        listBean1.getManagedBeanName().getTextContent());
                assertEquals("listBean1-class",
                        listBean1.getManagedBeanClass().getTextContent());
                assertEquals("listBean1-scope",
                        listBean1.getManagedBeanScope().getTextContent());
            }
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
    
    public void testMapBeanProperties()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = FacesConfigArtifactEdit
                    .getFacesConfigArtifactEditForRead(project);
            assertNotNull(edit.getFacesConfig());

            ManagedBeanType bean = 
                getManagedBean("mapBean1"
                        , edit.getFacesConfig());
            assertNotNull(bean);

            MapEntriesType mapEntries = bean.getMapEntries();
            
            assertEquals("mapBeanEntryKey",
                         mapEntries.getKeyClass().getTextContent());
            assertEquals("mapBeanEntryValueClass",
                    mapEntries.getValueClass().getTextContent());
            
            MapEntryType mapEntry = 
                (MapEntryType) FacesConfigModelUtil
                    .findEObjectElementById(mapEntries.getMapEntry(), "mapBeanEntryWithValue");
            assertNotNull(mapEntry);
            assertEquals("mapBeanKey1"
                         , mapEntry.getKey().getTextContent());
            assertEquals("mapBeanValue1"
                         , mapEntry.getValue().getTextContent());
            assertNull(mapEntry.getNullValue());
            
            mapEntry = 
                (MapEntryType) FacesConfigModelUtil
                    .findEObjectElementById(mapEntries.getMapEntry(), "mapBeanEntryWithNullValue");
            assertNotNull(mapEntry);
            assertEquals("mapBeanKey2"
                        , mapEntry.getKey().getTextContent());
            assertNotNull(mapEntry.getNullValue());
            assertNull(mapEntry.getValue());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    public void testListBeanProperties()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = FacesConfigArtifactEdit
                    .getFacesConfigArtifactEditForRead(project);
            assertNotNull(edit.getFacesConfig());

            ManagedBeanType bean = 
                getManagedBean("listBean1"
                        , edit.getFacesConfig());
            assertNotNull(bean);

            ListEntriesType listEntries = bean.getListEntries();
            
            assertEquals("listBeanListPropertyClass",
                    listEntries.getValueClass().getTextContent());
            assertEquals(1, listEntries.getValue().size());
            assertEquals(1, listEntries.getNullValue().size());
            
            ValueType valueType = 
                (ValueType) FacesConfigModelUtil
                    .findEObjectElementById(listEntries.getValue()
                                    , "listBeanListProperty_value");
            assertNotNull(valueType);
            assertEquals("listBeanListPropertyValue", valueType.getTextContent().trim());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}