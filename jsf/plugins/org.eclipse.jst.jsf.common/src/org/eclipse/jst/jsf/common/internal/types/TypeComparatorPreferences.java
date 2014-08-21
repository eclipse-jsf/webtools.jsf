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
package org.eclipse.jst.jsf.common.internal.types;

import org.eclipse.emf.common.util.Diagnostic;

/**
 * Preference info for type comparator diagnostics
 * 
 * @author cbateman
 *
 */
public class TypeComparatorPreferences
{

    /**
     * @param diagnosticId
     * @return the default severity of a diagnostic
     */
    public int getDefaultSeverity(final int diagnosticId)
    {
        switch (diagnosticId)
        {
            case TypeComparatorDiagnosticFactory.METHOD_EXPRESSION_EXPECTED_ID:
                return Diagnostic.ERROR;
            case TypeComparatorDiagnosticFactory.INCOMPATIBLE_TYPES_ID:
                return Diagnostic.INFO;
            case TypeComparatorDiagnosticFactory.VALUE_EXPRESSION_EXPECTED_ID:
                return Diagnostic.ERROR;
            case TypeComparatorDiagnosticFactory.INCOMPATIBLE_METHOD_TYPES_ID:
                return Diagnostic.WARNING; /* Bug 243674 */
            case TypeComparatorDiagnosticFactory.PROPERTY_NOT_READABLE_ID:
                return Diagnostic.WARNING;
            case TypeComparatorDiagnosticFactory.PROPERTY_NOT_WRITABLE_ID:
                return Diagnostic.WARNING;
            default:
                throw new IllegalArgumentException("Diagnostic Id: "+ diagnosticId +" is out of range"); //$NON-NLS-1$ //$NON-NLS-2$

        }
    }

}
