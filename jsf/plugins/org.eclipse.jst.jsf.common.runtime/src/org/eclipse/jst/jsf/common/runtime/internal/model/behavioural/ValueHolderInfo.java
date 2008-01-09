package org.eclipse.jst.jsf.common.runtime.internal.model.behavioural;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;

public class ValueHolderInfo implements IValueHolderInfo 
{
    /**
     * 
     */
    private static final long serialVersionUID = 5000699728360703727L;

    protected final Object                  _value;
    protected final Object                  _localValue;
    protected final ConverterDecorator      _converterDecorator;
    
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
