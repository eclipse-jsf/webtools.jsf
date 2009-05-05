package org.eclipse.jst.pagedesigner.editors.palette;

/**
 * Data describing a palette drop.
 * 
 * @author cbateman
 *
 */
public interface IDropSourceData
{
    /**
     * @return uri of the tag's library. MUST NOT BE NULL.
     */
    public String getNamespace();
    /**
     * @return a unique id for a tag creation provider that may be different
     * from the uri. MUST NOT BE NULL.
     */
    public String getId();
}
