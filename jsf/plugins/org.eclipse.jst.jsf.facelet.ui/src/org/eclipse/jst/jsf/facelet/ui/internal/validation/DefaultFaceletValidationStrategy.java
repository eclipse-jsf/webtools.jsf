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
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.facelet.core.internal.util.ViewUtil;
import org.eclipse.jst.jsf.validation.internal.IJSFViewValidator;
import org.eclipse.jst.jsf.validation.internal.JSFValidatorFactory;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * The default validation strategy.
 *
 */
public class DefaultFaceletValidationStrategy extends AbstractFaceletValidationStrategy {

    @Override
    protected void doValidate(final IFile file, final IJSFViewValidator.IValidationReporter jsfReporter) {
        final IJSFViewValidator validator = JSFValidatorFactory
                .createDefaultXMLValidator();

        validator.validateView(file, jsfReporter);
        validateFaceletHtml(file, jsfReporter);

    }

    /**
     * @param file
     * @param reporter
     */
    protected void validateFaceletHtml(final IFile file,
            final IJSFViewValidator.IValidationReporter reporter)
    {
        IStructuredModel model = null;
        try
        {
            model = StructuredModelManager.getModelManager().getModelForRead(
                    file);

            final IStructuredDocument structuredDoc = model
                    .getStructuredDocument();

            validateDocument(structuredDoc, reporter, file.getProject());
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
     * @param structuredDoc
     * @param reporter
     * @param project
     */
    protected void validateDocument(IStructuredDocument structuredDoc,
            final IJSFViewValidator.IValidationReporter reporter, IProject project)
    {
        validateRoot(structuredDoc, reporter, project);
    }

    /**
     * @param structuredDoc
     * @param reporter
     * @param project
     */
    protected void validateRoot(IStructuredDocument structuredDoc,
            final IJSFViewValidator.IValidationReporter reporter, IProject project)
    {
        final IStructuredDocumentContext context = IStructuredDocumentContextFactory.INSTANCE
                .getContext(structuredDoc, -1);
        final IDOMContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
                .getDOMContextResolver(context);
        final Document document = resolver.getDOMDocument();
        Element rootElement = document.getDocumentElement();

        if ("html".equals(rootElement.getNodeName()))
        {
            final Set<Attr> declaredNamespaces = ViewUtil
                    .getDeclaredNamespaces(rootElement.getAttributes());
            final ITagRegistry tagRegistry = ViewUtil
                    .getHtmlTagRegistry(project);
            final Collection<? extends Namespace> namespaces;
            if (tagRegistry != null)
            {
                namespaces = tagRegistry.getAllTagLibraries();
            }
            else
            {
                // unexpected
                namespaces = Collections.EMPTY_SET;
                JSFCorePlugin.log(IStatus.ERROR, "Program Error: HTML tag registry not found"); //$NON-NLS-1$
            }

            for (final Attr attr : declaredNamespaces)
            {
                // only validate prefix declarations
                if (attr.getPrefix() != null && attr instanceof IDOMAttr)
                {
                    final String declaredUri = attr.getValue();
                    String findUri = null;
                    SEARCH_NAMESPACES: for (final Namespace ns : namespaces)
                    {
                        if (ns.getNSUri().equals(declaredUri))
                        {
                            findUri = ns.getNSUri();
                            break SEARCH_NAMESPACES;
                        }
                    }

                    if (findUri == null)
                    {
                        final Diagnostic diag = _diagnosticFactory.create_CANNOT_FIND_FACELET_TAGLIB(declaredUri);
                        final IDOMAttr domAttr = (IDOMAttr) attr;
                        reporter.report(diag, domAttr.getValueRegionStartOffset(), domAttr
                                .getValue().length());
                    }
                }
            }
        }
    }

}
