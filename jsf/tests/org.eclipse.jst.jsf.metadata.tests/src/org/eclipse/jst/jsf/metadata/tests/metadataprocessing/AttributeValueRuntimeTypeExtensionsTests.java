package org.eclipse.jst.jsf.metadata.tests.metadataprocessing;

import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.metadata.tests.metadataprocessing.features.IBarker;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValues;

public class AttributeValueRuntimeTypeExtensionsTests extends TestCase {
	private String uri1 = "http://org.eclipse.jsf/metadataprocessing";
	
	public void testBarkerExt(){
		List barkProcessors = MetaDataEnabledProcessingFactory.getInstance().
		getAttributeValueRuntimeTypeFeatureProcessors(IBarker.class, null, uri1, 
				"MyTag", "MyLongAttr");  //invalid 
		
		Assert.assertNotNull(barkProcessors);
		Assert.assertTrue(barkProcessors.isEmpty());
		
		
	}
	public void testGetPossibleValsWithExtensions(){
		List processors	= MetaDataEnabledProcessingFactory.getInstance().
			getAttributeValueRuntimeTypeFeatureProcessors(IPossibleValues.class, null, uri1, 
				"MyTag", "BooleanAttr");   
		
		Assert.assertNotNull(processors);
		Assert.assertTrue(!processors.isEmpty());
		Assert.assertTrue(processors.size() == 2); //there should be a Boolean and BarkProcessor capable of handling IPossibleValues
//		Iterator it = processors.iterator();
//		while (it.hasNext()){
//			dumpPossibleValues((IPossibleValues)it.next());
//		}

	}
	
	public void testNonFeatureExt(){
		List testProcessors = MetaDataEnabledProcessingFactory.getInstance().
		getAttributeValueRuntimeTypeFeatureProcessors(Test.class//invalid
				,null, uri1, "MyTag", "BooleanAttr");  
		
		Assert.assertNotNull(testProcessors);
		Assert.assertTrue(testProcessors.isEmpty());
		
		
	}
	private void dumpPossibleValues(IPossibleValues p){
		Iterator it = p.getPossibleValues().iterator();
		while (it.hasNext()){
			System.out.println((String)it.next());
		}
	}
}
