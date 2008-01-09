package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import org.eclipse.jst.jsf.common.runtime.internal.model.types.IClassTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.types.TypeInfo;

/**
 * Type information about a UIComponent
 * 
 * @author cbateman
 *
 */
public class ComponentTypeInfo extends TypeInfo implements IClassTypeInfo {
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
     * the fully qualified class name of the implementation class for this component.
     */
    protected final String _componentClass;
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
            final String renderFamily) {
        _componentType = componentType;
        _componentClass = componentClass;
        _componentFamily = componentFamily;
        _renderFamily = renderFamily;
    }

    /**
     * @return the component type or null if unknown (may not be at runtime)
     */
    public final String getComponentType() {
        return _componentType;
    }

    public final String getClassName() {
        return _componentClass;
    }

    /**
     * @return the component family
     */
    public final String getComponentFamily() {
        return _componentFamily;
    }

    /**
     * @return the render family
     */
    public final String getRenderFamily() {
        return _renderFamily;
    }

    public String toString() {
        return "Component Type Info: type = " + _componentType + " class= "
                + _componentClass + " family=" + _componentFamily
                + " renderer=" + _renderFamily;
    }
}
