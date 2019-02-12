/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.section;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.ui.page.ComponentsPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.detail.FacesConfigDetailsPage;
import org.eclipse.jst.jsf.facesconfig.ui.test.FacesConfigEditorTest;

/**
 * The test case for the master sections in ComponentsPage.
 * 
 * @author sfshi
 * 
 */
public class ComponentsPageTest extends FacesConfigEditorTest {

	ComponentsPage componentsPage;

	FacesConfigMasterSection[] facesConfigMasterSections;

	FacesConfigType facesConfig;

	/**
	 * Open the faces config file, then switch to "Components" page.
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.test.FacesConfigEditorTest#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		editor.setActiveEditorPage(ComponentsPage.PAGE_ID);
		componentsPage = (ComponentsPage) editor.getActiveEditor();
		assertNotNull(componentsPage);
		facesConfigMasterSections = componentsPage
				.getFacesConfigMasterSections();
		assertEquals(facesConfigMasterSections.length, 4);

		facesConfig = editor.getFacesConfig();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.test.FacesConfigEditorTest#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testComponentMasterSection() {
		/** Expand the "Component" secton, then click "Add" button */
		ComponentMasterSection componentSection = (ComponentMasterSection) facesConfigMasterSections[0];
		componentSection.getSection().setExpanded(true);
		assertFalse(componentSection.getRemoveButton().isEnabled());
		componentSection.addButtonSelected(null);
		ISelection selection = componentSection.getSelection();
		assertTrue(selection != null && !selection.isEmpty());
		assertEquals(facesConfig.getComponent().size(), 1);
		
		assertTrue(selection instanceof StructuredSelection);
		FacesConfigDetailsPage detailPage = (FacesConfigDetailsPage)componentsPage
				.getPage(componentsPage
						.getPageKey(((StructuredSelection) selection)
								.getFirstElement()));
		assertNotNull(detailPage);

		assertTrue(componentSection.getRemoveButton().isEnabled());
		componentSection.removeButtonSelected(null);
		componentSection.refresh();
		selection = componentSection.getSelection();
		assertTrue(selection == null || selection.isEmpty());
		assertEquals(facesConfig.getComponent().size(), 0);
		assertFalse(componentSection.getRemoveButton().isEnabled());
	}

	public void testConverterMasterSection() {

		/** Expand the "Converter" secton, then click "Add" button */
		ConverterMasterSection converterSection = (ConverterMasterSection) facesConfigMasterSections[1];
		converterSection.getSection().setExpanded(true);
		assertFalse(converterSection.getRemoveButton().isEnabled());
		converterSection.addButtonSelected(null);
		ISelection selection = converterSection.getSelection();
		assertTrue(selection != null && !selection.isEmpty());
		assertEquals(facesConfig.getConverter().size(), 1);
		
		assertTrue(selection instanceof StructuredSelection);
		FacesConfigDetailsPage detailPage = (FacesConfigDetailsPage)componentsPage
				.getPage(componentsPage
						.getPageKey(((StructuredSelection) selection)
								.getFirstElement()));
		assertNotNull(detailPage);
		
		assertTrue(converterSection.getRemoveButton().isEnabled());
		converterSection.removeButtonSelected(null);
		converterSection.refresh();
		selection = converterSection.getSelection();
		assertTrue(selection == null || selection.isEmpty());
		assertEquals(facesConfig.getConverter().size(), 0);
		assertFalse(converterSection.getRemoveButton().isEnabled());
	}

	public void testRenderKitMasterSection() {

		/** Expand the "RenderKit" secton, then click "Add" button */
		RenderkitMasterSection renderkitSection = (RenderkitMasterSection) facesConfigMasterSections[2];
		renderkitSection.getSection().setExpanded(true);
		assertFalse(renderkitSection.getRemoveButton().isEnabled());
		renderkitSection.addButtonSelected(null);
		ISelection selection = renderkitSection.getSelection();
		assertTrue(selection != null && !selection.isEmpty());
		assertEquals(facesConfig.getRenderKit().size(), 1);
		
		assertTrue(selection instanceof StructuredSelection);
		FacesConfigDetailsPage detailPage = (FacesConfigDetailsPage)componentsPage
				.getPage(componentsPage
						.getPageKey(((StructuredSelection) selection)
								.getFirstElement()));
		assertNotNull(detailPage);

		assertTrue(renderkitSection.getRemoveButton().isEnabled());
		renderkitSection.removeButtonSelected(null);
		renderkitSection.refresh();
		selection = renderkitSection.getSelection();
		assertTrue(selection == null || selection.isEmpty());
		assertEquals(facesConfig.getRenderKit().size(), 0);
		assertFalse(renderkitSection.getRemoveButton().isEnabled());
	}

	public void testValidatorMasterSection() {

		/** Expand the "Validator" secton, then click "Add" button */
		ValidatorMasterSection section = (ValidatorMasterSection) facesConfigMasterSections[3];
		section.getSection().setExpanded(true);
		assertFalse(section.getRemoveButton().isEnabled());
		section.addButtonSelected(null);
		ISelection selection = section.getSelection();
		assertTrue(selection != null && !selection.isEmpty());
		assertEquals(facesConfig.getValidator().size(), 1);
		
		assertTrue(selection instanceof StructuredSelection);
		FacesConfigDetailsPage detailPage = (FacesConfigDetailsPage)componentsPage
				.getPage(componentsPage
						.getPageKey(((StructuredSelection) selection)
								.getFirstElement()));
		assertNotNull(detailPage);

		assertTrue(section.getRemoveButton().isEnabled());
		section.removeButtonSelected(null);
		section.refresh();
		selection = section.getSelection();
		assertTrue(selection == null || selection.isEmpty());
		assertEquals(facesConfig.getValidator().size(), 0);
		assertFalse(section.getRemoveButton().isEnabled());
	}
}
