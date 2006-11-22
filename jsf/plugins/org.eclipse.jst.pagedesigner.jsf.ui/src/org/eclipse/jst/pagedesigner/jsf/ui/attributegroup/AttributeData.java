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
 * @author mengbo
 * @version 1.5
 */
public class AttributeData
{
    public static final String SuperType = "superTyle";
    public static final String Project = "project";

    public String uri;
    public String elementName;
    public String attributeName;
    public Object value;
    public HashMap paramMap = new HashMap();

    public AttributeData()
    {
        // TODO: do we need this constructor?
    }

    public AttributeData(String uri,String elementName,String name)
    {
        this.uri = uri;
        this.attributeName = name;
        this.elementName = elementName;
    }
}
