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
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteLifecycleTestCase_1_2 extends WriteLifecycleTestCase {
    private final static String EXTENDED_LIFECYCLE_ID = "extended-lifecycle-id";

    private final static String LIFECYCLE_EXTENSION = CommonStructuresUtil
            .createPreficedString(LIFECYCLE, "extension");
    private final static String LIFECYCLE_EXTENSION_ID = CommonStructuresUtil
            .createPreficedString(LIFECYCLE_EXTENSION, "id");
    private final static String LIFECYCLE_EXTENSION_TAG = CommonStructuresUtil
            .createPreficedString(LIFECYCLE_EXTENSION, "tag");

    public WriteLifecycleTestCase_1_2(String name) {
        super(name);
    }

    public void testLifecycleExtension() {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = getArtifactEditForWrite();
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage
                    .getFacesConfigFactory();

            LifecycleType lifecycle = facesConfigFactory.createLifecycleType();
            lifecycle.setId(EXTENDED_LIFECYCLE_ID);

            LifecycleExtensionType extensionType = facesConfigFactory
                    .createLifecycleExtensionType();
            extensionType.setId(LIFECYCLE_EXTENSION_ID);

            extensionType.getChildNodes().add(
                    createDynamicElement(LIFECYCLE_EXTENSION_TAG));

            lifecycle.getLifecycleExtension().add(extensionType);
            edit.getFacesConfig().getLifecycle().add(lifecycle);
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

            LifecycleType lifecycle = (LifecycleType) FacesConfigModelUtil
                    .findEObjectElementById(edit.getFacesConfig()
                            .getLifecycle(), EXTENDED_LIFECYCLE_ID);
            assertNotNull(lifecycle);

            assertEquals(1, lifecycle.getLifecycleExtension().size());
            LifecycleExtensionType extensionType = (LifecycleExtensionType) lifecycle
                    .getLifecycleExtension().get(0);
            assertEquals(LIFECYCLE_EXTENSION_ID, extensionType.getId());

            assertEquals(1, extensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) extensionType
                    .getChildNodes().get(0);
            assertEquals(LIFECYCLE_EXTENSION_TAG, element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}
