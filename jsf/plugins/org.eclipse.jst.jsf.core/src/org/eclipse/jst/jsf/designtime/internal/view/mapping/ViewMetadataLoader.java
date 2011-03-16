package org.eclipse.jst.jsf.designtime.internal.view.mapping;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
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
    private final IProject                                      _project;
    private final IMetaDataQuery			 					_query;

    /**
     * @param project
     */
    public ViewMetadataLoader(final IProject project)
    {
        _project = project;
    	final  IMetaDataDomainContext modelContext = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(_project);
		_query = MetaDataQueryFactory.getInstance().createQuery(modelContext);
		
    }

    /**
     * @param tagId
     * @return the tag to view object mapping metadata for a tag in a particular
     *         metadata context or null if not found.
     */
    public TagMapping getTagToViewMapping(final TagIdentifier tagId)
    {

		final Entity entity = _query.getQueryHelper().getEntity(tagId.getUri(), tagId.getTagName());
//        final ITaglibDomainMetaDataModelContext modelContext = createMetadataContext(tagId
//                .getUri());
//        final Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(
//                modelContext, tagId.getTagName());
        if (entity != null)
        {
            final Trait trait = _query.getQueryHelper().getTrait(
                    entity, ViewMetadataMapper.DEFAULT_MAPPING_TRAIT_ID);
            if (trait != null)
            {
                return (TagMapping) trait.getValue();
            }
        }
        return null;
    }


    /**
     * @param tagId
     * @param name 
     * @return the attribute mapping for name on tagId or null if known
     */
    public AttributeToPropertyMapping getAttributeMapping(
            final TagIdentifier tagId, final String name)
    {

        final Entity entity = _query.getQueryHelper().getEntity(
                tagId.getUri(), tagId.getTagName()+"/"+name); //$NON-NLS-1$
        if (entity != null)
        {
            final Trait trait = _query.getQueryHelper().getTrait(
                    entity, ViewMetadataMapper.DEFAULT_ATTRIBUTE_TRAIT_ID);
            if (trait != null)
            {
                return (AttributeToPropertyMapping) trait.getValue();
            }
        }
        return null;
    }
}
