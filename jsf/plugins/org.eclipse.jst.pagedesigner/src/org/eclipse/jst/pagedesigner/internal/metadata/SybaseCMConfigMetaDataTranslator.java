/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.internal.metadata;

import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.MetadataFactory;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataTranslator;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.ListOfValues;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.TraitTypesFactory;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor;
import org.eclipse.jst.pagedesigner.meta.IAttributeRuntimeValueType;
import org.eclipse.jst.pagedesigner.meta.IElementDescriptor;
import org.eclipse.jst.pagedesigner.meta.OLDIValueType;
import org.eclipse.jst.pagedesigner.meta.internal.SimpleCMRegistry;

/**
 * Translates Sybase MD into JSF common MD and attribute value type system.
 * 
 * Supplied to ease migration to JSF Common meta data by allowing current MD into JSF common md format.
 * 
 * @deprecated this will be disappearing along with all of the Sybase meta data 
 * and supporting classes before Ganymede release
 *
 */
public class SybaseCMConfigMetaDataTranslator implements IMetaDataTranslator {
	private static final String ATTR_VAL_RUNTIME_TYPE = "attribute-value-runtime-type";
	private IMetaDataModelMergeAssistant _assistant;

	public boolean canTranslate(IMetaDataSourceModelProvider modelProvider) {		
		if (modelProvider.getSourceModel() != null && 
				modelProvider.getSourceModel() instanceof SimpleCMRegistry)
			return true;
		return false;
	}
	
	public void translate(IMetaDataModelMergeAssistant assistant) {
		_assistant = assistant;
		SimpleCMRegistry reg = (SimpleCMRegistry)assistant.getSourceModelProvider().getSourceModel();
		traverseCMRegistryModel(reg);
	}

	private void traverseCMRegistryModel(SimpleCMRegistry reg){
		for (Iterator it=reg.getMap().entrySet().iterator(); it.hasNext();){
		    Map.Entry entry = (Map.Entry)it.next();			
			IElementDescriptor tagElem = (IElementDescriptor)entry.getValue();
			findOrCreateTag(tagElem);
		}
	}
	
	private void findOrCreateTag(IElementDescriptor tagElem) {
		Entity tag =_assistant.getMergedEntity(getMergedModel(), tagElem.getTagName());
		if (tag == null){
			tag = internalCreateTagEntity(tagElem);
			getMergedModel().getChildEntities().add(tag);
		}
		createTagTraits(tag, tagElem);
		createAttributeEntities(tag, tagElem);
	}

	private void createAttributeEntities(Entity tag, IElementDescriptor tagElem) {
		for (IAttributeDescriptor attrDesc : tagElem.getAttributeDescriptors()){
			Entity attr =_assistant.getMergedEntity(tag, attrDesc.getAttributeName());
			if (attr == null) {
				attr = internalCreateAttributeEntity(attrDesc);
				tag.getChildEntities().add(attr);
			}
			createAttributeTraits(attr, attrDesc);
		}
		if (tagElem.getReference() != null) {
			//create attributes from referenced tag
			createAttributeEntities(tag,tagElem.getReference());
		}
	}

	private void createAttributeTraits(Entity attr,
			IAttributeDescriptor attrDesc) {
		
		//convert ValueType to runtime-attrib-values
		convertValueType(attr, attrDesc);

		createTraitAndSimpleAnyTypeValue(attr, "display-label", attrDesc.getLabelString());
		createTraitAndSimpleAnyTypeValue(attr, "description", attrDesc.getDescription());
		createTraitAndSimpleAnyTypeValue(attr, "default-value", attrDesc.getDefaultValue());
		createTraitAndSimpleAnyTypeValue(attr, IPropertyPageDescriptor.PROP_DESC_CATEGORY, attrDesc.getCategory());
		createTraitAndSimpleAnyTypeValue(attr, "required", String.valueOf(attrDesc.isRequired()));
		
		
		
	}

	private void createTraitAndSimpleAnyTypeValue(Entity attr, String traitKey,
			String traitValue) {
		
		if (traitValue != null && traitValue.trim().length() > 0) {
			Trait trait = createTrait(attr, traitKey);
			setTraitValueUsingSimpleAnyType(trait, traitValue);	
			attr.getTraits().add(trait);
		}
	}

	private void convertValueType(Entity attr, IAttributeDescriptor attrDesc) {
		if (hasTrait(attr, ATTR_VAL_RUNTIME_TYPE)){
			return;
		}
		String rtType = null;
		String valType = attrDesc.getValueType();
		Trait trait = createTrait(attr, ATTR_VAL_RUNTIME_TYPE);
		if (OLDIValueType.ENUMERATED.equals(valType)){
			if ("lang".equalsIgnoreCase(attrDesc.getAttributeName()))
				rtType = IAttributeRuntimeValueType.LANGUAGECODE;
//			else if ()
			else {
				rtType = IAttributeRuntimeValueType.STRING;
				createPossibleValuesAndValidValueTraits(attr, attrDesc);
			}
		}
		else if (OLDIValueType.BOOLEAN.equals(valType)) {
			rtType = IAttributeRuntimeValueType.BOOLEAN;
		}
		else if (OLDIValueType.CLASSNAME.equals(valType)) {
			rtType = IAttributeRuntimeValueType.JAVACLASS;
			//"superType=I:javax.faces.event.ActionListener" 
			int colonIndex = attrDesc.getTypeParameter().lastIndexOf(":");
			String superTypeValue= attrDesc.getTypeParameter().substring(colonIndex+1);
			String superTypeType= attrDesc.getTypeParameter().substring(colonIndex-1,colonIndex);
			if (superTypeType.equalsIgnoreCase("I"))
				createTraitAndSimpleAnyTypeValue(attr, "valid-interfaces", superTypeValue);
			else
				createTraitAndSimpleAnyTypeValue(attr, "valid-superclass", superTypeValue);
		}
		else if (OLDIValueType.CLASSPATH_RESOURCE.equals(valType)) {
			rtType = IAttributeRuntimeValueType.RESOURCEBUNDLE;
		}
		else if (OLDIValueType.COLOR.equals(valType)) {
			rtType = IAttributeRuntimeValueType.COLOR;
		}
		else if (OLDIValueType.CSSCLASS.equals(valType)) {
			rtType = IAttributeRuntimeValueType.CSSCLASS;
		}
		else if (OLDIValueType.CSSID.equals(valType)) {
			rtType = IAttributeRuntimeValueType.CSSID;
		}
		else if (OLDIValueType.CSSSTYLE.equals(valType)) {
			rtType = IAttributeRuntimeValueType.CSSSTYLE;
		}
		else if (OLDIValueType.CURRENCYCODE.equals(valType)) {
			rtType = IAttributeRuntimeValueType.CURRENCYCODE;
		}
		else if (OLDIValueType.JAVASCRIPT.equals(valType)) {
			rtType = IAttributeRuntimeValueType.SCRIPT;
		}
		else if (OLDIValueType.LINK.equals(valType)) {
			rtType = IAttributeRuntimeValueType.LINK;
		}
		else if (OLDIValueType.LOCALE.equals(valType)) {
			rtType = IAttributeRuntimeValueType.LOCALE;
		}
		else if (OLDIValueType.METHODBINDING.equals(valType)) {
			rtType = IAttributeRuntimeValueType.METHODBINDING;
			if (attr.getId().equals("validator"))			
				createValidatorParams(attr);
			else if (attr.getId().equals("actionListener")){
				Trait t = createTrait(attr, "runtime-param-types");
				t.setValue(createAndSetSimpleAnyTypeValue("javax.faces.event.ActionEvent"));
				attr.getTraits().add(t);
			}
			else if (attr.getId().equals("valueChangeListener")){
				Trait t = createTrait(attr, "runtime-param-types");
				t.setValue(createAndSetSimpleAnyTypeValue("javax.faces.event.ValueChangeEvent"));
				attr.getTraits().add(t);
			}
			Trait t2 = createTrait(attr, "runtime-return-type");
			t2.setValue(createAndSetSimpleAnyTypeValue("void"));	
			attr.getTraits().add(t2);
		}
		else if (OLDIValueType.MULTICHOICE.equals(valType)) {
			rtType = IAttributeRuntimeValueType.STRING;
		}
		else if (OLDIValueType.NAMED_BOOLEAN.equals(valType)) {
			rtType = IAttributeRuntimeValueType.BOOLEAN;
		}
		else if (OLDIValueType.PROPERTYBINDING.equals(valType)) {
			rtType = IAttributeRuntimeValueType.VALUEBINDING;
		}
		else if (OLDIValueType.RELATIVEPATH.equals(valType)) {
			rtType = IAttributeRuntimeValueType.RELATIVEPATH;
		}
		else if (OLDIValueType.TIMEZONE.equals(valType)) {
			rtType = IAttributeRuntimeValueType.TIMEZONE;
		}
		else if (OLDIValueType.WEBPATH.equals(valType)) {
			rtType = IAttributeRuntimeValueType.WEBPATH;
//			createWebPathValidExtensionsTrait(attr, attrDesc);
		}
		else
			rtType = IAttributeRuntimeValueType.STRING;
		
		setTraitValueUsingSimpleAnyType(trait, rtType.toString());		
		setTypeParamsIfAny(attr, attrDesc);
		attr.getTraits().add(trait);
	}

	private void setTypeParamsIfAny(Entity attr, IAttributeDescriptor attrDesc) {
		//this method is relying on the fact that only one typeParam is being set.   this is all that was ever done by the Sybase config
		if (attrDesc.getTypeParameter()!= null){
			String typeParam = attrDesc.getTypeParameter(); 
			if (typeParam.indexOf(IAttributeDescriptor.PARAMETER_SUFFIX) > -1){
				int i = typeParam.indexOf(IAttributeDescriptor.PARAMETER_SUFFIX);
				String suffixes = typeParam.substring(i+IAttributeDescriptor.PARAMETER_SUFFIX.length()+1);
				Trait trait = createTrait(attr, "file-extensions");
				StringTokenizer st = new StringTokenizer(suffixes, ";");
				ListOfValues exts = TraitTypesFactory.eINSTANCE.createListOfValues();
				while (st.hasMoreElements()){
					String ext = st.nextToken();
					exts.getEntries().add(createAndSetSimpleAnyTypeValue(ext));
				}
				trait.setValue(exts);
				attr.getTraits().add(trait);
			}
			else if (typeParam.indexOf(IAttributeDescriptor.PARAMETER_DEFAULT) > -1){
				int i = typeParam.indexOf(IAttributeDescriptor.PARAMETER_DEFAULT);
				String defaultVal = typeParam.substring(i+IAttributeDescriptor.PARAMETER_DEFAULT.length()+1);
				createTraitAndSimpleAnyTypeValue(attr, "default-value", defaultVal);
			}
			else if (typeParam.indexOf(IAttributeDescriptor.PARAMETER_SEPARATOR) > -1){
				int i = typeParam.indexOf(IAttributeDescriptor.PARAMETER_SEPARATOR);
				String sep = typeParam.substring(i+IAttributeDescriptor.PARAMETER_SEPARATOR.length()+1);
				createTraitAndSimpleAnyTypeValue(attr, "multi-choice-seperator", sep);
			}
			else if (typeParam.indexOf(IAttributeDescriptor.PARAMETER_STYLE) > -1){
				int i = typeParam.indexOf(IAttributeDescriptor.PARAMETER_STYLE);
				String style = typeParam.substring(i+IAttributeDescriptor.PARAMETER_STYLE.length()+1);
				createTraitAndSimpleAnyTypeValue(attr, "style", style);
			}
			else if (typeParam.indexOf(IAttributeDescriptor.PARAMETER_SUPER_TYPE) > -1){
				//do nothing; handled by JavaClassType translation
			}
			else if (typeParam.length() > 0){
				//what to do?  CSSClass seems to require 'empty' type param
				System.out.println(typeParam);
			}
		}
	}

//	private void createWebPathValidExtensionsTrait(Entity attr, IAttributeDescriptor attrDesc) {
//		List<String> exts = new ArrayList<String>();
//		if (attrDesc.getTypeParameter().indexOf("=") > 0){
//			String extS = attrDesc.getTypeParameter().substring(attrDesc.getTypeParameter().indexOf("=")+1);//remove "suffix="
//			StringTokenizer st = new StringTokenizer(extS, ";");
//			while (st.hasMoreElements()){
//				exts.add((String)st.nextElement());
//			}
//			Trait trait = createTrait(attr, "file-extension-filters");
//			ListOfValues list =  TraitTypesFactory.eINSTANCE.createListOfValues();
//			for (String ext : exts){
//				list.getEntries().add(createAndSetSimpleAnyTypeValue(ext));
//			}
//			trait.setValue(list);
//			attr.getTraits().add(trait);
//		}
//	}

	private void createValidatorParams(Entity attr) {
		Trait t = createTrait(attr, "runtime-param-types");
		ListOfValues list =  TraitTypesFactory.eINSTANCE.createListOfValues();
		list.getEntries().add(createAndSetSimpleAnyTypeValue("javax.faces.context.FacesContext"));
		list.getEntries().add(createAndSetSimpleAnyTypeValue("javax.faces.component.UIComponent"));
		list.getEntries().add(createAndSetSimpleAnyTypeValue("java.lang.Object"));
	
		t.setValue(list);
		attr.getTraits().add(t);
	}
	
	private SimpleAnyType createAndSetSimpleAnyTypeValue(String rawVal){
		SimpleAnyType item = XMLTypeFactory.eINSTANCE.createSimpleAnyType();
		item.setRawValue(rawVal);
		return item;
	}

	private void createPossibleValuesAndValidValueTraits(Entity attr,
			IAttributeDescriptor attrDesc) {
		if (attrDesc.getOptions() != null){
			Trait validVals = MetadataFactory.eINSTANCE.createTrait();
			validVals.setId("valid-values");
			validVals.setSourceModelProvider(_assistant.getSourceModelProvider());
			
			Trait displayVals = MetadataFactory.eINSTANCE.createTrait();
			displayVals.setId("displayed-values");
			displayVals.setSourceModelProvider(_assistant.getSourceModelProvider());
			
			Map options = attrDesc.getOptions();
			ListOfValues validVs = TraitTypesFactory.eINSTANCE.createListOfValues();
			ListOfValues displayVs = TraitTypesFactory.eINSTANCE.createListOfValues();
			for (Iterator it=options.entrySet().iterator();it.hasNext();){
				Map.Entry entry = (Map.Entry)it.next();
				validVs.getEntries().add(createAndSetSimpleAnyTypeValue((String)entry.getKey()));
				displayVs.getEntries().add(createAndSetSimpleAnyTypeValue((String)entry.getValue()));
			}
			if (validVs.getEntries().size() > 0) {
				validVals.setValue(validVs);
				displayVals.setValue(displayVs);
				attr.getTraits().add(validVals);
				attr.getTraits().add(displayVals);
			}
		}		
	}

	private void setTraitValueUsingSimpleAnyType(Trait trait, String type) {
		SimpleAnyType value = XMLTypeFactory.eINSTANCE.createSimpleAnyType();
		value.setRawValue(type);
		trait.setValue(value);
	}
	
	private Trait createTrait(Entity entity, String id){
		Trait trait = MetadataFactory.eINSTANCE.createTrait();
		trait.setId(id);
		trait.setSourceModelProvider(_assistant.getSourceModelProvider());
		return trait;
	}
	
	private boolean hasTrait(Entity attr, String traitKey) {
		
		return false;
	}

	private void createTagTraits(Entity tag, IElementDescriptor tagElem) {
		if (tagElem.getHelpContextID() != null && !tagElem.getHelpContextID().equals("")){
			//create help-topic-id trait...
			//not bothering at the moment as we are not providing tooling at the moment
		}
	}

	private Entity internalCreateTagEntity(IElementDescriptor tagElem) {
		Entity tag = MetadataFactory.eINSTANCE.createEntity();
		tag.setId(tagElem.getTagName());
		return tag;
	}
	
	private Entity internalCreateAttributeEntity(IAttributeDescriptor attrDesc) {
		Entity attr = MetadataFactory.eINSTANCE.createEntity();
		attr.setId(attrDesc.getAttributeName());
		return attr;
	}

	private Model getMergedModel(){
		if ((Model)_assistant.getMergedModel().getRoot() != null)
			return (Model)_assistant.getMergedModel().getRoot();
		
		Model model = MetadataFactory.eINSTANCE.createModel();
		model.setId(_assistant.getMergedModel().getModelKey().getUri());
		model.setSourceModelProvider(_assistant.getSourceModelProvider());
		_assistant.getMergedModel().setRoot(model);
		return model;
		
	}
}
