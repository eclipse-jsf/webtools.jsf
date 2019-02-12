/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.tests.updated;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataException;
import org.eclipse.jst.jsf.common.metadata.query.internal.AbstractMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.IPredicateMatcher;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainEntityPredicateMatcher;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainPredicateMatcherFactory;
import org.junit.Before;
import org.junit.Test;

public class TaglibMetaDataQueryTests extends AbstractBaseMetaDataTestCase {

	private ITaglibDomainMetaDataQuery _query;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(project);
		_query = MetaDataQueryFactory.getInstance().createQuery(context);
		assertNotNull(_query);
	}

	@Test
	public void testFindTagLibraryModel() {
		Model model = _query.findTagLibraryModel(baseTestUri);		
		assertNotNull(model);
		assertEquals(baseTestUri, model.getId());
		
		Model model2 = _query.findTagLibraryModel(baseTestUri);
		assertNotNull(model2);
		assertSame(model, model2);
		
		Model model3 = _query.findTagLibraryModel("http://org.eclipse.jsf/test2");
		assertNotNull(model3);
		assertNotSame(model, model3);
	}

	@Test
	public void testFindTagEntity() {
		Model tagLib = _query.findTagLibraryModel(baseTestUri);		
		assertNotNull(tagLib);
		Entity tag = _query.findTagEntity(tagLib, "loaded");
		assertNotNull(tag);
		assertEquals("loaded", tag.getId());
	}

	@Test
	public void testFindTagAttributeEntity() {
		Model tagLib = _query.findTagLibraryModel(baseTestUri);		
		assertNotNull(tagLib);
		Entity tag = _query.findTagEntity(tagLib, "loaded");
		assertNotNull(tag);
		Entity attr = _query.findTagAttributeEntity(tag, "att2");
		assertNotNull(attr);
		assertEquals("att2", attr.getId());
	}

	@Test
	public void testFindTrait() {
		Model tagLib = _query.findTagLibraryModel(baseTestUri);		
		assertNotNull(tagLib);
		Trait trait = _query.findTrait(tagLib, "model-trait");
		assertNotNull(trait);
	}

	@Test
	public void testAbstractSuper() {
		assertNotNull(((AbstractMetaDataQuery)_query).getManager());
		IMetaDataDomainContext context = ((AbstractMetaDataQuery)_query).getDomainContext();
		assertNotNull(context);
		assertEquals(domain, context.getDomainId());
		assertNotNull(context.getAdapter(IProject.class));
		assertSame(project, context.getAdapter(IProject.class));
	}

	@Test
	public void testHelperAvailable() {
		assertNotNull(_query.getQueryHelper());
	}

// -----------------------------------------------------------------------------------------------------------------------
// ---     Tests below are implicitly testing the TaglibDomainRegexQueryMatcherFactory and the predicate matchers it produces:
// ---        * TaglibDomainEntityIdRegexPredicateMatcher
// ---        * TaglibDomainTraitIdRegexPredicateMatcher	
	
	@Test
	public void testFindModelWithQuerySpec() {
		ITaglibDomainPredicateMatcherFactory matcherFactory = MetaDataQueryContextFactory.getInstance().getTaglibDomainPredicateMatcherFactory();
		ITaglibDomainEntityPredicateMatcher matcher = matcherFactory.createTagLibraryModelMatcher(baseTestUri);
		try {
			IResultSet<Entity> models = _query.findEntities(matcher);
			assertNotNull(models);
			assertEquals(1, models.getResults().size());
		} catch (MetaDataException e) {
			fail();
		} 
	}

	@Test
	public void testFindSingleTagWithQuerySpec() {
		ITaglibDomainPredicateMatcherFactory matcherFactory = MetaDataQueryContextFactory.getInstance().getTaglibDomainPredicateMatcherFactory();
		ITaglibDomainEntityPredicateMatcher matcher = matcherFactory.createTagEntityMatcher(baseTestUri, "NLS");
		try {
			IResultSet<Entity> entities = _query.findEntities(matcher);
			assertNotNull(entities);
			assertEquals(1, entities.getResults().size());
		} catch (MetaDataException e) {
			fail();
		} 
	}

	@Test
	public void testFindMultipleTagsWithQuerySpec() {
		ITaglibDomainPredicateMatcherFactory matcherFactory = MetaDataQueryContextFactory.getInstance().getTaglibDomainPredicateMatcherFactory();
		ITaglibDomainEntityPredicateMatcher matcher = matcherFactory.createTagEntityMatcher(baseTestUri, "loaded");
		try {
			IResultSet<Entity> tagEntities = _query.findEntities(matcher);
			assertNotNull(tagEntities);
			assertTrue(tagEntities.getResults().size() == 2);
		} catch (MetaDataException e) {
			fail();
		} 
		
		matcher = matcherFactory.createTagEntityMatcher(baseTestUri, ".*");
		try {
			IResultSet<Entity> tagEntities = _query.findEntities(matcher);
			assertNotNull(tagEntities);
			assertTrue(tagEntities.getResults().size() == 6);
		} catch (MetaDataException e) {
			fail();
		} 
	}

	@Test
	public void testFindSingleTagAttributeWithQuerySpec() {
		ITaglibDomainPredicateMatcherFactory matcherFactory = MetaDataQueryContextFactory.getInstance().getTaglibDomainPredicateMatcherFactory();
		ITaglibDomainEntityPredicateMatcher matcher = matcherFactory.createTagAttributeEntityMatcher(baseTestUri, "loaded", "att2");
		try {
			IResultSet<Entity> tagAttrEntities = _query.findEntities(matcher);
			assertNotNull(tagAttrEntities);
			assertTrue(tagAttrEntities.getResults().size() == 1);
		} catch (MetaDataException e) {
			fail();
		} 
	}

	@Test
	public void testFindMultipleTagAttrsWithQuerySpec() {
		ITaglibDomainPredicateMatcherFactory matcherFactory = MetaDataQueryContextFactory.getInstance().getTaglibDomainPredicateMatcherFactory();
		ITaglibDomainEntityPredicateMatcher matcher = matcherFactory.createTagAttributeEntityMatcher(baseTestUri, "loaded", ".*");
		try {
			IResultSet<Entity> tagAttrEntities = _query.findEntities(matcher);
			assertNotNull(tagAttrEntities);
			assertEquals(5, tagAttrEntities.getResults().size()); //4 from 1st loaded, plus 1 from second
		} catch (MetaDataException e) {
			fail();
		} 
		
		matcher = matcherFactory.createTagAttributeEntityMatcher(baseTestUri, "NLS", ".*");
		try {
			IResultSet<Entity> tagAttrEntities = _query.findEntities(matcher);
			assertNotNull(tagAttrEntities);
			assertEquals(1, tagAttrEntities.getResults().size() );
		} catch (MetaDataException e) {
			fail();
		} 
		
		matcher = matcherFactory.createTagAttributeEntityMatcher(baseTestUri, ".*", "att*");
		try {
			IResultSet<Entity> tagAttrEntities = _query.findEntities(matcher);
			assertNotNull(tagAttrEntities);
			assertEquals(4, tagAttrEntities.getResults().size());			
		} catch (MetaDataException e) {
			fail();
		} 
	}

	@Test
	public void testFindSingleTagAttrWithMultiQuerySpec() {
		ITaglibDomainPredicateMatcherFactory matcherFactory = MetaDataQueryContextFactory.getInstance().getTaglibDomainPredicateMatcherFactory();
		ITaglibDomainEntityPredicateMatcher matcher = matcherFactory.createTagAttributeEntityMatcher(baseTestUri, "loaded", ".*");
		Entity tagAttrEntity = _query.findEntity(matcher);
		assertNotNull(tagAttrEntity);
	}

	@Test
	public void testFindSingleTraitWithSingleEntityQuerySpec() {
		ITaglibDomainPredicateMatcherFactory matcherFactory = MetaDataQueryContextFactory.getInstance().getTaglibDomainPredicateMatcherFactory();
		ITaglibDomainEntityPredicateMatcher entityMatcher 	= matcherFactory.createTagAttributeEntityMatcher(baseTestUri, "loaded", "att2");
		IPredicateMatcher<Trait> traitMatcher = matcherFactory.createTraitMatcher("A2");
		try {
			IResultSet<Trait> traits = _query.findTraits(entityMatcher, traitMatcher);
			assertNotNull(traits );
			assertEquals(1, traits.getResults().size());	
		} catch (MetaDataException e) {
			fail();			
		} 
	}

	@Test
	public void testFindMultipleTraitsWithSingleEntityQuerySpec() {
		
		ITaglibDomainPredicateMatcherFactory matcherFactory = MetaDataQueryContextFactory.getInstance().getTaglibDomainPredicateMatcherFactory();
		ITaglibDomainEntityPredicateMatcher entityMatcher 	= matcherFactory.createTagAttributeEntityMatcher(baseTestUri, "loaded", "att2");
		
		//which traits beginning with "A" are available on "loaded" tag 
		IPredicateMatcher<Trait> traitMatcher = matcherFactory.createTraitMatcher("^[AB]2");
		try {
			IResultSet<Trait> traits = _query.findTraits(entityMatcher, traitMatcher);
			assertNotNull(traits );
			assertEquals(2, traits.getResults().size());	
		} catch (MetaDataException e) {
			fail();			
		} 
	}

	@Test
	public void testFindMultipleTraitsWithMultipleEntityQuerySpec() {
		ITaglibDomainPredicateMatcherFactory matcherFactory = MetaDataQueryContextFactory.getInstance().getTaglibDomainPredicateMatcherFactory();
		ITaglibDomainEntityPredicateMatcher entityMatcher	= matcherFactory.createTagEntityMatcher(baseTestUri, ".*");
		//which traits beginning with "A" are available on any tag 
		IPredicateMatcher<Trait> traitMatcher 	= matcherFactory.createTraitMatcher("^A.*");
		try {
			IResultSet<Trait> traits = _query.findTraits(entityMatcher, traitMatcher);
			assertNotNull(traits);
			assertEquals(2, traits.getResults().size());	
		} catch (MetaDataException e) {
			fail();			
		} 		
		
		//which traits beginning with "A" or "a" are available on any tag attribute
		entityMatcher 	= matcherFactory.createTagAttributeEntityMatcher(baseTestUri, ".*", ".*");
		traitMatcher 	= matcherFactory.createTraitMatcher("^[aA].*");
		try {
			IResultSet<Trait> traits = _query.findTraits(entityMatcher, traitMatcher);
			assertNotNull(traits);
			assertEquals(6, traits.getResults().size());	
		} catch (MetaDataException e) {
			fail();			
		} 
	}

	@Test
	public void testFindSingleTraitFromMultipleEntityQuerySpecWithSearchControl() {
		ITaglibDomainPredicateMatcherFactory matcherFactory = MetaDataQueryContextFactory.getInstance().getTaglibDomainPredicateMatcherFactory();
		ITaglibDomainEntityPredicateMatcher entityMatcher	= matcherFactory.createTagEntityMatcher(baseTestUri, ".*");
		//which traits beginning with "A" are available on any tag... but stop and return only the first
		IPredicateMatcher<Trait> traitMatcher 	= matcherFactory.createTraitMatcher("^A.*");
		Trait trait = _query.findTrait(entityMatcher, traitMatcher);
		assertNotNull(trait);

		//which traits beginning with "A" or "a" are available on any tag attribute... but stop and return only the first
		entityMatcher 	= matcherFactory.createTagAttributeEntityMatcher(baseTestUri, ".*", ".*");
		traitMatcher 	= matcherFactory.createTraitMatcher("^[aA].*");
		trait = _query.findTrait(entityMatcher, traitMatcher);
		assertNotNull(trait);
	}

}
