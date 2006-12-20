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
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class WriteReferencedBeanTestCase extends TestCase {
	private static final String WEB_INF_FACES_CONFIG2_XML = "WEB-INF/faces-config2.xml";

    IProject project = null;

    private final static String REFERENCED_BEAN = "referenced-bean";
    private final static String REFERENCED_BEAN_NAME =
        CommonStructuresUtil.createPreficedString(REFERENCED_BEAN, CommonStructuresUtil.NAME);
    private final static String REFERENCED_BEAN_CLASS =
        CommonStructuresUtil.createPreficedString(REFERENCED_BEAN, CommonStructuresUtil.CLASS);
    
	public WriteReferencedBeanTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}
	
	public void testWriteReferencedBeanToFileTwo() 
    {
		FacesConfigArtifactEdit edit = null;
		
		try 
        {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, WEB_INF_FACES_CONFIG2_XML);
			assertNotNull(edit.getFacesConfig());
            
			FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
			FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();

			ReferencedBeanType referencedBean = facesConfigFactory.createReferencedBeanType();

            referencedBean.getDescription().add(CommonStructuresUtil.createDescription(REFERENCED_BEAN));
            referencedBean.getDisplayName().add(CommonStructuresUtil.createDisplayName(REFERENCED_BEAN));
            referencedBean.getIcon().add(CommonStructuresUtil.createIcon(REFERENCED_BEAN));

            {
        		ReferencedBeanNameType referencedBeanNameType = 
                    facesConfigFactory.createReferencedBeanNameType();
        		referencedBeanNameType.setTextContent(REFERENCED_BEAN_NAME);
                referencedBeanNameType.setId(
                    CommonStructuresUtil.createPreficedString(
                            REFERENCED_BEAN_NAME, CommonStructuresUtil.ID));
                referencedBean.setReferencedBeanName(referencedBeanNameType);
            }
            
            {
    			ReferencedBeanClassType referencedBeanClassType = 
                    facesConfigFactory.createReferencedBeanClassType();
    			referencedBeanClassType.setTextContent(REFERENCED_BEAN_CLASS);
                referencedBeanClassType.setId(
                        CommonStructuresUtil.createPreficedString(
                                REFERENCED_BEAN_CLASS, CommonStructuresUtil.ID));
    			referencedBean.setReferencedBeanClass(referencedBeanClassType);
            }
            
            referencedBean.setId(
                    CommonStructuresUtil.createPreficedString(REFERENCED_BEAN,CommonStructuresUtil.ID));
            
            edit.getFacesConfig().getReferencedBean().add(referencedBean);
			edit.save(null);
		} finally {
			if (edit != null) {
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

			assertEquals(1, edit.getFacesConfig().getReferencedBean().size());
            ReferencedBeanType referencedBean = (ReferencedBeanType)
                edit.getFacesConfig().getReferencedBean().get(0);
            
            CommonStructuresUtil.assertMatchesDescription(REFERENCED_BEAN
                    , (DescriptionType) referencedBean.getDescription().get(0));
            CommonStructuresUtil.assertMatchesDisplayName(REFERENCED_BEAN
                    , (DisplayNameType) referencedBean.getDisplayName().get(0));
            CommonStructuresUtil.assertMatchesIcon(REFERENCED_BEAN
                    , (IconType) referencedBean.getIcon().get(0));

            {
                ReferencedBeanNameType referencedBeanNameType = 
                    referencedBean.getReferencedBeanName();
                assertEquals(REFERENCED_BEAN_NAME, referencedBeanNameType.getTextContent());
                assertEquals(CommonStructuresUtil.createPreficedString(
                                REFERENCED_BEAN_NAME, CommonStructuresUtil.ID)
                             , referencedBeanNameType.getId()); 
            }
            
            {
                ReferencedBeanClassType referencedBeanClassType = 
                    referencedBean.getReferencedBeanClass();
                assertEquals(REFERENCED_BEAN_CLASS, referencedBeanClassType.getTextContent());
                assertEquals(CommonStructuresUtil.createPreficedString(
                                 REFERENCED_BEAN_CLASS, CommonStructuresUtil.ID)
                             , referencedBeanClassType.getId());
            }

            assertEquals(CommonStructuresUtil.createPreficedString(REFERENCED_BEAN,CommonStructuresUtil.ID)
                        , referencedBean.getId());
        } 
        finally 
        {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
}