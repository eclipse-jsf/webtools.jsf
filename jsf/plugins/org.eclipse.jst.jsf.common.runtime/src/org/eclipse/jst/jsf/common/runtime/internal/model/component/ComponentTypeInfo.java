package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import org.eclipse.jst.jsf.common.runtime.internal.model.types.IClassTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.types.TypeInfo;

public class ComponentTypeInfo extends TypeInfo implements IClassTypeInfo {
    /**
     * serializable uid
     */
    private static final long serialVersionUID = -311156682935177206L;
    protected final String _componentType; // may be null, since may not be
                                            // known at runtime
    protected final String _componentClass;
    protected final String _componentFamily;
    protected final String _renderFamily;

    public ComponentTypeInfo(final String componentType,
            final String componentClass, final String componentFamily,
            final String renderFamily) {
        _componentType = componentType;
        _componentClass = componentClass;
        _componentFamily = componentFamily;
        _renderFamily = renderFamily;
    }

    public final String getComponentType() {
        return _componentType;
    }

    public final String getClassName() {
        return _componentClass;
    }

    public final String getComponentFamily() {
        return _componentFamily;
    }

    public final String getRenderFamily() {
        return _renderFamily;
    }

    public String toString() {
        return "Component Type Info: type = " + _componentType + " class= "
                + _componentClass + " family=" + _componentFamily
                + " renderer=" + _renderFamily;
    }
}
