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
package org.eclipse.jst.jsf.facelet.core.internal.registry;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ComponentPropertyHandler;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttribute;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttributeHandler;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.TagAttributeHandler;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.ViewMetadataLoader;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.IAttributeAdvisor;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute;
import org.eclipse.jst.jsf.facelet.core.internal.tagmodel.FaceletAttribute;

/**
 * An attribute advisor that composes multiple strategies for deriving information
 * about Facelet tags.
 * 
 * @author cbateman
 *
 */
/*package*/ class MetadataAttributeAdvisor implements IAttributeAdvisor
{
    private final TagIdentifier      _tagId;
    private final ViewMetadataLoader _loader;
    private List<FaceletTaglibTagAttribute> _attributes;

    /**
     * @param tagId
     * @param loader
     * @param attributes 
     */
    public MetadataAttributeAdvisor(final TagIdentifier tagId,
            final ViewMetadataLoader loader, final List<FaceletTaglibTagAttribute> attributes)
    {
        _tagId = tagId;
        _loader = loader;
        _attributes = attributes;
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
        final Map<String, ITagAttribute>  attributes = new HashMap<String, ITagAttribute>();
        
        for (final FaceletTaglibTagAttribute attribute : _attributes)
        {
            attributes.put(attribute.getName(), new FaceletAttribute(attribute));
        }
        return Collections.unmodifiableMap(attributes);
    }
}
