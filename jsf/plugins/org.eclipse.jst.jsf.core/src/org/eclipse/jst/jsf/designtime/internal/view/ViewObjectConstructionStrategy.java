package org.eclipse.jst.jsf.designtime.internal.view;

import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;

/**
 * A strategy for constructing a new view object from a tag element
 * @author cbateman
 * @param <VIEW_DEFN_BASE_TYPE> 
 *
 */
public abstract class ViewObjectConstructionStrategy<VIEW_DEFN_BASE_TYPE>
{
    /**
     * For a tag element return the corresponding view object or null if
     * there is no such object.
     * 
     * @param viewBase the source object in the source
     * @param tagElement
     * @return a new view object or null if we can't do so
     */
    public abstract ViewObject createViewObject(VIEW_DEFN_BASE_TYPE viewBase, ITagElement tagElement); 
}
