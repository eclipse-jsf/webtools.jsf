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
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteManagedBeanTestCase_1_2 extends WriteManagedBeanTestCase {
    private final static String EXTENDED_MANAGED_BEAN_ID = "extended-managed-bean-id";

    private final static String MANAGED_BEAN_EXTENSION = CommonStructuresUtil
            .createPreficedString(MANAGED_BEAN, "extension");
    private final static String MANAGED_BEAN_EXTENSION_ID = CommonStructuresUtil
            .createPreficedString(MANAGED_BEAN_EXTENSION, "id");
    private final static String MANAGED_BEAN_EXTENSION_TAG = CommonStructuresUtil
            .createPreficedString(MANAGED_BEAN_EXTENSION, "tag");

    public WriteManagedBeanTestCase_1_2(String name) {
        super(name);
    }

    public void testManagedBeanExtension() {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = getArtifactEditForWrite();
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage
                    .getFacesConfigFactory();

            ManagedBeanType managedBean = facesConfigFactory.createManagedBeanType();
            managedBean.setId(EXTENDED_MANAGED_BEAN_ID);

            ManagedBeanExtensionType extensionType = facesConfigFactory
                    .createManagedBeanExtensionType();
            extensionType.setId(MANAGED_BEAN_EXTENSION_ID);

            extensionType.getChildNodes().add(
                    createDynamicElement(MANAGED_BEAN_EXTENSION_TAG));

            managedBean.getManagedBeanExtension().add(extensionType);
            edit.getFacesConfig().getManagedBean().add(managedBean);
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

            ManagedBeanType managedBean = (ManagedBeanType) FacesConfigModelUtil
                    .findEObjectElementById(edit.getFacesConfig()
                            .getManagedBean(), EXTENDED_MANAGED_BEAN_ID);
            assertNotNull(managedBean);

            assertEquals(1, managedBean.getManagedBeanExtension().size());
            ManagedBeanExtensionType extensionType = (ManagedBeanExtensionType) managedBean
                    .getManagedBeanExtension().get(0);
            assertEquals(MANAGED_BEAN_EXTENSION_ID, extensionType.getId());

            assertEquals(1, extensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) extensionType
                    .getChildNodes().get(0);
            assertEquals(MANAGED_BEAN_EXTENSION_TAG, element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}
