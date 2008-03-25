/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     John Kaplan, johnkaplantech@gmail.com - 108071 [code templates] template for body of newly created class
 *     Oracle - adapted for JSF tooling
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.validation;

import org.eclipse.osgi.util.NLS;

/**
 * NLS messages for validation severity preferences
 * 
 * @author cbateman
 *
 */
public final class PreferencesMessages extends NLS {

    private static final String BUNDLE_NAME= "org.eclipse.jst.jsf.ui.internal.validation.messages";//$NON-NLS-1$

    private PreferencesMessages() {
        // Do not instantiate
    }

    static {
        NLS.initializeMessages(BUNDLE_NAME, PreferencesMessages.class);
    }

    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesPreferencePage_title;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_error;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_warning;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_ignore;

    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_section_id_resolution;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_section_general;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_section_type_coercion_problems;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_section_constant_folding_and_unused_code;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_section_programming_errors;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_section_type_comparison;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_needsbuild_title;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_needsfullbuild_message;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_needsprojectbuild_message;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_buildwarning_dont_show_again;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_common_description;

    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_general_syntax_error;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_empty_el_expression;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_missing_closing_expr_bracket;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_cannot_apply_operator_to_method_binding;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_dotted_property_key_should_use_array;

    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_variable_not_found;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_member_not_found;

    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_binary_op_numeric_coercion_error;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_binary_op_boolean_coercion_error;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_binary_op_no_coercion_available;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_binary_op_literal_to_number_coercion_error;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_unary_op_numeric_coercion_error;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_unary_op_boolean_coercion_error;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_unary_op_string_coercion_not_guaranteed;
    
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_both_binary_operands_null;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_binary_expression_always_evaluates_same;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_equality_with_null_always_same;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_unary_expression_always_evaluates_same;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_empty_expression_always_false;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_enumeration_comparision_always_same;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_minus_on_null_always_zero;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_first_argument_short_circuits_expression;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_second_argument_always_evaluates_same;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_apply_dot_operator_with_null;
    
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_possible_division_by_zero;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_possible_array_index_out_of_bounds;
    /**
     * see messages.properties
     */
    public static String  ProblemSeveritiesConfigurationBlock_pb_incompatible_enumeration_comparison;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_member_is_intermediate;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_method_expression_expected;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_incompatible_type_assignment;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_value_expression_expected;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_incompatible_method_types;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_property_not_readable;
    /**
     * see messages.properties
     */
    public static String ProblemSeveritiesConfigurationBlock_pb_property_not_writable;
}
