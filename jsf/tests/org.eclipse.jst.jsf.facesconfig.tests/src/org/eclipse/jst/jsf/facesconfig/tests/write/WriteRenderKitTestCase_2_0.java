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
package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType;
import org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class WriteRenderKitTestCase_2_0 extends WriteRenderKitTestCase_1_2 
{
    private final static String RENDER_KIT_ID =
        CommonStructuresUtil.createPreficedString(RENDER_KIT+"20", CommonStructuresUtil.ID);
//    private final static String CLIENT_BEHAVIOR_RENDERER= "client-behavior-renderer";
    private final static String CLIENT_BEHAVIOR_RENDERER_TYPE= "client-behavior-renderer-type";
    private final static String CLIENT_BEHAVIOR_RENDERER_CLASS= "client-behavior-renderer-class";

    public WriteRenderKitTestCase_2_0(String name) {
        super(name);
    }

    public void testRenderKit20() {
		FacesConfigArtifactEdit edit = null;
		
		try 
        {
			edit = getArtifactEditForWrite();
			assertNotNull(edit.getFacesConfig());
			FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
			FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();

			RenderKitType renderKit = facesConfigFactory.createRenderKitType();

			renderKit.getDescription().add(CommonStructuresUtil.createDescription(RENDER_KIT));
            renderKit.getDisplayName().add(CommonStructuresUtil.createDisplayName(RENDER_KIT));
            renderKit.getIcon().add(CommonStructuresUtil.createIcon(RENDER_KIT));
            
            {
            	ClientBehaviorRendererType clientBehaviorRenderer = facesConfigFactory.createClientBehaviorRendererType();
//            	clientBehaviorRenderer.setId
//                    (CommonStructuresUtil.createPreficedString(CLIENT_BEHAVIOR_RENDERER, CommonStructuresUtil.ID));
    			renderKit.getClientBehaviorRenderer().add(clientBehaviorRenderer);
            	ClientBehaviorRendererTypeType clientBehaviorRendererType = facesConfigFactory.createClientBehaviorRendererTypeType();
            	clientBehaviorRendererType.setTextContent(CLIENT_BEHAVIOR_RENDERER_TYPE);
            	clientBehaviorRenderer.setClientBehaviorRendererType(clientBehaviorRendererType);
            	ClientBehaviorRendererClassType clientBehaviorRendererClass = facesConfigFactory.createClientBehaviorRendererClassType();
            	clientBehaviorRendererClass.setTextContent(CLIENT_BEHAVIOR_RENDERER_CLASS);
            	clientBehaviorRenderer.setClientBehaviorRendererClass(clientBehaviorRendererClass);
            }
            
            renderKit.setId(RENDER_KIT_ID);
            EList list = edit.getFacesConfig().getRenderKit();
			list.add(renderKit);
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
                    .getRenderKit(), RENDER_KIT_ID);
            assertNotNull(renderKit);
            
            assertEquals(1, renderKit.getDescription().size());
            CommonStructuresUtil.assertMatchesDescription(RENDER_KIT,
                (DescriptionType) renderKit.getDescription().get(0));

            assertEquals(1, renderKit.getDisplayName().size());
            CommonStructuresUtil.assertMatchesDisplayName(RENDER_KIT,
                    (DisplayNameType) renderKit.getDisplayName().get(0));

            assertEquals(1, renderKit.getIcon().size());
            CommonStructuresUtil.assertMatchesIcon(RENDER_KIT,
                    (IconType) renderKit.getIcon().get(0));
            
            {
                assertEquals(1, renderKit.getClientBehaviorRenderer().size());
                ClientBehaviorRendererType clientBehaviorRenderer = (ClientBehaviorRendererType)renderKit.getClientBehaviorRenderer().get(0);
                ClientBehaviorRendererTypeType clientBehaviorRendererType = clientBehaviorRenderer.getClientBehaviorRendererType();
                assertEquals(CLIENT_BEHAVIOR_RENDERER_TYPE, clientBehaviorRendererType.getTextContent());
                ClientBehaviorRendererClassType clientBehaviorRendererClass = clientBehaviorRenderer.getClientBehaviorRendererClass();
                assertEquals(CLIENT_BEHAVIOR_RENDERER_CLASS, clientBehaviorRendererClass.getTextContent());
            }
            assertEquals(RENDER_KIT_ID, renderKit.getId());
            
        } finally {
			if (edit != null) {
				edit.dispose();
			}
		}
    }

}
