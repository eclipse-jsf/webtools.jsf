package org.eclipse.jst.pagedesigner.editors.palette;


/**
 * Provides generic information for tag creation from a palette drop.
 * 
 * @author cbateman
 * 
 */
public interface ITagDropSourceData extends IDropSourceData
{
    /**
     * @return tagName
     */
    public String getTagName();

    /**
     * Convenience method returning the tag libraries default prefix, if
     * applicable
     * 
     * @return default prefix
     */
    public String getDefaultPrefix();
}
