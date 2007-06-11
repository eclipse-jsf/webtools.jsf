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

import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TaglibDomainMetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;

public class TinyTestTests extends AbstractBaseMetaDataTestCase {
	protected ITaglibDomainMetaDataModelContext baseContext;
	Model model;
	Trait trait;
	
	public void setUp() throws Exception {
		super.setUp();
		String uri = "http://org.eclipse.jsf/tinytest";
		
		baseContext = new TaglibDomainMetaDataModelContextImpl(domain, project, uri);
		model = TaglibDomainMetaDataQueryHelper.getModel(baseContext);
		assertNotNull(model);
	}

	public void testGetValue() {
		Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, "A/copy1");
		assertNotNull(entity);
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "model-trait");
		assertNotNull(trait);
		assertNotNull(trait.getValue());
		assertTrue(trait.getValue() instanceof AnyType);
		assertEquals("ATrait", TraitValueHelper.getValueAsString(trait));
		
		entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, "B/copy1");
		assertNotNull(entity);
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "model-trait");
		assertNotNull(trait);
		assertNotNull(trait.getValue());
		assertTrue(trait.getValue() instanceof AnyType);
		assertEquals("ATrait", TraitValueHelper.getValueAsString(trait));
	}

}
