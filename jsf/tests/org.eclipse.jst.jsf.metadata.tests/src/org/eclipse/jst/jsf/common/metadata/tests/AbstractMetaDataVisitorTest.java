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

package org.eclipse.jst.jsf.common.metadata.tests;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.common.metadata.query.AbstractMetaDataVisitor;

public class AbstractMetaDataVisitorTest extends TestCase {
	private NullMetaDataVisitor visitor;
	protected void setUp() throws Exception {
		super.setUp();
		visitor = new NullMetaDataVisitor();		
	}

	public void testFindTraits() {
		Assert.assertNotNull(visitor);
		Assert.assertEquals(false, visitor.stopVisiting());
	}

	private class NullMetaDataVisitor extends AbstractMetaDataVisitor{
		//
	}
	
}
