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
package org.eclipse.jst.jsf.facesconfig.tests.read;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.StateManagerType;
import org.eclipse.jst.jsf.facesconfig.emf.SupportedLocaleType;
import org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
/*
 * This Junit class is used to test that the different of child lists
 * of Application are correctly populated.
 */
public class ReadApplicationTestCase extends TestCase {
	IProject project = null;

	public ReadApplicationTestCase(String name) {
		super(name);
	}

	
	
	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 * The following method is used to test for the empty navigation rule. Since
	 * I am supplying a single faces-config.xml file as a testing file, I had to
	 * testcases fit in to it by controlling the conditions
	 * 
	 */
	public void testSingleApplication() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);
            assertEquals(1, application1.getActionListener().size());
            assertEquals(1, application1.getDefaultRenderKitId().size());
            assertEquals(1, application1.getLocaleConfig().size());
            assertEquals(1, application1.getMessageBundle().size());
            assertEquals(1, application1.getNavigationHandler().size());
            assertEquals(1, application1.getPropertyResolver().size());
            assertEquals(1, application1.getStateManager().size());
            assertEquals(1, application1.getVariableResolver().size());
            assertEquals(1, application1.getViewHandler().size());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	private ApplicationType getApplication1(FacesConfigArtifactEdit edit)
    {
        ApplicationType application1 = 
            FacesConfigModelUtil.findApplicationById
                (edit.getFacesConfig().getApplication(), "application1");
        assertNotNull(application1);
        return application1;
    }
    
	/*
	 * Testing for action-listener
	 * 
	 */
	public void testActionListener() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);
            ActionListenerType actionListener =
                FacesConfigModelUtil.findActionListenerById
                    (application1.getActionListener(), "actionListener1");
            assertNotNull(actionListener);
            assertEquals("Action listener",
                actionListener.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for default-render-kit-id
	 * 
	 */
	public void testDefaultRenderKitId() {
		FacesConfigArtifactEdit edit = null;
		try {
            edit = FacesConfigArtifactEdit
                    .getFacesConfigArtifactEditForRead(project);
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);
            DefaultRenderKitIdType defaultRenderKit =
                FacesConfigModelUtil.findDefaultRenderKitIdTypeById
                    (application1.getDefaultRenderKitId(), "defaultRenderKit1");
             assertNotNull(defaultRenderKit);
             assertEquals("some renderer kit",
                             defaultRenderKit.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for a single entry of message-bundle
	 * 
	 */
	public void testMessageBundle() {
		FacesConfigArtifactEdit edit = null;
		try {
            edit = FacesConfigArtifactEdit
                    .getFacesConfigArtifactEditForRead(project);
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);
            MessageBundleType messageBundleType =
                FacesConfigModelUtil.findMessageBundleTypeById
                    (application1.getMessageBundle(), "messageBundle1");
            assertNotNull(messageBundleType);
            assertEquals("some messeage bundle goes here",
                            messageBundleType.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for a single entry of navigation-handler
	 * 
	 */
	public void testNavigationHandler() {
		FacesConfigArtifactEdit edit = null;
		try {
            edit = FacesConfigArtifactEdit
                .getFacesConfigArtifactEditForRead(project);
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);   
            NavigationHandlerType navigationHandlerType =
                (NavigationHandlerType) 
                    FacesConfigModelUtil.findEObjectElementById
                        (application1.getNavigationHandler(), "navigationHandler1");
            assertNotNull(navigationHandlerType);
            assertEquals("navigation handler",
                    navigationHandlerType.getTextContent().trim());		
        } finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for a single entry of view-handler
	 * 
	 */
	public void testViewHandler() {
		FacesConfigArtifactEdit edit = null;
		try {
            edit = FacesConfigArtifactEdit
                .getFacesConfigArtifactEditForRead(project);
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);   
            ViewHandlerType viewHandlerType =
                (ViewHandlerType) 
                    FacesConfigModelUtil.findEObjectElementById
                        (application1.getViewHandler(), "viewHandler1");
            assertNotNull(viewHandlerType);
            assertEquals("view handler",
                          viewHandlerType.getTextContent().trim());     
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for a single entry of state-manager
	 * 
	 */
	public void testStateManager() {
		FacesConfigArtifactEdit edit = null;
		try {
            edit = FacesConfigArtifactEdit
                .getFacesConfigArtifactEditForRead(project);
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);   
            StateManagerType stateManagerType =
                (StateManagerType) 
                    FacesConfigModelUtil.findEObjectElementById
                        (application1.getStateManager(), "stateManager1");
            assertNotNull(stateManagerType);
            assertEquals("state manager",
                    stateManagerType.getTextContent().trim());     
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
		

	/*
	 * Testing for the variable-resolver
	 */

	public void testSingleVariableResolver() {
		FacesConfigArtifactEdit edit = null;
		try {
            edit = FacesConfigArtifactEdit
                .getFacesConfigArtifactEditForRead(project);
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);   
            VariableResolverType variableResolverType =
                (VariableResolverType) 
                    FacesConfigModelUtil.findEObjectElementById
                        (application1.getVariableResolver(), "variableResolver1");
            assertNotNull(variableResolverType);
            assertEquals("com.ibm.faces.databind.SelectItemsVarResolver",
                         variableResolverType.getTextContent().trim());     
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for the property-resolver
	 */
	public void testSinglePropertyResolver() {
		FacesConfigArtifactEdit edit = null;
		try {
            edit = FacesConfigArtifactEdit
                .getFacesConfigArtifactEditForRead(project);
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);   
            PropertyResolverType propertyResolverType =
                (PropertyResolverType) 
                    FacesConfigModelUtil.findEObjectElementById
                        (application1.getPropertyResolver(), "propertyResolver1");
            assertNotNull(propertyResolverType);
            assertEquals("com.ibm.faces.databind.SelectItemsPropResolver",
                    propertyResolverType.getTextContent().trim());     
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Testing for the local-config within an application
	 */
	public void testLocalConfig() {
		FacesConfigArtifactEdit edit = null;
		try {
            edit = FacesConfigArtifactEdit
                .getFacesConfigArtifactEditForRead(project);
            assertNotNull(edit.getFacesConfig());
            ApplicationType application1 = getApplication1(edit);   
            LocaleConfigType localeConfigType =
                (LocaleConfigType) 
                    FacesConfigModelUtil.findEObjectElementById
                        (application1.getLocaleConfig(), "localeConfig1");
            assertNotNull(localeConfigType);

            assertEquals("en", localeConfigType.getDefaultLocale().getTextContent());
            assertEquals("defaultLocale1", localeConfigType.getDefaultLocale().getId());
            
            assertEquals(2, localeConfigType.getSupportedLocale().size());
            
            SupportedLocaleType supportedLocaleType =
                (SupportedLocaleType)
                    FacesConfigModelUtil.findEObjectElementById(
                        localeConfigType.getSupportedLocale(), 
                        "supportedLocale_en");
            assertNotNull(supportedLocaleType);
            assertEquals("en", supportedLocaleType.getTextContent().trim());
            
            supportedLocaleType =
                (SupportedLocaleType)
                    FacesConfigModelUtil.findEObjectElementById(
                        localeConfigType.getSupportedLocale(), 
                        "supportedLocale_de");
            assertNotNull(supportedLocaleType);
            assertEquals("de", supportedLocaleType.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
}