package org.eclipse.jst.jsf.common.metadata.tests;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.query.EmptyResultSet;
import org.eclipse.jst.jsf.common.metadata.query.IEntityQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.ITraitQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataException;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataQueryHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.SearchControl;
import org.eclipse.jst.jsf.common.metadata.query.internal.SimpleEntityQueryVisitorImpl;
import org.eclipse.jst.jsf.common.metadata.query.internal.SimpleTraitQueryVisitorImpl;
import org.eclipse.jst.jsf.common.metadata.query.internal.HierarchicalSearchControl;

/**
 * Thoroughly excercises the MetaDataQueryHelper APIs that will end up touching many of the metadata areas in normal circumstances
 * where there is only a single standard metadata model for a given URI.  
 *
 */
public class MetaDataQueryHelperTests extends AbstractBaseMetaDataTestCase {

	protected IMetaDataModelContext baseContext;
	protected IMetaDataModelContext nullProjectContext;
	protected IMetaDataModelContext negativeContextBadUri;
	protected IMetaDataModelContext negativeContextBadDomain;
	
	public void setUp()  throws Exception {
		super.setUp();
		
	    //setup model contexts
	    baseContext = new MetaDataModelContextImpl(project, domain, baseTestUri);
	    nullProjectContext = new MetaDataModelContextImpl(null, domain, baseTestUri);
	    negativeContextBadUri = new MetaDataModelContextImpl(project, domain, "blah");
	    negativeContextBadDomain = new MetaDataModelContextImpl(project, badDomain, baseTestUri);
	}
	
	/**
	 * Test getModel method but also performs basic read unit tests on Model
	 */
	public void testGetModel() {
	//base
		//positive test
		Model model = MetaDataQueryHelper.getModel(baseContext);
		assertNotNull(baseTestUri+" model should not be null.",model);
		

		

		

		

		

		

		

		

		
	//null proj
		
		model = MetaDataQueryHelper.getModel(nullProjectContext);
		assertNotNull(baseTestUri+" model should not be null.",model);
		//id
		assertNotNull(model.getId());
		assertEquals(baseTestUri+" is not same as model.getId()",baseTestUri, model.getId() );
		
		
		
	//negative tests
		model = MetaDataQueryHelper.getModel(negativeContextBadUri);
		assertNull(model);
		
		model = MetaDataQueryHelper.getModel(negativeContextBadDomain);
		assertNotNull(model);//will use default strategy to load
	}

	public void testGetEntityIMetaDataModelContextString() {
		//positive
		Entity entity = MetaDataQueryHelper.getEntity(baseContext, "loaded");
		assertNotNull(entity);
		
		entity = MetaDataQueryHelper.getEntity(baseContext, "loaded/att3");
		assertNotNull(entity);
		
		//negative
		entity = MetaDataQueryHelper.getEntity(baseContext, "doesnotexist");
		assertNull(entity);
	}

	/**
	 * Return multiple entities
	 * Partially tests SimpleEntityQueryVisitorImpl searchControl
	 */
	public void testGetEntitiesIMetaDataModelContextStringIEntityQueryVisitor() {
		IEntityQueryVisitor visitor = new SimpleEntityQueryVisitorImpl(new HierarchicalSearchControl(1, HierarchicalSearchControl.SCOPE_ALL_LEVELS));
		IResultSet rs = MetaDataQueryHelper.getEntities(baseContext, "loaded", visitor);
		assertNotNull(rs);
		Entity entity = null;
		try {
			assertFalse(rs instanceof EmptyResultSet);
			assertEquals(1, rs.getSize());
			assertTrue(rs.hasNext());
			entity = (Entity)rs.next();
			assertNotNull(entity);
			assertEquals(entity.getId(), "loaded");
			rs.close();
		} catch (MetaDataException e) {
			//MetaDataException not currently being thrown
			fail();
		}
		
		//test returning multiple (2)
		visitor = new SimpleEntityQueryVisitorImpl(new HierarchicalSearchControl(SearchControl.COUNT_LIMIT_NONE, HierarchicalSearchControl.SCOPE_ALL_LEVELS));
		rs = MetaDataQueryHelper.getEntities(baseContext, "loaded", visitor);
		assertNotNull(rs);
		try {
			assertFalse(rs instanceof EmptyResultSet);
			assertEquals(2, rs.getSize());
			assertTrue(rs.hasNext());
			entity = (Entity)rs.next();
			assertNotNull(entity);
			assertEquals(entity.getId(), "loaded");
			Entity secondentity = (Entity)rs.next();
			assertNotNull(secondentity);
			assertEquals("loaded", secondentity.getId());
			assertFalse(secondentity == entity);
			rs.close();
		} catch (MetaDataException e) {
			//MetaDataException not currently being thrown
			fail();
		}
	}

	public void testGetTraitEntityString() {
		Entity entity = MetaDataQueryHelper.getEntity(baseContext, "loaded/att3");

		//positive
		Trait trait = MetaDataQueryHelper.getTrait(entity, "A3");
		assertNotNull(trait);
		
		//negative
		trait = MetaDataQueryHelper.getTrait(entity, "Z3");
		assertNull(trait);
	}

	/**
	 * Return multiple traits
	 * Also tests SimpleEntityQueryVisitorImpl and IResultSet
	 */
	public void testGetTraits() {
		//TEST with 1 count
		ITraitQueryVisitor visitor = new SimpleTraitQueryVisitorImpl(new SearchControl(1));
		Model model = MetaDataQueryHelper.getModel(baseContext);
		IResultSet rs = MetaDataQueryHelper.getTraits(model, "model-trait", visitor);
		assertNotNull(rs);
		Trait trait = null;
		try {
			assertFalse(rs instanceof EmptyResultSet);
			assertEquals(1, rs.getSize());
			assertTrue(rs.hasNext());
			trait = (Trait)rs.next();
			assertNotNull(trait);
			assertEquals("model-trait", trait.getId());
			rs.close();
		} catch (MetaDataException e) {
			//MetaDataException not currently being thrown
			fail();
		}
		
		//test with COUNT_LIMIT_NONE
		visitor = new SimpleTraitQueryVisitorImpl(new SearchControl(SearchControl.COUNT_LIMIT_NONE));
		rs = MetaDataQueryHelper.getTraits(model, "model-trait", visitor);
		assertNotNull(rs);
		try {
			assertFalse(rs instanceof EmptyResultSet);
			assertEquals(2, rs.getSize());
			assertTrue(rs.hasNext());
			trait = (Trait)rs.next();
			assertNotNull(trait);
			assertEquals("model-trait", trait.getId());		
			assertTrue(rs.hasNext());
			Trait secondTrait = (Trait)rs.next();
			assertNotNull(secondTrait);
			assertEquals("model-trait", secondTrait.getId());	
			assertFalse(trait == secondTrait);
			rs.close();
		} catch (MetaDataException e) {
			//MetaDataException not currently being thrown
			fail();
		}
		
	}

	public void testGetEntityEntityString() {
		Entity entity = MetaDataQueryHelper.getEntity(baseContext, "loaded");
		//positive
		entity = MetaDataQueryHelper.getEntity(entity, "att3");
		assertNotNull(entity);
		
		//negative
		entity = MetaDataQueryHelper.getEntity(entity, "zzz");
		assertNull(entity);
	}

	public void testGetEntitiesEntityStringIEntityQueryVisitor() {
		//negative test
		
		IResultSet rs = MetaDataQueryHelper.getEntities(negativeContextBadUri, "foo", new SimpleEntityQueryVisitorImpl());
		assertNotNull(rs);
		try {
			assertEquals(0, rs.getSize());
			assertFalse(rs.hasNext());
		} catch (MetaDataException e) {
			//MetaDataException not currently being thrown
			fail();
		}
	}

	public void testGetTraitIMetaDataModelContextStringString() {
		//positive
		Trait trait = MetaDataQueryHelper.getTrait(baseContext, "loaded/att3", "A3");
		assertNotNull(trait);
		
		//negative
		trait = MetaDataQueryHelper.getTrait(baseContext, "loaded/att3", "zzz");
		assertNull(trait);
		
		trait = MetaDataQueryHelper.getTrait(baseContext, "doesnotexist", "A3");
		assertNull(trait);
		
		trait = MetaDataQueryHelper.getTrait(negativeContextBadDomain, "doesnotexist", "A3");
		assertNull(trait);
	}
	

}
