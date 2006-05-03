package org.eclipse.jst.jsf.metadata.tests.metadataprocessing;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.metadata.tests.Activator;
import org.eclipse.jst.jsf.metadataprocessors.internal.AttributeValueRuntimeTypeFactory;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.ITypeDescriptor;

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
		Assert.assertNotNull(atype.getRuntimeType().getClassName());
		Assert.assertTrue(atype.getRuntimeType().getBundleID().equals(Activator.ID_BUNDLE));		
		Assert.assertTrue(atype.getRuntimeType().getTypeID().equals(AttributeValueRuntimeTypesRegistryTests.BOOLEANTYPE_ID));
//		List features =  atype.getFeatureAdapters(IPossibleValues.class);
//		Assert.assertNotNull(features);
//		Assert.assertFalse(features.isEmpty());
//		Assert.assertTrue(features.size() == 1);    
		
		atype = factory.getType(AttributeValueRuntimeTypesRegistryTests.STRINGARRAYTYPE_ID);
		Assert.assertNotNull(atype);
		Assert.assertNotNull(atype.getRuntimeType().getClassName());
		Assert.assertTrue(atype.getRuntimeType().getBundleID().equals(Activator.ID_BUNDLE));		
		Assert.assertTrue(atype.getRuntimeType().getTypeID().equals(AttributeValueRuntimeTypesRegistryTests.STRINGARRAYTYPE_ID));

	}
	
	public void testNoImplTest(){
		AttributeValueRuntimeTypeFactory factory = AttributeValueRuntimeTypeFactory.getInstance();
		Assert.assertNotNull(factory);
		
		ITypeDescriptor atype = factory.getType(AttributeValueRuntimeTypesRegistryTests.NOIMPLTYPE_ID);
		Assert.assertNotNull(atype);
		Assert.assertNotNull(atype.getRuntimeType().getClassName());
		Assert.assertTrue(atype.getRuntimeType().getBundleID().equals(Activator.ID_BUNDLE));	
		
	}


}
