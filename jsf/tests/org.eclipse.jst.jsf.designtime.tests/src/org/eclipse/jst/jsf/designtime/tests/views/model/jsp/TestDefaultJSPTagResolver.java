/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.tests.views.model.jsp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.DefaultJSPTagResolver;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.JSPTagResolvingStrategy;

public class TestDefaultJSPTagResolver extends BaseStrategyTestClass
{

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

    public void testGetId()
    {
        assertEquals("org.eclipse.jst.jsf.metadata.DefaultJSPTagResolver", _strategy.getId());
    }

    @Override
    protected JSPTagResolvingStrategy createStrategy()
    {
        return new DefaultJSPTagResolver(_webProjectTestEnv.getTestProject());
    }

    @Override
    protected List<String> getTestUris()
    {
        return Collections.unmodifiableList(Arrays.asList(new String[]
        { ITLDConstants.URI_JSF_CORE, ITLDConstants.URI_JSF_HTML }));
    }
}
