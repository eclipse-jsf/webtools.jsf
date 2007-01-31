package org.eclipse.jst.pagedesigner.viewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;

public class DefaultDropLocationStrategy extends AbstractDropLocationStrategy 
{
    // the amount of vertical offset below the mouse pointer to place
    // the upper left of the drop hint tooltip
    private static final int DROP_HINT_VERTICAL_OFFSET = 20;

    public DefaultDropLocationStrategy(EditPart host) {
        super(host);
    }

    public DesignPosition calculateDesignPosition(EditPart host, Point p,
            IPositionMediator validator) {
        return EditPartPositionHelper.findEditPartPosition(
                host, p, validator);
    }

    public List showTargetFeedback(EditPart host, DesignPosition position, ChangeBoundsRequest request) 
    {
        
        List feedback = new ArrayList(4);
        feedback.add(showFeedbackRect(createCaretBounds(position)));
        feedback.add(showDropHintLabel(request.getLocation(), position));
        
        return feedback;
    }

    
    
    protected final RectangleFigure showFeedbackRect(Rectangle rect) {
        RectangleFigure pf = createFeedbackFigure();
        pf.translateToRelative(rect);
        pf.setBounds(rect);
        return pf;
    }
    
    protected Rectangle createCaretBounds(DesignPosition position)
    {
        Rectangle rect = EditPartPositionHelper
            .convertToAbsoluteCaretRect(position);

        // to avoid enlarge feedback pane.
        rect = rect.intersect(getFeedbackLayer().getBounds());
        
        return rect;
    }
    
    protected RectangleFigure createFeedbackFigure() 
    {
        RectangleFigure feedbackFigure = new RectangleFigure();
        feedbackFigure.setFill(true);
        feedbackFigure.setOutline(true);
        feedbackFigure.setLineWidth(1);
        feedbackFigure.setForegroundColor(ColorConstants.red);
        feedbackFigure.setBounds(new Rectangle(0, 0, 0, 0));
        feedbackFigure.setXOR(true);
        addFeedback(feedbackFigure);
        return feedbackFigure;
    }

    /**
     * Shows a label in a position relative to the drop marker
     * that hints where the new component will be dropped in
     * respect of components already there
     */
    protected final Label showDropHintLabel(Point mousePosition, DesignPosition position)
    {
        Label    dropHintLabel = new Label();
        dropHintLabel.setOpaque(true);
        dropHintLabel.setBackgroundColor(ColorConstants.tooltipBackground);
        dropHintLabel.setBorder(
                new LineBorder(ColorConstants.black, 1)
                {
                    // add an extra pixel of inset to make sure the text
                    // isn't pressed against the border
                    public Insets getInsets(IFigure figure) {
                        return new Insets(getWidth()+1);
                    }
                }
        );
        addFeedback(dropHintLabel);

        final String hintText = getDropHintText(position);
        dropHintLabel.setText(hintText);
        //TODO: need to handle viewport clipping and adjust label location appropriately
        Dimension hintSize = dropHintLabel.getPreferredSize();
        Point hintLocation = new Point(mousePosition.x, mousePosition.y+DROP_HINT_VERTICAL_OFFSET);
        
        Rectangle hintRect = new Rectangle(hintLocation, hintSize);

        // to avoid enlarge feedback pane.
        //hintRect = hintRect.intersect(getFeedbackLayer().getBounds());
        dropHintLabel.translateToRelative(hintRect);
        dropHintLabel.setBounds(hintRect);
        
        return dropHintLabel;
    }
    
    protected String getDropHintText(DesignPosition position)
    {
        StringBuffer buffer = new StringBuffer("Place");
        
        EditPart prevPart = position.getSiblingEditPart(false);
        EditPart nextPart = position.getSiblingEditPart(true);

        if (nextPart instanceof ElementEditPart)
        {
            buffer.append(" before ");
            buffer.append(((ElementEditPart)nextPart).getTagConvert().getHostElement().getNodeName());
            buffer.append(",");
        }
        
        if (prevPart instanceof ElementEditPart)
        {
            buffer.append(" after ");
            buffer.append(((ElementEditPart)prevPart).getTagConvert().getHostElement().getNodeName());
            buffer.append(",");
        }
        
        buffer.append(" inside ");
        buffer.append(position.getContainerNode().getNodeName());
        
        return buffer.toString();
    }
}
