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
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for greater than or equal (ge, >=)
 * 
 * @author cbateman
 *
 */
public class GreaterThanEqTestCase extends SingleJSPTestCase {

    public GreaterThanEqTestCase()
    {
        super("/testdata/jsps/greaterThanEq.jsp.data", "/greaterThanEq.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
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
        assertEquals("myBean.stringProperty >= '3'", getELText(_structuredDocument,828));
        assertEquals("myBean.stringProperty ge '3'", getELText(_structuredDocument,889));
        assertEquals("myBean.integerProperty >= 3", getELText(_structuredDocument,950));
        assertEquals("myBean.integerProperty ge 3", getELText(_structuredDocument,1010));
        assertEquals("myBean.integerProperty >= '4' ", getELText(_structuredDocument,1070));
        assertEquals("myBean.integerProperty ge '4' ", getELText(_structuredDocument,1133));
        assertEquals("myBean.comparableProperty >= myBean.collectionProperty", getELText(_structuredDocument,1197));
        assertEquals("myBean.comparableProperty ge myBean.collectionProperty", getELText(_structuredDocument,1284));
        assertEquals("myBean.integerProperty >= -3", getELText(_structuredDocument,1373));
        assertEquals("myBean.doubleProperty >= 5", getELText(_structuredDocument,1434));
        assertEquals("5 ge myBean.bigIntegerProperty", getELText(_structuredDocument,1493));
        assertEquals("myBean.bigDoubleProperty >= myBean.bigIntegerProperty", getELText(_structuredDocument,1556));
        assertEquals("myBean.coins >= 'quarter'", getELText(_structuredDocument,1642));
        assertEquals("myBean.coins ge 'quarter'", getELText(_structuredDocument,1700));
        assertEquals("myBean.rawEnum >= 'quarter'", getELText(_structuredDocument,1758));
        assertEquals("myBean.coinEnum ge 'quarter'", getELText(_structuredDocument,1818));
        assertEquals("myBean.rawEnum >= myBean.coins", getELText(_structuredDocument,1875));
        assertEquals("myBean.coinEnum >= myBean.colors", getELText(_structuredDocument,1934));

        assertEquals("5 >= 3", getELText(_structuredDocument,2026));
        assertEquals("5 ge 3", getELText(_structuredDocument,2062));
        assertEquals("'4' >= '34'", getELText(_structuredDocument,2098));
        assertEquals("'4' ge '34'", getELText(_structuredDocument,2139));
        assertEquals("'34' >= '34'", getELText(_structuredDocument,2180));
        assertEquals("'34' ge '34'", getELText(_structuredDocument,2222));
        assertEquals("-5 >= 2", getELText(_structuredDocument,2264));
        assertEquals("-5 ge 2", getELText(_structuredDocument,2301));
        assertEquals("2 >= -5", getELText(_structuredDocument,2338));
        assertEquals("2 ge -5", getELText(_structuredDocument,2375));
        assertEquals("-5 >= -5", getELText(_structuredDocument,2412));
        assertEquals("-5 ge -5", getELText(_structuredDocument,2450));
        assertEquals("myBean.integerProperty >= null", getELText(_structuredDocument,2488));
        assertEquals("null ge myBean.integerProperty", getELText(_structuredDocument,2548));

        assertEquals("5 >= true", getELText(_structuredDocument,2629));
        assertEquals("5 ge true", getELText(_structuredDocument,2668));
        assertEquals("myBean.integerProperty >= myBean.booleanProperty", getELText(_structuredDocument,2707));
        assertEquals("myBean.integerProperty ge myBean.booleanProperty", getELText(_structuredDocument,2785));
        assertEquals("myBean.stringArrayProperty >= myBean.booleanProperty", getELText(_structuredDocument,2863));
        assertEquals("myBean.stringArrayProperty ge myBean.booleanProperty", getELText(_structuredDocument,2945));
        assertEquals("myBean.integerProperty >= true ", getELText(_structuredDocument,3030));
        assertEquals("myBean.integerProperty ge true ", getELText(_structuredDocument,3094));
        assertEquals("myBean.booleanProperty >= true", getELText(_structuredDocument,3158));
        assertEquals("myBean.booleanProperty ge true", getELText(_structuredDocument,3221));
        assertEquals("true >= false", getELText(_structuredDocument,3282));
        assertEquals("true ge false", getELText(_structuredDocument,3326));
        assertEquals("myBean.coins >= myBean.colors", getELText(_structuredDocument,3369));
        assertEquals("myBean.coins ge myBean.colors", getELText(_structuredDocument,3428));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(828, Signature.SIG_BOOLEAN);
        assertNoError(889, Signature.SIG_BOOLEAN);
        assertNoError(950, Signature.SIG_BOOLEAN);
        assertNoError(1010, Signature.SIG_BOOLEAN);
        assertNoError(1070, Signature.SIG_BOOLEAN);
        assertNoError(1133, Signature.SIG_BOOLEAN);
        assertNoError(1197, Signature.SIG_BOOLEAN);
        assertNoError(1284, Signature.SIG_BOOLEAN);
        assertNoError(1373, Signature.SIG_BOOLEAN);
        assertNoError(1434, Signature.SIG_BOOLEAN);
        assertNoError(1493, Signature.SIG_BOOLEAN);
        assertNoError(1556, Signature.SIG_BOOLEAN);
        assertNoError(1642, Signature.SIG_BOOLEAN);
        assertNoError(1700, Signature.SIG_BOOLEAN);
        assertNoError(1758, Signature.SIG_BOOLEAN);
        assertNoError(1818, Signature.SIG_BOOLEAN);
        assertNoError(1875, Signature.SIG_BOOLEAN);
        assertNoError(1934, Signature.SIG_BOOLEAN);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticWarning(2026, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2062, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2098, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2139, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2180, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2222, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2264, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2301, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2338, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2375, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2412, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2450, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2488, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2548, Signature.SIG_BOOLEAN, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

    }

    @Override
    public void testErrorExprs()
    {
        List<ReportedProblem> list = assertSemanticError(2629, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2668, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2707, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2785, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2863, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(2945, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3030, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3094, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3158, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3221, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3282, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3326, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3369, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE_ID);

        list = assertSemanticError(3428, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE_ID);
    }
}
