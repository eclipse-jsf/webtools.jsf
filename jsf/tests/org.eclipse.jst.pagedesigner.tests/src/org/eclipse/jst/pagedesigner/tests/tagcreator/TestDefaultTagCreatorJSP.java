/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.pagedesigner.tests.tagcreator;

import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;

public class TestDefaultTagCreatorJSP extends BaseDefaultTagCreatorTestCase 
{
    public TestDefaultTagCreatorJSP()
    {
        super("jsp", "jsp");
    }

    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception 
    {
        super.tearDown();
    }

    public void testIncludeDirective() throws Exception
    {
        doCreateTest(TagIdentifierFactory.createJSPTagWrapper("jsp11", "jsp:directive.include"), "jsp", "jsp", 349,false, null);
        doCreateTest(TagIdentifierFactory.createJSPTagWrapper("jsp11", "jsp:directive.include"), "jspx", "jspx", 492,false, null);
    }
}
