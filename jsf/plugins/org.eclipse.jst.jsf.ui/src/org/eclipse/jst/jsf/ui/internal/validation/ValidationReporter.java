/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.ui.internal.validation;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.validation.internal.IJSFViewValidator;
import org.eclipse.jst.jsf.validation.internal.ValidationPreferences;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;

/*package*/class ValidationReporter implements
        IJSFViewValidator.IValidationReporter
{
    private final IValidator            _validator;
    private final IReporter             _reporter;
    private final IFile                 _file;
    private final ValidationMessageFactory  _factory;
    
    public ValidationReporter(final IValidator validator,
            final IReporter reporter, final IFile file,
            final ValidationPreferences prefs, final IStructuredModel model)
    {
        _validator = validator;
        _reporter = reporter;
        _file = file;
        _factory = new ValidationMessageFactory(prefs, model);
    }

    public void report(final Diagnostic problem, final int start,
            final int length)
    {
        final IMessage message = _factory.createFromDiagnostic(
                problem, start, length, _file);

        if ((message.getSeverity() & IMessage.ALL_MESSAGES) != 0)
        {
            _reporter.addMessage(_validator, message);
        }
    }

    public void report(IMessage message)
    {
        if ((message.getSeverity() & IMessage.ALL_MESSAGES) != 0)
        {
            _reporter.addMessage(_validator, message);
        }
    }
}
