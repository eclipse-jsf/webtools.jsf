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
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
/*
 * This Junit class is used to test the referenced-bean which is one of 
 * many items inside the root elemnt faces-config in the configuration
 * information hierarchy of the faces-config.xml file 
 *
 */
public class FacesConfigFactoryImplForReadSingleReferencedBean extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadSingleReferencedBean(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}

	/*
	 * check for the existence of referenced-bean with in
	 * the root element - faces-config. There should be at least
	 * one of such kind (item)
	 */
	public void testSingleReferncedBean() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList referencedBean = edit.getFacesConfig().getReferencedBean();
				assertTrue(!referencedBean.isEmpty());
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
				EList referencedBean = edit.getFacesConfig().getReferencedBean();
				assertTrue(!referencedBean.isEmpty());
				for (int i = 0; i < referencedBean.size(); i++) {
					ReferencedBeanType item = (ReferencedBeanType) referencedBean
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

	
	/*
	 * Check for the Display-name
	 */
	public void testDisplayName() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList referencedBean = edit.getFacesConfig().getReferencedBean();
				assertTrue(!referencedBean.isEmpty());
				for (int i = 0; i < referencedBean.size(); i++) {
					ReferencedBeanType item = (ReferencedBeanType) referencedBean
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
	 * get the icon 
	 * 
	 */
	public void testNonEmptyIcon() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList referencedBean = edit.getFacesConfig().getReferencedBean();
				assertTrue(!referencedBean.isEmpty());
				ReferencedBeanType item = (ReferencedBeanType) referencedBean
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
	/*
	 * check for refrenced-bean-name
	 */
	public void testReferencedBeaName(){
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList referencedBean = edit.getFacesConfig().getReferencedBean();
				assertTrue(!referencedBean.isEmpty());
				for (int i = 0; i < referencedBean.size(); i++) {
					ReferencedBeanType item = (ReferencedBeanType) referencedBean
							.get(i);
					String  referencedBeanName = item.getReferencedBeanName().getTextContent();
					System.out.println(" referenced-bean-name is  : " + referencedBeanName);
					assertEquals("referenced-bean-name", referencedBeanName);
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
		
	}
/*
 * check for the referenced-bean-for-class item 
 */
	public void testNonEmptyReferencedBeanForClass() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList referencedBean = edit.getFacesConfig().getReferencedBean();
				assertTrue(!referencedBean.isEmpty());
				for (int i = 0; i < referencedBean.size(); i++) {
					ReferencedBeanType item = (ReferencedBeanType) referencedBean
							.get(i);
					String  referencedBeanClass = item.getReferencedBeanClass().getTextContent();
					System.out.println(" referenced-bean-class is  : " + referencedBeanClass);
					assertEquals("referenced-bean-class", referencedBeanClass);
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
}