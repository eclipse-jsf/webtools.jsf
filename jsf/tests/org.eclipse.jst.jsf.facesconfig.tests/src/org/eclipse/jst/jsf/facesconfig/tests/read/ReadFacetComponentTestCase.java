/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.FacetType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/**
 * Test the FacetType on a ComponentType
 * 
 * @author cbateman
 *
 */
public class ReadFacetComponentTestCase extends BaseReadTestCase {

    public ReadFacetComponentTestCase(String name) {
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
    
    private ComponentType getComponent1(FacesConfigType facesConfigType)
    {
        return (ComponentType) FacesConfigModelUtil
            .findEObjectElementById
               (facesConfigType.getComponent(), "component1");
    }
    
    private FacetType getFacet1(FacesConfigType facesConfigType)
    {
        ComponentType component1 = getComponent1(facesConfigType);
        assertNotNull(component1);
        return (FacetType) FacesConfigModelUtil
           .findEObjectElementById
               (component1.getFacet(), "componentFacet1");
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
                     ,"componentFacet1_descripton1");
            assertEquals("Facet1 Description"
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
                            ,"componentFacet1_displayName1");
            assertEquals("Component Facet 1"
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
                        (facetType.getIcon(), "componentFacet1_icon1");
            assertNotNull(iconType);
            
            assertEquals("facet-small-icon",
                         iconType.getSmallIcon().getTextContent());
            assertEquals("facet-large-icon",
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
            
            assertEquals("facetName_chieb_sieb"
                    ,facet1.getFacetName().getTextContent().trim());
        }  finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}
