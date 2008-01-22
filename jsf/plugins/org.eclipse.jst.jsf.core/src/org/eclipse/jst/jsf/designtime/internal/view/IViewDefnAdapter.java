package org.eclipse.jst.jsf.designtime.internal.view;

import java.util.List;

import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;

/**
 * <p>A generic adapter used to adapt arbitrary view definitions to framework
 * objects.</p>
 *
 * @author cbateman
 * @param <VIEW_DEFN_BASE_TYPE> the base type of all view definition objects
 * @param <VIEW_CONTAINER_TYPE> the type of the container that is expeccted to hold the view defn
 *
 */
public interface IViewDefnAdapter<VIEW_DEFN_BASE_TYPE, VIEW_CONTAINER_TYPE>
{
    /**
     * @param viewDefnObject 
     * @param constructionData 
     * @param viewContainer 
     * @return a view object corresponding to the viewDefnBaseType object
     * or null if none.
     */
    ViewObject mapToViewObject(VIEW_DEFN_BASE_TYPE viewDefnObject, ViewObjectConstructionStrategy<? extends VIEW_DEFN_BASE_TYPE> constructionData, VIEW_CONTAINER_TYPE viewContainer);
    
    /**
     * @param viewDefnObject
     * @return the id for the viewDefnObject or null if none.  Generally, null
     * should indicate that no id is present but could be.  This is distinct from
     * the case where viewDefnObject can never define an id, in which case 
     * IllegalArgumentException should be thrown.
     * 
     * @throws IllegalArgumentException may be thrown to indicate that viewDefnObject
     * does not correspond to a ViewObject type that has an id.
     * 
     */
    String getId(VIEW_DEFN_BASE_TYPE viewDefnObject) throws IllegalArgumentException;
  
    /**
     * Normally this is a workspace resource (IFile) or higher level document
     * type like an IDocument.
     * 
     * @param context
     * @param viewId
     * @return the container resource for the viewId in context.
     */
    VIEW_CONTAINER_TYPE getContainer(DTFacesContext context, String viewId);
    
    /**
     * @param container
     * @return the view roots for the definition in container or empty if none.
     * MUST NOT BE NULL.
     */
    List<VIEW_DEFN_BASE_TYPE> getViewDefnRoots(VIEW_CONTAINER_TYPE container);
}
