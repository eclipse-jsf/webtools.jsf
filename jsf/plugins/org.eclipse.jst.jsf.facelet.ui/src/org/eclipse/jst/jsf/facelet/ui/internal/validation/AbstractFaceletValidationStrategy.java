/*******************************************************************************
 * Copyright (c) 2013, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.ui.internal.validation;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.facelet.core.internal.util.ViewUtil;
import org.eclipse.jst.jsf.validation.internal.IJSFViewValidator;
import org.eclipse.jst.jsf.validation.internal.ValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.facelet.FaceletDiagnosticFactory;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;


/**
 * Strategy that is delegated to from HTMLValidator to do the validation.  Allows third-parties to enhance
 * or replace the existing strategy.
 *
 */
public abstract class AbstractFaceletValidationStrategy {

    /**
     * return from getPriority to indicate no priority preference.  If any other strategy returns a value higher, then
     * a validator returning a smaller value including 0 will be ignored.  If all return DONT_CARE then they will
     * all be called.
     */
    public static final int DONT_CARE = 0;

    /**
     * The factory for diagnostics.
     */
    protected final FaceletDiagnosticFactory _diagnosticFactory = new FaceletDiagnosticFactory();

    /**
     * If not overriden, returns DONT_CARE.
     * 
     * @return a value indicating the override priority for this strategy.  If more than one validation strategy
     * exists, then *only* the one that returns the highest positive value will be used.  If a strategy returns 0, then
     * it is signallying that it does not care.  If no strategy returns a value greater than 0, then all strategies will
     * be called. Negative values are currently reserved and strategies returning negative values will be treated as though
     * they returned 0.
     */
    public int getPriority()
    {
        return DONT_CARE;
    }

//    /**
//     * @param helper
//     * @param reporter
//     * @return status of this validation
//     * @throws ValidationException
//     */
//    public IStatus validateInJob(final IValidationContext helper,
//            final IReporter reporter) throws ValidationException
//    {
//        IStatus status = Status.OK_STATUS;
//        try
//        {
//            validate(helper, reporter);
//        }
//        catch (final ValidationException e)
//        {
//            status = new Status(IStatus.ERROR, FaceletUiPlugin.PLUGIN_ID,
//                    IStatus.ERROR, e.getLocalizedMessage(), e);
//        }
//        return status;
//
//    }

    /**
     * @param file
     * @param reporter
     * @param caller
     */
    public final void validateFile(final IFile file, final IReporter reporter, final IValidator caller)
    {
        final ValidationPreferences prefs = new ValidationPreferences(
                JSFCorePlugin.getDefault().getPreferenceStore());
        prefs.load();

        IStructuredModel model = null;
        try
        {
            model = StructuredModelManager.getModelManager().getModelForRead(
                    file);

            final ValidationReporter jsfReporter = new ValidationReporter(caller,
                    reporter, file, prefs, model);
            doValidate(file, jsfReporter);
        }
        catch (final CoreException e)
        {
            JSFCorePlugin.log("Error validating JSF", e);
        }
        catch (final IOException e)
        {
            JSFCorePlugin.log("Error validating JSF", e);
        }
        finally
        {
            if (null != model)
            {
                model.releaseFromRead();
            }
        }
    }

    /**
     * @param file
     * @param jsfReporter
     */
    protected abstract void doValidate(final IFile file, IJSFViewValidator.IValidationReporter jsfReporter);
    
    
    /**
     * @param model
     * @return true validation should be executed against model
     */
    public boolean shouldValidate(final IFile model)
    {
        final IContentTypeManager manager = Platform.getContentTypeManager();
        final IContentType contentType = manager
                .getContentType("org.eclipse.wst.html.core.htmlsource");
        return (contentType.isAssociatedWith(model.getName()))
                && ViewUtil.isFaceletVDLFile(model);
    }
}
