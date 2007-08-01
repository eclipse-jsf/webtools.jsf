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
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;

public class MergeTests extends AbstractBaseMetaDataTestCase {
	private boolean _debugInfo = false;
	
	public void testIncludeMerge(){
		showDebugInfo(_debugInfo);
		String uri =  "TinyIncludeTest";
		ITaglibDomainMetaDataModelContext modelContext 
			= TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project, uri);
		startTime(uri);
		Model  model = TaglibDomainMetaDataQueryHelper.getModel(modelContext);
		endTime();
		assertNotNull(model);
		
		dumpMDTree(model, 0);
				
		//expect 0 model traits and 1 entity
		assertEquals(0, model.getTraits().size());
		assertEquals(1, model.getChildEntities().size());	
		
		
		//expect 2 traits on A
		Entity A_Entity = TaglibDomainMetaDataQueryHelper.getEntity(model, "A");
		assertEquals(2, A_Entity.getTraits().size());
		Trait t = TaglibDomainMetaDataQueryHelper.getTrait(A_Entity, "T1");
		assertNotNull(t);
		assertEquals("A1", TraitValueHelper.getValueAsString(t));
		t = TaglibDomainMetaDataQueryHelper.getTrait(A_Entity, "T2");
		assertNotNull(t);
		assertEquals("T2FromEG", TraitValueHelper.getValueAsString(t));
		
		//expect 2 child entities for A
		assertEquals(2, A_Entity.getChildEntities().size());		
		Entity a_Entity = TaglibDomainMetaDataQueryHelper.getEntity(A_Entity, "a");
		assertNotNull(a_Entity);
		assertEquals(1, a_Entity.getTraits().size());
		assertEquals(2, a_Entity.getChildEntities().size());	
		Entity subA_Entity = TaglibDomainMetaDataQueryHelper.getEntity(A_Entity, "sub-A");
		assertNotNull(subA_Entity);
		Entity subSubA_Entity = TaglibDomainMetaDataQueryHelper.getEntity(subA_Entity, "sub-sub-A");
		assertNotNull(subSubA_Entity);
		
	}
	


	public void testMergeOfFileAandFileB(){
		showDebugInfo(_debugInfo);
		String uri =  "http://org.eclipse.jsf/mergetest1";
		ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project, uri);
		startTime(uri);
		Model  model = TaglibDomainMetaDataQueryHelper.getModel(modelContext);
		assertNotNull(model);
		endTime();
		
		dumpMDTree(model, 0);
		
		//check model traits - 
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
		
		//check merge of entities within entities
		assertEquals(2, entity.getChildEntities().size());
		Entity secondEntity  = TaglibDomainMetaDataQueryHelper.getEntity(entity, "a");
		assertNotNull(secondEntity);
		assertEquals(2, secondEntity.getTraits().size());
		trait = TaglibDomainMetaDataQueryHelper.getTrait(secondEntity, "A1");
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
		//assertEquals(1, secondEntity.getTraits().size());//do not expect 2 as extra trait from include group is not included
		assertEquals(2, secondEntity.getTraits().size());//do NOW expect 2 as extra trait from include group IS included (https://bugs.eclipse.org/bugs/show_bug.cgi?id=191564)
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "A1");
		assertNotNull(trait);
		assertEquals("A1", TraitValueHelper.getValueAsString(trait));
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "A1");
		assertNotNull(trait);
		
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
	 * All entities and traits come from external model in this test
	 */
	public void testIncludeExternalModel(){
		showDebugInfo(_debugInfo);
		String uri =  "http://org.eclipse.jsf/mergetest2";
		ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project, uri);
		startTime(uri);
		Model  model = TaglibDomainMetaDataQueryHelper.getModel(modelContext);
		endTime();
		assertNotNull(model);
		
		dumpMDTree(model, 0);
		
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

	public void testLoadJSFCore(){
		showDebugInfo(_debugInfo);
		String uri =  "http://java.sun.com/jsf/core";
		ITaglibDomainMetaDataModelContext modelContext = 
			TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project, uri);
		startTime(uri);
		Model  model = TaglibDomainMetaDataQueryHelper.getModel(modelContext);
		endTime();
		assertNotNull(model);
		
//		dumpMDTree(model, 0);
	}
	
	public void testLoadJSFHTML(){
		showDebugInfo(_debugInfo);
		String uri =  "http://java.sun.com/jsf/html";
		ITaglibDomainMetaDataModelContext modelContext = 
			TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project, uri);
		startTime(uri);
		Model  model = TaglibDomainMetaDataQueryHelper.getModel(modelContext);
		endTime();
		assertNotNull(model);
		
//		dumpMDTree(model, 0);
	}
}
