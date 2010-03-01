/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
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
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TaglibDomainMetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.query.EmptyResultSet;
import org.eclipse.jst.jsf.common.metadata.query.IEntityQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.ITraitQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataException;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.HierarchicalSearchControl;
import org.eclipse.jst.jsf.common.metadata.query.internal.SearchControl;
import org.eclipse.jst.jsf.common.metadata.query.internal.SimpleEntityQueryVisitorImpl;
import org.eclipse.jst.jsf.common.metadata.query.internal.SimpleTraitQueryVisitorImpl;

/**
 * Thoroughly excercises the TaglibDomainMetaDataQueryHelper APIs that will end up touching many of the metadata areas in normal circumstances
 * where there is only a single standard metadata model for a given URI.  
 *
 */
public class MetaDataQueryHelperTests extends AbstractBaseMetaDataTestCase {

	protected ITaglibDomainMetaDataModelContext baseContext;
	protected ITaglibDomainMetaDataModelContext nullProjectContext;
	protected ITaglibDomainMetaDataModelContext negativeContextBadUri;
	protected ITaglibDomainMetaDataModelContext negativeContextBadDomain;
	
	public void setUp()  throws Exception {
		super.setUp();
		
	    //setup model contexts
	    baseContext = new TaglibDomainMetaDataModelContextImpl(domain, project, baseTestUri);
	    nullProjectContext = new TaglibDomainMetaDataModelContextImpl(domain, null, baseTestUri);
	    negativeContextBadUri = new TaglibDomainMetaDataModelContextImpl(domain, project, "blah");
	    negativeContextBadDomain = new TaglibDomainMetaDataModelContextImpl(badDomain, project, baseTestUri);
	}
	
	/**
	 * Test getModel method but also performs basic read unit tests on Model
	 */
	public void testGetModel() {
	//base
		//positive test
		Model model = TaglibDomainMetaDataQueryHelper.getModel(baseContext);
		assertNotNull(baseTestUri+" model should not be null.",model);
		
	//null proj
		
		model = TaglibDomainMetaDataQueryHelper.getModel(nullProjectContext);
		assertNotNull(baseTestUri+" model should not be null.",model);
		//id
		assertNotNull(model.getId());
		assertEquals(baseTestUri+" is not same as model.getId()",baseTestUri, model.getId() );
		
		
		
	//negative tests
		model = TaglibDomainMetaDataQueryHelper.getModel(negativeContextBadUri);
		assertNull(model);
		
		model = TaglibDomainMetaDataQueryHelper.getModel(negativeContextBadDomain);
		assertNotNull(model);//will use default strategy to load
	}

	public void testGetEntityIMetaDataModelContextString() {
		//positive
		Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, "loaded");
		assertNotNull(entity);
		
		entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, "loaded/att3");
		assertNotNull(entity);
		
		//negative
		entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, "doesnotexist");
		assertNull(entity);
	}

	/**
	 * Return multiple entities
	 * Partially tests SimpleEntityQueryVisitorImpl searchControl
	 */
	public void testGetEntitiesIMetaDataModelContextStringIEntityQueryVisitor() {
		IEntityQueryVisitor visitor = new SimpleEntityQueryVisitorImpl(new HierarchicalSearchControl(1, HierarchicalSearchControl.SCOPE_ALL_LEVELS));
		IResultSet rs = TaglibDomainMetaDataQueryHelper.getEntities(baseContext, "loaded", visitor);
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
		rs = TaglibDomainMetaDataQueryHelper.getEntities(baseContext, "loaded", visitor);
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
		Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, "loaded/att3");

		//positive
		Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "A3");
		assertNotNull(trait);
		
		//negative
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "Z3");
		assertNull(trait);
	}

	/**
	 * Return multiple traits
	 * Also tests SimpleEntityQueryVisitorImpl and IResultSet
	 */
	public void testGetTraits() {
		//TEST with 1 count
		ITraitQueryVisitor visitor = new SimpleTraitQueryVisitorImpl(new SearchControl(1));
		Model model = TaglibDomainMetaDataQueryHelper.getModel(baseContext);
		IResultSet rs = TaglibDomainMetaDataQueryHelper.getTraits(model, "model-trait", visitor);
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
		rs = TaglibDomainMetaDataQueryHelper.getTraits(model, "model-trait", visitor);
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
		Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, "loaded");
		//positive
		entity = TaglibDomainMetaDataQueryHelper.getEntity(entity, "att3");
		assertNotNull(entity);
		
		//negative
		entity = TaglibDomainMetaDataQueryHelper.getEntity(entity, "zzz");
		assertNull(entity);
	}

	public void testGetEntitiesEntityStringIEntityQueryVisitor() {
		//negative test
		
		IResultSet rs = TaglibDomainMetaDataQueryHelper.getEntities(negativeContextBadUri, "foo", new SimpleEntityQueryVisitorImpl());
		assertNotNull(rs);
		try {
			assertEquals(0, rs.getResults().size());
		} catch (MetaDataException e) {			
			fail(e.getMessage());
		}
	}

	public void testGetTraitIMetaDataModelContextStringString() {
		//positive
		Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(baseContext, "loaded/att3", "A3");
		assertNotNull(trait);
		
		//negative
		trait = TaglibDomainMetaDataQueryHelper.getTrait(baseContext, "loaded/att3", "zzz");
		assertNull(trait);
		
		trait = TaglibDomainMetaDataQueryHelper.getTrait(baseContext, "doesnotexist", "A3");
		assertNull(trait);
		
		trait = TaglibDomainMetaDataQueryHelper.getTrait(negativeContextBadDomain, "doesnotexist", "A3");
		assertNull(trait);
	}
	
	public void testResultSets() {
		IResultSet rs = TaglibDomainMetaDataQueryHelper.getEntities(baseContext, "loaded", new SimpleEntityQueryVisitorImpl());
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
		Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, "loaded/does_not_exist/nor_this");
		assertNull(entity);
	}
	
	public void testGetEntityBadEntityKey() {
		Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, "");
		assertNull(entity);

		entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, null);
		assertNull(entity);
		
		entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, "/");
		assertNull(entity);

	}
	
	public void testNullProject() {		
		Model model = TaglibDomainMetaDataQueryHelper.getModel(nullProjectContext);
		assertNotNull(model);
		Model model1 = TaglibDomainMetaDataQueryHelper.getModel(nullProjectContext);
		assertEquals(model, model1);		
	}

}
