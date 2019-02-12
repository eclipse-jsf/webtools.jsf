/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType;
import org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadRenderKitTestCase_2_0 extends ReadRenderKitTestCase_1_2 {
    public ReadRenderKitTestCase_2_0(String name) {
        super(name);
    }

    protected void initialize(TestConfiguration testConfiguration) {
        // override base when not in a configurable test suite
        if(_testConfiguration == null)
        {
            _facesConfigFile = "WEB-INF/faces-config_2_0.xml";
            _facesVersion = IJSFCoreConstants.JSF_VERSION_2_0;
        }
        else
        {
            super.initialize(testConfiguration);
        }
    }

    public void testClientBehaviorRenderer() {

        FacesConfigArtifactEdit edit = null;
        try 
        {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            RenderKitType renderKit1 = getRenderKit1(edit.getFacesConfig());
            assertNotNull(renderKit1);

            ClientBehaviorRendererType clientBehaviorRenderer1 = (ClientBehaviorRendererType)renderKit1.getClientBehaviorRenderer().get(0);
//                (ClientBehaviorRendererType) FacesConfigModelUtil.findEObjectElementById
//                (renderKit1.getClientBehaviorRenderer(), "client-behavior-renderer-id");
            assertNotNull(clientBehaviorRenderer1);
            
			ClientBehaviorRendererTypeType clientBehaviorRendererType1 = clientBehaviorRenderer1.getClientBehaviorRendererType();
            assertNotNull(clientBehaviorRendererType1);
			assertEquals("MyClientBehaviorRendererType",
					clientBehaviorRendererType1.getTextContent().trim());
            
			ClientBehaviorRendererClassType clientBehaviorRendererClass1 = clientBehaviorRenderer1.getClientBehaviorRendererClass();
            assertNotNull(clientBehaviorRendererClass1);
			assertEquals("com.test.MyClientBehaviorRenderer",
					clientBehaviorRendererClass1.getTextContent().trim());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}
