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
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the FacesConfigFactoryImpl
 * class.
 */
public class WriteFactoryTestCase extends TestCase {
	private static final String WEB_INF_FACES_CONFIG2_XML = "WEB-INF/faces-config2.xml";
    IProject project = null;

    private final static String  FACTORY = "factory";
    private final static String  APPLICATION_FACTORY = "application-factory";
    private final static String  FACESCONTEXT_FACTORY = "faces-context-factory";
    private final static String  LIFECYCLE_FACTORY = "lifecycle-factory";
    private final static String  RENDERKIT_FACTORY = "render-kit-factory";
    
	public WriteFactoryTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}
	
	public void testWriteFacesconfigFile() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, WEB_INF_FACES_CONFIG2_XML);
			assertNotNull(edit.getFacesConfig());
			FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
			FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();

			FactoryType newfactory = facesConfigFactory.createFactoryType();

			{
    			ApplicationFactoryType newApplicationFactory = facesConfigFactory.createApplicationFactoryType();
    			newApplicationFactory.setTextContent(APPLICATION_FACTORY);
                newApplicationFactory.setId(CommonStructuresUtil
                        .createPreficedString(APPLICATION_FACTORY, CommonStructuresUtil.ID));
    			newfactory.getApplicationFactory().add(newApplicationFactory);
            }
            
            {
    			FacesContextFactoryType facesContextFactory = facesConfigFactory.createFacesContextFactoryType();
    			facesContextFactory.setTextContent(FACESCONTEXT_FACTORY);
                facesContextFactory.setId(CommonStructuresUtil
                        .createPreficedString(FACESCONTEXT_FACTORY, CommonStructuresUtil.ID));
    			newfactory.getFacesContextFactory().add(facesContextFactory);
            }	
			
            {
    			LifecycleFactoryType lifecycleFactory = facesConfigFactory.createLifecycleFactoryType();
    			lifecycleFactory.setTextContent(LIFECYCLE_FACTORY);
                lifecycleFactory.setId(CommonStructuresUtil
                        .createPreficedString(LIFECYCLE_FACTORY, CommonStructuresUtil.ID));
    			newfactory.getLifecycleFactory().add(lifecycleFactory);
            }
            
            {
    			RenderKitFactoryType renderKit = facesConfigFactory.createRenderKitFactoryType();
    			renderKit.setTextContent(RENDERKIT_FACTORY);
                renderKit.setId(CommonStructuresUtil
                        .createPreficedString(RENDERKIT_FACTORY, CommonStructuresUtil.ID));
    			newfactory.getRenderKitFactory().add(renderKit);
            }			
			
            newfactory.setId(CommonStructuresUtil
                        .createPreficedString(FACTORY, CommonStructuresUtil.ID));
            
			edit.getFacesConfig().getFactory().add(newfactory);
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
            
            assertEquals(1, edit.getFacesConfig().getFactory().size());
            
            FactoryType newFactory = 
                (FactoryType) edit.getFacesConfig().getFactory().get(0);

            {
                assertEquals(1, newFactory.getApplicationFactory().size());
                ApplicationFactoryType newApplicationFactory = 
                    (ApplicationFactoryType) newFactory.getApplicationFactory().get(0);
                
                assertEquals(APPLICATION_FACTORY, newApplicationFactory.getTextContent());
                assertEquals(CommonStructuresUtil
                                .createPreficedString(APPLICATION_FACTORY, CommonStructuresUtil.ID)
                        , newApplicationFactory.getId());
            }
            
            {
                assertEquals(1, newFactory.getFacesContextFactory().size());
                FacesContextFactoryType facesContextFactory = 
                    (FacesContextFactoryType) newFactory.getFacesContextFactory().get(0);

                assertEquals(FACESCONTEXT_FACTORY, facesContextFactory.getTextContent());
                assertEquals(CommonStructuresUtil
                                .createPreficedString(FACESCONTEXT_FACTORY, CommonStructuresUtil.ID)
                             , facesContextFactory.getId());
            }   
            
            {
                assertEquals(1, newFactory.getLifecycleFactory().size());
                LifecycleFactoryType lifecycleFactory = 
                    (LifecycleFactoryType) newFactory.getLifecycleFactory().get(0);
                
                assertEquals(LIFECYCLE_FACTORY, lifecycleFactory.getTextContent());
                assertEquals(CommonStructuresUtil
                                .createPreficedString(LIFECYCLE_FACTORY, CommonStructuresUtil.ID)
                             , lifecycleFactory.getId());
            }
            
            {
                assertEquals(1, newFactory.getRenderKitFactory().size());
                RenderKitFactoryType renderKit = 
                    (RenderKitFactoryType) newFactory.getRenderKitFactory().get(0);
                
                assertEquals(RENDERKIT_FACTORY, renderKit.getTextContent());
                assertEquals(CommonStructuresUtil
                                  .createPreficedString(RENDERKIT_FACTORY, CommonStructuresUtil.ID)
                             ,renderKit.getId());
            }           
            
            assertEquals(CommonStructuresUtil
                                .createPreficedString(FACTORY, CommonStructuresUtil.ID)
                         ,newFactory.getId());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
}