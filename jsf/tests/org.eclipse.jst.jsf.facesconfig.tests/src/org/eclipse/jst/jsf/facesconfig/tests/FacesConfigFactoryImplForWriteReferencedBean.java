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
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class FacesConfigFactoryImplForWriteReferencedBean extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForWriteReferencedBean(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}
	
	public void testWriteReferencedBeanToFileTwo() {
		//IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;
		
		String dispName = "display-name";
		String icon = "small-icon";
		String referenceBeanClass = "reference-bean-class";
		String referenceBeanName = "reference-bean-name";
		
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
	
				ReferencedBeanType newApplication = facesConfigFactory.createReferencedBeanType();
				
				DisplayNameType  actionList=  facesConfigFactory.createDisplayNameType();
				actionList.setTextContent(dispName);
				newApplication.getDisplayName().add(actionList);
				
				IconType  iconType=  facesConfigFactory.createIconType();
				SmallIconType smallIconType = facesConfigFactory.createSmallIconType();
				smallIconType.setTextContent(icon);
				iconType.setSmallIcon(smallIconType);
				newApplication.getIcon().add(iconType);
				
				ReferencedBeanNameType referencedBeanNameType = facesConfigFactory.createReferencedBeanNameType();
				referencedBeanNameType.setTextContent(referenceBeanName);
				newApplication.setReferencedBeanName(referencedBeanNameType);
				
				ReferencedBeanClassType referencedBeanClassType = facesConfigFactory.createReferencedBeanClassType();
				referencedBeanClassType.setTextContent(referenceBeanClass);
				newApplication.setReferencedBeanClass(referencedBeanClassType);
				
				edit.getFacesConfig().getReferencedBean().add(newApplication);
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

	
	public void testWriteReferencedBeanToFileOne() {
		//IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit1 = null;
		
		String dispName = "display-name";
		String icon = "small-icon";
		String referenceBeanClass = "reference-bean-class";
		String referenceBeanName = "reference-bean-name";
		
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
				smallIconType.setTextContent(icon);
				iconType.setSmallIcon(smallIconType);
				newApplication.getIcon().add(iconType);
				
				ReferencedBeanNameType referencedBeanNameType = facesConfigFactory.createReferencedBeanNameType();
				referencedBeanNameType.setTextContent(referenceBeanName);
				newApplication.setReferencedBeanName(referencedBeanNameType);
				
				ReferencedBeanClassType referencedBeanClassType = facesConfigFactory.createReferencedBeanClassType();
				referencedBeanClassType.setTextContent(referenceBeanClass);
				newApplication.setReferencedBeanClass(referencedBeanClassType);
				
				edit1.getFacesConfig().getReferencedBean().add(newApplication);
				edit1.save(null);
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		/*
		String result = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit1.getFacesConfig() != null) {
				EList lifecycles = edit1.getFacesConfig().getValidator();
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
			if (edit1 != null) {
				edit1.dispose();
			}
		}*/
	}
}