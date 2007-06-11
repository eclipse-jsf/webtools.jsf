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
package org.eclipse.jst.jsf.common.metadata.tests;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TaglibDomainMetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;

public class MergeTests extends AbstractBaseMetaDataTestCase {
	
	public void testMergeOfFileAandFileB(){
		String uri =  "http://org.eclipse.jsf/mergetest1";
		ITaglibDomainMetaDataModelContext modelContext 
			= TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project, uri);
		Model  model = TaglibDomainMetaDataQueryHelper.getModel(modelContext);
		assertNotNull(model);
		
		//check model traits
		assertEquals(3, model.getTraits().size());
		Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(model, "Dupe");
		assertNotNull(trait);
		assertEquals("dupe from A", TraitValueHelper.getValueAsString(trait));
		
		//check model entities
		assertEquals(5, model.getChildEntities().size());
		Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(model, "A");
		assertNotNull(entity);
		entity = TaglibDomainMetaDataQueryHelper.getEntity(model, "B");
		assertNotNull(entity);
		
		//check merge of traits within entities
		entity = TaglibDomainMetaDataQueryHelper.getEntity(model, "Dupe");
		assertNotNull(entity);
		assertEquals(2, entity.getTraits().size());
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "A1");
		assertNotNull(trait);
		assertEquals("A1FromA", TraitValueHelper.getValueAsString(trait));
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "B1");
		assertNotNull(trait);
		
		//check merge of entites within entities
		assertEquals(2, entity.getChildEntities().size());
		Entity secondEntity  = TaglibDomainMetaDataQueryHelper.getEntity(entity, "a");
		assertNotNull(secondEntity);
		assertEquals(2, secondEntity.getTraits().size());
		trait = trait = TaglibDomainMetaDataQueryHelper.getTrait(secondEntity, "A1");
		assertNotNull(trait);
		assertEquals("A1FromA", TraitValueHelper.getValueAsString(trait));
		
		//check merge of traits with included entities
		entity = TaglibDomainMetaDataQueryHelper.getEntity(model, "DupeWithInclude");
		assertNotNull(entity);
		assertEquals(2, entity.getTraits().size());
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "A1");
		assertNotNull(trait);
		assertEquals("A1", TraitValueHelper.getValueAsString(trait));
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "B");
		assertNotNull(trait);
		assertEquals("trait SHOULD appear in merge from eg2", TraitValueHelper.getValueAsString(trait));
		
		//check merge of entities with includes
		assertEquals(2, entity.getChildEntities().size());
		secondEntity  = TaglibDomainMetaDataQueryHelper.getEntity(entity, "a");
		assertNotNull(secondEntity);
		assertEquals(1, secondEntity.getTraits().size());//do not expect 2 as extra trait from include group is not inlcuded
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "A1");
		assertNotNull(trait);
		assertEquals("A1", TraitValueHelper.getValueAsString(trait));
		
		//check merge of entities with includes where all come from include
		entity = TaglibDomainMetaDataQueryHelper.getEntity(model, "DupeWithInclude2");
		assertNotNull(entity);
		assertEquals(1, entity.getTraits().size());
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "A1");
		assertNotNull(trait);
		assertEquals("A1FromEG1", TraitValueHelper.getValueAsString(trait));
		
		assertEquals(1, entity.getChildEntities().size());
		secondEntity  = TaglibDomainMetaDataQueryHelper.getEntity(entity, "a");
		assertNotNull(secondEntity);
		assertEquals(2, secondEntity.getTraits().size());
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "A1");
		assertNotNull(trait);
		assertEquals("A1FromEG1", TraitValueHelper.getValueAsString(trait));

	}
	
	/**
	 * All entites and traits come from external model in this test
	 */
	public void testIncludeExternalModel(){
		String uri =  "http://org.eclipse.jsf/mergetest2";
		ITaglibDomainMetaDataModelContext modelContext 
			= TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project, uri);
		Model  model = TaglibDomainMetaDataQueryHelper.getModel(modelContext);
		assertNotNull(model);
		
		//check model traits
		assertEquals(1, model.getTraits().size());
		Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(model, "A1");
		assertNotNull(trait);
		assertEquals("A1FromEG1", TraitValueHelper.getValueAsString(trait));
		
		//check model entities
		assertEquals(1, model.getChildEntities().size());
		Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(model, "a");
		assertNotNull(entity);
		assertEquals(0, entity.getChildEntities().size());
		assertEquals(2, entity.getTraits().size());
	}
}
