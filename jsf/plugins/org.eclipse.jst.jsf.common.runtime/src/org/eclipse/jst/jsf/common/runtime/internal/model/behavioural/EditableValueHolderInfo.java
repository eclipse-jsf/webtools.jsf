package org.eclipse.jst.jsf.common.runtime.internal.model.behavioural;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValueChangeListenerDecorator;

/**
 * Implementation of the IEditableValueHolderInfo
 * 
 * @author cbateman
 *
 */
public class EditableValueHolderInfo extends ValueHolderInfo implements
        IEditableValueHolderInfo 
{
    /**
     * 
     */
    private static final long serialVersionUID = -2115990809157328451L;
    
    private final boolean       _localSetValue;
    private final Object        _submittedValue;
    private final String        _validator;
    private final String        _valueChangeListener;
    private final boolean       _isImmediate;
    private final boolean       _isRequired;
    private final boolean       _isValid;
    private List                _validators;
    private List                _valueChangeListeners;
    
    /**
     * @param converterDecorator
     * @param localValue
     * @param value
     * @param isImmediate
     * @param isRequired
     * @param isValid
     * @param localSetValue
     * @param submittedValue
     * @param validator
     * @param valueChangeListener
     */
    public EditableValueHolderInfo(final ConverterDecorator converterDecorator,
            final Object localValue, final Object value, final boolean isImmediate,
            final boolean isRequired, final boolean isValid, final boolean localSetValue,
            final Object submittedValue, final String validator, final String valueChangeListener) 
    {
        super(converterDecorator, localValue, value);
        _isImmediate = isImmediate;
        _isRequired = isRequired;
        _isValid = isValid;
        _localSetValue = localSetValue;
        _submittedValue = submittedValue;
        _validator = validator;
        _valueChangeListener = valueChangeListener;
    }

    public final boolean isLocalSetValue() {
        return _localSetValue;
    }

    public final Object getSubmittedValue() {
        return _submittedValue;
    }

    public final String getValidator() {
        return _validator;
    }

    public final String getValueChangeListener() {
        return _valueChangeListener;
    }

    public final boolean isImmediate() {
        return _isImmediate;
    }

    public final boolean isRequired() {
        return _isRequired;
    }

    public final boolean isValid() {
        return _isValid;
    }

    public void addValidator(final ValidatorDecorator validator) 
    {
        if (_validators == null)
        {
            _validators = new ArrayList(2);
        }
        _validators.add(validator);
    }

    public void addValueChangeListener(
            final ValueChangeListenerDecorator valueChangeListenerInfo) 
    {
        if (_valueChangeListeners == null)
        {
            _valueChangeListeners = new ArrayList(2);
        }
        _valueChangeListeners.add(valueChangeListenerInfo);
    }

    public List getValidators() {
        if (_validators == null)
        {
            return Collections.EMPTY_LIST;
        }
        
        return Collections.unmodifiableList(_validators);
    }

    public List getValueChangeListeners() {
        if (_valueChangeListeners == null)
        {
            return Collections.EMPTY_LIST;
        }
        
        return Collections.unmodifiableList(_valueChangeListeners);
    }
}
