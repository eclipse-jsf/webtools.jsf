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
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Used to verify that marker offset/lengths are calculated correctly
 * for each type error message.  This test case should aim for coverage
 * of all diagnostic creation for EL.  Note that we can't currently get coverage
 * on ternaries (parser bug), empty bracket or missing bracket because these things
 * are validated outside the EL validator.
 * 
 * @author cbateman
 *
 */
public class MarkerOffsetsTestCase extends SingleJSPTestCase
{
    public MarkerOffsetsTestCase() {
        super("/testdata/jsps/markerOffsets.jsp.data", "/markerOffsets.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("5 + 3", getELText(_structuredDocument,880));
        assertEquals("null / null", getELText(_structuredDocument,915));
        assertEquals("myBean.integerProperty == null", getELText(_structuredDocument,956));
        assertEquals("false && myBean.booleanProperty", getELText(_structuredDocument,1015));
        assertEquals("myBean.booleanProperty && false", getELText(_structuredDocument,1075));
        assertEquals("!false", getELText(_structuredDocument,1135));
        assertEquals("empty 5", getELText(_structuredDocument,1171));
        assertEquals("-null", getELText(_structuredDocument,1208));
        assertEquals("-myBean.stringProperty", getELText(_structuredDocument,1243));
        assertEquals("myBean.integerProperty - (5 + 3)", getELText(_structuredDocument,1376));
        assertEquals("myBean.booleanProperty && myBean.integerProperty + 5 == null", getELText(_structuredDocument,1438));
        assertEquals("notABean.stringProperty", getELText(_structuredDocument,1569));
        assertEquals("myBean.notAProperty", getELText(_structuredDocument,1622));
        assertEquals("myBean.integerProperty + 5 + myBean.notAProperty", getELText(_structuredDocument,1671));
        assertEquals("myBean.integerProperty++", getELText(_structuredDocument,1769));
        assertEquals("myBean.", getELText(_structuredDocument,1823));
        assertEquals(" ", getELText(_structuredDocument,1860));

        assertEquals("myBean.integerProperty / 0", getELText(_structuredDocument,1978));
        assertEquals("myBean.integerProperty + myBean.booleanProperty", getELText(_structuredDocument,2034));
        assertEquals("myBean.integerProperty && myBean.booleanProperty", getELText(_structuredDocument,2111));
        assertEquals("myBean.booleanProperty >= myBean.collectionProperty", getELText(_structuredDocument,2189));
        assertEquals("5 + 'noNumberConversion'", getELText(_structuredDocument,2270));
        assertEquals("-true", getELText(_structuredDocument,2324));
        assertEquals("!5", getELText(_structuredDocument,2359));
        assertEquals("myBean.doubleProperty + myBean.getIntegerProperty", getELText(_structuredDocument,2418));

    }

    @Override
    public void testNoErrorExprs()
    {
        // marker offset tests are only meaningful for validation warnings and errors
        // since we are testing the length and offset values for their corresponding markers
    }
    @Override
    public void testWarningExprs()
    {
        // UPDATE REMINDER!  The third argument in assertContainsProblem is the start
        // offset (absolute) of the marker expected.  Before updating the assertSemanticWarning
        // make a note to the difference
        List<ReportedProblem> list = assertSemanticWarning(880, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID, 880, 5);

        list = assertSemanticWarning(915, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID, 915, 11);

        list = assertSemanticWarning(956, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID, 956, 30);

        list = assertSemanticWarning(1015, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID, 1015, 31);

        list = assertSemanticWarning(1075, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID, 1075, 31);

        list = assertSemanticWarning(1135, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID, 1135, 6);

        list = assertSemanticWarning(1171, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE_ID, 1171, 7);

        list = assertSemanticWarning(1208, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_MINUS_ON_NULL_ALWAYS_ZERO_ID, 1208, 5);

        list = assertSemanticWarning(1243, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED_ID, 1243, 22);

        list = assertSemanticWarning(1376, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID, 1402, 5);

        list = assertSemanticWarning(1438, null, 2);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID, 1464, 34);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID, 1438, 60);

        list = assertSemanticWarning(1569, null, 1);
        assertContainsProblem(list, DiagnosticFactory.VARIABLE_NOT_FOUND_ID, 1569, 8);

        list = assertSemanticWarning(1622, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID, 1629, 12);

        list = assertSemanticWarning(1671, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID, 1707, 12);

        list = assertSyntaxWarning(1769, 1);
        assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID, 1792, 1);

        // Is really correct to have the property offset start on the dot?
        list = assertSemanticWarning(1823, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID, 1829, 1);

        list = assertSyntaxWarning(1860, 1);
        assertContainsProblem(list, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID, 1860, 1);
    }

    @Override
    public void testErrorExprs()
    {
        // UPDATE REMINDER!  The third argument in assertContainsProblem is the start
        // offset (absolute) of the marker expected.  Before updating the assertSemanticWarning
        // make a note to the difference
        List<ReportedProblem> list = assertSemanticError(1978, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID, 1978, 26);

        list = assertSemanticError(2034, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID, 2034, 47);

        list = assertSemanticError(2111, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID, 2111, 48);

        list = assertSemanticError(2189, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID, 2189, 51);

        list = assertSemanticError(2270, null, 1);
        assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS_ID, 2270, 24);

        list = assertSemanticError(2324, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID, 2324, 5);

        list = assertSemanticError(2359, null, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID, 2359, 2);

        list = assertSemanticError(2418, null, 1);
        assertContainsProblem(list, DiagnosticFactory.CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING_ID, 2442, 25);
    }

}