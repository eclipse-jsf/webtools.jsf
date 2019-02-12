/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.common.internal.types;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.osgi.util.NLS;

/**
 * Diagnostic Factory for type comparator diagnostics.
 * 
 * @author cbateman
 * 
 */
public final class TypeComparatorDiagnosticFactory
{
    /**
     * The id used in the source field of all Diagnostic's created by this
     * factory to uniquely identify TypeComparator validation as their source
     * type.
     */
    public final static String SOURCE_IDENTIFIER                     = "org.eclipse.jst.jsf.common.types.TypeComparator"; //$NON-NLS-1$

    /**
     * A method expression was expected, but something else (i.e. a value
     * expression) was provided.
     */
    public final static int    METHOD_EXPRESSION_EXPECTED_ID = 0;

    /**
     * Value expression type was incompatible with the expected type.
     */
    public static final int    INCOMPATIBLE_TYPES_ID         = 1;

    /**
     * A value expression was expected, but something else (i.e. a method
     * expression) was provided.
     */
    public static final int    VALUE_EXPRESSION_EXPECTED_ID  = 2;

    /**
     * Method expression signature did not match what was expected.
     */
    public static final int    INCOMPATIBLE_METHOD_TYPES_ID  = 3;

    /**
     * A property was expected to be readable but no getter was found.
     */
    public static final int    PROPERTY_NOT_READABLE_ID      = 4;

    /**
     * A property was expected to be writable but no setter was found
     */
    public static final int    PROPERTY_NOT_WRITABLE_ID      = 5;
    
    /**
     * the number of diagnostic ids
     */
    public static final int    NUM_IDS = 6;

    private final TypeComparatorPreferences _prefs;

    /**
     * @param prefs
     */
    public TypeComparatorDiagnosticFactory(final TypeComparatorPreferences prefs)
    {
        _prefs = prefs;
    }
    // A method expression was supplied as expected, but its signature did
    // * not match the expected.
    /**
     * @return a diagnostic
     */
    public Diagnostic create_METHOD_EXPRESSION_EXPECTED()
    {
        return create(METHOD_EXPRESSION_EXPECTED_ID, Messages
                .getString("TypeComparator.Expression.No_Method")); //$NON-NLS-1$
    }

    /**
     * @param params
     * @return a diagnostic
     */
    public Diagnostic create_INCOMPATIBLE_TYPES(final Object[] params)
    {
        return create(
                INCOMPATIBLE_TYPES_ID,
                NLS
                        .bind(
                                Messages
                                        .getString("TypeComparator.Expression.Incompatible_Value"), params)); //$NON-NLS-1$
    }

    /**
     * @return a diagnostic
     */
    public Diagnostic create_VALUE_EXPRESSION_EXPECTED()
    {
        return create(VALUE_EXPRESSION_EXPECTED_ID, Messages
                .getString("TypeComparator.Expression.No_Value")); //$NON-NLS-1$
    }

    /**
     * @param params
     * @return a diagnostic
     */
    public Diagnostic create_INCOMPATIBLE_METHOD_TYPES(final Object[] params)
    {
        return create(INCOMPATIBLE_METHOD_TYPES_ID, NLS.bind(Messages
                .getString("TypeComparator.Expression.Incompatible_Method"), //$NON-NLS-1$
                params));
    }

    /**
     * @return a diagnostic
     */
    public Diagnostic create_PROPERTY_NOT_READABLE()
    {
        return create(PROPERTY_NOT_READABLE_ID, Messages
                .getString("TypeComparator.Expression.Not.Gettable")); //$NON-NLS-1$
    }

    /**
     * @return a diagnostic
     */
    public Diagnostic create_PROPERTY_NOT_WRITABLE()
    {
        return create(PROPERTY_NOT_WRITABLE_ID, Messages
                .getString("TypeComparator.Expression.Expected.Settable")); //$NON-NLS-1$
    }

    private BasicDiagnostic create(int diagnosticId, String message)
    {
        final int severity = _prefs.getDefaultSeverity(diagnosticId);
        return new BasicDiagnostic(severity, SOURCE_IDENTIFIER, diagnosticId, message,
                null);
    }
}
