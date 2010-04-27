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

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.tests.AbstractBaseMetaDataTestCase;

public class IncludeEntityGroupImplTests extends AbstractBaseMetaDataTestCase {
	IncludeEntityGroup group;
	
	public void setUp() throws Exception {
		super.setUp();
		IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(project);
		ITaglibDomainMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(context);
		Model model = query.findTagLibraryModel(baseTestUri);
		assertNotNull(model);
		Entity entity = query.findTagEntity(model, "loaded");
		assertNotNull(entity);
		group = (IncludeEntityGroup)entity.getIncludeGroups().get(0);
	}
	public void testGetId() {
		assertEquals("eg2", group.getId());
	}

	public void testSetId() {
		String id = group.getId();
		group.setId("new");
		assertEquals("new",group.getId());
		group.setId(id);
	}

	public void testGetModelUri() {
		assertNull(group.getModelUri());		
	}

	public void testSetModelUri() {
		String uri = null;
		group.setModelUri("new");
		assertEquals("new",group.getModelUri());
		group.setModelUri(uri);
	}

}
