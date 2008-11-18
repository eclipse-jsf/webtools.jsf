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
package org.eclipse.jst.jsf.ui.internal.common;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IImageDescriptorProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfos;
import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * @author cbateman
 * 
 */
public class MetadataTagImageManager
{
    private static final String TRAIT_ICON_SMALL = "small-icon"; //$NON-NLS-1$

    private static final String TRAIT_ICON_LARGE = "large-icon"; //$NON-NLS-1$

    /**
     * @param project
     * @param tagId
     * @return small image using metadata. May be null.
     */
    public Image getSmallIconImage(final IProject project, final TagIdentifier tagId)
    {
        return getSmallIconImage(project, tagId.getUri(), tagId.getTagName());
    }
    
    /**
     * Returns small image using metadata and may be null. Caller should NOT
     * dispose the image, but should call TagImageManager's dispose(image)
     * 
     * @param project
     * @param nsUri
     * @param tagName
     * @return small image using metadata. May be null.
     */
    public Image getSmallIconImage(final IProject project, final String nsUri,
            final String tagName)
    {
        Image image = null;
        final Model model = getModel(project, nsUri);
        if (model != null)
        {
            final ImageDescriptor imgDesc = getIconImageDescriptor(model,
                    tagName, true);
            image = getOrCreateImage(imgDesc);
        }

        return image;
    }

    private Image getOrCreateImage(final ImageDescriptor imgDesc)
    {
        Image image = null;
        if (imgDesc != null)
        {
            image = JSFUiPlugin.getDefault().getImageRegistry().get(
                    imgDesc.toString());
            if (image == null)
            {
                image = imgDesc.createImage();
                JSFUiPlugin.getDefault().getImageRegistry().put(
                        imgDesc.toString(), image);
            }
            else if (image.isDisposed())
            { // should not occur, but handling just in case
                JSFUiPlugin.getDefault().getImageRegistry().remove(
                        imgDesc.toString());
                image = imgDesc.createImage();
                JSFUiPlugin.getDefault().getImageRegistry().put(
                        imgDesc.toString(), image);
            }
        }
        return image;
    }

    private Model getModel(final IProject project, final String nsUri)
    {
        final ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper
                .createMetaDataModelContext(project, nsUri);
        final Model model = TaglibDomainMetaDataQueryHelper
                .getModel(modelContext);
        // no caching at this time so there is no need to listen to model
        // notifications
        // if (model != null && !hasAdapter(model))
        // addAdapter(model);
        return model;
    }

    private ImageDescriptor getIconImageDescriptor(final Model model,
            final String tagName, final boolean small)
    {
        ImageDescriptor icon = null;

        // use palette infos if available
        final Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(model,
                "paletteInfos"); //$NON-NLS-1$
        if (trait != null)
        {
            final PaletteInfos tags = (PaletteInfos) trait.getValue();
            for (final Iterator it = tags.getInfos().iterator(); it.hasNext();)
            {
                final PaletteInfo tag = (PaletteInfo) it.next();
                if (tag.getId().equalsIgnoreCase(tagName))
                {
                    final IMetaDataSourceModelProvider sourceProvider = ((Trait) tag
                            .eContainer().eContainer())
                            .getSourceModelProvider();
                    if (small)
                    {
                        icon = getImageDescriptorFromString(sourceProvider, tag
                                .getSmallIcon());
                    }
                    else
                    {
                        icon = getImageDescriptorFromString(sourceProvider, tag
                                .getLargeIcon());
                    }

                    break;
                }
            }
        }
        else
        {
            for (final Iterator it = model.getChildEntities().iterator(); it
                    .hasNext();)
            {
                final Entity tagAsEntity = (Entity) it.next();
                if (tagAsEntity.getId().equalsIgnoreCase(tagName))
                {
                    if (small)
                    {
                        icon = getImageDescriptorFromTagTraitValueAsString(
                                tagAsEntity, TRAIT_ICON_SMALL, ImageDescriptor
                                        .getMissingImageDescriptor());
                    }
                    else
                    {
                        icon = getImageDescriptorFromTagTraitValueAsString(
                                tagAsEntity, TRAIT_ICON_LARGE, ImageDescriptor
                                        .getMissingImageDescriptor());
                    }

                    break;
                }
            }

        }

        return icon;
    }

    private ImageDescriptor getImageDescriptorFromString(
            IMetaDataSourceModelProvider sourceModelProvider, String imgDesc)
    {
        ImageDescriptor image = null;
        IImageDescriptorProvider imageProvider = (IImageDescriptorProvider) sourceModelProvider
                .getAdapter(IImageDescriptorProvider.class);
        if (imageProvider != null)
        {
            image = imageProvider.getImageDescriptor(imgDesc);
        }
        return image;
    }

    private ImageDescriptor getImageDescriptorFromTagTraitValueAsString(
            Entity entity, String key, ImageDescriptor defaultValue)
    {
        Trait t = TaglibDomainMetaDataQueryHelper.getTrait(entity, key);
        if (t != null)
        {
            String imgDesc = TraitValueHelper.getValueAsString(t);
            return getImageDescriptorFromString(t.getSourceModelProvider(),
                    imgDesc);
        }
        return defaultValue;
    }
}
