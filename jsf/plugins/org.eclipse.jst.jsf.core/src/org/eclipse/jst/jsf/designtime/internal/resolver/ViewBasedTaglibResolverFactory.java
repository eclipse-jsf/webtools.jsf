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
package org.eclipse.jst.jsf.designtime.internal.resolver;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IMetadataContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.IStructuredDocumentContextResolverFactory2;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.designtime.DTAppManagerUtil;
import org.eclipse.jst.jsf.designtime.internal.view.XMLViewDefnAdapter;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A Taglib resolver that delegates to the design tieme view handler so that it
 * is independent of the tag definition type in use. Document must still be an
 * IStructuredDocument.
 * 
 */
public class ViewBasedTaglibResolverFactory implements
IStructuredDocumentContextResolverFactory2, IAdaptable
{
    public IDOMContextResolver getDOMContextResolver(
            final IStructuredDocumentContext context)
    {
        // no dom resolver
        return null;
    }

    public IMetadataContextResolver getMetadataContextResolver(
            final IStructuredDocumentContext context)
    {
        // no metadata resolver
        return null;
    }

    public ITaglibContextResolver getTaglibContextResolver(
            final IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            final IWorkspaceContextResolver resolver = INSTANCE
            .getWorkspaceContextResolver(context);
            if (resolver != null)
            {
                final IResource res = resolver.getResource();

                if (res instanceof IFile)
                {
                    final IFile file = (IFile) res;
                    final IProject project = file.getProject();

                    if (project != null)
                    {
                        try
                        {
                            return new ViewBasedTaglibResolver(context, file,
                                    project);
                        }
                        catch (final IllegalArgumentException e)
                        {
                            // the constructor will throw this if the view
                            // definition
                            // adapter for file is not of the base type it needs
                            // just fall through, no need to log
                        }
                    }
                }
            }
        }

        return null;
    }
    
	public ITaglibContextResolver getTaglibContextResolverFromDelegates(
			IStructuredDocumentContext context) {
		throw new UnsupportedOperationException();
	}

	public <T> T getResolver(final IStructuredDocumentContext context, final Class<T> clazz) {
		if (clazz.equals(ITagElementResolver.class) ||
				clazz.equals(ITaglibContextResolver.class)) {
			return (T)getTaglibContextResolver(context);
		}
		return null;
	}
    /**
     * A taglib resolver that goes through the design time view handler to
     * resolve tags. This allows us to abstract the definition format (XML) from
     * the kind of tag handlers that process it (JSP vs. Facelets vs. others).
     * 
     */
    private static class ViewBasedTaglibResolver implements
    ITaglibContextResolver, ITagElementResolver
    {
        private final IProject                   _project;
        private final IFile                      _file;
        private final IStructuredDocumentContext _context;

        /**
         * @param context
         * @param file
         * @param project
         */
        private ViewBasedTaglibResolver(
                final IStructuredDocumentContext context, final IFile file,
                final IProject project)
        {
            super();
            _context = context;
            _file = file;
            _project = project;

            if (DTAppManagerUtil.getXMLViewDefnAdapter(project, file) == null)
            {
                throw new IllegalArgumentException(
                "View definition adapter not found"); //$NON-NLS-1$
            }
        }

        public String getTagPrefixForURI(final String uri)
        {
            final XMLViewDefnAdapter adapter = DTAppManagerUtil
                    .getXMLViewDefnAdapter(_file);

            if (adapter != null)
            {
                return adapter.getPrefix(uri, _context.getStructuredDocument());
            }
            return null;
        }

        public String getTagURIForNodeName(final Node node)
        {
            final XMLViewDefnAdapter adapter = DTAppManagerUtil
            .getXMLViewDefnAdapter(_file);
            final Element element = getElement(node);
            if (element != null && adapter != null)
            {
                return adapter.getNamespace(element, _context.getStructuredDocument());
            }
            return null;
        }

        public NodeList getTagsByNamespaceURI(final String uri,
                final String tagName)
        {
            // TODO: what is the purpose of this API? deprecate?
            return null;
        }

        public boolean hasTag(final Node node)
        {
            // it is sufficient to check that the view adapter will give us
            // a non-null tag element
            return _getTagElement(node) != null;
        }

        public boolean canResolveContext(final IModelContext modelContext)
        {
            // must be a JSP page
            final Object adapter = modelContext.getAdapter(IStructuredDocumentContext.class);

            if (adapter instanceof IStructuredDocumentContext)
            {
                return ((IStructuredDocumentContext)adapter).getStructuredDocument() instanceof IStructuredDocument;
            }
            return false;
        }

        private Element getElement(final Node node)
        {
            Element checkNode = null;

            if (node instanceof Element)
            {
                checkNode = (Element) node;
            }
            else if (node instanceof Attr)
            {
                checkNode = ((Attr) node).getOwnerElement();
            }
            return checkNode;
        }

        private ITagElement _getTagElement(final Node node)
        {
            final Element element = getElement(node);

            final XMLViewDefnAdapter adapter =
                DTAppManagerUtil.getXMLViewDefnAdapter(_project, _file);

            if (element != null && adapter != null)
            {
                return adapter.mapToTagElement(element, _context
                        .getStructuredDocument());
            }
            return null;
        }
        
		public ITagElement getTagElement(final Node node) {			
			return _getTagElement(node);
		}

    }


    public ITextRegionContextResolver getTextRegionResolver(
            final IStructuredDocumentContext context)
    {
        // no text region resolver
        return null;
    }

    public IWorkspaceContextResolver getWorkspaceContextResolver(
            final IStructuredDocumentContext context)
    {
        // no workspace resolver
        return null;
    }

    public Object getAdapter(final Class adapter)
    {
        if (adapter.isInstance(this))
        {
            return this;
        }
        return null;
    }

}
