/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ComponentPropertyHandler;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttribute;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttributeHandler;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.TagAttributeHandler;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.ViewMetadataLoader;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDAttributeDeclaration;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;

/* package */class MetadataAttributeAdvisor implements IAttributeAdvisor
{
    private final TagIdentifier      _tagId;
    private final ViewMetadataLoader _loader;
    private final TLDElementDeclaration _tldElement;

    public MetadataAttributeAdvisor(final TagIdentifier tagId,
            final ViewMetadataLoader loader, TLDElementDeclaration tldElement)
    {
        _tagId = tagId;
        _loader = loader;
        _tldElement = tldElement;
    }

    public ITagAttributeHandler createAttributeHandler(final String name)
    throws UnknownAttributeException
    {
        final AttributeToPropertyMapping mapping = _loader.getAttributeMapping(
                _tagId, name);
        if (mapping != null)
        {
            final String customHandler = mapping.getCustomConversionFactoryId();
            final boolean isELAllowed = mapping.isElAllowed();
            final String propertyName = mapping.getPropertyName();
            if (propertyName != null)
            {
                return new ComponentPropertyHandler(customHandler, name,
                        isELAllowed, propertyName);
            }
            return new TagAttributeHandler(customHandler, name, isELAllowed);
        }
        return new TagAttributeHandler(null, name, false);
    }

    public Map<String, ? extends ITagAttribute> getAttributes()
    {
        final Map<String, TLDTagAttribute> attributes = new HashMap<String, TLDTagAttribute>();
        
        for (int i = 0; i < _tldElement.getAttributes().getLength(); i++)
        {
            CMNode item = _tldElement.getAttributes().item(i);
            if (item instanceof TLDAttributeDeclaration)
            {
                TLDTagAttribute attr = new TLDTagAttribute((TLDAttributeDeclaration) item);
                attributes.put(attr.getName(), attr);
            }
        }
        return attributes;
    }
}
