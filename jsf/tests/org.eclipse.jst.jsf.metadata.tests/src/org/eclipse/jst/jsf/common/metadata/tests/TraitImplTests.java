/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.tests;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TaglibDomainMetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;

public class TraitImplTests extends AbstractBaseMetaDataTestCase {
	protected ITaglibDomainMetaDataModelContext baseContext;
	Model model;
	Trait trait;
	
	public void setUp() throws Exception {
		super.setUp();
		
		baseContext = new TaglibDomainMetaDataModelContextImpl(domain, project, baseTestUri);
		model = TaglibDomainMetaDataQueryHelper.getModel(baseContext);
		assertNotNull(model);
		trait = TaglibDomainMetaDataQueryHelper.getTrait(baseContext, "loaded", "A");
		assertNotNull(trait);
	}

	public void testGetValue() {
		assertNotNull(trait.getValue());
		assertTrue(trait.getValue() instanceof EObject);
		assertEquals("a", TraitValueHelper.getValueAsString(trait));
	}

	public void testSetValue() {
//		fail("Not yet implemented");
	}

	public void testGetSourceModel() {
		assertNotNull(trait.getSourceModelProvider());
		assertTrue(trait.getSourceModelProvider().getSourceModel() instanceof Model);
	}

	public void testSetSourceModel() {
// Set during model load.   no need to test.
	}

	public void testGetId() {
		//id
		assertNotNull(trait.getId());
		assertEquals("A", trait.getId() );		
	}

	public void testSetId() {
		String id = trait.getId();
		trait.setId("new");
		assertEquals("new",trait.getId());
		trait.setId(id);
	}

	public void testAccept() {
//		Excercised by Query tests
	}

}
