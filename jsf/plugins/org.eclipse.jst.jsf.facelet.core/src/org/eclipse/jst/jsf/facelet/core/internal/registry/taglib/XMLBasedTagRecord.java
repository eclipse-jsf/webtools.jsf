/*******************************************************************************
 * Copyright (c) 2008, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag;

/**
 * A tag record based on an xml tag defined Facelet taglib.xml
 * @author cbateman
 *
 */
public class XMLBasedTagRecord extends FaceletTagRecord
{
    /**
     * 
     */
    private static final long serialVersionUID = 1411551451386954263L;
    private final FaceletTaglib _taglibDefn;
    private Map<String, FaceletTaglibTag>       _tagIndexByName;

    /**
     * @param taglibDefn
     * @param descriptor 
     */
    public XMLBasedTagRecord(final FaceletTaglib taglibDefn, final TagRecordDescriptor descriptor)
    {
        super(descriptor);
        _taglibDefn = taglibDefn;
    }

    @Override
    public String getURI()
    {
        return _taglibDefn.getNamespaceUri();
    }


    @Override
    public FaceletTaglibTag getTag(final String name)
    {
        return getAndIndexElementDeclaration(name);
    }

    private synchronized FaceletTaglibTag getAndIndexElementDeclaration(final String name)
    {
        FaceletTaglibTag tagDefn = null;

        if (_tagIndexByName == null)
        {
            _tagIndexByName = new HashMap<String, FaceletTaglibTag>();
        }
        else
        {
            tagDefn = _tagIndexByName.get(name);
        }

        if (tagDefn == null && _tagIndexByName.size() < _taglibDefn.getTag().size())
        {
            tagDefn = findTag(name);
            if (tagDefn != null)
            {
                _tagIndexByName.put(name, tagDefn);
            }
        }

        return tagDefn;
    }

    private FaceletTaglibTag findTag(final String name)
    {
        for (final FaceletTaglibTag tag : _taglibDefn.getTag())
        {
            if (name.equals(tag.getTagName()))
            {
                return tag;
            }
        }
        return null;
    }

    @Override
    public List<FaceletTaglibTag> getTags()
    {
        return Collections.unmodifiableList(_taglibDefn.getTag());
    }

    public int getNumTags()
    {
        return _taglibDefn.getTag().size();
    }
}
