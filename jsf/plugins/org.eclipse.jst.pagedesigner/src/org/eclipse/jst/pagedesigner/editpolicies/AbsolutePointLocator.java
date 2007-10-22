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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author cbateman
 *
 */
public class AbsolutePointLocator implements Locator 
{
    private static AbsolutePointLocator   INSTANCE;
    private final static Point    DEFAULT_POINT = new Point(0,0);
    
    private Point  _referencePoint = DEFAULT_POINT; 
    private int    _xOffset = 0;
    private int    _yOffset = 0;
    private IFigure _intersectFigure;
    
    /**
     * @return the singleton instance
     */
    public synchronized static AbsolutePointLocator getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new AbsolutePointLocator();
        }
        return INSTANCE;
    }
    
    /** 
     * Relocates the target figure to the reference point with possible x and y
     * offsetting.  Uses the target's preferredSize as the new size.
     */
    public void relocate(IFigure target) 
    {
        Point leftTop = new Point(_referencePoint.x+_xOffset, _referencePoint.y+_yOffset);
        

        //figure.translateToAbsolute(leftTop);
        target.translateToRelative(leftTop);
        Dimension d = target.getPreferredSize();
        Rectangle rect = new Rectangle(leftTop, d);

        // to avoid enlargemeent
        if (_intersectFigure != null)
        {
            rect = rect.intersect(_intersectFigure.getBounds());
        }
        
        target.setBounds(rect);
    }

    /**
     * Sets the reference point used to calculate the location to which
     * relocate will relocate its target.  The x and y offset values are added
     * to the reference point before final re-location.  If point is null
     * then the reference is set to (0,0)
     * @param point
     * @param xoffset 
     * @param yoffset 
     */
    public void setReferencePoint(Point point, int xoffset, int yoffset)
    {
        if (point == null)
        {
            _referencePoint = DEFAULT_POINT;
        }
        else
        {
            _referencePoint = point;
        }
       
        _xOffset = xoffset;
        _yOffset = yoffset;
    }
    
    /**
     * Sets the figure used to calculate a rectangular intersect of the 
     * relocated target.  This normally set to the parent of the target
     * such as a layer to ensure that the relocate target does not enlarge
     * its parent by relocating outside it's rectangle.
     * 
     * If intersectFigure is set to null, then no intersect calculation will
     * be performed.
     * 
     * @param intersectFigure
     */
    public void setIntersectFigure(IFigure intersectFigure)
    {
        _intersectFigure = intersectFigure;
    }
    
    private AbsolutePointLocator() {/*no external instantiation*/}
}
