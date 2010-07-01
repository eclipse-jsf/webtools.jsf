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
package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.Iterator;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.MetadataFactory;
import org.eclipse.jst.jsf.common.metadata.MetadataPackage;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.BooleanValue;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.StringValue;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.TraitTypesFactory;
import org.eclipse.wst.xml.core.internal.contentmodel.CMAttributeDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;

/**
 * Abstract class that the taglib domain translators use to 
 * convert the CM model to a standard meta data model
 *
 */
public abstract class AbstractTagLibDomainContentModelMetaDataTranslator {
	
	/**
	 * {@link IMetaDataModelMergeAssistant} to use
	 */
	protected IMetaDataModelMergeAssistant _assistant;

	/**
	 * Set the assistant to use during the translation.   Must be called prior to doTransalate(doc);
	 * @param assistant
	 */
	protected void setAssistant(IMetaDataModelMergeAssistant assistant){
		_assistant = assistant;
	}
	
	/**
	 * Transforms the CMDocument into entities and traits of a standard metadata model 
	 * using the assistant that must be set before this call.
	 * @param doc
	 */
	protected void doTranslate(CMDocument doc){
		createTags(doc);
		createTagfileTraits(doc);		
	}
	
	/**
	 * @param entity
	 * @param key
	 * @param value
	 */
	protected void createSimpleStringEntityTraitIfNecessary(final Entity entity, final String key,
			final String value) {
		Trait t = findTraitOnEntityById(entity, key);
		if (t == null){
			t = internalCreateTrait(entity, key);

			StringValue val = TraitTypesFactory.eINSTANCE.createStringValue();			
			val.setValue(value);
			
			t.setValue(val);
		}
	}

	/**
	 * @param entity
	 * @param key
	 * @param value
	 */
	protected void createSimpleBooleanObjectEntityTraitIfNecessary(final Entity entity,
			String key, boolean value) {
		
		Trait t = findTraitOnEntityById(entity, key);
		if (t == null){
			t = internalCreateTrait(entity, key);

			BooleanValue val = TraitTypesFactory.eINSTANCE.createBooleanValue();		
			val.setTrue(value);
			
			t.setValue(val);
		}
		
	}
	
	/**
	 * @param entity
	 * @param key
	 * @return Trait
	 */
	protected Trait internalCreateTrait(final Entity entity, final String key) {
		Trait t = MetadataFactory.eINSTANCE.createTrait();
		t.setId(key);
		t.setSourceModelProvider(_assistant.getSourceModelProvider());
		entity.getTraits().add(t);
		return t;
	}
	
	/**
	 * @param entity
	 * @param key
	 * @return Trait
	 */
	protected Trait findTraitOnEntityById(final Entity entity, final String key) {
		for (Iterator it=entity.getTraits().iterator();it.hasNext();){
			Trait t = (Trait)it.next();
			if (key.equals(t.getId()))
				return t;
		}
		return null;
	}

	/**
	 * @param nodeName
	 * @return Entity
	 */
	protected Entity findTagEntity(final String nodeName) {
		for (Iterator it=getMergedModel().getChildEntities().iterator();it.hasNext();){
			Entity entity = (Entity)it.next();
			if (nodeName.equals(entity.getId()))
				return entity;
		}
		return null;
	}

	/**
	 * @param tag
	 * @param attributeName
	 * @return Attribute entity for supplied attribute name and given Tag entity.  Will return null if not found.
	 */
	protected Entity findAttributeEntityForTagEntity(final Entity tag, final String attributeName) {
		for (Iterator it=tag.getChildEntities().iterator();it.hasNext();){
			Entity attr = (Entity)it.next();
			if (attributeName.equals(attr.getId()))
				return attr;
		}
		return null;
	}
	/**
	 * Create entities for tags
	 * @param doc
	 */
	protected void createTags(final CMDocument doc) {
		for (Iterator it=doc.getElements().iterator();it.hasNext();){
			CMElementDeclaration tag = (CMElementDeclaration)it.next();
			Entity entity = findTagEntity(tag.getNodeName());
			if (entity == null){
				entity = MetadataFactory.eINSTANCE.createEntity();
				entity.setId(getTagNodeName(tag));
				entity.setType("tag"); //$NON-NLS-1$
				getMergedModel().getChildEntities().add(entity);
			}
			setTagEntityTraits(tag, entity);
		}
		
	}
	
	/**
	 * @param tag
	 * @return tag node name
	 */
	protected String getTagNodeName(CMElementDeclaration tag) {
		return tag.getNodeName();
	}

	/**
	 * Sets the standard traits for a tag entity from the element declaration
	 * @param tag
	 * @param entity
	 */
	protected void setTagEntityTraits(CMElementDeclaration tag, Entity entity) {
		createSimpleStringEntityTraitIfNecessary(entity, "display-label", getTagDisplayName(tag)); //$NON-NLS-1$
		createSimpleStringEntityTraitIfNecessary(entity, "description", getTagDescription(tag));	 //$NON-NLS-1$
		createSimpleStringEntityTraitIfNecessary(entity, "small-icon", getTagSmallIcon(tag)); //$NON-NLS-1$
		createSimpleStringEntityTraitIfNecessary(entity, "large-icon", getTagLargeIcon(tag)); //$NON-NLS-1$
		createSimpleBooleanObjectEntityTraitIfNecessary(entity, "expert", getTagIsExpert(tag)); //$NON-NLS-1$
		createSimpleBooleanObjectEntityTraitIfNecessary(entity, "hidden", getTagIsHidden(tag)); //$NON-NLS-1$
		
		createAttributeEntities(entity, tag);
	}
	
	/**
	 * @param tagEntity
	 * @param tag
	 */
	protected void createAttributeEntities(Entity tagEntity,
			CMElementDeclaration tag) {
		
		for (Iterator it=tag.getAttributes().iterator();it.hasNext();){
			CMAttributeDeclaration cmAttr = (CMAttributeDeclaration)it.next();			
			Entity attr = findAttributeEntityForTagEntity(tagEntity, cmAttr.getAttrName());
			if (attr == null) {
				attr = MetadataFactory.eINSTANCE.createEntity();
				attr.setId(cmAttr.getAttrName());
				tagEntity.getChildEntities().add(attr);
			}
			createAttributeTraits(attr, cmAttr);
		}
			
		
	}

	/**
	 * @param attr
	 * @param cmAttr
	 */
	protected void createAttributeTraits(Entity attr,
			CMAttributeDeclaration cmAttr) {
		
		createSimpleStringEntityTraitIfNecessary(attr, "description", getTagAttributeDescription(cmAttr));	 //$NON-NLS-1$
		createSimpleBooleanObjectEntityTraitIfNecessary(attr, "required", getTagAttributeIsRequired(cmAttr)); //$NON-NLS-1$
		createSimpleStringEntityTraitIfNecessary(attr, "default-value", getTagAttributeDefaultValue(cmAttr)); //$NON-NLS-1$
	}
	
	/**
	 * @param cmAttr
	 * @return null.   subclass should override if CMAttributeDeclaration has the metadata.
	 */
	protected String getTagAttributeDescription(CMAttributeDeclaration cmAttr) {return null;}
	
	/**
	 * @param cmAttr
	 * @return false.   subclass should override if CMAttributeDeclaration has the metadata.
	 */
	protected boolean getTagAttributeIsRequired(CMAttributeDeclaration cmAttr) {return cmAttr.getUsage() == CMAttributeDeclaration.REQUIRED;}

	/**
	 * @param cmAttr
	 * @return null.   subclass should override if CMAttributeDeclaration has the metadata.
	 */
	protected String getTagAttributeDefaultValue(CMAttributeDeclaration cmAttr) {return null;}

	/**
	 * @param tag
	 * @return false.   subclass should override if CMElementDeclaration has the metadata.
	 */
	protected boolean getTagIsHidden(CMElementDeclaration tag) {return false;}

	/**
	 * @param tag
	 * @return false.   subclass should override if CMElementDeclaration has the metadata.
	 */
	protected boolean getTagIsExpert(CMElementDeclaration tag) {return false;}

	/**
	 * @param tag
	 * @return null.   subclass should override if CMElementDeclaration has the metadata.
	 */
	protected String getTagLargeIcon(CMElementDeclaration tag) {return null;}

	/**
	 * @param tag
	 * @return null.   subclass should override if CMElementDeclaration has the metadata.
	 */
	protected String getTagSmallIcon(CMElementDeclaration tag) {return null;}

	/**
	 * @param tag
	 * @return null.   subclass should override if CMElementDeclaration has the metadata.
	 */
	protected String getTagDescription(CMElementDeclaration tag) {return null;}

	/**
	 * @param tag
	 * @return tag.getElementName()
	 */
	protected String getTagDisplayName(CMElementDeclaration tag) {return tag.getElementName();}

	/**
	 * Creates standard traits for tag file entity from CMDocument metadata
	 * @param doc
	 */
	protected void createTagfileTraits(CMDocument doc) {
		Model model = getMergedModel();

		createSimpleStringEntityTraitIfNecessary(model, "display-label", getURIDisplayLabel()); //$NON-NLS-1$
		createSimpleStringEntityTraitIfNecessary(model, "description", getURIDescription()); //$NON-NLS-1$
		createSimpleStringEntityTraitIfNecessary(model, "default-prefix", getURIDefaultPrefix()); //$NON-NLS-1$
		createSimpleBooleanObjectEntityTraitIfNecessary(model, "expert", getURIExpert()); //$NON-NLS-1$
		createSimpleBooleanObjectEntityTraitIfNecessary(model, "hidden", getURIHidden()); //$NON-NLS-1$
		
	}
	
	/**
	 * @return the display label to use for this model.  Subclasses should override if model has the meta data.
	 */
	protected String getURIDisplayLabel(){
		return getMergedModel().getId();
	}
	
	/**
	 * @return default prefix to use for tags.  Returns null. Subclasses should override if model has the meta data.
	 */
	protected String getURIDefaultPrefix(){
		return null;
	}

	/**
	 * @return description to use.  Default is the URI.  Subclasses should override if model has the meta data.
	 */
	protected String getURIDescription(){
		return getMergedModel().getId();
	}

	/**
	 * @return false.  Subclasses should override if model has the meta data.
	 */
	protected boolean getURIExpert() { return false;}

	/**
	 * @return false.  Subclasses should override if model has the meta data.
	 */
	protected boolean getURIHidden() {return false;	}

	/**
	 * @return a model of all entities and traits
	 */
	protected Model getMergedModel() {
		Model model = (Model)_assistant.getMergedModel().getRoot();
		if (model == null){
			//need to create model key object
			Model tld = createTLDModel();
			_assistant.getMergedModel().setRoot(tld);
			model = (Model)_assistant.getMergedModel().getRoot();
		}
		return model;
	}

	/**
	 * @return new model 
	 */
	protected Model createTLDModel() {
		Model entity = getFactory().createModel();
		entity.setId(_assistant.getMergedModel().getModelKey().getUri());
		return entity;
	}	
	
	/**
	 * @return MetadataFactory instance
	 */
	protected MetadataFactory getFactory(){
		return (MetadataFactory) MetadataPackage.eINSTANCE.getEFactoryInstance(); 
	}
	
	/**
	 * @return CMDocument being used for the current translate call
	 */
	protected CMDocument getSourceModel() {
		return (CMDocument)_assistant.getSourceModelProvider().getSourceModel();
	}
}
