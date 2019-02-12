/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
