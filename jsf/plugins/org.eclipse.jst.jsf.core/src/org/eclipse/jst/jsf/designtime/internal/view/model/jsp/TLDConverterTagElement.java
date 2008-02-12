package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IConverterTagElement;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

/**
 * A TLD-defined tag (i.e. JSP) that maps one-to-one with a JSF Converter
 * 
 * @author cbateman
 *
 */
public class TLDConverterTagElement extends TLDJSFTagElement implements IConverterTagElement
{
    private final ConverterTypeInfo     _converterTypeInfo;
    
    /**
     * @param elementDecl
     * @param converterTypeInfo
     */
    public TLDConverterTagElement(TLDElementDeclaration elementDecl, ConverterTypeInfo converterTypeInfo)
    {
        super(elementDecl);
        _converterTypeInfo = converterTypeInfo;
    }

    @Override
    public TagType getType()
    {
        return TagType.CONVERTER;
    }

    /**
     * @return the type info for this converter
     */
    public final ConverterTypeInfo getConverter()
    {
        return _converterTypeInfo;
    }
    
    @Override
    public String toString()
    {
        return _converterTypeInfo.toString();
    }
}
