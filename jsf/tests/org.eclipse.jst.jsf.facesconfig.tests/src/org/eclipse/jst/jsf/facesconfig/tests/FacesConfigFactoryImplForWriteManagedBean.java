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
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class FacesConfigFactoryImplForWriteManagedBean extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForWriteManagedBean(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}
	
	public void testWriteManagedBean() {
		//IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;
		
		//String desc = "description";
		String dispName = "display-name";	
		
		String propertyDisplayName = "managed-property-display-name";
		
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
	
				ManagedBeanType managedBT = facesConfigFactory.createManagedBeanType();
				
				DisplayNameType  actionList=  facesConfigFactory.createDisplayNameType();
				actionList.setTextContent(dispName);
				managedBT.getDisplayName().add(actionList);		
				
				IconType  iconType=  facesConfigFactory.createIconType();
				SmallIconType smallIconType = facesConfigFactory.createSmallIconType();
				smallIconType.setTextContent("SMALL-ICON");
				iconType.setSmallIcon(smallIconType);
				managedBT.getIcon().add(iconType);
				
				ManagedBeanNameType managedBeanNameType = facesConfigFactory.createManagedBeanNameType();
				managedBeanNameType.setTextContent("MANAGED-BEAN-NAME");
				managedBT.setManagedBeanName(managedBeanNameType);
				
				ManagedBeanClassType managedBeanClassType = facesConfigFactory.createManagedBeanClassType();
				managedBeanClassType.setTextContent("MANAGED-BEAN-CLASS");
				managedBT.setManagedBeanClass(managedBeanClassType);
				
				ManagedBeanScopeType managedBeanScopeType = facesConfigFactory.createManagedBeanScopeType();
				managedBeanScopeType.setTextContent("MANAGED-BEAN-SCOPE");
				managedBT.setManagedBeanScope(managedBeanScopeType);
				
				ManagedPropertyType manageedPropType = facesConfigFactory.createManagedPropertyType();
				
				DisplayNameType  propertyDispName=  facesConfigFactory.createDisplayNameType();
				propertyDispName.setTextContent(propertyDisplayName);
				manageedPropType.getDisplayName().add(propertyDispName);
				
				IconType  propertyIconType=  facesConfigFactory.createIconType();
				LargeIconType largeIconType = facesConfigFactory.createLargeIconType();
				largeIconType.setTextContent("property-SMALL-ICON");
				propertyIconType.setLargeIcon(largeIconType);
				manageedPropType.getIcon().add(propertyIconType);
				
				PropertyNameType propertyNameType = facesConfigFactory.createPropertyNameType();
				propertyNameType.setTextContent("managedBean->managedProperty->property-name");
				manageedPropType.setPropertyName(propertyNameType);
				
				PropertyClassType propertyClassType = facesConfigFactory.createPropertyClassType();
				propertyClassType.setTextContent("managedBean->managedProperty->property-class");
				manageedPropType.setPropertyClass(propertyClassType);
				/*
				 *  THERE IS A  SERIOUS PROBLEM HERE THE "null-value" tag should have No value 
				 *  inside it but here i am able to set some values which makes the XML file
				 *  invalid.
				 */
				//manageedPropType.setNullValue("managedBean->managedProperty->property-nullValue");
				/* Problem: discussed this with Xiao nan on 03/29/06
				 * The following is REDUNDANT and should actually be removed from the API.
				 * manageedPropType.setNullValue(null) produces the same output as not having it there
				 * even more important is that in either case another value has to be added to conform 
				 * to the xml file so that it validates.
				 */
				//manageedPropType.setNullValue(null);
				ValueType valueType = facesConfigFactory.createValueType();
				valueType.setTextContent("some new value instead of null values");
				manageedPropType.setValue(valueType);
				managedBT.getManagedProperty().add(manageedPropType);
				
				//managedBT.getDisplayName().add(actionList);			
				edit.getFacesConfig().getManagedBean().add(managedBT);
				edit.save(null);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
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
						result = phaseListener.getTextContent();
						break;
					}
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
}

