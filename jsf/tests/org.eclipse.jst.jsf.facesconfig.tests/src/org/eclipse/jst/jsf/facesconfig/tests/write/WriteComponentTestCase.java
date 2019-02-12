/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacetType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class WriteComponentTestCase extends BaseWriteTestCase {

    private static final String COMPONENT = "component";
    
    private static final String COMPONENT_CLASS = "component-class";

    private static final String COMPONENT_TYPE = "component-type";

	public WriteComponentTestCase(String name) {
		super(name);
	}

	public void testWriteComponent() {
	    FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForWrite();
			assertNotNull(edit.getFacesConfig());
			FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
			FacesConfigFactory facesConfigFactory = facesConfigPackage
					.getFacesConfigFactory();

			ComponentType newComponent = facesConfigFactory
					.createComponentType();

            newComponent.getDescription().add(CommonStructuresUtil.createDescription(COMPONENT));
            newComponent.getDisplayName().add(CommonStructuresUtil.createDisplayName(COMPONENT));
            newComponent.getIcon().add(CommonStructuresUtil.createIcon(COMPONENT));
            
            {
				ComponentTypeType componentTypeType = facesConfigFactory.createComponentTypeType();
				componentTypeType.setTextContent(COMPONENT_TYPE);
                componentTypeType.setId(CommonStructuresUtil.createPreficedString(COMPONENT_TYPE
                        , CommonStructuresUtil.ID));
				newComponent.setComponentType(componentTypeType);
            }
            
            {
				ComponentClassType componentClassType = facesConfigFactory.createComponentClassType();
				componentClassType.setTextContent(COMPONENT_CLASS);
                componentClassType.setId(CommonStructuresUtil.createPreficedString(COMPONENT_CLASS
                        , CommonStructuresUtil.ID));
				newComponent.setComponentClass(componentClassType);
            }

            // attributes
			newComponent.getAttribute().add(CommonStructuresUtil.createAttribute(COMPONENT));

			// property section
			newComponent.getProperty().add(CommonStructuresUtil.createProperty(COMPONENT));

            // facets
            newComponent.getFacet().add(CommonStructuresUtil.createFacet(COMPONENT));
            
            // component-extension
            ComponentExtensionType ext = 
                facesConfigFactory.createComponentExtensionType();
            
            DynamicElement element = facesConfigFactory.createDynamicElement();
            element.setName("some-meta-data");
            ext.getChildNodes().add(element);
            newComponent.getComponentExtension().add(ext);
            
			edit.getFacesConfig().getComponent().add(newComponent);
			edit.save(null);
		} finally {
			if (edit != null) {
				edit.dispose();
                assertTrue(edit.isDisposed());
                edit = null;
			}
		}

		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            
            assertEquals(1, edit.getFacesConfig().getComponent().size());
            
            final ComponentType component = 
                (ComponentType) edit.getFacesConfig().getComponent().get(0);
            
            assertEquals(1, component.getDescription().size());
            CommonStructuresUtil.assertMatchesDescription
                (COMPONENT, (DescriptionType) component.getDescription().get(0));
            
            assertEquals(1, component.getDisplayName().size());
            CommonStructuresUtil.assertMatchesDisplayName
                (COMPONENT, (DisplayNameType)component.getDisplayName().get(0));
            
            assertEquals(1, component.getIcon().size());
            CommonStructuresUtil.assertMatchesIcon
                (COMPONENT, (IconType)component.getIcon().get(0));
            {
                ComponentTypeType componentTypeType = component.getComponentType();
                assertEquals(COMPONENT_TYPE
                        , componentTypeType.getTextContent());
                assertEquals(CommonStructuresUtil.createPreficedString(COMPONENT_TYPE
                            , CommonStructuresUtil.ID)
                        , componentTypeType.getId());
            }
            
            {
                ComponentClassType componentClassType = component.getComponentClass();
                assertEquals(COMPONENT_CLASS
                        , componentClassType.getTextContent());
                assertEquals(CommonStructuresUtil.createPreficedString(COMPONENT_CLASS
                            , CommonStructuresUtil.ID)
                        , componentClassType.getId());
            }
            
            assertEquals(1, component.getAttribute().size());
            CommonStructuresUtil.assertMatchAttribute(COMPONENT
                    ,(AttributeType) component.getAttribute().get(0));
            
            assertEquals(1, component.getProperty().size());
            CommonStructuresUtil.assertMatchProperty(COMPONENT
                    ,(PropertyType) component.getProperty().get(0));

            assertEquals(1, component.getFacet().size());
            CommonStructuresUtil.assertMatchFacet(COMPONENT
                    ,(FacetType) component.getFacet().get(0));
            
            // component-extension
            assertEquals(1, component.getComponentExtension().size());
            ComponentExtensionType ext =
                (ComponentExtensionType) component.getComponentExtension().get(0);
            
            assertEquals(1, ext.getChildNodes().size());
            DynamicElement element = 
                (DynamicElement)ext.getChildNodes().get(0);
            assertEquals("some-meta-data", element.getName());
           
		} finally {

			if (edit != null) {
				edit.dispose();
			}
		}
    }
}