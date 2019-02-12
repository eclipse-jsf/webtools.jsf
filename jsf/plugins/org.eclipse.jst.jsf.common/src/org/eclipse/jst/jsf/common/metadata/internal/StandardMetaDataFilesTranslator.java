/*******************************************************************************
 * Copyright (c) 2007, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.EntityGroup;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.StandardMetaDataFileRegistry.StandardMetaDataFilesProvider;


/**
 * A 'null' translation of a metadata file.  Entities and traits are not transformed.
 *
 */
public class StandardMetaDataFilesTranslator implements IMetaDataTranslator {

	public boolean canTranslate(final IMetaDataSourceModelProvider modelProvider) {
		if (modelProvider instanceof StandardMetaDataFilesProvider)
			return true;
		return false;
	}
	
	public void translate(final IMetaDataModelMergeAssistant assistant) {//TODO: throw proper errors
		//null translate - sourceModel object are already Entities and traits
		//traverse the tree and add to model
		
		//temp - throw proper errors 
		//assert assistant.getSourceModel() instanceof ModelKeyDescriptor;
		
		final MetaDataModel mm = assistant.getMergedModel();
		final Model mk = (Model)assistant.getSourceModelProvider().getSourceModel();
		if (mm.getRoot() == null) {
			//create copy, otherwise source model becomes merged model because of reference
			final Copier copier = new Copier();		
			final Model newModel = (Model)copier.copy(mk.getModel());
			copier.copyReferences();
			mm.setRoot(newModel);
		}
		else {
			//for each entity and trait call "add".   assistant will handle merge.			
			if (mk != null) {//possible that model was not loaded 
				traverseAndAdd(assistant, mk);
			} else if (StandardModelFactory.DEBUG_MD_LOAD) {
				JSFCommonPlugin.log(IStatus.ERROR,"Unable to load source model: "+assistant.getSourceModelProvider()); //$NON-NLS-1$
			}
		}			
	}
	
	/**
	 * Add entity and any children
	 * @param assistant
	 * @param entity
	 */
	protected void traverseAndAdd(final IMetaDataModelMergeAssistant assistant, final Entity entity){
		final Entity mmEntity = assistant.addEntity(entity);
		
		if (entity instanceof Model){
			final Model model = (Model)entity;
			for (final Iterator/*EntityGroup*/ it=model.getEntityGroups().iterator();it.hasNext();){
				assistant.addEntityGroup((EntityGroup)it.next());
			}
		}
		
		for (final Iterator/*<Trait>*/ it=entity.getTraits().iterator();it.hasNext();){
			final Trait trait = (Trait)it.next();
			assistant.addTrait(mmEntity, trait);
		}
		
		for (final Iterator/*<EntityKey>*/ it=entity.getChildEntities().iterator();it.hasNext();){
			final Entity e = (Entity)it.next();
			traverseAndAdd(assistant, e);
		}
		
	}

}
