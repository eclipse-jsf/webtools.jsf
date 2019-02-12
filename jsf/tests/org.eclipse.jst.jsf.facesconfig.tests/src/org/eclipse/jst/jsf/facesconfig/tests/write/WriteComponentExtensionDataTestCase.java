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
package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteComponentExtensionDataTestCase extends BaseWriteTestCase {
    private static final String COMPONENT_ID = "component1";
    
    private static final String COMPONENT_CLASS = "component-class";

    private static final String COMPONENT_TYPE = "component-type";

    public WriteComponentExtensionDataTestCase(String name) {
        super(name);
    }


    public void testWriteExtensionData()
    {
        FacesConfigArtifactEdit edit = null;
        try 
        {
            edit = FacesConfigArtifactEdit
                .getFacesConfigArtifactEditForWrite(project, "WEB-INF/faces-config-ext-data1.xml");
            assertNotNull(edit);
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage
                    .getFacesConfigFactory();

            ComponentType newComponent = 
                (ComponentType) FacesConfigModelUtil
                    .findEObjectElementById(edit.getFacesConfig()
                            .getComponent(), COMPONENT_ID);

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
            
//            ComponentExtensionType extType =
//                facesConfigFactory.createComponentExtensionType();
//            extType.setId(CommonStructuresUtil.createPreficedString(COMPONENT, "extension-id"));
//            
//            DynamicElement singleRoot = facesConfigFactory.createDynamicElement();
//            singleRoot.setName("any-data");
//            DynamicAttribute attribute = facesConfigFactory.createDynamicAttribute();
//            attribute.setName("some-attribute");
//            attribute.setValue("some-value");
//            singleRoot.getAttributes().add(attribute);
//            
//            DynamicElement firstChild = facesConfigFactory.createDynamicElement();
//            firstChild.setName("first-child");
//            singleRoot.getChildNodes().add(firstChild);
//            
//            DynamicElement secondChild =
//                facesConfigFactory.createDynamicElement();
//            secondChild.setName("second-child");
//            //secondChild.setTextContent("secondChild text content");
//            singleRoot.getChildNodes().add(secondChild);
//            extType.getChildNodes().add(singleRoot);
//            newComponent.getComponentExtension().add(extType);
            
            edit.getFacesConfig().getComponent().add(newComponent);
            edit.save(null);
        } finally {
            if (edit != null) {
                edit.dispose();
                assertTrue(edit.isDisposed());
                edit = null;
            }
        }
    }
}
