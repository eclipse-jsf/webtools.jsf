/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.metadata.tests.annotations;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationHelper;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationPropertyValue;

/**
 * Test all methods in CMAnnotationHelper
 * Uses /testfiles/metadata/jsf_test.xml. 
 * 
 * @author Gerry Kessler - Oracle
 */
public class AnnotationHelperTestCases extends TestCase implements ICMAnnotationTestCases {	
	
	public void testGetCMAttributePropertiesAll(){
		//positive
		Assert.assertNotNull(CMAnnotationHelper.getCMAttributeProperties(PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_VALIDATORID, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST));
		Assert.assertEquals(CMAnnotationHelper.getCMAttributeProperties(PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_VALIDATORID, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST).size(), 1);
		
		//negative
		Assert.assertTrue(CMAnnotationHelper.getCMAttributeProperties(PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_VALIDATORID, "bogus").isEmpty());
		//case sensitive test - should not locate		
		Assert.assertTrue(CMAnnotationHelper.getCMAttributeProperties(PUBLICID, TEST_ELEMENT_VALIDATOR.toUpperCase(), TEST_ATTR_VALIDATORID, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST).isEmpty());
		Assert.assertTrue(CMAnnotationHelper.getCMAttributeProperties(PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_VALIDATORID.toUpperCase(), TEST_ATTR_PROPERTY_NAME_CONTENTASSIST).isEmpty());
		Assert.assertTrue(CMAnnotationHelper.getCMAttributeProperties(PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_VALIDATORID, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST.toUpperCase()).isEmpty());
	}
	
	public void testGetCMAttributePropertiesForBundle(){
		//positive
		Assert.assertNotNull(CMAnnotationHelper.getCMAttributeProperties(BUNDLEID, PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_VALIDATORID, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST));
		Assert.assertEquals(CMAnnotationHelper.getCMAttributeProperties(BUNDLEID, PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_VALIDATORID, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST).size(), 1);		
		
		//negative		
		Assert.assertTrue(CMAnnotationHelper.getCMAttributeProperties(BUNDLEID, PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_VALIDATORID, "bogus").isEmpty());
		//case sensitive test - should not locate
		Assert.assertTrue(CMAnnotationHelper.getCMAttributeProperties(BUNDLEID, PUBLICID, TEST_ELEMENT_VALIDATOR.toUpperCase(), TEST_ATTR_VALIDATORID, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST).isEmpty());
		Assert.assertTrue(CMAnnotationHelper.getCMAttributeProperties(BUNDLEID, PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_VALIDATORID.toUpperCase(), TEST_ATTR_PROPERTY_NAME_CONTENTASSIST).isEmpty());
		Assert.assertTrue(CMAnnotationHelper.getCMAttributeProperties(BUNDLEID, PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_VALIDATORID, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST.toUpperCase()).isEmpty());
		
		
	}
	
	public void testGetCMElementPropertiesAll(){
		//positive
		Assert.assertTrue(CMAnnotationHelper.getCMElementProperties(PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST).isEmpty());
		Assert.assertEquals(CMAnnotationHelper.getCMElementProperties(PUBLICID, TEST_ELEMENT_NAME_NOATTRS, TEST_PROPERTY_NAME_A).size(), 1);
		Assert.assertNotNull(CMAnnotationHelper.getCMElementProperties(PUBLICID, TEST_ELEMENT_NAME_NOATTRS, TEST_PROPERTY_NAME_A));
		List propvals = CMAnnotationHelper.getCMElementProperties(PUBLICID, TEST_ELEMENT_NAME_NOATTRS, TEST_PROPERTY_NAME_A);
		Assert.assertEquals(propvals.size(), 1);
		Object obj = propvals.get(0);
		Assert.assertEquals(obj instanceof CMAnnotationPropertyValue, true);
		Assert.assertEquals(((CMAnnotationPropertyValue)obj).getPropertyValue(), TEST_PROPERTY_VALUE_a);
		//negative	
		//case sensitive test - should not locate
		Assert.assertTrue(CMAnnotationHelper.getCMElementProperties(PUBLICID, TEST_ELEMENT_NAME_NOATTRS.toUpperCase(), TEST_PROPERTY_NAME_A).isEmpty());
		Assert.assertTrue(CMAnnotationHelper.getCMElementProperties(PUBLICID, TEST_ELEMENT_NAME_NOATTRS, TEST_PROPERTY_NAME_A.toLowerCase()).isEmpty());
		

	}
	
	public void testGetCMElementPropertiesForBundle(){
		//positive
		Assert.assertTrue(CMAnnotationHelper.getCMElementProperties(BUNDLEID, PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST).isEmpty());
		Assert.assertEquals(CMAnnotationHelper.getCMElementProperties(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_NOATTRS, TEST_PROPERTY_NAME_A).size(), 1);
		Assert.assertNotNull(CMAnnotationHelper.getCMElementProperties(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_NOATTRS, TEST_PROPERTY_NAME_A));
		List propvals = CMAnnotationHelper.getCMElementProperties(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_NOATTRS, TEST_PROPERTY_NAME_A);
		Assert.assertEquals(propvals.size(), 1);
		Object obj = propvals.get(0);
		Assert.assertEquals(obj instanceof CMAnnotationPropertyValue, true);
		Assert.assertEquals(((CMAnnotationPropertyValue)obj).getPropertyValue(), TEST_PROPERTY_VALUE_a);		
		//negative	
		//case sensitive test - should not locate
		Assert.assertTrue(CMAnnotationHelper.getCMElementProperties(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_NOATTRS.toUpperCase(), TEST_PROPERTY_NAME_A).isEmpty());
		Assert.assertTrue(CMAnnotationHelper.getCMElementProperties(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_NOATTRS, TEST_PROPERTY_NAME_A.toLowerCase()).isEmpty());

	}
	
	public void testGetCMAttributePropertyValue(){
//		positive
		Assert.assertEquals(CMAnnotationHelper.getCMAttributePropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_VALIDATORID, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST), TEST_ATTR_PROPERTY_VALUE_CONTENTASSIST);
//		elements and attrs with props
		Assert.assertEquals(CMAnnotationHelper.getCMAttributePropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED,TEST_ATTR4_NAMEa, TEST_PROPERTY_NAMEa),TEST_PROPERTY_VALUEa);
		Assert.assertEquals(CMAnnotationHelper.getCMAttributePropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED,TEST_ATTR4_NAMEb, TEST_PROPERTY_NAMEb),TEST_PROPERTY_VALUEb);
		Assert.assertEquals(CMAnnotationHelper.getCMAttributePropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED,TEST_ATTR4_NAMEc, TEST_PROPERTY_NAMEc),TEST_PROPERTY_VALUEc);

		//NLS - - temporarily disabled because of automated tests build issues
//		Assert.assertEquals(CMAnnotationHelper.getCMAttributePropertyValue(BUNDLEID, PUBLICID, TEST_NLS,TEST_NLS,"NLS1"), "a day in the life" + getNLSSuffix());
//		Assert.assertEquals(CMAnnotationHelper.getCMAttributePropertyValue(BUNDLEID, PUBLICID, TEST_NLS,TEST_NLS,"NLS2"), "another string" + getNLSSuffix());	
//		Assert.assertEquals(CMAnnotationHelper.getCMAttributePropertyValue(BUNDLEID, PUBLICID, TEST_NLS,TEST_NLS,"NLS3"), "NLS3(key not found)");			
		
//		negative			
		Assert.assertFalse(CMAnnotationHelper.getCMAttributePropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_VALIDATORID, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST).equals("bogus"));
		Assert.assertNull(CMAnnotationHelper.getCMAttributePropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_VALIDATOR, TEST_ATTR_VALIDATORID, "bogus"));
		
		//no properties or attrs
		Assert.assertNull(CMAnnotationHelper.getCMAttributePropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_NOPROPSORATTRS, TEST_ATTR_VALIDATORID,TEST_PROPERTY_NAME_A));

	}
	
	public void testGetCMAttributePropertyValues(){
		//positive
		Assert.assertEquals(CMAnnotationHelper.getCMAttributePropertyValues(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED, TEST_ATTR4_NAMEc, TEST_PROPERTY_MULTIVAL).size(), 3);
		List list = new ArrayList(3);
		list.add("1");
		list.add("2");
		list.add("3");
		Assert.assertEquals(CMAnnotationHelper.getCMAttributePropertyValues(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED, TEST_ATTR4_NAMEc, TEST_PROPERTY_MULTIVAL), list);
		//negative
		Assert.assertTrue(CMAnnotationHelper.getCMAttributePropertyValues(BUNDLEID, PUBLICID, TEST_ELEMENT_VALIDATOR,TEST_ATTR_VALIDATORID, TEST_PROPERTY_NAME_A).isEmpty());
		Assert.assertEquals(CMAnnotationHelper.getCMAttributePropertyValues(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED, TEST_ATTR4_NAMEc, TEST_PROPERTY_MULTIVAL).size(), 3);
	
	}
	
	public void testGetCMElementPropertyValue(){
		//positive
		Assert.assertEquals(CMAnnotationHelper.getCMElementPropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED, TEST_PROPERTY_NAME_A), TEST_PROPERTY_VALUE_a);
		//element props but no Attrs in data
		Assert.assertNotNull(CMAnnotationHelper.getCMElementPropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_NOATTRS,TEST_PROPERTY_NAME_A));
		Assert.assertEquals(CMAnnotationHelper.getCMElementPropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_NOATTRS,TEST_PROPERTY_NAME_A), TEST_PROPERTY_VALUE_a);
		Assert.assertEquals(CMAnnotationHelper.getCMElementPropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_NOATTRS,TEST_PROPERTY_NAME_B), TEST_PROPERTY_VALUE_b);
		//elements and attrs with props
		Assert.assertNotNull(CMAnnotationHelper.getCMElementPropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED,TEST_PROPERTY_NAME_A));
		Assert.assertEquals(CMAnnotationHelper.getCMElementPropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED,TEST_PROPERTY_NAME_A), TEST_PROPERTY_VALUE_a);
		Assert.assertEquals(CMAnnotationHelper.getCMElementPropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED,TEST_PROPERTY_NAME_B), TEST_PROPERTY_VALUE_b);
		//NLS
//		Assert.assertEquals(CMAnnotationHelper.getCMElementPropertyValue(BUNDLEID, PUBLICID, TEST_NLS,"NLS1"), "a day in the life" + getNLSSuffix());
//		Assert.assertEquals(CMAnnotationHelper.getCMElementPropertyValue(BUNDLEID, PUBLICID, TEST_NLS,"NLS2"), "another string" + getNLSSuffix());	
//		Assert.assertEquals(CMAnnotationHelper.getCMElementPropertyValue(BUNDLEID, PUBLICID, TEST_NLS,"NLS3"), "NLS3(key not found)");	
		
		//negative			
		Assert.assertFalse(CMAnnotationHelper.getCMElementPropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED, TEST_PROPERTY_NAME_A).equals("bogus"));
		Assert.assertNull(CMAnnotationHelper.getCMElementPropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED, "bogus"));
		
		//no properties or attrs
		Assert.assertNull(CMAnnotationHelper.getCMElementPropertyValue(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_NOPROPSORATTRS,TEST_PROPERTY_NAME_A));

	}
	
	public void testGetCMElementPropertyValues(){
		//positive
		Assert.assertEquals(((String)CMAnnotationHelper.getCMElementPropertyValues(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED, TEST_PROPERTY_NAME_A).get(0)), TEST_PROPERTY_VALUE_a);
		Assert.assertEquals(CMAnnotationHelper.getCMElementPropertyValues(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED, TEST_PROPERTY_MULTIVAL).size(), 3);
		List list = new ArrayList(3);
		list.add("1");
		list.add("2");
		list.add("3");
		Assert.assertEquals(CMAnnotationHelper.getCMElementPropertyValues(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED, TEST_PROPERTY_MULTIVAL), list);		
		
		//negative			
		Assert.assertFalse(((String)CMAnnotationHelper.getCMElementPropertyValues(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED, TEST_PROPERTY_NAME_A).get(0)).equals("bogus"));
		Assert.assertNull(CMAnnotationHelper.getCMElementPropertyValues(BUNDLEID, PUBLICID, TEST_ELEMENT_NAME_LOADED, "bogus"));
		
	}	
	public void testHasAnnotationsAll(){
		//positive		
		Assert.assertEquals(CMAnnotationHelper.hasAnnotations(PUBLICID), true);
		//negative		
		Assert.assertFalse(CMAnnotationHelper.hasAnnotations("bogus"));
	}
	
	public void testHasAnnotationsForBundle(){
		//positive
		Assert.assertEquals(CMAnnotationHelper.hasAnnotations(BUNDLEID, PUBLICID), true);
		//negative	
		Assert.assertFalse(CMAnnotationHelper.hasAnnotations(BUNDLEID, "bogus"));
	}	
	
	//return "(en_US)" if that is the locale
//	private String getNLSSuffix() {
//		return Platform.getNL().equals("en_US") ? "(en_US)" : "";
//	}
}
