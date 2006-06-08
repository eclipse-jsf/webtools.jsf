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
package org.eclipse.jst.jsf.facesconfig.tests;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultLocaleType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.StateManagerType;
import org.eclipse.jst.jsf.facesconfig.emf.SupportedLocaleType;
import org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
/*
 * This class is used to test wether the writing into the faces-config.xml
 * file is being done propertly and the outputs are as expected 
 * 
 */
public class FacesConfigFactoryImplForWriteApplicationTwoFiles extends TestCase {
	IProject project = null;
	
	FacesConfigArtifactEdit edit1 = null;
	FacesConfigArtifactEdit edit2 = null;
	
	String actionListener = "action-listener";
	String variableResolver = "variable-resolver";
	String defaultRenderKitId = "default-render-kit-id";
	String messageBundle= "message-bundle";
	String navigationHandler = "navigation-handler";
	String viewHandler = "view-handler";
	String stateManager="state-manager";
	String propertyResolver="property-resolver";

	public FacesConfigFactoryImplForWriteApplicationTwoFiles(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}
	/*
	 * Do the writing here to all the attributes
	 * 
	 */
	
	public void testWriteApplicationToFileTwo() {
		//IProject project = WizardUtil.getTestProject();
	
		//Local-Config is REMAINING HERE TO BE ADDED LATER ON.
		
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
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
				defaultLocaleType.setTextContent("default-locals");
				localConfigType.setDefaultLocale(defaultLocaleType);
				
				SupportedLocaleType supportedLocalType = facesConfigFactory.createSupportedLocaleType();
				supportedLocalType.setTextContent("supported-local-type");
				localConfigType.getSupportedLocale().add(supportedLocalType);
				//newApplication.getSpportedLocal().add("some value"); blahhhhhh;
				//***************** missing method above line  **********************//
				newApplication.getLocaleConfig().add(localConfigType);
				
				edit2.getFacesConfig().getApplication().add(newApplication);
				edit2.save(null);
			}
		} finally {
			if (edit2 != null) {
				edit2.dispose();
			}
		}

		String actionList = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList apps = edit2.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList actListener = appType.getActionListener();
					for (int j=0; j<actListener.size(); j++) {
						ActionListenerType listener = (ActionListenerType)actListener.get(j);
						actionList = listener.getTextContent();
						System.out.println("The action listener of Application  is : " + actionList);
						assertEquals(actionListener,actionList);
						break;
					}
				}
			}
		} finally {
			
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		//variableResolver
		String varResolver = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList apps = edit2.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList varRes = appType.getVariableResolver();
					for (int j=0; j<varRes.size(); j++) {
						VariableResolverType resolver = (VariableResolverType)varRes.get(j);
						varResolver = resolver.getTextContent();
						System.out.println("The action listener of Application  is : " + varResolver);
						assertEquals(variableResolver,varResolver);
						break;
					}
				}
			}
		} finally {
			
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		//defaultRenderKitId
		String defRenderKitId = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList apps = edit2.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList defKit = appType.getDefaultRenderKitId();
					for (int j=0; j<defKit.size(); j++) {
						DefaultRenderKitIdType defaultKit = (DefaultRenderKitIdType)defKit.get(j);
						defRenderKitId = defaultKit.getTextContent();
						System.out.println("The action listener of Application  is : " + defRenderKitId);
						assertEquals(defaultRenderKitId,defRenderKitId);
						break;
					}
				}
			}
		} finally {
			
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		
		//messageBundle
		String messBundle = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList apps = edit2.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList bundle = appType.getMessageBundle();
					for (int j=0; j<bundle.size(); j++) {
						MessageBundleType bundleType = (MessageBundleType)bundle.get(j);
						messBundle = bundleType.getTextContent();
						System.out.println("The action listener of Application  is : " + messBundle);
						assertEquals(messageBundle,messBundle);
						break;
					}
				}
			}
		} finally {
			
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		
		
		//navigationHandler
		String naviHandler = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList apps = edit2.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList navigation = appType.getNavigationHandler();
					for (int j=0; j<navigation.size(); j++) {
						NavigationHandlerType handler = (NavigationHandlerType)navigation.get(j);
						naviHandler = handler.getTextContent();
						System.out.println("The action listener of Application  is : " + naviHandler);
						assertEquals(navigationHandler, naviHandler);
						break;
					}
				}
			}
		} finally {
			
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		
		//viewHandler
		String viewHand = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList apps = edit2.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList view = appType.getViewHandler();
					for (int j=0; j<view.size(); j++) {
						ViewHandlerType handler = (ViewHandlerType)view.get(j);
						viewHand = handler.getTextContent();
						System.out.println("The action listener of Application is: " + viewHand);
						assertEquals(viewHandler, viewHand);
						break;
					}
				}
			}
		} finally {
			
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		
		//stateManager
		String stateMgr= null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList apps = edit2.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList actListener = appType.getStateManager();
					for (int j=0; j<actListener.size(); j++) {
						StateManagerType manager = (StateManagerType)actListener.get(j);
						stateMgr = manager.getTextContent();
						System.out.println("The action listener of Application is: " + stateMgr);
						assertEquals(stateManager,stateMgr);
						break;
					}
				}
			}
		} finally {
			
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		
		//propertyResolver
		String propertyRes = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList apps = edit2.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList prop = appType.getPropertyResolver();
					for (int j=0; j<prop.size(); j++) {
						PropertyResolverType resolver = (PropertyResolverType)prop.get(j);
						propertyRes = resolver.getTextContent();
						System.out.println("The action listener of Application is: " + propertyRes);
						assertEquals(propertyResolver,propertyRes);
						break;
					}
				}
			}
		} finally {
			
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		
		//LOCAL-CONFIG REMAINING.
		String defLocal = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit2.getFacesConfig() != null) {
				EList apps = edit2.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList config = appType.getLocaleConfig();
					for (int j=0; j<config.size(); j++) {
						LocaleConfigType localConf = (LocaleConfigType) config.get(i);
						defLocal = localConf.getDefaultLocale().getTextContent();
						System.out.println("The Defualt Local config of Application is: " + defLocal);
						assertEquals("default-locals",defLocal);
						break;
					}
				}
			}
		} finally {
			
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		//supportedLocal
		String supportedLocal = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit2.getFacesConfig() != null) {
				EList apps = edit2.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList localConf = appType.getLocaleConfig();
					for(int k=0; k<localConf.size(); k++){
						LocaleConfigType confType = (LocaleConfigType) localConf.get(k);
						EList localConfList = confType.getSupportedLocale();
							for (int j=0; j<localConfList.size(); j++) {
								SupportedLocaleType supportedLocalConf = (SupportedLocaleType) localConfList.get(i);
								supportedLocal = supportedLocalConf.getTextContent();
								System.out.println("The Supported Local config of Application is: " + supportedLocal);
								break;
							}
					}
				}
			}
		} finally {
			
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		
		
	}	
	
	public void testWriteApplicationToFileOne() {
		//IProject project = WizardUtil.getTestProject();
	
		//Local-Config is REMAINING HERE TO BE ADDED LATER ON.
		
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
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
				defaultLocaleType.setTextContent("default-locals");
				localConfigType.setDefaultLocale(defaultLocaleType);
				
				SupportedLocaleType supportedLocalType = facesConfigFactory.createSupportedLocaleType();
				supportedLocalType.setTextContent("supported-local-type");
				localConfigType.getSupportedLocale().add(supportedLocalType);
				//newApplication.getSpportedLocal().add("some value"); blahhhhhh;
				//***************** missing method above line  **********************//
				newApplication.getLocaleConfig().add(localConfigType);
				
				edit1.getFacesConfig().getApplication().add(newApplication);
				edit1.save(null);
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		String resultFactory = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList lifecycles = edit1.getFacesConfig().getFactory();
				for (int i = 0; i < lifecycles.size(); i++) {
					FactoryType appType = (FactoryType) lifecycles.get(i);
					EList appFactory = appType.getApplicationFactory();
					for (int j=0; j<appFactory.size(); j++) {
						ApplicationFactoryType app = (ApplicationFactoryType)appFactory.get(j);
						resultFactory = app.getTextContent();
						System.out.println("The Application is: " + resultFactory);
						//assert here
						break;
					}
				}
			}
		} finally {
			
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		String actionList = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit1.getFacesConfig() != null) {
				EList apps = edit1.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList actListener = appType.getActionListener();
					for (int j=0; j<actListener.size(); j++) {
						ActionListenerType listener = (ActionListenerType)actListener.get(j);
						actionList = listener.getTextContent();
						System.out.println("The action listener of Application is: " + actionList);
						assertEquals(actionListener,actionList);
						break;
					}
				}
			}
		} finally {
			
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		//variableResolver
		String varResolver = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList apps = edit1.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList varRes = appType.getVariableResolver();
					for (int j=0; j<varRes.size(); j++) {
						VariableResolverType resolver = (VariableResolverType)varRes.get(j);
						varResolver = resolver.getTextContent();
						System.out.println("The action listener of Application is: " + varResolver);
						assertEquals(variableResolver,varResolver);
						break;
					}
				}
			}
		} finally {
			
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		//defaultRenderKitId
		String defRenderKitId = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList apps = edit1.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList defKit = appType.getDefaultRenderKitId();
					for (int j=0; j<defKit.size(); j++) {
						DefaultRenderKitIdType defaultKit = (DefaultRenderKitIdType)defKit.get(j);
						defRenderKitId = defaultKit.getTextContent();
						System.out.println("The action listener of Application is: " + defRenderKitId);
						assertEquals(defaultRenderKitId,defRenderKitId);
						break;
					}
				}
			}
		} finally {
			
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		//messageBundle
		String messBundle = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList apps = edit1.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList bundle = appType.getMessageBundle();
					for (int j=0; j<bundle.size(); j++) {
						MessageBundleType bundleType = (MessageBundleType)bundle.get(j);
						messBundle = bundleType.getTextContent();
						System.out.println("The action listener of Application is: " + messBundle);
						assertEquals(messageBundle,messBundle);
						break;
					}
				}
			}
		} finally {
			
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		
		//navigationHandler
		String naviHandler = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList apps = edit1.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList navigation = appType.getNavigationHandler();
					for (int j=0; j<navigation.size(); j++) {
						NavigationHandlerType navHandler = (NavigationHandlerType)navigation.get(j);
						naviHandler = navHandler.getTextContent();
						System.out.println("The action listener of Application is: " + naviHandler);
						assertEquals(navigationHandler, naviHandler);
						break;
					}
				}
			}
		} finally {
			
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		//viewHandler
		String viewHand = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList apps = edit1.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList view = appType.getViewHandler();
					for (int j=0; j<view.size(); j++) {
						ViewHandlerType handler = (ViewHandlerType)view.get(j);
						viewHand = handler.getTextContent();
						System.out.println("The action listener of Application is: " + viewHand);
						assertEquals(viewHandler, viewHand);
						break;
					}
				}
			}
		} finally {
			
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		//stateManager
		String stateMgr= null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList apps = edit1.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList actListener = appType.getStateManager();
					for (int j=0; j<actListener.size(); j++) {
						StateManagerType listener = (StateManagerType)actListener.get(j);
						stateMgr = listener.getTextContent();
						System.out.println("The action listener of Application is: " + stateMgr);
						assertEquals(stateManager,stateMgr);
						break;
					}
				}
			}
		} finally {
			
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		//propertyResolver
		String propertyRes = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList apps = edit1.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList prop = appType.getPropertyResolver();
					for (int j=0; j<prop.size(); j++) {
						PropertyResolverType listener = (PropertyResolverType)prop.get(j);
						propertyRes = listener.getTextContent();
						System.out.println("The action listener of Application is: " + propertyRes);
						assertEquals(propertyResolver,propertyRes);
						break;
					}
				}
			}
		} finally {
			
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		//LOCAL-CONFIG REMAINING.
		String defLocal = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList apps = edit1.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList config = appType.getLocaleConfig();
					for (int j=0; j<config.size(); j++) {
						LocaleConfigType localConf = (LocaleConfigType) config.get(i);
						defLocal = localConf.getDefaultLocale().getTextContent();
						System.out.println("The Defualt Local config of Application is: " + defLocal);
						assertEquals("default-locals",defLocal);
						break;
					}
				}
			}
		} finally {
			
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		//supportedLocal
		String supportedLocal = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList apps = edit1.getFacesConfig().getApplication();
				for (int i = 0; i < apps.size(); i++) {
					ApplicationType appType = (ApplicationType) apps.get(i);
					EList localConf = appType.getLocaleConfig();
					for(int k=0; k<localConf.size(); k++){
						LocaleConfigType confType = (LocaleConfigType) localConf.get(k);
						EList localConfList = confType.getSupportedLocale();
							for (int j=0; j<localConfList.size(); j++) {
								SupportedLocaleType supportedLocalConf = (SupportedLocaleType) localConfList.get(i);
								supportedLocal = supportedLocalConf.getTextContent();
								System.out.println("The Supported Local config of Application is: " + supportedLocal);
								//assertEquals("supported-local-type",supportedLocal);
								break;
					}
					}
				}
			}
		} finally {
			
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
	}	
}