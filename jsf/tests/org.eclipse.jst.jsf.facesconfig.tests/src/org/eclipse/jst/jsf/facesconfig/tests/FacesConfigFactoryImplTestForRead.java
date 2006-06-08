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
 * This Junit class is used to test the FacesConfigFactoryImpl
 * class. 
 * 
 */
public class FacesConfigFactoryImplTestForRead extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplTestForRead(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}

	/*
	 * The following method is used to test for the empty navigation rule. Since
	 * I am supplying a single faces-config.xml file as a testing file, I had to
	 * testcases fit in to it by controlling the conditions
	 * 
	 */
	public void testEmptyNavigationRule() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				assertTrue(!navRules.isEmpty());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testSingleNavigationsRule() {
		// testSingleNavigationsRule
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				assertTrue(navRules.size() != 0);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testMultipleNavigationRules() {
		// assertTrue(...);

		// IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;

		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();

				// assertEquals(1, navRules.size());
				System.out.println("navRules size " + navRules.size());
				// for (int num=0; num<4; num++) {
				// Sort the items
				for (int i = 0; i < navRules.size(); i++) {
					NavigationRuleType navRule = (NavigationRuleType) navRules
							.get(i);

					String navRulesDisName = navRule.getDisplayName()
							.toString();
					System.out.println("navRule names " + navRulesDisName);

					//String sFromTreeId = navRule.getFromViewId();
					// assertEquals("/Page1.jsp",sFromTreeId);
					EList navCases = navRule.getNavigationCase();

					// sassertEquals(2,navCases.size());
					System.out.println("	navCases size " + navCases.size());
					for (int j = 0; j < navCases.size(); j++) {
						NavigationCaseType navCase = (NavigationCaseType) navCases
								.get(j);

						EList caseNames = navCase.getDisplayName();
						System.out.println("CLASS  = " + caseNames.getClass());

						String sOutcome = navCase.getFromOutcome().getTextContent();
						System.out.println("	sOutcome " + sOutcome);
						String sActionRef = navCase.getFromAction().getTextContent();
						System.out.println("	sActionRef " + sActionRef);
						String sToPage = navCase.getToViewId().getTextContent();
						System.out.println("	sOutcome " + sToPage);
					}
					// }

				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

}