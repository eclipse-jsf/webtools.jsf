package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;

/**
 * Validator decorator.
 * 
 * @author cbateman
 *
 */
public class ValidatorDecorator extends ComponentDecorator {
    /**
     * 
     */
    private static final long serialVersionUID = -2898015711621314782L;

    private final ValidatorTypeInfo     _typeInfo;

    /**
     * @param decorates
     * @param typeInfo
     */
    public ValidatorDecorator(final ComponentInfo decorates, final ValidatorTypeInfo typeInfo) {
        super(decorates);
        _typeInfo = typeInfo;
    }

    /**
     * @return the validator's type info
     */
    public final ValidatorTypeInfo getTypeInfo()
    {
        return _typeInfo;
    }
}
