/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.BaseNameType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.ELResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType;
import org.eclipse.jst.jsf.facesconfig.emf.VarType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadApplicationTestCase_1_2 extends ReadApplicationTestCase {

    public ReadApplicationTestCase_1_2(String name) {
        super(name);
    }

    protected void initialize(TestConfiguration testConfiguration) {
        // override base when not in a configurable test suite
        if(_testConfiguration == null)
        {
            _facesConfigFile = "WEB-INF/faces-config_1_2.xml";
            _facesVersion = IJSFCoreConstants.JSF_VERSION_1_2;
        }
        else
        {
            super.initialize(testConfiguration);
        }
    }

    public void testELResolver()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);   
            ELResolverType elResolverType =
                (ELResolverType) 
                    FacesConfigModelUtil.findEObjectElementById
                        (application1.getELResolver(), "el-resolver-id");
            assertNotNull(elResolverType);
            assertEquals("com.test.MyELResolver",
                    elResolverType.getTextContent().trim());     
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    public void testResourceBundle()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);   
            ResourceBundleType resourceBundle =
                (ResourceBundleType) 
                    FacesConfigModelUtil.findEObjectElementById
                        (application1.getResourceBundle(), "resourceBundleId1");
            assertNotNull(resourceBundle);
            
            // test descriptioni
            {
                DescriptionType descType = 
                    (DescriptionType) FacesConfigModelUtil.findEObjectElementById
                    (resourceBundle.getDescription(), "resourceBundleDesc");
                assertEquals("Resource Bundle description blah"
                             , descType.getTextContent());
            }
            // test displayname
            {
                DisplayNameType displayNameType = 
                    (DisplayNameType) FacesConfigModelUtil.findEObjectElementById
                        (resourceBundle.getDisplayName(), "resourceBundleDisplayName");
                assertEquals("Resource Bundle display name blah"
                             , displayNameType.getTextContent());            
            }
            // test icon
            {
                IconType iconType = 
                    (IconType) FacesConfigModelUtil.findEObjectElementById
                        (resourceBundle.getIcon(), "resourceBundleId");
                assertEquals("resourceBundle-smallIcon"
                             , iconType.getSmallIcon().getTextContent());
                assertEquals("resourceBundle-largeIcon"
                        , iconType.getLargeIcon().getTextContent());
            }
            
            BaseNameType baseName = resourceBundle.getBaseName();
            assertNotNull(baseName);
            assertEquals("com.test.Bundle", baseName.getTextContent());

            VarType var = resourceBundle.getVar();
            assertNotNull(var);
            assertEquals("bundleVar", var.getTextContent());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    public void testApplicationExtension()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);   
            ApplicationExtensionType applicationExt =
                (ApplicationExtensionType) 
                    FacesConfigModelUtil.findEObjectElementById
                        (application1.getApplicationExtension(), "applicatinExtension1");
            assertNotNull(applicationExt);
            assertEquals(1,
                    applicationExt.getChildNodes().size());
            DynamicElement element = 
                (DynamicElement) applicationExt.getChildNodes().get(0);
            assertEquals("some-extension-data", element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}
