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
package org.eclipse.jst.pagedesigner.viewer;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;

/**
 * Abstract parent of all concrete client implmentation of IDropLocationStrategy's
 * Clients should sub-class instead of implementing {@link IDropLocationStrategy} directly
 * to avoid future API breakage
 * 
 * @author cbateman
 *
 */
public abstract class AbstractDropLocationStrategy  implements IDropLocationStrategy 
{
    private final EditPartViewer        _viewer;
    
    /**
     * @param viewer
     */
    public AbstractDropLocationStrategy(EditPartViewer viewer)
    {
        _viewer = viewer;
    }
    
    public abstract DesignPosition calculateDesignPosition(EditPart host, Point p,
            IPositionMediator validator);

    public abstract List showTargetFeedback(EditPart host, DesignPosition position, DropRequest request);

    /**
     * @return the viewer in which this strategy may show target feedback
     */
    protected final EditPartViewer getViewer() 
    {
        return _viewer;
    }
    
    /**
     * @return the figure for the feedback layer
     */
    protected final IFigure getFeedbackLayer()
    {
        return LayerManager.Helper.find(_viewer.getRootEditPart().getContents()).getLayer(LayerConstants.FEEDBACK_LAYER);
    }
    
    /**
     * Adds the specified <code>Figure</code> to the {@link LayerConstants#FEEDBACK_LAYER}.
     * @param figure the feedback to add
     */
    protected void addFeedback(IFigure figure) {
        getFeedbackLayer().add(figure);
    }
    
    /**
     * Removes the specified <code>Figure</code> to the {@link LayerConstants#FEEDBACK_LAYER}.
     * @param figure the feedback to remove
     */
    protected void removeFeedback(IFigure figure) {
        getFeedbackLayer().remove(figure);
    }
}
