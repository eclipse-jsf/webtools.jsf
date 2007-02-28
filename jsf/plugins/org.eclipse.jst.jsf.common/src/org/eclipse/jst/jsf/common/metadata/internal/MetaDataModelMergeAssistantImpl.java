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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.EntityGroup;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.IncludeEntityGroup;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Model;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.MetaDataQueryHelper;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.SearchControl;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.SimpleMetaDataQueryVisitorImpl;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.SimpleResultSet;
/**
 * Implements {@link IMetaDataModelMergeAssistant}
 * 
 * Responsible for merging source models into one.  The first model in has subsequent 
 * entities and traits added to it.   
 * 
 * TODO - make locating of existing entities and traits in the merged model more efficient
 *
 */
public class MetaDataModelMergeAssistantImpl implements
		IMetaDataModelMergeAssistant {
	
	private MetaDataModel mergedModel;
	private Copier copier;
	private SimpleMetaDataQueryVisitorImpl visitor;
	private IMetaDataSourceModelProvider provider;
	
	/**
	 * Constructor
	 * @param model
	 */
	public MetaDataModelMergeAssistantImpl(MetaDataModel model) {
		this.mergedModel = model;
		copier = new Copier();
		visitor = new SimpleMetaDataQueryVisitorImpl(new SearchControl(1, SearchControl.SCOPE_ALL_LEVELS));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant#getMergedModel()
	 */
	public MetaDataModel getMergedModel() {
		return mergedModel;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant#getSourceModel()
	 */
	public IMetaDataSourceModelProvider getSourceModelProvider() {
		return provider;
	}

	public void setSourceModelProvider(IMetaDataSourceModelProvider provider) {
		this.provider = provider;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant#addEntityGroup(org.eclipse.jst.jsf.common.metadata.internal.provisional.EntityGroup)
	 */
	public void addEntityGroup(EntityGroup entityGroup) {
		Model model = (Model)getMergedModel().getRoot();
		if (!isExistingEntityGroup(model, entityGroup)){
			model.getEntityGroups().add(entityGroup);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant#addEntity(org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity)
	 */
	public boolean addEntity(final Entity entity) {
		Entity mmEntity = getMergedEntity(entity);
		if (mmEntity == null){
			addEntityAsNecessary((Entity)entity.eContainer(), entity);
			return true;
		}
		addIncludeGroupsAsNecessary(mmEntity, entity);
		return false;
	}

	private void addIncludeGroupsAsNecessary(final Entity mmEntity, final Entity entity) {
		for (Iterator it=entity.getIncludeGroups().iterator();it.hasNext();){
			IncludeEntityGroup grp = (IncludeEntityGroup)it.next();
			boolean found = false;
			for (Iterator it2=mmEntity.getIncludeGroups().iterator();it2.hasNext();){
				IncludeEntityGroup grp2 = (IncludeEntityGroup)it2.next();
				if (grp2.equals(grp)){
					found = true;
					break;
				}
			}
			if (!found){//maybe we should clone and add
				mmEntity.getIncludeGroups().add(grp);
			}
		}
	}

	/**
	 * Checks to see if the entity (by id) is present in the mergedModel or not.
	 * If not, it will perform a copy of the entity and it's attributes using
	 * EcoreUtil.Copier.  
	 *  
	 * TODO - check: do we need to copy?   Why not use source entity?
	 * 
	 * @param parent
	 * @param entity
	 * @return
	 */
	private Entity addEntityAsNecessary(final Entity parent, final Entity entity) {		
		Entity mmParent = null;
		Entity mmEntity = null;
		if (parent != null){
			mmParent = getMergedEntity(parent);
			if (mmParent == null){
				mmParent = addEntityAsNecessary((Entity)entity.eContainer(), entity);
			}
		}
		if (mmParent != null){
			mmEntity = addEntityInternal(mmParent, entity);
			return mmEntity;
		}
		return mmEntity;
	}
	
	private boolean isExistingEntityGroup(Model model, EntityGroup entityGroup) {
		boolean found = false;
		for(Iterator it=model.getEntityGroups().iterator();it.hasNext();){
			if (entityGroup.getId().equals(((EntityGroup)it.next()).getId()))
				return true;			
		}
		return found;
	}

	private boolean isExistingChildEntity(final Entity parent, final Entity entity) {
		boolean found = false;
		for(Iterator it=parent.getChildEntities().iterator();it.hasNext();){
			if (entity.getId().equals(((Entity)it.next()).getId()))
				return true;			
		}
		return found;
	}
	
	private boolean isExistingTrait(final Entity entity, final Trait trait) {
		boolean found = false;
		for(Iterator it=entity.getTraits().iterator();it.hasNext();){
			if (trait.getId().equals(((Trait)it.next()).getId()))
				return true;			
		}
		return found;
	}

	private synchronized Entity addEntityInternal(final Entity parent, final Entity entity) {
		Entity mmEntity =(Entity)copier.copy((EObject)entity);
		copier.copyReferences();
		parent.getChildEntities().add(mmEntity);
		return mmEntity;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant#addTrait(org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity, org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait)
	 */
	public boolean addTrait(Entity entity, Trait trait) {
		Entity returnedEntity = getMergedEntity(entity);
		if (returnedEntity != null){
			return addTraitAsNecessary(returnedEntity, trait);
		}
		return false;
	}
	

	private boolean addTraitAsNecessary(Entity mergedEntity, Trait trait) {
		Trait mmTrait = getMergedTrait(mergedEntity, trait);
		if (mmTrait == null){			
			mmTrait = addTraitInternal(mergedEntity, trait);
			return true;

		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant#setMergeComplete()
	 */
	public void setMergeComplete() {
		Model model = (Model)getMergedModel().getRoot();
		if (model != null)
			processIncludeGroups(model);		
	}
	
	/**
	 * Copies the passed trait and adds it to the merged entity.
	 * The source model provider of the trait is then set on the trait so that 
	 * the trait can know where it came from.
	 * 
	 * @param parent
	 * @param trait
	 * @return merged Trait
	 */
	private Trait addTraitInternal(final Entity parent, final Trait trait) {		
		Trait mmTrait =(Trait)copier.copy((EObject)trait);
		copier.copyReferences();
		parent.getTraits().add(mmTrait);
		//set the model key to know from where the trait came
		mmTrait.setSourceModelProvider(trait.getSourceModelProvider());
		return mmTrait;
	}

	/**
	 * Locates the entity in the merged model matching by id only. 
	 * If not loacted, this method returns null;
	 * 
	 * @param entity
	 * @return merged entity
	 */
	public Entity getMergedEntity(Entity entity){
		if (entity instanceof Model)
			return (Entity)mergedModel.getRoot();
		
		Entity ret = null;
		String entityKey = getIdRelativeToRoot(entity);
		SimpleResultSet rs = (SimpleResultSet)visitor.findEntities((Entity)mergedModel.getRoot(), entityKey);
		if (rs.getResults().size() >0)
			ret = (Entity)rs.getResults().get(0);
		
		rs.close();
		return ret;
	}
	
	private String getIdRelativeToRoot(final Entity entity) {
		Entity e = entity;
		StringBuffer buf = new StringBuffer();
		while (e.eContainer() != null){
			buf.insert(0, e.getId());
			if (e.eContainer()!=null && e.eContainer().eContainer() != null)
				buf.insert(0,"/");
			e = (Entity)e.eContainer();
		}
		return buf.toString();
	}

	/**
	 * Locates the trait in the merged model matching by id only. 
	 * If not located, this method returns null;
	 *  
	 * @param entity
	 * @param trait
	 * @return merged Trait
	 */
	public Trait getMergedTrait(Entity entity, Trait trait){
		SimpleResultSet rs = (SimpleResultSet)visitor.findTraits(entity, trait.getId());
		Trait ret = null;
		if (rs.getResults().size() >0)
			ret = (Trait)rs.getResults().get(0);
		
		rs.close();
		return ret;
	}
	
	private void processIncludeGroups(final Model root) {
		addEntityGroupReferencesRecursively((Entity)root);
	}

	private void addEntityGroupReferencesRecursively(final Entity entity) {
		doIncludes(entity);
		for (int i=0, size=entity.getChildEntities().size();i<size;i++){
			addEntityGroupReferencesRecursively((Entity)entity.getChildEntities().get(i));
		}		
	}

	private void doIncludes(final Entity entity){
		for (int j=0, groupsSize=entity.getIncludeGroups().size();j<groupsSize; j++){				
			IncludeEntityGroup include = (IncludeEntityGroup)entity.getIncludeGroups().get(j);				
			if (include.getId() != null){
				//is this a local merge?
				if (include.getModelUri() == null|| (include.getModelUri().equals(getMergedModel().getModelKey().getUri())) ){
					EntityGroup eg = ((Model)getMergedModel().getRoot()).findIncludeGroup(include.getId());
					addIncludeRefs(entity, eg);
				} else //external model include
					addIncludeRefs(entity, include);
			}
		}
	}
	/*
	 * Adds traits and child entities from an external entity group to the passed entity
	 * @param entity
	 * @param include
	 */
	private void addIncludeRefs(final Entity entity, final IncludeEntityGroup include) {
		IMetaDataModelContext modelContext = new MetaDataModelContextImpl(
				getMergedModel().getModelKey().getProject(), getMergedModel()
						.getModelKey().getDomain(), include.getModelUri());
		
		Model externalModel = MetaDataQueryHelper.getModel(modelContext);
		EntityGroup entityGroup = externalModel.findIncludeGroup(include.getId());
		
		addIncludeRefs(entity, entityGroup);
	}

	/*
	 * Adds traits and child entities from a local entity group to the passed entity if not already present for the entity.
	 * First 
	 * @param entity
	 * @param entityGroup
	 */
	private void addIncludeRefs(final Entity entity, final EntityGroup entityGroup) {
		if (entityGroup == null)
			return;
	
		for (int i=0, size=entityGroup.getTraits().size();i<size;i++){
			addIncludedTraitAsNecessary(entity, (Trait)entityGroup.getTraits().get(i));
		}
		
		for (int i=0, size=entityGroup.getChildEntities().size();i<size;i++){
			addIncludedEntityAsNecessary(entity, (Entity)entityGroup.getChildEntities().get(i));
		}
	}


	private void addIncludedTraitAsNecessary(final Entity entity, final Trait trait) {		
		if (!(isExistingTrait(entity, trait))){
			addTraitInternal(entity, trait);
		}
	}
	
	/*
	 * Checks to see if the entity (by id) is present as a child entity in the parent or not.
	 * If not, it will add the entity to the childEntities <u>without</u> copying.
	 * 
	 * @param parent
	 * @param entity
	 * @return
	 */
	private void addIncludedEntityAsNecessary(final Entity parent, final Entity entity) {		
		if (!(isExistingChildEntity(parent, entity))){
			addEntityInternal(parent, entity);
		}
	}


}
