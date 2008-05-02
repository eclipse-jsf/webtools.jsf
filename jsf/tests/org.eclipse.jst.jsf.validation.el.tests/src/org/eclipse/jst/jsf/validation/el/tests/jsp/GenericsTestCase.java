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

public class GenericsTestCase extends SingleJSPTestCase
{
    public GenericsTestCase()
    {
        super("/testdata/jsps/propertiesOfMaps.jsp.data", "/propertiesOfMaps.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("beanWithMapProperties.integerProperty", getELText(_structuredDocument,880));
        assertEquals("bundle.bundleProp1", getELText(_structuredDocument,955));
        assertEquals("bundle.x.y", getELText(_structuredDocument,1006));
        assertEquals("beanWithMapProperties.treeMap.foo", getELText(_structuredDocument,1049));
        assertEquals("beanWithMapProperties.treeMap.foo.x", getELText(_structuredDocument,1115));
        assertEquals("beanWithMapProperties.mapProperty.foo", getELText(_structuredDocument,1183));
        assertEquals("beanWithMapProperties.mapProperty.foo.x", getELText(_structuredDocument,1253));
        assertEquals("mapBean.foo", getELText(_structuredDocument,1325));
        assertEquals("mapBean.foo.x", getELText(_structuredDocument,1369));
        assertEquals("mapBean.getIgnoredIntProperty", getELText(_structuredDocument,1415));
        assertEquals("bundle.y", getELText(_structuredDocument,1478));
        assertEquals("bundle.bundleProp1.z", getELText(_structuredDocument,1519));
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
