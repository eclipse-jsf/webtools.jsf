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
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.RelativeHandleLocator;

/**
 * A locator for handles that attach to the corner of square figures
 * @author cbateman
 *
 */
class CornerRelativeHandleLocator extends RelativeHandleLocator
{
    private double relativeX = 0;
    private double relativeY = 0;
    private double offsetXMultiplier = 0;
    private double offsetYMultiplier = 0;
    
    /**
     * @param reference
     * @param location
     */
    public CornerRelativeHandleLocator(IFigure reference, int location) {
        super(reference, location);
        
        // unfortunately, relativeX and relativeY are private in super
        // so duplicate calc code here
        switch (location & PositionConstants.NORTH_SOUTH) 
        {
            case PositionConstants.NORTH:
                relativeY = 0; 
                offsetYMultiplier = -1.0;
            break;

            case PositionConstants.SOUTH:
                relativeY = 1.0; 
                offsetYMultiplier = 0;
            break;
            default:
                relativeY = 0.5;
        }

        switch (location & PositionConstants.EAST_WEST) 
        {
            case PositionConstants.WEST:
                relativeX = 0; 
                offsetXMultiplier = -1.0;
            break;
            case PositionConstants.EAST:
                relativeX = 1.0;
                offsetXMultiplier = 0;                    
            break;
            default:
                relativeX = 0.5;
        }
    }

    /**
     * Pass in targetBounds to be updated and newTargetSize.  targetBounds will
     * be applied to the figure that is being relocated immediately after this method
     * @param targetBounds
     * @param newTargetSize
     */
    protected void relocateBounds(Rectangle targetBounds, Dimension newTargetSize)
    {
        // copied from super.relocate because relativeX/Y are private in super
        // changed from super to remove div by 2 that centers target; we want
        // it to be corner-to-corner
        targetBounds.x
            += (int) (((targetBounds.width+2) * relativeX) + ((newTargetSize.width+1)*offsetXMultiplier));
        targetBounds.y
            += (int) (targetBounds.height * relativeY + ((newTargetSize.height+1)*offsetYMultiplier));

        targetBounds.setSize(newTargetSize);
    }

    /**
     * @param relocateFigure
     * @return a modifiable Rectangle that represents the bounds of the figure to be relocated
     */
    protected Rectangle getCurrentTargetBounds(IFigure relocateFigure)
    {
        IFigure reference = getReferenceFigure();
        Rectangle targetBounds = new PrecisionRectangle(getReferenceBox().getResized(-1, -1));
        reference.translateToAbsolute(targetBounds);
        relocateFigure.translateToRelative(targetBounds);
        return targetBounds;
    }
    
    /**
     * @param relocateFigure
     * @return the new target dimensions
     */
    protected Dimension getNewTargetSize(IFigure relocateFigure)
    {
        return relocateFigure.getPreferredSize();
    }
    
    public void relocate(IFigure target) {
        Rectangle targetBounds = getCurrentTargetBounds(target);
        Dimension targetSize = getNewTargetSize(target);
        relocateBounds(targetBounds, targetSize);

        target.setBounds(targetBounds);        
    }
}