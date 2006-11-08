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
import org.eclipse.jst.jsf.facesconfig.emf.ValueType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class FacesConfigFactoryImplForWriteManagedBeanTwoFiles extends TestCase {
	IProject project = null;

	FacesConfigArtifactEdit edit = null;

	FacesConfigArtifactEdit edit1 = null;

	public FacesConfigFactoryImplForWriteManagedBeanTwoFiles(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	public void testWriteManagedBean() {
		// IProject project = WizardUtil.getTestProject();

		String dispName = "display-name-two";
		String propertyDisplayName = "managed-property-display-name";

		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage
						.getFacesConfigFactory();

				ManagedBeanType managedBT = facesConfigFactory
						.createManagedBeanType();

				DisplayNameType actionList = facesConfigFactory
						.createDisplayNameType();
				actionList.setTextContent(dispName);
				managedBT.getDisplayName().add(actionList);

				IconType iconType = facesConfigFactory.createIconType();
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

				ManagedPropertyType manageedPropType = facesConfigFactory
						.createManagedPropertyType();

				DisplayNameType propertyDispName = facesConfigFactory
						.createDisplayNameType();
				propertyDispName.setTextContent(propertyDisplayName);
				manageedPropType.getDisplayName().add(propertyDispName);

				IconType propertyIconType = facesConfigFactory.createIconType();
				LargeIconType propertyLargeIconType = facesConfigFactory.createLargeIconType();
				propertyLargeIconType.setTextContent("property-SMALL-ICON");
				propertyIconType.setLargeIcon(propertyLargeIconType);
				manageedPropType.getIcon().add(propertyIconType);

				PropertyNameType propertyNameType = facesConfigFactory.createPropertyNameType();
				propertyNameType.setTextContent("managedBean->managedProperty->property-name");
				manageedPropType.setPropertyName(propertyNameType);
				
				PropertyClassType propertyClassType = facesConfigFactory.createPropertyClassType();
				propertyClassType.setTextContent("managedBean->managedProperty->property-class");
				manageedPropType.setPropertyClass(propertyClassType);
				
				ValueType valueType = facesConfigFactory.createValueType();
				valueType.setTextContent("some new value instead of null values");
				manageedPropType.setValue(valueType);
				managedBT.getManagedProperty().add(manageedPropType);

				// managedBT.getDisplayName().add(actionList);
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

				EList lifecycles = edit.getFacesConfig().getManagedBean();
				for (int i = 0; i < lifecycles.size(); i++) {
					ManagedBeanType lifecycle = (ManagedBeanType) lifecycles
							.get(i);
					EList phaseListeners = lifecycle.getDisplayName();
					for (int j = 0; j < phaseListeners.size(); j++) {
						DisplayNameType phaseListener = (DisplayNameType) phaseListeners
								.get(j);
						result = phaseListener.getTextContent();
						System.out
								.println("wrte-read results is (for file 2): "
										+ result);

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

	public void testWriteManagedBeanToFileOne() {
		String displayName = "display-name-file-one";

		String propertyDisplayName = "managed-property-display-name-file-one";

		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage
						.getFacesConfigFactory();

				ManagedBeanType managedBT = facesConfigFactory
						.createManagedBeanType();

				DisplayNameType actionList = facesConfigFactory
						.createDisplayNameType();
				actionList.setTextContent(displayName);
				managedBT.getDisplayName().add(actionList);

				IconType iconType = facesConfigFactory.createIconType();
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

				ManagedPropertyType manageedPropType = facesConfigFactory
						.createManagedPropertyType();

				DisplayNameType propertyDispName = facesConfigFactory
						.createDisplayNameType();
				propertyDispName.setTextContent(propertyDisplayName);
				manageedPropType.getDisplayName().add(propertyDispName);

				IconType propertyIconType = facesConfigFactory.createIconType();
				LargeIconType propertyLargeIconType = facesConfigFactory.createLargeIconType();
				propertyLargeIconType.setTextContent("property-LARGE-ICON");
				propertyIconType.setLargeIcon(propertyLargeIconType);
				manageedPropType.getIcon().add(propertyIconType);

				PropertyNameType propertyNameType = facesConfigFactory.createPropertyNameType();
				propertyNameType.setTextContent("managedBean->managedProperty->property-name");
				manageedPropType.setPropertyName(propertyNameType);
				
				PropertyClassType propertyClassType = facesConfigFactory.createPropertyClassType();
				propertyClassType.setTextContent("managedBean->managedProperty->property-class");
				manageedPropType.setPropertyClass(propertyClassType);
				
				ValueType valueType = facesConfigFactory.createValueType();
				valueType.setTextContent("some new value instead of null values");
				manageedPropType.setValue(valueType);
				managedBT.getManagedProperty().add(manageedPropType);

				// managedBT.getDisplayName().add(actionList);
				edit1.getFacesConfig().getManagedBean().add(managedBT);
				edit1.save(null);
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		String resu = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList lifecycles = edit1.getFacesConfig().getManagedBean();
				for (int i = 0; i < lifecycles.size(); i++) {
					ManagedBeanType lifecycle = (ManagedBeanType) lifecycles
							.get(i);
					EList phaseListeners = lifecycle.getDisplayName();
					for (int j = 0; j < phaseListeners.size(); j++) {
						DisplayNameType phaseListener = (DisplayNameType) phaseListeners
								.get(j);
						resu = phaseListener.getTextContent();
						System.out.println("write-read results is (file one) "
								+ resu);
						assertEquals("display-name-file-one", resu);
						break;
					}
				}
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		String icon = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList lifecycles = edit1.getFacesConfig().getManagedBean();
				for (int i = 0; i < lifecycles.size(); i++) {
					ManagedBeanType lifecycle = (ManagedBeanType) lifecycles
							.get(i);
					EList phaseListeners = lifecycle.getIcon();
					for (int j = 0; j < phaseListeners.size(); j++) {
						IconType phaseListener = (IconType) phaseListeners
								.get(j);
						icon = phaseListener.getSmallIcon().getTextContent();
						System.out.println("wrte-read ICON results is (file one) "
								+ icon);
						// for now comment this out!!
						assertEquals("SMALL-ICON", icon);
						break;
					}
				}
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		String ManagedBeanName = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList managedBeans = edit1.getFacesConfig().getManagedBean();
				for (int i = 0; i < managedBeans.size(); i++) {
					ManagedBeanType managedBean = (ManagedBeanType) managedBeans
							.get(i);

					ManagedBeanName = managedBean.getManagedBeanName().getTextContent();
					System.out.println("wrte-read results is (file one) "
							+ ManagedBeanName);

					assertEquals("MANAGED-BEAN-NAME", ManagedBeanName);
					break;
				}
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		String ManagedBeanClass = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList managedBeans = edit1.getFacesConfig().getManagedBean();
				for (int i = 0; i < managedBeans.size(); i++) {
					ManagedBeanType managedBean = (ManagedBeanType) managedBeans
							.get(i);

					ManagedBeanClass = managedBean.getManagedBeanClass().getTextContent();

					System.out.println("wrte-read results is (file one) "
							+ ManagedBeanClass);

					assertEquals("MANAGED-BEAN-CLASS", ManagedBeanClass);
					break;
				}
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		String ManagedBeanScope = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList managedBeans = edit1.getFacesConfig().getManagedBean();
				for (int i = 0; i < managedBeans.size(); i++) {
					ManagedBeanType managedBean = (ManagedBeanType) managedBeans
							.get(i);
					ManagedBeanScope = managedBean.getManagedBeanScope().getTextContent();
					System.out.println("wrte-read results is (file one) "
							+ ManagedBeanScope);
					assertEquals("MANAGED-BEAN-SCOPE", ManagedBeanScope);
					break;
				}
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// NEED TO DO THE SAME FOR ***** ==> "MANAGED PROPERTY" HERE!!!
		// PROPERTY PART
		/*
		 * 
				manageedPropType
						.setPropertyName("managedBean->managedProperty->property-name");
				manageedPropType
						.setPropertyClass("managedBean->managedProperty->property-class");
				manageedPropType
						.setValue("some new value instead of null values");
		 * 
		 */
		
	
//		display name within property
		String propertyDispNameResult = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList managedBeanList = edit1.getFacesConfig().getManagedBean();
				for (int i = 0; i < managedBeanList.size(); i++) {
					ManagedBeanType beanType = (ManagedBeanType) managedBeanList
							.get(i);
					EList attributeList = beanType.getManagedProperty();
					for (int j = 0; j < attributeList.size(); j++) {
						ManagedPropertyType attType = (ManagedPropertyType) attributeList
								.get(j);
						
						EList namesList  = attType.getDisplayName();
						for(int k=0; k<namesList.size(); k++){
						DisplayNameType iconT = (DisplayNameType) namesList.get(k);
						propertyDispNameResult = iconT.getTextContent();
						System.out
								.println("property display name for file 1 of managed bean is : "
										+ propertyDispNameResult);
						assertEquals(propertyDisplayName,propertyDispNameResult);
						
						break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		
		
		//icon within property
		String propertyIconResult = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList managedBeanList = edit1.getFacesConfig().getManagedBean();
				for (int i = 0; i < managedBeanList.size(); i++) {
					ManagedBeanType converter = (ManagedBeanType) managedBeanList
							.get(i);
					EList attributeList = converter.getManagedProperty();
					for (int j = 0; j < attributeList.size(); j++) {
						ManagedPropertyType attType = (ManagedPropertyType) attributeList
								.get(j);
						
						EList iconList  = attType.getIcon();
						for(int k=0; k<iconList.size(); k++){
						IconType iconT = (IconType) iconList.get(k);
						propertyIconResult = iconT.getLargeIcon().getTextContent();
						System.out
								.println("propetry icon for file 1 of managed bean is : "
										+ propertyIconResult);
						assertEquals("property-LARGE-ICON",
								propertyIconResult);
						
						break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		
		String propertyNameResult = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList managedBeanList = edit1.getFacesConfig().getManagedBean();
				for (int i = 0; i < managedBeanList.size(); i++) {
					ManagedBeanType beanType = (ManagedBeanType) managedBeanList
							.get(i);
					EList attributeList = beanType.getManagedProperty();
					for (int j = 0; j < attributeList.size(); j++) {
						ManagedPropertyType attType = (ManagedPropertyType) attributeList
								.get(j);
						propertyNameResult = attType.getPropertyName().getTextContent();
						System.out
								.println("property name for file 1 of managed-bean is : "
										+ propertyNameResult);
						assertEquals("managedBean->managedProperty->property-name",
								propertyNameResult);
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

	
		String propertyClassResult = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList managedBeanList = edit1.getFacesConfig().getManagedBean();
				for (int i = 0; i < managedBeanList.size(); i++) {
					ManagedBeanType beanT = (ManagedBeanType) managedBeanList
							.get(i);
					EList attributeList = beanT.getManagedProperty();
					for (int j = 0; j < attributeList.size(); j++) {
						ManagedPropertyType attType = (ManagedPropertyType) attributeList
								.get(j);
						propertyClassResult = attType.getPropertyClass().getTextContent();
						System.out
								.println("propetry class for file 1 of converer is : "
										+ propertyClassResult);
						assertEquals("managedBean->managedProperty->property-class",
								propertyClassResult);
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
