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
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.ELAssert;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test suite for testing validation of arithmetic add
 * 
 * @author cbateman
 *
 */
public class ArithmeticAddTestCase extends SingleJSPTestCase
{
    public ArithmeticAddTestCase()
    {
        super("/testdata/jsps/arithmeticAdd.jsp.data", "/arithmeticAdd.jsp", JSFVersion.V1_1, FACES_CONFIG_FILE_NAME_1_1);
    }
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        super.testSanity();

        assertEquals("myBean.integerProperty  + 3", ELAssert.getELText(_structuredDocument,874));
        assertEquals("myBean.stringProperty + 3", ELAssert.getELText(_structuredDocument,934));
        assertEquals("myBean.integerProperty + myBean.integerProperty", ELAssert.getELText(_structuredDocument,992));
        assertEquals("myBean.bigIntegerProperty + 4", ELAssert.getELText(_structuredDocument,1072));
        assertEquals("myBean.bigIntegerProperty + 5.5", ELAssert.getELText(_structuredDocument,1134));
        assertEquals("myBean.bigDoubleProperty + 5.5", ELAssert.getELText(_structuredDocument,1198));
        assertEquals("myBean.doubleProperty + 5", ELAssert.getELText(_structuredDocument,1261));

        assertEquals("5 + 3", ELAssert.getELText(_structuredDocument,1343));
        assertEquals("5.5 + 4", ELAssert.getELText(_structuredDocument,1378));
        assertEquals("5.5 + null", ELAssert.getELText(_structuredDocument,1415));
        assertEquals("'5' + '4'", ELAssert.getELText(_structuredDocument,1458));
        assertEquals("null + null", ELAssert.getELText(_structuredDocument,1500));
        assertEquals("5.5 + 3.5", ELAssert.getELText(_structuredDocument,1545));

        assertEquals("5 + true", ELAssert.getELText(_structuredDocument,1605));
        assertEquals("myBean.integerProperty + myBean.booleanProperty", ELAssert.getELText(_structuredDocument,1643));
        assertEquals("myBean.stringArrayProperty + myBean.booleanProperty", ELAssert.getELText(_structuredDocument,1720));
        assertEquals("myBean.integerProperty + true ", ELAssert.getELText(_structuredDocument,1804));
        assertEquals("'a' + 'b'", ELAssert.getELText(_structuredDocument,1867));
        assertEquals("myBean.bigIntegerProperty + true", ELAssert.getELText(_structuredDocument,1909));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(874, Signature.SIG_LONG);
        assertNoError(934, Signature.SIG_LONG);
        assertNoError(992, Signature.SIG_LONG);
        assertNoError(1072, TypeConstants.TYPE_BIG_INTEGER);
        assertNoError(1134, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1198, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1261, Signature.SIG_DOUBLE);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticWarning(1343, Signature.SIG_LONG, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1378, Signature.SIG_DOUBLE,  1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1415, Signature.SIG_DOUBLE, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1458, Signature.SIG_LONG,  1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1500, Signature.SIG_LONG,  1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID);

        list = assertSemanticWarning(1545, Signature.SIG_DOUBLE, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
    }

    @Override
    public void testErrorExprs()
    {
        List<ReportedProblem> list = assertSemanticError(1605, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1643, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1720, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1804, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1867, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS_ID);

        list = assertSemanticError(1909, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}