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
package org.eclipse.jst.jsf.validation.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.designtime.DTAppManagerUtil;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler;
import org.eclipse.jst.jsf.designtime.internal.view.IViewRootHandle;
import org.eclipse.jst.jsf.designtime.resolver.IStructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.validation.internal.IJSFViewValidator.IValidationReporter;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * A context object used pass JSF validation information around. Not related to
 * the model context framework.
 * 
 * @author cbateman
 * 
 */
public final class JSFValidationContext
{
    private final ValidationPreferences _prefs;
    private final boolean               _isIncremental;
    private final IDTViewHandler        _adapter;
    private final DiagnosticFactory     _diagnosticFactory;
    private final IFile                 _file;
    private final IValidationReporter   _reporter;
    private final IStructuredDocumentSymbolResolverFactory _symbolResolverFactory;
    // defer initializing this until is asked for because it is expensive
    private IViewRootHandle       _viewRootHandle;

    /**
     * @param isIncremental --
     *            true if this is "as-you-type" validation, false if this is
     *            "Build" or "Run Validation" validation *
     * @param prefs
     * @param adapter 
     * @param diagnosticFactory 
     * @param file
     * @param reporter
     * @param symbolResolveFactory 
     */
    public JSFValidationContext(final boolean isIncremental,
            final ValidationPreferences prefs,
            final IDTViewHandler adapter,
            final DiagnosticFactory diagnosticFactory, final IFile file,
            final IValidationReporter reporter, final IStructuredDocumentSymbolResolverFactory symbolResolveFactory)
    {
        super();
        _isIncremental = isIncremental;
        _prefs = prefs;
        _adapter = adapter;
        _diagnosticFactory = diagnosticFactory;
        _file = file;
        _reporter = reporter;
        _symbolResolverFactory = symbolResolveFactory;
    }

    /**
     * 
     * @return true if user preferences say we should do EL validation, false
     *         otherwise
     */
    public final boolean shouldValidateEL()
    {
        _prefs.load();
        if (_isIncremental)
        {
            return _prefs.getElPrefs().isEnableIncrementalValidation();
        }

        return _prefs.getElPrefs().isEnableBuildValidation();
    }


    /**
     * @return the view handler
     */
    public IDTViewHandler getAdapter()
    {
        return _adapter;
    }

    /**
     * @return the validator
     */
//    public IValidator getValidator()
//    {
//        return _validator;
//    }

    /**
     * @return validation preferences
     */
    public ValidationPreferences getPrefs()
    {
        return _prefs;
    }

    /**
     * @return diagnostic factory
     */
    public DiagnosticFactory getDiagnosticFactory()
    {
        return _diagnosticFactory;
    }

    /**
     * @return the file
     */
    public IFile getFile()
    {
        return _file;
    }

    /**
     * @return the reporter.
     */
    public IValidationReporter getReporter()
    {
        return _reporter;
    }

    /**
     * @return the symbol resolver factory for this context
     */
    public IStructuredDocumentSymbolResolverFactory getSymbolResolverFactory()
    {
        return _symbolResolverFactory;
    }

    /**
     * This method will be long running on first call, since it has to update
     * the view root if not initialized.
     * 
     * @return the view root handle
     */
    public IViewRootHandle getViewRootHandle()
    {
        if (_viewRootHandle == null)
        {
            _viewRootHandle = DTAppManagerUtil.getViewRootHandle(_file);
            if (_viewRootHandle.getCachedViewRoot() == null)
            {
                _viewRootHandle.updateViewRoot();
            }
        }
        return _viewRootHandle;
    }

}
