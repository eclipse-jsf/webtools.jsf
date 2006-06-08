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
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the for the existence of
 * renderer
 */
public class FacesConfigFactoryImplForReadRenderer extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadRenderer(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}

	/*
	 * Check to see if there at least one Renderer in place
	 */
	public void testSingleRenderer() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList renderKit = edit.getFacesConfig().getRenderKit();
				assertTrue(!renderKit.isEmpty());
				for (int i = 0; i < renderKit.size(); i++) {
					RenderKitType rendererType = (RenderKitType) renderKit
							.get(i);
					assertTrue(!rendererType.getRenderer().isEmpty());
					EList rend = rendererType.getRenderer();
					for (int k = 0; k < rend.size(); k++) {
						RendererType rendType = (RendererType) rend.get(k);
						String type = rendType.getRendererType().getTextContent();
						System.out.println("Renderer Type is ****> " + type);
						assertTrue(!type.equals(null));
						assertTrue(!rendType.getRendererClass().getTextContent().equals(null));
						assertTrue(!rendType.getComponentFamily().getTextContent()
								.equals(null));
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
	 * check for Description. It simply checks to see if there is at least
	 * one such item
	 * 
	 */

	public void testDescription() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList renderKit = edit.getFacesConfig().getRenderKit();
				assertTrue(!renderKit.isEmpty());
				for (int i = 0; i < renderKit.size(); i++) {
					RenderKitType rendererType = (RenderKitType) renderKit
							.get(i);
					// System.out.println("Renderer Component is = = ==>" +
					// rendererType.getComponentFamily() );
					assertTrue(!rendererType.getRenderer().isEmpty());
					EList rend = rendererType.getRenderer();
					for (int k = 0; k < rend.size(); k++) {
						RendererType rendType = (RendererType) rend.get(k);
						EList desc = rendType.getDescription();
						assertTrue(desc.size()!=0);
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
	 * Check for Display-name. It simply checks to see if there is at least
	 * one such item
	 * 
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
					RenderKitType rendererType = (RenderKitType) renderKit
							.get(i);
					assertTrue(!rendererType.getRenderer().isEmpty());
					EList rend = rendererType.getRenderer();
					for (int k = 0; k < rend.size(); k++) {
						RendererType rendType = (RendererType) rend.get(k);
						EList disp= rendType.getDisplayName();
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
	 * Test for Icons.. It simply checks to see if there is at least
	 * one such item
	 * 
	 */
	public void testIcon() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList renderKit = edit.getFacesConfig().getRenderKit();
				assertTrue(!renderKit.isEmpty());
				for (int i = 0; i < renderKit.size(); i++) {
					RenderKitType rendererType = (RenderKitType) renderKit
							.get(i);
					assertTrue(!rendererType.getRenderer().isEmpty());
					EList rend = rendererType.getRenderer();
					for (int k = 0; k < rend.size(); k++) {
						RendererType rendType = (RendererType) rend.get(k);
						EList icon= rendType.getIcon();
						assertTrue(icon.size()!=0);
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
	 * This one tests for the existence of three items
	 * They are the required items by all renderers
	 * They are : renderer-type, renderer-class and component-family.
	 * It thought it was better to put them together instead of
	 * writing single -separate methods for all of them. 
	 */
	public void testStringForRequiredEntries() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList renderKit = edit.getFacesConfig().getRenderKit();
				assertTrue(!renderKit.isEmpty());
				for (int i = 0; i < renderKit.size(); i++) {
					RenderKitType rendererType = (RenderKitType) renderKit
							.get(i);
					assertTrue(!rendererType.getRenderer().isEmpty());
					EList rend = rendererType.getRenderer();
					for (int k = 0; k < rend.size(); k++) {
						RendererType rendType = (RendererType) rend.get(k);
						//String desc = rendType.getRendererType();
						//System.out.println("Renderer Type is ****> " + desc);
						assertTrue(!rendType.getRendererType().getTextContent().equals(null));
						assertEquals("renderer-type" , rendType.getRendererType().getTextContent());
						assertTrue(!rendType.getRendererClass().getTextContent().equals(null));
						assertEquals("renderer-class",rendType.getRendererClass().getTextContent());
						assertTrue(!rendType.getComponentFamily().getTextContent().equals(null));
						assertEquals("component-family",rendType.getComponentFamily().getTextContent());
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
	 * check for attribute. It simply checks to see if there is at least
	 * one such item
	 * 
	 */
	public void testAttribute() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList renderKit = edit.getFacesConfig().getRenderKit();
				assertTrue(!renderKit.isEmpty());
				for (int i = 0; i < renderKit.size(); i++) {
					RenderKitType rendererType = (RenderKitType) renderKit
							.get(i);
					assertTrue(!rendererType.getRenderer().isEmpty());
					EList rend = rendererType.getRenderer();
					for (int k = 0; k < rend.size(); k++) {
						RendererType rendType = (RendererType) rend.get(k);
						EList disp= rendType.getAttribute();
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
	 * check for Renderer-Extension. It simply checks to see if there is at least
	 * one such item
	 * 
	 */
	
	public void testRendererExtension() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList renderKit = edit.getFacesConfig().getRenderKit();
				assertTrue(!renderKit.isEmpty());
				for (int i = 0; i < renderKit.size(); i++) {
					RenderKitType rendererType = (RenderKitType) renderKit
							.get(i);
					assertTrue(!rendererType.getRenderer().isEmpty());
					EList rend = rendererType.getRenderer();
					for (int k = 0; k < rend.size(); k++) {
						RendererType rendType = (RendererType) rend.get(k);
						EList ext= rendType.getRendererExtension();
						assertTrue(ext.size()!=0);
						System.out.println("The size of this object is ==>> " + ext.size());
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