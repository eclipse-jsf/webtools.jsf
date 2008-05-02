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
package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.FacetType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadFacetRendererTestCase extends BaseReadTestCase {

    public ReadFacetRendererTestCase(String name) {
        super(name);
    }

    /*
     * The following method is used to test for the existence of a single
     * attribute in the Compoenent Element. While testing I had just one with
     * everything (all children) inside it
     */
    public void testSingleFacet() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            FacetType facet1 = getFacet1(edit.getFacesConfig());
            assertNotNull(facet1);
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    private FacetType getFacet1(FacesConfigType facesConfigType)
    {
        RenderKitType renderKit =
            (RenderKitType) FacesConfigModelUtil
            .findEObjectElementById
               (facesConfigType.getRenderKit(), "renderKit1");
        assertNotNull(renderKit);
        
        RendererType renderer =
            (RendererType) FacesConfigModelUtil
            .findEObjectElementById
               (renderKit.getRenderer(), "renderKit1Renderer");
        assertNotNull(renderer);
        
        return (FacetType) FacesConfigModelUtil
                    .findEObjectElementById
                        (renderer.getFacet(), "renderKit1Renderer1Facet1");
    }
    
    /*
     * This is to test the description child inside of Attribute
     * 
     */

    public void testDescription() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            FacetType facetType = 
                 getFacet1(edit.getFacesConfig());
            assertNotNull(facetType);
            
            DescriptionType descType =
                (DescriptionType)FacesConfigModelUtil.findEObjectElementById
                    (facetType.getDescription()
                     ,"renderKit1Renderer1Facet1Description");
            assertEquals("RendererFacetDescription"
                         , descType.getTextContent().trim());
        } 
        finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /*
     * A simple test to check if the Display Name is present 
     * within the faces-config.xml file
     */
    
    public void testDisplayName() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
    
            FacetType facetType = 
                getFacet1(edit.getFacesConfig());
            assertNotNull(facetType);
    
            final DisplayNameType displayNameType =
                (DisplayNameType)FacesConfigModelUtil.findEObjectElementById
                    (facetType.getDisplayName()
                            ,"renderKit1Renderer1Facet1DisplayName");
            assertEquals("RendererFacetDisplayName"
                 , displayNameType.getTextContent().trim());        
        } 
        finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /*
     * Checks  to see if there is an icon defined 
     * 
     */
    public void testIcon() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            FacetType facetType = getFacet1(edit.getFacesConfig());
            assertNotNull(facetType);
            
            IconType iconType =
                (IconType) FacesConfigModelUtil
                    .findEObjectElementById
                        (facetType.getIcon(), "renderKit1Renderer1Facet1Icon");
            assertNotNull(iconType);
            
            assertEquals("facet-renderer-small-icon",
                         iconType.getSmallIcon().getTextContent());
            assertEquals("facet-renderer-large-icon",
                         iconType.getLargeIcon().getTextContent());
        }  finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /**
     * 
     */
    public void testFacetName() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            FacetType facet1 = getFacet1(edit.getFacesConfig());
            assertNotNull(facet1);
            
            assertEquals("rendererFacetName"
                    ,facet1.getFacetName().getTextContent().trim());
        }  finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}
