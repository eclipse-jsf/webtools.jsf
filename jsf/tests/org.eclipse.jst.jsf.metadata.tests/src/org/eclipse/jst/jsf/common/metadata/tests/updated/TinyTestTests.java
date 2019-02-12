/*******************************************************************************
 * Copyright (c) 2001, 2011 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.tests.updated;

import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.tests.AbstractBaseMetaDataTestCase;

public class TinyTestTests extends AbstractBaseMetaDataTestCase {
	Model model;
	Trait trait;
	private ITaglibDomainMetaDataQuery _query;
	
	public void setUp() throws Exception {
		super.setUp();
		String uri = "http://org.eclipse.jsf/tinytest";
		
		IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(project);
		_query = MetaDataQueryFactory.getInstance().createQuery(context);
		model = _query.findTagLibraryModel(uri);
		assertNotNull(model);
	}

	public void testGetValue() {
		Entity entity = _query.findTagEntity(model, "A/copy1");
		assertNotNull(entity);
		trait = _query.findTrait(entity, "model-trait");
		assertNotNull(trait);
		assertNotNull(trait.getValue());
		assertTrue(trait.getValue() instanceof AnyType);
		assertEquals("ATrait", TraitValueHelper.getValueAsString(trait));
		
		entity = _query.getQueryHelper().getEntity(model, "B/copy1");
		assertNotNull(entity);
		trait = _query.findTrait(entity, "model-trait");
		assertNotNull(trait);
		assertNotNull(trait.getValue());
		assertTrue(trait.getValue() instanceof AnyType);
		assertEquals("ATrait", TraitValueHelper.getValueAsString(trait));
	}

}
