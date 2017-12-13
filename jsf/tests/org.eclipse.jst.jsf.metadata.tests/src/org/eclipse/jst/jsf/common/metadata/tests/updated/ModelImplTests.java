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
package org.eclipse.jst.jsf.common.metadata.tests.updated;

import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.tests.AbstractBaseMetaDataTestCase;

public class ModelImplTests extends AbstractBaseMetaDataTestCase {

	Model model;
	public void setUp() throws Exception {
		super.setUp();
		
		IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(project);
		ITaglibDomainMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(context);
		model = query.findTagLibraryModel(baseTestUri);
		assertNotNull(model);
	}
	
	public void testGetModel() {
		assertNotNull(model.getModel());
		assertEquals(model.getModel(), model);
	}

	public void testGetModelId() {
		assertEquals(baseTestUri, model.getId());
	}

	public void testGetSourceModelProvider() {
		//getSourceModelProvider
		assertNotNull(model.getSourceModelProvider());	
		assertTrue(model.getSourceModelProvider() instanceof IMetaDataSourceModelProvider);
	}

	public void testSetSourceModelProvider() {
//		fail("Not yet implemented");
	}

	public void testSetCurrentModelContext() {
//		fail("Not yet implemented");
	}

	public void testGetEntityGroups() {
		//EntityGroups
		assertNotNull(model.getEntityGroups());
		assertEquals(2, model.getEntityGroups().size());
	}

	public void testFindIncludeGroup() {
		assertNotNull(model.findIncludeGroup("eg1"));
	}

	public void testGetChildEntities() {
		//childEntities
		assertNotNull(model.getChildEntities());
		assertEquals(6, model.getChildEntities().size());
	}

	public void testGetTraits() {
		//traits
		assertNotNull(model.getTraits());
		assertEquals(2, model.getTraits().size());		
	}

	public void testGetIncludeGroups() {
		//IncludeGroups
		assertNotNull(model.getIncludeGroups());
		assertEquals(0, model.getIncludeGroups().size());
	}

	public void testGetId() {
		//id
		assertNotNull(model.getId());
		assertEquals(baseTestUri+" is not same as model.getId()", baseTestUri, model.getId() );		
	}

	public void testSetId() {
		String id = model.getId();
		model.setId("newid");
		assertEquals("newid",model.getId());
		model.setId(id);
	}

	public void testGetType() {
		//type
		assertNotNull(model.getType());
		assertEquals(model.getType(), TYPE_TAG_FILE);
	}

	public void testSetType() {
		String type = model.getType();
		model.setType("newid");
		assertEquals("newid",model.getType());
		model.setType(type);
	}

}
