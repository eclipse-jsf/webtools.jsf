/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.converter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.utils.JSFSharedImages;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.PageDesignerTraceOptions;
import org.eclipse.jst.pagedesigner.converter.html.HTMLConverterFactory;
import org.eclipse.jst.pagedesigner.converter.jsp.JSPConverterFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class ConverterFactoryRegistry
{
    List _factories = new ArrayList();

    private static ConverterFactoryRegistry _instance;

    /**
	 *
	 */
    private ConverterFactoryRegistry()
    {
        final List<IConverterFactory> facs = ConverterFacRegistryReader
                .getAllHandlers();
        if (facs != null)
        {
            for (final IConverterFactory fac : facs)
            {
                addFactory(fac);
            }
        }
        _factories.add(new JSPConverterFactory());
        _factories.add(new HTMLConverterFactory());
        
        // TODO: this is not ideal, but until we get a better system for 
        // doing converter factory ordering:
        // loop through the list and place the DTManager
    }

    /**
     * @param fac
     */
    public void addFactory(final IConverterFactory fac)
    {
        _factories.add(fac);
    }

    /**
     * @param ele
     * @param mode
     * @param targetDocument
     * @return the new btag converter
     */
    public ITagConverter createTagConverter(final Element ele, final int mode,
            final IDOMDocument targetDocument)
    {
        final ITagConverter converter = internalCreateTagConverter(ele, mode);
        if (converter != null)
        {
            converter.setDestDocument(targetDocument);
        }
        return converter;
    }

    /**
     * @param ele
     * @param mode
     * @return the new tag converter
     */
    protected final ITagConverter internalCreateTagConverter(final Element ele,
            final int mode)
    {
        final String uri = CMUtil.getElementNamespaceURI(ele);
        // first round, match uri
        for (int i = 0, size = _factories.size(); i < size; i++)
        {
            final IConverterFactory fac = (IConverterFactory) _factories.get(i);
            final String facuri = fac.getSupportedURI();
            if (facuri != null && facuri.equals(uri))
            {
                final ITagConverter converter = fac.createConverter(ele, mode);
                if (converter != null)
                {
                    if (PageDesignerTraceOptions.TRACE_CONVERTERSELECT)
                    {
                        PageDesignerTraceOptions
                                .log("ConverterFactoryRegistry: first loop, " //$NON-NLS-1$
                                        + String
                                                .format(
                                                        "Selected converter %s for uri=%s, tagname=%s", //$NON-NLS-1$
                                                        converter.getClass()
                                                                .getName(),
                                                        uri, ele.getLocalName()));
                    }
                    return converter;
                }
            }
        }
        // second round
        for (int i = 0, size = _factories.size(); i < size; i++)
        {
            final IConverterFactory fac = (IConverterFactory) _factories.get(i);
            final String facuri = fac.getSupportedURI();
            if (facuri == null)
            {
                final ITagConverter converter = fac.createConverter(ele, mode);
                if (converter != null)
                {
                    if (PageDesignerTraceOptions.TRACE_CONVERTERSELECT)
                    {
                        PageDesignerTraceOptions
                                .log("ConverterFactoryRegistry: second loop, " //$NON-NLS-1$
                                        + String
                                                .format(
                                                        "Selected converter %s for uri=%s, tagname=%s", //$NON-NLS-1$
                                                        converter.getClass()
                                                                .getName(),
                                                        uri, ele.getLocalName()));
                    }
                    return converter;
                }
            }
        }

        // can't find. We need some default tag converter for it.
        // if the tag is empty, show it as icon.
        if (uri == null || ITLDConstants.URI_HTML.equals(uri))
        {
            if (PageDesignerTraceOptions.TRACE_CONVERTERSELECT)
            {
                PageDesignerTraceOptions
                        .log("ConverterFactoryRegistry: factory not found, " //$NON-NLS-1$
                                + String
                                        .format(
                                                "Selected DumTagConverter for uri=%s, tagname=%s", //$NON-NLS-1$
                                                uri, ele.getLocalName()));
            }

            // basically, for HTML or non JSP tag, directly renders it.
            return new DumTagConverter(ele);
        }
        final CMElementDeclaration decl = CMUtil.getElementDeclaration(ele);
        if (decl == null)
        {
            if (PageDesignerTraceOptions.TRACE_CONVERTERSELECT)
            {
                PageDesignerTraceOptions
                        .log("ConverterFactoryRegistry: factory and decl not found, " //$NON-NLS-1$
                                + String
                                        .format(
                                                "Selected DumTagConverter for uri=%s, tagname=%s", //$NON-NLS-1$
                                                uri, ele.getLocalName()));
            }
            return new DumTagConverter(ele);
        }
        final int contentType = decl.getContentType();
        if (contentType == CMElementDeclaration.EMPTY)
        {
            if (PageDesignerTraceOptions.TRACE_CONVERTERSELECT)
            {
                PageDesignerTraceOptions
                        .log("ConverterFactoryRegistry: factory not found, content is EMPTY, " //$NON-NLS-1$
                                + String
                                        .format(
                                                "Selected HiddenTagConverter with UnknownImage for uri=%s, tagname=%s", //$NON-NLS-1$
                                                uri, ele.getLocalName()));
            }

            // if the tag is empty, show it as icon.
            return new HiddenTagConverter(ele, new LabelProvider()
            {
                @Override
                public Image getImage(final Object element)
                {
                    return getUnknownImage();
                }
            });
        }
        if (PageDesignerTraceOptions.TRACE_CONVERTERSELECT)
        {
            PageDesignerTraceOptions
                    .log("ConverterFactoryRegistry: fall-through to default case, " //$NON-NLS-1$
                            + String
                                    .format(
                                            "Selected DefaultUnknownTagConverter with UnknownImage for uri=%s, tagname=%s", //$NON-NLS-1$
                                            uri, ele.getLocalName()));
        }
        return new DefaultUnknownTagConverter(ele, mode);

    }

    private static Image getUnknownImage()
    {
        return JSFUICommonPlugin.getDefault().getImage(
                JSFSharedImages.DEFAULT_PALETTE_TAG_IMG);
    }

    /**
     * @return the singleton instance of the registry
     */
    public synchronized static ConverterFactoryRegistry getInstance()
    {
        if (_instance == null)
        {
            _instance = new ConverterFactoryRegistry();
        }
        return _instance;
    }
}
