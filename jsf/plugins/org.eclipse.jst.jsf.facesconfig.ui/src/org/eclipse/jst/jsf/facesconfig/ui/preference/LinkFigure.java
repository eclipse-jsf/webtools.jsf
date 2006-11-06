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
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class LinkFigure extends PolylineConnection implements IBaseFigure {
	public static Color defaultLineColor = ColorConstants.black;

	public static int defaultLineWidth = 1;

	public static boolean defaultLabelVisible = false;

	public static Color defaultLabelForeground = ColorConstants.black;

	public static Color defaultLabelBackground = ColorConstants.white;

	public static Font defaultFont = JFaceResources.getFontRegistry().get(
			JFaceResources.DEFAULT_FONT);

	private LinkLabel label;

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
	public void setToolTipText(String text) {
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
			return ((Label) getToolTip()).getText();
		return null;
	}

	public void setHighlight(boolean flag) {
		if (flag) {
			setForegroundColor(ColorConstants.titleBackground);
			// setLineWidth(defaultLineWidth * 2);
		} else {
			setForegroundColor(defaultLineColor);
			// setLineWidth(defaultLineWidth);
		}
	}

	public void setSelected(boolean flag) {
		if (flag) {
			// setForegroundColor(ColorConstants.titleBackground);
			setLineWidth(defaultLineWidth * 2);
		} else {
			// setForegroundColor(defaultLineColor);
			setLineWidth(defaultLineWidth);
		}
	}

	public void setLabelVisible(boolean flag) {
		getLabel().setVisible(flag);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#setText()
	 */
	public void setText(String text) {
		if (text == null && text.length() == 0) {
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
			return null;
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
	public void setIcon(Image image) {
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
	public void addDecorator(BaseFigureDecorator decorator) {
		int pos = decorator.getPosition();
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
	public void removeDecorator(int position) {
		if (position == BaseFigureDecorator.DEFAULT_DECORATOR_POSITION)
			setTargetDecoration(null);
		else
			setSourceDecoration(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getDecorators()
	 */
	public List getDecorators() {
		List list = new ArrayList(2);
		Object d;
		d = getSourceDecoration();
		if (d != null)
			list.add(d);
		d = getTargetDecoration();
		if (d != null)
			list.add(d);
		return list;
	}

	public LinkLabel getLabel() {
		if (label == null) {
			label = new LinkLabel();
			add(label, new MidpointLocator(this, 0));
		}
		return label;
	}

	public void setLabelForeground(Color c) {
		getLabel().setForegroundColor(c);
	}

	public void setLabelBackground(Color c) {
		getLabel().setBackgroundColor(c);
	}

	public void setFont(Font f) {
		getLabel().setFont(f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Shape#setLineWidth(int)
	 */
	public void setLineWidth(int w) {
		getLabel().setBorderWidth(w);
		super.setLineWidth(w);
	}

	public void setForegroundColor(Color c) {
		getLabel().setBorderColor(c);
		super.setForegroundColor(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Polyline#setPoints(org.eclipse.draw2d.geometry.PointList)
	 */
	public void setPoints(PointList points) {
		// TODO Auto-generated method stub
		super.setPoints(points);
		if (label != null) {
			int i = getPoints().size() / 2 - 1;
			if (i < 0)
				i = 0;
			if (getLayoutManager() != null)
				getLayoutManager().setConstraint(label,
						new MidpointLocator(this, i));
		}
	}

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
					IFigure child = (IFigure) getChildren().get(i);
					bounds.union(child.getBounds());
				}
			}
		}
		return bounds;
	}

	/*
	 * Helper class for line labels.
	 */
	public class LinkLabel extends Label {
		public LinkLabel() {
			setBorder(new LineBorder());
			setOpaque(true);
			setForegroundColor(defaultLabelForeground);
			setBackgroundColor(defaultLabelBackground);
			setFont(defaultFont);
			LineBorder border_ = new LineBorder(defaultLineWidth);
			setBorder(border_);
			border_.setColor(defaultLineColor);
			setVisible(defaultLabelVisible);
		}

		public void setBorderWidth(int w) {
			((LineBorder) getBorder()).setWidth(w);
		}

		public void setBorderColor(Color c) {
			((LineBorder) getBorder()).setColor(c);
		}

		protected boolean useLocalCoordinates() {
			return false;
		}
	}
}