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
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacetType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class WriteRenderKitTestCase extends TestCase 
{
	IProject project = null;
    
    private final static String RENDER_KIT = "render-kit";
    private final static String RENDER_KIT_CLASS = 
        CommonStructuresUtil.createPreficedString(RENDER_KIT, CommonStructuresUtil.CLASS);
    private final static String RENDERER = "renderer";
    private final static String COMPONENT_FAMILY = "component-family";
    private final static String RENDERER_COMPONENT_FAMILY = 
        CommonStructuresUtil.createPreficedString(RENDERER, COMPONENT_FAMILY);
    private final static String RENDERER_TYPE =
        CommonStructuresUtil.createPreficedString(RENDERER, "type");
    private final static String RENDERER_CLASS =
        CommonStructuresUtil.createPreficedString(RENDERER, CommonStructuresUtil.CLASS);

	public WriteRenderKitTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}
	
	public void testWriteRenderKit() {
		//IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;
		
		try 
        {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			assertNotNull(edit.getFacesConfig());
			FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
			FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();

			RenderKitType renderKit = facesConfigFactory.createRenderKitType();

			renderKit.getDescription().add(CommonStructuresUtil.createDescription(RENDER_KIT));
            renderKit.getDisplayName().add(CommonStructuresUtil.createDisplayName(RENDER_KIT));
            renderKit.getIcon().add(CommonStructuresUtil.createIcon(RENDER_KIT));
            
            {
                RenderKitIdType renderKitIdType = facesConfigFactory.createRenderKitIdType();
    			renderKitIdType.setTextContent(RENDER_KIT);
                renderKitIdType.setId
                    (CommonStructuresUtil.createPreficedString(RENDER_KIT, CommonStructuresUtil.ID));
    			renderKit.setRenderKitId(renderKitIdType);
            }
            
            {
    			RenderKitClassType renderKitClassType = facesConfigFactory.createRenderKitClassType();
    			renderKitClassType.setTextContent(RENDER_KIT_CLASS);
                renderKitClassType.setId(
                    CommonStructuresUtil.createPreficedString(RENDER_KIT_CLASS, CommonStructuresUtil.ID));
    			renderKit.setRenderKitClass(renderKitClassType);
            }
            
			RendererType renderer = facesConfigFactory.createRendererType();

            renderer.getDescription().add(CommonStructuresUtil.createDescription(RENDERER));
            renderer.getDisplayName().add(CommonStructuresUtil.createDisplayName(RENDERER));
            renderer.getIcon().add(CommonStructuresUtil.createIcon(RENDERER));
            
            {
    			ComponentFamilyType componentFamily = 
                    facesConfigFactory.createComponentFamilyType();
    			componentFamily.setTextContent(RENDERER_COMPONENT_FAMILY);
                componentFamily.setId(
                    CommonStructuresUtil.createPreficedString(RENDERER_COMPONENT_FAMILY, CommonStructuresUtil.ID));
    			renderer.setComponentFamily(componentFamily);
            }
            
            {
    			RendererTypeType rendererTypeType = facesConfigFactory.createRendererTypeType();
    			rendererTypeType.setTextContent(RENDERER_TYPE);
                rendererTypeType.setId(
                    CommonStructuresUtil.createPreficedString(RENDERER_TYPE, CommonStructuresUtil.ID));
    			renderer.setRendererType(rendererTypeType);
            }

            {
    			RendererClassType rendererClassType = facesConfigFactory.createRendererClassType();
    			rendererClassType.setTextContent(RENDERER_CLASS);
                rendererClassType.setId(
                        CommonStructuresUtil.createPreficedString(RENDERER_CLASS, CommonStructuresUtil.ID));
    			renderer.setRendererClass(rendererClassType);
            }
            
            renderer.getAttribute().add(CommonStructuresUtil.createAttribute(RENDERER));
            renderer.getFacet().add(CommonStructuresUtil.createFacet(RENDERER));
            renderer.setId(CommonStructuresUtil.createPreficedString(RENDERER, CommonStructuresUtil.ID));
            
			renderKit.getRenderer().add(renderer);
			renderKit.setId(CommonStructuresUtil.createPreficedString(RENDER_KIT, CommonStructuresUtil.ID));
            
			edit.getFacesConfig().getRenderKit().add(renderKit);
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
					project, "WEB-INF/faces-config2.xml");
			assertNotNull(edit.getFacesConfig());
			assertEquals(1, edit.getFacesConfig().getRenderKit().size());
            
            RenderKitType renderKit = 
                (RenderKitType) edit.getFacesConfig().getRenderKit().get(0);
            
            assertEquals(1, renderKit.getDescription().size());
            CommonStructuresUtil.assertMatchesDescription(RENDER_KIT,
                (DescriptionType) renderKit.getDescription().get(0));

            assertEquals(1, renderKit.getDisplayName().size());
            CommonStructuresUtil.assertMatchesDisplayName(RENDER_KIT,
                    (DisplayNameType) renderKit.getDisplayName().get(0));

            assertEquals(1, renderKit.getIcon().size());
            CommonStructuresUtil.assertMatchesIcon(RENDER_KIT,
                    (IconType) renderKit.getIcon().get(0));
            
            {

                RenderKitIdType renderKitIdType = renderKit.getRenderKitId();
                assertEquals(RENDER_KIT, renderKitIdType.getTextContent());
                assertEquals(CommonStructuresUtil.createPreficedString(RENDER_KIT, CommonStructuresUtil.ID)
                        , renderKitIdType.getId());
            }
            
            {
                RenderKitClassType renderKitClassType = renderKit.getRenderKitClass();
                assertEquals(RENDER_KIT_CLASS, renderKitClassType.getTextContent());
                assertEquals(
                    CommonStructuresUtil.createPreficedString(RENDER_KIT_CLASS, CommonStructuresUtil.ID)
                    ,renderKitClassType.getId());
            }
            
            assertEquals(1, renderKit.getRenderer().size());
            RendererType renderer = (RendererType) renderKit.getRenderer().get(0);

            assertEquals(1, renderer.getDescription().size());
            CommonStructuresUtil.assertMatchesDescription(RENDERER
                    ,(DescriptionType) renderer.getDescription().get(0));

            assertEquals(1, renderer.getDisplayName().size());
            CommonStructuresUtil.assertMatchesDisplayName(RENDERER
                    , (DisplayNameType) renderer.getDisplayName().get(0));

            assertEquals(1, renderer.getIcon().size());
            CommonStructuresUtil.assertMatchesIcon(RENDERER
                    , (IconType) renderer.getIcon().get(0));
            
            {
                ComponentFamilyType componentFamily = renderer.getComponentFamily();
                
                assertEquals(RENDERER_COMPONENT_FAMILY
                        , componentFamily.getTextContent());
                
                assertEquals(CommonStructuresUtil.createPreficedString(RENDERER_COMPONENT_FAMILY, CommonStructuresUtil.ID)
                        , componentFamily.getId());
            }
            
            {
                RendererTypeType rendererTypeType = renderer.getRendererType();
                assertEquals(RENDERER_TYPE
                    , rendererTypeType.getTextContent());
                
                assertEquals(CommonStructuresUtil.createPreficedString(RENDERER_TYPE, CommonStructuresUtil.ID)
                    , rendererTypeType.getId());
            }

            {
                RendererClassType rendererClassType = renderer.getRendererClass();
                assertEquals(RENDERER_CLASS, rendererClassType.getTextContent());
                assertEquals(CommonStructuresUtil.createPreficedString(RENDERER_CLASS, CommonStructuresUtil.ID)
                    , rendererClassType.getId());
            }

            assertEquals(1, renderer.getAttribute().size());
            CommonStructuresUtil.assertMatchAttribute(RENDERER
                , (AttributeType) renderer.getAttribute().get(0));
            
            assertEquals(1, renderer.getFacet().size());
            CommonStructuresUtil.assertMatchFacet(RENDERER
                , (FacetType) renderer.getFacet().get(0));
            
            assertEquals(CommonStructuresUtil.createPreficedString(RENDERER, CommonStructuresUtil.ID)
                , renderer.getId());

            assertEquals(CommonStructuresUtil.createPreficedString(RENDER_KIT, CommonStructuresUtil.ID)
                , renderKit.getId());

        } finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
}
