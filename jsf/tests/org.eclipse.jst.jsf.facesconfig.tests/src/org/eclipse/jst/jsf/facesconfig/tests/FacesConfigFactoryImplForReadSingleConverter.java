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
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the Converter which is one of 
 * many items inside the root elemnt faces-config in the configuration
 * information hierarchy of the faces-config.xml file 
 *
 */
public class FacesConfigFactoryImplForReadSingleConverter extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadSingleConverter(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 * Check to see if this element exists. There sould be at least one
	 * of such kind listed in the file.
	 */
	public void testSingleConverter() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList converter = edit.getFacesConfig().getConverter();
				assertTrue(!converter.isEmpty());
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
				EList converter = edit.getFacesConfig().getConverter();
				assertTrue(!converter.isEmpty());
				for (int i = 0; i < converter.size(); i++) {
					ConverterType item = (ConverterType) converter
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
 * Check for the display-name
 */
	
	public void testDisplayName() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList converter = edit.getFacesConfig().getConverter();
				assertTrue(!converter.isEmpty());
				for (int i = 0; i < converter.size(); i++) {
					ConverterType item = (ConverterType) converter
							.get(i);

					EList disp = item.getDisplayName();
					assertTrue(!disp.isEmpty()&& disp.size()==1);
					
					
					 for(int z=0; z< disp.size(); z++){
						 DisplayNameType dispT = (DisplayNameType)disp.get(z);
						 String dispNameStr =dispT.getTextContent();
						 System.out.println("INSIDE CONVERTER [DISPLAY NAME  IS ]" + dispNameStr);
						 
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
	 * get the icon of a converter
	 * 
	 */
	public void testNonEmptyIcon() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList converter = edit.getFacesConfig().getConverter();
				assertTrue(!converter.isEmpty());
				ConverterType item = (ConverterType) converter
						.get(0);

				EList icon = item.getIcon();
				assertTrue(!icon.isEmpty());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	/*
	 * check for the class name in converter
	 */
	public void testNonEmptyConverterForClass() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList converter = edit.getFacesConfig().getConverter();
				assertTrue(!converter.isEmpty());
				for (int i = 0; i < converter.size(); i++) {
					ConverterType item = (ConverterType) converter
							.get(i);
					String converterforClass = item.getConverterForClass().getTextContent();
					
					System.out.println("Converter-for-class is  : " + converterforClass);
					assertEquals("converter-for-class", converterforClass);
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

/*
 * Check for the converter-class item
 */
	public void testNonEmptyConverterClass() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList converter = edit.getFacesConfig().getConverter();
				assertTrue(!converter.isEmpty());
				for (int i = 0; i < converter.size(); i++) {
					ConverterType item = (ConverterType) converter
							.get(i);
					String converterClass = item.getConverterClass().getTextContent();
					System.out.println("Converter-class is  : " + converterClass);
					assertEquals("converter-class", converterClass);
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

/*
 * Checks for the existence of attribute with in Converter
 */
	public void testAttribute() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList comp = edit.getFacesConfig().getConverter();
				assertTrue(!comp.isEmpty());
				for (int i = 0; i < comp.size(); i++) {
					ConverterType navRule = (ConverterType)comp.get(i);
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
 * checks for the property 
 */
	public void testProperty() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList converter = edit.getFacesConfig().getConverter();
				assertTrue(!converter.isEmpty());
				for (int i = 0; i < converter.size(); i++) {
					ConverterType item = (ConverterType)converter.get(i);
					EList property = item.getProperty();
					assertTrue(!property.isEmpty());
					assertEquals(1, property.size());
					
					for(int z=0; z<property.size(); z++){
						PropertyType pType = (PropertyType) property.get(z);
						String str = pType.getPropertyName().getTextContent();
						System.out.println("PROPERTY NAME IN CONVERTER IS  ::" + str);
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