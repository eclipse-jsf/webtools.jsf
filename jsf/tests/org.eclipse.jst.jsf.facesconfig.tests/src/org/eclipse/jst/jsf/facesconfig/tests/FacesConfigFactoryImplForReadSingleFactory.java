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
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
/*
 * This Junit class is used to test the factory which is one of 
 * many items inside the root elemnt faces-config in the configuration
 * information hierarchy of the faces-config.xml file 
 *
 */
public class FacesConfigFactoryImplForReadSingleFactory extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadSingleFactory(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}

	/*
	 * The following method is used to test for the at least one
	 * factory with in the faces-config.xml for reading
	 */
	public void testSingleFactory() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList factory = edit.getFacesConfig().getFactory();
				assertTrue(!factory.isEmpty());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	// Test for the Descirption
	public void testNonEmptyApplicationFactory() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList factory = edit.getFacesConfig().getFactory();
				assertTrue(!factory.isEmpty());
				for (int i = 0; i < factory.size(); i++) {
					FactoryType item = (FactoryType) factory
							.get(i);
					EList appFactory = item.getApplicationFactory();
					assertTrue(!appFactory.isEmpty());
					// sassertEquals(2,navCases.size());
					System.out.println(" size of description is : "+ appFactory.size());
					
				
					
					
					for(int z=0; z<appFactory.size(); z++){
						
						ApplicationFactoryType appType = (ApplicationFactoryType) appFactory.get(z);
						String value = appType.getTextContent();
						System.out.println("iNSIDE [factory], APPLICATION-VALUS IS : " + value );
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
 * check for the faces-context-factory element
 */
		public void testFacesContextFactory() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList factory= edit.getFacesConfig().getFactory();
				assertTrue(!factory.isEmpty());
				for (int i = 0; i < factory.size(); i++) {
					FactoryType item = (FactoryType) factory
							.get(i);

					EList facesContext = item.getFacesContextFactory();
					assertTrue(!facesContext.isEmpty()&& facesContext.size()==1);
					

					for(int z=0; z<facesContext.size(); z++){
						
						FacesContextFactoryType ctxType = (FacesContextFactoryType) facesContext.get(z);
						String value = ctxType.getTextContent();
						System.out.println("iNSIDE [factory], FACESCONTEXT-VALUS IS : " + value );
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
		 * check for the lifecycle-factotry with  in factory
		 */

		public void testLifectycleFactory() {
			FacesConfigArtifactEdit edit = null;
			try {
				edit = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project);
				if (edit.getFacesConfig() != null) {
					EList factory= edit.getFacesConfig().getFactory();
					assertTrue(!factory.isEmpty());
					for (int i = 0; i < factory.size(); i++) {
						FactoryType item = (FactoryType) factory
								.get(i);

						EList lifectycle = item.getLifecycleFactory();
						assertTrue(!lifectycle.isEmpty()&& lifectycle.size()==1);
						
						for(int z=0; z<lifectycle.size(); z++){
							
							LifecycleFactoryType ctxType = (LifecycleFactoryType) lifectycle.get(z);
							String value = ctxType.getTextContent();
							System.out.println("iNSIDE [factory], LIFECYCLE-VALUS IS : " + value );
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
 * check for the Rendert-kit-factory with in the factory
 */
		public void testRenderKitFactory() {
			FacesConfigArtifactEdit edit = null;
			try {
				edit = FacesConfigArtifactEdit
						.getFacesConfigArtifactEditForRead(project);
				if (edit.getFacesConfig() != null) {
					EList factory= edit.getFacesConfig().getFactory();
					assertTrue(!factory.isEmpty());
					for (int i = 0; i < factory.size(); i++) {
						FactoryType item = (FactoryType) factory
								.get(i);

						EList renderKit = item.getRenderKitFactory();
						assertTrue(!renderKit.isEmpty()&& renderKit.size()==1);
					}
				}
			} finally {
				if (edit != null) {
					edit.dispose();
				}
			}
		}
	
}