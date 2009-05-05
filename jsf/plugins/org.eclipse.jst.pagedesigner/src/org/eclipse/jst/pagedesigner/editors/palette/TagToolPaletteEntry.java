/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.palette;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationTool;

/**
 * Represents a tag palette item entry in the web page designer palette
 * 
 */
public class TagToolPaletteEntry extends CombinedTemplateCreationEntry
        implements IDropSourceDataProvider
{

    /**
     * Constructor
     * 
     * @param template
     *            the template item for the drop
     * @param label
     * @param shortDescription
     * @param iconSmall
     * @param iconLarge
     */
    public TagToolPaletteEntry(final ITagDropSourceData template,
            final String label, final String shortDescription,
            final ImageDescriptor iconSmall, final ImageDescriptor iconLarge)
    {
        super(label, shortDescription, template, NOOP_FACTORY, iconSmall,
                iconLarge);
    }

    /**
     * @return tagName
     */
    public String getTagName()
    {
        return getTemplate().getTagName();
    }

    /**
     * Convenience method returning the tag libraries default prefix, if
     * applicable
     * 
     * @return default prefix
     */
    public String getDefaultPrefix()
    {
        return getTemplate().getDefaultPrefix();
    }

    /**
     * @return uri of the tag's library
     */
    public String getURI()
    {
        return getTemplate().getNamespace();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.palette.ToolEntry#createTool()
     */
    @Override
    public Tool createTool()
    {
        return new ItemCreationTool(getTemplate());
    }

    private final static CreationFactory NOOP_FACTORY = new CreationFactory()
    {
        public Object getNewObject()
        {
            // should never get called
            throw new UnsupportedOperationException(
                    "createTool should be overriden, so this should never be called"); //$NON-NLS-1$
        }

        public Object getObjectType()
        {
            // should never get called
            throw new UnsupportedOperationException(
                    "createTool should be overriden, so this should never be called"); //$NON-NLS-1$
        }
    };

    @Override
    public ITagDropSourceData getTemplate()
    {
        return (ITagDropSourceData) super.getTemplate();
    }

    public IDropSourceData getDropSourceData()
    {
        return getTemplate();
    }
}
