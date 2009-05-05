package org.eclipse.jst.pagedesigner.editors.palette;

/**
 * Used by objects (usually palette entries) that wish provide drop source data
 * to a client.
 * 
 * @author cbateman
 *
 */
public interface IDropSourceDataProvider
{
    /**
     * @return a drop source data in context
     */
    IDropSourceData  getDropSourceData();
}
