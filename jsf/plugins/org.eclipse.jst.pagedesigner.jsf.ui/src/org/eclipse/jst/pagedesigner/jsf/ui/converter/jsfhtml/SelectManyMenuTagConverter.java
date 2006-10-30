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

import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class SelectManyMenuTagConverter extends SelectBasedTagConverter
{

    /**
     * @param host
     */
    public SelectManyMenuTagConverter(Element host)
    {
        super(host);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.jsfhtml.SelectBasedTagConverter#handleMultipleAndSize(org.w3c.dom.Element, org.w3c.dom.Element)
     */
    protected void handleMultipleAndSize(Element hostEle, Element selectEle)
    {
        // If the component is a UISelectMany instance, render "multiple" as the value of the 
        // "multiple" attribute.
        selectEle.setAttribute("multiple", "multiple");

        // If the "size" attribute is specified, render its value as the value of the "size" 
        // attribute. Otherwise use the number of items as the value of the "size" attribute.
        selectEle.setAttribute("size", "1");
    }

}
