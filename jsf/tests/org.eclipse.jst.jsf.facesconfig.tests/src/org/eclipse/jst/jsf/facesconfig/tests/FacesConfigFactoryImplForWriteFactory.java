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

public class FacesConfigFactoryImplForWriteFactory extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForWriteFactory(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}
	
	public void testWriteFacesconfigFile() {
		//IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;
		String applicationFactory="";
		applicationFactory += "application-factory";
		String facesContext = "Faces-Contexto";
		String lifecyclefactory = "lifecycle-factory";
		String renderKitFactory = "render-kit-factory";
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
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
				
				
				edit.getFacesConfig().getFactory().add(newfactory);
				
				edit.save(null);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
		//String result = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList lifecycles = edit.getFacesConfig().getFactory();
				for (int i = 0; i < lifecycles.size(); i++) {
					FactoryType lifecycle = (FactoryType) lifecycles.get(i);
					EList phaseListeners = lifecycle.getApplicationFactory();
					for (int j=0; j<phaseListeners.size(); j++) {
                        //TODO:??
					    // ApplicationFactoryType phaseListener = (ApplicationFactoryType)phaseListeners.get(j);
						//result = phaseListener.getTextContent();
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	
	public void testWriteFactoryFacesContextFactory() {
		//IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;
		//String sTestString = "";
		String facesContextFactory=null;
		
		
		facesContextFactory = "faces-context-factory";
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
				
				
				FactoryType newfactory = facesConfigFactory.createFactoryType();
				
				
				FacesContextFactoryType newFacesContextFactory = facesConfigFactory.createFacesContextFactoryType();
				
				
				newFacesContextFactory.setTextContent(facesContextFactory);
				newfactory.getFacesContextFactory().add(newFacesContextFactory);
				edit.getFacesConfig().getFactory().add(newfactory);
				
				edit.save(null);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
		//String result = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList factories = edit.getFacesConfig().getFactory();
				for (int i = 0; i < factories.size(); i++) {
					FactoryType factory = (FactoryType) factories.get(i);
					EList facescContextFactories = factory.getFacesContextFactory();
					for (int j=0; j<facescContextFactories.size(); j++) {
                        // TODO:???
//						FacesContextFactoryType faacesContext = (FacesContextFactoryType)facescContextFactories.get(j);
//						result = faacesContext.getTextContent();
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	public void testWriteFactoryLifecycleFactory() {
		//IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;
		//String sTestString = "";
		String lifecycleFactory=null;
		
		
		lifecycleFactory = "lifecycle-factory";
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
				
				
				FactoryType newfactory = facesConfigFactory.createFactoryType();
				
				
				LifecycleFactoryType newLifecycleFactory = facesConfigFactory.createLifecycleFactoryType();
				
				
				newLifecycleFactory.setTextContent(lifecycleFactory);
				newfactory.getLifecycleFactory().add(newLifecycleFactory);
				edit.getFacesConfig().getFactory().add(newfactory);
				
				edit.save(null);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
		//String result = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList factories = edit.getFacesConfig().getFactory();
				for (int i = 0; i < factories.size(); i++) {
					FactoryType factory = (FactoryType) factories.get(i);
					EList lifecycleFactories = factory.getLifecycleFactory();
					for (int j=0; j<lifecycleFactories.size(); j++) {
                        // TODO: ???
//						LifecycleFactoryType lifecyle = (LifecycleFactoryType)lifecycleFactories.get(j);
//						result = lifecyle.getTextContent();
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	
	
	
	public void testWriteRednderKitFactory() {
		//IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;
		//String sTestString = "";
		String renderKitFactory=null;
	
		renderKitFactory = "render-kit-factory";
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
			
				FactoryType newfactory = facesConfigFactory.createFactoryType();
				
				RenderKitFactoryType renderKitFact = facesConfigFactory.createRenderKitFactoryType();
				
				renderKitFact.setTextContent(renderKitFactory);
				newfactory.getRenderKitFactory().add(renderKitFact);
				edit.getFacesConfig().getFactory().add(newfactory);
				
				edit.save(null);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
		//String result = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList factories = edit.getFacesConfig().getFactory();
				for (int i = 0; i < factories.size(); i++) {
					FactoryType factory = (FactoryType) factories.get(i);
					EList renderKitFactories = factory.getRenderKitFactory();
					for (int j=0; j<renderKitFactories.size(); j++) {
                        // TODO: ???
//						RenderKitFactoryType render = (RenderKitFactoryType)renderKitFactories.get(j);
//						result = render.getTextContent();
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	
}