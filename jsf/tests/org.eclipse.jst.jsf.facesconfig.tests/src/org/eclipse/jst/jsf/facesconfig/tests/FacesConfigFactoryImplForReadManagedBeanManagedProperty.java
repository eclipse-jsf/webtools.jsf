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
import org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the Managed-Property
 * of the Managed-Bean Element
 *
 */
public class FacesConfigFactoryImplForReadManagedBeanManagedProperty extends
		TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadManagedBeanManagedProperty(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

/*
 * Check to see there is at least one property that exists within attribute
 * 
 */
	public void testSingeleManagedProperty() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList managedBean = edit.getFacesConfig().getManagedBean();
				assertTrue(!managedBean.isEmpty());
				for (int i = 0; i < managedBean.size(); i++) {
					ManagedBeanType compType = (ManagedBeanType) managedBean.get(i);

					assertTrue(!compType.getManagedProperty().isEmpty());
					EList attr = compType.getManagedProperty();
					for (int k = 0; k < attr.size(); k++) {
						ManagedPropertyType attrType = (ManagedPropertyType) attr.get(k);
						String name = attrType.getPropertyName().getTextContent();
						System.out.println("Attriute name is ****> " + name);
						assertTrue(attrType.getPropertyName().getTextContent().length()!=0);
						assertEquals("property-name", name);

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
	 * Get the Description. It simply checks to see if there is at least
	 * one such item
	 * 
	 */
	
	public void testManagedBeanPropertyDescription() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList managedBean = edit.getFacesConfig().getManagedBean();
				assertTrue(!managedBean.isEmpty());
				for (int i = 0; i < managedBean.size(); i++) {
					ManagedBeanType compType = (ManagedBeanType) managedBean.get(i);
			
					assertTrue(!compType.getManagedProperty().isEmpty());
					EList attr = compType.getManagedProperty();
					for (int k = 0; k < attr.size(); k++) {
						ManagedPropertyType attrType = (ManagedPropertyType) attr.get(k);
						EList desc = attrType.getDescription();
						assertTrue(!desc.isEmpty()&& desc.size()>0);
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
	 * Get the Display-name. It simply checks to see if there is at least
	 * one such item
	 * 
	 */
	
	public void testManagedBeanPropertyDisplayName() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList managedBean = edit.getFacesConfig().getManagedBean();
				assertTrue(!managedBean.isEmpty());
				for (int i = 0; i < managedBean.size(); i++) {
					ManagedBeanType compType = (ManagedBeanType) managedBean.get(i);
					// System.out.println("Renderer Component is = = ==>" +
					// rendererType.getComponentFamily() );
					assertTrue(!compType.getManagedProperty().isEmpty());
					EList attr = compType.getManagedProperty();
					for (int k = 0; k < attr.size(); k++) {
						ManagedPropertyType attrType = (ManagedPropertyType) attr.get(k);
						EList displayName = attrType.getDisplayName();
						assertTrue(!displayName.isEmpty()&& displayName.size()>0);
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
	 * Get the Icon. It simply checks to see if there is at least
	 * one such item
	 * 
	 */
	public void testManagedBeanPropertyIcon() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList managedBean = edit.getFacesConfig().getManagedBean();
				assertTrue(!managedBean.isEmpty());
				for (int i = 0; i < managedBean.size(); i++) {
					ManagedBeanType compType = (ManagedBeanType) managedBean.get(i);
					// System.out.println("Renderer Component is = = ==>" +
					// rendererType.getComponentFamily() );
					assertTrue(!compType.getManagedProperty().isEmpty());
					EList attr = compType.getManagedProperty();
					for (int k = 0; k < attr.size(); k++) {
						ManagedPropertyType attrType = (ManagedPropertyType) attr.get(k);
						EList icon = attrType.getIcon();
						assertTrue(!icon.isEmpty()&& icon.size()>0);
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
	 * Get both the Property-name and Property-class. It simply checks to see if
	 *  there is at least one each items and check if the names are identical
	 *  as given in the input xml file.
	 * 
	 */
	public void testManagedPropertyNameAndClass() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList managedBean = edit.getFacesConfig().getManagedBean();
				assertTrue(!managedBean.isEmpty());
				for (int i = 0; i < managedBean.size(); i++) {
					ManagedBeanType compType = (ManagedBeanType) managedBean.get(i);

					assertTrue(!compType.getManagedProperty().isEmpty());
					EList attr = compType.getManagedProperty();
					for (int k = 0; k < attr.size(); k++) {
						ManagedPropertyType attrType = (ManagedPropertyType) attr.get(k);
						String name = attrType.getPropertyName().getTextContent();
						System.out.println("Attriute name is ****> " + name);
						assertTrue(attrType.getPropertyName().getTextContent().length()!=0);
						assertEquals("property-name", name);
						
						String propertyClass = attrType.getPropertyClass().getTextContent();
						assertTrue(attrType.getPropertyClass().getTextContent().length()!=0 && propertyClass.equals("property-class"));

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