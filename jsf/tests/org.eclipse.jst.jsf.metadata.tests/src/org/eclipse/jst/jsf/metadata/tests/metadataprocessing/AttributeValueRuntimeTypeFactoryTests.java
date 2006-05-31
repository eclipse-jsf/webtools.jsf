package org.eclipse.jst.jsf.metadata.tests.metadataprocessing;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.metadata.tests.Activator;
import org.eclipse.jst.jsf.metadataprocessors.internal.AttributeValueRuntimeTypeFactory;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.ITypeDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValues;

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
		Assert.assertTrue(atype.getTypeExtension().getBundleID().equals(Activator.ID_BUNDLE));		
		Assert.assertTrue(atype.getTypeExtension().getTypeID().equals(AttributeValueRuntimeTypesRegistryTests.BOOLEANTYPE_ID));
		List features =  atype.getFeatureAdapters(IPossibleValues.class);
		Assert.assertNotNull(features);
		Assert.assertFalse(features.isEmpty());
		Assert.assertEquals(features.size(), 2);  //we expect MyBooleanType and MetaDataEnabledBarkProcessor  
		
		atype = factory.getType(AttributeValueRuntimeTypesRegistryTests.STRINGARRAYTYPE_ID);
		Assert.assertNotNull(atype);
		Assert.assertNotNull(atype.getTypeExtension().getClassName());
		Assert.assertTrue(atype.getTypeExtension().getBundleID().equals(Activator.ID_BUNDLE));		
		Assert.assertTrue(atype.getTypeExtension().getTypeID().equals(AttributeValueRuntimeTypesRegistryTests.STRINGARRAYTYPE_ID));

	}
	
	public void testNoImplTest(){
		AttributeValueRuntimeTypeFactory factory = AttributeValueRuntimeTypeFactory.getInstance();
		Assert.assertNotNull(factory);
		
		ITypeDescriptor atype = factory.getType(AttributeValueRuntimeTypesRegistryTests.NOIMPLTYPE_ID);
		Assert.assertNotNull(atype);
		Assert.assertNotNull(atype.getTypeExtension().getClassName());
		Assert.assertTrue(atype.getTypeExtension().getBundleID().equals(Activator.ID_BUNDLE));	
		
	}


}
