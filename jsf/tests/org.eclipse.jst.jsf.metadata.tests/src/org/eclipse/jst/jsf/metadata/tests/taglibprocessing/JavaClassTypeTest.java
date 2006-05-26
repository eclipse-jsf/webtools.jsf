package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import junit.framework.Assert;

import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValues;

public class JavaClassTypeTest extends TaglibProcessingTestCase {
	public void testPossibleValues(){
		Assert.assertNotNull(possibleValueAdapters);
		Assert.assertFalse(possibleValueAdapters.isEmpty());
		IPossibleValues pv = (IPossibleValues)possibleValueAdapters.get(0);
		pv.setStructuredDocumentContext(docContext);
//		List vals = pv.getPossibleValues();
//		Assert.assertFalse(vals.isEmpty());
	}
}
