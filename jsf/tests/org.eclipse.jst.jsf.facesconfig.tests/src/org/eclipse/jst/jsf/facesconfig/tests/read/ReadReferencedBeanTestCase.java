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
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
/*
 * This Junit class is used to test the referenced-bean which is one of 
 * many items inside the root elemnt faces-config in the configuration
 * information hierarchy of the faces-config.xml file 
 *
 */
public class ReadReferencedBeanTestCase extends TestCase {
	IProject project = null;

	public ReadReferencedBeanTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 * check for the existence of referenced-bean with in
	 * the root element - faces-config. There should be at least
	 * one of such kind (item)
	 */
	public void testSingleReferncedBean() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			assertNotNull(edit.getFacesConfig());
            
			assertNotNull(getReferencedBean1(edit.getFacesConfig()));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    private ReferencedBeanType getReferencedBean1(FacesConfigType facesConfig)
    {
        return (ReferencedBeanType)
            FacesConfigModelUtil
                .findEObjectElementById(facesConfig.getReferencedBean()
                        , "referencedBean1");
    }
    
	// Test for the Descirption
	public void testDescriptionGroup() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			assertNotNull(edit.getFacesConfig());
                
            ReferencedBeanType referencedBean =
                getReferencedBean1(edit.getFacesConfig());
            assertNotNull(referencedBean);
            
            DescriptionType descType =
                (DescriptionType) FacesConfigModelUtil
                    .findEObjectElementById(referencedBean.getDescription()
                                        , "referencedBean1Description");
            assertNotNull(descType);
            assertEquals("Referenced Bean Desc", descType.getTextContent());
            
            DisplayNameType displayName =
                (DisplayNameType) FacesConfigModelUtil
                .findEObjectElementById(referencedBean.getDisplayName()
                                        , "referencedBean1DisplayName");
            assertNotNull(displayName);
            assertEquals("referenced-bean-display"
                         , displayName.getTextContent());
            
            IconType iconType =
                (IconType) FacesConfigModelUtil
                    .findEObjectElementById(referencedBean.getIcon()
                                        , "referencedBean1Icon");
            assertNotNull(iconType);
            assertEquals("referenced-bean-small-icon"
                         , iconType.getSmallIcon().getTextContent());
            assertEquals("referenced-bean-large-icon"
                         , iconType.getLargeIcon().getTextContent());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	
	/*
	 * check for refrenced-bean-name
	 */
	public void testSingleValuedProperties(){
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			assertNotNull(edit.getFacesConfig());
            
            ReferencedBeanType referencedBean =
                getReferencedBean1(edit.getFacesConfig());
            assertNotNull(referencedBean);

            assertEquals("referenced-bean-name"
                        ,referencedBean.getReferencedBeanName().getTextContent().trim());
            assertEquals("referenced-bean-class"
                    ,referencedBean.getReferencedBeanClass().getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
}