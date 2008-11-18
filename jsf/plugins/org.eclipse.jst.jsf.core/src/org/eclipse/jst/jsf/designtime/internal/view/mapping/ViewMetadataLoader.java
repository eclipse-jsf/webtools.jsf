package org.eclipse.jst.jsf.designtime.internal.view.mapping;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping;

/**
 * Loader class for view metadata.
 * 
 * @author cbateman
 * 
 */
public class ViewMetadataLoader
{
    private final IProject                                       _project;
    private final Map<String, ITaglibDomainMetaDataModelContext> _metadataContexts;

    /**
     * @param project
     */
    public ViewMetadataLoader(final IProject project)
    {
        _project = project;
        _metadataContexts = new HashMap<String, ITaglibDomainMetaDataModelContext>();
    }

    /**
     * @param tagId
     * @return the tag to view object mapping metadata for a tag in a particular
     *         metadata context or null if not found.
     */
    public TagMapping getTagToViewMapping(final TagIdentifier tagId)
    {
        final ITaglibDomainMetaDataModelContext modelContext = createMetadataContext(tagId
                .getUri());
        final Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(
                modelContext, tagId.getTagName());
        if (entity != null)
        {
            final Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(
                    entity, ViewMetadataMapper.DEFAULT_MAPPING_TRAIT_ID);
            if (trait != null)
            {
                return (TagMapping) trait.getValue();
            }
        }
        return null;
    }

    private ITaglibDomainMetaDataModelContext createMetadataContext(
            final String uri)
    {
        ITaglibDomainMetaDataModelContext modelContext = _metadataContexts
                .get(uri);

        if (modelContext == null)
        {
            modelContext = TaglibDomainMetaDataQueryHelper
                    .createMetaDataModelContext(_project, uri);
            _metadataContexts.put(uri, modelContext);
        }
        return modelContext;
    }

    /**
     * @param tagId
     * @param name 
     * @return the attribute mapping for name on tagId or null if known
     */
    public AttributeToPropertyMapping getAttributeMapping(
            final TagIdentifier tagId, final String name)
    {
        final ITaglibDomainMetaDataModelContext modelContext = createMetadataContext(tagId
                .getUri());
        final Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(
                modelContext, tagId.getTagName()+"/"+name); //$NON-NLS-1$
        if (entity != null)
        {
            final Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(
                    entity, ViewMetadataMapper.DEFAULT_ATTRIBUTE_TRAIT_ID);
            if (trait != null)
            {
                return (AttributeToPropertyMapping) trait.getValue();
            }
        }
        return null;
    }
}
