package org.eclipse.jst.jsf.common.runtime.internal.model.behavioural;

import java.io.Serializable;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;

/**
 * Represents design time information about the ValueHolder behavioural
 * interface.
 * 
 * @author cbateman
 *
 */
public interface IValueHolderInfo extends Serializable
{
    /**
     * @return the value, may be null
     */
    Object getValue();
    
    /**
     * @return the raw value without expression value evaluation.  Note that 
     * {@link #getValue()} may return the same value in cases where this value
     * holder is derived at design time without EL expression evaluation. May be null.
     */
    Object getLocalValue();
    
    /**
     * @return the converter for this value holder or null if none.
     */
    ConverterDecorator getConverter();
}
