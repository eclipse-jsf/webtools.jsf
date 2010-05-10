package org.eclipse.jst.jsf.core.metadata.internal;

import java.util.Iterator;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.MetadataFactory;
import org.eclipse.jst.jsf.common.metadata.MetadataPackage;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataTranslator;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.BooleanValue;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.StringValue;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.TraitTypesFactory;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttribute;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;

/**
 * Translates some pieces of a {@link Namespace} metadata into standard {@link Entity} and {@link Trait} metadata
 *
 */
public class NamespaceMetaDataTranslator  		
		implements IMetaDataTranslator  {
	/**
	 * {@link IMetaDataModelMergeAssistant} to use
	 */
	private IMetaDataModelMergeAssistant _assistant;

	public void translate(final IMetaDataModelMergeAssistant assistant) {
		_assistant = assistant;
		final Namespace ns = ((INamespaceModelProvider)_assistant.getSourceModelProvider()).getNamespace();
		doTranslate(ns);
	}

	public boolean canTranslate(final IMetaDataSourceModelProvider modelProvider) {
		if (modelProvider != null &&
				modelProvider instanceof INamespaceModelProvider)
			return true;
		return false;
	}

	/**
	 * Set the assistant to use during the translation.   Must be called prior to doTransalate(doc);
	 * @param assistant
	 */
	protected void setAssistant(final IMetaDataModelMergeAssistant assistant){
		_assistant = assistant;
	}
	
	/**
	 * Transforms the Namespace into entities and traits of a standard metadata model 
	 * using the assistant that must be set before this call.
	 * @param tagLib
	 */
	protected void doTranslate(final Namespace tagLib){
		createTags(tagLib);
		createTagfileTraits(tagLib);		
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
	 * @param tagLib
	 */
	protected void createTags(final Namespace tagLib) {
		for (final ITagElement tag : tagLib.getViewElements()){
			Entity entity = findTagEntity(tag.getName());
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
	protected String getTagNodeName(final ITagElement tag) {
		return tag.getName();
	}

	/**
	 * Sets the standard traits for a tag entity from the element declaration
	 * @param tag
	 * @param entity
	 */
	protected void setTagEntityTraits(final ITagElement tag, final Entity entity) {
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
	protected void createAttributeEntities(final Entity tagEntity, final 
			ITagElement tag) {
		
		for (final ITagAttribute tagAttr : tag.getAttributes().values()){					
			Entity attr = findAttributeEntityForTagEntity(tagEntity, tagAttr.getName());
			if (attr == null) {
				attr = MetadataFactory.eINSTANCE.createEntity();
				attr.setId(tagAttr.getName());
				tagEntity.getChildEntities().add(attr);
			}
			createAttributeTraits(attr, tagAttr);
		}
			
		
	}

	/**
	 * @param attr
	 * @param tagAttr
	 */
	protected void createAttributeTraits(final Entity attr,
			final ITagAttribute tagAttr) {
		
		createSimpleStringEntityTraitIfNecessary(attr, "description", getTagAttributeDescription(tagAttr));	 //$NON-NLS-1$
		createSimpleBooleanObjectEntityTraitIfNecessary(attr, "required", getTagAttributeIsRequired(tagAttr)); //$NON-NLS-1$
		createSimpleStringEntityTraitIfNecessary(attr, "default-value", getTagAttributeDefaultValue(tagAttr)); //$NON-NLS-1$
	}
	
	/**
	 * @param tagAttr
	 * @return null.   subclass should override if ITagAttribute has the metadata.
	 */
	protected String getTagAttributeDescription(final ITagAttribute tagAttr) {return tagAttr.getDescription();}
	
	/**
	 * @param tagAttr
	 * @return false.   subclass should override if ITagAttribute has the metadata.
	 */
	protected boolean getTagAttributeIsRequired(final ITagAttribute tagAttr) {return tagAttr.isRequired();}

	/**
	 * @param tagAttr
	 * @return null.   subclass should override if ITagAttribute has the metadata.
	 */
	protected String getTagAttributeDefaultValue(final ITagAttribute tagAttr) {return null;}

	/**
	 * @param tag
	 * @return false.   subclass should override if ITagElement has the metadata.
	 */
	protected boolean getTagIsHidden(final ITagElement tag) {return false;}

	/**
	 * @param tag
	 * @return false.   subclass should override if ITagElement has the metadata.
	 */
	protected boolean getTagIsExpert(final ITagElement tag) {return false;}

	/**
	 * @param tag
	 * @return null.   subclass should override if ITagElement has the metadata.
	 */
	protected String getTagLargeIcon(final ITagElement tag) {return null;}

	/**
	 * @param tag
	 * @return null.   subclass should override if ITagElement has the metadata.
	 */
	protected String getTagSmallIcon(ITagElement tag) {return null;}

	/**
	 * @param tag
	 * @return null.   subclass should override if ITagElement has the metadata.
	 */
	protected String getTagDescription(final ITagElement tag) {return null;}

	/**
	 * @param tag
	 * @return tag.getElementName()
	 */
	protected String getTagDisplayName(ITagElement tag) {return tag.getName();}

	/**
	 * Creates standard traits for tag file entity from CMDocument metadata
	 * @param ns
	 */
	protected void createTagfileTraits(final Namespace ns) {
		Model model = getMergedModel();

		createSimpleStringEntityTraitIfNecessary(model, "display-label", getNamespaceDisplayLabel()); //$NON-NLS-1$
		createSimpleStringEntityTraitIfNecessary(model, "description", getNamespaceDescription()); //$NON-NLS-1$
		createSimpleStringEntityTraitIfNecessary(model, "default-prefix", getNamespaceDefaultPrefix()); //$NON-NLS-1$
		createSimpleBooleanObjectEntityTraitIfNecessary(model, "expert", getNamespaceExpert()); //$NON-NLS-1$
		createSimpleBooleanObjectEntityTraitIfNecessary(model, "hidden", getNamespaceHidden()); //$NON-NLS-1$
		
	}
	
	/**
	 * @return the display label to use for this model.  Subclasses should override if model has the meta data.
	 */
	protected String getNamespaceDisplayLabel(){
		return getMergedModel().getId();
	}
	
	/**
	 * @return default prefix to use for tags.  Returns null. Subclasses should override if model has the meta data.
	 */
	protected String getNamespaceDefaultPrefix(){
		return null;
	}

	/**
	 * @return description to use.  Default is the URI.  Subclasses should override if model has the meta data.
	 */
	protected String getNamespaceDescription(){
		return getMergedModel().getId();
	}

	/**
	 * @return false.  Subclasses should override if model has the meta data.
	 */
	protected boolean getNamespaceExpert() { return false;}

	/**
	 * @return false.  Subclasses should override if model has the meta data.
	 */
	protected boolean getNamespaceHidden() {
//		Model model = (Model)_assistant.getMergedModel().getRoot();
//		if (model != null) {
//			return model.getChildEntities().size() == 0 ? true : false;
//		}
		return false;	
	}

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
		final Model entity = getFactory().createModel();
		entity.setId(_assistant.getMergedModel().getModelContext().getModelIdentifier());
		return entity;
	}	

	private MetadataFactory getFactory(){
		return (MetadataFactory) MetadataPackage.eINSTANCE.getEFactoryInstance(); 
	}

}
