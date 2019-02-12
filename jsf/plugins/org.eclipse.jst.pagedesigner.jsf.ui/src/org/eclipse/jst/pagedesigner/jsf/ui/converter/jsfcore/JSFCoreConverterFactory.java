/*******************************************************************************
 * Copyright (c) 2006, 2011 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http:// https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.converter.jsfcore;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory2;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.eclipse.jst.pagedesigner.converter.HiddenTagConverter;
import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.converter.TagConverterToInlineBlock;
import org.eclipse.jst.pagedesigner.dtmanager.converter.internal.DTTagConverter;
import org.eclipse.jst.pagedesigner.editors.palette.TagImageManager;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Element;


/**
 * @author mengbo
 * @version 1.5
 * @deprecated Use DTTagConverter meta-data instead
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
        /*
        _dtConversionSupported.add(IJSFConstants.TAG_VIEW);
        _dtConversionSupported.add(IJSFConstants.TAG_FACET);
        _dtConversionSupported.add(IJSFConstants.TAG_VERBATIM);
        */
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
                IStructuredDocumentContext context = IStructuredDocumentContextFactory2.INSTANCE.getContext(hostElement);
                if (context != null){   
                	IWorkspaceContextResolver wsResolver  = IStructuredDocumentContextResolverFactory.INSTANCE.getWorkspaceContextResolver(context);
                	if (wsResolver != null){
                		return TagImageManager.getInstance().getSmallIconImage((IFile)wsResolver.getResource(),ITLDConstants.URI_JSF_CORE, hostElement.getLocalName());
                	}
                }
            }
            
            return null;
        }
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.IConverterFactory#getSupportedURI()
     */
    public String getSupportedURI()
    {
        return ITLDConstants.URI_JSF_CORE;
    }
    
    private ITagConverter createDTTagConverter(Element element)
    {
        return new DTTagConverter(element);
    }
}
