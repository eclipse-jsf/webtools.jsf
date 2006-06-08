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

public class FacesConfigFactoryImplForWriteComponentTwoFiles extends TestCase {
	IProject project = null;

	FacesConfigArtifactEdit edit = null;

	FacesConfigArtifactEdit edit1 = null;

	String displayName = "display-name";

	String icon = "small-icon";

	String attributeDispName = "attribute-display-name";

	public FacesConfigFactoryImplForWriteComponentTwoFiles(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}

	public void testWriteComponent() {
		// IProject project = WizardUtil.getTestProject();

		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage
						.getFacesConfigFactory();

				ComponentType newComponent = facesConfigFactory
						.createComponentType();

				DisplayNameType disp = facesConfigFactory
						.createDisplayNameType();
				disp.setTextContent(displayName);
				newComponent.getDisplayName().add(disp);

				IconType iconType = facesConfigFactory.createIconType();
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

				AttributeType attrType = facesConfigFactory
						.createAttributeType();

				DisplayNameType attributeDisplayName = facesConfigFactory
						.createDisplayNameType();
				attributeDisplayName.setTextContent(attributeDispName);
				attrType.getDisplayName().add(attributeDisplayName);

				IconType attributeDIcon = facesConfigFactory.createIconType();
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

				// ATTRIBUTE EXTENISON REMAIND HERE
				// AttributeExtensionType attrExtType =
				// facesConfigFactory.createAttributeExtensionType();

				newComponent.getAttribute().add(attrType);

				// property section
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

				edit.getFacesConfig().getComponent().add(newComponent);
				edit.save(null);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
		String dispNam = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList components = edit.getFacesConfig().getComponent();
				for (int i = 0; i < components.size(); i++) {
					ComponentType comp = (ComponentType) components.get(i);
					EList disp = comp.getDisplayName();
					for (int j = 0; j < disp.size(); j++) {
						DisplayNameType name = (DisplayNameType) disp.get(j);
						dispNam = name.getTextContent();
						System.out
								.println("The display name for component is  "
										+ dispNam);
						assertEquals(displayName, dispNam);
						break;
					}
				}
			}
		} finally {

			if (edit != null) {
				edit.dispose();
			}
		}

		// icon
		String fetchedIcon = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList components = edit.getFacesConfig().getComponent();
				for (int i = 0; i < components.size(); i++) {
					ComponentType comp = (ComponentType) components.get(i);
					EList disp = comp.getIcon();
					for (int j = 0; j < disp.size(); j++) {
						IconType iconT = (IconType) disp.get(j);
						fetchedIcon = iconT.getSmallIcon().getTextContent();
						System.out.println("The icon name for component is  "
								+ fetchedIcon);
						assertEquals(icon, fetchedIcon);
						break;
					}
				}
			}
		} finally {

			if (edit != null) {
				edit.dispose();
			}
		}
		// componentType
		String compType = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList components = edit.getFacesConfig().getComponent();
				for (int i = 0; i < components.size(); i++) {
					ComponentType comp = (ComponentType) components.get(i);
					compType = comp.getComponentType().getTextContent();
					System.out.println("The comp type for component is  "
							+ compType);
					assertEquals("component-type", compType);
					break;
				}
			}

		} finally {

			if (edit != null) {
				edit.dispose();
			}
		}

		// componentClass
		String compClass = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList components = edit.getFacesConfig().getComponent();
				for (int i = 0; i < components.size(); i++) {
					ComponentType comp = (ComponentType) components.get(i);
					compClass = comp.getComponentClass().getTextContent();
					System.out.println("The comp class name for component is  "
							+ compClass);
					assertEquals("component-class", compClass);
					break;
				}
			}
		} finally {

			if (edit != null) {
				edit.dispose();
			}
		}

		//
	}

	public void testWriteComponentToFileOne() {
		// IProject project = WizardUtil.getTestProject();

		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage
						.getFacesConfigFactory();

				ComponentType newComponent = facesConfigFactory
						.createComponentType();

				DisplayNameType disp = facesConfigFactory
						.createDisplayNameType();
				disp.setTextContent(displayName);
				newComponent.getDisplayName().add(disp);

				IconType iconType = facesConfigFactory.createIconType();
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

				AttributeType attrType = facesConfigFactory
						.createAttributeType();

				DisplayNameType attributeDisplayName = facesConfigFactory
						.createDisplayNameType();
				attributeDisplayName.setTextContent(attributeDispName);
				attrType.getDisplayName().add(attributeDisplayName);

				IconType attributeDIcon = facesConfigFactory.createIconType();
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

				// ATTRIBUTE EXTENISON REMAIND HERE
				// AttributeExtensionType attrExtType =
				// facesConfigFactory.createAttributeExtensionType();

				newComponent.getAttribute().add(attrType);

				// ******* Not working show it to Xiao Nan....
				// ComponentExtensionType componentExtType =
				// facesConfigFactory.createComponentExtensionType();
				// componentExtType.

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

				// ComponentExtensionType compExtType =
				// facesConfigFactory.createComponentExtensionType();
				edit1.getFacesConfig().getComponent().add(newComponent);
				edit1.save(null);
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		String dispNam = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList components = edit1.getFacesConfig().getComponent();
				for (int i = 0; i < components.size(); i++) {
					ComponentType comp = (ComponentType) components.get(i);
					EList disp = comp.getDisplayName();
					for (int j = 0; j < disp.size(); j++) {
						DisplayNameType name = (DisplayNameType) disp.get(j);
						dispNam = name.getTextContent();
						System.out
								.println("The display name for component is  "
										+ dispNam);
						assertEquals(displayName, dispNam);
						break;
					}
				}
			}
		} finally {

			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// icon
		String fetchedIcon = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList components = edit1.getFacesConfig().getComponent();
				for (int i = 0; i < components.size(); i++) {
					ComponentType comp = (ComponentType) components.get(i);
					EList disp = comp.getIcon();
					for (int j = 0; j < disp.size(); j++) {
						IconType iconT = (IconType) disp.get(j);
						fetchedIcon = iconT.getSmallIcon().getTextContent();
						System.out.println("The icon name for component is  "
								+ fetchedIcon);
						assertEquals(icon, fetchedIcon);
						break;
					}
				}
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		// componentType
		String compType = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList components = edit1.getFacesConfig().getComponent();
				for (int i = 0; i < components.size(); i++) {
					ComponentType comp = (ComponentType) components.get(i);
					compType = comp.getComponentType().getTextContent();
					System.out.println("The comp type for component is  "
							+ compType);
					assertEquals("component-type", compType);
					break;
				}
			}

		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// componentClass
		String compClass = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList components = edit1.getFacesConfig().getComponent();
				for (int i = 0; i < components.size(); i++) {
					ComponentType comp = (ComponentType) components.get(i);
					compClass = comp.getComponentClass().getTextContent();
					System.out.println("The comp class for component is  "
							+ compClass);
					assertEquals("component-class", compClass);
					break;
				}
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}

	}
}