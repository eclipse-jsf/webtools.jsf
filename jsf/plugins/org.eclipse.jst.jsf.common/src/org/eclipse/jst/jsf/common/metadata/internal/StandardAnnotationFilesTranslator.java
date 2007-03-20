/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.Iterator;

import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.EntityGroup;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Model;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;


public class StandardAnnotationFilesTranslator implements IMetaDataTranslator {

	public void translate(IMetaDataModelMergeAssistant assistant) {//TODO: throw proper errors
		//null translate - sourceModel object are already Entities and traits
		//traverse the tree and add to model
		
		//temp - throw proper errors 
		//assert assistant.getSourceModel() instanceof ModelKeyDescriptor;
		
		MetaDataModel mm = assistant.getMergedModel();
		if (mm.getRoot() == null)
			mm.setRoot((Model)assistant.getSourceModelProvider().getSourceModel());
		
		else {
			//for each entity and trait call "add".   assistant will handle merge.
			Model mk = (Model)assistant.getSourceModelProvider().getSourceModel();
			if (mk != null)//possible that model was not loaded
				traverseAndAdd(assistant, mk);
		}			
	}
	
	protected void traverseAndAdd(IMetaDataModelMergeAssistant assistant, final Entity entity){
		assistant.addEntity(entity);
		
		if (entity instanceof Model){
			Model model = (Model)entity;
			for (final Iterator/*EntityGroup*/ it=model.getEntityGroups().iterator();it.hasNext();){
				assistant.addEntityGroup((EntityGroup)it.next());
			}
		}
		
		for (final Iterator/*<Trait>*/ it=entity.getTraits().iterator();it.hasNext();){
			Trait trait = (Trait)it.next();
			assistant.addTrait(entity, trait);
		}
		
		for (final Iterator/*<EntityKey>*/ it=entity.getChildEntities().iterator();it.hasNext();){
			Entity e = (Entity)it.next();
			traverseAndAdd(assistant, e);
		}
		
	}
}
