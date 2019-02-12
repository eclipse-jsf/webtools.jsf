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
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.pagedesigner.tests.tagcreator.base.BaseItemCreationToolTestCase;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMDocType;

/**
 * Some basic tests to cover HTML tags
 * @author cbateman
 *
 */
public class TestItemCreationToolHTML extends BaseItemCreationToolTestCase
{
    public TestItemCreationToolHTML()
    {
        super("html");
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

    public void testCreateButton() throws Exception
    {
    	JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(JSFCoreUtilHelper.createSimpleRegistryFactory());
        doCreateTest(TagIdentifierFactory.createJSPTagWrapper(CMDocType.HTML_DOC_TYPE, "INPUT.BUTTON"), "html", "html", 358,false);
        doCreateTest(TagIdentifierFactory.createJSPTagWrapper(CMDocType.HTML_DOC_TYPE, "INPUT.BUTTON"), "xhtml", "xhtml", 350,false);
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(null);
    }
}
