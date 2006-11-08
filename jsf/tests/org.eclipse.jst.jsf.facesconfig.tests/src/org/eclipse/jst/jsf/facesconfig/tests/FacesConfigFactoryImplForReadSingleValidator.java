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
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the validator which is one of 
 * many items inside the root elemnt faces-config in the configuration
 * information hierarchy of the faces-config.xml file 
 *
 */
public class FacesConfigFactoryImplForReadSingleValidator extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadSingleValidator(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 * The following method is used to test for the empty navigation rule. Since
	 * I am supplying a single faces-config.xml file as a testing file, I had to
	 * testcases fit in to it by controlling the conditions
	 * 
	 */
	public void testSingleValidator() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList validator = edit.getFacesConfig().getValidator();
				assertTrue(!validator.isEmpty());
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
				EList validator = edit.getFacesConfig().getValidator();
				assertTrue(!validator.isEmpty());
				for (int i = 0; i < validator.size(); i++) {
					ValidatorType item = (ValidatorType) validator
							.get(i);
					
					EList desc = item.getDescription();

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

	
	public void testDisplayName() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList validator = edit.getFacesConfig().getValidator();
				assertTrue(!validator.isEmpty());
				for (int i = 0; i < validator.size(); i++) {
					ValidatorType item = (ValidatorType) validator
							.get(i);

					EList disp = item.getDisplayName();
					assertTrue(!disp.isEmpty()&& disp.size()==1);
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
				EList validator = edit.getFacesConfig().getValidator();
				assertTrue(!validator.isEmpty());
				ValidatorType item = (ValidatorType) validator
						.get(0);

				EList icon = item.getIcon();
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
	
	public void testValidatorId() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList validator = edit.getFacesConfig().getValidator();
				assertTrue(!validator.isEmpty());
				for (int i = 0; i < validator.size(); i++) {
					ValidatorType item = (ValidatorType) validator
							.get(i);
					String validatorId = item.getValidatorId().getTextContent();
					//String compClass = item.getComponentClass();
					// just check that from-View-id is not empty and that
					// has emelent named "fromViewId"
					System.out.println("validator-Id is  : " + validatorId);
					assertEquals("validator-Id", validatorId);
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	
	public void testNonEmptyValidatorClass() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList validator = edit.getFacesConfig().getValidator();
				assertTrue(!validator.isEmpty());
				for (int i = 0; i < validator.size(); i++) {
					ValidatorType item = (ValidatorType) validator
							.get(i);
					String validatorClass = item.getValidatorClass().getTextContent();
					System.out.println("validator-class is  : " + validatorClass);
					assertEquals("validator-class", validatorClass);
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	
	public void testAttribute() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList validator = edit.getFacesConfig().getValidator();
				assertTrue(!validator.isEmpty());
				for (int i = 0; i < validator.size(); i++) {
					ValidatorType navRule = (ValidatorType)validator.get(i);
					EList attr = navRule.getAttribute();
					assertTrue(!attr.isEmpty());
					//assertEquals(1, attr.size());
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testProperty() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList validator = edit.getFacesConfig().getValidator();
				assertTrue(!validator.isEmpty());
				for (int i = 0; i < validator.size(); i++) {
					ValidatorType item = (ValidatorType)validator.get(i);
					EList property = item.getProperty();
					assertTrue(!property.isEmpty());
					//assertEquals(1, property.size());
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}


}