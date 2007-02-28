package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.Iterator;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataFactory;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Model;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;

public abstract class AbstractTagLibDomainContentModelMetaDataTranslator {
	
	protected IMetaDataModelMergeAssistant _assistant;

	protected void setAssistant(IMetaDataModelMergeAssistant assistant){
		_assistant = assistant;
	}
	
	protected void doTranslate(CMDocument doc){
		createTags(doc);
		createTagfileTraits(doc);		
	}
	
	protected void createSimpleStringEntityTraitIfNecessary(final Entity entity, final String key,
			final String value) {
		Trait t = findTraitOnEntityById(entity, key);
		if (t == null){
			t = internalCreateTrait(entity, key);

			SimpleAnyType val = XMLTypeFactory.eINSTANCE.createSimpleAnyType();
			val.setInstanceType(EcorePackage.eINSTANCE.getEString());
			val.setValue(value);
			
			t.setValue(val);
		}
	}

	protected void createSimpleBooleanObjectEntityTraitIfNecessary(final Entity entity,
			String key, boolean value) {
		
		Trait t = findTraitOnEntityById(entity, key);
		if (t == null){
			t = internalCreateTrait(entity, key);

			SimpleAnyType val = XMLTypeFactory.eINSTANCE.createSimpleAnyType();
			val.setInstanceType(EcorePackage.eINSTANCE.getEBoolean());
			val.setRawValue(value == true ? "true" : "false");
			
			t.setValue(val);
		}
		
	}
	
	protected Trait internalCreateTrait(final Entity entity, final String key) {
		Trait t = MetadataFactory.eINSTANCE.createTrait();
		t.setId(key);
		t.setSourceModelProvider(_assistant.getSourceModelProvider());
		entity.getTraits().add(t);
		return t;
	}
	protected Trait findTraitOnEntityById(final Entity entity, final String key) {
		for (Iterator it=entity.getTraits().iterator();it.hasNext();){
			Trait t = (Trait)it.next();
			if (key.equals(t.getId()))
				return t;
		}
		return null;
	}

	protected Entity findTagEntity(final String nodeName) {
		for (Iterator it=getMergedModel().getChildEntities().iterator();it.hasNext();){
			Entity entity = (Entity)it.next();
			if (nodeName.equals(entity.getId()))
				return entity;
		}
		return null;
	}

	protected void createTags(final CMDocument doc) {
		for (Iterator it=doc.getElements().iterator();it.hasNext();){
			CMElementDeclaration tag = (CMElementDeclaration)it.next();
			Entity entity = findTagEntity(tag.getNodeName());
			if (entity == null){
				entity = MetadataFactory.eINSTANCE.createEntity();
				entity.setId(tag.getNodeName());
				entity.setType("tag");
				getMergedModel().getChildEntities().add(entity);
			}
			setTagEntityTraits(tag, entity);
		}
		
	}
	
	protected void setTagEntityTraits(CMElementDeclaration tag, Entity entity) {
		createSimpleStringEntityTraitIfNecessary(entity, "display-label", getTagDisplayName(tag));
		createSimpleStringEntityTraitIfNecessary(entity, "description", getTagDescription(tag));	
		createSimpleStringEntityTraitIfNecessary(entity, "small-icon", getTagSmallIcon(tag));
		createSimpleStringEntityTraitIfNecessary(entity, "large-icon", getTagLargeIcon(tag));
		createSimpleBooleanObjectEntityTraitIfNecessary(entity, "expert", getTagIsExpert(tag));
		createSimpleBooleanObjectEntityTraitIfNecessary(entity, "hidden", getTagIsHidden(tag));
		
//		createRequiredAttrTraits(entity, tag);
	}
	
	protected boolean getTagIsHidden(CMElementDeclaration tag) {return false;}

	protected boolean getTagIsExpert(CMElementDeclaration tag) {return false;}

	protected String getTagLargeIcon(CMElementDeclaration tag) {return null;}

	protected String getTagSmallIcon(CMElementDeclaration tag) {return null;}

	protected String getTagDescription(CMElementDeclaration tag) {return null;}

	protected String getTagDisplayName(CMElementDeclaration tag) {return tag.getElementName();}

	protected void createTagfileTraits(CMDocument doc) {
		Model model = getMergedModel();

		createSimpleStringEntityTraitIfNecessary(model, "display-label", getURIDisplayLabel());			
		createSimpleStringEntityTraitIfNecessary(model, "description", getURIDescription());
		createSimpleStringEntityTraitIfNecessary(model, "default-prefix", getURIDefaultPrefix());
		createSimpleBooleanObjectEntityTraitIfNecessary(model, "expert", getURIExpert());
		createSimpleBooleanObjectEntityTraitIfNecessary(model, "hidden", getURIHidden());
		
	}
	
	protected String getURIDisplayLabel(){
		return getMergedModel().getId();
	}
	
	protected String getURIDefaultPrefix(){
		return null;
	}

	protected String getURIDescription(){
		return getMergedModel().getId();
	}

	protected boolean getURIExpert() { return false;}

	protected boolean getURIHidden() {return false;	}

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

//	protected void createTagTraits(IMetaDataModelMergeAssistant assistant,
//			ITaglibDescriptor desc) {
//		Model tld = (Model)assistant.getMergedModel().getRoot();
//	}

	protected Model createTLDModel() {
		Model entity = getFactory().createModel();
		entity.setId(_assistant.getMergedModel().getModelKey().getUri());
		return entity;
	}	
	
	protected MetadataFactory getFactory(){
		return (MetadataFactory) MetadataPackage.eINSTANCE.getEFactoryInstance(); 
	}
	
	protected CMDocument getSourceModel() {
		return (CMDocument)_assistant.getSourceModelProvider().getSourceModel();
	}
}
