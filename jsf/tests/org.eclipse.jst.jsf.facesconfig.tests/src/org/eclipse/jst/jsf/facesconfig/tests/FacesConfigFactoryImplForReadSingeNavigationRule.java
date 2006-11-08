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
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the FacesConfigFactoryImpl
 * class. 
 *
 */


public class FacesConfigFactoryImplForReadSingeNavigationRule extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadSingeNavigationRule(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 * The following method is used to test for the existence of at least
	 * one navigation-rule
	 */
	public void testSingleNavigationRule() {
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

	// Test for the Descirption

	public void testNonEmptyDescription() {
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
					String navRulesDisName = navRule.getDisplayName()
							.toString();
					System.out.println("navRule names " + navRulesDisName);

					// String sFromTreeId = navRule.getFromViewId().getTextContent();
					// assertEquals("/Page1.jsp",sFromTreeId);
					EList desc = navRule.getDescription();

					assertTrue(!desc.isEmpty());
					// sassertEquals(2,navCases.size());
					System.out.println(" size of description is : "
							+ desc.size());
			
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Check for Sisplay-name. There should be at least one item of such kind
	 */
	public void testNonEmptyDisplayName() {
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
					EList disp = navRule.getDescription();
					assertTrue(!disp.isEmpty());
					System.out.println(" size of display name  is : "
							+ disp.size());
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Test for the exisence of navigation-cases
	 */

	public void testNonEmptyNavigationCases() {
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

					EList navCases = navRule.getNavigationCase();

					// just check that from-View-id is not empty and that
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
	 * assert the existence of icon 
	 */

	public void testNonEmptyIcon() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getNavigationRule();
				assertTrue(!navRules.isEmpty());
				NavigationRuleType navRule = (NavigationRuleType) navRules
						.get(0);

				EList icon = navRule.getIcon();
				assertTrue(!icon.isEmpty());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

}