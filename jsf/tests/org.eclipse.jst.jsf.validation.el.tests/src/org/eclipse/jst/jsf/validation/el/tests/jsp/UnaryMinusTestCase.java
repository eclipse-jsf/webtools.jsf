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
 * Test cases for unary minus
 * 
 * @author cbateman
 */
public class UnaryMinusTestCase extends SingleJSPTestCase
{
    public UnaryMinusTestCase()
    {
        super("/testdata/jsps/unaryMinus.jsp.data", "/unaryMinus.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("-myBean.integerProperty", ELAssert.getELText(_structuredDocument,828));
        assertEquals("-myBean.doubleProperty", ELAssert.getELText(_structuredDocument,881));
        assertEquals("-myBean.bigDoubleProperty", ELAssert.getELText(_structuredDocument,933));
        assertEquals("-myBean.bigIntegerProperty", ELAssert.getELText(_structuredDocument,988));
        assertEquals("-5", ELAssert.getELText(_structuredDocument,1127));
        assertEquals("-5.5", ELAssert.getELText(_structuredDocument,1162));
        assertEquals("-'5'", ELAssert.getELText(_structuredDocument,1199));
        assertEquals("-'5.5'", ELAssert.getELText(_structuredDocument,1236));

        assertEquals("-myBean.stringProperty", ELAssert.getELText(_structuredDocument,1072));
        assertEquals("-null", ELAssert.getELText(_structuredDocument,1275));

        assertEquals("-false", ELAssert.getELText(_structuredDocument,1332));
        assertEquals("-true", ELAssert.getELText(_structuredDocument,1368));
        assertEquals("-myBean.booleanProperty", ELAssert.getELText(_structuredDocument,1403));
        assertEquals("-myBean.collectionProperty", ELAssert.getELText(_structuredDocument,1456));
        assertEquals("-myBean.mapProperty", ELAssert.getELText(_structuredDocument,1512));
        assertEquals("-myBean.stringArrayProperty", ELAssert.getELText(_structuredDocument,1561));
        assertEquals("-'notANumber'", ELAssert.getELText(_structuredDocument,1618));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(828, Signature.SIG_INT);
        assertNoError(881, Signature.SIG_DOUBLE);
        assertNoError(933, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(988, TypeConstants.TYPE_BIG_INTEGER);
        assertNoError(1127, Signature.SIG_LONG);
        assertNoError(1162, Signature.SIG_DOUBLE);
        assertNoError(1199, Signature.SIG_LONG);
        assertNoError(1236, Signature.SIG_DOUBLE);

    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticWarning(1072, Signature.SIG_LONG, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED_ID);

        list = assertSemanticWarning(1275, Signature.SIG_LONG, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.UNARY_OP_MINUS_ON_NULL_ALWAYS_ZERO_ID);
    }

    @Override
    public void testErrorExprs()
    {
        List<ReportedProblem> list = assertSemanticError(1332, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1368, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1403, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1456, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1512, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1561, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1618, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }

}
