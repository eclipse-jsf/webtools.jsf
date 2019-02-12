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
package org.eclipse.jst.jsf.validation.el.tests.jsp;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.validation.el.tests.base.ELAssert;
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
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(JSFCoreUtilHelper.createSimpleRegistryFactory());
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(null);
    }

    @Override
    public void testSanity()
    {
        assertEquals("beanWithMapProperties.integerProperty", ELAssert.getELText(_structuredDocument,880));
        assertEquals("bundle.bundleProp1", ELAssert.getELText(_structuredDocument,955));
        assertEquals("bundle.x.y", ELAssert.getELText(_structuredDocument,1006));
        assertEquals("beanWithMapProperties.treeMap.foo", ELAssert.getELText(_structuredDocument,1049));
        assertEquals("beanWithMapProperties.treeMap.foo.x", ELAssert.getELText(_structuredDocument,1115));
        assertEquals("beanWithMapProperties.mapProperty.foo", ELAssert.getELText(_structuredDocument,1183));
        assertEquals("beanWithMapProperties.mapProperty.foo.x", ELAssert.getELText(_structuredDocument,1253));
        assertEquals("mapBean.foo", ELAssert.getELText(_structuredDocument,1325));
        assertEquals("mapBean.foo.x", ELAssert.getELText(_structuredDocument,1369));
        assertEquals("mapBean.getIgnoredIntProperty", ELAssert.getELText(_structuredDocument,1415));

        assertEquals("bundle.y", ELAssert.getELText(_structuredDocument,1478));
        assertEquals("bundle.bundleProp1.z", ELAssert.getELText(_structuredDocument,1519));
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
