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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Model;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.SearchControl;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.SimpleMetaDataQueryVisitorImpl;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.SimpleResultSet;
/**
 * Implements {@link IMetaDataModelMergeAssistant}
 * 
 * Responsible for merging source models into one.  The first model in has subsequent 
 * entities and traits added to it.   
 * 
 * TODO - make locating of existing entities and traits more efficient
 *
 */
public class MetaDataModelMergeAssistantImpl implements
		IMetaDataModelMergeAssistant {
	
	private MetaDataModel mergedModel;
	private Object source;
	private Copier copier;
	private SimpleMetaDataQueryVisitorImpl visitor;
	
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
	public Object getSourceModel() {
		return source;
	}

	public void setSourceModel(Object source) {
//		_sourceModelUsed = false;
		this.source = source;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant#addEntity(org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity)
	 */
	public boolean addEntity(Entity entity) {
		Entity mmEntity = getMergedEntity(entity);
		if (mmEntity == null){
			addEntityAsNecessary(entity.getParent(), entity);
//			_sourceModelUsed = true;
			return true;
		}
		return false;
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
				mmParent = addEntityAsNecessary(entity.getParent(), entity);
			}
		}
		if (mmParent != null){
			mmEntity =(Entity)copier.copy((EObject)entity);
			mmParent.getChildEntities().add(mmEntity);
			return mmEntity;
		}
		return mmEntity;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant#addTrait(org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity, org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait)
	 */
	public boolean addTrait(Entity entity, Trait trait) {
		Entity returnedEntity = getMergedEntity(entity);
		if (returnedEntity != null){
			Trait mmTrait = getMergedTrait(returnedEntity, trait);
			if (mmTrait == null){			
				mmTrait = addTraitInternal(returnedEntity, trait);
				return true;

			}
		}
		return false;
	}

	/**
	 * Copies the passed trait and adds it to the merged entity.
	 * The source model of the trait is then set on the trait so that 
	 * the trait can know where it came from.
	 * 
	 * @param parent
	 * @param trait
	 * @return merged Trait
	 */
	private Trait addTraitInternal(final Entity parent, final Trait trait) {		
		Trait mmTrait =(Trait)copier.copy((EObject)trait);
		parent.getTraits().add(mmTrait);
		//set the model key to know from where the trait came
		mmTrait.setSourceModel(trait.getSourceModel());
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
		if (rs.size() >0)
			ret = (Entity)rs.nextElement();
		
		rs.close();
		return ret;
	}
	
	private String getIdRelativeToRoot(final Entity entity) {
		Entity e = entity;
		StringBuffer buf = new StringBuffer();
		while (e.getParent() != null){
			buf.insert(0,e.getId());
			if (e.getParent()!=null && e.getParent().getParent() != null)
				buf.insert(0,"/");
			e = e.getParent();
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
		if (rs.size() >0)
			ret = (Trait)rs.nextElement();
		
		rs.close();
		return ret;
	}
}
