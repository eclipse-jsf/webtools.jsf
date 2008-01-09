package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;

public interface IConverterTagElement extends IJSFTagElement
{
    /**
     * @return the type info for the converter represented by this
     * element
     */
    ConverterTypeInfo getConverter();
}
