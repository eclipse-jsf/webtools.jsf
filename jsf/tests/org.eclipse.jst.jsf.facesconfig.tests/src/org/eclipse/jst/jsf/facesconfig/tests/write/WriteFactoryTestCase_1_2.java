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

import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteFactoryTestCase_1_2 extends WriteFactoryTestCase {
    private final static String EXTENDED_FACTORY_ID = "extended-factory-id";
    
    private final static String FACTORY_EXTENSION = CommonStructuresUtil
            .createPreficedString(FACTORY, "extension");
    private final static String FACTORY_EXTENSION_ID = CommonStructuresUtil
            .createPreficedString(FACTORY_EXTENSION, "id");
    private final static String FACTORY_EXTENSION_TAG = CommonStructuresUtil
            .createPreficedString(FACTORY_EXTENSION, "tag");

    public WriteFactoryTestCase_1_2(String name) {
        super(name);
    }

    public void testFactoryExtension() {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = getArtifactEditForWrite();
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage
                    .getFacesConfigFactory();

            FactoryType factory = facesConfigFactory.createFactoryType();
            factory.setId(EXTENDED_FACTORY_ID);

            FactoryExtensionType extensionType =
                facesConfigFactory.createFactoryExtensionType();
            extensionType.setId(FACTORY_EXTENSION_ID);

            extensionType.getChildNodes().add(createDynamicElement(FACTORY_EXTENSION_TAG));
            
            factory.getFactoryExtension().add(extensionType);
            edit.getFacesConfig().getFactory().add(factory);
            edit.save(null);
        } finally {
            if (edit != null) {
                edit.dispose();
                assertTrue(edit.isDisposed());
                edit = null;
            }
        }
        
        try 
        {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            FactoryType factory = 
                (FactoryType) FacesConfigModelUtil
                    .findEObjectElementById(edit.getFacesConfig().getFactory(), EXTENDED_FACTORY_ID);
            assertNotNull(factory);
            
            assertEquals(1, factory.getFactoryExtension().size());
            FactoryExtensionType extensionType =
                (FactoryExtensionType) factory.getFactoryExtension().get(0);
            assertEquals(FACTORY_EXTENSION_ID, extensionType.getId());

            assertEquals(1, extensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) extensionType.getChildNodes().get(0);
            assertEquals(FACTORY_EXTENSION_TAG, element.getName());
        }
        finally
        {
            
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}
