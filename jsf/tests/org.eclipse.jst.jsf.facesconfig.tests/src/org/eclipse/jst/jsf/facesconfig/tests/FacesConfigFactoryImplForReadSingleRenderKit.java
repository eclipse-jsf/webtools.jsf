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
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the render which is one of 
 * many items inside the root elemnt faces-config in the configuration
 * information hierarchy of the faces-config.xml file 
 *
 */
public class FacesConfigFactoryImplForReadSingleRenderKit extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadSingleRenderKit(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 *Test to see if there is at least one render-kit.
	 *This should be specified in the file for reading (faces-config)
	 */
	public void testSingleRenderKit() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList renderKit = edit.getFacesConfig().getRenderKit();
				assertTrue(!renderKit.isEmpty());
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
				EList renderKit = edit.getFacesConfig().getRenderKit();
				assertTrue(!renderKit.isEmpty());
				for (int i = 0; i < renderKit.size(); i++) {
					RenderKitType item = (RenderKitType) renderKit.get(i);

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
 * check for the display-name
 */
	public void testDisplayName() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList renderKit = edit.getFacesConfig().getRenderKit();
				assertTrue(!renderKit.isEmpty());
				for (int i = 0; i < renderKit.size(); i++) {
					RenderKitType item = (RenderKitType) renderKit.get(i);
					EList disp = item.getDisplayName();
					assertTrue(!disp.isEmpty() && disp.size() == 1);
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
				EList renderKit = edit.getFacesConfig().getRenderKit();
				assertTrue(!renderKit.isEmpty());
				RenderKitType item = (RenderKitType) renderKit.get(0);

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
	 * chech for hte render-kit-id  element
	 */

	public void testNonEmptyRenderKitId() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList renderKits = edit.getFacesConfig()
						.getRenderKit();
				assertTrue(!renderKits.isEmpty());
				for (int i = 0; i < renderKits.size(); i++) {
					RenderKitType item = (RenderKitType) renderKits.get(i);
					String renderKitId = item.getRenderKitId().getTextContent();
					System.out.println(" render-kit-Id is  : " + renderKitId);
					assertEquals("render-kit-Id", renderKitId);
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	/*
	 * Check for the existence of the Render-kit-class
	 */

	public void testNonEmptyRenderKitClass() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList renderKits = edit.getFacesConfig()
						.getRenderKit();
				assertTrue(!renderKits.isEmpty());
				for (int i = 0; i < renderKits.size(); i++) {
					RenderKitType item = (RenderKitType) renderKits.get(i);
					String renderKitClass = item.getRenderKitClass().getTextContent();
					System.out.println(" render-kit-class is  : "
							+ renderKitClass);
					assertEquals("render-kit-class", renderKitClass);
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	/*
	 * Checks to see if there is at least one renderer
	 * 
	 */

	public void testRenderer() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList renderKit = edit.getFacesConfig().getRenderKit();
				assertTrue(!renderKit.isEmpty());
				for (int i = 0; i < renderKit.size(); i++) {
					RenderKitType item = (RenderKitType) renderKit.get(i);
				
					EList renders = item.getRenderer();

					assertTrue(!renders.isEmpty());
					// sassertEquals(2,navCases.size());
					System.out.println(" size of renderer (in render-kit objs) is : "
							+ renders.size());
					
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}

	}

}