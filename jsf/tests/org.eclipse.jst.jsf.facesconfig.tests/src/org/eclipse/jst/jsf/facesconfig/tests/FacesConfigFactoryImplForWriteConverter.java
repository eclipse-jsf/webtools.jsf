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
import org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class FacesConfigFactoryImplForWriteConverter extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForWriteConverter(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}
	
	public void testWriteApplication() {
		//IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;
		
		//String desc = "description";
		String dispName = "display-name";
		String converterForClass = "converter-for-class";
		String converterClass= "converter-class";
		String converterIcon="Converter-icon";
		
		//attribute related variables
		String attributeDispName = "attribute-display-name";
		String attributeIcon= "attribute-icon";
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
	
				ConverterType newApplication = facesConfigFactory.createConverterType();
				
				DisplayNameType  actionList=  facesConfigFactory.createDisplayNameType();
				actionList.setTextContent(dispName);
				newApplication.getDisplayName().add(actionList);	
				
				IconType iconT = facesConfigFactory.createIconType();
				SmallIconType smallIconType = facesConfigFactory.createSmallIconType();
				smallIconType.setTextContent(converterIcon);
				iconT.setSmallIcon(smallIconType);
				newApplication.getIcon().add(iconT);	
				
				ConverterClassType converterClassType = facesConfigFactory.createConverterClassType();
				converterClassType.setTextContent(converterClass);
				newApplication.setConverterClass(converterClassType);
				
				ConverterForClassType converterForClassType = facesConfigFactory.createConverterForClassType();
				converterForClassType.setTextContent(converterForClass);
				newApplication.setConverterForClass(converterForClassType);
			
				AttributeType attrType = facesConfigFactory.createAttributeType();
				
				DisplayNameType attributeDisplayName=  facesConfigFactory.createDisplayNameType();
				attributeDisplayName.setTextContent(attributeDispName);
				attrType.getDisplayName().add(attributeDisplayName);
				
				IconType attributeDIcon=  facesConfigFactory.createIconType();
				SmallIconType attributeDSmallIcon = facesConfigFactory.createSmallIconType();
				attributeDSmallIcon.setTextContent(attributeIcon);
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
			
				//ATTRIBUTE EXTENSION HERE
				
			/*	AttributeExtensionType attrExtType = facesConfigFactory.createAttributeExtensionType();
				attrType.getAttributeExtension().add(attrExtType);
				newApplication.getAttribute().add(attrType);
				*/
				edit.getFacesConfig().getConverter().add(newApplication);
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
				EList lifecycles = edit.getFacesConfig().getConverter();
				for (int i = 0; i < lifecycles.size(); i++) {
					ConverterType lifecycle = (ConverterType) lifecycles.get(i);
					EList phaseListeners = lifecycle.getDisplayName();
					for (int j=0; j<phaseListeners.size(); j++) {
                        // TODO: ??
//						DisplayNameType phaseListener = (DisplayNameType)phaseListeners.get(j);
//						result = phaseListener.getTextContent();
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

