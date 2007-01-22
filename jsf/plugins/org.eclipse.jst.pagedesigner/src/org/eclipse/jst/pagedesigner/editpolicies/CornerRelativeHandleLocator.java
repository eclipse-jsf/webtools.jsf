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

class CornerRelativeHandleLocator extends RelativeHandleLocator
{
    protected double relativeX = 0;
    protected double relativeY = 0;
    protected double offsetXMultiplier = 0;
    protected double offsetYMultiplier = 0;
    
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

    public void relocate(IFigure target) {
        IFigure reference = getReferenceFigure();
        Rectangle targetBounds = new PrecisionRectangle(getReferenceBox().getResized(-1, -1));
        reference.translateToAbsolute(targetBounds);
        target.translateToRelative(targetBounds);
        //targetBounds.resize(1, 1);

        Dimension targetSize = target.getPreferredSize();

        // copied from super.relocate because relativeX/Y are private in super
        // changed from super to remove div by 2 that centers target; we want
        // it to be corner-to-corner
        targetBounds.x
            += (int) ((targetBounds.width+2 * relativeX) + ((targetSize.width+1)*offsetXMultiplier));
        targetBounds.y
            += (int) (targetBounds.height * relativeY + ((targetSize.height+1)*offsetYMultiplier));
        targetBounds.setSize(targetSize);
        target.setBounds(targetBounds);        
    }
}