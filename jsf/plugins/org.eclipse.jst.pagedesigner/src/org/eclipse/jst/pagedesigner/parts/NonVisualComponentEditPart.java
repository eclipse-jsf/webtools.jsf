/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.parts;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.editpolicies.NonVisualChildGraphicalEditPolicy;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Represents a node that is non-visual in the runtime rendering
 * but which may wish to have a meta-representation on the design canvas.
 *
 */
public class NonVisualComponentEditPart extends NodeEditPart 
{
    protected IFigure createFigure() 
    {
        IFigure figure_ = new ImageFigure(getTagConverter().getVisualImage())
        {

            protected void paintFigure(Graphics graphics) {
                super.paintFigure(graphics);
                
                if (getImage() == null)
                    return;

                Rectangle srcRect = new Rectangle(getImage().getBounds());
                graphics.drawImage(getImage(), srcRect, getClientArea());
            }
            
        };
        
        figure_.setMinimumSize(new Dimension(0,0));
        return figure_;
    }

    public void notifyChanged(INodeNotifier notifier, int eventType,
            Object changedFeature, Object oldValue, Object newValue, int pos) {
        // for now, do nothing
    }

    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE
                          , new NonVisualChildGraphicalEditPolicy());
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
                null);
    }

    @Override
    public void deactivate()
    {
        ITagConverter tagConverter = (ITagConverter) getModel();
        if (tagConverter != null)
        {
            tagConverter.dispose();
        }
        
        // always do super stuff
        super.deactivate();
    }

    /**
     * @return the tag converter
     */
    protected ITagConverter getTagConverter()
    {
    	ITagConverter tagConverter = (ITagConverter)getModel();
    	//need to call convertRefresh to get image (if any) from metadata
    	if (tagConverter != null) {
    		tagConverter.convertRefresh(null);
    	}
    	return tagConverter;
    }
    
    /**
     * @return the host element for this edit part
     */
    protected Element getModelElement()
    {
        return getTagConverter().getHostElement();
    }

    public IDOMNode getIDOMNode() 
    {
        return (IDOMNode) getModelElement();
    }

    public Node getDOMNode() {
        return getModelElement();
    }

//    public DragTracker getDragTracker(Request request) {
//        // TODO: need to define drag semantics for these
//        // Also, right now edit part dragging causes bad behaviour
//        // in the non-visual decorator
//        return null;//new ObjectModeDragTracker(this);
//    }
}
