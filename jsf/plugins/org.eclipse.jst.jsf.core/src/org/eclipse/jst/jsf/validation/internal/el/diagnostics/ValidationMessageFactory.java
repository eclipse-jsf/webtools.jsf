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

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.validation.internal.ELValidationPreferences;
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
    public static Message createFromDiagnostic(final Diagnostic diagnostic, int offset, int length, IFile file, ELValidationPreferences prefs)
    {
        final int severity = 
            prefs.getDiagnosticSeverity(diagnostic.getCode()); 

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
     * @param diagnostic
     * @return a Message severity equivilent to diagnostic.getSeverity()
     */
    private static int convertSeverity(int severity)
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

