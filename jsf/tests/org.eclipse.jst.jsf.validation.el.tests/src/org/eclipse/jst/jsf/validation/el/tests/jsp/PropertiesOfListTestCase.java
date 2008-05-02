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
package org.eclipse.jst.jsf.validation.el.tests.jsp;

import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;

public class PropertiesOfListTestCase extends SingleJSPTestCase
{
    public PropertiesOfListTestCase()
    {
        super("/testdata/jsps/propertiesOfMaps.jsp.data", "/propertiesOfMaps.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testErrorExprs() {
        // TODO Auto-generated method stub

    }

    @Override
    public void testNoErrorExprs() {
        // TODO Auto-generated method stub

    }

    @Override
    public void testWarningExprs() {
        // TODO Auto-generated method stub

    }

}
