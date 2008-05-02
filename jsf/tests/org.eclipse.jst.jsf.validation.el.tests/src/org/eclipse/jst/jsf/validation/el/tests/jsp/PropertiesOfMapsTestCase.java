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

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;

/**
 * Tests cases with the default property resolver where maps are being
 * queried for their properties.  This is different than with beans, since
 * for unconstrained maps, we often can't fully validate type
 * 
 * @author cbateman
 *
 */
public class PropertiesOfMapsTestCase extends SingleJSPTestCase
{
    public PropertiesOfMapsTestCase()
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
    public void testNoErrorExprs()
    {
        assertNoError(880, Signature.SIG_INT);
        assertNoError(955, TypeConstants.TYPE_STRING);
        assertNoError(1006, TypeConstants.TYPE_STRING);
        assertNoError(1049, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1115, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1183, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1253, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1325, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1369, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1415, TypeConstants.TYPE_JAVAOBJECT);
    }

    @Override
    public void testWarningExprs()
    {
        assertSemanticWarning(1478, null, 1);
        assertSemanticWarning(1519, null, 1);
    }

    @Override
    public void testErrorExprs()
    {
        // do nothing; no error cases
    }
}
