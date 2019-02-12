/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.tagmodel;

import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IJSFTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttribute;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.TagElement;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.IAttributeAdvisor;
import org.eclipse.jst.jsf.facelet.core.internal.cm.FaceletDocumentFactory;
import org.eclipse.jst.jsf.facelet.core.internal.cm.TagInfo;

/**
 * A description of the a facelet tag
 * 
 * @author cbateman
 * 
 */
public abstract class FaceletTag extends TagElement implements IJSFTagElement
{
    /**
     * 
     */
    private static final long                    serialVersionUID = 3027895246947365781L;
    private final String                         _uri;
    private final String                         _name;
    private final TagType                        _type;
    private final String                         _tagHandlerClass;
    private final AttributeHandlerMapAdapter     _attributeHandlerMapAdapter;
    private final IAttributeAdvisor              _advisor;

    /**
     * @param uri
     * @param name
     * @param type
     * @param tagHandlerClassName
     * @param docFactory
     * @param advisor
     */
    protected FaceletTag(final String uri, final String name,
            final TagType type, final String tagHandlerClassName,
            final FaceletDocumentFactory docFactory,
            final IAttributeAdvisor advisor)
    {
        _uri = uri;
        _name = name;
        _type = type;
        _tagHandlerClass = tagHandlerClassName;
        final TagInfo tagInfo = docFactory.getOrCreateExtraTagInfo(uri);
        _attributeHandlerMapAdapter = new AttributeHandlerMapAdapter(tagInfo, advisor, name);
        _advisor = advisor;
    }

    /**
     * @return the name of the tag
     */
    @Override
    public final String getName()
    {
        return _name;
    }

    public final TagType getType()
    {
        return _type;
    }

    @Override
    public String getUri()
    {
        return _uri;
    }

    @Override
    public String getTagHandlerClassName()
    {
        return _tagHandlerClass;
    }

    @Override
    public String toString()
    {
        return "Tag Name: " + getName() + "Tag Type: " + getType(); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Map<?, ?> getAttributeHandlers()
    {
        return _attributeHandlerMapAdapter;
    }

    public Map<String, ? extends ITagAttribute> getAttributes()
    {
        return _advisor.getAttributes();
    }

}
