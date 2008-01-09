package org.eclipse.jst.jsf.common.runtime.internal.model.behavioural;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;

/**
 * Implementation of the IValueHolderInfo.
 * 
 * @author cbateman
 *
 */
public class ValueHolderInfo implements IValueHolderInfo 
{
    /**
     * 
     */
    private static final long serialVersionUID = 5000699728360703727L;

    /**
     * the value (may include EL evaluation)
     */
    protected final Object                  _value;
    /**
     * the raw value  before EL evaluation.
     */
    protected final Object                  _localValue;
    /**
     * the converter or null if none.
     */
    protected final ConverterDecorator      _converterDecorator;
    
    /**
     * @param converterDecorator
     * @param localValue
     * @param value
     */
    public ValueHolderInfo(final ConverterDecorator converterDecorator,
            final Object localValue, final Object value) {
        super();
        _converterDecorator = converterDecorator;
        _localValue = localValue;
        _value = value;
    }
    public final Object getValue() {
        return _value;
    }
    public final Object getLocalValue() {
        return _localValue;
    }
    public final ConverterDecorator getConverter() {
        return _converterDecorator;
    }
}
