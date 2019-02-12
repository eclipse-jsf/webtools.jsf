/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.common.metadata.tests;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup;
import org.eclipse.jst.jsf.common.metadata.internal.TaglibDomainMetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;

public class IncludeEntityGroupImplTests extends AbstractBaseMetaDataTestCase {
	protected ITaglibDomainMetaDataModelContext baseContext;
	IncludeEntityGroup group;
	
	public void setUp() throws Exception {
		super.setUp();
		
		baseContext = new TaglibDomainMetaDataModelContextImpl(domain, project, baseTestUri);
		Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, "loaded");
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
