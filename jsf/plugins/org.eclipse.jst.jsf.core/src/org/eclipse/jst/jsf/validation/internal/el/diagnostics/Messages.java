/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.validation.internal.el.diagnostics;

import org.eclipse.osgi.util.NLS;

class Messages extends NLS {
    private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.validation.internal.el.diagnostics.messages"; //$NON-NLS-1$


    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    /**
     * see messages.properties
     */
    public static String POSSIBLE_DIV_BY_ZERO;
    /**
     * see messages.properties
     */
    public static String UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE;
    /**
     * see messages.properties
     */
    public static String UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN; 
    /**
     * see messages.properties
     */
    public static String TERNARY_OP_CANNOT_COERCE_CHOICE_TO_BOOLEAN_ID;
    /**
     * see messages.properties
     */
    public static String BINARY_OP_BOTH_OPERANDS_NULL;
    /**
     * see messages.properties
     */
    public static String BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION;
    /**
     * see messages.properties
     */
    public static String BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME;
    /**
     * see messages.properties
     */
    public static String BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME;
    /**
     * see messages.properties
     */
    public static String BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN;
    /**
     * see messages.properties
     */
    public static String BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS;
    /**
     * see messages.properties
     */
    public static String BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME;
    /**
     * see messages.properties
     */
    public static String BINARY_OP_NO_AVAILABLE_TYPE_COERCION;
    /**
     * see messages.properties
     */
    public static String BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS;
    /**
     * see messages.properties
     */
    public static String UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME;
    /**
     * see messages.properties
     */
    public static String UNARY_OP_MINUS_ON_NULL_ALWAYS_ZERO;
    /**
     * see messages.properties
     */
    public static String UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION;
    /**
     * see messages.properties
     */
    public static String UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED;
    /**
     * see messages.properties
     */
    public static String TERNARY_OP_CHOICE_IS_ALWAYS_SAME;
    /**
     * see messages.properties
     */
    public static String VM_PROP_NAME_NOT_FOUND;
    /**
     * see messages.properties
     */
    public static String VM_ROOT_NAME_NOT_FOUND;
    /**
     * see messages.properties
     */
    public static String CANNOT_APPLY_OPERATORS_TO_MB;
    /**
     * see messages.properties
     */
    public static String MISSING_CLOSING_EXPR_BRACKET;
    /**
     * see messages.properties
     */
    public static String GENERAL_SYNTAX_ERROR;
    /**
     * see messages.properties 
     */
    public static String EMPTY_EL_EXPRESSION;
    /**
     * see messages.properties
     */
    public static String BINARY_OP_DOT_WITH_VALUEB_NULL;
    /**
     * see messages.properties
     */
    public static String BINARY_OP_DOT_WITH_DOTTED_KEY_SHOULD_USE_ARRAY;
    
    /**
     * see messages.properties
     */
    public static String POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS;
    /**
     * see messages.properties
     */
    public static String BINARY_COMPARISON_WITH_ENUM_AND_CONST_ALWAYS_SAME;
    /**
     * see messages.properties
     */
    public static String BINARY_COMPARISON_WITH_TWO_ENUMS_ALWAYS_SAME;
    /**
     * see messages.properties
     */
    public static String BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE;

    /**
     * see messages.properties
     */
    public static String BINARY_COMPARISON_WITH_ENUM_AND_UNCOERCABLE_NONCONST_ALWAYS_SAME;
    /**
     * see messages.properties
     */
    public static String MEMBER_IS_INTERMEDIATE;

    private Messages() {
        // do nothing; no external instantiation
    }
}
