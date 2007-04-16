package org.eclipse.jst.jsf.common.metadata.tests;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataQueryHelper;

public class MergeTests extends AbstractBaseMetaDataTestCase {
	
	public void testMergeOfFileAandFileB(){
		String uri =  "http://org.eclipse.jsf/mergetest1";
		IMetaDataModelContext modelContext = new MetaDataModelContextImpl(project, domain, uri);
		Model  model = MetaDataQueryHelper.getModel(modelContext);
		assertNotNull(model);
		
		//check model traits
		assertEquals(3, model.getTraits().size());
		Trait trait = MetaDataQueryHelper.getTrait(model, "Dupe");
		assertNotNull(trait);
		assertEquals("dupe from A", TraitValueHelper.getValueAsString(trait));
		
		//check model entities
		assertEquals(5, model.getChildEntities().size());
		Entity entity = MetaDataQueryHelper.getEntity(model, "A");
		assertNotNull(entity);
		entity = MetaDataQueryHelper.getEntity(model, "B");
		assertNotNull(entity);
		
		//check merge of traits within entities
		entity = MetaDataQueryHelper.getEntity(model, "Dupe");
		assertNotNull(entity);
		assertEquals(2, entity.getTraits().size());
		trait = MetaDataQueryHelper.getTrait(entity, "A1");
		assertNotNull(trait);
		assertEquals("A1FromA", TraitValueHelper.getValueAsString(trait));
		trait = MetaDataQueryHelper.getTrait(entity, "B1");
		assertNotNull(trait);
		
		//check merge of entites within entities
		assertEquals(2, entity.getChildEntities().size());
		Entity secondEntity  = MetaDataQueryHelper.getEntity(entity, "a");
		assertNotNull(secondEntity);
		assertEquals(2, secondEntity.getTraits().size());
		trait = trait = MetaDataQueryHelper.getTrait(secondEntity, "A1");
		assertNotNull(trait);
		assertEquals("A1FromA", TraitValueHelper.getValueAsString(trait));
		
		//check merge of traits with included entities
		entity = MetaDataQueryHelper.getEntity(model, "DupeWithInclude");
		assertNotNull(entity);
		assertEquals(2, entity.getTraits().size());
		trait = MetaDataQueryHelper.getTrait(entity, "A1");
		assertNotNull(trait);
		assertEquals("A1", TraitValueHelper.getValueAsString(trait));
		trait = MetaDataQueryHelper.getTrait(entity, "B");
		assertNotNull(trait);
		assertEquals("trait SHOULD appear in merge from eg2", TraitValueHelper.getValueAsString(trait));
		
		//check merge of entities with includes
		assertEquals(2, entity.getChildEntities().size());
		secondEntity  = MetaDataQueryHelper.getEntity(entity, "a");
		assertNotNull(secondEntity);
		assertEquals(1, secondEntity.getTraits().size());//do not expect 2 as extra trait from include group is not inlcuded
		trait = MetaDataQueryHelper.getTrait(entity, "A1");
		assertNotNull(trait);
		assertEquals("A1", TraitValueHelper.getValueAsString(trait));
		
		//check merge of entities with includes where all come from include
		entity = MetaDataQueryHelper.getEntity(model, "DupeWithInclude2");
		assertNotNull(entity);
		assertEquals(1, entity.getTraits().size());
		trait = MetaDataQueryHelper.getTrait(entity, "A1");
		assertNotNull(trait);
		assertEquals("A1FromEG1", TraitValueHelper.getValueAsString(trait));
		
		assertEquals(1, entity.getChildEntities().size());
		secondEntity  = MetaDataQueryHelper.getEntity(entity, "a");
		assertNotNull(secondEntity);
		assertEquals(2, secondEntity.getTraits().size());
		trait = MetaDataQueryHelper.getTrait(entity, "A1");
		assertNotNull(trait);
		assertEquals("A1FromEG1", TraitValueHelper.getValueAsString(trait));

	}
	
	/**
	 * All entites and traits come from external model in this test
	 */
	public void testIncludeExternalModel(){
		String uri =  "http://org.eclipse.jsf/mergetest2";
		IMetaDataModelContext modelContext = new MetaDataModelContextImpl(project, domain, uri);
		Model  model = MetaDataQueryHelper.getModel(modelContext);
		assertNotNull(model);
		
		//check model traits
		assertEquals(1, model.getTraits().size());
		Trait trait = MetaDataQueryHelper.getTrait(model, "A1");
		assertNotNull(trait);
		assertEquals("A1FromEG1", TraitValueHelper.getValueAsString(trait));
		
		//check model entities
		assertEquals(1, model.getChildEntities().size());
		Entity entity = MetaDataQueryHelper.getEntity(model, "a");
		assertNotNull(entity);
		assertEquals(0, entity.getChildEntities().size());
		assertEquals(2, entity.getTraits().size());
	}
}
