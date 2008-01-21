/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.preference;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * @author Bob
 */
/*package*/ class LinkFigure extends PolylineConnection implements IBaseFigure {
    private final static Color defaultLineColor = ColorConstants.black;

    private final static int defaultLineWidth = 1;

    private final static boolean defaultLabelVisible = false;

    private final static Color defaultLabelForeground = ColorConstants.black;

    private final static Color defaultLabelBackground = ColorConstants.white;

    private final static Font defaultFont = JFaceResources.getFontRegistry().get(
            JFaceResources.DEFAULT_FONT);

    private LinkLabel label;

    /**
     * Default constructor
     */
    public LinkFigure() {
        super();
        setLineWidth(defaultLineWidth);
        setForegroundColor(defaultLineColor);

        // BaseFigureDecorator decorator;
        // decorator = new
        // BaseFigureDecorator(GEMPlugin.getDefault().getGEMImage("delete.gif"),"target",PositionConstants.WEST);
        // addDecorator(decorator);
        // decorator = new
        // BaseFigureDecorator(GEMPlugin.getDefault().getGEMImage("delete.gif"),"target",PositionConstants.EAST);
        // addDecorator(decorator);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#setToolTipText()
     */
    public void setToolTipText(final String text) {
        Label toolTip_ = null;

        if (text != null && text.length() > 0) {
            toolTip_ = new Label(text);
            toolTip_.setBorder(new MarginBorder(3));
        }

        super.setToolTip(toolTip_);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getToolTipText()
     */
    public String getToolTipText() {
        if (getToolTip() != null)
        {
            return ((Label) getToolTip()).getText();
        }
        return null;
    }

    public void setHighlight(final boolean flag) {
        if (flag) {
            setForegroundColor(ColorConstants.titleBackground);
            // setLineWidth(defaultLineWidth * 2);
        } else {
            setForegroundColor(defaultLineColor);
            // setLineWidth(defaultLineWidth);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#setText()
     */
    public void setText(final String text) {
        if (text != null && text.length() == 0) {
            if (label != null) {
                remove(label);
                label = null;
            }
        } else {
            getLabel().setText(text);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getText()
     */
    public String getText() {
        if (label == null)
        {
            return null;
        }
        return label.getText();
    }

    /*
     * CR374981: Long activity labels do not get wrapped or truncated This
     * method was added to the IBaseFigure interface to support direct edit of
     * figure labels on the canvas.
     */
    public Rectangle getTextBounds() {
        return label.getTextBounds();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#setIcon(org.eclipse.swt.graphics.Image)
     */
    public void setIcon(final Image image) {
        getLabel().setIcon(image);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getIcon()
     */
    public Image getIcon() {
        return getLabel().getIcon();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#addDecorator(com.sybase.stf.gem.diagram.editor.figures.BaseFigureDecorator)
     */
    public void addDecorator(final BaseFigureDecorator decorator) {
        final int pos = decorator.getPosition();
        if (pos == BaseFigureDecorator.DEFAULT_DECORATOR_POSITION
                || pos == PositionConstants.WEST
                || pos == PositionConstants.LEFT) {
            setTargetDecoration(decorator);
        } else if (pos == PositionConstants.EAST
                || pos == PositionConstants.RIGHT) {
            setSourceDecoration(decorator);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#removeDecorator()
     */
    public void removeDecorator() {
        removeDecorator(BaseFigureDecorator.DEFAULT_DECORATOR_POSITION);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#removeDecorator(int)
     */
    public void removeDecorator(final int position) {
        if (position == BaseFigureDecorator.DEFAULT_DECORATOR_POSITION)
        {
            setTargetDecoration(null);
        }
        else
        {
            setSourceDecoration(null);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getDecorators()
     */
    public List getDecorators() {
        final List list = new ArrayList(2);
        Object d;
        d = getSourceDecoration();
        if (d != null)
        {
            list.add(d);
        }
        d = getTargetDecoration();
        if (d != null)
        {
            list.add(d);
        }
        return list;
    }

    private LinkLabel getLabel() {
        if (label == null) {
            label = new LinkLabel();
            add(label, new MidpointLocator(this, 0));
        }
        return label;
    }

    @Override
    public void setFont(final Font f) {
        getLabel().setFont(f);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.Shape#setLineWidth(int)
     */
    @Override
    public void setLineWidth(final int w) {
        getLabel().setBorderWidth(w);
        super.setLineWidth(w);
    }

    @Override
    public void setForegroundColor(final Color c) {
        getLabel().setBorderColor(c);
        super.setForegroundColor(c);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.Polyline#setPoints(org.eclipse.draw2d.geometry.PointList)
     */
    @Override
    public void setPoints(final PointList points) {
        super.setPoints(points);
        if (label != null) {
            int i = getPoints().size() / 2 - 1;
            if (i < 0)
            {
                i = 0;
            }
            if (getLayoutManager() != null)
            {
                getLayoutManager().setConstraint(label,
                        new MidpointLocator(this, i));
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        if (bounds == null) {
            if (bounds == null) {
                bounds = getPoints().getBounds().getExpanded(lineWidth / 2,
                        lineWidth / 2);
            }
            // CR382243: Clicking on the node icon selects the connection line
            // rather than the node
            // NOTE: expanding the bounds to include children (in this case,
            // the Link labels) causes problems when the connection line becomes
            // short (i.e., when the 2 anchor figures at each end of the
            // connection are
            // close together) because the bounds of this link figure will be
            // greater
            // than the actual length of the line. This causes the link figure
            // to
            // be hit-tested beyond its visual size.
            if (getLabel().isVisible()) {
                for (int i = 0; i < getChildren().size(); i++) {
                    final IFigure child = (IFigure) getChildren().get(i);
                    bounds.union(child.getBounds());
                }
            }
        }
        return bounds;
    }

    /*
     * Helper class for line labels.
     */
    private static class LinkLabel extends Label {
        LinkLabel() {
            setBorder(new LineBorder());
            setOpaque(true);
            setForegroundColor(defaultLabelForeground);
            setBackgroundColor(defaultLabelBackground);
            setFont(defaultFont);
            final LineBorder border_ = new LineBorder(defaultLineWidth);
            setBorder(border_);
            border_.setColor(defaultLineColor);
            setVisible(defaultLabelVisible);
        }

        void setBorderWidth(final int w) {
            ((LineBorder) getBorder()).setWidth(w);
        }

        void setBorderColor(final Color c) {
            ((LineBorder) getBorder()).setColor(c);
        }

        @Override
        protected boolean useLocalCoordinates() {
            return false;
        }
    }
}