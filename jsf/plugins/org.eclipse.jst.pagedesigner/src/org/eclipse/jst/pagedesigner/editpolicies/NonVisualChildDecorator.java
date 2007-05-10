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
/**
 * 
 */
package org.eclipse.jst.pagedesigner.editpolicies;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;

class NonVisualChildDecorator extends ElementDecoratorSquareHandle
{
   
    NonVisualChildDecorator(final GraphicalEditPart hostPart, int location)
    {
        super(hostPart, new CornerRelativeHandleLocator(hostPart.getFigure(), location));
    }

    protected void init() {
        setPreferredSize(new Dimension(8, 8));
    }
    
    public void ancestorMoved(IFigure ancestor) {
        // TODO: for some reason this causes an infinite loop
        // when in the feedback layer...
        //super.ancestorMoved(ancestor);
    }

    protected DragTracker createDragTracker() {
        return null;
    }
    
    protected int getAlpha()
    {
        return 255;
    }

}