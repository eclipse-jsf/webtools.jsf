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
package org.eclipse.jst.pagedesigner.jsf.ui.converter.jsfhtml;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory2;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.converter.HiddenTagConverter;
import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.dtmanager.converter.internal.DTTagConverter;
import org.eclipse.jst.pagedesigner.editors.palette.TagImageManager;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Element;


/**
 * @author mengbo
 * @version 1.5
 * @deprecated Use DTTagConverter meta-data instead
 */
public class JSFHTMLConverterFactory implements IConverterFactory
{
    private final MyLabelProvider  _labelProvider;
    
    // TODO C.B: this is a transitional step so that we can incrementally add
    // new meta-data driven tag converter one-by-one without breaking other existing
    // if a tag name is in this set, the new DTTagConverter will be created for it,
    // else, revert to original one
    private final static Set                   _dtConversionSupported;
    
    static
    {
        _dtConversionSupported = new HashSet();
        /*
        _dtConversionSupported.add(IJSFConstants.TAG_FORM);
        _dtConversionSupported.add(IJSFConstants.TAG_INPUTTEXT);
        _dtConversionSupported.add(IJSFConstants.TAG_INPUTSECRET);
        _dtConversionSupported.add(IJSFConstants.TAG_INPUTTEXTAREA);
        _dtConversionSupported.add(IJSFConstants.TAG_OUTPUTTEXT);
        _dtConversionSupported.add(IJSFConstants.TAG_OUTPUTLABEL);
        _dtConversionSupported.add(IJSFConstants.TAG_GRAPHICIMAGE);
        _dtConversionSupported.add(IJSFConstants.TAG_PANELGRID);
        */
    }
    
    /**
     * 
     */
    public JSFHTMLConverterFactory()
    {
        super();
        _labelProvider = new MyLabelProvider();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.IConverterFactory#createConverter(org.w3c.dom.Element)
     */
    public ITagConverter createConverter(Element element, int mode)
    {
        ITagConverter converter;
        String tagName = element.getLocalName();
        
        if (_dtConversionSupported.contains(tagName))
        {
            converter = createDTTagConverter(element);
        }
        else if (IJSFConstants.TAG_COLUMN.equalsIgnoreCase(tagName))
        {
            converter = new ColumnTagConverter(element);
        }
        else if (IJSFConstants.TAG_COMMANDBUTTON.equalsIgnoreCase(tagName))
        {
            converter = new CommandButtonTagConverter(element);
        }
        else if (IJSFConstants.TAG_COMMANDLINK.equalsIgnoreCase(tagName))
        {
            converter = new CommandLinkTagConverter(element);
        }
        else if (IJSFConstants.TAG_DATATABLE.equalsIgnoreCase(tagName))
        {
            converter = new DataTableTagConverter(element);
        }
        else if (IJSFConstants.TAG_FORM.equalsIgnoreCase(tagName))
        {
            converter = new JSFFormTagConverter(element);
        }
        else if (IJSFConstants.TAG_GRAPHICIMAGE.equalsIgnoreCase(tagName))
        {
            converter = new GraphicImageTagConveter(element);
        }
        else if (IJSFConstants.TAG_INPUTHIDDEN.equalsIgnoreCase(tagName))
        {
            return new HiddenTagConverter(element, _labelProvider);
        }
        else if (IJSFConstants.TAG_INPUTSECRET.equalsIgnoreCase(tagName))
        {
            converter = new InputTagConverter(element, "password");
        }
        else if (IJSFConstants.TAG_INPUTTEXT.equalsIgnoreCase(tagName))
        {
            converter = new InputTagConverter(element, "text");
        }
        else if (IJSFConstants.TAG_INPUTTEXTAREA.equalsIgnoreCase(tagName))
        {
            converter = new InputTextAreaTagConverter(element);
        }
        else if (IJSFConstants.TAG_MESSAGE.equalsIgnoreCase(tagName)
        || IJSFConstants.TAG_MESSAGES.equalsIgnoreCase(tagName))
        {
            converter = new HiddenTagConverter(element, _labelProvider);
        }
        else if (IJSFConstants.TAG_OUTPUTTEXT.equalsIgnoreCase(tagName)
        || IJSFConstants.TAG_OUTPUTFORMAT.equalsIgnoreCase(tagName))
        {
            converter = new OutputTextTagConverter(element);
        }
        else if (IJSFConstants.TAG_OUTPUTLABEL.equalsIgnoreCase(tagName))
        {
            converter = new OutputLabelTagConverter(element);
        }
        else if (IJSFConstants.TAG_OUTPUTLINK.equalsIgnoreCase(tagName))
        {
            converter = new OutputLinkTagConverter(element);
        }
        else if (IJSFConstants.TAG_PANELGRID.equalsIgnoreCase(tagName))
        {
            converter = new PanelGridTagConverter(element);
        }
        else if (IJSFConstants.TAG_PANELGROUP.equalsIgnoreCase(tagName))
        {
            converter = new PanelGroupTagConverter(element);
        }
        else if (IJSFConstants.TAG_SELECTBOOLEANCHECKBOX.equalsIgnoreCase(tagName))
        {
            converter = new SelectBooleanCheckboxTagConverter(element);
        }
        else if (IJSFConstants.TAG_SELECTMANYCHECKBOX.equalsIgnoreCase(tagName))
        {
            converter = new TableBasedSelectTagConverter(element, "checkbox");
        }
        else if (IJSFConstants.TAG_SELECTMANYLISTBOX.equalsIgnoreCase(tagName))
        {
            converter = new SelectManyListboxTagConverter(element);
        }
        else if (IJSFConstants.TAG_SELECTMANYMENU.equalsIgnoreCase(tagName))
        {
            converter = new SelectManyMenuTagConverter(element);
        }
        else if (IJSFConstants.TAG_SELECTONELISTBOX.equalsIgnoreCase(tagName))
        {
            converter = new SelectOneListboxTagConverter(element);
        }
        else if (IJSFConstants.TAG_SELECTONEMENU.equalsIgnoreCase(tagName))
        {
            converter = new SelectOneMenuTagConverter(element);
        }
        else if (IJSFConstants.TAG_SELECTONERADIO.equalsIgnoreCase(tagName))
        {
            converter = new TableBasedSelectTagConverter(element, "radio");
        }
        else
        {
            return null;
        }
        converter.setMode(mode);
        return converter;
    }

    private static class MyLabelProvider extends LabelProvider
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
                		return TagImageManager.getInstance().getSmallIconImage(wsResolver.getProject(),ITLDConstants.URI_JSF_HTML, hostElement.getLocalName());
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
        return ITLDConstants.URI_JSF_HTML;
    }
    
    private ITagConverter createDTTagConverter(Element element)
    {
        return new DTTagConverter(element);
    }
}
