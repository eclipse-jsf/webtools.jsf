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

import java.util.List;

import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.ELAssert;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for bean variable resolution
 * 
 * @author cbateman
 *
 */
public class BeanVariableResolutionTestCase extends SingleJSPTestCase
{
    public BeanVariableResolutionTestCase() {
        super("/testdata/jsps/beanVariableResolution.jsp.data", "/beanVariableResolution.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("myBean", ELAssert.getELText(_structuredDocument,523));
        assertEquals("myBeanSubClass", ELAssert.getELText(_structuredDocument,562));
        assertEquals("mapBean", ELAssert.getELText(_structuredDocument,609));
        assertEquals("mapBean1", ELAssert.getELText(_structuredDocument,649));
        assertEquals("hiddenBean", ELAssert.getELText(_structuredDocument,690));
        assertEquals("myBean_none", ELAssert.getELText(_structuredDocument,733));
        assertEquals("myBeanSettable", ELAssert.getELText(_structuredDocument,777));

        assertEquals("myBean1", ELAssert.getELText(_structuredDocument,851));
        assertEquals("someOtherBeanName", ELAssert.getELText(_structuredDocument,891));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(523, "Lbeans.MyBean;");
        assertNoError(562, "Lbeans.MyBeanSubClass;");
        assertNoError(609, "Lbeans.MapBean;");
        assertNoError(649, "Lbeans.MapBean;");
        assertNoError(690, "Lbeans.MyBean;");
        assertNoError(733, "Lbeans.MyBean;");
        assertNoError(777, "Lbeans.MyBeanSettable;");
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticInfo(851, null, 1);
        ELAssert.assertContainsProblem(list,DiagnosticFactory.VARIABLE_NOT_FOUND_ID);

        list = assertSemanticInfo(891, null, 1);
        ELAssert.assertContainsProblem(list,DiagnosticFactory.VARIABLE_NOT_FOUND_ID);
    }

    @Override
    public void testErrorExprs()
    {
        // no error cases
    }

}
