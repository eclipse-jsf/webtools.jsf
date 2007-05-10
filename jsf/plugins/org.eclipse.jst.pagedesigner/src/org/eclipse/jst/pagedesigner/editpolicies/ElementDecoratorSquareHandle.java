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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.SquareHandle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;

public class ElementDecoratorSquareHandle extends SquareHandle {

    
    public ElementDecoratorSquareHandle(GraphicalEditPart owner, Locator loc,
            Cursor c) {
        super(owner, loc, c);
    }
    public ElementDecoratorSquareHandle(GraphicalEditPart owner, Locator loc) {
        super(owner, loc);
    }
    protected DragTracker createDragTracker() {
        return null;
    }
    protected Color getBorderColor() {
        return ColorConstants.black;
    }

    protected Color getFillColor() 
    {
        return ColorConstants.white;
    }

    protected int getAlpha()
    {
        return 255;
    }
    
    /**
     * Draws the handle with fill color and outline color dependent 
     * on the primary selection status of the owner editpart.
     *
     * @param g The graphics used to paint the figure.
     */
    public void paintFigure(Graphics g) {
        Rectangle r = getBounds();
        r.shrink(1, 1);
        try {
            g.setBackgroundColor(getFillColor());
            g.setAlpha(getAlpha());
            g.fillRectangle(r.x, r.y, r.width, r.height);
            g.setForegroundColor(getBorderColor()); 
            g.drawRectangle(r.x, r.y, r.width, r.height);
        } finally {
            //We don't really own rect 'r', so fix it.
            r.expand(1, 1);
        }
    }

}
