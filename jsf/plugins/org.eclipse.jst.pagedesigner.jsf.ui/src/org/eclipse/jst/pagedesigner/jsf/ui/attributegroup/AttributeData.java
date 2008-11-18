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
package org.eclipse.jst.pagedesigner.jsf.ui.attributegroup;

import java.util.HashMap;

/**
 * Information and value about a tag attribute
 * @author mengbo
 * @version 1.5
 */
class AttributeData
{
    /**
     * possible key in the param map
     */
    static final String SuperType = "superTyle"; //$NON-NLS-1$
    /**
     * possible key in the param map
     */
    static final String Project = "project"; //$NON-NLS-1$

    private final String uri;
    private final String elementName;
    private final String attributeName;
    private Object value;
    private HashMap paramMap; // null; lazy initialized

    /**
     * @param uri
     * @param elementName
     * @param name
     */
    public AttributeData(String uri,String elementName,String name)
    {
        this.uri = uri;
        this.attributeName = name;
        this.elementName = elementName;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @return the element name
     */
    public String getElementName() {
        return elementName;
    }

    /**
     * @return the attribute name
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return the param map
     */
    public HashMap getParamMap() 
    {
        if (paramMap == null)
        {
            paramMap = new HashMap();
        }
        return paramMap;
    }
}
