package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;

/**
 * A tag element that causes the construction registration of a converter on
 * a parent object.
 * 
 * @author cbateman
 *
 */
public interface IConverterTagElement extends IJSFTagElement
{
    /**
     * @return the type info for the converter represented by this
     * element
     */
    ConverterTypeInfo getConverter();
}
