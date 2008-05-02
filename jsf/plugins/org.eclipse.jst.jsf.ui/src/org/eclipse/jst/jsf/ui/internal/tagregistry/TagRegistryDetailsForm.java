/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractXMLSectionsDetailsForm;
import org.eclipse.jst.jsf.core.internal.TagRegistryFactoryInfo;
import org.eclipse.jst.jsf.ui.internal.tagregistry.TaglibContentProvider.TagRegistryInstance;
import org.eclipse.swt.widgets.Composite;

/**
 * Details form for a tag registry
 * 
 * @author cbateman
 * 
 */
public class TagRegistryDetailsForm extends AbstractXMLSectionsDetailsForm
{
    private final static String TAGREGISTRY_SECTION_KEY = "tagRegistrySection";
    private XMLTextSection      _tagRegistrySection;

    @Override
    protected Map<? extends Object, XMLTextSection> createXMLTextSections(
            final Composite parent)
    {
        final Map<String, XMLTextSection> sections = new HashMap<String, XMLTextSection>();
        _tagRegistrySection = new XMLTextSection(getToolkit(), parent,
                "Namespace");
        sections.put(TAGREGISTRY_SECTION_KEY, _tagRegistrySection);
        return sections;
    }

    @Override
    protected Set<XMLTextSection> getInitiallyExpanded(
            final Map<Object, XMLTextSection> sections)
    {
        return Collections.singleton(_tagRegistrySection);
    }

    @Override
    protected void doUpdateSelection(final Object newSelection)
    {
        if (newSelection instanceof TagRegistryInstance)
        {
            final TagRegistryInstance tagRegistry = (TagRegistryInstance) newSelection;
            final TagRegistryFactoryInfo info = tagRegistry.getInfo();

            if (info != null)
            {
                final String format = "<form><p><b>Description:</b> %s</p> <p><b>Id:</b> %s</p> <p><b>Content-Types:</b> %s</p></form>";
                final String description = info.getDescription();
                final String id = info.getId();
                final Set<IContentType> contentTypes = info.getContentTypes();
                String contentTypeLabel = "";
                final Iterator<IContentType> it = contentTypes.iterator();
                for (int i = 0; i < contentTypes.size() - 1 && it.hasNext(); i++)
                {
                    final IContentType ctype = it.next();
                    contentTypeLabel += ctype.getName() + ",";
                }

                if (it.hasNext())
                {
                    final IContentType ctype = it.next();
                    contentTypeLabel += ctype.getName();
                }

                _tagRegistrySection.setText(String.format(format, description,
                        id, contentTypeLabel), true, false);
                _tagRegistrySection.refresh();
            }
        }
    }
}
