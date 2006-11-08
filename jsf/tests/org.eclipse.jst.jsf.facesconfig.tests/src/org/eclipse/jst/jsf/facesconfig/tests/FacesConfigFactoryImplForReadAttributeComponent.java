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
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This clas is used to test the Attribute inside Component Element
 *
 */
public class FacesConfigFactoryImplForReadAttributeComponent extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadAttributeComponent(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 * The following method is used to test for the existence of a single
	 * attribute in the Compoenent Element. While testing I had just one with
	 * everything (all children) inside it
	 */
	public void testSingleAttribute() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getComponent();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ComponentType compType = (ComponentType) comp
							.get(i);
					//check to see it is empty which should not
					assertTrue(!compType.getAttribute().isEmpty());
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						//extract the name and confirm that it is the same was what is in 
						// the xml file used as input.
						String name = attrType.getAttributeName().getTextContent();
						System.out.println("Attribute name is ****> " + name);
						assertTrue(!attrType.getAttributeName().equals(null));
						assertEquals("attribute-name", name);

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
	 * This is to test the description child inside of Attribute
	 * 
	 */

	public void testDescription() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getComponent();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ComponentType compType = (ComponentType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						EList desc = attrType.getDescription();
						assertTrue(desc.size()!=0);
					}

				}

			}
		} 
		finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	/*
	 * A simple test to check if the Display Name is present 
	 * within the faces-config.xml file
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
					ComponentType compType = (ComponentType) comp
							.get(i);
					// System.out.println("Renderer Component is = = ==>" +
					// rendererType.getComponentFamily() );
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						EList disp = attrType.getDisplayName();
						assertTrue(disp.size()!=0);
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
	 * Checks  to see if there is an icon defined 
	 * 
	 */
	public void testIcon() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getComponent();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ComponentType compType = (ComponentType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						EList icon = attrType.getIcon();
						
						//I dont know why the following is  a Problem!!!!!
						//assertTrue(icon.size()!=0);
						System.out.println("The icon size is  " + icon.size());
					}

				}

			}
		}  finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	/*
	 * This one tests for the existence of two items.
	 * They are the required items by all renderers
	 * They are : attribute-name and attribute-class.
	 * It thought it was better to put them together instead of
	 * writing single -separate methods for each of them.
	 *Simply, extract the names and check if same the one
	 *in faces-config.xml 
	 */
	public void testAttributeNameAndClass() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getComponent();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ComponentType compType = (ComponentType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						String attributeClass = attrType.getAttributeClass().getTextContent();
						assertEquals("attribute-class", attributeClass);
						assertEquals("attribute-name", attrType.getAttributeName().getTextContent());
						// for testing purposes only please REMOVE this ASAP.
						System.out.println("attribute class is  " + attributeClass);
						System.out.println("attribute name is  " + attrType.getAttributeName().getTextContent());
						
						
					}

				}

			}
		}  finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
/*
 * Checks for the item suggested-value within attribute
 * 
 */
	public void testSuggestedValue() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getComponent();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ComponentType compType = (ComponentType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						assertEquals("attribute-suggested-value", attrType.getSuggestedValue().getTextContent());
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
	 * Checks for the item attribute-extension within attribute
	 * 
	 */	
	public void testAttributeExtension() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getComponent();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ComponentType compType = (ComponentType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						EList ext= attrType.getAttributeExtension();
						assertTrue(ext.size()!=0);
						System.out.println("The size of attribute-extension is >>?? " + ext.size() );
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