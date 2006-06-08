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
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the FacesConfigFactoryImpl
 * class.
 */

//NEEDS to be changed to reflect navigagtion rule 
// as of now it just shows lifeclycle.

public class FacesConfigFactoryImplForWriteFactoryTwoFiles extends TestCase {
	IProject project = null;
	FacesConfigArtifactEdit edit1 = null;
	FacesConfigArtifactEdit edit2 = null;
	
	String applicationFactory = "application-factory";
	String facesContext = "Faces-Contexto";
	String lifecyclefactory = "lifecycle-factory";
	String renderKitFactory = "render-kit-factory";
	

	public FacesConfigFactoryImplForWriteFactoryTwoFiles(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}
	
	public void testWriteFacesconfigFile() {
		//IProject project = WizardUtil.getTestProject();

		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
				
				
				FactoryType newfactory = facesConfigFactory.createFactoryType();
				
				ApplicationFactoryType newApplicationFactory = facesConfigFactory.createApplicationFactoryType();
				newApplicationFactory.setTextContent(applicationFactory);
				newfactory.getApplicationFactory().add(newApplicationFactory);
						
				FacesContextFactoryType facesContextFactory = facesConfigFactory.createFacesContextFactoryType();
				facesContextFactory.setTextContent(facesContext);
				newfactory.getFacesContextFactory().add(facesContextFactory);
		
				
				LifecycleFactoryType lifeclycleFactory = facesConfigFactory.createLifecycleFactoryType();
				lifeclycleFactory.setTextContent(lifecyclefactory);
				newfactory.getLifecycleFactory().add(lifeclycleFactory);
				
				RenderKitFactoryType renderKit = facesConfigFactory.createRenderKitFactoryType();
				renderKit.setTextContent(renderKitFactory);
				newfactory.getRenderKitFactory().add(renderKit);
				
				
				edit2.getFacesConfig().getFactory().add(newfactory);
				
				edit2.save(null);
			}
		} finally {
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		String result = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList factoryList = edit2.getFacesConfig().getFactory();
				for (int i = 0; i < factoryList.size(); i++) {
					FactoryType facto = (FactoryType) factoryList.get(i);
					EList appFactList = facto.getApplicationFactory();
					for (int j=0; j<appFactList.size(); j++) {
						ApplicationFactoryType app = (ApplicationFactoryType)appFactList.get(j);
						result = app.getTextContent();
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}
	}
	
	
	//fileOne
	public void testWriteFacesconfigFileToFile() {
		//IProject project = WizardUtil.getTestProject();

		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
				
				
				FactoryType newfactory = facesConfigFactory.createFactoryType();
				
				ApplicationFactoryType newApplicationFactory = facesConfigFactory.createApplicationFactoryType();
				newApplicationFactory.setTextContent(applicationFactory);
				newfactory.getApplicationFactory().add(newApplicationFactory);
						
				FacesContextFactoryType facesContextFactory = facesConfigFactory.createFacesContextFactoryType();
				facesContextFactory.setTextContent(facesContext);
				newfactory.getFacesContextFactory().add(facesContextFactory);
		
				
				LifecycleFactoryType lifeclycleFactory = facesConfigFactory.createLifecycleFactoryType();
				lifeclycleFactory.setTextContent(lifecyclefactory);
				newfactory.getLifecycleFactory().add(lifeclycleFactory);
				
				RenderKitFactoryType renderKit = facesConfigFactory.createRenderKitFactoryType();
				renderKit.setTextContent(renderKitFactory);
				newfactory.getRenderKitFactory().add(renderKit);
				
				
				edit1.getFacesConfig().getFactory().add(newfactory);
				
				edit1.save(null);
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		String result = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit1.getFacesConfig() != null) {
				EList lifecycles = edit1.getFacesConfig().getFactory();
				for (int i = 0; i < lifecycles.size(); i++) {
					FactoryType lifecycle = (FactoryType) lifecycles.get(i);
					EList phaseListeners = lifecycle.getApplicationFactory();
					for (int j=0; j<phaseListeners.size(); j++) {
						ApplicationFactoryType phaseListener = (ApplicationFactoryType)phaseListeners.get(j);
						result = phaseListener.getTextContent();
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
	}
	
	
	
	public void testWriteFactoryFacesContextFactory() {
		//IProject project = WizardUtil.getTestProject();
		//FacesConfigArtifactEdit edit2 = null;
		
		String facesContextFactory=null;
		
		
		facesContextFactory = "faces-context-factory";
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
				
				
				FactoryType newfactory = facesConfigFactory.createFactoryType();
				
				
				FacesContextFactoryType newFacesContextFactory = facesConfigFactory.createFacesContextFactoryType();
				
				
				newFacesContextFactory.setTextContent(facesContextFactory);
				newfactory.getFacesContextFactory().add(newFacesContextFactory);
				edit2.getFacesConfig().getFactory().add(newfactory);
				
				edit2.save(null);
			}
		} finally {
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		String result = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList factories = edit2.getFacesConfig().getFactory();
				for (int i = 0; i < factories.size(); i++) {
					FactoryType factory = (FactoryType) factories.get(i);
					EList facescContextFactories = factory.getFacesContextFactory();
					for (int j=0; j<facescContextFactories.size(); j++) {
						FacesContextFactoryType faacesContext = (FacesContextFactoryType)facescContextFactories.get(j);
						result = faacesContext.getTextContent();
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}
	}
	
	
	//fileOne version is below:
	public void testWriteFactoryFacesContextFactoryToFileOne() {
		//IProject project = WizardUtil.getTestProject();
		//FacesConfigArtifactEdit edit1 = null;
		String facesContextFactory=null;
	
		facesContextFactory = "faces-context-factory";
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
				
				
				FactoryType newfactory = facesConfigFactory.createFactoryType();
				
				
				FacesContextFactoryType newFacesContextFactory = facesConfigFactory.createFacesContextFactoryType();
				
				
				newFacesContextFactory.setTextContent(facesContextFactory);
				newfactory.getFacesContextFactory().add(newFacesContextFactory);
				edit1.getFacesConfig().getFactory().add(newfactory);
				
				edit1.save(null);
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		String result = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit1.getFacesConfig() != null) {
				EList factories = edit1.getFacesConfig().getFactory();
				for (int i = 0; i < factories.size(); i++) {
					FactoryType factory = (FactoryType) factories.get(i);
					EList facescContextFactories = factory.getFacesContextFactory();
					for (int j=0; j<facescContextFactories.size(); j++) {
						FacesContextFactoryType faacesContext = (FacesContextFactoryType)facescContextFactories.get(j);
						result = faacesContext.getTextContent();
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
	}
	
	
	
	public void testWriteFactoryLifecycleFactory() {
		//IProject project = WizardUtil.getTestProject();
		//FacesConfigArtifactEdit edit1 = null;
		
		String lifecycleFactory=null;
			
		lifecycleFactory = "lifecycle-factory";
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
				
				FactoryType newfactory = facesConfigFactory.createFactoryType();
				
				LifecycleFactoryType newLifecycleFactory = facesConfigFactory.createLifecycleFactoryType();
							
				newLifecycleFactory.setTextContent(lifecycleFactory);
				newfactory.getLifecycleFactory().add(newLifecycleFactory);
				edit2.getFacesConfig().getFactory().add(newfactory);
				
				edit2.save(null);
			}
		} finally {
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		String result = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList factories = edit2.getFacesConfig().getFactory();
				for (int i = 0; i < factories.size(); i++) {
					FactoryType factory = (FactoryType) factories.get(i);
					EList lifecycleFactories = factory.getLifecycleFactory();
					for (int j=0; j<lifecycleFactories.size(); j++) {
						LifecycleFactoryType lifecyle = (LifecycleFactoryType)lifecycleFactories.get(j);
						result = lifecyle.getTextContent();
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}
	}
	
	
	//for fileOne
	
	public void testWriteFactoryLifecycleFactoryToFileOne() {
		//IProject project = WizardUtil.getTestProject();
		//FacesConfigArtifactEdit edit1 = null;
		
		String lifecycleFactory=null;
		
		
		lifecycleFactory = "lifecycle-factory";
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
				
				
				FactoryType newfactory = facesConfigFactory.createFactoryType();
				
				
				LifecycleFactoryType newLifecycleFactory = facesConfigFactory.createLifecycleFactoryType();
				
				
				newLifecycleFactory.setTextContent(lifecycleFactory);
				newfactory.getLifecycleFactory().add(newLifecycleFactory);
				edit1.getFacesConfig().getFactory().add(newfactory);
				
				edit1.save(null);
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		String result = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList factories = edit1.getFacesConfig().getFactory();
				for (int i = 0; i < factories.size(); i++) {
					FactoryType factory = (FactoryType) factories.get(i);
					EList lifecycleFactories = factory.getLifecycleFactory();
					for (int j=0; j<lifecycleFactories.size(); j++) {
						LifecycleFactoryType lifecyle = (LifecycleFactoryType)lifecycleFactories.get(j);
						result = lifecyle.getTextContent();
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
	}
	

	
	public void testWriteRednderKitFactory() {
		//IProject project = WizardUtil.getTestProject();
		//FacesConfigArtifactEdit edit1 = null;
		
		String renderKitFactory=null;
	
		renderKitFactory = "render-kit-factory";
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
			
				FactoryType newfactory = facesConfigFactory.createFactoryType();
				
				RenderKitFactoryType renderKitFact = facesConfigFactory.createRenderKitFactoryType();
				
				renderKitFact.setTextContent(renderKitFactory);
				newfactory.getRenderKitFactory().add(renderKitFact);
				edit2.getFacesConfig().getFactory().add(newfactory);
				
				edit2.save(null);
			}
		} finally {
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		String result = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList factories = edit2.getFacesConfig().getFactory();
				for (int i = 0; i < factories.size(); i++) {
					FactoryType factory = (FactoryType) factories.get(i);
					EList renderKitFactories = factory.getRenderKitFactory();
					for (int j=0; j<renderKitFactories.size(); j++) {
						RenderKitFactoryType render = (RenderKitFactoryType)renderKitFactories.get(j);
						result = render.getTextContent();
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}
	}
	
	
	//for file one
	
	public void testWriteRednderKitFactoryToFileOne() {
		//IProject project = WizardUtil.getTestProject();
		//FacesConfigArtifactEdit edit1 = null;
		
		String renderKitFactory=null;
	
		renderKitFactory = "render-kit-factory";
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
			
				FactoryType newfactory = facesConfigFactory.createFactoryType();
				
				RenderKitFactoryType renderKitFact = facesConfigFactory.createRenderKitFactoryType();
				
				renderKitFact.setTextContent(renderKitFactory);
				newfactory.getRenderKitFactory().add(renderKitFact);
				edit1.getFacesConfig().getFactory().add(newfactory);
				
				edit1.save(null);
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		String result = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit1.getFacesConfig() != null) {
				EList factories = edit1.getFacesConfig().getFactory();
				for (int i = 0; i < factories.size(); i++) {
					FactoryType factory = (FactoryType) factories.get(i);
					EList renderKitFactories = factory.getRenderKitFactory();
					for (int j=0; j<renderKitFactories.size(); j++) {
						RenderKitFactoryType render = (RenderKitFactoryType)renderKitFactories.get(j);
						result = render.getTextContent();
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
	}
	
}
