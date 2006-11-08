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
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class FacesConfigFactoryImplForWriteValidatorTwoFiles extends TestCase {
	IProject project = null;

	FacesConfigArtifactEdit edit1 = null;

	FacesConfigArtifactEdit edit2 = null;

	// String desc = "description";
	String dispName = "display-name";

	String dispNameOne = "display-name";

	String attributeDispName = "attribute-display-name";

	String attributeIcon = "attribute-icon";

	public FacesConfigFactoryImplForWriteValidatorTwoFiles(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	public void testWriteValidatorToFileTwo() {
		// IProject project = WizardUtil.getTestProject();

		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage
						.getFacesConfigFactory();

				ValidatorType newValidator = facesConfigFactory
						.createValidatorType();
				
				ValidatorIdType validatorIdType = facesConfigFactory.createValidatorIdType();
				validatorIdType.setTextContent("validator-id");
				newValidator.setValidatorId(validatorIdType);
				
				ValidatorClassType validatorClassType = facesConfigFactory.createValidatorClassType();
				validatorClassType.setTextContent("validator-class");
				newValidator.setValidatorClass(validatorClassType);

				DisplayNameType actionList = facesConfigFactory
						.createDisplayNameType();
				actionList.setTextContent(dispName);
				newValidator.getDisplayName().add(actionList);

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

				// ATTRIBUTE EXTENISON REMAIND HERE
				// There is a problem with extension. Here is a good place to
				// see that.
				// AttributeExtensionType attrExtType =
				// facesConfigFactory.createAttributeExtensionType();

				AttributeExtensionType attrExtType = facesConfigFactory
						.createAttributeExtensionType();
				attrType.getAttributeExtension().add(attrExtType);
				newValidator.getAttribute().add(attrType);

				newValidator.getAttribute().add(attrType);
			
				//adding property
				PropertyType propType = facesConfigFactory.createPropertyType();
				
				PropertyNameType propertyNameType = facesConfigFactory.createPropertyNameType();
				propertyNameType.setTextContent("property-name-validation-file-two");
				propType.setPropertyName(propertyNameType);
				
				PropertyClassType propertyClassType = facesConfigFactory.createPropertyClassType();
				propertyClassType.setTextContent("property-class-validation-file-two");
				propType.setPropertyClass(propertyClassType);
				
				DefaultValueType propertyDefaultValueType = facesConfigFactory.createDefaultValueType();
				propertyDefaultValueType.setTextContent("property-default-value-validation-file-two");
				propType.setDefaultValue(propertyDefaultValueType);
				
				newValidator.getProperty().add(propType);
				
				edit2.getFacesConfig().getValidator().add(newValidator);
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
				EList lifecycles = edit2.getFacesConfig().getValidator();
				for (int i = 0; i < lifecycles.size(); i++) {
					ValidatorType lifecycle = (ValidatorType) lifecycles.get(i);
					EList phaseListeners = lifecycle.getDisplayName();
					for (int j = 0; j < phaseListeners.size(); j++) {
						DisplayNameType phaseListener = (DisplayNameType) phaseListeners
								.get(j);
						result = phaseListener.getTextContent();
						System.out.println("display name for file 2 is : "
								+ result);
						assertEquals(dispName, result);
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

		String resultIcon = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList validatorList = edit2.getFacesConfig().getValidator();
				for (int i = 0; i < validatorList.size(); i++) {
					ValidatorType validator = (ValidatorType) validatorList
							.get(i);
					EList icons = validator.getIcon();
					for (int j = 0; j < icons.size(); j++) {
						IconType icon = (IconType) icons.get(j);
						resultIcon = icon.getLargeIcon().getTextContent();
						assertEquals(attributeIcon, resultIcon);
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

		// render-kit-id
		String resultValidatorId = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList validatorList = edit2.getFacesConfig().getValidator();
				for (int i = 0; i < validatorList.size(); i++) {
					ValidatorType validator = (ValidatorType) validatorList
							.get(i);
					resultValidatorId = validator.getValidatorId().getTextContent();
					assertEquals("validator-id", resultValidatorId);
					break;
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}

		// renderKitClass

		// render-kit-id
		String resultValidatorClass = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList validatorList = edit2.getFacesConfig().getValidator();
				for (int i = 0; i < validatorList.size(); i++) {
					ValidatorType validator = (ValidatorType) validatorList
							.get(i);
					resultValidatorClass = validator.getValidatorClass().getTextContent();
					assertEquals("validator-class", resultValidatorClass);
					break;
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}

		
		
		// ATTRIBUTE PART OF VALIDATOR.
		//attributeDisplayName
		String displayNameResult = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList validatorList = edit2.getFacesConfig().getValidator();
				for (int i = 0; i < validatorList.size(); i++) {
					ValidatorType validator = (ValidatorType) validatorList
							.get(i);
					EList attributeList = validator.getAttribute();
					for (int j = 0; j < attributeList.size(); j++) {
						AttributeType attType = (AttributeType) attributeList
								.get(j);
						EList displayNameList = attType.getDisplayName();
						for (int k = 0; k < displayNameList.size(); k++) {
							DisplayNameType dispType = (DisplayNameType) displayNameList
									.get(j);
							displayNameResult = dispType.getTextContent();
							System.out
									.println("attribute display name for file 2 of validator is : "
											+ displayNameResult);
							assertEquals(attributeDispName, displayNameResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}

		// ATTRIBUTE PART OF VALIDATOR.
		// attribute-name
		String attributeNameResult = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList validatorList = edit2.getFacesConfig().getValidator();
				for (int i = 0; i < validatorList.size(); i++) {
					ValidatorType validator = (ValidatorType) validatorList
							.get(i);
					EList attributeList = validator.getAttribute();
					for (int j = 0; j < attributeList.size(); j++) {
						AttributeType attType = (AttributeType) attributeList
								.get(j);
						attributeNameResult = attType.getAttributeName().getTextContent();
						System.out
								.println("****  attribute name for file 2 of validator is : "
										+ attributeNameResult);
						assertEquals("attribute-name", attributeNameResult);
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
		
		
		//attributeIcon
		String attributeIconResult = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList validatorList = edit2.getFacesConfig().getValidator();
				for (int i = 0; i < validatorList.size(); i++) {
					ValidatorType validator = (ValidatorType) validatorList
							.get(i);
					EList attributeList = validator.getAttribute();
					for (int j = 0; j < attributeList.size(); j++) {
						AttributeType attType = (AttributeType) attributeList
								.get(j);
						EList iconList = attType.getIcon();
						for (int k = 0; k < iconList.size(); k++) {
							IconType iconType = (IconType) iconList
									.get(j);
							attributeIconResult = iconType.getSmallIcon().getTextContent();
							System.out
									.println("attribute icon name for file 2 of validator is : "
											+ attributeIconResult);
							assertEquals(attributeIcon, attributeIconResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}

		// attributeClass
		String attributeClassResult = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList validatorList = edit2.getFacesConfig().getValidator();
				for (int i = 0; i < validatorList.size(); i++) {
					ValidatorType validator = (ValidatorType) validatorList
							.get(i);
					EList attributeList = validator.getAttribute();
					for (int j = 0; j < attributeList.size(); j++) {
						AttributeType attType = (AttributeType) attributeList
								.get(j);
						attributeClassResult = attType.getAttributeClass().getTextContent();
						System.out
								.println("attribute classe for file 2 of validator is : "
										+ attributeClassResult);
						assertEquals("attribute-class", attributeClassResult);
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
		
		// attributeValue
		String attributeValueResult = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList validatorList = edit2.getFacesConfig().getValidator();
				for (int i = 0; i < validatorList.size(); i++) {
					ValidatorType validator = (ValidatorType) validatorList
							.get(i);
					EList attributeList = validator.getAttribute();
					for (int j = 0; j < attributeList.size(); j++) {
						AttributeType attType = (AttributeType) attributeList
								.get(j);
						attributeValueResult = attType.getDefaultValue().getTextContent();
						System.out
								.println("attribute default value for file 2 of validator is : "
										+ attributeValueResult);
						assertEquals("default-value", attributeValueResult);
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
		
		
		//suggestedValue
		String suggestedValueResult = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList validatorList = edit2.getFacesConfig().getValidator();
				for (int i = 0; i < validatorList.size(); i++) {
					ValidatorType validator = (ValidatorType) validatorList
							.get(i);
					EList attributeList = validator.getAttribute();
					for (int j = 0; j < attributeList.size(); j++) {
						AttributeType attType = (AttributeType) attributeList
								.get(j);
						suggestedValueResult = attType.getSuggestedValue().getTextContent();
						System.out
								.println("attribute suggested value for file 2 of validator is : "
										+ suggestedValueResult);
						assertEquals("suggested-value", suggestedValueResult);
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
		
		
		/*
		 * 
		 * 	PropertyType propType = facesConfigFactory.createPropertyType();
				propType.setPropertyName("property-name-validation-file-two");
				propType.setPropertyClass("property-class-validation-file-two");
				propType.setDefaultValue("property-default-value-validation-file-two");
				
				newValidator.getProperty().add(propType);
		 */
		//PROPERTY PART
		String propertyNameResult = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList validatorList = edit2.getFacesConfig().getValidator();
				for (int i = 0; i < validatorList.size(); i++) {
					ValidatorType validator = (ValidatorType) validatorList
							.get(i);
					EList attributeList = validator.getProperty();
					for (int j = 0; j < attributeList.size(); j++) {
						PropertyType attType = (PropertyType) attributeList
								.get(j);
						propertyNameResult = attType.getPropertyName().getTextContent();
							System.out
									.println("propetry name for file 2 of validator is : "
											+ propertyNameResult);
							assertEquals("property-name-validation-file-two", propertyNameResult);
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

		String propertyClassResult=null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList validatorList = edit2.getFacesConfig().getValidator();
				for (int i = 0; i < validatorList.size(); i++) {
					ValidatorType validator = (ValidatorType) validatorList
							.get(i);
					EList attributeList = validator.getProperty();
					for (int j = 0; j < attributeList.size(); j++) {
						PropertyType attType = (PropertyType) attributeList
								.get(j);
						propertyClassResult = attType.getPropertyClass().getTextContent();
							System.out
									.println("propetry class for file 2 of validator is : "
											+ propertyClassResult);
							assertEquals("property-class-validation-file-two", propertyClassResult);
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
		
		
		String propertyDefaultValueResult=null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList validatorList = edit2.getFacesConfig().getValidator();
				for (int i = 0; i < validatorList.size(); i++) {
					ValidatorType validator = (ValidatorType) validatorList
							.get(i);
					EList attributeList = validator.getProperty();
					for (int j = 0; j < attributeList.size(); j++) {
						PropertyType attType = (PropertyType) attributeList
								.get(j);
						propertyDefaultValueResult = attType.getDefaultValue().getTextContent();
							System.out
									.println("propetry default value for file 2 of validator is : "
											+ propertyDefaultValueResult);
							assertEquals("property-default-value-validation-file-two", propertyDefaultValueResult);
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
		
	}

	public void testWriteValidatorToFileOne() {
		// IProject project = WizardUtil.getTestProject();

		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage
						.getFacesConfigFactory();

				ValidatorType newValidator = facesConfigFactory
						.createValidatorType();
				
				ValidatorIdType validatorIdType = facesConfigFactory.createValidatorIdType();
				validatorIdType.setTextContent("validator-id");
				newValidator.setValidatorId(validatorIdType);
				
				ValidatorClassType validatorClassType = facesConfigFactory.createValidatorClassType();
				validatorClassType.setTextContent("validator-class");
				newValidator.setValidatorClass(validatorClassType);

				DisplayNameType actionList = facesConfigFactory
						.createDisplayNameType();
				actionList.setTextContent(dispNameOne);
				newValidator.getDisplayName().add(actionList);

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
				newValidator.getAttribute().add(attrType);

				newValidator.getAttribute().add(attrType);

				edit1.getFacesConfig().getValidator().add(newValidator);
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
				EList lifecycles = edit1.getFacesConfig().getValidator();
				for (int i = 0; i < lifecycles.size(); i++) {
					ValidatorType lifecycle = (ValidatorType) lifecycles.get(i);
					EList phaseListeners = lifecycle.getDisplayName();
					for (int j = 0; j < phaseListeners.size(); j++) {
						DisplayNameType phaseListener = (DisplayNameType) phaseListeners
								.get(j);
						result = phaseListener.getTextContent();
						System.out.println("display-name for file one is: "
								+ result);
						assertEquals(dispNameOne, result);
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
	}

}
