package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import java.util.List;
import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;

/**
 * A design-time analog for the UIOutput
 * 
 * @author cbateman
 *
 */
public class UIOutputInfo extends ComponentInfo implements IValueHolderInfo {
    /**
     * serializable uid
     */
    private static final long serialVersionUID = 9096297578991706150L;

    /**
     * the value
     */
    protected final Object _value;
    /**
     * the value before any EL evaluation
     */
    protected final Object _localValue;

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param valueHolderInfo
     * @param isRendered
     */
    protected UIOutputInfo(final String id, final ComponentInfo parent,
            final ComponentTypeInfo typeInfo,
            final IValueHolderInfo valueHolderInfo, final boolean isRendered) {
        super(id, parent, typeInfo, isRendered);

        if (valueHolderInfo == null) {
            _value = null;
            _localValue = null;
        } else {
            _value = valueHolderInfo.getValue();
            _localValue = valueHolderInfo.getLocalValue();

            final ConverterDecorator converter = valueHolderInfo.getConverter();
            if (converter != null) {
                addDecorator(converter, ComponentFactory.CONVERTER);
            }
        }
    }

    /**
     * @param parent
     * @param typeInfo
     * @param attributes
     */
    protected UIOutputInfo(final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final Map attributes)
    {
        this(getStringProperty("id", attributes, true),
                parent,
                typeInfo,
                getValueHolderInfo("$valueHolderInfo", attributes),
                getBooleanProperty("rendered", attributes));
    }
    
    /**
     * @param key
     * @param attributes
     * @return the non-standard value holder info that encapsulates the
     * ValueHolder properties.  This is never mandatory.
     */
    protected static IValueHolderInfo getValueHolderInfo(String key, Map attributes)
    {
        return (IValueHolderInfo) attributes.get(key);
    }
    
    // @Override
    protected String getMostSpecificComponentName() {
        return "UIOutput";
    }

    public final ConverterDecorator getConverter() {
        // should only be a single converter decorator...
        // so on this interface we'll return the first one if present.
        // to do things like error checking, use the getDecorator
        final List converters = getDecorators(ComponentFactory.CONVERTER);

        if (converters.size() > 0) {
            return (ConverterDecorator) converters.get(0);
        }

        return null;
    }

    public final Object getLocalValue() {
        return _localValue;
    }

    public final Object getValue() {
        return _value;
    }
}