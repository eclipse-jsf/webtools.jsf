package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import junit.framework.Assert;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.swt.graphics.RGB;

public class ColorTypeTest extends TaglibProcessingTestCase {
	public void testPossibleValues(){		
		Assert.assertNotNull(possibleValueAdapters);
		Assert.assertFalse(possibleValueAdapters.isEmpty());
		
		IPossibleValues pvs = (IPossibleValues)possibleValueAdapters.get(0);
		Assert.assertTrue(pvs.getPossibleValues().size() == 16 );
		Assert.assertTrue(pvs.getPossibleValues().get(1) instanceof IPossibleValue);	
		IPossibleValue pv = (IPossibleValue)pvs.getPossibleValues().get(1);
		Assert.assertTrue(pv.getValue().equalsIgnoreCase("silver"));
		Assert.assertNotNull(pv.getIcon());
		Assert.assertTrue(pv.getIcon() instanceof ImageDescriptor);
		Assert.assertEquals(192, pv.getIcon().getImageData().getRGBs()[0].red);
		Assert.assertEquals(192, pv.getIcon().getImageData().getRGBs()[0].green);
		Assert.assertEquals(192, pv.getIcon().getImageData().getRGBs()[0].blue);
		
	}
	
	public void testValidValues(){		
		Assert.assertNotNull(validValuesAdapters);
		Assert.assertFalse(validValuesAdapters.isEmpty());
		
		IValidValues vv =(IValidValues)validValuesAdapters.get(0);
		Assert.assertTrue(vv.isValidValue("Black"));
		Assert.assertTrue(vv.isValidValue("BLACK"));
		Assert.assertTrue(vv.isValidValue("black"));
		Assert.assertTrue(vv.isValidValue("    BLACK     "));
		Assert.assertTrue(vv.isValidValue("Silver"));
		Assert.assertTrue(vv.isValidValue("Gray"));
		Assert.assertTrue(vv.isValidValue("White"));
		Assert.assertTrue(vv.isValidValue("Red"));
		Assert.assertTrue(vv.isValidValue("Purple"));
		Assert.assertTrue(vv.isValidValue("Fuchsia"));
		Assert.assertTrue(vv.isValidValue("Green"));
		Assert.assertTrue(vv.isValidValue("Lime"));
		Assert.assertTrue(vv.isValidValue("Olive"));
		Assert.assertTrue(vv.isValidValue("Yellow"));
		Assert.assertTrue(vv.isValidValue("Navy"));
		Assert.assertTrue(vv.isValidValue("Blue"));
		Assert.assertTrue(vv.isValidValue("Teal"));
		Assert.assertTrue(vv.isValidValue("Aqua"));
		
		Assert.assertFalse(vv.isValidValue("BlackAndBlue"));
		vv.getValidationMessages().clear();
		
		Assert.assertTrue(vv.isValidValue("#FFFFFF"));
		Assert.assertTrue(vv.isValidValue("#0FFFFF"));
		Assert.assertTrue(vv.isValidValue("#000001"));
		Assert.assertFalse(vv.isValidValue("1#FFFFF"));
		vv.getValidationMessages().clear();
		Assert.assertFalse(vv.isValidValue("FFFFFF"));
		vv.getValidationMessages().clear();
	}
	
	public void testDefaultValues(){		
		Assert.assertNotNull(defaultValueAdapters);
		Assert.assertFalse(defaultValueAdapters.isEmpty());
		
		IDefaultValue dv =(IDefaultValue)defaultValueAdapters.get(0);
		Assert.assertTrue(dv.getDefaultValue() != null);
		Assert.assertTrue(dv.getDefaultValue().equalsIgnoreCase("Black"));
	}
	
	public void testCreateValues(){		
		Assert.assertNotNull(createValuesAdapters);
		Assert.assertTrue(createValuesAdapters.isEmpty());
	}
	
	public void testGetExpectedReturnType(){
		Assert.assertNotNull(validELValuesAdapters);
		IValidELValues vvel = (IValidELValues)validELValuesAdapters.get(0);
		try {
			Assert.assertEquals("String", vvel.getExpectedRuntimeType().toUserReadableString());
		} catch (ELIsNotValidException e) {
			fail("testGetExpectedReturnType");
		}
	}
}
