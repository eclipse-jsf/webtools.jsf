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
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test for Component with in faces-config
 * root Element.
 *
 */
public class FacesConfigFactoryImplForReadSingleComponent extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadSingleComponent(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 * assert there is at least one Component listed
	 */
	public void testSingleComponent() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList navComponent = edit.getFacesConfig().getComponent() ;
				assertTrue(!navComponent.isEmpty());
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
				EList component = edit.getFacesConfig().getComponent();
				assertTrue(!component.isEmpty());
				for (int i = 0; i < component.size(); i++) {
					ComponentType navRule = (ComponentType) component
							.get(i);
					
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
	 *Check for the Display name 
	 * 
	 */
	public void testDisplayName() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getComponent();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ComponentType navRule = (ComponentType) comp
							.get(i);

					EList disp = navRule.getDisplayName();

					assertTrue(!disp.isEmpty());
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	
	/*
	 * get the icon of a compnent
	 * 
	 */
	public void testNonEmptyIcon() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getComponent();
				assertTrue(!comp.isEmpty());
				ComponentType navRule = (ComponentType) comp
						.get(0);

				EList icon = navRule.getIcon();
				assertTrue(!icon.isEmpty());

				// HOW COME THE SIZE IS 0 FOR ICON????
				System.out.println(" total number of icons  is : "
						+ icon.size());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	/*
	 * check to see there is the Component-type and the Component-class
	 */
	public void testNonEmptyComponentTypeAndClass() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList navRules = edit.getFacesConfig().getComponent();
				assertTrue(!navRules.isEmpty());
				for (int i = 0; i < navRules.size(); i++) {
					ComponentType navRule = (ComponentType) navRules
							.get(i);

					String compType = navRule.getComponentType().getTextContent();
					String compClass = navRule.getComponentClass().getTextContent();
					System.out.println("Compoonent type is  : " + compType);
					System.out.println("Compoonent class is  : " + compClass);
					assertTrue(!compType.equals(null));
					assertEquals("component-type", compType);
					assertEquals("ComponentClass", compClass);
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	
	/*
	 * check for the attribute part of the Component 
	 */
	public void testAttribute() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getComponent();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ComponentType navRule = (ComponentType) comp
							.get(i);

					EList attr = navRule.getAttribute();
					assertTrue(!attr.isEmpty());
					assertEquals(1, attr.size());
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

/*
 * check to see there is property listed in the input file
 */
	public void testProperty() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getComponent();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ComponentType navRule = (ComponentType) comp
							.get(i);

					EList attr = navRule.getProperty();
					assertTrue(!attr.isEmpty());
					assertEquals(1, attr.size());
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	/*
	 * Check for hte Component-Extension inside Component
	 */

	public void testComponentExtension() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getComponent();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ComponentType navRule = (ComponentType) comp
							.get(i);

					EList ext = navRule.getComponentExtension();
					assertTrue(!ext.isEmpty());
					assertEquals(1, ext.size());
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
}