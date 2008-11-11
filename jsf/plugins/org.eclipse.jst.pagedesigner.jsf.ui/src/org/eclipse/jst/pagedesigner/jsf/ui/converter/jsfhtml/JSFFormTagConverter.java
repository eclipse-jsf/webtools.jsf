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

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.eclipse.jst.pagedesigner.converter.JSFConverterUtil;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 * @deprecated Use DTTagConverter meta-data instead
 */
public class JSFFormTagConverter extends AbstractTagConverter
{

    /**
     * @param host
     */
    public JSFFormTagConverter(Element host)
    {
        super(host);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
     */
    protected Element doConvertRefresh()
    {
        // The value of the "method" attribute must be "post". The value of the 
        // "action" attribute must be the result of passing the view identifier 
        // of the current view to the getActionURL()  method of the ViewHandler for 
        // this application, then passing that String to the encodeActionURL()  
        // method on the ExternalContext. 
        // XXX: as our tag converter only serve designer and preview, so we only
        // deal with attributes relating to visual effect. Ignoring "method", "action",
        // etc.
        Element hostEle = getHostElement();

        Element formEle = createElement(IHTMLConstants.TAG_FORM);

        // If the "styleClass" attribute is specified, render its value as the 
        // value of the "class" attribute. 
        JSFConverterUtil.copyAttribute(hostEle, IJSFConstants.ATTR_STYLECLASS, formEle, IHTMLConstants.ATTR_CLASS);
        formEle.removeAttribute(IJSFConstants.ATTR_STYLECLASS);

        // pass through other attributes
        // XXX: handle ignored attributes here.
        JSFConverterUtil.copyAllAttributes(hostEle, formEle, null);

        // Render all the necessary hidden fields for all commandLink instances 
        // in the page just before the close of the "form" element.
        // XXX: ignored for these hidden fields.

        // children of old <h:form> should still be children.
        this.copyChildren(hostEle, formEle);

        return formEle;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isMultiLevel()
     */
    public boolean isMultiLevel()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isWidget()
     */
    public boolean isWidget()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#needBorderDecorator()
     */
    public boolean needBorderDecorator()
    {
        return true;
    }
}
