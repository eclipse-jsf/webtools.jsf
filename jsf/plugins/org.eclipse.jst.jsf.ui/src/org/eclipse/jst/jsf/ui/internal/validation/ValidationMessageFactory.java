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

package org.eclipse.jst.jsf.ui.internal.validation;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.validation.internal.ELValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.ValidationPreferences;
import org.eclipse.wst.validation.internal.core.Message;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Creates configured validation Message objects.
 * 
 * @author cbateman
 *
 */
public final class ValidationMessageFactory
{
    /**
     * @param diagnostic
     * @param offset
     * @param length
     * @param file
     * @param prefs
     * @return a configured message
     */
    public static Message createFromDiagnostic(final Diagnostic diagnostic, final int offset, final int length, final IFile file, final ValidationPreferences prefs)
    {

        final int code = diagnostic.getCode();
        
        int severity = diagnostic.getSeverity();

        // XXX: this is a temporary solution.  We need a way to allow 
        // severity overrides from default that is decoupled from the
        // factory or code value for a diagnostic
        if (code >= 0)
        {
            final ELValidationPreferences elPrefs =
                prefs.getElPrefs();
            severity =
                elPrefs.getDiagnosticSeverity(code);
        }

        final Message message =
            new MyLocalizedMessage(
                    convertSeverity(severity),
                    diagnostic.getMessage(),
                    file,
                    diagnostic.getCode());

        message.setOffset(offset);
        message.setLength(length);

        return message;
    }

    /**
     * @param severity
     * @return a Message severity equivilent to diagnostic.getSeverity()
     */
    private static int convertSeverity(final int severity)
    {
        switch (severity)
        {
            case Diagnostic.ERROR:
                return IMessage.HIGH_SEVERITY;
            case Diagnostic.WARNING:
                return IMessage.NORMAL_SEVERITY;
            case Diagnostic.INFO:
                return IMessage.LOW_SEVERITY;
            case Diagnostic.OK:
            default:
                // no bits set
                return 0;
        }
    }

    private ValidationMessageFactory()
    {
        // no external instantiation
    }
}

