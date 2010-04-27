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
package org.eclipse.jst.jsf.common.metadata.tests.updated;

import java.util.List;
import java.util.Locale;

import junit.framework.Assert;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper2;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.ListOfValues;
import org.eclipse.jst.jsf.test.util.ConfigurableTestCase;

public class TraitValueHelperTests extends ConfigurableTestCase {
	private final String uri = "http://org.eclipse.jsf/traithelpertest";
	private Entity entity;
	private Entity nlsEntity;
	private ITaglibDomainMetaDataQuery _query;
	private TraitValueHelper2 _helper;
	
	public TraitValueHelperTests(){
		super();
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		
		IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(null);
		_query = MetaDataQueryFactory.getInstance().createQuery(context);
		entity = _query.getQueryHelper().getEntity(uri, "tag/attr1");
		Assert.assertNotNull(entity);
		
		nlsEntity = _query.getQueryHelper().getEntity(uri, "NLS/NLS");
		Assert.assertNotNull(nlsEntity);
		
		_helper = new TraitValueHelper2();
	}

	public void testGetValueType() {		
		//singleString
		Trait trait = _query.getQueryHelper().getTrait(entity, "singleString");
		Assert.assertNotNull(trait);
		Assert.assertEquals("AnyType", _helper.getValueType(trait).getName());
		
		//anInteger
		trait = _query.getQueryHelper().getTrait(entity, "anInteger");
		Assert.assertNotNull(trait);
		Assert.assertEquals("AnyType", _helper.getValueType(trait).getName());
		
		//multivalStrings
		trait = _query.getQueryHelper().getTrait(entity, "multivalStrings");
		Assert.assertNotNull(trait);
		Assert.assertEquals("ListOfValues", _helper.getValueType(trait).getName());
		
		//multivalIntegers
		trait = _query.getQueryHelper().getTrait(entity, "multivalIntegers");
		Assert.assertNotNull(trait);
		Assert.assertEquals("ListOfValues", _helper.getValueType(trait).getName());
		
		//null tests
		Assert.assertNull( _helper.getValueType(null));
		EObject val = trait.getValue();
		trait.setValue(null);
		Assert.assertNull( _helper.getValueType(trait));
		trait.setValue(val);
		
		trait = _query.getQueryHelper().getTrait(entity, "NullVal");
		Assert.assertNotNull(trait);
		Assert.assertEquals("AnyType", _helper.getValueType(trait).getName());
	}
 
	public void testGetValue() {
		//singleString
		Trait trait = _query.getQueryHelper().getTrait(entity, "singleString");
		Assert.assertNotNull(trait);
		Assert.assertEquals("AString", (String)_helper.getValue(trait));
		
		//anInteger
		trait = _query.getQueryHelper().getTrait(entity, "anInteger");
		Assert.assertNotNull(trait);
		Assert.assertEquals(1, Integer.parseInt((String)_helper.getValue(trait)));
		
		//aTrueInt
		trait = _query.getQueryHelper().getTrait(entity, "aTrueInt");
		Assert.assertNotNull(trait);
		Assert.assertNotNull(trait.getValue());
		Assert.assertTrue(trait.getValue() instanceof SimpleAnyType);
		Assert.assertTrue(((SimpleAnyType)trait.getValue()).getInstanceType().getInstanceClassName().equals("int"));
		Assert.assertEquals("1", _helper.getValue(trait));

		//null tests
		Assert.assertNull( _helper.getValue(null));
		EObject val = trait.getValue();
		trait.setValue(null);
		Assert.assertNull( _helper.getValue(trait));
		//reset
		trait.setValue(val);
		
		trait = _query.getQueryHelper().getTrait(entity, "NullVal");
		Assert.assertNotNull(trait);
		Assert.assertNull(_helper.getValue(trait));
	}

	public void testGetValueAsString() {
		//singleString
		Trait trait = _query.getQueryHelper().getTrait(entity, "singleString");
		Assert.assertNotNull(trait);
		Assert.assertEquals("AString", _helper.getValueAsString(trait));
		
		//anInteger
		trait = _query.getQueryHelper().getTrait(entity, "anInteger");
		Assert.assertNotNull(trait);
		Assert.assertEquals("1", _helper.getValueAsString(trait));
		
		
		//null tests
		Assert.assertNull( _helper.getValue(null));
		EObject val = trait.getValue();
		trait.setValue(null);
		Assert.assertNull( _helper.getValue(trait));
		trait.setValue(val);
		
		trait = _query.getQueryHelper().getTrait(entity, "NullVal");
		Assert.assertNotNull(trait);
		Assert.assertEquals(null, _helper.getValueAsString(trait));
	}

	public void testGetValueAsListOfStrings() {
		//multivalStrings
		Trait trait = _query.getQueryHelper().getTrait(entity, "multivalStrings");
		Assert.assertNotNull(trait);
		Assert.assertTrue(_helper.getValueAsListOfStrings(trait) instanceof List<?>);
		List<?> vals = _helper.getValueAsListOfStrings(trait);
		Assert.assertEquals(3, vals.size());
		Assert.assertTrue(vals.get(0) instanceof String);
		Assert.assertEquals("A", (String)vals.get(0));
		Assert.assertEquals("B", (String)vals.get(1));
		Assert.assertEquals("C", (String)vals.get(2));
		
		//multivalIntegers
		trait = _query.getQueryHelper().getTrait(entity, "multivalIntegers");
		Assert.assertNotNull(trait);
		Assert.assertTrue(_helper.getValueAsListOfStrings(trait) instanceof List<?>);
		vals = _helper.getValueAsListOfStrings(trait);
		Assert.assertEquals(3, vals.size());
		Assert.assertTrue(vals.get(0) instanceof String);
		Assert.assertEquals("1", (String)vals.get(0));
		Assert.assertEquals("2", (String)vals.get(1));
		Assert.assertEquals("3", (String)vals.get(2));
	}

	public void testGetNLSValue() {
		//single NLS String
		Trait trait = _query.getQueryHelper().getTrait(nlsEntity, "NLS");
		Assert.assertNotNull(trait);
		Assert.assertEquals("%NLS1", _helper.getValue(trait));
		if (Locale.getDefault().getCountry().equalsIgnoreCase("us") &&
				Locale.getDefault().getLanguage().equalsIgnoreCase("en")) {
			Assert.assertEquals("a day in the life(en_US)", _helper.getValueAsString(trait));
		}
		
		//multiple NLS Strings
		trait = _query.getQueryHelper().getTrait(nlsEntity, "multivalNLS");
		Assert.assertNotNull(trait);
		Assert.assertNotNull(trait.getValue());
		Assert.assertTrue(trait.getValue() instanceof ListOfValues);
		List<?> vals = _helper.getValueAsListOfStrings(trait);
		Assert.assertEquals(2, vals.size());
		if (Locale.getDefault().getCountry().equalsIgnoreCase("us") &&
				Locale.getDefault().getLanguage().equalsIgnoreCase("en")) {
			Assert.assertEquals("a day in the life(en_US)", vals.get(0));
			Assert.assertEquals("another string(en_US)", vals.get(1));
		} 
		
		trait = _query.getQueryHelper().getTrait(entity, "NullVal");
		Assert.assertNotNull(trait);
		Assert.assertEquals(null, _helper.getValueAsString(trait));
	}

	public void testGetValueAsBoolean() {
		//valid true
		Trait trait = _query.getQueryHelper().getTrait(entity, "BooleanVal");
		Assert.assertNotNull(trait);
		Assert.assertNotNull(trait.getValue());
		Assert.assertEquals(true, _helper.getValueAsBoolean(trait));		
		
		trait = _query.getQueryHelper().getTrait(entity, "NullBooleanVal");
		Assert.assertNotNull(trait);
		Assert.assertNotNull(trait.getValue());
		Assert.assertEquals(false, _helper.getValueAsBoolean(trait));
	}
	
	public void testStringValueType() {
		final Model m =  _query.getQueryHelper().getModel(uri);
		assertNotNull(m);

		final Trait t = _query.getQueryHelper().getTrait( m, "TraitId");
		assertNotNull(t);
		
		final EObject obj = (EObject)t.getValue();
		assertEquals("StringValue", obj.eClass().getName());
		assertEquals("TraitValue", _helper.getValueAsString(t));
	
	}
	
	public void testBooleanValueType() {
		final Model m =  _query.getQueryHelper().getModel(uri);
		assertNotNull(m);
		
		//true
		final Trait t2 = _query.getQueryHelper().getTrait( m, "TraitId2");
		assertNotNull(t2);
		
		final EObject obj2 = (EObject)t2.getValue();
		assertEquals("BooleanValue", obj2.eClass().getName());
		assertEquals(true, _helper.getValueAsBoolean(t2));
		assertEquals("true", _helper.getValueAsString(t2));
		
		//false
		final Trait t3 = _query.getQueryHelper().getTrait( m, "TraitId3");
		assertNotNull(t3);
		
		final EObject obj3 = (EObject)t3.getValue();
		assertEquals("BooleanValue", obj3.eClass().getName());
		assertEquals(false, _helper.getValueAsBoolean(t3));
	
	}
	
//	@SuppressWarnings("restriction")
//	public void testGetValueInstanceClassName() {
//		Trait trait = _query.getQueryHelper().getTrait(entity, "singleString");
//		Assert.assertNotNull(trait);
//		Assert.assertNotNull(trait.getValue());
//		Assert.assertEquals("java.lang.String", _helper.getValueInstanceClassName(trait));		
//		
//		trait = _query.getQueryHelper().getTrait(entity, "anInteger");
//		Assert.assertNotNull(trait);
//		Assert.assertNotNull(trait.getValue());
//		Assert.assertEquals("java.lang.String", _helper.getValueInstanceClassName(trait));	
//		
//		trait = _query.getQueryHelper().getTrait(entity, "aTrueInt");
//		Assert.assertNotNull(trait);
//		Assert.assertNotNull(trait.getValue());
//		Assert.assertEquals("int", _helper.getValueInstanceClassName(trait));	
//		
//		trait = _query.getQueryHelper().getTrait(entity, "multivalStrings");
//		Assert.assertNotNull(trait);
//		Assert.assertNotNull(trait.getValue());
//		Assert.assertEquals("org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl.ListOfValuesImpl", _helper.getValueInstanceClassName(trait));	
//		
//	}

}
