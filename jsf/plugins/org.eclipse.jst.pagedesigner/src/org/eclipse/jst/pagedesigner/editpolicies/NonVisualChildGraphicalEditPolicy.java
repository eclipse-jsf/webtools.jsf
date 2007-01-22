package org.eclipse.jst.pagedesigner.editpolicies;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.parts.NonVisualComponentEditPart;
import org.eclipse.swt.graphics.Color;
import org.w3c.dom.Element;

public class NonVisualChildGraphicalEditPolicy extends NonResizableEditPolicy 
{
    // the number of pixels to offset the top left of tooltop feedback
    // below the current mouse cursor location
    private static final int TOOLTIP_VERTICAL_OFFSET = 25;
    private final static Color HOVER_FEEDBACK_COLOR = ColorConstants.blue;
    
    
    protected BasicLabelToolTip   _toolTip;
    protected boolean             _showLabelFeedback;
    private RectangleFigure _mouseOverBorder;

    public void showTargetFeedback(Request request) {
        if (RequestConstants.REQ_SELECTION_HOVER.equals(request.getType())) {
            removeToolTipLabel();
            removeMouseOverBorder();
            Assert.isTrue(request instanceof LocationRequest);
            // don't show tooltip if drag is active
            _showLabelFeedback = !((NodeEditPart)getHost()).isDragActive();
            showHoverFeedback((LocationRequest)request);
        } else {
            super.showTargetFeedback(request);
        }

    }

    public void eraseTargetFeedback(Request request) {
        removeToolTipLabel();
        removeMouseOverBorder();
    }

    protected void showHoverFeedback(LocationRequest request)
    {
        if (_showLabelFeedback)
        {
            addMouseOverBorder();
            _toolTip = new BasicLabelToolTip(getTooltipText());
            addFeedback(_toolTip);

            // use mouse cursor plus an offset so the tooltip doesn't
            // appear z-ordered below the mouse cursor
            AbsolutePointLocator locator = AbsolutePointLocator.getInstance();
            locator.setReferencePoint(request.getLocation(), 0, TOOLTIP_VERTICAL_OFFSET);
            //  to avoid enlargemeent of the feedback layer
            locator.setIntersectFigure(getFeedbackLayer());
            locator.relocate(_toolTip);
        }
    }
    
    protected String getTooltipText() {
        Element element = (Element) ((NonVisualComponentEditPart)getHost()).getDOMNode();
        StringBuffer text = new StringBuffer(element.getTagName());
        return text.toString();
    }
    
    protected void addMouseOverBorder()
    {
        if (_mouseOverBorder == null)
        {
            _mouseOverBorder = new RectangleFigure();
            _mouseOverBorder.setFill(false);
            _mouseOverBorder.setOutline(true);
            _mouseOverBorder.setLineWidth(1);
            _mouseOverBorder.setForegroundColor(HOVER_FEEDBACK_COLOR);
        }
        
        addFeedback(_mouseOverBorder);
        
        IFigure hostFigure = getHostFigure();
        Rectangle r = hostFigure.getBounds().getCopy().expand(1, 1);
        hostFigure.translateToAbsolute(r);
        _mouseOverBorder.translateToRelative(r);

        _mouseOverBorder.setBounds(r);
    }
    
    protected void removeMouseOverBorder()
    {
        if (_mouseOverBorder != null) {
            removeFeedback(_mouseOverBorder);
            _mouseOverBorder = null;
        }
    }
    
    protected List createSelectionHandles() {
//        return super.createSelectionHandles();
        return Collections.EMPTY_LIST;  
    }

    protected void removeToolTipLabel()
    {
        if (_toolTip != null) {
            removeFeedback(_toolTip);
            _toolTip = null;
        }
    }

}
