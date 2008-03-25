/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.validation.el.tests.preferences;

import junit.framework.Assert;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.validation.internal.ELValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.Severity;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

final class PrefTestUtil extends Assert
{

    static void assertSeverityLevel(ELValidationPreferences prefs, int diagnosticId, int value)
    {
        final String key = ELValidationPreferences.getKeyById(diagnosticId);
        final Severity sev = prefs.getSeverity(key);
        final int diagnosticSeverity = 
            ELValidationPreferences.mapSeverityToDiag(sev.toString());
        assertEquals(diagnosticSeverity, value);
    }

    static void setByKey(Severity severity, ELValidationPreferences prefs)
    {
        prefs.setSeverity(ELValidationPreferences.BINARY_OP_BOTH_OPERANDS_NULL, severity);
        prefs.setSeverity(ELValidationPreferences.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO, severity);
        prefs.setSeverity(ELValidationPreferences.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION, severity);
        prefs.setSeverity(ELValidationPreferences.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME, severity);
        prefs.setSeverity(ELValidationPreferences.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME, severity);
        prefs.setSeverity(ELValidationPreferences.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN, severity);
        prefs.setSeverity(ELValidationPreferences.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS, severity);
        prefs.setSeverity(ELValidationPreferences.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME, severity);
        prefs.setSeverity(ELValidationPreferences.BINARY_OP_NO_AVAILABLE_TYPE_COERCION, severity);
        prefs.setSeverity(ELValidationPreferences.BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS, severity);
        prefs.setSeverity(ELValidationPreferences.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME, severity);
        prefs.setSeverity(ELValidationPreferences.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE, severity);
        prefs.setSeverity(ELValidationPreferences.UNARY_OP_MINUS_ON_NULL_ALWAYS_ZERO, severity);
        prefs.setSeverity(ELValidationPreferences.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION, severity);
        prefs.setSeverity(ELValidationPreferences.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN, severity);
        prefs.setSeverity(ELValidationPreferences.TERNARY_OP_CHOICE_IS_ALWAYS_SAME, severity);
        prefs.setSeverity(ELValidationPreferences.TERNARY_OP_CANNOT_COERCE_CHOICE_TO_BOOLEAN, severity);
        prefs.setSeverity(ELValidationPreferences.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED, severity);
        prefs.setSeverity(ELValidationPreferences.CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING, severity);
        prefs.setSeverity(ELValidationPreferences.MEMBER_NOT_FOUND, severity);
        prefs.setSeverity(ELValidationPreferences.VARIABLE_NOT_FOUND, severity);
        prefs.setSeverity(ELValidationPreferences.MISSING_CLOSING_EXPR_BRACKET, severity);
        prefs.setSeverity(ELValidationPreferences.GENERAL_SYNTAX_ERROR, severity);
        prefs.setSeverity(ELValidationPreferences.EMPTY_EL_EXPRESSION, severity);
        prefs.setSeverity(ELValidationPreferences.BINARY_OP_DOT_WITH_VALUEB_NULL, severity);
        prefs.setSeverity(ELValidationPreferences.BINARY_OP_DOT_WITH_DOTTED_KEY_SHOULD_USE_ARRAY, severity);
        prefs.setSeverity(ELValidationPreferences.POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS, severity);
        prefs.setSeverity(ELValidationPreferences.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME, severity);
        prefs.setSeverity(ELValidationPreferences.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE, severity);
    }
    static void assertSetByKey(Severity severity, ELValidationPreferences prefs)
    {
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_OP_BOTH_OPERANDS_NULL));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_OP_NO_AVAILABLE_TYPE_COERCION));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.UNARY_OP_MINUS_ON_NULL_ALWAYS_ZERO));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.TERNARY_OP_CHOICE_IS_ALWAYS_SAME));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.TERNARY_OP_CANNOT_COERCE_CHOICE_TO_BOOLEAN));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.MEMBER_NOT_FOUND));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.VARIABLE_NOT_FOUND));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.MISSING_CLOSING_EXPR_BRACKET));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.GENERAL_SYNTAX_ERROR));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.EMPTY_EL_EXPRESSION));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_OP_DOT_WITH_VALUEB_NULL));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_OP_DOT_WITH_DOTTED_KEY_SHOULD_USE_ARRAY));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME));
        assertEquals(severity, prefs.getSeverity(ELValidationPreferences.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE));
    }

    static void assertExpectedDefaults(ELValidationPreferences prefs)
    {
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID, Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID,Diagnostic.ERROR);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID, Diagnostic.ERROR);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID,Diagnostic.ERROR);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME_ID, Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_OP_NO_AVAILABLE_TYPE_COERCION_ID,Diagnostic.ERROR);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS_ID,Diagnostic.ERROR);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.UNARY_OP_MINUS_ON_NULL_ALWAYS_ZERO_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID,Diagnostic.ERROR);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID,Diagnostic.ERROR);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.TERNARY_OP_CHOICE_IS_ALWAYS_SAME_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.TERNARY_OP_CANNOT_COERCE_CHOICE_TO_BOOLEAN_ID,Diagnostic.ERROR);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING_ID,Diagnostic.ERROR);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.MEMBER_NOT_FOUND_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.VARIABLE_NOT_FOUND_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.MISSING_CLOSING_EXPR_BRACKET_ID,Diagnostic.ERROR);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.EMPTY_EL_EXPRESSION_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_OP_DOT_WITH_VALUEB_NULL_ID, Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_OP_DOT_WITH_DOTTED_KEY_SHOULD_USE_ARRAY_ID, Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID,Diagnostic.WARNING);
        PrefTestUtil.assertSeverityLevel(prefs, DiagnosticFactory.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE_ID,Diagnostic.ERROR);
    }
}
