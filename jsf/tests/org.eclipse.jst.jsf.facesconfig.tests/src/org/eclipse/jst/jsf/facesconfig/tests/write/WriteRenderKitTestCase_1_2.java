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
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteRenderKitTestCase_1_2 extends WriteRenderKitTestCase 
{
    private final static String EXTENDED_RENDERKIT_ID = "extended-renderkit-rule-id";

    private final static String RENDERKIT_EXTENSION = CommonStructuresUtil
            .createPreficedString(RENDER_KIT, "extension");
    private final static String RENDERKIT_EXTENSION_ID = CommonStructuresUtil
            .createPreficedString(RENDERKIT_EXTENSION, "id");
    private final static String RENDERKIT_EXTENSION_TAG = CommonStructuresUtil
            .createPreficedString(RENDERKIT_EXTENSION, "tag");

    public WriteRenderKitTestCase_1_2(String name) {
        super(name);
    }

    public void testRenderKitExtension() {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = getArtifactEditForWrite();
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage
                    .getFacesConfigFactory();

            RenderKitType renderKit = facesConfigFactory.createRenderKitType();
            renderKit.setId(EXTENDED_RENDERKIT_ID);

            RenderKitExtensionType extensionType = facesConfigFactory
                    .createRenderKitExtensionType();
            extensionType.setId(RENDERKIT_EXTENSION_ID);

            extensionType.getChildNodes().add(
                    createDynamicElement(RENDERKIT_EXTENSION_TAG));

            renderKit.getRenderKitExtension().add(extensionType);
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
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            RenderKitType renderKit = (RenderKitType) FacesConfigModelUtil
                    .findEObjectElementById(edit.getFacesConfig()
                            .getRenderKit(), EXTENDED_RENDERKIT_ID);
            assertNotNull(renderKit);

            assertEquals(1, renderKit.getRenderKitExtension().size());
            RenderKitExtensionType extensionType = (RenderKitExtensionType) renderKit
                    .getRenderKitExtension().get(0);
            assertEquals(RENDERKIT_EXTENSION_ID, extensionType.getId());

            assertEquals(1, extensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) extensionType
                    .getChildNodes().get(0);
            assertEquals(RENDERKIT_EXTENSION_TAG, element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}
