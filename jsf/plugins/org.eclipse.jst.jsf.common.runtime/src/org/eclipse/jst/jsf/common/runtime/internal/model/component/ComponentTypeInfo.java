package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import org.eclipse.jst.jsf.common.runtime.internal.model.types.ClassTypeInfo;

/**
 * Type information about a UIComponent
 * 
 * @author cbateman
 * 
 */
public class ComponentTypeInfo extends ClassTypeInfo
{
    /**
     * serializable uid
     */
    private static final long serialVersionUID = -311156682935177206L;
    /**
     * the ComponentType (see JSF spec for definition)
     */
    protected final String _componentType; // may be null, since may not be
    // known at runtime
    /**
     * the component family (see JSF spec)
     */
    protected final String _componentFamily;
    /**
     * the render family (see JSF spec)
     */
    protected final String _renderFamily;
    
    /**
     * @param componentType
     * @param componentClass
     * @param componentFamily
     * @param renderFamily
     */
    public ComponentTypeInfo(final String componentType,
            final String componentClass, final String componentFamily,
            final String renderFamily)
    {
        super(componentClass, new String[0], new String[0]);
        _componentType = componentType;
        _componentFamily = componentFamily;
        _renderFamily = renderFamily;
    }

    /**
     * @param componentType
     * @param superClasses
     * @param interfaces
     * @param componentClass
     * @param componentFamily
     * @param renderFamily
     */
    public ComponentTypeInfo(final String componentType,
            final String componentClass, 
            final String[] superClasses, final String[] interfaces,
            final String componentFamily,
            final String renderFamily)
    {
        super(componentClass, superClasses, interfaces);
        _componentType = componentType;
        _componentFamily = componentFamily;
        _renderFamily = renderFamily;
    }

    /**
     * @return the component type or null if unknown (may not be at runtime)
     */
    public final String getComponentType()
    {
        return _componentType;
    }

    /**
     * @return the component family
     */
    public final String getComponentFamily()
    {
        return _componentFamily;
    }

    /**
     * @return the render family
     */
    public final String getRenderFamily()
    {
        return _renderFamily;
    }

    public String toString()
    {
        return "Component Type Info: type = " + _componentType + " family=" + _componentFamily
                + " renderer=" + _renderFamily + ", "+super.toString();
    }
}
