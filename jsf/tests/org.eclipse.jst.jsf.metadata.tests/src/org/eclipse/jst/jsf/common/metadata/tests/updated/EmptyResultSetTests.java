/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.tests.updated;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.common.metadata.query.EmptyResultSet;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataException;

public class EmptyResultSetTests extends TestCase {
	private IResultSet _results;
	
	public void setUp() throws Exception {
		_results = new EmptyResultSet();
	}
	
	public void testCloseAndIsClosed() {
		Assert.assertFalse(_results.isClosed());
		try {
			_results.close();
			Assert.assertTrue(_results.isClosed());
		} catch (MetaDataException e) {
			fail("testCloseAndIsClosed with Exception: "+e.getMessage());
		}
	}

	public void testGetResults() {		
		try {
			Assert.assertEquals(0, _results.getResults().size());
		} catch (MetaDataException e) {
			fail("testGetResults: should NOT be MetaDataException");
		}
		try {
			_results.close();
			Assert.assertEquals(0, _results.getResults().size());
			fail("testGetResults: should be MetaDataException");		
		} catch (MetaDataException e) {
			//test passes
		}
	}


}
