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
import org.eclipse.jst.jsf.facesconfig.emf.AttributeExtensionType;
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
import org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class FacesConfigFactoryImplForWriteConverterTwoFiles extends TestCase {
	IProject project = null;

	FacesConfigArtifactEdit edit2 = null;

	FacesConfigArtifactEdit edit1 = null;

	String dispName = "display-name";

	String converterForClass = "converter-for-class";

	String converterClass = "converter-class";

	String converterIcon = "Converter-icon";

	// attribute related variables
	String attributeDispName = "attribute-display-name";

	String attributeIcon = "attribute-icon";

	public FacesConfigFactoryImplForWriteConverterTwoFiles(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}

	public void testWriteApplication() {
		// IProject project = WizardUtil.getTestProject();

		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage
						.getFacesConfigFactory();

				ConverterType newApplication = facesConfigFactory
						.createConverterType();

				DisplayNameType actionList = facesConfigFactory
						.createDisplayNameType();
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

				AttributeType attrType = facesConfigFactory
						.createAttributeType();

				DisplayNameType attributeDisplayName = facesConfigFactory
						.createDisplayNameType();
				attributeDisplayName.setTextContent(attributeDispName);
				attrType.getDisplayName().add(attributeDisplayName);

				IconType attributeDIcon = facesConfigFactory.createIconType();
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

				AttributeExtensionType attrExtType = facesConfigFactory
						.createAttributeExtensionType();
				attrType.getAttributeExtension().add(attrExtType);
				newApplication.getAttribute().add(attrType);

				newApplication.getAttribute().add(attrType);

				// need to add Propety here PropertyType
				PropertyType propType = facesConfigFactory.createPropertyType();
				
				PropertyNameType propertyNameType = facesConfigFactory.createPropertyNameType();
				propertyNameType.setTextContent("property-name-validation-file-two");
				propType.setPropertyName(propertyNameType);
				
				PropertyClassType propertyClassType = facesConfigFactory.createPropertyClassType();
				propertyClassType.setTextContent("property-class-validation-file-two");
				propType.setPropertyClass(propertyClassType);
				
				DefaultValueType propDefaultValueType = facesConfigFactory.createDefaultValueType();
				propDefaultValueType.setTextContent("property-default-value-validation-file-two");
				propType.setDefaultValue(propDefaultValueType);

				newApplication.getProperty().add(propType);

				edit2.getFacesConfig().getConverter().add(newApplication);
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
				EList converterList = edit2.getFacesConfig().getConverter();
				for (int i = 0; i < converterList.size(); i++) {
					ConverterType converter = (ConverterType) converterList
							.get(i);
					EList nameList = converter.getDisplayName();
					for (int j = 0; j < nameList.size(); j++) {
						DisplayNameType name = (DisplayNameType) nameList
								.get(j);
						result = name.getTextContent();
						assertEquals(dispName, result);
						System.out
								.println("The display name in converter has value :: "
										+ result);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}

		// icon
		String icon = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList converterList = edit2.getFacesConfig().getConverter();
				for (int i = 0; i < converterList.size(); i++) {
					ConverterType converter = (ConverterType) converterList
							.get(i);
					EList iconList = converter.getIcon();
					for (int j = 0; j < iconList.size(); j++) {
						IconType iconT = (IconType) iconList.get(j);
						icon = iconT.getSmallIcon().getTextContent();
						assertEquals(converterIcon, icon);
						System.out
								.println("The display name in converter has value :: "
										+ icon);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}

		// converer-for-class
		String conForClass = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList converterList = edit2.getFacesConfig().getConverter();
				for (int i = 0; i < converterList.size(); i++) {
					ConverterType converter = (ConverterType) converterList
							.get(i);
					conForClass = converter.getConverterForClass().getTextContent();
					assertEquals(converterForClass, conForClass);
					System.out
							.println("The converter for class in converter has value :: "
									+ conForClass);
					break;
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}

		// coverterClass
		String conClass = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList converterList = edit2.getFacesConfig().getConverter();
				for (int i = 0; i < converterList.size(); i++) {
					ConverterType converter = (ConverterType) converterList
							.get(i);
					conClass = converter.getConverterClass().getTextContent();
					assertEquals(converterClass, conClass);
					System.out
							.println("The converter class in converter has value :: "
									+ conClass);
					break;
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
			String displayNameResult = null;
			try {
				edit2 = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project,
								"WEB-INF/faces-config2.xml");
				if (edit2.getFacesConfig() != null) {
					EList converterList = edit2.getFacesConfig().getConverter();
					for (int i = 0; i < converterList.size(); i++) {
						ConverterType converter = (ConverterType) converterList
								.get(i);
						EList attributeList = converter.getAttribute();
						for (int j = 0; j < attributeList.size(); j++) {
							AttributeType attType = (AttributeType) attributeList
									.get(j);
							EList displayNameList = attType.getDisplayName();
							for (int k = 0; k < displayNameList.size(); k++) {
								DisplayNameType dispType = (DisplayNameType) displayNameList
										.get(j);
								displayNameResult = dispType.getTextContent();
								System.out
										.println("attribute display name for file 2 of converter is : "
												+ displayNameResult);
								assertEquals(attributeDispName,
										displayNameResult);
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

			// ATTRIBUTE PART OF CONVERTER.
			// attribute-name
			String attributeNameResult = null;
			try {
				edit2 = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project,
								"WEB-INF/faces-config2.xml");
				if (edit2.getFacesConfig() != null) {
					EList converterList = edit2.getFacesConfig().getConverter();
					for (int i = 0; i < converterList.size(); i++) {
						ConverterType converter = (ConverterType) converterList
								.get(i);
						EList attributeList = converter.getAttribute();
						for (int j = 0; j < attributeList.size(); j++) {
							AttributeType attType = (AttributeType) attributeList
									.get(j);
							attributeNameResult = attType.getAttributeName().getTextContent();
							System.out
									.println("****  attribute name for file 2 of converter is : "
											+ attributeNameResult);
							assertEquals("attribute-name", attributeNameResult);
							break;
						}
					}
				}
			} finally {

				if (edit2 != null) {
					edit2.dispose();
				}
			}

			// attributeIcon
			String attributeIconResult = null;
			try {
				edit2 = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project,
								"WEB-INF/faces-config2.xml");
				if (edit2.getFacesConfig() != null) {
					EList converterList = edit2.getFacesConfig().getConverter();
					for (int i = 0; i < converterList.size(); i++) {
						ConverterType converter = (ConverterType) converterList
								.get(i);
						EList attributeList = converter.getAttribute();
						for (int j = 0; j < attributeList.size(); j++) {
							AttributeType attType = (AttributeType) attributeList
									.get(j);
							EList iconList = attType.getIcon();
							for (int k = 0; k < iconList.size(); k++) {
								IconType iconType = (IconType) iconList.get(j);
								attributeIconResult = iconType.getSmallIcon().getTextContent();
								System.out
										.println("attribute icon name for file 2 of converter is : "
												+ attributeIconResult);
								assertEquals(attributeIcon, attributeIconResult);
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

			// attributeClass
			String attributeClassResult = null;
			try {
				edit2 = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project,
								"WEB-INF/faces-config2.xml");
				if (edit2.getFacesConfig() != null) {
					EList converterList = edit2.getFacesConfig().getConverter();
					for (int i = 0; i < converterList.size(); i++) {
						ConverterType converter = (ConverterType) converterList
								.get(i);
						EList attributeList = converter.getAttribute();
						for (int j = 0; j < attributeList.size(); j++) {
							AttributeType attType = (AttributeType) attributeList
									.get(j);
							attributeClassResult = attType.getAttributeClass().getTextContent();
							System.out
									.println("attribute classe for file 2 of converter is : "
											+ attributeClassResult);
							assertEquals("attribute-class",
									attributeClassResult);
							break;
						}
					}
				}
			} finally {

				if (edit2 != null) {
					edit2.dispose();
				}
			}

			// attributeValue
			String attributeValueResult = null;
			try {
				edit2 = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project,
								"WEB-INF/faces-config2.xml");
				if (edit2.getFacesConfig() != null) {
					EList converterList = edit2.getFacesConfig().getConverter();
					for (int i = 0; i < converterList.size(); i++) {
						ConverterType converter = (ConverterType) converterList
								.get(i);
						EList attributeList = converter.getAttribute();
						for (int j = 0; j < attributeList.size(); j++) {
							AttributeType attType = (AttributeType) attributeList
									.get(j);
							attributeValueResult = attType.getDefaultValue().getTextContent();
							System.out
									.println("attribute default value for file 2 of converter is : "
											+ attributeValueResult);
							assertEquals("default-value", attributeValueResult);
							break;
						}
					}
				}
			} finally {

				if (edit2 != null) {
					edit2.dispose();
				}
			}

			// suggestedValue
			String suggestedValueResult = null;
			try {
				edit2 = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project,
								"WEB-INF/faces-config2.xml");
				if (edit2.getFacesConfig() != null) {
					EList converterList = edit2.getFacesConfig().getConverter();
					for (int i = 0; i < converterList.size(); i++) {
						ConverterType converter = (ConverterType) converterList
								.get(i);
						EList attributeList = converter.getAttribute();
						for (int j = 0; j < attributeList.size(); j++) {
							AttributeType attType = (AttributeType) attributeList
									.get(j);
							suggestedValueResult = attType.getSuggestedValue().getTextContent();
							System.out
									.println("attribute suggested value for file 2 of converter is : "
											+ suggestedValueResult);
							assertEquals("suggested-value",
									suggestedValueResult);
							break;
						}
					}
				}
			} finally {

				if (edit2 != null) {
					edit2.dispose();
				}
			}
			
			//PROPERTY 
			
			
			
		}

	}

	public void testWriteConverterToFilesOne() {
		// IProject project = WizardUtil.getTestProject();

		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage
						.getFacesConfigFactory();

				ConverterType newApplication = facesConfigFactory
						.createConverterType();

				DisplayNameType actionList = facesConfigFactory
						.createDisplayNameType();
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

				AttributeType attrType = facesConfigFactory
						.createAttributeType();

				DisplayNameType attributeDisplayName = facesConfigFactory
						.createDisplayNameType();
				attributeDisplayName.setTextContent(attributeDispName);
				attrType.getDisplayName().add(attributeDisplayName);

				IconType attributeDIcon = facesConfigFactory.createIconType();
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

				// ATTRIBUTE EXTENSION HERE

				/*
				 * AttributeExtensionType attrExtType =
				 * facesConfigFactory.createAttributeExtensionType();
				 * attrType.getAttributeExtension().add(attrExtType);
				 * newApplication.getAttribute().add(attrType);
				 */
				edit1.getFacesConfig().getConverter().add(newApplication);
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
				EList converterList = edit1.getFacesConfig().getConverter();
				for (int i = 0; i < converterList.size(); i++) {
					ConverterType converter = (ConverterType) converterList
							.get(i);
					EList phaseListeners = converter.getDisplayName();
					for (int j = 0; j < phaseListeners.size(); j++) {
						DisplayNameType phaseListener = (DisplayNameType) phaseListeners
								.get(j);
						result = phaseListener.getTextContent();
						assertEquals(dispName, result);
						System.out
								.println("The display name in converter has value :: "
										+ result);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// icon
		String icon = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList converterList = edit1.getFacesConfig().getConverter();
				for (int i = 0; i < converterList.size(); i++) {
					ConverterType converter = (ConverterType) converterList
							.get(i);
					EList phaseListeners = converter.getIcon();
					for (int j = 0; j < phaseListeners.size(); j++) {
						IconType phaseListener = (IconType) phaseListeners
								.get(j);
						icon = phaseListener.getSmallIcon().getTextContent();
						assertEquals(converterIcon, icon);
						System.out
								.println("The display name in converter has value :: "
										+ icon);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// converer-for-class
		String conForClass = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList converterList = edit1.getFacesConfig().getConverter();
				for (int i = 0; i < converterList.size(); i++) {
					ConverterType converter = (ConverterType) converterList
							.get(i);
					conForClass = converter.getConverterForClass().getTextContent();
					assertEquals(converterForClass, conForClass);
					System.out
							.println("The display name in converter has value :: "
									+ conForClass);
					break;
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// coverterClass
		String conClass = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList converterList = edit1.getFacesConfig().getConverter();
				for (int i = 0; i < converterList.size(); i++) {
					ConverterType converter = (ConverterType) converterList
							.get(i);
					conClass = converter.getConverterClass().getTextContent();
					assertEquals(converterClass, conClass);
					System.out
							.println("The converter class in converter has value :: "
									+ conClass);
					break;
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
			String displayNameResult = null;
			try {
				edit1 = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project,
								"WEB-INF/faces-config1.xml");
				if (edit1.getFacesConfig() != null) {
					EList converterList = edit1.getFacesConfig().getConverter();
					for (int i = 0; i < converterList.size(); i++) {
						ConverterType converter = (ConverterType) converterList
								.get(i);
						EList attributeList = converter.getAttribute();
						for (int j = 0; j < attributeList.size(); j++) {
							AttributeType attType = (AttributeType) attributeList
									.get(j);
							EList displayNameList = attType.getDisplayName();
							for (int k = 0; k < displayNameList.size(); k++) {
								DisplayNameType dispType = (DisplayNameType) displayNameList
										.get(j);
								displayNameResult = dispType.getTextContent();
								System.out
										.println("attribute display name for file 1 of converter is : "
												+ displayNameResult);
								assertEquals(attributeDispName,
										displayNameResult);
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

			// ATTRIBUTE PART OF CONVERTER.
			// attribute-name
			String attributeNameResult = null;
			try {
				edit1 = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project,
								"WEB-INF/faces-config1.xml");
				if (edit1.getFacesConfig() != null) {
					EList converterList = edit1.getFacesConfig().getConverter();
					for (int i = 0; i < converterList.size(); i++) {
						ConverterType converter = (ConverterType) converterList
								.get(i);
						EList attributeList = converter.getAttribute();
						for (int j = 0; j < attributeList.size(); j++) {
							AttributeType attType = (AttributeType) attributeList
									.get(j);
							attributeNameResult = attType.getAttributeName().getTextContent();
							System.out
									.println("****  attribute name for file 1 of converter is : "
											+ attributeNameResult);
							assertEquals("attribute-name", attributeNameResult);
							break;
						}
					}
				}
			} finally {

				if (edit1 != null) {
					edit1.dispose();
				}
			}

			// attributeIcon
			String attributeIconResult = null;
			try {
				edit1 = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project,
								"WEB-INF/faces-config1.xml");
				if (edit1.getFacesConfig() != null) {
					EList converterList = edit1.getFacesConfig().getConverter();
					for (int i = 0; i < converterList.size(); i++) {
						ConverterType converter = (ConverterType) converterList
								.get(i);
						EList attributeList = converter.getAttribute();
						for (int j = 0; j < attributeList.size(); j++) {
							AttributeType attType = (AttributeType) attributeList
									.get(j);
							EList iconList = attType.getIcon();
							for (int k = 0; k < iconList.size(); k++) {
								IconType iconType = (IconType) iconList.get(j);
								attributeIconResult = iconType.getSmallIcon().getTextContent();
								System.out
										.println("attribute icon name for file 1 of converter is : "
												+ attributeIconResult);
								assertEquals(attributeIcon, attributeIconResult);
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

			// attributeClass
			String attributeClassResult = null;
			try {
				edit1 = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project,
								"WEB-INF/faces-config1.xml");
				if (edit1.getFacesConfig() != null) {
					EList converterList = edit1.getFacesConfig().getConverter();
					for (int i = 0; i < converterList.size(); i++) {
						ConverterType converter = (ConverterType) converterList
								.get(i);
						EList attributeList = converter.getAttribute();
						for (int j = 0; j < attributeList.size(); j++) {
							AttributeType attType = (AttributeType) attributeList
									.get(j);
							attributeClassResult = attType.getAttributeClass().getTextContent();
							System.out
									.println("attribute classe for file 1 of converter is : "
											+ attributeClassResult);
							assertEquals("attribute-class",
									attributeClassResult);
							break;
						}
					}
				}
			} finally {

				if (edit1 != null) {
					edit1.dispose();
				}
			}

			// attributeValue
			String attributeValueResult = null;
			try {
				edit1 = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project,
								"WEB-INF/faces-config1.xml");
				if (edit1.getFacesConfig() != null) {
					EList converterList = edit1.getFacesConfig().getConverter();
					for (int i = 0; i < converterList.size(); i++) {
						ConverterType converter = (ConverterType) converterList
								.get(i);
						EList attributeList = converter.getAttribute();
						for (int j = 0; j < attributeList.size(); j++) {
							AttributeType attType = (AttributeType) attributeList
									.get(j);
							attributeValueResult = attType.getDefaultValue().getTextContent();
							System.out
									.println("attribute default value for file 1 of converter is : "
											+ attributeValueResult);
							assertEquals("default-value", attributeValueResult);
							break;
						}
					}
				}
			} finally {

				if (edit1 != null) {
					edit1.dispose();
				}
			}

			// suggestedValue
			String suggestedValueResult = null;
			try {
				edit1 = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project,
								"WEB-INF/faces-config1.xml");
				if (edit1.getFacesConfig() != null) {
					EList converterList = edit1.getFacesConfig().getConverter();
					for (int i = 0; i < converterList.size(); i++) {
						ConverterType converter = (ConverterType) converterList
								.get(i);
						EList attributeList = converter.getAttribute();
						for (int j = 0; j < attributeList.size(); j++) {
							AttributeType attType = (AttributeType) attributeList
									.get(j);
							suggestedValueResult = attType.getSuggestedValue().getTextContent();
							System.out
									.println("attribute suggested value for file 1 of converter is : "
											+ suggestedValueResult);
							assertEquals("suggested-value",
									suggestedValueResult);
							break;
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
}
