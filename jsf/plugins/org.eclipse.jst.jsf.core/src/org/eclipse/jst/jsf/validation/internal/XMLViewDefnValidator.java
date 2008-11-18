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

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.region.Region2AttrAdapter;
import org.eclipse.jst.jsf.core.internal.region.Region2ElementAdapter;
import org.eclipse.jst.jsf.core.internal.region.Region2ElementAdapter.NoElementException;
import org.eclipse.jst.jsf.designtime.DTAppManagerUtil;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler;
import org.eclipse.jst.jsf.designtime.resolver.IStructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.designtime.resolver.StructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.jst.jsf.validation.internal.strategy.AttributeValidatingStrategy;
import org.eclipse.jst.jsf.validation.internal.strategy.ContainmentValidatingStrategy;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;

/**
 * Validator for JSF view definitions that are structured in XML format
 * 
 * @author cbateman
 * 
 */
public class XMLViewDefnValidator implements IJSFViewValidator
{
    // TODO: should the source validator be a separate class in jsp.ui?
    // problem with simple split off is that preference must also be split off
    static final boolean DEBUG;
    static
    {
        final String value = Platform
        .getDebugOption("org.eclipse.jst.jsf.core/validation"); //$NON-NLS-1$
        DEBUG = value != null && value.equalsIgnoreCase("true"); //$NON-NLS-1$
    }

    // default.  Overridable by calling setSymbolResolverFactory
    private IStructuredDocumentSymbolResolverFactory   _symbolResolverFactory =
        StructuredDocumentSymbolResolverFactory.getInstance();

    /**
     * Validates a JSP tag.
     * 
     * Currently only attribute values with supplied annotation meta-data is
     * being validated. Also, only JSF EL is being validated and not JSP EL.
     * 
     * This method may be extended in the future to validate tag semantics an
     * other cross attribute validations.
     * 
     * @param container
     * @param jsfValidationContext 
     * 
     */
    private void validateTag(final IStructuredDocumentRegion container,
            final JSFValidationContext jsfValidationContext, 
            final ContainmentValidatingStrategy containmentStrategy)
    {
        // TODO: later when we move to composite strategy, run in SafeRunner
        // to protect against exceptions generated in external code.
        try
        {
            final Region2ElementAdapter regionAdapter = new Region2ElementAdapter(
                    container);

//            final IStructuredDocumentContext elementContext = 
//                IStructuredDocumentContextFactory.INSTANCE
//                    .getContext(container.getParentDocument(), container
//                            .getStartOffset());

            if (containmentStrategy.isInteresting(regionAdapter))
            {
                containmentStrategy.validate(regionAdapter);
            }

            final AttributeValidatingStrategy  strategy =
                new AttributeValidatingStrategy(jsfValidationContext);

            for (final Region2AttrAdapter attrAdapter : regionAdapter
                    .getAttributes().values())
            {
                if (strategy.isInteresting(attrAdapter))
                {
                    strategy.validate(attrAdapter);
                }
            }
        }
        catch (NoElementException ne)
        {
            // XXX:
        }
    }

    public void validateView(final IFile viewFile, final IValidationReporter reporter)
    {
        IStructuredModel model = null;
        if (DEBUG)
        {
            System.out.println("executing JSPSemanticsValidator.validateFile"); //$NON-NLS-1$
        }

        try
        {
            final JSFValidationContext jsfValidationContext =
                createValidationContext(false, viewFile, reporter);

            if (jsfValidationContext == null)
            {
                return;
            }

            model = StructuredModelManager.getModelManager().getModelForRead(
                    viewFile);

            final IStructuredDocument structuredDoc =
                model.getStructuredDocument();
            final IStructuredDocumentRegion[] regions =
                structuredDoc.getStructuredDocumentRegions();
            validateRegions(regions, jsfValidationContext, 
                    new ContainmentValidatingStrategy(jsfValidationContext));
        }
        catch (final CoreException e)
        {
            JSFCorePlugin.log("Error validating JSF", e); //$NON-NLS-1$
        }
        catch (final IOException e)
        {
            JSFCorePlugin.log("Error validating JSF", e); //$NON-NLS-1$
        }
        finally
        {
            if (null != model)
            {
                model.releaseFromRead();
            }
        }
    }

    public void validateView(IFile viewFile,
            IStructuredDocumentRegion[] regions, IValidationReporter reporter)
    {
        final JSFValidationContext jsfValidationContext =
            createValidationContext(true, viewFile, reporter);

        if (jsfValidationContext == null)
        {
            return;
        }

        validateRegions(regions, jsfValidationContext, 
                new ContainmentValidatingStrategy(jsfValidationContext));
    }
    
    private void validateRegions(final IStructuredDocumentRegion[] regions, 
                                 final JSFValidationContext context,
                                 final ContainmentValidatingStrategy containmentValidator)
    {
        for (final IStructuredDocumentRegion curNode : regions)
        {
            if (curNode.getFirstRegion().getType() == DOMRegionContext.XML_TAG_OPEN)
            {
                validateTag(curNode, context, containmentValidator);
            }
        }
    }

    private JSFValidationContext createValidationContext(
            final boolean isIncremental, final IFile file,
            final IValidationReporter reporter)
    {
        final ValidationPreferences prefs = new ValidationPreferences(
                JSFCorePlugin.getDefault().getPreferenceStore());
        prefs.load();
        final DiagnosticFactory diagnosticFactory = new DiagnosticFactory();

        final IDTViewHandler viewHandler = DTAppManagerUtil.getViewHandler(file
                .getProject());

        // only validate files that our designtime supports
        if (viewHandler == null || !viewHandler.supportsViewDefinition(file))
        {
            return null;
        }

        return new JSFValidationContext(isIncremental, prefs, viewHandler,
                diagnosticFactory, file, reporter, _symbolResolverFactory);
    }
}
