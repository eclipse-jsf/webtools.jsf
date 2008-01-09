package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
    
    /**
     * the component id
     */
    protected final String              _id;
    /**
     * the component's parent or null if none
     */
    protected final ComponentInfo       _parent;
    /**
     * the type info for this component
     */
    protected final ComponentTypeInfo   _componentTypeInfo;
    /**
     * the rendered flage
     */
    protected final boolean             _isRendered;

    /**
     * @param id
     * @param parent
     * @param componentTypeInfo
     * @param isRendered
     */
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
    
    /**
     * @return the id
     */
    public final String getId() {
        return _id;
    }

    /**
     * @return the component type info
     */
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
     * This is a convenience method for {@link #getDecorators(Class)}
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
    
    /**
     * @param component
     * @return if component corresponds to a facet of this component, returns
     * the name of that facet.  Returns null if not found.
     */
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
    
    /**
     * @param name
     * @return if this has a facet called name, then returns it's single root
     * component.
     */
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
    
    /**
     * @return used for toString.  Clients should not use.
     */
    public String getMostSpecificComponentName()
    {
        return "Component";
    }

    /**
     * @return the parent of this component or null.
     */
    public final ComponentInfo getParent() {
        return _parent;
    }

    /**
     * @return the rendered flag
     */
    public final boolean isRendered() {
        return _isRendered;
    }
}
