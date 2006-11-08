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
import org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class FacesConfigFactoryImplForWriteRenderKit extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForWriteRenderKit(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}
	
	public void testWriteRenderKit() {
		//IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;
		
		String dispName = "display-name";
		String icon = "large-icon";
		String renderKitId = "render-kit-id";
		String renderKitClass = "render-kit-class";
		String rendererFamily = "component-family";
		String rendererClass = "renderer-class";
		String rendererType = "renderer-type";
		//String rendererExtension = "renderer-extension";
		String rendDispName="renderer-display-name";
		String rendIcon = "renderer-icon";
		
		//Attribute related variables
		String attrDispName="attribute-display-name";
		String attrIcon = "attribute-icon";
		
		
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
	
				RenderKitType newApplication = facesConfigFactory.createRenderKitType();
				
				DisplayNameType  actionList=  facesConfigFactory.createDisplayNameType();
				actionList.setTextContent(dispName);
				newApplication.getDisplayName().add(actionList);
				
				IconType  iconType=  facesConfigFactory.createIconType();
				LargeIconType largeIconType = facesConfigFactory.createLargeIconType();
				largeIconType.setTextContent(icon);
				iconType.setLargeIcon(largeIconType);
				newApplication.getIcon().add(iconType);
				
				RenderKitIdType renderKitIdType = facesConfigFactory.createRenderKitIdType();
				renderKitIdType.setTextContent(renderKitId);
				newApplication.setRenderKitId(renderKitIdType);
				
				RenderKitClassType renderKitClassType = facesConfigFactory.createRenderKitClassType();
				renderKitClassType.setTextContent(renderKitClass);
				newApplication.setRenderKitClass(renderKitClassType);
				
				RendererType rendererT = facesConfigFactory.createRendererType();
				
				DisplayNameType  renDispName=  facesConfigFactory.createDisplayNameType();
				renDispName.setTextContent(rendDispName);
				rendererT.getDisplayName().add(renDispName);
				
				IconType rendIconType=  facesConfigFactory.createIconType();
				LargeIconType rendLargeIconType = facesConfigFactory.createLargeIconType();
				rendLargeIconType.setTextContent(rendIcon);
				rendIconType.setLargeIcon(rendLargeIconType);
				rendererT.getIcon().add(rendIconType);
				
				ComponentFamilyType componentFamilyType = facesConfigFactory.createComponentFamilyType();
				componentFamilyType.setTextContent(rendererFamily);
				rendererT.setComponentFamily(componentFamilyType);
				
				RendererTypeType rendererTypeType = facesConfigFactory.createRendererTypeType();
				rendererTypeType.setTextContent(rendererType);
				rendererT.setRendererType(rendererTypeType);
				
				RendererClassType rendererClassType = facesConfigFactory.createRendererClassType();
				rendererClassType.setTextContent(rendererClass);
				rendererT.setRendererClass(rendererClassType);
			
				//NEED TO COME BACK TO THIS PORTION SOMETIME SOON
				//RendererExtensionType rendererextType = facesConfigFactory.createRendererExtensionType();
				//rendererextType.
				//rendererT.getRendererExtension().add(rendererExtension);

				
				
				AttributeType attrType = facesConfigFactory.createAttributeType();
				
				DisplayNameType  attributeDispName=  facesConfigFactory.createDisplayNameType();
				attributeDispName.setTextContent(attrDispName);
				attrType.getDisplayName().add(attributeDispName);
				
				IconType attrIconType=  facesConfigFactory.createIconType();
				LargeIconType attrLargeIconType = facesConfigFactory.createLargeIconType();
				attrLargeIconType.setTextContent(attrIcon);
				attrIconType.setLargeIcon(attrLargeIconType);
				attrType.getIcon().add(attrIconType);

				AttributeNameType attributeNameType = facesConfigFactory.createAttributeNameType();
				attributeNameType.setTextContent("attribute-name");
				attrType.setAttributeName(attributeNameType);
				
				AttributeClassType attributeClassType = facesConfigFactory.createAttributeClassType();
				attributeClassType.setTextContent("attribute-class");
				attrType.setAttributeClass(attributeClassType);
				
				DefaultValueType defaultValueType = facesConfigFactory.createDefaultValueType();
				defaultValueType.setTextContent("attribute-defaulat-value");
				attrType.setDefaultValue(defaultValueType);
				
				SuggestedValueType suggestedValueType = facesConfigFactory.createSuggestedValueType();
				suggestedValueType.setTextContent("attribute-suggested-value");
				attrType.setSuggestedValue(suggestedValueType);
				
				//Check if this is actually correct!!!
			/*	AttributeExtensionType attrExtType = facesConfigFactory.createAttributeExtensionType();
				attrType.getAttributeExtension().add(attrExtType);
				*/
								
				rendererT.getAttribute().add(attrType);
				//rendererT.getRendererExtension().add(rendererExtension);
				newApplication.getRenderer().add(rendererT);
				
				edit.getFacesConfig().getRenderKit().add(newApplication);
				edit.save(null);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
		/*
		String result = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList lifecycles = edit.getFacesConfig().getValidator();
				for (int i = 0; i < lifecycles.size(); i++) {
					ValidatorType lifecycle = (ValidatorType) lifecycles.get(i);
					EList phaseListeners = lifecycle.getDisplayName();
					for (int j=0; j<phaseListeners.size(); j++) {
						DisplayNameType phaseListener = (DisplayNameType)phaseListeners.get(j);
						result = phaseListener.getValue();
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}*/
	}
	
}