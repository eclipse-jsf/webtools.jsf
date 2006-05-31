package org.eclipse.jst.jsf.metadata.tests.metadataprocessing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContext;
import org.eclipse.jst.jsf.metadata.tests.metadataprocessing.features.IBarker;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValue;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValues;

public class MetaDataProcessorsFactoryTests extends TestCase {
	private String uri1 = "http://org.eclipse.jsf/metadataprocessing";
	/*
	 * Test method for 'org.eclipse.jst.jsf.metadataprocessors.internal.provisional.MetaDataEnabledProcessorsFactory.getAttributeValueRuntimeTypeProcessors(Class, String, String, String)'
	 */
	public void testGetAttributeValueRuntimeTypeProcessors() {
		IStructuredDocumentContext docContext = null;
		
		List possibleValueProcessors = MetaDataEnabledProcessingFactory.getInstance().
			getAttributeValueRuntimeTypeFeatureProcessors(IPossibleValues.class, docContext, uri1, 
					"MyTag", "MyBooleanAttr");
		Assert.assertNotNull(possibleValueProcessors);
		Assert.assertFalse(possibleValueProcessors.isEmpty());
		Assert.assertTrue(possibleValueProcessors.size() == 2);

		IPossibleValues processor = (IPossibleValues)possibleValueProcessors.get(0);	
		Assert.assertNotNull(processor.getPossibleValues());
//		dumpPossibleValues(processor);		
		
		possibleValueProcessors = MetaDataEnabledProcessingFactory.getInstance().
			getAttributeValueRuntimeTypeFeatureProcessors(IPossibleValues.class, docContext, uri1, 
				"MyTag", "MyValidValsAttr");
		
		Assert.assertNotNull(possibleValueProcessors);
		Assert.assertFalse(possibleValueProcessors.isEmpty());
		processor = (IPossibleValues)possibleValueProcessors.get(0);		
		dumpPossibleValues(processor);
		
		//negative tests
		//not a valid attribute annotation
		possibleValueProcessors = MetaDataEnabledProcessingFactory.getInstance().
		getAttributeValueRuntimeTypeFeatureProcessors(IPossibleValues.class, docContext, uri1, 
				"MyTag", "bogus");  //invalid 
		
		Assert.assertTrue(possibleValueProcessors.isEmpty());
		
		//missing runtime-type annotation
		possibleValueProcessors = MetaDataEnabledProcessingFactory.getInstance().
		getAttributeValueRuntimeTypeFeatureProcessors(IPossibleValues.class, docContext, uri1, 
				"MyTag", "MyNonMetaDataEnabledAttr");  //invalid 
		
		Assert.assertTrue(possibleValueProcessors.isEmpty());
		
		//missing processor type
		possibleValueProcessors = MetaDataEnabledProcessingFactory.getInstance().
		getAttributeValueRuntimeTypeFeatureProcessors(IPossibleValues.class, docContext, uri1, 
				"MyTag", "MyMissingType");  //value in attr-val-runtime-type is invalid
		
		Assert.assertNotNull(possibleValueProcessors);
		Assert.assertTrue(possibleValueProcessors.isEmpty());
		
		//invalid processor - type exists but does not support specified type
		possibleValueProcessors = MetaDataEnabledProcessingFactory.getInstance().
		getAttributeValueRuntimeTypeFeatureProcessors(IPossibleValues.class, docContext, uri1, 
				"MyTag", "MyLongAttr");  //invalid 

		Assert.assertNotNull(possibleValueProcessors);
		Assert.assertTrue(possibleValueProcessors.isEmpty());
		
		//get extended feature - Bark Processors	
		List barkProcessors	= MetaDataEnabledProcessingFactory.getInstance().
		getAttributeValueRuntimeTypeFeatureProcessors(IBarker.class, docContext, uri1, 
				"MyTag", "BooleanAttr");  //invalid 
		
		Assert.assertNotNull(barkProcessors);
		Assert.assertTrue(!barkProcessors.isEmpty());
		Assert.assertTrue(barkProcessors.size() == 1);
		
		IBarker barker = (IBarker)barkProcessors.get(0);
		Iterator it = barker.getBarks().iterator();
		while (it.hasNext()){
			System.out.println((String)it.next());
		}
		
	}
	
	public void testDualTypeTest(){
		System.out.println("---------------- DualTypeTest ------------------");
		//second plugin (metadataprocessingtests2 marks the same attr with another type
		List possibleValueProcessors = MetaDataEnabledProcessingFactory.getInstance().
			getAttributeValueRuntimeTypeFeatureProcessors(IPossibleValues.class, null, uri1, 
				"MyTag", "MyDualTypeAttr");
		Assert.assertNotNull(possibleValueProcessors);
		Assert.assertEquals(2,possibleValueProcessors.size());  //expect StringType + NoImplPossibleVals
		
		List vals = new ArrayList();
		Iterator it = possibleValueProcessors.iterator();
		while (it.hasNext()){
			IPossibleValues p = (IPossibleValues)it.next();
			vals.addAll(p.getPossibleValues());
			dumpPossibleValues(p);
		}
		Assert.assertTrue(vals.size() == 8);
		System.out.println("---------------- END DualTypeTest ------------------");
	}
	
	private void dumpPossibleValues(IPossibleValues p){
		Iterator it = p.getPossibleValues().iterator();
		while (it.hasNext()){
			Object obj = it.next();
			if (obj instanceof String)
				System.out.println((String)obj);
			else if (obj instanceof IPossibleValue)
				System.out.println(((IPossibleValue)obj).getDisplayValue());
		}
	}

}
