package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import junit.framework.Assert;

import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValue;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IValidValues;


public class StringTypeTest extends TaglibProcessingTestCase {
	public void testPossibleValues(){		
		Assert.assertNotNull(possibleValueAdapters);
		Assert.assertFalse(possibleValueAdapters.isEmpty());
		
		IPossibleValues pv =(IPossibleValues)getProcessorForTaglibProcessingBundle(possibleValueAdapters);
		Assert.assertTrue(pv.getPossibleValues().size() == 4);
		
		IPossibleValue val = (IPossibleValue)pv.getPossibleValues().get(0);
		Assert.assertFalse(val.getDisplayValue().equals(val.getValue()));
		Assert.assertTrue(val.getIcon().equals("/icons/foo.gif"));
		Assert.assertTrue(val.isDefaultValue());
		
		val = (IPossibleValue)pv.getPossibleValues().get(1);
		Assert.assertFalse(val.getDisplayValue().equals(val.getValue()));
		Assert.assertTrue(val.getIcon().equals("/icons/foo.gif"));
		Assert.assertFalse(val.isDefaultValue());
	}
	
	public void testValidValues(){		
		Assert.assertNotNull(validValuesAdapters);
		Assert.assertFalse(validValuesAdapters.isEmpty());
		
		IValidValues vv =(IValidValues)getProcessorForTaglibProcessingBundle(possibleValueAdapters);
		Assert.assertTrue(vv.isValidValue("A"));
		Assert.assertTrue(vv.isValidValue("B"));
		Assert.assertFalse(vv.isValidValue("a"));

	}
	
	public void testDefaultValues(){		
		Assert.assertNotNull(defaultValueAdapters);
		Assert.assertFalse(defaultValueAdapters.isEmpty());
		
		IDefaultValue dv =(IDefaultValue)getProcessorForTaglibProcessingBundle(possibleValueAdapters);
		Assert.assertNotNull(dv.getDefaultValue());

	}
	
	public void testCreateValues(){		
		Assert.assertNotNull(createValuesAdapters);
		Assert.assertTrue(createValuesAdapters.isEmpty());
	}
	
}
