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
package org.eclipse.jst.jsf.facesconfig.tests.write;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.NullValueType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class WriteManagedBeanTestCase extends TestCase {
	private static final String WEB_INF_FACES_CONFIG2_XML = "WEB-INF/faces-config2.xml";
    IProject project = null;
    
    private final static String  MANAGED_BEAN = "managed-bean";
    private final static String  SCOPE = "scope";
    private final static String  MANAGED_BEAN_NAME = 
        CommonStructuresUtil.createPreficedString(MANAGED_BEAN, CommonStructuresUtil.NAME);
    private final static String  MANAGED_BEAN_SCOPE =
        CommonStructuresUtil.createPreficedString(MANAGED_BEAN, SCOPE);
    private final static String  MANAGED_BEAN_CLASS =
        CommonStructuresUtil.createPreficedString(MANAGED_BEAN, CommonStructuresUtil.CLASS);
    private final static String MANAGED_BEAN_PROPERTY =
        CommonStructuresUtil.createPreficedString(MANAGED_BEAN, "property");
    
	public WriteManagedBeanTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
        
        FacesConfigArtifactEdit edit = null;
        try
        {
            edit =
                FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
                    project, WEB_INF_FACES_CONFIG2_XML);
            edit.getFacesConfig().getManagedBean().clear();
            edit.save(null);
        }
        finally
        {
            if (edit != null)
            {
                edit.dispose();
            }
        }
	}
	
	public void testWriteManagedBean() 
    {
		FacesConfigArtifactEdit edit = null;

        try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, WEB_INF_FACES_CONFIG2_XML);
			assertNotNull(edit.getFacesConfig());
			FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
			FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();

			ManagedBeanType managedBean = createManagedBeanBase();
			
			
            { // null value
                ManagedPropertyType managedPropWithNullValue = 
                    CommonStructuresUtil
                        .createManagedPropertyBase(
                                CommonStructuresUtil.createPreficedString("null", MANAGED_BEAN_PROPERTY));
    
                NullValueType nullValue = facesConfigFactory.createNullValueType();
                nullValue.setId(
                    CommonStructuresUtil.createPreficedString(
                        CommonStructuresUtil.createPreficedString("null", MANAGED_BEAN_PROPERTY)
                        , CommonStructuresUtil.ID));
                managedPropWithNullValue.setNullValue(nullValue);
                managedBean.getManagedProperty().add(managedPropWithNullValue);
            }

            { // value property
    			ManagedPropertyType managedPropWithValue = 
                    CommonStructuresUtil
                        .createManagedPropertyBase(
                                CommonStructuresUtil.createPreficedString("value", MANAGED_BEAN_PROPERTY));
    
                managedPropWithValue.setValue
                    (CommonStructuresUtil.createValue(MANAGED_BEAN_PROPERTY));
    			managedBean.getManagedProperty().add(managedPropWithValue);
            }
                
            { // map property
                ManagedPropertyType managedMapProp = 
                    CommonStructuresUtil
                        .createManagedPropertyBase(
                                CommonStructuresUtil.createPreficedString("map", MANAGED_BEAN_PROPERTY));
    
                managedMapProp.setMapEntries(
                    (CommonStructuresUtil.createMapEntries(MANAGED_BEAN_PROPERTY)));
                managedBean.getManagedProperty()
                    .add(managedMapProp);
            }
            
            { // list property
                ManagedPropertyType managedListProp = 
                    CommonStructuresUtil
                        .createManagedPropertyBase(
                                CommonStructuresUtil.createPreficedString("list", MANAGED_BEAN_PROPERTY));
    
                managedListProp.setListEntries(
                    (CommonStructuresUtil.createListEntries(MANAGED_BEAN_PROPERTY)));
                managedBean.getManagedProperty()
                    .add(managedListProp);
            }
			
			edit.getFacesConfig().getManagedBean().add(managedBean);
			edit.save(null);
		} finally {
			if (edit != null) {
				edit.dispose();
                assertTrue(edit.isDisposed());
                edit = null;
			}
		}

        try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, WEB_INF_FACES_CONFIG2_XML);
			assertNotNull(edit.getFacesConfig());
			
            assertEquals(1, edit.getFacesConfig().getManagedBean().size());
            
            ManagedBeanType managedBean = 
                (ManagedBeanType) edit.getFacesConfig().getManagedBean().get(0);
            
            assertMatchManagedBeanBase(managedBean);
            
            assertEquals(4, managedBean.getManagedProperty().size());
            
            { // null value
                ManagedPropertyType managedPropWithNullValue =
                    (ManagedPropertyType) managedBean.getManagedProperty().get(0);

                CommonStructuresUtil.assertMatchManagedPropertyBase
                    (CommonStructuresUtil.createPreficedString("null", MANAGED_BEAN_PROPERTY)
                        ,managedPropWithNullValue);
                
                NullValueType nullValue = managedPropWithNullValue.getNullValue();
                assertEquals(CommonStructuresUtil.createPreficedString(
                                CommonStructuresUtil.createPreficedString("null", MANAGED_BEAN_PROPERTY)
                                , CommonStructuresUtil.ID)
                             , nullValue.getId());
            }

            { // value property
                ManagedPropertyType managedPropWithValue = 
                    (ManagedPropertyType) managedBean.getManagedProperty().get(1);
                
                CommonStructuresUtil.assertMatchManagedPropertyBase
                    (CommonStructuresUtil.createPreficedString("value", MANAGED_BEAN_PROPERTY)
                        ,managedPropWithValue);

                ValueType valueType = managedPropWithValue.getValue();
                CommonStructuresUtil.assertMatchValue(MANAGED_BEAN_PROPERTY, valueType);
            }
                
            { // map property
                ManagedPropertyType managedMapProp = 
                    (ManagedPropertyType) managedBean.getManagedProperty().get(2);
                
                CommonStructuresUtil
                    .assertMatchManagedPropertyBase(
                            CommonStructuresUtil.createPreficedString("map", MANAGED_BEAN_PROPERTY)
                            , managedMapProp);

               CommonStructuresUtil.assertMatchMapEntries(MANAGED_BEAN_PROPERTY
                                                          ,managedMapProp.getMapEntries());
            }
            
            { // list property
                ManagedPropertyType managedListProp = 
                    (ManagedPropertyType) managedBean.getManagedProperty().get(3);

                CommonStructuresUtil
                    .assertMatchManagedPropertyBase(
                         CommonStructuresUtil.createPreficedString("list", MANAGED_BEAN_PROPERTY)
                         , managedListProp);
    
                CommonStructuresUtil.assertMatchListEntries(MANAGED_BEAN_PROPERTY
                        , managedListProp.getListEntries());
            }
            
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    public void testWriteMapManagedBean()
    {
        FacesConfigArtifactEdit edit = null;

        try 
        {
            edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
                    project, WEB_INF_FACES_CONFIG2_XML);
            assertNotNull(edit.getFacesConfig());
            
            ManagedBeanType managedBean = createManagedBeanBase();
            managedBean.setMapEntries(CommonStructuresUtil.createMapEntries(MANAGED_BEAN));
            
            edit.getFacesConfig().getManagedBean().add(managedBean);
            edit.save(null);
        }
        finally
        {
            if (edit != null) 
            {
                edit.dispose();
                assertTrue(edit.isDisposed());
                edit = null;
            }
        }
        
        try 
        {
            edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
                    project, WEB_INF_FACES_CONFIG2_XML);
            assertNotNull(edit.getFacesConfig());

            assertEquals(1, edit.getFacesConfig().getManagedBean().size());
            ManagedBeanType managedBean = 
                (ManagedBeanType) edit.getFacesConfig().getManagedBean().get(0); 

            assertMatchManagedBeanBase(managedBean);
            MapEntriesType mapEntries = managedBean.getMapEntries();
            CommonStructuresUtil.assertMatchMapEntries(MANAGED_BEAN, mapEntries);
        }
        finally
        {
            if (edit != null)
            {
                edit.dispose();
            }
        }
    }
    
    public void testWriteListManagedBean()
    {
        FacesConfigArtifactEdit edit = null;

        try 
        {
            edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
                    project, WEB_INF_FACES_CONFIG2_XML);
            assertNotNull(edit.getFacesConfig());
            
            ManagedBeanType managedBean = createManagedBeanBase();
            managedBean.setListEntries(CommonStructuresUtil.createListEntries(MANAGED_BEAN));
            edit.getFacesConfig().getManagedBean().add(managedBean);
            edit.save(null);
        }
        finally
        {
            if (edit != null) 
            {
                edit.dispose();
                assertTrue(edit.isDisposed());
                edit = null;
            }
        }
        
        try 
        {
            edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
                    project, WEB_INF_FACES_CONFIG2_XML);
            assertNotNull(edit.getFacesConfig());

            assertEquals(1, edit.getFacesConfig().getManagedBean().size());
            ManagedBeanType managedBean = 
                (ManagedBeanType) edit.getFacesConfig().getManagedBean().get(0); 

            assertMatchManagedBeanBase(managedBean);
            ListEntriesType mapEntries = managedBean.getListEntries();
            CommonStructuresUtil.assertMatchListEntries(MANAGED_BEAN, mapEntries);
        }
        finally
        {
            if (edit != null)
            {
                edit.dispose();
            }
        }
    }
    
    private ManagedBeanType createManagedBeanBase()
    {
        FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
        FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();

        ManagedBeanType managedBean = facesConfigFactory.createManagedBeanType();

        managedBean.getDescription().add(CommonStructuresUtil.createDescription(MANAGED_BEAN));
        managedBean.getDisplayName().add(CommonStructuresUtil.createDisplayName(MANAGED_BEAN));
        managedBean.getIcon().add(CommonStructuresUtil.createIcon(MANAGED_BEAN));

        {
            ManagedBeanNameType managedBeanNameType = facesConfigFactory.createManagedBeanNameType();
            managedBeanNameType.setTextContent(MANAGED_BEAN_NAME);
            managedBeanNameType.setId(
                    CommonStructuresUtil.createPreficedString(MANAGED_BEAN_NAME
                            , CommonStructuresUtil.ID));
            managedBean.setManagedBeanName(managedBeanNameType);
        }
        
        {
            ManagedBeanClassType managedBeanClassType = facesConfigFactory.createManagedBeanClassType();
            managedBeanClassType.setTextContent(MANAGED_BEAN_CLASS);
            managedBeanClassType.setId(
                    CommonStructuresUtil.createPreficedString(MANAGED_BEAN_CLASS
                            , CommonStructuresUtil.ID));
            managedBean.setManagedBeanClass(managedBeanClassType);
        }
        
        {
            ManagedBeanScopeType managedBeanScopeType = facesConfigFactory.createManagedBeanScopeType();
            managedBeanScopeType.setTextContent(MANAGED_BEAN_SCOPE);
            managedBeanScopeType.setId(
                    CommonStructuresUtil.createPreficedString(MANAGED_BEAN_SCOPE
                            , CommonStructuresUtil.ID));
            managedBean.setManagedBeanScope(managedBeanScopeType);
        }
        
        return managedBean;
    }
    
    
    private void assertMatchManagedBeanBase(ManagedBeanType managedBean)
    {
        {
            assertEquals(1, managedBean.getDescription().size());
            DescriptionType description = 
                (DescriptionType) managedBean.getDescription().get(0);
            CommonStructuresUtil.assertMatchesDescription(MANAGED_BEAN, description);
        }
        
        {
            assertEquals(1, managedBean.getDisplayName().size());
            DisplayNameType displayName = 
                (DisplayNameType) managedBean.getDisplayName().get(0);
            CommonStructuresUtil.assertMatchesDisplayName(MANAGED_BEAN, displayName);
        }
        
        {
            assertEquals(1, managedBean.getIcon().size());
            IconType icon = 
                (IconType) managedBean.getIcon().get(0);
            CommonStructuresUtil.assertMatchesIcon(MANAGED_BEAN, icon);
        }
        
        {
            ManagedBeanNameType managedBeanNameType = managedBean.getManagedBeanName();
            assertEquals(MANAGED_BEAN_NAME, managedBeanNameType.getTextContent());
            assertEquals(CommonStructuresUtil.createPreficedString(MANAGED_BEAN_NAME
                            , CommonStructuresUtil.ID)
                         ,managedBeanNameType.getId());
        }
        
        {
            ManagedBeanClassType managedBeanClassType = managedBean.getManagedBeanClass();
            assertEquals(MANAGED_BEAN_CLASS, managedBeanClassType.getTextContent());
            assertEquals(CommonStructuresUtil.createPreficedString(MANAGED_BEAN_CLASS
                              , CommonStructuresUtil.ID)
                         , managedBeanClassType.getId());
        }
        
        {
            ManagedBeanScopeType managedBeanScopeType = managedBean.getManagedBeanScope();
            assertEquals(MANAGED_BEAN_SCOPE, managedBeanScopeType.getTextContent());
            assertEquals(CommonStructuresUtil.createPreficedString(MANAGED_BEAN_SCOPE
                               , CommonStructuresUtil.ID)
                         , managedBeanScopeType.getId());
        }
    }
}

