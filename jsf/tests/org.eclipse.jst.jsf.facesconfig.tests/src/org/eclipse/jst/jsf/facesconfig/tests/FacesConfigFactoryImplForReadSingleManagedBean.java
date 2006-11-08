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
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the managed-bean which is one of 
 * many items inside the root elemnt faces-config in the configuration
 * information hierarchy of the faces-config.xml file 
 *
 */
public class FacesConfigFactoryImplForReadSingleManagedBean extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadSingleManagedBean(String name) {
		super(name);
	}

	
	
	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 *Test to see if there is at least one managed-bean.
	 *This should be specified in the file for reading (faces-config)
	 */
	public void testSingleManagedBean() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList managedBean = edit.getFacesConfig().getManagedBean();
				assertTrue(!managedBean.isEmpty());
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
				EList managedBean = edit.getFacesConfig().getManagedBean();
				assertTrue(!managedBean.isEmpty());
				for (int i = 0; i < managedBean.size(); i++) {
					ManagedBeanType item = (ManagedBeanType) managedBean
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
 * Test for the display-name
 */
		public void testDisplayName() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList managedBean= edit.getFacesConfig().getManagedBean();
				assertTrue(!managedBean.isEmpty());
				for (int i = 0; i < managedBean.size(); i++) {
					ManagedBeanType item = (ManagedBeanType) managedBean
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
				EList managedBean = edit.getFacesConfig().getManagedBean();
				assertTrue(!managedBean.isEmpty());
				ManagedBeanType item = (ManagedBeanType) managedBean
						.get(0);
				EList icon = item.getIcon();
				assertTrue(!icon.isEmpty());
	//check on the size of the icons on outpu ******
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
	 * check to see if there is managed-bean-name and check if it
	 * is as expected
	 */
	public void testNonEmptyManagedBeanName() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList managedBean = edit.getFacesConfig().getManagedBean();
				assertTrue(!managedBean.isEmpty());
				for (int i = 0; i < managedBean.size(); i++) {
					ManagedBeanType item = (ManagedBeanType) managedBean
							.get(i);
					String name = item.getManagedBeanName().getTextContent();
					System.out.println("Managed-bean-class is  : " + name);
					assertEquals("managed-bean-name", name);
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	
	/*
	 * Check for a managed-bean-class
	 */
	public void testNonEmptyManagedBeanClass() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList managedBean = edit.getFacesConfig().getManagedBean();
				assertTrue(!managedBean.isEmpty());
				for (int i = 0; i < managedBean.size(); i++) {
					ManagedBeanType item = (ManagedBeanType) managedBean
							.get(i);
					String managedBeanClass = item.getManagedBeanClass().getTextContent();
					System.out.println("Managed-bean-class is  : " + managedBeanClass);
					assertEquals("managed-bean-class", managedBeanClass);
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
/*
 * check for the managed-bean-scope
 */
	public void testNonEmptyManagedBeanScope() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList managedBean = edit.getFacesConfig().getManagedBean();
				assertTrue(!managedBean.isEmpty());
				for (int i = 0; i < managedBean.size(); i++) {
					ManagedBeanType item = (ManagedBeanType) managedBean
							.get(i);
					String scope = item.getManagedBeanScope().getTextContent();
					System.out.println("Managed-bean-class is  : " + scope);
					assertEquals("managed-bean-scope",  scope);
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

}