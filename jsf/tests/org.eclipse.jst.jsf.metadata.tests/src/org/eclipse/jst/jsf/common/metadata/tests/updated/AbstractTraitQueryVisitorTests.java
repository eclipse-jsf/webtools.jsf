/*******************************************************************************
 * Copyright (c) 2007, 2010 Oracle Corporation and others.
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

package org.eclipse.jst.jsf.common.metadata.tests.updated;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.AbstractTraitQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.EmptyResultSet;

public class AbstractTraitQueryVisitorTests extends TestCase {
	private NullTraitQueryVisitor visitor;
	protected void setUp() throws Exception {
		super.setUp();
		visitor = new NullTraitQueryVisitor();		
	}

	public void testFindTraits() {
		Assert.assertNotNull(visitor);
		Assert.assertEquals(EmptyResultSet.class, visitor.findTraits(null, null).getClass());
	}

	private class NullTraitQueryVisitor extends AbstractTraitQueryVisitor{

		@Override
		public void visit(Trait trait) {
			//nada		
		}

		
	}
	
}
