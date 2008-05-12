/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
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
    /**
     * 
     */
    private static final long serialVersionUID = 2854457220470229847L;
    private final ConverterTypeInfo     _converterTypeInfo;

    /**
     * @param elementDecl
     * @param converterTypeInfo
     * @param advisor 
     */
    public TLDConverterTagElement(final TLDElementDeclaration elementDecl, final ConverterTypeInfo converterTypeInfo, final IAttributeAdvisor advisor)
    {
        super(elementDecl, advisor);
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
