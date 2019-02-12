/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.metadataprocessing;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.metadata.tests.MetadataTestsPlugin;
import org.eclipse.jst.jsf.metadataprocessors.internal.AbstractMetaDataEnabledType;
import org.eclipse.jst.jsf.metadataprocessors.internal.AttributeValueRuntimeTypeRegistry;

public class AttributeValueRuntimeTypesRegistryTests extends TestCase {
	public final static String BOOLEANTYPE_ID = MetadataTestsPlugin.ID_BUNDLE + ".MyBooleanType";
	public final static String STRINGARRAYTYPE_ID = MetadataTestsPlugin.ID_BUNDLE + ".MyStringArrayType";
	public final static String NOIMPLTYPE_ID = MetadataTestsPlugin.ID_BUNDLE + ".NoImplType";
	
	/*
	 * Test method for 'org.eclipse.jst.jsf.metadataprocessors.internal.AbstractMetaDataEnabledTypeRegistry.getTypes(String)'
	 */
	public void testGetTypes() {
		AttributeValueRuntimeTypeRegistry reg = AttributeValueRuntimeTypeRegistry.getInstance();
		
		Assert.assertNotNull(reg.getType(BOOLEANTYPE_ID));
		Object obj = reg.getType(BOOLEANTYPE_ID);
		Assert.assertTrue( obj instanceof AbstractMetaDataEnabledType);
		AbstractMetaDataEnabledType type = (AbstractMetaDataEnabledType)obj;
		
		Assert.assertTrue(type.getBundleID().equals(MetadataTestsPlugin.ID_BUNDLE));
		Assert.assertTrue(type.getTypeID().equals(BOOLEANTYPE_ID));
		Assert.assertTrue(type.getClassName().equals("org.eclipse.jst.jsf.metadata.tests.metadataprocessing.types.MyBooleanType"));
				
		Assert.assertNotNull(reg.getType(STRINGARRAYTYPE_ID));
		obj = reg.getType(STRINGARRAYTYPE_ID);
		Assert.assertTrue( obj instanceof AbstractMetaDataEnabledType);
		type = (AbstractMetaDataEnabledType)obj;
		
		Assert.assertTrue(type.getBundleID().equals(MetadataTestsPlugin.ID_BUNDLE));
		Assert.assertTrue(type.getTypeID().equals(STRINGARRAYTYPE_ID));
		Assert.assertTrue(type.getClassName().equals("org.eclipse.jst.jsf.taglibprocessing.attributevalues.StringType"));
		
	}

}
