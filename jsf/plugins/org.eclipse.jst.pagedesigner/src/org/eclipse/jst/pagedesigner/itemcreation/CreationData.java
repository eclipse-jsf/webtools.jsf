package org.eclipse.jst.pagedesigner.itemcreation;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfos;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationInfo;
import org.eclipse.jst.pagedesigner.utils.JSPUtil;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMDocType;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;

/**
 * Value object that wraps creation data
 *  
 * <p><b>Provisional API - subject to change</b></p>
 *  
 * @author cbateman
 *
 */
public final class CreationData
{
    private final TagToolPaletteEntry   _tagEntry;
    private final String                _prefix; 
    private final IDOMPosition          _domPosition;
    private final IDOMModel             _model;
    private final IAdaptable            _customizationData;
    
    private TagIdentifier               _tagId; // = null; lazy init on creation 
    
    /**
     * The tag {@link Entity} being created
     */
    private Entity _tagEntity; // = null; lazy load because derived from
                               // potentially expensive meta-data search
    
    /**
     * The {@link ITaglibDomainMetaDataModelContext} for the tag creation
     */
    private final ITaglibDomainMetaDataModelContext _taglibMetaDataContext;

    // mutable because it may be changed from the original _domPosition
    private IDOMPosition _adjustedPosition;

    
    /**
     * @param tagEntry  TODO: remove this direct dependence on the palette
     * @param model 
     * @param domPosition 
     * @param taglibMetaDataContext 
     * @param customizationData 
     */
    public CreationData(final TagToolPaletteEntry tagEntry, final IDOMModel model, final IDOMPosition domPosition, final ITaglibDomainMetaDataModelContext taglibMetaDataContext, final IAdaptable customizationData) 
    {
        super();
        this._tagEntry = tagEntry;
        this._prefix = getPrefix(getUri(), model, getDefaultPrefix());
        this._taglibMetaDataContext = taglibMetaDataContext;
        this._domPosition = domPosition;
        this._adjustedPosition = _domPosition;
        this._model = model;
        this._customizationData = customizationData; 
    }

    /**
     * Returns the ns prefix for the tag and also creates taglib reference if necessary
     * @param uri
     * @param model
     * @param suggested
     * @return prefix to use
     */
    protected static String getPrefix(String uri, IDOMModel model,
            String suggested) {
        // TODO: this shouldn't really add to the document
        if (ITLDConstants.URI_HTML.equalsIgnoreCase(uri)
                || ITLDConstants.URI_JSP.equalsIgnoreCase(uri)
                || CMDocType.JSP11_DOC_TYPE.equalsIgnoreCase(uri)) {
            return null;
        }

        // now handles custom tag lib
        return JSPUtil.getOrCreatePrefix(model, uri, suggested);
    }
    
    /**
     * @return the tag identifier uri
     */
    public String getUri() {
        return _tagEntry.getURI();
    }

    /**
     * @return the default prefix
     */
    public String getDefaultPrefix() {
        return _tagEntry.getDefaultPrefix();
    }

    /**
     * @return the local prefix for the  tag
     */
    public String getPrefix() {
        return _prefix;
    }

    /**
     * @return the tag name
     */
    public String getTagName() {
        return _tagEntry.getTagName();
    }

    /**
     * @return the id
     */
    private String getItemId() {
        return _tagEntry.getId();
    }

    /**
     * @return the palette entry that this creation info is based on
     */
    public TagToolPaletteEntry getTagEntry() {
        return _tagEntry;
    }
    
    /**
     * @return {@link TagCreationInfo} for the tag entity
     */
    public TagCreationInfo getTagCreationInfo(){
        Model model = TaglibDomainMetaDataQueryHelper.getModel(_taglibMetaDataContext);
        if (model != null){
            Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(model, PaletteInfos.TRAIT_ID);
            if (trait != null){
                PaletteInfos pis = (PaletteInfos)trait.getValue();
                PaletteInfo pi = pis.findPaletteInfoById(getItemId());
                if (pi != null){
                    return pi.getTagCreation();                 
                }
            }
            //tag-creation trait on entity directly?
            Entity tag = getTagEntity();
            if (tag != null){//metadata exists
                trait = TaglibDomainMetaDataQueryHelper.getTrait(tag, "tag-create");
                if (trait != null && trait.getValue() != null){
                    return (TagCreationInfo)trait.getValue();                   
                }
            }
        }
        return null;
    }

    
    /**
     * @return the {@link Entity} for this tag element being created
     */
    public Entity getTagEntity() {
        if (_tagEntity == null){
            _tagEntity = TaglibDomainMetaDataQueryHelper.getEntity(_taglibMetaDataContext, getTagName());
            
        }
        return _tagEntity;
    }
    
    /**
     * @return flag indicating that html form container ancestor is required
     */
    public boolean isHTMLFormRequired() {
        Trait t = TaglibDomainMetaDataQueryHelper.getTrait(getTagEntity(), "requires-html-form");
        if (t != null)
            return TraitValueHelper.getValueAsBoolean(t);
        
        return false;
    }
    
    /**
     * @return flag indicating that jsf component
     */
    public boolean isJSFComponent() {      
        Model model = TaglibDomainMetaDataQueryHelper.getModel(_taglibMetaDataContext);
        Trait t = TaglibDomainMetaDataQueryHelper.getTrait(model, "is-jsf-component-library");
        if (t != null)
            return TraitValueHelper.getValueAsBoolean(t);
        
        return false;
    }

    /**
     * @return the metadata context for the tag
     */
    public ITaglibDomainMetaDataModelContext getTaglibMetaDataContext() {
        return _taglibMetaDataContext;
    }

    /**
     * @return the original dom position of the tag creation
     */
    public IDOMPosition getDomPosition() {
        return _domPosition;
    }

    /**
     * @return the dom model
     */
    public IDOMModel getModel() 
    {
        return _model;
    }

    /**
     * @return the adjusted position (calculated to account for containers etc.)
     */
    public IDOMPosition getAdjustedPosition() {
        return _adjustedPosition;
    }

    /**
     * @param adjustedPosition
     */
    /*package*/ void setAdjustedPosition(IDOMPosition adjustedPosition) {
        _adjustedPosition = adjustedPosition;
    }

    /**
     * @return the TagIdentifer for the tag to be created
     */
    public TagIdentifier getTagId() 
    {
        if (_tagId == null)
        {
            _tagId = TagIdentifierFactory.createJSPTagWrapper(getUri(), getTagName());
        }
        return _tagId;
    }

    /**
     * @return the customization data passed in from the tool.  May be null.
     */
    public IAdaptable getDropCustomizationData() {
        return _customizationData;
    }
}