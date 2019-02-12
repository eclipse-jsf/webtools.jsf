/*******************************************************************************
 * Copyright (c) 2006, 2010 Oracle Corporation.
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
package org.eclipse.jst.jsf.ui.internal.validation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.internal.types.TypeComparatorDiagnosticFactory;
import org.eclipse.jst.jsf.validation.internal.ELValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.JSFTypeComparatorPreferences;
import org.eclipse.jst.jsf.validation.internal.ValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.jst.jsf.validation.internal.facelet.FaceletDiagnosticFactory;
import org.eclipse.jst.jsf.validation.internal.facelet.FaceletValidationPreferences;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
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
    private final Map<String, SeverityOverrideStrategy> _strategies;
    private IStructuredModel _model = null;

    /**
     * @param prefs
     * @param model
     */
    public ValidationMessageFactory(final ValidationPreferences prefs,
            final IStructuredModel model)
    {
        _model = model;
        _strategies = new HashMap<String, SeverityOverrideStrategy>();
        _strategies.put(DiagnosticFactory.SOURCE_ID,
                new ELSeverityOverrideStrategy(prefs));
        _strategies.put(TypeComparatorDiagnosticFactory.SOURCE_IDENTIFIER,
                new TypeComparatorOverrideStrategy(prefs));
        _strategies.put(FaceletDiagnosticFactory.SOURCE_ID,
                new FaceletSeverityOverrideStrategy(prefs));
    }

    /**
     * @param diagnostic
     * @param offset
     * @param length
     * @param file
     * @return a configured message
     */
    public Message createFromDiagnostic(final Diagnostic diagnostic,
            final int offset, final int length, final IFile file)
    {
        int severity = diagnostic.getSeverity();
        final String sourceId = diagnostic.getSource();
        final SeverityOverrideStrategy strategy = _strategies.get(sourceId);
        // only override if there's a strategy to do so
        if (strategy != null)
        {
            final Integer value = strategy.override(diagnostic);
            if (value != null)
            {
                severity = value.intValue();
            }
        }
        final Message message = new MyLocalizedMessage(
                convertSeverity(severity), diagnostic.getMessage(), file,
                diagnostic.getCode());
        message.setOffset(offset);
        message.setLength(length);
        if (this._model != null)
        {
            IStructuredDocument flatModel = this._model.getStructuredDocument();
            if (flatModel != null)
            {
                int line = flatModel.getLineOfOffset(message.getOffset());
                if (line >= 0)
                    message.setLineNo(line + 1);
            }
        }
        return message;
    }

    /**
     * @param severity
     * @return a Message severity equivilent to diagnostic.getSeverity()
     */
    private int convertSeverity(final int severity)
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

    private static class ELSeverityOverrideStrategy extends
            SeverityOverrideStrategy
    {
        private final ValidationPreferences _prefs;

        public ELSeverityOverrideStrategy(final ValidationPreferences prefs)
        {
            super(DiagnosticFactory.SOURCE_ID);
            _prefs = prefs;
        }

        @Override
        public Integer override(final Diagnostic diagnostic)
        {
            final int code = diagnostic.getCode();
            final ELValidationPreferences elPrefs = _prefs.getElPrefs();
            return Integer.valueOf(elPrefs.getDiagnosticSeverity(code));
        }

        @Override
        public String getDisplayName()
        {
            return Messages.ValidationMessageFactory_DefaultElSeverityDisplayName;
        }
    }

    private static class TypeComparatorOverrideStrategy extends
            SeverityOverrideStrategy
    {
        private final ValidationPreferences _prefs;

        public TypeComparatorOverrideStrategy(final ValidationPreferences prefs)
        {
            super(TypeComparatorDiagnosticFactory.SOURCE_IDENTIFIER);
            _prefs = prefs;
        }

        @Override
        public Integer override(final Diagnostic diagnostic)
        {
            final int code = diagnostic.getCode();
            final JSFTypeComparatorPreferences jsfTypeCompPrefs = _prefs
                    .getTypeComparatorPrefs();
            return Integer
                    .valueOf(jsfTypeCompPrefs.getDiagnosticSeverity(code));
        }

        @Override
        public String getDisplayName()
        {
            return Messages.ValidationMessageFactory_DefaultTypeComparatorDisplayName;
        }
    }

    private static class FaceletSeverityOverrideStrategy extends
            SeverityOverrideStrategy
    {
        private final ValidationPreferences _prefs;

        public FaceletSeverityOverrideStrategy(final ValidationPreferences prefs)
        {
            super(FaceletDiagnosticFactory.SOURCE_ID);
            _prefs = prefs;
        }

        @Override
        public Integer override(final Diagnostic diagnostic)
        {
            final int code = diagnostic.getCode();
            final FaceletValidationPreferences faceletPrefs = _prefs
                    .getFaceletValidationPrefs();
            return Integer.valueOf(faceletPrefs.getDiagnosticSeverity(code));
        }

        @Override
        public String getDisplayName()
        {
            return Messages.ValidationMessageFactory_DefaultFaceletSeverityDisplayName;
        }
    }
}