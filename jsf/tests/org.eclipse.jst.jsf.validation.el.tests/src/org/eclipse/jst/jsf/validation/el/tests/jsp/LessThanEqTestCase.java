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
 * Test cases for less than or equal (<=, le)
 * 
 * @author cbateman
 */
public class LessThanEqTestCase extends SingleJSPTestCase
{

    public LessThanEqTestCase() {
        super("/testdata/jsps/lessThanEq.jsp.data", "/lessThanEq.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
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

        assertEquals("myBean.stringProperty <= '3'", ELAssert.getELText(_structuredDocument,827));
        assertEquals("myBean.stringProperty le '3'", ELAssert.getELText(_structuredDocument,888));
        assertEquals("myBean.integerProperty <= 3", ELAssert.getELText(_structuredDocument,949));
        assertEquals("myBean.integerProperty le 3", ELAssert.getELText(_structuredDocument,1009));
        assertEquals("myBean.integerProperty <= '4' ", ELAssert.getELText(_structuredDocument,1069));
        assertEquals("myBean.integerProperty le '4' ", ELAssert.getELText(_structuredDocument,1132));
        assertEquals("myBean.comparableProperty <= myBean.collectionProperty", ELAssert.getELText(_structuredDocument,1196));
        assertEquals("myBean.comparableProperty le myBean.collectionProperty", ELAssert.getELText(_structuredDocument,1283));
        assertEquals("myBean.integerProperty <= -3", ELAssert.getELText(_structuredDocument,1370));
        assertEquals("myBean.doubleProperty <= 5", ELAssert.getELText(_structuredDocument,1431));
        assertEquals("5 le myBean.bigIntegerProperty", ELAssert.getELText(_structuredDocument,1490));
        assertEquals("myBean.bigDoubleProperty <= myBean.bigIntegerProperty", ELAssert.getELText(_structuredDocument,1553));
        assertEquals("myBean.coins <= 'quarter'", ELAssert.getELText(_structuredDocument,1639));
        assertEquals("myBean.coins le 'quarter'", ELAssert.getELText(_structuredDocument,1697));
        assertEquals("myBean.rawEnum <= 'quarter'", ELAssert.getELText(_structuredDocument,1755));
        assertEquals("myBean.coinEnum le 'quarter'", ELAssert.getELText(_structuredDocument,1815));
        assertEquals("myBean.rawEnum <= myBean.coins", ELAssert.getELText(_structuredDocument,1872));
        assertEquals("myBean.coinEnum <= myBean.colors", ELAssert.getELText(_structuredDocument,1931));

        assertEquals("5 <= 3", ELAssert.getELText(_structuredDocument,2022));
        assertEquals("5 le 3", ELAssert.getELText(_structuredDocument,2058));
        assertEquals("'4' <= '34'", ELAssert.getELText(_structuredDocument,2094));
        assertEquals("'4' le '34'", ELAssert.getELText(_structuredDocument,2135));
        assertEquals("'34' <= '34'", ELAssert.getELText(_structuredDocument,2176));
        assertEquals("'34' le '34'", ELAssert.getELText(_structuredDocument,2218));
        assertEquals("-5 <= 2", ELAssert.getELText(_structuredDocument,2260));
        assertEquals("-5 le 2", ELAssert.getELText(_structuredDocument,2297));
        assertEquals("2 <= -5", ELAssert.getELText(_structuredDocument,2334));
        assertEquals("2 le -5", ELAssert.getELText(_structuredDocument,2371));
        assertEquals("-5 <= -5", ELAssert.getELText(_structuredDocument,2408));
        assertEquals("-5 le -5", ELAssert.getELText(_structuredDocument,2446));
        assertEquals("myBean.integerProperty <= null", ELAssert.getELText(_structuredDocument,2484));
        assertEquals("null le myBean.integerProperty", ELAssert.getELText(_structuredDocument,2544));

        assertEquals("5 <= true", ELAssert.getELText(_structuredDocument,2625));
        assertEquals("5 le true", ELAssert.getELText(_structuredDocument,2664));
        assertEquals("myBean.integerProperty <= myBean.booleanProperty", ELAssert.getELText(_structuredDocument,2703));
        assertEquals("myBean.integerProperty le myBean.booleanProperty", ELAssert.getELText(_structuredDocument,2781));
        assertEquals("myBean.stringArrayProperty <= myBean.booleanProperty", ELAssert.getELText(_structuredDocument,2859));
        assertEquals("myBean.stringArrayProperty le myBean.booleanProperty", ELAssert.getELText(_structuredDocument,2941));
        assertEquals("myBean.integerProperty <= true ", ELAssert.getELText(_structuredDocument,3026));
        assertEquals("myBean.integerProperty le true ", ELAssert.getELText(_structuredDocument,3090));
        assertEquals("myBean.booleanProperty <= true", ELAssert.getELText(_structuredDocument,3154));
        assertEquals("myBean.booleanProperty le true", ELAssert.getELText(_structuredDocument,3217));
        assertEquals("true <= false", ELAssert.getELText(_structuredDocument,3278));
        assertEquals("true le false", ELAssert.getELText(_structuredDocument,3322));
        assertEquals("myBean.coins <= myBean.colors", ELAssert.getELText(_structuredDocument,3365));
        assertEquals("myBean.coins le myBean.colors", ELAssert.getELText(_structuredDocument,3424));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(827, Signature.SIG_BOOLEAN);
        assertNoError(888, Signature.SIG_BOOLEAN);
        assertNoError(949, Signature.SIG_BOOLEAN);
        assertNoError(1009, Signature.SIG_BOOLEAN);
        assertNoError(1069, Signature.SIG_BOOLEAN);
        assertNoError(1132, Signature.SIG_BOOLEAN);
        assertNoError(1196, Signature.SIG_BOOLEAN);
        assertNoError(1283, Signature.SIG_BOOLEAN);
        assertNoError(1370, Signature.SIG_BOOLEAN);
        assertNoError(1431, Signature.SIG_BOOLEAN);
        assertNoError(1490, Signature.SIG_BOOLEAN);
        assertNoError(1553, Signature.SIG_BOOLEAN);
        assertNoError(1639, Signature.SIG_BOOLEAN);
        assertNoError(1697, Signature.SIG_BOOLEAN);
        assertNoError(1755, Signature.SIG_BOOLEAN);
        assertNoError(1815, Signature.SIG_BOOLEAN);
        assertNoError(1872, Signature.SIG_BOOLEAN);
        assertNoError(1931, Signature.SIG_BOOLEAN);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticWarning(2022, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2058, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2094, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2135, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2176, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2218, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2260, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2297, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2334, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2371, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2408, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2446, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2484, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2544, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID);
    }

    @Override
    public void testErrorExprs()
    {
        List<ReportedProblem> list = assertSemanticError(2625, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2664, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2703, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2781, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2859, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(2941, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3026, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3090, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3154, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3217, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3278, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3322, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID);

        list = assertSemanticError(3365, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE_ID);

        list = assertSemanticError(3424, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE_ID);
    }
}
