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
import org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class FacesConfigFactoryImplForWriteComponent extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForWriteComponent(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}
	
	public void testWriteComponent() {
		//IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;
		
		//String description = "description";
		String displayName = "display-name";
		String icon="small-icon";

		String attributeDispName = "attribute-display-name";
		
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
		
				
				ComponentType newComponent = facesConfigFactory.createComponentType();
				
				DisplayNameType  disp=  facesConfigFactory.createDisplayNameType();
				disp.setTextContent(displayName);
				newComponent.getDisplayName().add(disp);
				
				
				IconType  iconType=  facesConfigFactory.createIconType();
				SmallIconType smallIconType = facesConfigFactory.createSmallIconType();
				smallIconType.setTextContent(icon);
				iconType.setSmallIcon(smallIconType);
				newComponent.getIcon().add(iconType);
				
				ComponentTypeType componentTypeType = facesConfigFactory.createComponentTypeType();
				componentTypeType.setTextContent("component-type");
				newComponent.setComponentType(componentTypeType);

				ComponentClassType componentClassType = facesConfigFactory.createComponentClassType();
				componentClassType.setTextContent("component-class");
				newComponent.setComponentClass(componentClassType);
				
				AttributeType attrType = facesConfigFactory.createAttributeType();
			
				DisplayNameType attributeDisplayName=  facesConfigFactory.createDisplayNameType();
				attributeDisplayName.setTextContent(attributeDispName);
				attrType.getDisplayName().add(attributeDisplayName);
				
				IconType attributeDIcon=  facesConfigFactory.createIconType();
				SmallIconType attributeDSmallIcon = facesConfigFactory.createSmallIconType();
				attributeDSmallIcon.setTextContent(icon);
				attributeDIcon.setSmallIcon(attributeDSmallIcon);
				attrType.getIcon().add(attributeDIcon);

				AttributeNameType attributeNameType = facesConfigFactory.createAttributeNameType();
				attributeNameType.setTextContent("attribute-name");
				attrType.setAttributeName(attributeNameType);
				
				AttributeClassType attributeClassType = facesConfigFactory.createAttributeClassType();
				attributeClassType.setTextContent("attribute-class");
				attrType.setAttributeClass(attributeClassType);
				
				DefaultValueType defaultValueType = facesConfigFactory.createDefaultValueType();
				defaultValueType.setTextContent("default-value");
				attrType.setDefaultValue(defaultValueType);
				
				SuggestedValueType suggestedValueType = facesConfigFactory.createSuggestedValueType();
				suggestedValueType.setTextContent("suggested-value");
				attrType.setSuggestedValue(suggestedValueType);
				
				//ATTRIBUTE EXTENISON REMAIND HERE
				//AttributeExtensionType attrExtType = facesConfigFactory.createAttributeExtensionType();
			
				newComponent.getAttribute().add(attrType);
		
				//******* Not working show it to Xiao Nan....
				//ComponentExtensionType componentExtType = facesConfigFactory.createComponentExtensionType();
				//componentExtType.
				
				PropertyType propType = facesConfigFactory.createPropertyType();
				
				PropertyNameType propertyNameType = facesConfigFactory.createPropertyNameType();
				propertyNameType.setTextContent("property-name");
				propType.setPropertyName(propertyNameType);
				
				PropertyClassType propertyClassType = facesConfigFactory.createPropertyClassType();
				propertyClassType.setTextContent("property-class");
				propType.setPropertyClass(propertyClassType);
				
				DefaultValueType propDefaultValueType = facesConfigFactory.createDefaultValueType();
				propDefaultValueType.setTextContent("property-default-value");
				propType.setDefaultValue(propDefaultValueType);
				
				newComponent.getProperty().add(propType);
				
			//	ComponentExtensionType compExtType = facesConfigFactory.createComponentExtensionType();
			
				
				edit.getFacesConfig().getComponent().add(newComponent);
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
				EList components = edit.getFacesConfig().getComponent();
				for (int i = 0; i < components.size(); i++) {
					ComponentType comp = (ComponentType) components.get(i);
					EList descr = comp.getDescription();
					for (int j=0; j<descr.size(); j++) {
                        // TODO: ???
						//DescriptionType phaseListener = (DescriptionType)descr.get(j);
						//result = phaseListener.getLang();
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