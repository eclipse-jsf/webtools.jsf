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
package org.eclipse.jst.pagedesigner.itemcreation.internal;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.elementedit.ElementEditFactoryRegistry;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.jst.pagedesigner.itemcreation.AbstractTagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;

/**
 * Creates instances of
 * {@link org.eclipse.jst.pagedesigner.itemcreation.ITagCreator}s 
 * (Eventually) Will use TagCreavtorFactories registered using
 * org.eclipse.jst.jsf.pagedesigner.tagcreationfactories ext-pt. Currently only
 * using DefaultTagCreator.
 */
public class TagCreationFactory
{
    private static TagCreationFactory INSTANCE = null;

    /**
     * @return singleton instance
     */
    public synchronized static TagCreationFactory getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new TagCreationFactory();
        }
        return INSTANCE;
    }

    /**
     * Using the TagToolPaletteEntry, locate the factory to use for tag creation
     * 
     * @param tagId
     * 
     * @return ITagCreator
     */
    public ITagCreator createTagCreator(final TagIdentifier tagId)
    {

        IElementEdit elementEdit = ElementEditFactoryRegistry.getInstance()
                .createElementEdit(tagId);

        if (elementEdit != null)
        {
            // for (ITagCreatorFactory factory : factories)
            // {
            ITagCreator tagCreator = null;
            try
            {
                tagCreator = elementEdit.getTagCreator(tagId);
            }
            catch (Exception e)
            {
                PDPlugin.getLogger(getClass()).error(
                        "Error.ProblemLoadingTagCreatorFactory", //$NON-NLS-1$
                        elementEdit.getClass().toString(), e);
            }

            if (tagCreator != null)
            {
                if (tagCreator instanceof AbstractTagCreator)
                {
                    return tagCreator;
                }
                // if non-null, skipped, log the issue
                PDPlugin.getLogger(getClass()).error(
                        "Error.ProblemLoadingTagCreatorFactory", //$NON-NLS-1$
                        "Tag creator must extend AbstractTagCreator", //$NON-NLS-1$
                        new Throwable());
            }
        }

        // all else fails, use the internal default
        return new DefaultTagCreator();
    }

}
