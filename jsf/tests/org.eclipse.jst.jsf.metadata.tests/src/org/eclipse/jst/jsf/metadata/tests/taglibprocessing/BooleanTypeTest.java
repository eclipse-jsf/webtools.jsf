package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;


import junit.framework.Assert;

import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IValidValues;


public class BooleanTypeTest extends TaglibProcessingTestCase {
	public void testPossibleValues(){		
		Assert.assertNotNull(possibleValueAdapters);
		Assert.assertFalse(possibleValueAdapters.isEmpty());
		IPossibleValues pv =(IPossibleValues)getProcessorForTaglibProcessingBundle(possibleValueAdapters);
		Assert.assertTrue(pv.getPossibleValues().size() == 2);
	
		
		//why has this changed?
		//as the tests have added a second impl of IPossibleVals check
		pv =(IPossibleValues)getBarkProcessingBundle(possibleValueAdapters);
		Assert.assertNotNull(pv);
		//however since "barks" metadata is not defined by the annotation file in that bundle, there are no return vals
		Assert.assertTrue(pv.getPossibleValues().isEmpty());

	}
	
	public void testValidValues(){		
		Assert.assertNotNull(validValuesAdapters);
		Assert.assertFalse(validValuesAdapters.isEmpty());
		IValidValues vv =(IValidValues)getProcessorForTaglibProcessingBundle(validValuesAdapters);
		vv.getValidationMessages().clear();
		Assert.assertTrue(vv.isValidValue("true"));
		Assert.assertTrue(vv.getValidationMessages().size()==0);
		vv.getValidationMessages().clear();
		Assert.assertTrue(vv.isValidValue("false"));
		vv.getValidationMessages().clear();
		Assert.assertTrue(vv.isValidValue("False"));
		Assert.assertEquals(vv.getValidationMessages().size(), 0);
		vv.getValidationMessages().clear();
		Assert.assertEquals(vv.isValidValue("blue"), true);//valueOf("bougus") == false so it is a valid boolean
		Assert.assertEquals(vv.getValidationMessages().size(), 0);
	}
	
	public void testDefaultValues(){		
		Assert.assertNotNull(defaultValueAdapters);
		Assert.assertFalse(defaultValueAdapters.isEmpty());
		Assert.assertTrue(((IDefaultValue)getProcessorForTaglibProcessingBundle(defaultValueAdapters)).getDefaultValue().equals("true"));
	}
	
	public void testCreateValues(){		
		Assert.assertNotNull(createValuesAdapters);
		Assert.assertTrue(createValuesAdapters.isEmpty());
	}
	
}
