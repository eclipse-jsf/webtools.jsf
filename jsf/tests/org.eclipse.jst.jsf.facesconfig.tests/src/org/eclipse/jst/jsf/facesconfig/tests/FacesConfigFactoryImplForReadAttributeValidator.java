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
import org.eclipse.jst.jsf.facesconfig.emf.AttributeExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test Attibute part in the Validator Element
 *
 */
public class FacesConfigFactoryImplForReadAttributeValidator extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadAttributeValidator(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 * checks to see if there exists an atribute in the first place
	 * 
	 */
	public void testSingleAttribute() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getValidator();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ValidatorType compType = (ValidatorType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						String name = attrType.getAttributeName().getTextContent();
						//System.out.println("Attriute name is ****> " + name);
						assertTrue(!name.equals(null));
						//assertEquals("validator-attribute-name", name);
						

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
				EList comp = edit.getFacesConfig().getValidator();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ValidatorType compType = (ValidatorType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						EList desc = attrType.getDescription();
						
						for(int z=0; z<desc.size(); z++){
							DescriptionType descT = (DescriptionType)desc.get(z);
							String name  = descT.getLang();
							System.out.println("The DDDDESCRIP IS ::: " + name);
							
							
						}
						
						
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
	 * Get the Display Name . It simply checks to see if there is at least
	 * one such item
	 * 
	 */

	public void testDisplayName() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getValidator();
				
				
				/*		
				ValidatorType newApplication = facesConfigFactory.createValidatorType();
				DisplayNameType  actionList=  facesConfigFactory.createDisplayNameType();
				actionList.setValue(dispName);
		
				 * 
				 */
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ValidatorType compType = (ValidatorType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						EList disp = attrType.getDisplayName();
						assertTrue(disp.size()!=0);
						 for(int z=0; z< disp.size(); z++){
							 DisplayNameType dispT = (DisplayNameType)disp.get(z);
							 String dispNameStr =dispT.getTextContent();
							 System.out.println("INSIDE VALIDATOR [DISPLAY NAME GET IS ]" + dispNameStr);
							 
						 }
						
					}
					
					/*
					FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
					FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
					ValidatorType newApplication = facesConfigFactory.createValidatorType();
					DisplayNameType  actionList=  facesConfigFactory.createDisplayNameType();
					dispName = actionList.getValue();
					System.out.println("The display name is [READING IN ATTRIBUTE VALIDATOR]" + dispName );
					*/

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
				EList comp = edit.getFacesConfig().getValidator();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ValidatorType compType = (ValidatorType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						EList icon = attrType.getIcon();
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
				EList comp = edit.getFacesConfig().getValidator();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ValidatorType compType = (ValidatorType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						String attributeClass = attrType.getAttributeClass().getTextContent();
						assertEquals("validator-attribute-class", attributeClass);
						assertEquals("validator-attribute-name", attrType.getAttributeName().getTextContent());
						// for testing purposes only please REMOVE this ASAP.
						System.out.println("validator-attribute class is  " + attributeClass);
						System.out.println("validator-attribute name is  " + attrType.getAttributeName().getTextContent());
						
						String attValue = attrType.getDefaultValue().getTextContent();
						System.out.println("ATTRIBUTE VALUE IS   ::" + attValue);
						
						
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
	 * Get the Suggested-value. It simply checks to see if there is at least
	 * one such item
	 * 
	 */	
	public void testSuggestedValue() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getValidator();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ValidatorType compType = (ValidatorType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						assertEquals("attribute-suggested-value", attrType.getSuggestedValue().getTextContent());
						String suggestedValue = attrType.getSuggestedValue().getTextContent();
						System.out.println("SUGGESTED VALUE IS   ::" + suggestedValue);
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
	 * Get the Attribute-Extension. It simply checks to see if there is at least
	 * one such item
	 * 
	 */
	public void testAttributeExtension() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getValidator();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ValidatorType compType = (ValidatorType) comp
							.get(i);
					assertTrue(!compType.getAttribute().isEmpty());
					
					EList attr = compType.getAttribute();
					for (int k = 0; k < attr.size(); k++) {
						AttributeType attrType = (AttributeType) attr.get(k);
						EList ext= attrType.getAttributeExtension();
						assertTrue(ext.size()!=0);
						System.out.println("The size of attribute-extension is >>?? " + ext.size() );
						
						for(int z=0; z<ext.size(); z++){
							AttributeExtensionType attExtType = (AttributeExtensionType) ext.get(z);
							
							System.out.println("EXTENSION is ext:::: " + attExtType.getAny().toString());
							
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
	
}