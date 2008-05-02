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
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test suite for arithmetic remainder (mod, %)
 * @author cbateman
 *
 */
public class ArithmeticModuloTestCase extends SingleJSPTestCase
{
    public ArithmeticModuloTestCase()
    {
        super("/testdata/jsps/arithmeticModulo.jsp.data", "/WEB-INF/arithmeticModulo.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
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

        assertEquals("myBean.integerProperty % 3", getELText(_structuredDocument,828));
        assertEquals("myBean.integerProperty mod 3", getELText(_structuredDocument,887));
        assertEquals("myBean.stringProperty % 3", getELText(_structuredDocument,948));
        assertEquals("myBean.stringProperty mod 3", getELText(_structuredDocument,1006));
        assertEquals("myBean.integerProperty % myBean.integerProperty", getELText(_structuredDocument,1066));
        assertEquals("myBean.integerProperty mod myBean.integerProperty", getELText(_structuredDocument,1146));
        assertEquals("myBean.bigIntegerProperty mod 4", getELText(_structuredDocument,1228));
        assertEquals("myBean.doubleProperty mod 4", getELText(_structuredDocument,1292));
        assertEquals("myBean.doubleProperty mod 5.43", getELText(_structuredDocument,1351));

        assertEquals("5 % 3", getELText(_structuredDocument,1437));
        assertEquals("5 mod 3", getELText(_structuredDocument,1472));
        assertEquals("5.5 % 4 ", getELText(_structuredDocument,1509));
        assertEquals("5.5 mod 4 ", getELText(_structuredDocument,1547));
        assertEquals("'5' % '4'", getELText(_structuredDocument,1590));
        assertEquals("'5' mod '4'", getELText(_structuredDocument,1632));
        assertEquals("null % null", getELText(_structuredDocument,1676));
        assertEquals("null mod null", getELText(_structuredDocument,1720));

        assertEquals("5 % true", getELText(_structuredDocument,1784));
        assertEquals("5 mod true", getELText(_structuredDocument,1822));
        assertEquals("myBean.stringArrayProperty % myBean.booleanProperty", getELText(_structuredDocument,1862));
        assertEquals("myBean.stringArrayProperty mod myBean.booleanProperty", getELText(_structuredDocument,1943));
        assertEquals("myBean.integerProperty % true ", getELText(_structuredDocument,2029));
        assertEquals("myBean.integerProperty mod true ", getELText(_structuredDocument,2092));
        assertEquals("'a' % 'b'", getELText(_structuredDocument,2157));
        assertEquals("'a' mod 'b'", getELText(_structuredDocument,2199));
        assertEquals("5.5 % null", getELText(_structuredDocument,2240));
        assertEquals("5.5 mod null", getELText(_structuredDocument,2280));
        assertEquals("5%0", getELText(_structuredDocument,2322));
        assertEquals("5 mod 0", getELText(_structuredDocument,2355));
        assertEquals("myBean.integerProperty % myBean.booleanProperty", getELText(_structuredDocument,2392));
        assertEquals("myBean.integerProperty mod myBean.booleanProperty", getELText(_structuredDocument,2469));
        assertEquals("myBean.bigIntegerProperty % myBean.booleanProperty", getELText(_structuredDocument,2548));
        assertEquals("myBean.bigIntegerProperty mod null", getELText(_structuredDocument,2628));
        assertEquals("myBean.doubleProperty % true", getELText(_structuredDocument,2691));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(828,Signature.SIG_LONG);
        assertNoError(887, Signature.SIG_LONG);
        assertNoError(948, Signature.SIG_LONG);
        assertNoError(1006, Signature.SIG_LONG);
        assertNoError(1066, Signature.SIG_LONG);
        assertNoError(1146, Signature.SIG_LONG);
        assertNoError(1228, TypeConstants.TYPE_BIG_INTEGER);
        assertNoError(1292, Signature.SIG_DOUBLE);
        assertNoError(1351, Signature.SIG_DOUBLE);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticWarning(1437, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1472, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1509, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1547, Signature.SIG_DOUBLE, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1590, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1632, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1676, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID);

        list = assertSemanticWarning(1720, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID);

    }

    @Override
    public void testErrorExprs()
    {
        List<ReportedProblem> list = assertSemanticError(1784, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1822, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1862, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1943, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2029, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2092, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2157, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2199, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2240, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2280, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2322, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2355, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2392, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2469, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2548, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2628, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2691, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}
