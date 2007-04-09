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
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.AncestorListener;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * And IconFigure consists of a bitmap image with a text label below it.
 * 
 * @author bbrodt
 */
public class IconFigure extends Label implements IBaseFigure {
	public static int defaultTextPlacement = PositionConstants.SOUTH;

	public static Font defaultFont = JFaceResources.getFontRegistry().get(
			JFaceResources.DEFAULT_FONT);

	public static Color defaultForegroundColor = ColorConstants.black;

	private ArrayList decorators;

	private IconLabel iconLabel = null;

	// CR389070: Figures are abbreviating rule figures names and making them
	// unreadable
	// New class that implements a separate "floating" label which is NOT
	// included in bounds calculations for this IconFigure
	private class IconLabel extends Label {
		private LabelLocator locator;

		private IconFigure host;

		public IconLabel(IconFigure host, String text) {
			super(text);
			this.host = host;
			locator = new LabelLocator(this);
		}

		public IconFigure getHost() {
			return host;
		}

		public Dimension getPreferredSize(int wHint, int hHing) {
			return host.getTextBounds().getSize();
		}

		public void setText(String text) {
			super.setText(text);
			if (locator != null)
				locator.setConstraints();
		}

		public void invalidate() {
			if (locator != null)
				locator.setConstraints();
			super.invalidate();
		}
	}

	// CR389070: Figures are abbreviating rule figures names and making them
	// unreadable
	// helper class that relocates the IconLabel whenever ancestor is moved or
	// added/removed
	private class LabelLocator implements AncestorListener {
		IconLabel label;

		public LabelLocator(IconLabel label) {
			this.label = label;
			label.getHost().addAncestorListener(this);
		}

		// CR408950: BP Save problems
		// fixed icon label refresh problems
		private Layer getParentLayer() {
			IFigure fig = label.getHost();
			while (fig != null) {
				if (fig instanceof Layer)
					return (Layer) fig;
				fig = fig.getParent();
			}
			return null;
		}

		public void ancestorAdded(IFigure ancestor) {
			Layer layer = getParentLayer();
			if (layer != null && !layer.getChildren().contains(label)) {
				layer.add(label);
				setConstraints();
			}
		}

		public void ancestorMoved(IFigure ancestor) {
			setConstraints();
		}

		public void ancestorRemoved(IFigure ancestor) {
			// CR400779: GEM minimizing a complex activity sometimes leaves junk
			// on the screen
			if (label.getParent() != null
					&& label.getParent().getChildren().contains(label))
				label.getParent().remove(label);
		}

		public void setConstraints() {
			Layer layer = getParentLayer();
			if (layer != null && layer.getLayoutManager() != null
					&& layer.getChildren().contains(label)) {
				Rectangle r = IconFigure.this.getParent().getBounds().getCopy();
				r.translate(IconFigure.this.getBounds().getLocation());
				r.translate(IconFigure.this.getTextLocation());
				r.width = -1;
				r.height = -1;
				Rectangle oldr = (Rectangle) layer.getLayoutManager()
						.getConstraint(label);
				if (oldr == null || oldr.x != r.x || oldr.y != r.y)
					layer.setConstraint(label, r);
			}
		}
	}

	/**
	 * @param name 
	 * @param icon 
	 */
	public IconFigure(String name, Image icon) {
		super(name, icon);
		setTextPlacement(defaultTextPlacement);
		setOpaque(false);
		setIcon(icon);
		setIconTextGap(0);
		setForegroundColor(defaultForegroundColor);
		// CR396303: DND in RulePanel does not get correct location if viewer is
		// scrolled
		setBackgroundColor(ColorConstants.titleGradient);
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);

		// CR389070: Figures are abbreviating rule figures names and making them
		// unreadable
		iconLabel = new IconLabel(this, name);
		iconLabel.setFont(getFont());
	}

	// CR389070: Figures are abbreviating rule figures names and making them
	// unreadable
	public Label getLabel() {
		return iconLabel;
	}

	public List getDecorators() {
		if (decorators == null)
			decorators = new ArrayList();
		return decorators;
	}

	public Font getFont() {
		if (defaultFont == null)
        {
			defaultFont = JFaceResources.getFontRegistry().get(
					JFaceResources.DEFAULT_FONT);
        }
        
		if (getLocalFont() == null)
        {
            // TODO: replaced a deprecated assignment to font
            // with this, but the behaviour is a little different
			setFont(defaultFont);
        }
        
		return getLocalFont();
	}

	public void setFont(Font f) {
        Font localFont = getLocalFont();
		if (localFont != f) {
			iconLabel.setFont(f);
		}
        super.setFont(f);
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);
		iconLabel.setVisible(visible);
	}

	public void setText(String text) {
		super.setText(text);
		// CR389070: Figures are abbreviating rule figures names and making them
		// unreadable
		if (iconLabel != null && iconLabel.isVisible()) {
			iconLabel.setText(text);
			iconLabel.setFont(getFont());
			// labelLocator.setConstraints();
		}
	}

	public void setToolTipText(String text) {
		Label toolTipLabel = null;

		if (text != null && text.length() > 0) {
			toolTipLabel = new Label(text);
			toolTipLabel.setBorder(new MarginBorder(3));
		}

		super.setToolTip(toolTipLabel);
	}

	public String getToolTipText() {
		if (getToolTip() != null)
			return ((Label) getToolTip()).getText();
		return null;
	}

	public void setHighlight(boolean flag) {
		if (flag)
			setOpaque(true);
		else
			setOpaque(false);
	}

	// CR389070: Figures are abbreviating rule figures names and making them
	// unreadable
	protected Dimension getSubStringTextSize() {
		return getTextSize();
	}

	/*
	 * CR374981: Long activity labels do not get wrapped or truncated We must
	 * override this so that the label gets truncated
	 */
	public Dimension getPreferredSize(int wHint, int hHint) {
		if (prefSize == null) {
			super.getPreferredSize(-1, -1);
			prefSize.width = getIconBounds().getSize().width;
			Dimension minSize_ = getMinimumSize(wHint, hHint);
			if (prefSize.width < minSize_.width)
				prefSize.width = minSize_.width;
			if (prefSize.height < minSize_.height)
				prefSize.height = minSize_.height;
		}
		return prefSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.IFigure#getMinimumSize(int, int)
	 */
	public Dimension getMinimumSize(int w, int h) {
		return new Dimension(8, 8);
	}

	public void addDecorator(BaseFigureDecorator decorator) {
		removeDecorator(decorator.getPosition());
		getDecorators().add(decorator);
		add(decorator);
		invalidate();
	}

	public void removeDecorator() {
		removeDecorator(BaseFigureDecorator.DEFAULT_DECORATOR_POSITION);
	}

	public void removeDecorator(int position) {
		Iterator it = getDecorators().iterator();
		while (it.hasNext()) {
			BaseFigureDecorator d = (BaseFigureDecorator) it.next();
			if (d.getPosition() == position) {
				it.remove();
				remove(d);
				invalidate();
				break;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Label#setTextPlacement(int)
	 */
	public void setTextPlacement(int where) {
		super.setTextPlacement(where);
		layout();
		invalidate();
	}

	public void invalidate() {
		// CR405873: F111-Error decorator missing
		// this was moved to paintFigure()
		// placeDecorators();
		super.invalidate();
	}

	private void placeDecorators() {
		Point o = getLocation();
		Point p = getIconBounds().getLocation();
		Dimension size = this.getIconBounds().getSize();
		Iterator it = getDecorators().iterator();
		while (it.hasNext()) {
			int x = p.x - o.x, y = p.y - o.y;
			BaseFigureDecorator decorator = (BaseFigureDecorator) it.next();
			Dimension decoratorSize = decorator.getPreferredSize();
			switch (decorator.getPosition()) {
			case (PositionConstants.CENTER | PositionConstants.MIDDLE):
				// CR378889: Case of decoration that should be right in the
				// middle.
				// Had to add a fudge factor because it's not exactly where I
				// want it.
				int fudge = 4;
				y += size.height / 2 - decoratorSize.height / 2 + fudge;
				x += size.width / 2 - decoratorSize.width / 2;
				break;
			case PositionConstants.NORTH:
				x += size.width / 2 - decoratorSize.width / 2;
				break;
			case PositionConstants.NORTH_EAST:
				x += size.width - decoratorSize.width;
				break;
			case PositionConstants.EAST:
				x += size.width - decoratorSize.width;
				y += size.height / 2 - decoratorSize.height / 2;
				break;
			case PositionConstants.SOUTH_EAST:
				x += size.width - decoratorSize.width;
				y += size.height - decoratorSize.height;
				break;
			case PositionConstants.SOUTH:
				x += size.width / 2 - decoratorSize.width / 2;
				y += size.height - decoratorSize.height;
				break;
			case PositionConstants.SOUTH_WEST:
				y += size.height - decoratorSize.height;
				break;
			case PositionConstants.WEST:
				y += size.height / 2 - decoratorSize.height / 2;
				break;
			case PositionConstants.NORTH_WEST:
				break;
			}
			getLayoutManager().setConstraint(decorator,
					new Rectangle(x, y, -1, -1));
		}
		layout();
	}

	// CR389070: Figures are abbreviating rule figures names and making them
	// unreadable
	protected void paintFigure(Graphics graphics) {
		if (isOpaque())
			super.paintFigure(graphics);
		// CR405873: F111-Error decorator missing
		placeDecorators();
		Rectangle bounds_ = getBounds();
		graphics.translate(bounds_.x, bounds_.y);
		if (getIcon() != null)
			graphics.drawImage(getIcon(), getIconLocation());
		if (iconLabel == null || !iconLabel.isVisible()) {
			if (!isEnabled()) {
				graphics.translate(1, 1);
				graphics.setForegroundColor(ColorConstants.buttonLightest);
				graphics.drawText(getSubStringText(), getTextLocation());
				graphics.translate(-1, -1);
				graphics.setForegroundColor(ColorConstants.buttonDarker);
			}
			graphics.drawText(getSubStringText(), getTextLocation());
		}
		graphics.translate(-bounds_.x, -bounds_.y);
	}
}