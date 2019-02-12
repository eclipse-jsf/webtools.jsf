/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;

public class EntityImplTests extends AbstractBaseMetaDataTestCase {
	protected IMetaDataModelContext baseContext;
	Model model;
	Entity entity;

	public void setUp() throws Exception {
		super.setUp();
		IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(project); 
		ITaglibDomainMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(context);
		model = query.findTagLibraryModel(baseTestUri);
		assertNotNull(model);
		entity = query.findTagEntity(model, "loaded");
		assertNotNull(entity);
	}
	
	public void testGetChildEntities() {
		//childEntities
		assertNotNull(entity.getChildEntities());
		//should be 1 from eg2, plus 3
		assertEquals(4, entity.getChildEntities().size());
	}

	public void testGetTraits() {
		//traits
		assertNotNull(entity.getTraits());
		//should be 1 from eg2, plus 3
		assertEquals(4, entity.getTraits().size());		
	}

	public void testGetIncludeGroups() {
		//IncludeGroups
		assertNotNull(entity.getIncludeGroups());
		assertEquals(1, entity.getIncludeGroups().size());
	}

	public void testGetId() {
		assertNotNull(entity.getId());
		assertEquals("loaded", entity.getId() );		
	}

	public void testSetId() {
		String id = entity.getId();
		entity.setId("new");
		assertEquals("new",entity.getId());
		entity.setId(id);
	}

	public void testGetType() {
		//type
		assertNotNull(entity.getType());
		assertEquals(entity.getType(), TYPE_TAG);
	}

	public void testSetType() {
		String type = entity.getType();
		entity.setType("new");
		assertEquals("new",entity.getType());
		entity.setType(type);
	}

	public void testAccept() {
//		Query tests excercises this
	}

	public void testGetModel() {
		assertNotNull(entity.getModel());
		assertEquals(model, entity.getModel());
	}

}
