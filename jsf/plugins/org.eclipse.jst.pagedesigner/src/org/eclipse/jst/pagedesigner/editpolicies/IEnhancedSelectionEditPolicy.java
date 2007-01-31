package org.eclipse.jst.pagedesigner.editpolicies;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.swt.graphics.Cursor;

/**
 * Allows a selection edit policy to specify extra capabilities
 * 
 * @author cbateman
 */
public interface IEnhancedSelectionEditPolicy 
{
    /**
     * @return the cursor that should be displayed when a selection tool
     * enters the hit box (mouses over) of an edit part but no selection
     * or drag operations have occurred yet.  Null indicates the caller should
     * use whatever default it deems appropriate.
     */
    Cursor getSelectionToolCursor(Point mouseLocation);
    
    /**
     * @param mouseLocator
     * @return a drag tracker to be used when the mouse moves over a certain
     * location and mouse state is in initial state or null if the policy
     * doesn't wish to customize this. This allows an edit part
     * to customize what drag tracker is used depending on where the mouse is positioned
     * on its figure.
     */
    DragTracker getSelectionDragTracker(LocationRequest request);
}
