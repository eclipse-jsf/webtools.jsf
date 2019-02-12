/*******************************************************************************
 * Copyright (c) 2006, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 * 
 ********************************************************************************/

package org.eclipse.jst.jsf.validation.el.tests.preferences;

import java.util.Locale;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.validation.internal.ELValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.ValidationPreferences;
import org.eclipse.wst.validation.internal.core.Message;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Creates configured validation Message objects.
 * 
 * TODO: This is duplicated from jsf.ui because I didn't want to cause a dependency
 * on that plugin.  Should this logic really be in UI?  IMessage is not an SSE
 * framework.
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
        final ELValidationPreferences elPrefs =
            prefs.getElPrefs();

        final int severity =
            elPrefs.getDiagnosticSeverity(diagnostic.getCode());

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
    
    /**
     * EL customized localizable validation message
     * @author cbateman
     *
     */
    static class MyLocalizedMessage extends Message
    {
        private final String _message;
        private final int    _errorCode;

        /**
         * @param severity
         * @param messageText
         * @param targetObject
         * @param errorCode 
         */
        public MyLocalizedMessage(int severity, String messageText, IResource targetObject, int errorCode) {
            this(severity, messageText, (Object) targetObject, errorCode);
        }

        /**
         * @param severity
         * @param messageText
         * @param targetObject
         * @param errorCode 
         */
        private MyLocalizedMessage(int severity, String messageText, Object targetObject, int errorCode) {
            super(JSFCorePlugin.getDefault().getBundle().getSymbolicName(), severity, 
                    messageText);
            _message = messageText;
            setTargetObject(targetObject);
            _errorCode = errorCode;
        }

        /**
         * @return the localized message
         */
        public String getLocalizedMessage() {
            return _message;
        }

        /**
         * @see org.eclipse.wst.validation.internal.core.Message#getText()
         */
        public String getText() {
            return getLocalizedMessage();
        }

        /**
         * @see org.eclipse.wst.validation.internal.core.Message#getText(java.lang.ClassLoader)
         */
        public String getText(ClassLoader cl) {
            return getLocalizedMessage();
        }

        /**
         * @see org.eclipse.wst.validation.internal.core.Message#getText(java.util.Locale)
         */
        public String getText(Locale l) {
            return getLocalizedMessage();
        }

        public String getText(Locale l, ClassLoader cl) {
            return getLocalizedMessage();
        }

        /**
         * @return the error code related to this message
         */
        public int getErrorCode() {
            return _errorCode;
        }


        /**
         * @param offset
         * @return true if this message applies to document offset
         */
        public boolean appliesTo(int offset)
        {
            return (offset >= getOffset() && offset < getOffset()+getLength());
        }
    }

}

