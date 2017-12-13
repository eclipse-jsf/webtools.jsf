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

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the for the existence of
 * renderer
 */
public class ReadRendererTestCase extends BaseReadTestCase {

	public ReadRendererTestCase(String name) {
		super(name);
	}

	/*
	 * Check to see if there at least one Renderer in place
	 */
	public void testSingleRenderer() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            
            assertNotNull(getRenderer1(edit.getFacesConfig()));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
    private RendererType getRenderer1(FacesConfigType facesConfig)
    {
        RenderKitType renderKitType =
            (RenderKitType) FacesConfigModelUtil
                .findEObjectElementById(facesConfig.getRenderKit()
                                        , "renderKit1");
        assertNotNull(renderKitType);
        
        return 
            (RendererType) FacesConfigModelUtil
                .findEObjectElementById(renderKitType.getRenderer()
                        , "renderKit1Renderer");
    }
    
	/*
	 * check for Description. It simply checks to see if there is at least
	 * one such item
	 * 
	 */

	public void testDescriptionGroup() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());

            RendererType renderKit = getRenderer1(edit.getFacesConfig());
            assertNotNull(renderKit);
            
            DescriptionType descType =
                (DescriptionType) FacesConfigModelUtil
                    .findEObjectElementById(renderKit.getDescription()
                                        , "renderKit1RendererDescription");
            assertNotNull(descType);
            assertEquals("my renderer description", descType.getTextContent());
            
            DisplayNameType displayName =
                (DisplayNameType) FacesConfigModelUtil
                .findEObjectElementById(renderKit.getDisplayName()
                                        , "renderKit1RendererDisplayName");
            assertNotNull(displayName);
            assertEquals("my renderer display name"
                         , displayName.getTextContent());
            
            IconType iconType =
                (IconType) FacesConfigModelUtil
                    .findEObjectElementById(renderKit.getIcon()
                                        , "renderKit1RendererIcon");
            assertNotNull(iconType);
            assertEquals("renderer-small-icon"
                         , iconType.getSmallIcon().getTextContent());
            assertEquals("renderer-large-icon"
                         , iconType.getLargeIcon().getTextContent());

        } finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	/*
	 * This one tests for the existence of three items
	 * They are the required items by all renderers
	 * They are : renderer-type, renderer-class and component-family.
	 * It thought it was better to put them together instead of
	 * writing single -separate methods for all of them. 
	 */
	public void testStringForRequiredEntries() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());

            RendererType renderer = getRenderer1(edit.getFacesConfig());
            assertNotNull(renderer);

			assertEquals("renderer-type" 
                    , renderer.getRendererType().getTextContent());
			assertEquals("renderer-class"
                    , renderer.getRendererClass().getTextContent());
			assertEquals("component-family"
                    ,renderer.getComponentFamily().getTextContent());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	/**
	 * Basic check.  See ReadAttributeRendererTestCase for detailed
     * testing 
	 */
	public void testAttribute() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			
            RendererType renderer = getRenderer1(edit.getFacesConfig());
            assertNotNull(renderer);
            
            assertEquals(1, renderer.getAttribute().size());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
    public void testFacet()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            RendererType renderer = getRenderer1(edit.getFacesConfig());
            assertNotNull(renderer);
            
            assertEquals(1, renderer.getFacet().size());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
	/*
	 * check for Renderer-Extension. It simply checks to see if there is at least
	 * one such item
	 * 
	 */
	
	public void testRendererExtension() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			if (edit.getFacesConfig() != null) {
				EList renderKit = edit.getFacesConfig().getRenderKit();
				assertTrue(!renderKit.isEmpty());
				for (int i = 0; i < renderKit.size(); i++) {
					RenderKitType rendererType = (RenderKitType) renderKit
							.get(i);
					assertTrue(!rendererType.getRenderer().isEmpty());
					EList rend = rendererType.getRenderer();
					for (int k = 0; k < rend.size(); k++) {
						RendererType rendType = (RendererType) rend.get(k);
						EList ext= rendType.getRendererExtension();
						assertTrue(ext.size()!=0);
					}
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
}