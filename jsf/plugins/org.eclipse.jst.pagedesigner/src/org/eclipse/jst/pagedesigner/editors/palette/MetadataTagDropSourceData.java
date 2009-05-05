/**
 * 
 */
package org.eclipse.jst.pagedesigner.editors.palette;

import java.util.List;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfos;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationAttribute;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationInfo;
import org.eclipse.jst.pagedesigner.utils.CommandUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;

/**
 * A source data that is enhanced by metadata.
 * 
 * @author cbateman
 *
 */
public final class MetadataTagDropSourceData implements ITagDropSourceData
{
    private final IDOMModel _model;
    private final TagCreationInfo _tagCreationInfo;
    private final ITagDropSourceData _delegate;

    /**
     * @param creationProvider
     * @param model
     */
    public MetadataTagDropSourceData(
            final ITagDropSourceData creationProvider,
            final IDOMModel model)
    {
        _delegate = creationProvider;
        _model = model;
        _tagCreationInfo = getTagCreationInfo();
    }

    /**
     * @return the pre-defined attributes for the tag from meta-data or
     *  null if none.
     */
    public List<TagCreationAttribute> getAttributes()
    {
        if (_tagCreationInfo != null)
        {
            return _tagCreationInfo.getAttributes();
        }
        return null;
    }

    /**
     * @return the template derived from metadata for tag drop or null if none.
     */
    public String getTemplate()
    {
        if (_tagCreationInfo != null)
        {
            return (String) _tagCreationInfo.getTemplate();
        }
        return null;
    }

    public String getDefaultPrefix()
    {
        return _delegate.getDefaultPrefix();
    }

    public String getTagName()
    {
        return _delegate.getTagName();
    }

    public String getNamespace()
    {
        return _delegate.getNamespace();
    }

    private TagCreationInfo getTagCreationInfo()
    {
        final ITaglibDomainMetaDataModelContext metadataContext = CommandUtil
                .getMetadataContext(getNamespace(), _model);
        TagCreationInfo tagCreationInfo = null;
        String id = getId();
        if (metadataContext != null && id != null)
        {
            tagCreationInfo = createCreationInfo(metadataContext, id,
                    getTagName());
        }
        return tagCreationInfo;
    }

    public String getId()
    {
        return _delegate.getId();
    }
    
    /**
     * @param metaDataContext
     * @param id
     * @param tagName
     * @return a tag creation info for the tag and id in the metadata context
     */
    static TagCreationInfo createCreationInfo(
            final ITaglibDomainMetaDataModelContext metaDataContext,
            final String id, final String tagName)
    {
        final Model model = TaglibDomainMetaDataQueryHelper
                .getModel(metaDataContext);
        if (model != null)
        {
            Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(model,
                    PaletteInfos.TRAIT_ID);
            if (trait != null)
            {
                final PaletteInfos pis = (PaletteInfos) trait.getValue();
                final PaletteInfo pi = pis.findPaletteInfoById(id);
                if (pi != null)
                {
                    return pi.getTagCreation();
                }
            }
            // tag-creation trait on entity directly?
            final Entity tag = getTagEntity(metaDataContext, tagName);
            if (tag != null)
            {// metadata exists
                trait = TaglibDomainMetaDataQueryHelper.getTrait(tag,
                        "tag-create"); //$NON-NLS-1$
                if (trait != null && trait.getValue() != null)
                {
                    return (TagCreationInfo) trait.getValue();
                }
            }
        }
        return null;
    }
    /**
     * @param metaDataContext
     * @param tagName
     * @return the {@link Entity} for this tag element being created
     */
    private static Entity getTagEntity(
            final ITaglibDomainMetaDataModelContext metaDataContext,
            final String tagName)
    {
        return TaglibDomainMetaDataQueryHelper.getEntity(metaDataContext,
                tagName);
    }
}