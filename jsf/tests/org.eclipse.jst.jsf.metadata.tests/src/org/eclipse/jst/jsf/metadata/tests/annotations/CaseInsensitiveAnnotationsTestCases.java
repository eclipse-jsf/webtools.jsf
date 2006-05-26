package org.eclipse.jst.jsf.metadata.tests.annotations;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationHelper;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationPropertyValue;
/**
 * Ensure case insensitive meta-data can be located correctly.
 * Uses /testfiles/metadata/CaseInsensitive.xml
 * 
 * @author Gerry Kessler - Oracle
 */
public class CaseInsensitiveAnnotationsTestCases extends TestCase {
	private String uri = "http://org.eclipse.jsf/caseInsensitiveTest";
	private String cmElementName = "FoO";
	private String cmAttributeName = "ValuE";
	private String meta_prop_name = "TypE";
	private String meta_prop_value = "String";

	public void testCIElementAnnotations(){
		Assert.assertEquals(CMAnnotationHelper.getCMElementProperties(uri, cmElementName, meta_prop_name).size(), 1);
		List props = CMAnnotationHelper.getCMElementProperties(uri, cmElementName, meta_prop_name);
		for (int i = 0;i<props.size();i++){
			Assert.assertTrue(props.get(i) instanceof CMAnnotationPropertyValue);
			CMAnnotationPropertyValue prop = (CMAnnotationPropertyValue) props.get(i);
			Assert.assertTrue(prop.getPropertyValues().size() == 1);
			Assert.assertEquals(prop.getPropertyValue(), meta_prop_value + String.valueOf(i+1));
		}
	}
	
	public void testCIDupeAttributeAnnotations(){
		Assert.assertEquals(CMAnnotationHelper.getCMAttributeProperties(uri, cmElementName, cmAttributeName, meta_prop_name).size(), 1);
		List props = CMAnnotationHelper.getCMAttributeProperties(uri, cmElementName, cmAttributeName, meta_prop_name);
		for (int i = 0;i<props.size();i++){
			Assert.assertTrue(props.get(i) instanceof CMAnnotationPropertyValue);
			CMAnnotationPropertyValue prop = (CMAnnotationPropertyValue) props.get(i);
			Assert.assertTrue(prop.getPropertyValues().size() == 1);
			Assert.assertEquals(prop.getPropertyValue(), meta_prop_value + String.valueOf(i+1));
		}
	}

}
