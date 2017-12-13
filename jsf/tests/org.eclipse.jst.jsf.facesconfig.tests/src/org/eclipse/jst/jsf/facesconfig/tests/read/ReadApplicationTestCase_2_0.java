/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultValidatorsType;
import org.eclipse.jst.jsf.facesconfig.emf.ResourceHandlerType;
import org.eclipse.jst.jsf.facesconfig.emf.SourceClassType;
import org.eclipse.jst.jsf.facesconfig.emf.SystemEventClassType;
import org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerClassType;
import org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadApplicationTestCase_2_0 extends ReadApplicationTestCase_1_2 {

    public ReadApplicationTestCase_2_0(String name) {
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

//    public void testPartialTraversal()
//    {
//        FacesConfigArtifactEdit edit = null;
//        try {
//            edit = getArtifactEditForRead();
//            assertNotNull(edit.getFacesConfig());
//            ApplicationType application1 = getApplication1(edit);   
//            PartialTraversalType partialTraversal =
//                (PartialTraversalType) 
//                    FacesConfigModelUtil.findEObjectElementById
//                        (application1.getPartialTraversal(), "partial-traversal-id");
//            assertNotNull(partialTraversal);
//            assertEquals("com.test.MyPartialTraversal",
//            		partialTraversal.getTextContent().trim());     
//        } finally {
//            if (edit != null) {
//                edit.dispose();
//            }
//        }
//    }
    
    public void testResourceHandler()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);   
            ResourceHandlerType resourceHandler =
                (ResourceHandlerType) 
                    FacesConfigModelUtil.findEObjectElementById
                        (application1.getResourceHandler(), "resource-handler-id");
            assertNotNull(resourceHandler);
            assertEquals("com.test.MyResourceHandler",
            		resourceHandler.getTextContent().trim());     
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    public void testSystemEventListener()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);   
            SystemEventListenerType systemEventListener =
                (SystemEventListenerType) 
                    FacesConfigModelUtil.findEObjectElementById
                        (application1.getSystemEventListener(), "system-event-listener-id");
            assertNotNull(systemEventListener);

            SystemEventListenerClassType systemEventListenerClass = systemEventListener.getSystemEventListenerClass();
//                (SystemEventListenerClassType) 
//                FacesConfigModelUtil.findEObjectElementById
//                    (systemEventListener.getSystemEventListenerClass(), "system-event-listener-class-id");
            assertNotNull(systemEventListenerClass);
            assertEquals("com.test.MySystemEventListener",
            		systemEventListenerClass.getTextContent().trim());     
            
            SystemEventClassType systemEventClass = systemEventListener.getSystemEventClass();
//                (SystemEventClassType) 
//                FacesConfigModelUtil.findEObjectElementById
//                    (systemEventListener.getSystemEventClass(), "system-event-class-id");
            assertNotNull(systemEventClass);
            assertEquals("com.test.MySystemEvent",
            		systemEventClass.getTextContent().trim());     
            
            SourceClassType sourceClass = systemEventListener.getSourceClass();
//                (SourceClassType) 
//                FacesConfigModelUtil.findEObjectElementById
//                    (systemEventListener.getSourceClass(), "source-class-id");
            assertNotNull(sourceClass);
            assertEquals("com.test.MySource",
            		sourceClass.getTextContent().trim());     
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

    public void testDefaultValidator()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);   
            DefaultValidatorsType defaultValidators =
                (DefaultValidatorsType) 
                    FacesConfigModelUtil.findEObjectElementById
                        (application1.getDefaultValidators(), "default-validators-id");
            assertNotNull(defaultValidators);
            ValidatorIdType validatorId = 
                (ValidatorIdType) 
                FacesConfigModelUtil.findEObjectElementById
                    (defaultValidators.getValidatorId(), "validator-id-id");
            assertNotNull(validatorId);
            assertEquals("MyValidator",
            		validatorId.getTextContent().trim());     
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
}
