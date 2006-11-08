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
import org.eclipse.jst.jsf.facesconfig.emf.FromActionType;
import org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class FacesConfigFactoryImplForWriteNavigationRuleTwoFiles extends TestCase {
	IProject project = null;
	//IProject project = WizardUtil.getTestProject();
	FacesConfigArtifactEdit edit = null;
	FacesConfigArtifactEdit edit1 = null;
	
	public FacesConfigFactoryImplForWriteNavigationRuleTwoFiles(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}
	
	public void testWriteApplication() {
		
		//String desc = "description";
		String dispNameTwo = "display-name";
		
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
				actionList.setTextContent(dispNameTwo);
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
		
		String result = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList names = rule.getDisplayName();
					for (int j=0; j<names.size(); j++) {
						DisplayNameType displayName = (DisplayNameType)names.get(j);
						result = displayName.getTextContent();
						assertEquals( dispNameTwo ,result);
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
		
		
		//icon
		String resultIcon=null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList icons = rule.getIcon();
					for (int j=0; j<icons.size(); j++) {
						IconType iconName = (IconType)icons.get(j);
						resultIcon = iconName.getLargeIcon().getTextContent();
						assertEquals( "large-icon" ,resultIcon);
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
		
		//fromViewId
		String resultFromViewId=null;
		
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					resultFromViewId = rule.getFromViewId().getTextContent();
						assertEquals( "from-view-id" ,resultFromViewId);
						break;
					}
				}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}
		
		//NAVIGATION CASE REMAINING HERE!!!
		String navCaseResult=null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList navCases = rule.getNavigationCase();
					for (int j=0; j<navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases.get(j);
						EList names = navCase.getDisplayName();
						for(int k=0; k<names.size();k++){
							DisplayNameType displayName = (DisplayNameType)names.get(k);
							navCaseResult = displayName.getTextContent();
							assertEquals( "navigation-case-display-name" ,navCaseResult);
							break;
						}
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

		//icon
		//"navigation-case-icon"
		String iconResult=null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList navCases = rule.getNavigationCase();
					for (int j=0; j<navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases.get(j);
						EList icons = navCase.getIcon();
						for(int k=0; k<icons.size();k++){
							IconType icon = (IconType)icons.get(k);
							iconResult = icon.getLargeIcon().getTextContent();
							assertEquals("navigation-case-icon" ,iconResult);
							break;
						}
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}
		
		
		//fromAction
		String fromActionResult=null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList navCases = rule.getNavigationCase();
					for (int j=0; j<navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases.get(j);
						fromActionResult = navCase.getFromAction().getTextContent();
							assertEquals( "navigation-case-from-action" ,fromActionResult);
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
		
		//fromOutcome
		String fromOutcomeResult=null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList navCases = rule.getNavigationCase();
					for (int j=0; j<navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases.get(j);
						fromOutcomeResult = navCase.getFromOutcome().getTextContent();
							assertEquals( "navigation-case-from-outcome" ,fromOutcomeResult);
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
		
		//toViewId
		String toViewIdResult=null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList navCases = rule.getNavigationCase();
					for (int j=0; j<navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases.get(j);
						toViewIdResult = navCase.getToViewId().getTextContent();
							assertEquals( "navigation-case-from-view-id",toViewIdResult);
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
	
	
	public void testWriteNavigationRuleToFileOne() {
		//String desc = "description-to-file-one";
		String dispNameOne = "display-name-of-file-one";
		
		//Navigation case related values
		String caseDispName ="navigation-case-display-name-of-file-one";
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
	
				NavigationRuleType newApplication = facesConfigFactory.createNavigationRuleType();
				
				DisplayNameType  actionList=  facesConfigFactory.createDisplayNameType();
				actionList.setTextContent(dispNameOne);
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
				navCaseLargeIconType.setTextContent("navigation-case-icon-file-one");
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
				
				edit1.getFacesConfig().getNavigationRule().add(newApplication);
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
				EList navRules = edit1.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList names = rule.getDisplayName();
					for (int j=0; j<names.size(); j++) {
						DisplayNameType displayName = (DisplayNameType)names.get(j);
						result = displayName.getTextContent();
						assertEquals( dispNameOne ,result);
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		//icon
		String resultIcon=null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList navRules = edit1.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList icons = rule.getIcon();
					for (int j=0; j<icons.size(); j++) {
						IconType iconName = (IconType)icons.get(j);
						resultIcon = iconName.getLargeIcon().getTextContent();
						assertEquals( "large-icon" ,resultIcon);
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		//fromViewId
		String resultFromViewId=null;
		
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList navRules = edit1.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					resultFromViewId = rule.getFromViewId().getTextContent();
						assertEquals( "from-view-id" ,resultFromViewId);
						break;
					}
				}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		//NAVIGATION CASE REMAINING HERE!!!
		String navCaseResult=null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList navRules = edit1.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList navCases = rule.getNavigationCase();
					for (int j=0; j<navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases.get(j);
						EList names = navCase.getDisplayName();
						for(int k=0; k<names.size();k++){
							DisplayNameType displayName = (DisplayNameType)names.get(k);
							navCaseResult = displayName.getTextContent();
							assertEquals( "navigation-case-display-name-of-file-one" ,navCaseResult);
							break;
						}
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		//icon
		//"navigation-case-icon"
		String iconResult=null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList navRules = edit1.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList navCases = rule.getNavigationCase();
					for (int j=0; j<navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases.get(j);
						EList icons = navCase.getIcon();
						for(int k=0; k<icons.size();k++){
							IconType icon = (IconType)icons.get(k);
							iconResult = icon.getLargeIcon().getTextContent();
							assertEquals("navigation-case-icon-file-one" ,iconResult);
							break;
						}
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		
		//fromAction
		String fromActionResult=null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList navRules = edit1.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList navCases = rule.getNavigationCase();
					for (int j=0; j<navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases.get(j);
						fromActionResult = navCase.getFromAction().getTextContent();
							assertEquals( "navigation-case-from-action" ,fromActionResult);
							break;
						}
					}
				}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		//fromOutcome
		String fromOutcomeResult=null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList navRules = edit1.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList navCases = rule.getNavigationCase();
					for (int j=0; j<navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases.get(j);
						fromOutcomeResult = navCase.getFromOutcome().getTextContent();
							assertEquals( "navigation-case-from-outcome" ,fromOutcomeResult);
							break;
						}
					}
				}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		//toViewId
		String toViewIdResult=null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList navRules = edit1.getFacesConfig().getNavigationRule();
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType rule = (NavigationRuleType) navRules.get(i);
					EList navCases = rule.getNavigationCase();
					for (int j=0; j<navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases.get(j);
						toViewIdResult = navCase.getToViewId().getTextContent();
							assertEquals( "navigation-case-from-view-id",toViewIdResult);
							break;
						}
					}
				}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		
		
	}
}