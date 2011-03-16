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
package org.eclipse.jst.jsf.facelet.core.internal.util;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.facelet.core.internal.cm.addtagmd.ElementData;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfos;

/**
 * Loads tag meta-data.
 * 
 * @author cbateman
 * 
 */
public class TagMetadataLoader
{
    private static final String PALETTE_INFOS      = "paletteInfos"; //$NON-NLS-1$

    private static final String TRAIT_DISPLAY_NAME = "displayName"; //$NON-NLS-1$

    private static final String TRAIT_DESCRIPTION  = "description"; //$NON-NLS-1$

    private static final String TRAIT_ADDITIONALELEMENTDATA = "additionalElementData"; //$NON-NLS-1$
    
    private final IProject      _project;

    /**
     * @param project
     */
    public TagMetadataLoader(final IProject project)
    {
        _project = project;
    }

    /**
     * @param nsUri
     * @param tagName
     * @return the display name for tagName or null if not found.
     */
    public String getDisplayName(final String nsUri, final String tagName)
    {
        return getString(nsUri, tagName, TRAIT_DISPLAY_NAME);
    }

    /**
     * @param nsUri
     * @param tagName
     * @return the description for tagName or null if not found.
     */
    public String getDescription(final String nsUri, final String tagName)
    {
        return getString(nsUri, tagName, TRAIT_DESCRIPTION);
    }

    /**
     * @param nsUri
     * @param tagName
     * @return attribute data identified by the name or null.
     */
    public ElementData getElementData(final String nsUri,
            final String tagName)
    {
		final IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(_project);
		final ITaglibDomainMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(context);
		final Entity entity = query.getQueryHelper().getEntity(nsUri, tagName);

//        final ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper
//                .createMetaDataModelContext(_project, nsUri);
//
//        final Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(
//                modelContext, tagName);

        if (entity != null)
        {
            Trait trait = query.findTrait(entity, TRAIT_ADDITIONALELEMENTDATA);
//                TaglibDomainMetaDataQueryHelper.getTrait(entity, TRAIT_ADDITIONALELEMENTDATA);
            if (trait != null)
            {
                EObject value= trait.getValue();
                if (value instanceof ElementData)
                {
                    return (ElementData) value;
                }
            }
        }
        return null;
    }

    private String getString(final String nsUri, final String tagName,
            final String key)
    {
        String value = null;
		final IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(_project);
		final ITaglibDomainMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(context);
        final Model model = getModel(query, nsUri);
        if (model != null)
        {
            value = getString(query, key, model, tagName);
        }
        return value;
    }

    private String getString(final ITaglibDomainMetaDataQuery query, final String key, final Model model,
            final String tagName)
    {
        String value = null;

        // use palette infos if available
        final Trait trait = query.findTrait(model,
                PALETTE_INFOS);
        if (trait != null)
        {
            final PaletteInfos tags = (PaletteInfos) trait.getValue();
            for (final Iterator it = tags.getInfos().iterator(); it.hasNext();)
            {
                final PaletteInfo tag = (PaletteInfo) it.next();
                if (tag.getId().equalsIgnoreCase(tagName))
                {
                    final EStructuralFeature feature = tag.eClass()
                            .getEStructuralFeature(key);
                    if (feature != null)
                    {
                        if (tag.eIsSet(feature))
                        {
                            final Object strValue = tag.eGet(feature);
                            if (strValue instanceof String)
                            {
                                value = (String) strValue;
                            }
                        }
                    }
                }
            }
        }
        return value;
    }

    private Model getModel(final ITaglibDomainMetaDataQuery query, final String nsUri)
    {
    	final Model model = query.findTagLibraryModel(nsUri);
//        final ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper
//                .createMetaDataModelContext(_project, nsUri);
//        final Model model = TaglibDomainMetaDataQueryHelper
//                .getModel(modelContext);
        // no caching at this time so there is no need to listen to model
        // notifications
        // if (model != null && !hasAdapter(model))
        // addAdapter(model);
    	
        return model;
    }
}
