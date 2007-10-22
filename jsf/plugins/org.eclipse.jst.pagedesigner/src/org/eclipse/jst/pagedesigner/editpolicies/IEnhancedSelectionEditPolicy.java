/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
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
     * @param mouseLocation 
     * @return the cursor that should be displayed when a selection tool
     * enters the hit box (mouses over) of an edit part but no selection
     * or drag operations have occurred yet.  Null indicates the caller should
     * use whatever default it deems appropriate.
     */
    Cursor getSelectionToolCursor(Point mouseLocation);
    
    /**
     * @param request 
     * @param mouseLocator
     * @return a drag tracker to be used when the mouse moves over a certain
     * location and mouse state is in initial state or null if the policy
     * doesn't wish to customize this. This allows an edit part
     * to customize what drag tracker is used depending on where the mouse is positioned
     * on its figure.
     */
    DragTracker getSelectionDragTracker(LocationRequest request);
}
