/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import junit.framework.Assert;

import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;

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
