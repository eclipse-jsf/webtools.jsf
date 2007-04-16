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

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.contentmodel.annotation.CMAnnotationHelper;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.CMAnnotationFileRegistry;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.CMAnnotationMap;

/**
  * CMAnnotatations Metadata framework has been "taken out of service"
 * Please use org.eclipse.jst.jsf.common.metadata
 * 
 * Test functions in the CMAnnotationMap.  
 * Uses /testfiles/metadata/jsf_test.xml
 * 
 * @author Gerry Kessler - Oracle
 */
public class AnnotationMapTestCases extends TestCase implements ICMAnnotationTestCases{ 
	private CMAnnotationFileRegistry reg;
	private List maps;
	
	public void setUp(){
		reg = CMAnnotationFileRegistry.getInstance();
		Assert.assertTrue(reg != null);
	}
	
	public void testAnnotationsMapsDefaultParser(){
		//get first which is http://org.eclipse.jsf/test
		boolean hasInfos = CMAnnotationHelper.hasAnnotations(PUBLICID);
		Assert.assertTrue(hasInfos);
		
		maps = reg.getAnnotationMaps(PUBLICID);
		Assert.assertNotNull(maps);
		
		CMAnnotationMap map = (CMAnnotationMap)maps.get(0);

		execAsserts(map);
	}
	public void testAnnotationsMapsDifferentParser(){
		//Test public api w/ non-default parser.   Really just delegates to default, but it is a different class.
		//http://org.eclipse.jsf/test2
		boolean hasInfos = CMAnnotationHelper.hasAnnotations(PUBLICID+"2");
		Assert.assertTrue(hasInfos);
		
		maps = reg.getAnnotationMaps(PUBLICID+"2");
		Assert.assertNotNull(maps);
		
		CMAnnotationMap map = (CMAnnotationMap)maps.get(0);
		
		execAsserts(map);
	
	}

	private void execAsserts(CMAnnotationMap map){
//		Test annotation map functions
		// no props on element; only on attr		
		Assert.assertNull(map.getCMElementProperty(TEST_ELEMENT_VALIDATOR,TEST_ELEMENT1_PROPERTY_NAME));
		Assert.assertNotNull(map.getCMAttributeProperty(TEST_ELEMENT_VALIDATOR,TEST_ATTR_VALIDATORID, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST));
		Assert.assertEquals(map.getCMAttributeProperty(TEST_ELEMENT_VALIDATOR,TEST_ATTR_VALIDATORID, TEST_ATTR_PROPERTY_NAME_CONTENTASSIST),TEST_ATTR_PROPERTY_VALUE_CONTENTASSIST);
		Assert.assertNull(map.getCMAttributeProperty(TEST_ELEMENT_VALIDATOR,TEST_ATTR_VALIDATORID, TEST_PROPERTY_NAME_A));
		
		//no properties or attrs
		Assert.assertNull(map.getCMElementProperty(TEST_ELEMENT_NAME_NOPROPSORATTRS,TEST_PROPERTY_NAME_A));
		Assert.assertNull(map.getCMAttributeProperty(TEST_ELEMENT_NAME_NOPROPSORATTRS, TEST_ATTR_VALIDATORID,TEST_PROPERTY_NAME_A));
		
		//element props but no Attrs
		Assert.assertNotNull(map.getCMElementProperty(TEST_ELEMENT_NAME_NOATTRS,TEST_PROPERTY_NAME_A));
		Assert.assertEquals(map.getCMElementProperty(TEST_ELEMENT_NAME_NOATTRS,TEST_PROPERTY_NAME_A), TEST_PROPERTY_VALUE_a);
		Assert.assertEquals(map.getCMElementProperty(TEST_ELEMENT_NAME_NOATTRS,TEST_PROPERTY_NAME_B), TEST_PROPERTY_VALUE_b);
		
		//elements and attrs with props
		Assert.assertNotNull(map.getCMElementProperty(TEST_ELEMENT_NAME_LOADED,TEST_PROPERTY_NAME_A));
		Assert.assertEquals(map.getCMElementProperty(TEST_ELEMENT_NAME_LOADED,TEST_PROPERTY_NAME_A), TEST_PROPERTY_VALUE_a);
		Assert.assertEquals(map.getCMElementProperty(TEST_ELEMENT_NAME_LOADED,TEST_PROPERTY_NAME_B), TEST_PROPERTY_VALUE_b);
		Assert.assertEquals(map.getCMAttributeProperty(TEST_ELEMENT_NAME_LOADED,TEST_ATTR4_NAMEa, TEST_PROPERTY_NAMEa),TEST_PROPERTY_VALUEa);
		Assert.assertEquals(map.getCMAttributeProperty(TEST_ELEMENT_NAME_LOADED,TEST_ATTR4_NAMEb, TEST_PROPERTY_NAMEb),TEST_PROPERTY_VALUEb);
		Assert.assertEquals(map.getCMAttributeProperty(TEST_ELEMENT_NAME_LOADED,TEST_ATTR4_NAMEc, TEST_PROPERTY_NAMEc),TEST_PROPERTY_VALUEc);

//		multiple values test
		Assert.assertEquals(map.getCMAttributePropertyValues(TEST_ELEMENT_NAME_LOADED,TEST_ATTR4_NAMEc, TEST_PROPERTY_MULTIVAL).size(), 3);
		Assert.assertEquals(map.getCMElementPropertyValues(TEST_ELEMENT_NAME_LOADED, TEST_PROPERTY_MULTIVAL).size(), 3);
		
//NLS Test - temporarily disabled because of automated tests build issues
//		Assert.assertEquals(map.getCMElementProperty(TEST_NLS,"NLS1"), "a day in the life" + getNLSSuffix() );
//		Assert.assertEquals(map.getCMElementProperty(TEST_NLS,"NLS2"), "another string" + getNLSSuffix());	
//		Assert.assertEquals(map.getCMElementProperty(TEST_NLS,"NLS3"), "NLS3(key not found)");	
//		
//		Assert.assertEquals(map.getCMAttributeProperty(TEST_NLS,TEST_NLS,"NLS1"), "a day in the life" + getNLSSuffix());
//		Assert.assertEquals(map.getCMAttributeProperty(TEST_NLS,TEST_NLS,"NLS2"), "another string" + getNLSSuffix());	
//		Assert.assertEquals(map.getCMAttributeProperty(TEST_NLS,TEST_NLS,"NLS3"), "NLS3(key not found)");			
	}

	//return "(en_US)" if that is the locale
//	private String getNLSSuffix() {
//		return Platform.getNL().equals("en_US") ? "(en_US)" : "";
//	}
	
}
