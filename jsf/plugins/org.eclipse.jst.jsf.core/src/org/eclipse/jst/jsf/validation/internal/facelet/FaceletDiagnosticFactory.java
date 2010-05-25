/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    Xianan Jiang/IBM - repurposed for Facelets.
 ********************************************************************************/
package org.eclipse.jst.jsf.validation.internal.facelet;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.osgi.util.NLS;

/**
 * Factory for all operator diagnostics. Class should not be implemented,
 * instantiated or sub-classed.
 * 
 * @author cbateman, xjiang
 * 
 */
public final class FaceletDiagnosticFactory
{
    /**
     * The id used in the source field of all Diagnostic's created by this
     * factory to uniquely identify EL validation as their source type.
     */
    public final static String SOURCE_ID = "org.eclipse.jst.jsf.facelet.core.internal.validation.diagnostics.Diagnostics";//$NON-NLS-1$
    /**
     * Problem id
     */
    public final static int CANNOT_FIND_FACELET_TAGLIB_ID = 0;
    /**
     * Count of ids
     */
    public final static int NUM_IDS = 1;

    /**
     * @param uri
     * @return a configured diagnostic
     */
    public Diagnostic create_CANNOT_FIND_FACELET_TAGLIB(final String uri)
    {
        final String message = NLS.bind(Messages.CANNOT_FIND_FACELET_TAGLIB,
                uri);
        return create(CANNOT_FIND_FACELET_TAGLIB_ID, message);
    }

    private BasicDiagnostic create(final int diagnosticId, final String message)
    {
        final int severity = FaceletValidationPreferences
                .getDefaultSeverity(diagnosticId);
        return new BasicDiagnostic(severity, SOURCE_ID, diagnosticId, message,
                null);
    }
}
