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
import org.eclipse.jst.jsf.facesconfig.emf.FromActionType;
import org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class FacesConfigFactoryImplForWriteNavigationRule extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForWriteNavigationRule(String name) {
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
		
		//Navigation case related values
		String caseDispName ="navigation-case-display-name";
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
	
				NavigationRuleType newApplication = facesConfigFactory.createNavigationRuleType();
				
				DisplayNameType  actionList=  facesConfigFactory.createDisplayNameType();
				actionList.setTextContent(dispName);
				newApplication.getDisplayName().add(actionList);
				
				IconType  iconType=  facesConfigFactory.createIconType();
				LargeIconType largeIconType = facesConfigFactory.createLargeIconType();
				largeIconType.setTextContent("large-icon");
				iconType.setLargeIcon(largeIconType);
				newApplication.getIcon().add(iconType);
				
				FromViewIdType fromViewIdType = facesConfigFactory.createFromViewIdType();
				fromViewIdType.setTextContent("from-view-id");
				newApplication.setFromViewId(fromViewIdType);
				
				NavigationCaseType navCaseType = facesConfigFactory.createNavigationCaseType();
				
				DisplayNameType  navCasedisplayName=  facesConfigFactory.createDisplayNameType();
				navCasedisplayName.setTextContent(caseDispName);
				navCaseType.getDisplayName().add(navCasedisplayName);
				
				IconType  navCaseIconType=  facesConfigFactory.createIconType();
				LargeIconType navCaseLargeIconType = facesConfigFactory.createLargeIconType();
				navCaseLargeIconType.setTextContent("navigation-case-icon");
				navCaseIconType.setLargeIcon(navCaseLargeIconType);
				navCaseType.getIcon().add(navCaseIconType);
				
				FromActionType fromActionType = facesConfigFactory.createFromActionType();
				fromActionType.setTextContent("navigation-case-from-action");
				navCaseType.setFromAction(fromActionType);
				
				FromOutcomeType fromOutcomeType = facesConfigFactory.createFromOutcomeType();
				fromOutcomeType.setTextContent("navigation-case-from-outcome");
				navCaseType.setFromOutcome(fromOutcomeType);
				
				ToViewIdType toViewIdType = facesConfigFactory.createToViewIdType();
				toViewIdType.setTextContent("navigation-case-from-view-id");
				navCaseType.setToViewId(toViewIdType);
				
				newApplication.getNavigationCase().add(navCaseType);
				
				edit.getFacesConfig().getNavigationRule().add(newApplication);
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