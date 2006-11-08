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
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class FacesConfigFactoryImplForWriteReferencedBeanTwoFiles extends TestCase {
	IProject project = null;
	FacesConfigArtifactEdit edit1 = null;
	FacesConfigArtifactEdit edit2 = null;	
	String desc = "description";
	String dispName = "display-name";
	String validatorId = "validator-id";
	String smallIcon = "small-icon";
	String referencedBeanClass = "reference-bean-class";
	String referencedBeanName = "reference-bean-name";
	
	public FacesConfigFactoryImplForWriteReferencedBeanTwoFiles(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}
	
	public void testWriteReferencedBean() {
		//IProject project = WizardUtil.getTestProject();
		
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
	
				ReferencedBeanType newApplication = facesConfigFactory.createReferencedBeanType();
				
				DisplayNameType  actionList=  facesConfigFactory.createDisplayNameType();
				actionList.setTextContent(dispName);
				newApplication.getDisplayName().add(actionList);
				
				IconType  iconType=  facesConfigFactory.createIconType();
				SmallIconType smallIconType = facesConfigFactory.createSmallIconType();
				smallIconType.setTextContent(smallIcon);
				iconType.setSmallIcon(smallIconType);
				newApplication.getIcon().add(iconType);
				
				ReferencedBeanNameType referencedBeanNameType = facesConfigFactory.createReferencedBeanNameType();
				referencedBeanNameType.setTextContent(referencedBeanName);
				newApplication.setReferencedBeanName(referencedBeanNameType);
				
				ReferencedBeanClassType referencedBeanClassType = facesConfigFactory.createReferencedBeanClassType();
				referencedBeanClassType.setTextContent(referencedBeanClass);
				newApplication.setReferencedBeanClass(referencedBeanClassType);
				
				edit2.getFacesConfig().getReferencedBean().add(newApplication);
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
				EList referencedBeans = edit2.getFacesConfig().getReferencedBean();
				for (int i = 0; i < referencedBeans.size(); i++) {
					ReferencedBeanType referncedBean = (ReferencedBeanType) referencedBeans.get(i);
					EList dispNames = referncedBean.getDisplayName();
					for (int j=0; j<dispNames.size(); j++) {
						DisplayNameType phaseListener = (DisplayNameType)dispNames.get(j);
						result = phaseListener.getTextContent();
						System.out.println("display-name for reference-bean is " + result);
						assertEquals(dispName,result);
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
		
		//icon
		String icon = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList referencedBeans = edit2.getFacesConfig().getReferencedBean();
				for (int i = 0; i < referencedBeans.size(); i++) {
					ReferencedBeanType referncedBean = (ReferencedBeanType) referencedBeans.get(i);
					EList icons = referncedBean.getIcon();
					for (int j = 0; j < icons.size(); j++) {
						IconType phaseListener = (IconType)icons.get(j);
						icon = phaseListener.getSmallIcon().getTextContent();
						System.out.println("small-icon for reference-bean is " + icon);
						assertEquals(smallIcon,icon);
						break;
					}
				}
			}
		} finally {
			//assertTrue(icon != null && icon.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		
		//referenced-bean name
		String beanName = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList referencedBeans = edit2.getFacesConfig().getReferencedBean();
				for (int i = 0; i < referencedBeans.size(); i++) {
					ReferencedBeanType referncedBean = (ReferencedBeanType) referencedBeans.get(i);
					beanName = referncedBean.getReferencedBeanName().getTextContent();
						System.out.println("display-name for reference-bean is " + beanName);
						assertEquals(referencedBeanName,beanName);
						break;
				}
			}
		} finally {
			//assertTrue(referencedBeanName != null && referencedBeanName.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		
		
		//referenced-bean class

		String beanClass = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList referencedBeans = edit2.getFacesConfig().getReferencedBean();
				for (int i = 0; i < referencedBeans.size(); i++) {
					ReferencedBeanType referencedBean = (ReferencedBeanType) referencedBeans.get(i);
					beanClass = referencedBean.getReferencedBeanClass().getTextContent();
						System.out.println("display-name for reference-bean is " + beanClass);
						assertEquals(referencedBeanClass,beanClass);
						break;
					}
			}
		} finally {
			//assertTrue(referencedBeanClass != null && referencedBeanClass.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		
	}
	
	public void testWriteReferencedBeanFileTwo() {
		//IProject project = WizardUtil.getTestProject();
		
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
	
				ReferencedBeanType newApplication = facesConfigFactory.createReferencedBeanType();
				
				DisplayNameType  actionList=  facesConfigFactory.createDisplayNameType();
				actionList.setTextContent(dispName);
				newApplication.getDisplayName().add(actionList);
				
				IconType  iconType=  facesConfigFactory.createIconType();
				SmallIconType smallIconType = facesConfigFactory.createSmallIconType();
				smallIconType.setTextContent(smallIcon);
				iconType.setSmallIcon(smallIconType);
				newApplication.getIcon().add(iconType);
				
				ReferencedBeanNameType referencedBeanNameType = facesConfigFactory.createReferencedBeanNameType();
				referencedBeanNameType.setTextContent(referencedBeanName);
				newApplication.setReferencedBeanName(referencedBeanNameType);
				
				ReferencedBeanClassType referencedBeanClassType = facesConfigFactory.createReferencedBeanClassType();
				referencedBeanClassType.setTextContent(referencedBeanClass);
				newApplication.setReferencedBeanClass(referencedBeanClassType);
				
				edit1.getFacesConfig().getReferencedBean().add(newApplication);
				edit1.save(null);
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		String result = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit2.getFacesConfig() != null) {
				EList referencedBeans = edit2.getFacesConfig().getReferencedBean();
				for (int i = 0; i < referencedBeans.size(); i++) {
					ReferencedBeanType referncedBean = (ReferencedBeanType) referencedBeans.get(i);
					EList dispNames = referncedBean.getDisplayName();
					for (int j=0; j<dispNames.size(); j++) {
						DisplayNameType phaseListener = (DisplayNameType)dispNames.get(j);
						result = phaseListener.getTextContent();
						System.out.println("display-name for reference-bean is " + result);
						assertEquals(dispName,result);
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
		
		//icon
		String icon = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit2.getFacesConfig() != null) {
				EList referencedBeans = edit2.getFacesConfig().getReferencedBean();
				for (int i = 0; i < referencedBeans.size(); i++) {
					ReferencedBeanType referncedBean = (ReferencedBeanType) referencedBeans.get(i);
					EList icons = referncedBean.getIcon();
					for (int j=0; j<icons.size(); j++) {
						IconType phaseListener = (IconType)icons.get(j);
						icon = phaseListener.getSmallIcon().getTextContent();
						System.out.println("small-icon for reference-bean is " + icon);
						assertEquals(smallIcon,icon);
						break;
					}
				}
			}
		} finally {
			//assertTrue(icon != null && icon.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		
		//referenced-bean name
		String beanName = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit2.getFacesConfig() != null) {
				EList referencedBeans = edit2.getFacesConfig().getReferencedBean();
				for (int i = 0; i < referencedBeans.size(); i++) {
					ReferencedBeanType referncedBean = (ReferencedBeanType) referencedBeans.get(i);
					beanName = referncedBean.getReferencedBeanName().getTextContent();
						System.out.println("display-name for reference-bean is " + beanName);
						assertEquals(referencedBeanName,beanName);
						break;
				}
			}
		} finally {
			//assertTrue(referencedBeanName != null && referencedBeanName.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		
		//referenced-bean class
		String beanClass = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit2.getFacesConfig() != null) {
				EList referencedBeans = edit2.getFacesConfig().getReferencedBean();
				for (int i = 0; i < referencedBeans.size(); i++) {
					ReferencedBeanType referencedBean = (ReferencedBeanType) referencedBeans.get(i);
						beanClass = referencedBean.getReferencedBeanClass().getTextContent();
						System.out.println("display-name for reference-bean is " + beanClass);
						assertEquals(referencedBeanClass,beanClass);
						break;
					}
				}
		} finally {
			//assertTrue(referencedBeanClass != null && referencedBeanClass.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}
	}	
}