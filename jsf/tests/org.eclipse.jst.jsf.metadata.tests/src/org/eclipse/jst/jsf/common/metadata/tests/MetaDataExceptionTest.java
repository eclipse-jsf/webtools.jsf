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

package org.eclipse.jst.jsf.common.metadata.tests;

import java.io.IOException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.common.metadata.query.MetaDataException;

public class MetaDataExceptionTest extends TestCase {

	public void testMetaDataException() {
		MetaDataException e = new MetaDataException();
		Assert.assertNotNull(e);
	}

	public void testMetaDataExceptionString() {
		MetaDataException e = new MetaDataException("Foo");
		Assert.assertNotNull(e);
		Assert.assertEquals("Foo", e.getMessage());
	}

	public void testMetaDataExceptionStringThrowable() {
		IOException cause = new IOException("FooBar");
		MetaDataException e = new MetaDataException("Foo", cause );
		Assert.assertNotNull(e);
		Assert.assertEquals(cause, e.getCause());
		Assert.assertNotNull(e.getCause());
	}

}
