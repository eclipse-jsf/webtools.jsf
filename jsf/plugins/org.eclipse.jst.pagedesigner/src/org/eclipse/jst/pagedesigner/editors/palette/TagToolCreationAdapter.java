package org.eclipse.jst.pagedesigner.editors.palette;


import javax.xml.namespace.QName;

import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;

/**
 * ITagDropSourceData implementation useable by TagToolPaletteEntry for its 
 * drop transfer object.
 * 
 * @author cbateman
 * 
 */
public final class TagToolCreationAdapter implements ITagDropSourceData
{
    private String _uri;
    private String _tagName;
    private String _defaultPrefix;
    private String _id;

    /**
     * @param uri 
     * @param tagName 
     * @param defaultPrefix 
     * @param id 
     */
    public TagToolCreationAdapter(final String uri,  final String tagName, final String defaultPrefix,
            final String id)
    {
        _uri = uri;
        _tagName = tagName;
        _defaultPrefix = defaultPrefix;
        _id = id;        
    }

    public String getDefaultPrefix()
    {
        return _defaultPrefix;
    }

    public String getTagName()
    {
        return _tagName;
    }

    public String getNamespace()
    {
        return _uri;
    }

    public String getId()
    {
        return _id;
    }

    /**
     * @param provider
     * @param model
     * @return a metadata tag creation adapter for an existing tag
     * creation provider (which need not itself be a TagToolCreationAdapter)
     * and a dom model.
     */
    public static MetadataTagDropSourceData createMdTagCreationProvider(final ITagDropSourceData provider, final IDOMModel model)
    {
        return new MetadataTagDropSourceData(provider, model);
    }

    /**
     * @param container
     * @param paletteContext 
     * @return the tag creation provider
     */
    public static ITagDropSourceData findProviderForContainer(
            final QName container, final IPaletteContext paletteContext)
    {
    	
        return findProviderForContainer(container.getNamespaceURI(), container
                .getLocalPart(), paletteContext);
    }

    /**
     * @param uri
     * @param tagName
     * @param paletteContext
     * @return the tag creation provider
     */
    public static ITagDropSourceData findProviderForContainer(
            final String uri, final String tagName,
            final IPaletteContext paletteContext)
    {
    	final PaletteItemManager itemManager = PaletteItemManager.getInstance(paletteContext);
        if (itemManager != null) {
	    	final TaglibPaletteDrawer category = itemManager.findCategoryByURI(uri);
	        if (category != null)
	        {
	            final TagToolPaletteEntry tagItem = category
	                    .getTagPaletteEntryByTagName(tagName);
	            if (tagItem != null)
	            {
	                return tagItem.getTemplate();
	            }
	        }
        }
        return null;
    }

}
