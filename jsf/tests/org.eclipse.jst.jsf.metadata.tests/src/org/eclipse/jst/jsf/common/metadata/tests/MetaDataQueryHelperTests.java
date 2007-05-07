package org.eclipse.jst.jsf.common.metadata.tests;

import java.util.Collections;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.query.IEntityQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.ITraitQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataQueryHelper;
import org.eclipse.jst.jsf.common.metadata.query.SearchControl;
import org.eclipse.jst.jsf.common.metadata.query.SimpleMetaDataQueryVisitorImpl;

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
	 * Partially tests SimpleMetaDataQueryVisitorImpl searchControl
	 */
	public void testGetEntitiesIMetaDataModelContextStringIEntityQueryVisitor() {
		IEntityQueryVisitor visitor = new SimpleMetaDataQueryVisitorImpl(new SearchControl(1, SearchControl.SCOPE_ALL_LEVELS));
		IResultSet rs = MetaDataQueryHelper.getEntities(baseContext, "loaded", visitor);
		assertNotNull(rs);
		assertNotNull(rs.getResults());
		assertTrue(rs.getResults().size() == 1);
		Entity entity = (Entity)rs.getResults().get(0);
		assertNotNull(entity);
		assertEquals(entity.getId(), "loaded");
		rs.close();
		
		//test returning multiple (2)
		visitor = new SimpleMetaDataQueryVisitorImpl(new SearchControl(SearchControl.COUNT_LIMIT_NONE, SearchControl.SCOPE_ALL_LEVELS));
		rs = MetaDataQueryHelper.getEntities(baseContext, "loaded", visitor);
		assertNotNull(rs);
		assertNotNull(rs.getResults());
		assertTrue(rs.getResults().size() == 2);
		entity = (Entity)rs.getResults().get(0);
		assertNotNull(entity);
		assertEquals("loaded", entity.getId());
		Entity secondentity = (Entity)rs.getResults().get(1);
		assertNotNull(secondentity);
		assertEquals("loaded", secondentity.getId());
		assertFalse(secondentity == entity);
		rs.close();
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
	 * Also tests SimpleMetaDataQueryVisitorImpl and IResultSet
	 */
	public void testGetTraits() {
		//TEST with 1 count
		ITraitQueryVisitor visitor = new SimpleMetaDataQueryVisitorImpl(new SearchControl(1, SearchControl.SCOPE_ALL_LEVELS));
		Model model = MetaDataQueryHelper.getModel(baseContext);
		IResultSet rs = MetaDataQueryHelper.getTraits(model, "model-trait", visitor);
		assertNotNull(rs);
		assertNotNull(rs.getResults());
		assertTrue(rs.getResults().size() == 1);
		Trait trait = (Trait)rs.getResults().get(0);
		assertNotNull(trait);
		assertEquals("model-trait", trait.getId());
		rs.close();
		
		//test with COUNT_LIMIT_NONE
		visitor = new SimpleMetaDataQueryVisitorImpl(new SearchControl(SearchControl.COUNT_LIMIT_NONE, SearchControl.SCOPE_ALL_LEVELS));
		rs = MetaDataQueryHelper.getTraits(model, "model-trait", visitor);
		assertNotNull(rs);
		assertNotNull(rs.getResults());
		assertTrue(rs.getResults().size() == 1); //SimpleMetaDataQueryVisitorImpl only returns 1 trait currently!
		trait = (Trait)rs.getResults().get(0);
		assertNotNull(trait);
		assertEquals("model-trait", trait.getId());
		
		//SimpleMetaDataQueryVisitorImpl only returns 1 trait currently!
//		assertFalse(rs.hasMoreElements());
//		assertTrue(rs.hasMoreElements());
//		Trait secondTrait = (Trait)rs.nextElement();
//		assertNotNull(secondTrait);
//		assertEquals("model-trait", secondTrait.getId());
//		assertFalse(secondTrait == trait);
//		assertFalse(rs.hasMoreElements());
		rs.close();
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
		
		IResultSet rs = MetaDataQueryHelper.getEntities(negativeContextBadUri, "foo", new SimpleMetaDataQueryVisitorImpl());
		assertNotNull(rs);
		assertNotNull(rs.getResults());
		assertEquals(Collections.EMPTY_LIST, rs.getResults());
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
