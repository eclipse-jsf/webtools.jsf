package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.common.runtime.internal.debug.RenderNode;
import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.FacetDecorator;


/**
 * Models a basic UI component instance
 * TODO: should implement a visitor pattern to traverse component trees
 * @author cbateman
 *
 */
public class ComponentInfo extends ViewObject implements Serializable
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = 2517204356825585699L;
    
    private final static int            DEFAULT_ARRAY_SIZE = 4;
    
    protected final String              _id;
    protected final ComponentInfo       _parent;
    protected final ComponentTypeInfo   _componentTypeInfo;
    protected final boolean             _isRendered;
    protected RenderNode                _rootRenderedNode; // may be null if we don't have it

    protected ComponentInfo(final String id, final ComponentInfo parent, final ComponentTypeInfo componentTypeInfo, final boolean isRendered)
    {
        _id = translateForNull(id);
        _parent = parent;
        _componentTypeInfo = componentTypeInfo;
        _isRendered = isRendered;
    }
    
    private String translateForNull(final String arg)
    {
        
        if (arg == null || "!".equals(arg.trim()))
        {
            return null;
        }
        return arg.trim();
    }
    
    private List/*<ComponentInfo>*/    _children;
    
    public final String getId() {
        return _id;
    }

    public final ComponentTypeInfo getComponentTypeInfo() {
        return _componentTypeInfo;
    }

    /**
     * @return the children.  List is unmodifiable.  List contains all children
     * including facets.
     */
    public final List/*<ComponentInfo>*/ getChildren() {
        if (_children == null)
        {
            return Collections.EMPTY_LIST;
        }
        return Collections.unmodifiableList(_children);
    }

    /**
     * Get the sub-set of {@link #getChildren()} that are facets.
     * This is a convenience method for {@link #getDecorators(FacetDecorator.class)}
     * 
     * @return all component children that are facets
     */
    public final List getFacets()
    {
        return getDecorators(ComponentFactory.FACET);
    }
    
    /**
     * @param childComponent
     */
    public final void addChild(final ComponentInfo childComponent)
    {
        if (_children == null)
        {
            _children = new ArrayList(DEFAULT_ARRAY_SIZE);
        }
        _children.add(childComponent);
    }
    
    /**
     * @param name
     * @param facetComponent
     */
    public final void addFacet(final String name, final ComponentInfo facetComponent)
    {
        addChild(facetComponent);
        addDecorator(new FacetDecorator(name, facetComponent));
    }
    
    public final String getFacetName(final ComponentInfo component)
    {
        if (component == null) return null;
        
        final List  facets = getDecorators(ComponentFactory.FACET);

        for (final Iterator it = facets.iterator(); it.hasNext();)
        {
            final FacetDecorator facet = (FacetDecorator) it.next();
            if (component == facet.getDecorates())
            {
                return facet.getName();
            }
        }
        
        // component is not a facet
        return null;
    }
    
    public final ComponentInfo getFacet(final String name)
    {
        if (name == null) return null;
        
        final List  facets = getDecorators(ComponentFactory.FACET);

        for (final Iterator it = facets.iterator(); it.hasNext();)
        {
            final FacetDecorator  facet = (FacetDecorator) it.next();
            if (name.equals(facet.getName()))
            {
                return facet.getDecorates();
            }
        }
        
        // not found
        return null;
    }
    
    public String toString()
    {
        final String parentId = _parent != null ? _parent.getId() : "null";
        return getMostSpecificComponentName() + ": id=" + _id + ", parentId: "
                + parentId + ", family="
                + _componentTypeInfo.getComponentFamily() + ", render="
                + _componentTypeInfo.getRenderFamily();
    }
    
    public String getMostSpecificComponentName()
    {
        return "Component";
    }

    public final void setRenderNode(final RenderNode rootNode)
    {
        _rootRenderedNode = rootNode;
    }
    
    public final RenderNode getRenderNode()
    {
        return _rootRenderedNode;
    }

    public final ComponentInfo getParent() {
        return _parent;
    }

    public final boolean isRendered() {
        return _isRendered;
    }
}
