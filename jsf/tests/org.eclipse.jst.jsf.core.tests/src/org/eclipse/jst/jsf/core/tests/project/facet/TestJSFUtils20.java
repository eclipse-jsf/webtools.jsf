/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.project.facet;

import org.eclipse.jst.jsf.core.JSFVersion;


public class TestJSFUtils20 extends TestJSFUtils12
{

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        assertEquals(JSFVersion.V2_0, _jsfUtils.getVersion());
    }

    @Override
    protected JSFVersion getVersionToTestIn()
    {
        return JSFVersion.V2_0;
    }

    //@Test
    public void testUpdateWebApp_ExistingServlet_3_0()
    {
        super.testUpdateWebApp_ExistingServlet("3.0");
    }

    //@Test
    public void testUpdateWebApp_NewServlet_3_0()
    {
        super.testUpdateWebApp_NewServlet("3.0");
    }

    //@Test
    public void testRollbackWebApp_3_0()
    {
        super.testRollbackWebApp("3.0");
    }

    //@Test
    public void testGetFileUrlPath_NonNullCases_3_0()
    {
        super.testGetFileUrlPath_NonNullCases("3.0");
    }

    //@Test
    public void testGetFileUrlPath_NullCases_3_0()
    {
        super.testGetFileUrlPath_NullCases("3.0");
    }
}
