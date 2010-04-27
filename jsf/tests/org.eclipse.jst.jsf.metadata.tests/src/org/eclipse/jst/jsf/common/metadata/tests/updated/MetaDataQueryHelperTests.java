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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.EmptyResultSet;
import org.eclipse.jst.jsf.common.metadata.query.IEntityQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.ITraitQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataException;
import org.eclipse.jst.jsf.common.metadata.query.internal.AbstractMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.HierarchicalSearchControl;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataModelManagerContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataQueryHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.SearchControl;
import org.eclipse.jst.jsf.common.metadata.query.internal.SimpleEntityQueryVisitorImpl;
import org.eclipse.jst.jsf.common.metadata.query.internal.SimpleTraitQueryVisitorImpl;
import org.eclipse.jst.jsf.metadata.tests.MetadataTestsPlugin;
import org.junit.Before;
import org.junit.Test;

public class MetaDataQueryHelperTests extends AbstractBaseMetaDataTestCase {

	private IMetaDataQuery _query;
	private MetaDataQueryHelper _helper;
	@Before
	public void setUp() throws Exception {
		super.setUp();		
		IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(project);
		_query = MetaDataQueryFactory.getInstance().createQuery(context);
		assertNotNull(_query);
		_helper = (MetaDataQueryHelper)_query.getQueryHelper();		
		assertNotNull(_helper);
		
//	    baseContext = new TaglibDomainMetaDataModelContextImpl(domain, project, baseTestUri);
//	    nullProjectContext = new TaglibDomainMetaDataModelContextImpl(domain, null, baseTestUri);
//	    negativeContextBadUri = new TaglibDomainMetaDataModelContextImpl(domain, project, "blah");
//	    negativeContextBadDomain = new TaglibDomainMetaDataModelContextImpl(badDomain, project, baseTestUri);
	}

	/**
	 * Test getModel method but also performs basic read unit tests on Model
	 */
	public void testGetModel() {
	//base
		//positive test
		Model model = _helper.getModel(baseTestUri);
		assertNotNull(baseTestUri+" model should not be null.",model);
		
	//null proj
		IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(null);
		IMetaDataQuery nullProjectQuery = _query = MetaDataQueryFactory.getInstance().createQuery(context);
		model = nullProjectQuery.getQueryHelper().getModel(baseTestUri);
		assertNotNull(baseTestUri+" model should not be null.",model);
		//id
		assertNotNull(model.getId());
		assertEquals(baseTestUri+" is not same as model.getId()",baseTestUri, model.getId() );
		
		
		
	//negative tests
		context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(project);
		IMetaDataQuery badUriQuery = MetaDataQueryFactory.getInstance().createQuery(context);
		model = badUriQuery.getQueryHelper().getModel("blah");
		assertNull(model);
		
		IMetaDataModelManagerContext badContext = new IMetaDataModelManagerContext() {
			
			@SuppressWarnings("rawtypes")
			public Object getAdapter(Class adapter) {
				if (adapter == IProject.class)
					return project;
				return null;
			}
			
			public String getDomainId() {
				return "badDomainKey";
			}
			
			public IProject getProject() {				
				return project;
			}
		};
		
		MetadataTestsPlugin.getDefault().getLog().log(new Status(IStatus.INFO, MetadataTestsPlugin.ID_BUNDLE, "***** UnsupportedOperationException about to occur because of Test ! *****")); //$NON-NLS-1$
		IMetaDataQuery badDomainQuery = MetaDataQueryFactory.getInstance().createQuery(badContext);
		assertNull(badDomainQuery); 
	}

	public void testGetEntityIMetaDataModelContextString() {
		Model model = _helper.getModel(baseTestUri);
		//positive
		Entity entity = _helper.getEntity(model, "loaded");
		assertNotNull(entity);
		
		entity = _helper.getEntity(model, "loaded/att3");
		assertNotNull(entity);
		
		//negative
		entity = _helper.getEntity(model, "doesnotexist");
		assertNull(entity);
	}

	/**
	 * Return multiple entities
	 * Partially tests SimpleEntityQueryVisitorImpl searchControl
	 */
	public void testGetEntitiesIMetaDataModelContextStringIEntityQueryVisitor() {
		Model model = _helper.getModel(baseTestUri);
		IEntityQueryVisitor visitor = new SimpleEntityQueryVisitorImpl(new HierarchicalSearchControl(1, HierarchicalSearchControl.SCOPE_ALL_LEVELS));
		IResultSet rs = _helper.getEntities(model, "loaded", visitor);
		assertNotNull(rs);
		Entity entity = null;
		try {
			assertFalse(rs instanceof EmptyResultSet);
			assertEquals(1, rs.getResults().size());			
			entity = (Entity)rs.getResults().get(0);
			assertNotNull(entity);
			assertEquals(entity.getId(), "loaded");
			rs.close();
		} catch (MetaDataException e) {
			//MetaDataException not currently being thrown
			fail(e.getMessage());
		}
		
		//test returning multiple (2)
		visitor = new SimpleEntityQueryVisitorImpl(new HierarchicalSearchControl(SearchControl.COUNT_LIMIT_NONE, HierarchicalSearchControl.SCOPE_ALL_LEVELS));
		rs = _helper.getEntities(model, "loaded", visitor);
		assertNotNull(rs);
		try {
			assertFalse(rs instanceof EmptyResultSet);
			assertEquals(2, rs.getResults().size());
			entity = (Entity)rs.getResults().get(0);
			assertNotNull(entity);
			assertEquals(entity.getId(), "loaded");
			Entity secondentity = (Entity)rs.getResults().get(1);
			assertNotNull(secondentity);
			assertEquals("loaded", secondentity.getId());
			assertFalse(secondentity == entity);
			rs.close();
		} catch (MetaDataException e) {
			//MetaDataException not currently being thrown
			fail(e.getMessage());
		}
	}

	public void testGetTraitEntityString() {
		Model model = _helper.getModel(baseTestUri);
		Entity entity = _helper.getEntity(model, "loaded/att3");

		//positive
		Trait trait = _helper.getTrait(entity, "A3");
		assertNotNull(trait);
		
		//negative
		trait = _helper.getTrait(entity, "Z3");
		assertNull(trait);
	}

	/**
	 * Return multiple traits
	 * Also tests SimpleEntityQueryVisitorImpl and IResultSet
	 */
	public void testGetTraits() {
		//TEST with 1 count
		ITraitQueryVisitor visitor = new SimpleTraitQueryVisitorImpl(new SearchControl(1));
		Model model = _helper.getModel(baseTestUri);
		IResultSet rs = _helper.getTraits(model, "model-trait", visitor);
		assertNotNull(rs);
		Trait trait = null;
		try {
			assertFalse(rs instanceof EmptyResultSet);
			assertEquals(1, rs.getResults().size());
			trait = (Trait)rs.getResults().get(0);
			assertNotNull(trait);
			assertEquals("model-trait", trait.getId());
			rs.close();
		} catch (MetaDataException e) {
			//MetaDataException not currently being thrown
			fail(e.getMessage());
		}
		
		//test with COUNT_LIMIT_NONE
		visitor = new SimpleTraitQueryVisitorImpl(new SearchControl(SearchControl.COUNT_LIMIT_NONE));
		rs = _helper.getTraits(model, "model-trait", visitor);
		assertNotNull(rs);
		try {
			assertFalse(rs instanceof EmptyResultSet);
			assertEquals(2, rs.getResults().size());
			trait = (Trait)rs.getResults().get(0);
			assertNotNull(trait);
			assertEquals("model-trait", trait.getId());		
			Trait secondTrait = (Trait)rs.getResults().get(1);
			assertNotNull(secondTrait);
			assertEquals("model-trait", secondTrait.getId());	
			assertFalse(trait == secondTrait);
			rs.close();
		} catch (MetaDataException e) {
			//MetaDataException not currently being thrown
			fail(e.getMessage());
		}
		
	}

	public void testGetEntityEntityString() {
		Model model = _helper.getModel(baseTestUri);
		Entity entity = _helper.getEntity(model, "loaded");
		//positive
		entity = _helper.getEntity(entity, "att3");
		assertNotNull(entity);
		
		//negative
		entity = _helper.getEntity(entity, "zzz");
		assertNull(entity);
	}

	public void testGetEntitiesEntityStringIEntityQueryVisitor() {
		
		//negative test
		
		IResultSet rs = _helper.getEntities("badURI", "foo", new SimpleEntityQueryVisitorImpl());
		assertNotNull(rs);
		try {
			assertEquals(0, rs.getResults().size());
		} catch (MetaDataException e) {			
			fail(e.getMessage());
		}
	}

	public void testGetTraitIMetaDataModelContextStringString() {
		//positive
		Trait trait = _helper.getTrait(baseTestUri, "loaded/att3", "A3");
		assertNotNull(trait);
		
		//negative
		trait = _helper.getTrait(baseTestUri, "loaded/att3", "zzz");
		assertNull(trait);
		
		trait = _helper.getTrait(baseTestUri, "doesnotexist", "A3");
		assertNull(trait);
		
		trait = _helper.getTrait("badDomain", "doesnotexist", "A3");
		assertNull(trait);
	}
	
	public void testResultSets() {
		IResultSet rs = _helper.getEntities(baseTestUri, "loaded", new SimpleEntityQueryVisitorImpl());
		assertNotNull(rs);
		try {
			assertEquals(2, rs.getResults().size());
			assertFalse(rs.isClosed());
			rs.close();
			assertTrue(rs.isClosed());
		} catch (MetaDataException e1) {			
			fail(e1.getMessage());
		}
		
		try {
			rs.getResults();
			fail("Expected MetaDataException ");
		} catch (MetaDataException e2) {			
			//pass
		}
	}
	
	public void testGetEntityBeyondMDDepth() {
		Entity entity = _helper.getEntity(baseTestUri, "loaded/does_not_exist/nor_this");
		assertNull(entity);
	}
	
	public void testGetEntityBadEntityKey() {
		Entity entity = _helper.getEntity(baseTestUri, "");
		assertNull(entity);

		entity = _helper.getEntity(baseTestUri, null);
		assertNull(entity);
		
		entity = _helper.getEntity(baseTestUri, "/");
		assertNull(entity);

	}
	
	@Test
	public void testSubclassability() {
		IMetaDataQueryHelper myHelper = new MyMetaDataQueryHelper();
		_query.setQueryHelper(myHelper);
		assertSame(_query.getQueryHelper(), myHelper);
		
	}
	
	private class MyMetaDataQueryHelper extends MetaDataQueryHelper {
		MyMetaDataQueryHelper() {
			super(((AbstractMetaDataQuery)_query).getManager(), ((AbstractMetaDataQuery)_query).getDomainContext());
		}
	}
}
