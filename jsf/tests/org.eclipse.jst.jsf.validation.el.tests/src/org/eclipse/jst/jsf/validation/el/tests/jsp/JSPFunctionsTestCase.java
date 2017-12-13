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

import java.util.List;

import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.ELAssert;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for functions
 * 
 * @author cbateman
 */
public class JSPFunctionsTestCase extends SingleJSPTestCase
{
    public JSPFunctionsTestCase() {
        super("/testdata/jsps/jspFunctions.jsp.data", "/jspFunctions.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("f:someFunc(6) > 8", ELAssert.getELText(_structuredDocument,940));
        assertEquals("f:someFunc(6)", ELAssert.getELText(_structuredDocument,990));
        assertEquals("f:someFunc(true)", ELAssert.getELText(_structuredDocument,1036));

        assertEquals("someFunc(6) > 8", ELAssert.getELText(_structuredDocument,1226));
        assertEquals("someFunc(6)", ELAssert.getELText(_structuredDocument,1274));
        assertEquals("someFunc(true)", ELAssert.getELText(_structuredDocument,1318));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(940, null);
        assertNoError(990, null);
        assertNoError(1036, null);
    }

    @Override
    public void testWarningExprs()
    {
        // note: this will change to non-error when functions are properly supported
        // by the parser
        List<ReportedProblem> list = assertSyntaxWarning(1226, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID);

        list = assertSyntaxWarning(1274, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID);

        list = assertSyntaxWarning(1318, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID);
    }

    @Override
    public void testErrorExprs()
    {
        // no error cases
    }

}
