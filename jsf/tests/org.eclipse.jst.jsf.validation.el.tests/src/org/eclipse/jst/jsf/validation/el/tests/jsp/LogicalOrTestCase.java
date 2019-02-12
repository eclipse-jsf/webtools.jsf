/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others.
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

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.ELAssert;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for logical OR (or, ||)
 *
 * @author cbateman
 */
public class LogicalOrTestCase extends SingleJSPTestCase
{
    public LogicalOrTestCase()
    {
        super("/testdata/jsps/logicalOR.jsp.data", "/logicalOR.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("myBean.booleanProperty or myBean.booleanProperty", ELAssert.getELText(_structuredDocument,828));
        assertEquals("myBean.booleanProperty || myBean.booleanProperty", ELAssert.getELText(_structuredDocument,909));
        assertEquals("false || myBean.booleanProperty", ELAssert.getELText(_structuredDocument,990));
        assertEquals("false or myBean.booleanProperty", ELAssert.getELText(_structuredDocument,1054));
        assertEquals("null || myBean.booleanProperty ", ELAssert.getELText(_structuredDocument,1118));
        assertEquals("null or myBean.booleanProperty ", ELAssert.getELText(_structuredDocument,1182));
        assertEquals("'notTrue' || myBean.booleanProperty", ELAssert.getELText(_structuredDocument,1246));
        assertEquals("myBean.booleanProperty || false", ELAssert.getELText(_structuredDocument,1321));
        assertEquals("myBean.booleanProperty or false", ELAssert.getELText(_structuredDocument,1385));
        assertEquals("myBean.booleanProperty || null ", ELAssert.getELText(_structuredDocument,1449));
        assertEquals("myBean.booleanProperty or null", ELAssert.getELText(_structuredDocument,1513));
        assertEquals("myBean.booleanProperty || 'notTrue'", ELAssert.getELText(_structuredDocument,1576));
        assertEquals("myBean.booleanProperty or 'notTrue'", ELAssert.getELText(_structuredDocument,1646));

        assertEquals("'true' or myBean.booleanProperty", ELAssert.getELText(_structuredDocument,1738));
        assertEquals("'true' || myBean.booleanProperty", ELAssert.getELText(_structuredDocument,1804));
        assertEquals("myBean.booleanProperty or true ", ELAssert.getELText(_structuredDocument,1867));
        assertEquals("myBean.booleanProperty || true ", ELAssert.getELText(_structuredDocument,1928));
        assertEquals("myBean.booleanProperty || 'true'", ELAssert.getELText(_structuredDocument,1989));
        assertEquals("false || true", ELAssert.getELText(_structuredDocument,2051));
        assertEquals("null || true", ELAssert.getELText(_structuredDocument,2094));

        assertEquals("myBean.integerProperty || false", ELAssert.getELText(_structuredDocument,2161));
        assertEquals("false || myBean.integerProperty", ELAssert.getELText(_structuredDocument,2225));
        assertEquals("4 || false", ELAssert.getELText(_structuredDocument,2289));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(828, Signature.SIG_BOOLEAN);
        assertNoError(909, Signature.SIG_BOOLEAN);
        assertNoError(990, Signature.SIG_BOOLEAN);
        assertNoError(1054, Signature.SIG_BOOLEAN);
        assertNoError(1118, Signature.SIG_BOOLEAN);
        assertNoError(1182, Signature.SIG_BOOLEAN);
        assertNoError(1246, Signature.SIG_BOOLEAN);
        assertNoError(1321, Signature.SIG_BOOLEAN);
        assertNoError(1385, Signature.SIG_BOOLEAN);
        assertNoError(1449, Signature.SIG_BOOLEAN);
        assertNoError(1513, Signature.SIG_BOOLEAN);
        assertNoError(1576, Signature.SIG_BOOLEAN);
        assertNoError(1646, Signature.SIG_BOOLEAN);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticWarning(1738, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1804, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(1867, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1928, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1989, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2051, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2094, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID);
    }

    @Override
    public void testErrorExprs()
    {
        final List<ReportedProblem> list = assertSemanticError(2161, null,1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        assertSemanticError(2225, null,1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        assertSemanticError(2289, null,1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);
    }
}
