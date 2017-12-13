/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.metadataprocessing;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.metadata.tests.MetadataTestsPlugin;
import org.eclipse.jst.jsf.metadataprocessors.ITypeDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.internal.AttributeValueRuntimeTypeFactory;

public class AttributeValueRuntimeTypeFactoryTests extends TestCase {

	/*
	 * Test method for 'org.eclipse.jst.jsf.metadataprocessors.internal.AttributeValueRuntimeTypeFactory.getTypes(String)'
	 * Also tests the simple AbstractMetaDataEnabledType class
	 */
	public void testGetTypes() {
		AttributeValueRuntimeTypeFactory factory = AttributeValueRuntimeTypeFactory.getInstance();
		Assert.assertNotNull(factory);
		
		ITypeDescriptor atype = factory.getType(AttributeValueRuntimeTypesRegistryTests.BOOLEANTYPE_ID);
		Assert.assertNotNull(atype);
		Assert.assertNotNull(atype.getTypeExtension().getClassName());
		Assert.assertTrue(atype.getTypeExtension().getBundleID().equals(MetadataTestsPlugin.ID_BUNDLE));		
		Assert.assertTrue(atype.getTypeExtension().getTypeID().equals(AttributeValueRuntimeTypesRegistryTests.BOOLEANTYPE_ID));
		List<?> features =  atype.getFeatureAdapters(IPossibleValues.class);
		Assert.assertNotNull(features);
		Assert.assertFalse(features.isEmpty());
		Assert.assertEquals(2, features.size());  //we expect MyBooleanType and MetaDataEnabledBarkProcessor  
		
		atype = factory.getType(AttributeValueRuntimeTypesRegistryTests.STRINGARRAYTYPE_ID);
		Assert.assertNotNull(atype);
		Assert.assertNotNull(atype.getTypeExtension().getClassName());
		Assert.assertTrue(atype.getTypeExtension().getBundleID().equals(MetadataTestsPlugin.ID_BUNDLE));		
		Assert.assertTrue(atype.getTypeExtension().getTypeID().equals(AttributeValueRuntimeTypesRegistryTests.STRINGARRAYTYPE_ID));

	}
	
	public void testNoImplTest(){
		AttributeValueRuntimeTypeFactory factory = AttributeValueRuntimeTypeFactory.getInstance();
		Assert.assertNotNull(factory);
		
		ITypeDescriptor atype = factory.getType(AttributeValueRuntimeTypesRegistryTests.NOIMPLTYPE_ID);
		Assert.assertNotNull(atype);
		Assert.assertNotNull(atype.getTypeExtension().getClassName());
		Assert.assertTrue(atype.getTypeExtension().getBundleID().equals(MetadataTestsPlugin.ID_BUNDLE));	
		
	}


}
