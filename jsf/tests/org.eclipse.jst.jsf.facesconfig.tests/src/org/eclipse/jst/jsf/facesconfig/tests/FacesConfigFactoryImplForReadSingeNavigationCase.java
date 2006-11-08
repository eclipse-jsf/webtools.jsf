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
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the navigation-case
 * 
 */
public class FacesConfigFactoryImplForReadSingeNavigationCase extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadSingeNavigationCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 * check to see if there is one navigation-case in existence
	 * 
	 */
	public void testEmptyNavigationCase() {
		System.out.println("TESTING EMPTY NAV - CASE");
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				// assertTrue(navRules.isEmpty());

				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType navRule = (NavigationRuleType) navRules
							.get(i);

					EList navCases = navRule.getNavigationCase();
					assertTrue(!navCases.isEmpty());

				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
/*
 * Check for the Description. There should be at least one of such kind
 * in the xml file to be used  as input for this to behave correctly.
 */
	public void testDescription() {
		System.out.println("TESTING NAV - CASE - Description");
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				assertTrue(!navRules.isEmpty());
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType navRule = (NavigationRuleType) navRules
							.get(i);

					// assertEquals("/Page1.jsp",sFromTreeId);
					EList navCases = navRule.getNavigationCase();
					assertTrue(!navCases.isEmpty());
					System.out.println("	navCases size " + navCases.size());
					for (int j = 0; j < navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases
								.get(j);
						EList desc = navCase.getDescription();
						assertTrue(!desc.isEmpty());
						//System.out.println("size of Decription under navigation-case is ==> " + desc.size() );
					}
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}

	}

	/*
	 * Check for the Display-name. There should be at least one of such kind
	 */
	public void tetsDisplayName() {
		System.out.println("TESTING NAV - CASE - Display-name");
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				assertTrue(!navRules.isEmpty());
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType navRule = (NavigationRuleType) navRules
							.get(i);
					// assertEquals("/Page1.jsp",sFromTreeId);
					EList navCases = navRule.getNavigationCase();
					assertTrue(!navCases.isEmpty());
					System.out.println("	navCases size " + navCases.size());
					for (int j = 0; j < navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases
								.get(j);
						EList caseNames = navCase.getDisplayName();
						assertTrue(!caseNames.isEmpty());
						System.out.println("CLASS  = " + caseNames.getClass());
					}

				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}

	}
	
	/*
	 * Test for the existence of icon within the navigation-case
	 * 
	 */
	public void testIcon() {
		System.out.println("TESTING NAV - CASE - Description");
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				assertTrue(!navRules.isEmpty());
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType navRule = (NavigationRuleType) navRules
							.get(i);

					// assertEquals("/Page1.jsp",sFromTreeId);
					EList navCases = navRule.getNavigationCase();
					assertTrue(!navCases.isEmpty());
					System.out.println("	navCases size " + navCases.size());
					for (int j = 0; j < navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases
								.get(j);
						EList icon = navCase.getIcon();
						assertTrue(!icon.isEmpty());
						System.out.println("size of Decription under navigation-case is ==> " + icon.size() );
					}
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}

	}


	/*
	 * Test for the from-action of the navigation-case rule
	 * 
	 */
	public void testFromAction() {
		System.out.println("TESTING NAV - CASE - from-action");
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				assertTrue(!navRules.isEmpty());
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType navRule = (NavigationRuleType) navRules.get(i);
					EList navCases = navRule.getNavigationCase();
					assertTrue(!navCases.isEmpty());
						for(int j=0;j<navCases.size(); j++){
							NavigationCaseType navCase = (NavigationCaseType)navCases.get(j);
							String fromAction = navCase.getFromAction().getTextContent();
							System.out.println("from Action should be : :  :>>> " + fromAction);
							assertTrue(!fromAction.equals(null) );
							assertEquals("from-action", fromAction);	
						}
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * testing for the from-outcome? in navigation-case
	 * 
	 */
	
	public void testFromOutcome() {
		System.out.println("TESTING NAV - CASE - from-outcome");
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				assertTrue(!navRules.isEmpty());
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType navRule = (NavigationRuleType) navRules.get(i);
					EList navCases = navRule.getNavigationCase();
					assertTrue(!navCases.isEmpty());
						for(int j=0;j<navCases.size(); j++){
							NavigationCaseType navCase = (NavigationCaseType)navCases.get(j);
							String fromOutcome = navCase.getFromOutcome().getTextContent();
							System.out.println("from Outocome should be : :  :>>> " + fromOutcome);
							assertTrue(!fromOutcome.equals(null) );
							//assertEquals("from-outcome", fromOutcome);
						}
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	/*
	 * Check for the to-view-id. There should be at least one of such kind
	 */
	public void testToViewId() {
		System.out.println("TESTING NAV - CASE - to-view-id");
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				assertTrue(!navRules.isEmpty());
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType navRule = (NavigationRuleType) navRules.get(i);
					EList navCases = navRule.getNavigationCase();
					assertTrue(!navCases.isEmpty());
						for(int j=0;j<navCases.size(); j++){
							NavigationCaseType navCase = (NavigationCaseType)navCases.get(j);
							String toViewId = navCase.getToViewId().getTextContent();
							System.out.println("to-view-id should be : :  :>>> " + toViewId);
							assertTrue(!toViewId.equals(null) );
							assertEquals("/edit.jsp", toViewId);
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