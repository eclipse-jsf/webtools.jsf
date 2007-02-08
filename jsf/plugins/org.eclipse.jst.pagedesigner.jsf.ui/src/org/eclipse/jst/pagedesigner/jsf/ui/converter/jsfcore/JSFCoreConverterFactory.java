/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.converter.jsfcore;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.eclipse.jst.pagedesigner.converter.HiddenTagConverter;
import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.converter.TagConverterToInlineBlock;
import org.eclipse.jst.pagedesigner.jsf.ui.JSFUIPlugin;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.DTTagConverter;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Element;


/**
 * @author mengbo
 * @version 1.5
 */
public class JSFCoreConverterFactory implements IConverterFactory
{

    private final ILabelProvider        _labelProvider;
   
    // TODO C.B: this is a transitional step so that we can incrementally add
    // new meta-data driven tag converter one-by-one without breaking other existing
    // if a tag name is in this set, the new DTTagConverter will be created for it,
    // else, revert to original one
    private final static Set                   _dtConversionSupported;
    
    static
    {
        _dtConversionSupported = new HashSet();
        _dtConversionSupported.add(IJSFConstants.TAG_VIEW);
        _dtConversionSupported.add(IJSFConstants.TAG_FACET);
    }
    
    /**
     * 
     */
    public JSFCoreConverterFactory()
    {
        super();
        _labelProvider = new MyLabelProvider();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.IConverterFactory#createConverter(org.w3c.dom.Element)
     */
    public ITagConverter createConverter(Element element, int mode)
    {
        String tagName = element.getLocalName();
        ITagConverter converter;
        
        if (_dtConversionSupported.contains(tagName))
        {
            converter = createDTTagConverter(element);
        }
        else if (IJSFConstants.TAG_VIEW.equalsIgnoreCase(tagName) || IJSFConstants.TAG_SUBVIEW.equalsIgnoreCase(tagName))
        {
            converter = new ViewTagConverter(element);
            ((ViewTagConverter) converter).setNeedBorderDecorator(true);
        }
        else if (IJSFConstants.TAG_FACET.equalsIgnoreCase(tagName)
                || IJSFConstants.TAG_VERBATIM.equalsIgnoreCase(tagName))
        {
            AbstractTagConverter toSpan = new TagConverterToInlineBlock(element, mode);
            toSpan.setMinWidth(10);
            toSpan.setMinHeight(10);
            toSpan.setNeedBorderDecorator(true);
            converter = toSpan;
        }
        else if (IJSFConstants.TAG_LOADBUNDLE.equalsIgnoreCase(tagName))
        {
            converter = new LoadBundleTagConverter(element, _labelProvider);
        }
        else
        {
            converter = new HiddenTagConverter(element, _labelProvider);
        }
        converter.setMode(mode);
        return converter;
    }

    private static class MyLabelProvider extends org.eclipse.jface.viewers.LabelProvider
    {

        public Image getImage(Object element) 
        {
            if (element instanceof ITagConverter)
            {
                final Element hostElement = ((ITagConverter)element).getHostElement();
                final String tagName = hostElement.getLocalName();
                return getJSFCoreSharedImage(tagName);
            }
            
            return null;
        }
    }
    
    /**
     * @param tagName
     * @return
     */
    private static Image getJSFCoreSharedImage(String tagName)
    {
        Image image = JSFUIPlugin.getDefault().getImage("palette/JSFCORE/small/JSF_" + tagName.toUpperCase() + ".gif");
        return image;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.IConverterFactory#getSupportedURI()
     */
    public String getSupportedURI()
    {
        return IJMTConstants.URI_JSF_CORE;
    }
    
    private ITagConverter createDTTagConverter(Element element)
    {
        return new DTTagConverter(element);
    }
}
