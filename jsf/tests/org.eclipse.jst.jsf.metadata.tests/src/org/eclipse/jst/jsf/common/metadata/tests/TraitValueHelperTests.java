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

import java.util.List;
import java.util.Locale;

import junit.framework.Assert;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.ListOfValues;
import org.eclipse.jst.jsf.test.util.ConfigurableTestCase;

public class TraitValueHelperTests extends ConfigurableTestCase {
	private final String uri = "http://org.eclipse.jsf/traithelpertest";
	private Entity entity;
	private Entity nlsEntity;

	public TraitValueHelperTests(){
		super();
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		
		ITaglibDomainMetaDataModelContext context = TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(null, uri);
		entity = TaglibDomainMetaDataQueryHelper.getEntity(context, "tag/attr1");
		Assert.assertNotNull(entity);
		
		nlsEntity = TaglibDomainMetaDataQueryHelper.getEntity(context, "NLS/NLS");
		Assert.assertNotNull(nlsEntity);
	}

	public void testGetValueType() {		
		//singleString
		Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "singleString");
		Assert.assertNotNull(trait);
		Assert.assertEquals("AnyType", TraitValueHelper.getValueType(trait).getName());
		
		//anInteger
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "anInteger");
		Assert.assertNotNull(trait);
		Assert.assertEquals("AnyType", TraitValueHelper.getValueType(trait).getName());
		
		//multivalStrings
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "multivalStrings");
		Assert.assertNotNull(trait);
		Assert.assertEquals("ListOfValues", TraitValueHelper.getValueType(trait).getName());
		
		//multivalIntegers
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "multivalIntegers");
		Assert.assertNotNull(trait);
		Assert.assertEquals("ListOfValues", TraitValueHelper.getValueType(trait).getName());
		
		//null tests
		Assert.assertNull( TraitValueHelper.getValueType(null));
		EObject val = trait.getValue();
		trait.setValue(null);
		Assert.assertNull( TraitValueHelper.getValueType(trait));
		trait.setValue(val);
		
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "NullVal");
		Assert.assertNotNull(trait);
		Assert.assertEquals("AnyType", TraitValueHelper.getValueType(trait).getName());
	}
 
	public void testGetValue() {
		//singleString
		Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "singleString");
		Assert.assertNotNull(trait);
		Assert.assertEquals("AString", (String)TraitValueHelper.getValue(trait));
		
		//anInteger
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "anInteger");
		Assert.assertNotNull(trait);
		Assert.assertEquals(1, Integer.parseInt((String)TraitValueHelper.getValue(trait)));
		
		//aTrueInt
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "aTrueInt");
		Assert.assertNotNull(trait);
		Assert.assertNotNull(trait.getValue());
		Assert.assertTrue(trait.getValue() instanceof SimpleAnyType);
		Assert.assertTrue(((SimpleAnyType)trait.getValue()).getInstanceType().getInstanceClassName().equals("int"));
		Assert.assertEquals("1", TraitValueHelper.getValue(trait));

		//null tests
		Assert.assertNull( TraitValueHelper.getValue(null));
		EObject val = trait.getValue();
		trait.setValue(null);
		Assert.assertNull( TraitValueHelper.getValue(trait));
		//reset
		trait.setValue(val);
		
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "NullVal");
		Assert.assertNotNull(trait);
		Assert.assertNull(TraitValueHelper.getValue(trait));
	}

	public void testGetValueAsString() {
		//singleString
		Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "singleString");
		Assert.assertNotNull(trait);
		Assert.assertEquals("AString", TraitValueHelper.getValueAsString(trait));
		
		//anInteger
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "anInteger");
		Assert.assertNotNull(trait);
		Assert.assertEquals("1", TraitValueHelper.getValueAsString(trait));
		
		
		//null tests
		Assert.assertNull( TraitValueHelper.getValue(null));
		EObject val = trait.getValue();
		trait.setValue(null);
		Assert.assertNull( TraitValueHelper.getValue(trait));
		trait.setValue(val);
		
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "NullVal");
		Assert.assertNotNull(trait);
		Assert.assertEquals(null, TraitValueHelper.getValueAsString(trait));
	}

	public void testGetValueAsListOfStrings() {
		//multivalStrings
		Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "multivalStrings");
		Assert.assertNotNull(trait);
		Assert.assertTrue(TraitValueHelper.getValueAsListOfStrings(trait) instanceof List);
		List<?> vals = TraitValueHelper.getValueAsListOfStrings(trait);
		Assert.assertEquals(3, vals.size());
		Assert.assertTrue(vals.get(0) instanceof String);
		Assert.assertEquals("A", (String)vals.get(0));
		Assert.assertEquals("B", (String)vals.get(1));
		Assert.assertEquals("C", (String)vals.get(2));
		
		//multivalIntegers
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "multivalIntegers");
		Assert.assertNotNull(trait);
		Assert.assertTrue(TraitValueHelper.getValueAsListOfStrings(trait) instanceof List);
		vals = TraitValueHelper.getValueAsListOfStrings(trait);
		Assert.assertEquals(3, vals.size());
		Assert.assertTrue(vals.get(0) instanceof String);
		Assert.assertEquals("1", (String)vals.get(0));
		Assert.assertEquals("2", (String)vals.get(1));
		Assert.assertEquals("3", (String)vals.get(2));
	}

	public void testGetNLSValue() {
		//single NLS String
		Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(nlsEntity, "NLS");
		Assert.assertNotNull(trait);
		Assert.assertEquals("%NLS1", TraitValueHelper.getValue(trait));
		if (Locale.getDefault().getCountry().equalsIgnoreCase("us") &&
				Locale.getDefault().getLanguage().equalsIgnoreCase("en")) {
			Assert.assertEquals("a day in the life(en_US)", TraitValueHelper.getValueAsString(trait));
		}
		
		//multiple NLS Strings
		trait = TaglibDomainMetaDataQueryHelper.getTrait(nlsEntity, "multivalNLS");
		Assert.assertNotNull(trait);
		Assert.assertNotNull(trait.getValue());
		Assert.assertTrue(trait.getValue() instanceof ListOfValues);
		List<?> vals = TraitValueHelper.getValueAsListOfStrings(trait);
		Assert.assertEquals(2, vals.size());
		if (Locale.getDefault().getCountry().equalsIgnoreCase("us") &&
				Locale.getDefault().getLanguage().equalsIgnoreCase("en")) {
			Assert.assertEquals("a day in the life(en_US)", vals.get(0));
			Assert.assertEquals("another string(en_US)", vals.get(1));
		} 
		
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "NullVal");
		Assert.assertNotNull(trait);
		Assert.assertEquals(null, TraitValueHelper.getValueAsString(trait));
	}

	public void testGetValueAsBoolean() {
		//valid true
		Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "BooleanVal");
		Assert.assertNotNull(trait);
		Assert.assertNotNull(trait.getValue());
		Assert.assertEquals(true, TraitValueHelper.getValueAsBoolean(trait));		
		
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "NullBooleanVal");
		Assert.assertNotNull(trait);
		Assert.assertNotNull(trait.getValue());
		Assert.assertEquals(false, TraitValueHelper.getValueAsBoolean(trait));
	}
	
//	@SuppressWarnings("restriction")
//	public void testGetValueInstanceClassName() {
//		Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "singleString");
//		Assert.assertNotNull(trait);
//		Assert.assertNotNull(trait.getValue());
//		Assert.assertEquals("java.lang.String", TraitValueHelper.getValueInstanceClassName(trait));		
//		
//		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "anInteger");
//		Assert.assertNotNull(trait);
//		Assert.assertNotNull(trait.getValue());
//		Assert.assertEquals("java.lang.String", TraitValueHelper.getValueInstanceClassName(trait));	
//		
//		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "aTrueInt");
//		Assert.assertNotNull(trait);
//		Assert.assertNotNull(trait.getValue());
//		Assert.assertEquals("int", TraitValueHelper.getValueInstanceClassName(trait));	
//		
//		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "multivalStrings");
//		Assert.assertNotNull(trait);
//		Assert.assertNotNull(trait.getValue());
//		Assert.assertEquals("org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl.ListOfValuesImpl", TraitValueHelper.getValueInstanceClassName(trait));	
//		
//	}

}
