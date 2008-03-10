package org.eclipse.jst.jsf.designtime.internal.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.common.runtime.internal.model.datatypes.ELExpression;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler.ViewHandlerException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * A view definition adapter base class for view definition that use XML to
 * define the view.
 * 
 * @author cbateman
 * 
 */
public abstract class XMLViewDefnAdapter extends
        AbstractViewDefnAdapter<Node, IDocument>
{
    private static final String GENERATED_ID = "_generatedId";

    @Override
    public abstract IDocument getContainer(DTFacesContext context, String viewId);

    @Override
    public String getId(final Node viewDefnObject)
            throws IllegalArgumentException
    {
        if (viewDefnObject instanceof Element)
        {
            return ((Element) viewDefnObject).getAttribute("id");
        }
        throw new IllegalArgumentException(
                "Only Elements can define view object ids");
    }
    
    @Override
    public ViewObject mapToViewObject(
            final Node viewDefnObject,
            final ViewObjectConstructionStrategy<? extends Node> constructionData,
            final IDocument document)
    {
        switch (viewDefnObject.getNodeType())
        {
            case Node.ELEMENT_NODE:
                return createFromElement(
                        (Element) viewDefnObject,
                        (ViewObjectConstructionStrategy<Element>) constructionData,
                        document);
        }

        return null;
    }

    /**
     * @param viewDefnObject
     * @param viewContainer
     * @return the tag element corresponding to viewDefnObject in the context of
     *         viewContainer or null if not found. Node must be an Element in
     *         order for this work.
     */
    public ITagElement mapToTagElement(Node viewDefnObject,
            IDocument viewContainer)
    {
        if (viewDefnObject instanceof Element)
        {
            return findTagElement((Element) viewDefnObject, viewContainer);
        }
        return null;
    }

    @Override
    public List<Node> getViewDefnRoots(final IDocument container)
    {
        final List<Node> roots = new ArrayList<Node>();

        final IStructuredDocumentContext context =
            IStructuredDocumentContextFactory.INSTANCE.getContext(
                    container, -1);

        if (context != null)
        {
            final IDOMContextResolver resolver =
                IStructuredDocumentContextResolverFactory.INSTANCE
                .getDOMContextResolver(context);

            if (resolver != null)
            {
                final Document doc = resolver.getDOMDocument();

                if (doc != null)
                {
                    roots.add(doc);
                }
            }
        }

        return roots;
    }
    @Override
    public abstract DTELExpression getELExpression(IModelContext context)
            throws ViewHandlerException;

    // TODO: need to extend to other objects
    /**
     * @param element
     * @param constructionData
     * @param document
     * @return the component info for element
     */
    protected ViewObject createFromElement(final Element element,
            final ViewObjectConstructionStrategy<Element> constructionData,
            final IDocument document)
    {
        final ITagElement tagElement = findTagElement(element, document);
        return constructionData.createViewObject(element, tagElement);
    }

    /**
     * @param element
     * @param doc
     * @return the namespace uri for element in doc
     */
    public abstract String getNamespace(Element element, IDocument doc);

    /**
     * @param node
     * @param document
     * @return the tag element for node in document or null if none
     */
    protected abstract ITagElement findTagElement(final Element node,
            final IDocument document);

    /**
     * @param nodeName
     * @param lib
     * @return the tag element with local name nodeName in lib or null if none.
     */
    protected static ITagElement findTagElement(final String nodeName,
            final Namespace lib)
    {
        return lib.getViewElement(nodeName);
    }

    /**
     * @param namespaces
     * @param uri
     * @return the namespace object in namespaces with uri, 'uri', or null if
     *         none.
     */
    protected static Namespace getTaglib(
            final Collection<? extends Namespace> namespaces, final String uri)
    {
        for (final Namespace namespace : namespaces)
        {
            if (uri.equals(namespace.getNSUri()))
            {
                return namespace;
            }
        }
        return null;
    }

    /**
     * @return the prefix string to which a running count will be added to
     *         produced the default generated id
     */
    public String getGeneratedIdPrefix()
    {
        return GENERATED_ID;
    }

    /**
     * @param element
     * @param componentAttrName
     * @return the attribute on element matching the component attribute called
     *         componentAttrName or null if not found (this may indicate either
     *         no such attribute or that it simply not populated on element; no
     *         guarantee is made as to which it is)
     */
    public Attr mapAttributeToComponent(final Element element,
            final String componentAttrName)
    {
        // TODO: need to make meta-data type driven and validate bad conversion

        boolean mapByBeanName = true; // TODO: getMetadata for mapping instead

        if (mapByBeanName)
        {
            return element.getAttributeNode(componentAttrName);
        }

        return null;
    }

    /**
     * A design time EL expression
     *
     */
    public static class DTELExpression extends ELExpression
    {
        private final IStructuredDocumentContext _documentContext;
        private final String                     _originalText;

        /**
         * @param doc
         * @param text
         */
        public DTELExpression(final IStructuredDocumentContext doc,
                final String text)
        {
            _documentContext = doc;
            _originalText = text;
        }

        @Override
        public String getText()
        {
            return _originalText;
        }

        /**
         * @return the document context.  The document relative position of
         * the start of the EL expression is used 
         * 
         */
        public IStructuredDocumentContext getDocumentContext()
        {
            return _documentContext;
        }
        
    }
}
