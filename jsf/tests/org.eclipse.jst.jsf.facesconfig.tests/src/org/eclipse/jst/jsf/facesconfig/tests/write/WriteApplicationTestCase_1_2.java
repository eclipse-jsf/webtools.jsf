/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.jst.jsf.facesconfig.emf.ApplicationExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.BaseNameType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.ELResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType;
import org.eclipse.jst.jsf.facesconfig.emf.VarType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteApplicationTestCase_1_2 extends WriteApplicationTestCase 
{
    protected final static String   APPLICATION_ID_1_2 = "application-id-1_2";
    protected final static String   EL_RESOLVER = "org.test.MyELResolver";
    protected final static String   RESOURCE_BUNDLE = "resourceBundle";
    protected final static String   BASE_NAME = "baseName";
    protected final static String   VAR = "varX";
    protected final static String   APPLICATION_EXTENSION = "application-extension";
    
    public WriteApplicationTestCase_1_2(String name) 
    {
        super(name);
    }

    public void test12Features()
    {
        FacesConfigArtifactEdit edit = null;

        try 
        {
            edit = getArtifactEditForWrite();

            assertNotNull(edit.getFacesConfig());

            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage
                    .getFacesConfigFactory();

            ApplicationType newApplication = facesConfigFactory
                    .createApplicationType();
            newApplication.setId(APPLICATION_ID_1_2);
            
            ELResolverType elResolver = facesConfigFactory.createELResolverType();
            elResolver.setId(CommonStructuresUtil.createPreficedString(EL_RESOLVER, "id"));
            elResolver.setTextContent(EL_RESOLVER);
            
            newApplication.getELResolver().add(elResolver);
            
            ResourceBundleType  resourceBundle = 
                facesConfigFactory.createResourceBundleType();
            
            resourceBundle.getDescription().add(
                    CommonStructuresUtil.createDescription(RESOURCE_BUNDLE));
            resourceBundle.getDisplayName().add(
                    CommonStructuresUtil.createDisplayName(RESOURCE_BUNDLE));
            resourceBundle.getIcon().add(
                    CommonStructuresUtil.createIcon(RESOURCE_BUNDLE));
            
            {
                BaseNameType baseNameType = facesConfigFactory.createBaseNameType();
                baseNameType.setId(CommonStructuresUtil.createPreficedString(BASE_NAME, "id"));
                baseNameType.setTextContent(BASE_NAME);
                resourceBundle.setBaseName(baseNameType);
            }
            
            {
                VarType varType = facesConfigFactory.createVarType();
                varType.setId(CommonStructuresUtil.createPreficedString(VAR, "id"));
                varType.setTextContent(VAR);
                resourceBundle.setVar(varType);
            }
            resourceBundle.setId(CommonStructuresUtil.createPreficedString(RESOURCE_BUNDLE, "id"));
            newApplication.getResourceBundle().add(resourceBundle);

            ApplicationExtensionType appExt = 
                facesConfigFactory.createApplicationExtensionType();
            appExt.setId(
                CommonStructuresUtil.createPreficedString(APPLICATION_EXTENSION, "id"));
            
            DynamicElement element = createDynamicElement(
                    CommonStructuresUtil.createPreficedString(APPLICATION_EXTENSION, "tag"));
            appExt.getChildNodes().add(element);
            newApplication.getApplicationExtension().add(appExt);
            
            edit.getFacesConfig().getApplication().add(newApplication);
            edit.save(null);
        }
        finally
        {
            if (edit != null) {
                edit.dispose();
                // assert that the file has been disposed
                assertTrue(edit.isDisposed());
                edit = null;
            }
        }
        
        // now read back the file
        try 
        {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
//            assertEquals(1, edit.getFacesConfig().getApplication().size());
            ApplicationType application = 
                (ApplicationType) FacesConfigModelUtil
                .findEObjectElementById(edit.getFacesConfig().getApplication(), APPLICATION_ID_1_2);
            assertNotNull(application);
 
            assertEquals(1, application.getELResolver().size());
            ELResolverType elResolver = (ELResolverType) application .getELResolver().get(0);
            assertEquals(CommonStructuresUtil.createPreficedString(EL_RESOLVER, "id"),
                    elResolver.getId());
            assertEquals(EL_RESOLVER, elResolver.getTextContent());

            assertEquals(1, application.getResourceBundle().size());
            ResourceBundleType  resourceBundle = 
                (ResourceBundleType) application.getResourceBundle().get(0);
            
            assertEquals(1, resourceBundle.getDescription().size());
            CommonStructuresUtil.assertMatchesDescription
                (RESOURCE_BUNDLE, (DescriptionType) resourceBundle.getDescription().get(0));
            
            assertEquals(1, resourceBundle.getDisplayName().size());
            CommonStructuresUtil.assertMatchesDisplayName
                (RESOURCE_BUNDLE, (DisplayNameType) resourceBundle.getDisplayName().get(0));

            assertEquals(1, resourceBundle.getIcon().size());
            CommonStructuresUtil.assertMatchesIcon
                (RESOURCE_BUNDLE, (IconType) resourceBundle.getIcon().get(0));
            
            {
                BaseNameType baseNameType = resourceBundle.getBaseName();
                assertEquals(CommonStructuresUtil.createPreficedString(BASE_NAME, "id"),
                        baseNameType.getId());
                assertEquals(BASE_NAME, baseNameType.getTextContent());
            }
            
            {
                VarType varType = resourceBundle.getVar();
                assertEquals(CommonStructuresUtil.createPreficedString(VAR, "id"),
                        varType.getId());
                assertEquals(VAR, varType.getTextContent());

            }
            assertEquals(CommonStructuresUtil.createPreficedString(RESOURCE_BUNDLE, "id"),
                    resourceBundle.getId());
            
            ApplicationExtensionType appExt = 
                (ApplicationExtensionType) FacesConfigModelUtil
                .findEObjectElementById(application.getApplicationExtension()
                        , CommonStructuresUtil.createPreficedString(APPLICATION_EXTENSION, "id"));
            
            assertEquals(1, appExt.getChildNodes().size());
            DynamicElement element = 
                (DynamicElement) appExt.getChildNodes().get(0);
            assertEquals(CommonStructuresUtil.createPreficedString(APPLICATION_EXTENSION, "tag")
                    ,element.getName());
        }
        finally
        {
            if (edit != null)
            {
                edit.dispose();
            }
        }
    }
    
//    private ApplicationType getApplication(FacesConfigType facesConfig)
//    {
//        FacesConfigModelUtil
//    }
}

