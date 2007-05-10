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

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataQueryHelper;

public class EntityImplTests extends AbstractBaseMetaDataTestCase {
	protected IMetaDataModelContext baseContext;
	Model model;
	Entity entity;
	public void setUp() throws Exception {
		super.setUp();
		
		baseContext = new MetaDataModelContextImpl(project, domain, baseTestUri);
		model = MetaDataQueryHelper.getModel(baseContext);
		assertNotNull(model);
		entity = MetaDataQueryHelper.getEntity(baseContext, "loaded");
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
