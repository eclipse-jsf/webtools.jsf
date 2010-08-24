/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.tests.updated;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataModelManagerContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.test.util.mock.IWorkspaceContext;
import org.eclipse.jst.jsf.test.util.mock.MockWorkspaceContext;
import org.junit.Test;

public class MetaDataQueryFactoryTests extends TestCase {
	
	private IProject _project;
	
	public void setUp() throws Exception {
		IWorkspaceContext context = new MockWorkspaceContext();
		_project = context.createProject("MetaDataQueryFactoryTests"+"_"+getName());
	}

	@Test
	public void testGetInstance() {
		final MetaDataQueryFactory factory = MetaDataQueryFactory.getInstance();
		assertNotNull(factory);
		
		final MetaDataQueryFactory factory2 = MetaDataQueryFactory.getInstance();
		assertNotNull(factory2);
		assertSame(factory, factory2);		
	}

//	@Test
//	public void testCreateQueryWithNullProject() {
//		//tested null project query...  may not be valid anymore
//		IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainContext(null);
//		final IMetaDataQuery q = MetaDataQueryFactory.getInstance().createQuery(context);
//		assertNotNull(q);
//		assertTrue(q instanceof ITaglibMetaDataQuery);
//		
//		//check get new instance on each call
//		final ITaglibMetaDataQuery q2 = MetaDataQueryFactory.getInstance().createQuery(context);
//		assertNotNull(q2);
//		assertNotSame(q, q2);
//
//	}

	@Test
	public void testCreateQuery() {
		IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(_project);
		final IMetaDataQuery q = MetaDataQueryFactory.getInstance().createQuery(context);
		assertNotNull(q);
		assertTrue(q instanceof ITaglibDomainMetaDataQuery);
		
		//check get new instance on each call
		final ITaglibDomainMetaDataQuery q2 = MetaDataQueryFactory.getInstance().createQuery(context);
		assertNotNull(q2);
		assertNotSame(q, q2);
		
		IMetaDataModelManagerContext fakeDomainContext = new IMetaDataModelManagerContext() {
			
			@SuppressWarnings("rawtypes")
			public Object getAdapter(final Class adapter) {
				if (adapter == IProject.class)
					return _project;
				return null;
			}
			
			public String getDomainId() {
				return FakeDomainQueryFactory.FAKE_MD_DOMAIN;
			}
			
			public IProject getProject() {				
				return _project;
			}
		};
		final IMetaDataQuery q3 = MetaDataQueryFactory.getInstance().createQuery(fakeDomainContext);
		assertNotNull(q3);
		assertNotSame(q, q3);
		
	}
	
}
