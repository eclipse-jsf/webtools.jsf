/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.write;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultLocaleType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.StateManagerType;
import org.eclipse.jst.jsf.facesconfig.emf.SupportedLocaleType;
import org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
/*
 * This class is used to test wether the writing into the faces-config.xml
 * file is being done propertly and the outputs are as expected 
 * 
 */
public class WriteApplicationTestCase extends TestCase {
	private static final String SUPPORTED_LOCALE_TYPE = "supported-locale-type";

    private static final String DEFAULT_LOCALE = "default-locale";

    private static final String WEB_INF_FACES_CONFIG2_XML = "WEB-INF/faces-config2.xml";
    
    private final static String actionListener = "action-listener";
    private final static String variableResolver = "variable-resolver";
    private final static String defaultRenderKitId = "default-render-kit-id";
    private final static String messageBundle= "message-bundle";
    private final static String navigationHandler = "navigation-handler";
    private final static String viewHandler = "view-handler";
    private final static String stateManager="state-manager";
    private final static String propertyResolver="property-resolver";
    
    IProject project = null;

	public WriteApplicationTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}
	/*
	 * Do the writing here to all the attributes
	 * 
	 */
	
	public void testWriteApplication() {
		FacesConfigArtifactEdit edit2 = null;
		
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, WEB_INF_FACES_CONFIG2_XML);
            
            assertNotNull(edit2);
			assertNotNull(edit2.getFacesConfig());
            
			FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
			FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
	
			
			ApplicationType newApplication = facesConfigFactory.createApplicationType();
			
			
			ActionListenerType  actionList=  facesConfigFactory.createActionListenerType();
			actionList.setTextContent(actionListener);
			newApplication.getActionListener().add(actionList);
			
			
			
			VariableResolverType  variableResolverType=  facesConfigFactory.createVariableResolverType();
			variableResolverType.setTextContent(variableResolver);
			newApplication.getVariableResolver().add(variableResolverType);
		
			
			DefaultRenderKitIdType  renderKitIdType=  facesConfigFactory.createDefaultRenderKitIdType();
			renderKitIdType.setTextContent(defaultRenderKitId);
			newApplication.getDefaultRenderKitId().add(renderKitIdType);
				
			
			MessageBundleType  messageBundleType=  facesConfigFactory.createMessageBundleType();
			messageBundleType.setTextContent(messageBundle);
			newApplication.getMessageBundle().add(messageBundleType);
			
			
			NavigationHandlerType  navigationHandlerType=  facesConfigFactory.createNavigationHandlerType();
			navigationHandlerType.setTextContent(navigationHandler);
			newApplication.getNavigationHandler().add(navigationHandlerType);
			
			
			ViewHandlerType  viewHandlerType=  facesConfigFactory.createViewHandlerType();
			viewHandlerType.setTextContent(viewHandler);
			newApplication.getViewHandler().add(viewHandlerType);
			
			StateManagerType  stateManagerType=  facesConfigFactory.createStateManagerType();
			stateManagerType.setTextContent(stateManager);
			newApplication.getStateManager().add(stateManagerType);
			
			
			PropertyResolverType  propertyResolverType=  facesConfigFactory.createPropertyResolverType();
			propertyResolverType.setTextContent(propertyResolver);
			newApplication.getPropertyResolver().add(propertyResolverType);
			
			
			LocaleConfigType localConfigType = facesConfigFactory.createLocaleConfigType();
			DefaultLocaleType defaultLocaleType = facesConfigFactory.createDefaultLocaleType();
			defaultLocaleType.setTextContent(DEFAULT_LOCALE);
			localConfigType.setDefaultLocale(defaultLocaleType);
			
			SupportedLocaleType supportedLocalType = facesConfigFactory.createSupportedLocaleType();
			supportedLocalType.setTextContent(SUPPORTED_LOCALE_TYPE);
			localConfigType.getSupportedLocale().add(supportedLocalType);

			newApplication.getLocaleConfig().add(localConfigType);
			
			edit2.getFacesConfig().getApplication().add(newApplication);
			edit2.save(null);
		} finally {
			if (edit2 != null) {
				edit2.dispose();
                // assert that the file has been disposed
                assertTrue(edit2.isDisposed());
                edit2 = null;
			}
		}
		

        // now read back the file
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, WEB_INF_FACES_CONFIG2_XML);
            assertNotNull(edit2);
			assertNotNull(edit2.getFacesConfig());
            assertEquals(1, edit2.getFacesConfig().getApplication().size());
            ApplicationType application = 
                (ApplicationType) edit2.getFacesConfig().getApplication().get(0);
            
            assertEquals(1, application.getActionListener().size());
            assertEquals(actionListener
                    , ((ActionListenerType)application.getActionListener().get(0)).getTextContent());

            assertEquals(1, application.getVariableResolver().size());
            assertEquals(variableResolver
                    , ((VariableResolverType)application.getVariableResolver().get(0)).getTextContent());

            assertEquals(1, application.getDefaultRenderKitId().size());
            assertEquals(defaultRenderKitId
                    , ((DefaultRenderKitIdType)application.getDefaultRenderKitId().get(0)).getTextContent());
            
            assertEquals(1, application.getMessageBundle().size());
            assertEquals(messageBundle
                    , ((MessageBundleType)application.getMessageBundle().get(0)).getTextContent());
            
            assertEquals(1, application.getNavigationHandler().size());
            assertEquals(navigationHandler
                    , ((NavigationHandlerType)application.getNavigationHandler().get(0)).getTextContent());

            assertEquals(1, application.getViewHandler().size());
            assertEquals(viewHandler
                    , ((ViewHandlerType)application.getViewHandler().get(0)).getTextContent());

            assertEquals(1, application.getStateManager().size());
            assertEquals(stateManager
                    , ((StateManagerType)application.getStateManager().get(0)).getTextContent());

            assertEquals(1, application.getPropertyResolver().size());
            assertEquals(propertyResolver
                    , ((PropertyResolverType)application.getPropertyResolver().get(0)).getTextContent());

            
            assertEquals(1, application.getLocaleConfig().size());
            LocaleConfigType localConfigType = 
                (LocaleConfigType) application.getLocaleConfig().get(0);

            assertEquals(DEFAULT_LOCALE, localConfigType.getDefaultLocale().getTextContent());
            assertEquals(1, localConfigType.getSupportedLocale().size());
            
            assertEquals(SUPPORTED_LOCALE_TYPE
                    , ((SupportedLocaleType)localConfigType.getSupportedLocale().get(0)).getTextContent());
		} finally {
			
			if (edit2 != null) {
				edit2.dispose();
			}
		}
	}
}