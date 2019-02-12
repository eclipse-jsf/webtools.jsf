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
package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultValidatorsType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.ResourceHandlerType;
import org.eclipse.jst.jsf.facesconfig.emf.SourceClassType;
import org.eclipse.jst.jsf.facesconfig.emf.SystemEventClassType;
import org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerClassType;
import org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class WriteApplicationTestCase_2_0 extends WriteApplicationTestCase_1_2
{
	private final static String   APPLICATION_ID_2_0 = "application-id-2_0";
//    private final static String   PARTIAL_TRAVERSAL = "partial-traversal";
    private final static String   RESOURCE_HANDLER = "resource-handler";
    private final static String   SYSTEM_EVENT_LISTENER = "system-event-listener";
    private final static String   SYSTEM_EVENT_LISTENER_CLASS = "system-event-listener-class";
    private final static String   SYSTEM_EVENT_CLASS = "system-event-class";
    private final static String   SOURCE_CLASS = "source-class";
    private final static String   DEFAULT_VALIDATORS="default-validators";
    private final static String   VALIDATOR_ID="validator-id";
    
    public WriteApplicationTestCase_2_0(String name) 
    {
        super(name);
    }

    public void testApplication20()
    {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = getArtifactEditForWrite();

            assertNotNull(edit.getFacesConfig());

            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage
                    .getFacesConfigFactory();

            ApplicationType newApplication = facesConfigFactory
                    .createApplicationType();
            newApplication.setId(APPLICATION_ID_2_0);
            
//            PartialTraversalType partialTraversal = facesConfigFactory.createPartialTraversalType();
//            partialTraversal.setTextContent(PARTIAL_TRAVERSAL);
//            newApplication.getPartialTraversal().add(partialTraversal);

            ResourceHandlerType resourceHandlerType = facesConfigFactory.createResourceHandlerType();
            resourceHandlerType.setTextContent(RESOURCE_HANDLER);
            newApplication.getResourceHandler().add(resourceHandlerType);

            SystemEventListenerType systemEventListener = facesConfigFactory.createSystemEventListenerType();
            systemEventListener.setId(SYSTEM_EVENT_LISTENER);
            newApplication.getSystemEventListener().add(systemEventListener);
            SystemEventListenerClassType systemEventListenerClass = facesConfigFactory.createSystemEventListenerClassType();
            systemEventListenerClass.setTextContent(SYSTEM_EVENT_LISTENER_CLASS);
            systemEventListener.setSystemEventListenerClass(systemEventListenerClass);
            SystemEventClassType systemEventClass = facesConfigFactory.createSystemEventClassType();
            systemEventClass.setTextContent(SYSTEM_EVENT_CLASS);
            systemEventListener.setSystemEventClass(systemEventClass);
            SourceClassType sourceClass = facesConfigFactory.createSourceClassType();
            sourceClass.setTextContent(SOURCE_CLASS);
            systemEventListener.setSourceClass(sourceClass);

            DefaultValidatorsType defaultValidators = facesConfigFactory.createDefaultValidatorsType();
            defaultValidators.setId(DEFAULT_VALIDATORS);
            newApplication.getDefaultValidators().add(defaultValidators);
            ValidatorIdType validatorId = facesConfigFactory.createValidatorIdType();
            validatorId.setTextContent(VALIDATOR_ID);
            defaultValidators.getValidatorId().add(validatorId);
            
            edit.getFacesConfig().getApplication().add(newApplication);
            edit.save(null);
        } finally {
            if (edit != null) {
                edit.dispose();
                // assert that the file has been disposed
                assertTrue(edit.isDisposed());
                edit = null;
            }
        }

        // now read back the file
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            ApplicationType application = 
                (ApplicationType) FacesConfigModelUtil
                    .findEObjectElementById(edit.getFacesConfig().getApplication(), APPLICATION_ID_2_0);

//            assertEquals(1, application.getPartialTraversal().size());
//            assertEquals(PARTIAL_TRAVERSAL, ((PartialTraversalType) application
//                    .getPartialTraversal().get(0)).getTextContent());

            assertEquals(1, application.getResourceHandler().size());
            assertEquals(RESOURCE_HANDLER, ((ResourceHandlerType) application
                    .getResourceHandler().get(0)).getTextContent());

            assertEquals(1, application.getSystemEventListener().size());
            SystemEventListenerType systemEventListener = ((SystemEventListenerType) application
                    .getSystemEventListener().get(0));
            assertEquals(SYSTEM_EVENT_LISTENER, systemEventListener.getId());
//            assertEquals(1, systemEventListener.getSystemEventListenerClass().size());
//            assertEquals(SYSTEM_EVENT_LISTENER_CLASS, ((SystemEventListenerClassType) systemEventListener
//                    .getSystemEventListenerClass().get(0)).getTextContent());
            
          assertEquals(SYSTEM_EVENT_LISTENER_CLASS,systemEventListener
        		  	.getSystemEventListenerClass().getTextContent());
            
//            assertEquals(1, systemEventListener.getSystemEventClass().size());
//            assertEquals(SYSTEM_EVENT_CLASS, ((SystemEventClassType) systemEventListener
//                    .getSystemEventClass().get(0)).getTextContent());
            assertEquals(SYSTEM_EVENT_CLASS, systemEventListener
                  .getSystemEventClass().getTextContent());
            
//            assertEquals(1, systemEventListener.getSourceClass().size());
//            assertEquals(SOURCE_CLASS, ((SourceClassType) systemEventListener
//                    .getSourceClass().get(0)).getTextContent());

          assertEquals(SOURCE_CLASS, systemEventListener
        		  .getSourceClass().getTextContent());
            
            assertEquals(1, application.getDefaultValidators().size());
            DefaultValidatorsType defaultValidators = (DefaultValidatorsType)application.getDefaultValidators().get(0);
            assertEquals(DEFAULT_VALIDATORS, defaultValidators.getId());
            assertEquals(1, defaultValidators.getValidatorId().size());
            assertEquals(VALIDATOR_ID, ((ValidatorIdType)defaultValidators.getValidatorId().get(0)).getTextContent().trim());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}

