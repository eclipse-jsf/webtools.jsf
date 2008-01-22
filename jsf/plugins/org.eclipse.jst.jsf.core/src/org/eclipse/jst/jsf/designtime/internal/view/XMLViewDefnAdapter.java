package org.eclipse.jst.jsf.designtime.internal.view;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.w3c.dom.Attr;
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
    public abstract String getId(Node viewDefnObject)
            throws IllegalArgumentException;

    @Override
    public abstract ViewObject mapToViewObject(Node viewDefnObject,
            ViewObjectConstructionStrategy<? extends Node> constructionData,
            IDocument viewContainer);

    @Override
    public abstract List<Node> getViewDefnRoots(IDocument container);

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
    protected abstract String getNamespace(Element element, IDocument doc);

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
        for (final Iterator<? extends ITagElement> it = lib.getViewElements()
                .iterator(); it.hasNext();)
        {
            final ITagElement tagElement = it.next();

            if (nodeName.equals(tagElement.getName()))
            {
                return tagElement;
            }
        }

        return null;
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
     * @return the attribute on element matching the component attribute
     * called componentAttrName or null if not found (this may indicate
     * either no such attribute or that it simply not populated on element;
     * no guarantee is made as to which it is)
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
    
}
