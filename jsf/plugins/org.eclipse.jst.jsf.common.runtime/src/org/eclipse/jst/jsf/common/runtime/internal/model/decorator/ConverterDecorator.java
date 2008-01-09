package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;

/**
 * A converter decorator.
 * 
 * @author cbateman
 *
 */
public class ConverterDecorator extends ComponentDecorator {

    private final ConverterTypeInfo     _typeInfo;
    
    /**
     * serializable id
     */
    private static final long serialVersionUID = 3838224353030247227L;
    
    
    /**
     * @param decorates
     * @param typeInfo
     */
    public ConverterDecorator(final ComponentInfo decorates, final ConverterTypeInfo typeInfo)
    {
        super(decorates);
        _typeInfo = typeInfo;
    }

    /**
     * @return the converter's type info.
     */
    public final ConverterTypeInfo getTypeInfo()
    {
        return _typeInfo;
    }
}
