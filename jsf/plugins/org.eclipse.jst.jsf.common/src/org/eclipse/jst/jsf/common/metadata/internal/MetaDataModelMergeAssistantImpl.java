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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.EntityGroup;
import org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataException;
import org.eclipse.jst.jsf.common.metadata.query.internal.HierarchicalSearchControl;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.SearchControl;
import org.eclipse.jst.jsf.common.metadata.query.internal.SimpleEntityQueryVisitorImpl;
import org.eclipse.jst.jsf.common.metadata.query.internal.SimpleResultSet;
import org.eclipse.jst.jsf.common.metadata.query.internal.SimpleTraitQueryVisitorImpl;
/**
 * Implements {@link IMetaDataModelMergeAssistant}
 * 
 * Responsible for merging source models into one.  The first model in has subsequent 
 * entities and traits added to it.   
 * 
 * TODO - make locating of existing entities and traits in the merged model more efficient
 * TODO - refactor out Taglibdomain-only aspects of include-group processing 
 *
 */
public class MetaDataModelMergeAssistantImpl implements
		IMetaDataModelMergeAssistant {
	
	private MetaDataModel mergedModel;
	private SimpleEntityQueryVisitorImpl entityVisitor;
	private SimpleTraitQueryVisitorImpl traitVisitor;
	private IMetaDataSourceModelProvider provider;
	
	/**
	 * Constructor.   Queries with search control limited to first found.
	 * @param model
	 */
	public MetaDataModelMergeAssistantImpl(final MetaDataModel model) {
		this.mergedModel = model;
		entityVisitor = new SimpleEntityQueryVisitorImpl(new HierarchicalSearchControl(1, 
			HierarchicalSearchControl.SCOPE_ALL_LEVELS));
		traitVisitor = new SimpleTraitQueryVisitorImpl(new SearchControl(1));
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

	public void setSourceModelProvider(final IMetaDataSourceModelProvider provider) {
		this.provider = provider;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant#addEntityGroup(org.eclipse.jst.jsf.common.metadata.EntityGroup)
	 */
	public void addEntityGroup(final EntityGroup entityGroup) {
		final Model model = (Model)getMergedModel().getRoot();
		if (!isExistingEntityGroup(model, entityGroup)){
			model.getEntityGroups().add(entityGroup);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant#addEntity(org.eclipse.jst.jsf.common.metadata.Entity)
	 */
	public Entity addEntity(final Entity entity) {
		Entity mmEntity = getMergedEntity(entity);
		if (mmEntity == null){
			mmEntity = addEntityAsNecessary((Entity)entity.eContainer(), entity);
			return mmEntity;
		}
		addIncludeGroupsAsNecessary(mmEntity, entity);
		return mmEntity;
	}

	public Entity getMergedEntity(final Entity queryRoot, final String entityKey){		
		Entity ret = null;
		SimpleResultSet rs = (SimpleResultSet)entityVisitor.findEntities(queryRoot, entityKey);
		try {
			if (! rs.getResults().isEmpty()) 
				ret = (Entity)rs.getResults().get(0);				
			rs.close();
		} catch (MetaDataException e) {
			JSFCommonPlugin.log(IStatus.ERROR, "Error in getMergedEntity()", e); //$NON-NLS-1$
		}
		return ret;
	}
	private void addIncludeGroupsAsNecessary(final Entity mmEntity, final Entity entity) {
		for (final Iterator it=entity.getIncludeGroups().iterator();it.hasNext();){
			final IncludeEntityGroup grp = (IncludeEntityGroup)it.next();
			boolean found = false;
			for (Iterator it2=mmEntity.getIncludeGroups().iterator();it2.hasNext();){
				final IncludeEntityGroup grp2 = (IncludeEntityGroup)it2.next();
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
	
	private boolean isExistingEntityGroup(final Model model, final EntityGroup entityGroup) {
		boolean found = false;
		for(final Iterator it=model.getEntityGroups().iterator();it.hasNext();){
			if (entityGroup.getId().equals(((EntityGroup)it.next()).getId()))
				return true;			
		}
		return found;
	}

	private Entity getExistingChildEntity(final Entity parent, final Entity entity) {
		for(final Iterator it=parent.getChildEntities().iterator();it.hasNext();){
			final Entity foundEntity = (Entity)it.next();
			if (entity.getId().equals(foundEntity.getId()))
				return foundEntity;			
		}
		return null;
	}

	private /*synchronized*/ Entity addEntityInternal(final Entity parent, final Entity entity) {
		final Copier copier = new Copier();
		final Entity mmEntity =(Entity)copier.copy(entity);
		copier.copyReferences();
		parent.getChildEntities().add(mmEntity);
		return mmEntity;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant#addTrait(org.eclipse.jst.jsf.common.metadata.Entity, org.eclipse.jst.jsf.common.metadata.Trait)
	 */
	public boolean addTrait(final Entity entity, final Trait trait) {
		final Entity returnedEntity = getMergedEntity(entity);
		if (returnedEntity != null){
			return addTraitAsNecessary(returnedEntity, trait);
		}
		return false;
	}
	

	private boolean addTraitAsNecessary(Entity mergedEntity, Trait trait) {
		final Trait mmTrait = getMergedTrait(mergedEntity, trait);
		if (mmTrait == null){			
			addTraitInternal(mergedEntity, trait);
			return true;

		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant#setMergeComplete()
	 */
	public void setMergeComplete() {
		final Model model = (Model)getMergedModel().getRoot();
		if (model != null){
			StandardModelFactory.debug(">> Begin processIncludeGroups for: "+getMergedModel().getModelContext(),StandardModelFactory.DEBUG_MD_LOAD); //$NON-NLS-1$
			
			processIncludeGroups(model);			
			
			StandardModelFactory.debug(">> End processIncludeGroups for: "+getMergedModel().getModelContext(),StandardModelFactory.DEBUG_MD_LOAD); //$NON-NLS-1$
		}		
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
		final Copier copier = new Copier();
		final Trait mmTrait =(Trait)copier.copy(trait);
		copier.copyReferences();
		parent.getTraits().add(mmTrait);
		//set the model key to know from where the trait came
		mmTrait.setSourceModelProvider(trait.getSourceModelProvider());
		return mmTrait;
	}

	/**
	 * Locates the entity in the merged model matching by id only. 
	 * If not located, this method returns null;
	 * 
	 * @param entity
	 * @return merged entity
	 */
	private Entity getMergedEntity(final Entity entity){
		if (entity instanceof Model)
			return (Entity)mergedModel.getRoot();
		
		Entity ret = null;
		final String entityKey = getIdRelativeToRoot(entity);
		final SimpleResultSet rs = (SimpleResultSet)entityVisitor.findEntities((Entity)mergedModel.getRoot(), entityKey);
		try {
			if (! rs.getResults().isEmpty()) 
				ret = (Entity)rs.getResults().get(0);				
			rs.close();
		} catch (MetaDataException e) {
			JSFCommonPlugin.log(IStatus.ERROR, "Error in getMergedEntity()", e); //$NON-NLS-1$
		}
		return ret;
	}
	
	private String getIdRelativeToRoot(final Entity entity) {
		Entity e = entity;
		final StringBuffer buf = new StringBuffer();
		while (e.eContainer() != null){
			buf.insert(0, e.getId());
			if (e.eContainer()!=null && e.eContainer().eContainer() != null)
				buf.insert(0,"/"); //$NON-NLS-1$
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
	public Trait getMergedTrait(final Entity entity, final Trait trait){
		final SimpleResultSet rs = (SimpleResultSet)traitVisitor.findTraits(entity, trait.getId());
		Trait ret = null;
		try {
			if (! rs.getResults().isEmpty()) 
				ret = (Trait)rs.getResults().get(0);				
			rs.close();
		} catch (MetaDataException e) {
			JSFCommonPlugin.log(IStatus.ERROR, "Error in getMergedTrait()", e); //$NON-NLS-1$
		}
		return ret;
	}

	private void processIncludeGroups(final Model root) {
		addEntityGroupReferencesRecursively(root);
	}

	private void addEntityGroupReferencesRecursively(final Entity entity) {
		doIncludes(entity);
		for (int i=0, size=entity.getChildEntities().size();i<size;i++){
			addEntityGroupReferencesRecursively((Entity)entity.getChildEntities().get(i));
		}		
	}

	private void doIncludes(final Entity entity){
		for (int j=0, groupsSize=entity.getIncludeGroups().size();j<groupsSize; j++){				
			final IncludeEntityGroup include = (IncludeEntityGroup)entity.getIncludeGroups().get(j);				
			if (include.getId() != null){
				//is this a local merge?
				if (include.getModelUri() == null||
						(include.getModelUri()
							.equals(getMergedModel()
								.getModelContext().getModelIdentifier())) ){
					final EntityGroup eg = ((Model)getMergedModel().getRoot()).findIncludeGroup(include.getId());
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
		final IMetaDataModelContext modelContext = getMergedModel().getModelContext();
		final IMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(modelContext);
		final Model externalModel = query.getQueryHelper().getModel(include.getModelUri());
		if (externalModel != null){
			final EntityGroup entityGroup = externalModel.findIncludeGroup(include.getId());		
			addIncludeRefs(entity, entityGroup);
		}
		else {
			JSFCommonPlugin.log(IStatus.ERROR, "Unable to load external metadata model refs for "+modelContext.getModelIdentifier() //$NON-NLS-1$
					+ " into "+ include.getModelUri()); //$NON-NLS-1$
		}
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
			addTrait(entity, (Trait)entityGroup.getTraits().get(i));
		}
		
		for (int i=0, size=entityGroup.getChildEntities().size();i<size;i++){
			traverseAndAddIncludes(entity, (Entity)entityGroup.getChildEntities().get(i));
		}
	}
	
	private void traverseAndAddIncludes(final Entity parent, final Entity entity){
		final Entity mergedEntity = addIncludedEntityAsNecessary(parent, entity);
		
		for (final Iterator/*<Trait>*/ it=entity.getTraits().iterator();it.hasNext();){
			final Trait trait = (Trait)it.next();
			addTraitAsNecessary(mergedEntity, trait);
		}
		
		for (final Iterator/*<EntityKey>*/ it=entity.getChildEntities().iterator();it.hasNext();){
			final Entity e = (Entity)it.next();
			traverseAndAddIncludes(mergedEntity, e);//add as normal
		}
		
	}
		
	/*
	 * Checks to see if the entity (by id) is present as a child entity in the parent or not.
	 * If not, it will add the entity to the childEntities without copying.
	 * 
	 * @param parent
	 * @param entity
	 * @return Entity
	 */
	private Entity addIncludedEntityAsNecessary(final Entity parent, final Entity entity) {		
		Entity mergedEntity = getExistingChildEntity(parent, entity);
		if (mergedEntity == null){
			mergedEntity = addEntityInternal(parent, entity);
		}
		return mergedEntity;
	}

}
