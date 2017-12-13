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

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.ELAssert;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test suite for arithmetic subtract.
 * 
 * @author cbateman
 *
 */
public class ArithmeticMinusTestCase extends SingleJSPTestCase
{
    public ArithmeticMinusTestCase()
    {
        super("/testdata/jsps/arithmeticMinus.jsp.data", "/WEB-INF/arithmeticMinus.jsp", JSFVersion.V1_1, FACES_CONFIG_FILE_NAME_1_1);
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

        assertEquals("myBean.integerProperty - 3", ELAssert.getELText(_structuredDocument,828));
        assertEquals("myBean.stringProperty - 3", ELAssert.getELText(_structuredDocument,887));
        assertEquals("myBean.integerProperty - myBean.integerProperty", ELAssert.getELText(_structuredDocument,945));
        assertEquals("myBean.bigIntegerProperty - 4", ELAssert.getELText(_structuredDocument,1025));
        assertEquals("myBean.bigIntegerProperty - 5.5", ELAssert.getELText(_structuredDocument,1087));
        assertEquals("myBean.bigDoubleProperty - 5.5", ELAssert.getELText(_structuredDocument,1151));
        assertEquals("myBean.doubleProperty - 5", ELAssert.getELText(_structuredDocument,1214));

        assertEquals("5 - 3", ELAssert.getELText(_structuredDocument,1302));
        assertEquals("5.5 - 4", ELAssert.getELText(_structuredDocument,1337));
        assertEquals("5.5 - null", ELAssert.getELText(_structuredDocument,1374));
        assertEquals("'5' - '4'", ELAssert.getELText(_structuredDocument,1417));
        assertEquals("null - null", ELAssert.getELText(_structuredDocument,1459));
        assertEquals("5.5 - 3.5", ELAssert.getELText(_structuredDocument,1504));

        assertEquals("5 - true", ELAssert.getELText(_structuredDocument,1564));
        assertEquals("myBean.integerProperty - myBean.booleanProperty", ELAssert.getELText(_structuredDocument,1602));
        assertEquals("myBean.stringArrayProperty - myBean.booleanProperty", ELAssert.getELText(_structuredDocument,1679));
        assertEquals("myBean.integerProperty - true ", ELAssert.getELText(_structuredDocument,1763));
        assertEquals("'a' - 'b'", ELAssert.getELText(_structuredDocument,1826));
        assertEquals("myBean.bigIntegerProperty - true", ELAssert.getELText(_structuredDocument,1868));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(828, Signature.SIG_LONG);
        assertNoError(887, Signature.SIG_LONG);
        assertNoError(945, Signature.SIG_LONG);
        assertNoError(1025, TypeConstants.TYPE_BIG_INTEGER);
        assertNoError(1087, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1151, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1214, Signature.SIG_DOUBLE);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticWarning(1302, Signature.SIG_LONG, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1337, Signature.SIG_DOUBLE, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1374, Signature.SIG_DOUBLE, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1417, Signature.SIG_LONG, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1459, Signature.SIG_LONG, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID);

        list = assertSemanticWarning(1504, Signature.SIG_DOUBLE, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
    }

    @Override
    public void testErrorExprs()
    {
        List<ReportedProblem> list = assertSemanticError(1564, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1602, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1679, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1763, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1826, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS_ID);

        list = assertSemanticError(1868, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}
