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

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.query.AbstractEntityQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.EmptyResultSet;

public class AbstractEntityQueryVisitorTests extends TestCase {
	private NullEntityQueryVisitor visitor;
	protected void setUp() throws Exception {
		super.setUp();
		visitor = new NullEntityQueryVisitor();		
	}

	public void testFindEntities() {
		Assert.assertNotNull(visitor);
		Assert.assertEquals(EmptyResultSet.class, visitor.findEntities(null, null).getClass());
	}

	private class NullEntityQueryVisitor extends AbstractEntityQueryVisitor{

		@Override
		public void visit(Entity entity) {
			// nada			
		}

	}
	
}
