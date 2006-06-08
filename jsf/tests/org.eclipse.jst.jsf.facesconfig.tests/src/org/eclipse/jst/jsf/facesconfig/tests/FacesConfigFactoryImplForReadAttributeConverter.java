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
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test for the existence of all
 * items with in attribute of the Converter Element
 *
 */
public class FacesConfigFactoryImplForReadAttributeConverter extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadAttributeConverter(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}

	/*
	 * The following method is used to test for the existence of an attribute
	 * with in  Converter
	 */
	public void testSingleAttribute() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getConverter();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ConverterType compType = (ConverterType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						String name = attrType.getAttributeName().getTextContent();
						//System.out.println("Attribute name is ****> " + name);
						assertTrue(!attrType.getAttributeName().getTextContent().equals(null));
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
	 * Get the Description. It simply checks to see if there is at least
	 * one such item
	 * 
	 */

	public void testDescription() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getConverter();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ConverterType compType = (ConverterType) comp
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
	 * Get the Display-name. It simply checks to see if there is at least
	 * one such item
	 * 
	 */

	public void testDisplayName() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getConverter();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ConverterType compType = (ConverterType) comp
							.get(i);
					// System.out.println("Renderer Component is = = ==>" +
					// rendererType.getComponentFamily() );
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						EList disp = attrType.getDisplayName();
						assertTrue(disp.size()!=0);
						
						 for(int z=0; z< disp.size(); z++){
							 DisplayNameType dispT = (DisplayNameType)disp.get(z);
							 String dispNameStr =dispT.getTextContent();
							 System.out.println("INSIDE CONVERTER [DISPLAY NAME GET IS ]" + dispNameStr);
							 
						 }
						
						
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

	
	public void testIcon() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getConverter();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ConverterType compType = (ConverterType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						EList icon = attrType.getIcon();
				
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
	 * This one tests for the existence of three items
	 * They are the required items by all renderers
	 * They are : attribute-name and attribute-class .
	 * It just asserts if the names are the same as
	 * refered to in the input xml file
	 */
	public void testAttributeNameAndClass() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getConverter();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ConverterType compType = (ConverterType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						String attributeClass = attrType.getAttributeClass().getTextContent();
						assertEquals("attribute-class", attributeClass);
						assertEquals("attribute-name", attrType.getAttributeName().getTextContent());
						// for testing purposes only please REMOVE this ASAP.
						//System.out.println("attribute class is  " + attributeClass);
						//System.out.println("attribute name is  " + attrType.getAttributeName().getTextContent());
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
 * Check on for the suggested-value in the attribute
 * 
 */
	
	public void testSuggestedValue() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getConverter();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ConverterType compType = (ConverterType) comp
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
	 * Checks for attribute-extension
	 */
	
	public void testAttributeExtension() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getConverter();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ConverterType compType = (ConverterType) comp
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