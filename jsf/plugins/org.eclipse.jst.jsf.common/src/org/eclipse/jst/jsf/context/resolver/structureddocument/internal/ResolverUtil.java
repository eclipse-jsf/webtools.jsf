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
package org.eclipse.jst.jsf.context.resolver.structureddocument.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;

/**
 * Internal convenience class to simplify regularly repeated tasks
 * @author cbateman
 *
 */
public final class ResolverUtil
{
    /**
     * This method will generally only work if the document is an
     * IStructuredDocument.
     * 
     * @param document
     * @return the IFile corresponding to document or null if not applicable.
     * 
     */
    public static IFile getFileForDocument(final IDocument document)
    {
        final IStructuredDocumentContext context =
            IStructuredDocumentContextFactory.INSTANCE.getContext(document,
                    -1);

        if (context != null)
        {
            final IWorkspaceContextResolver wkResolver =
                IStructuredDocumentContextResolverFactory.INSTANCE
                .getWorkspaceContextResolver(context);

            if (wkResolver != null)
            {
                final IResource res = wkResolver.getResource();

                if (res instanceof IFile)
                {
                    return (IFile) res;
                }
            }
        }
        return null;
    }

    private ResolverUtil()
    {
        // no instantiation
        throw new UnsupportedOperationException();
    }
}
