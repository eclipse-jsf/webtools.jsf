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

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.SchemeBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;

/**
 * A border for the tabbed title bar
 *
 */
/*package*/ class TabbedTitleBarBorder extends SchemeBorder {
	// private int state = CompoundFigureListener.RESTORED;

	private IFigure parent;

	private ListenerList listenerList;

	private List tabList;

	private int currentTab;

	private Font font;

	private boolean visible = true;

	// from TitleBarBorder:
	private Color textColor = ColorConstants.black;

	private Color bgColor = ColorConstants.menuBackground;

	private Insets insets;

	private Insets padding = new Insets(2, 2, 2, 2);

	/**
	 * Constructs a TitleBarBorder with its label set to the name of this class.
	 * @param parent 
	 * 
	 * @since 2.0
	 */
	public TabbedTitleBarBorder(final IFigure parent) {
		super(SCHEMES.LOWERED);

		this.parent = parent;

		parent.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent me) {
				Insets padding_ = getPadding();
				Point mp = me.getLocation();
				mp.x -= padding_.left;
				mp.y -= padding_.top + 3; // 3==width of the outer border
				Point pp = parent.getBounds().getLocation();
				Rectangle tr = new Rectangle(pp.x, pp.y, 0, 0);
				//int activeIndex = -1;

				for (int i = 0; i < getTabList().size(); ++i) {
					Tab t = (Tab) tabList.get(i);
					Dimension d = t.getTextExtents();
					d.height += padding_.top + padding_.bottom;
					d.width += padding_.left;
					tr.setSize(d);
					if (tr.contains(mp)) {
						setCurrentTab(i);
						return;
					}
					tr.x += d.width;
				}
			}

			public void mouseReleased(MouseEvent me) {
                // do nothing: not handling release
			}

			public void mouseDoubleClicked(MouseEvent me) {
                // do nothing: not handling release
			}
		});
	}

	/**
	 * @return the tab list
	 */
	protected List getTabList() {
		if (tabList == null)
			tabList = new ArrayList();
		return tabList;
	}

	/**
	 * Add a new tab with the text
	 * @param text
	 * @return the previous number of tabs
	 */
	public int addTab(String text) {
		getTabList().add(new Tab(text));
		return getTabList().size() - 1;
	}

	/**
	 * @param index
	 */
	public void removeTab(int index) {
		if (index >= 0 && index < getTabList().size()) {
			tabList.remove(index);
			if (index >= tabList.size())
				index = tabList.size() - 1;
			setCurrentTab(index);
		}
	}

	/**
	 * @param newIndex
	 */
	public void setCurrentTab(int newIndex) {
		if (newIndex >= 0 && newIndex < getTabList().size()) {
			Tab newTab = (Tab) tabList.get(newIndex);
			int oldIndex = -1;
			for (int i = 0; i < tabList.size(); ++i) {
				Tab t = (Tab) tabList.get(i);
				if (t.isActive()) {
					oldIndex = i;
					t.setActive(false);
					break;
				}
			}
			newTab.setActive(true);
			if (parent != null) {
				parent.invalidate();
				parent.repaint();
			}
			currentTab = newIndex;
			fireTabChanged(oldIndex, newIndex);
		}
	}

	/**
	 * @return the index of the current tab
	 */
	public int getCurrentTab() {
		return currentTab;
	}

	/**
	 * @param index
	 * @return the contents object of the tab at index
	 * or null if  index is out of range
	 */
	public Object getContents(int index) {
		if (index >= 0 && index < getTabList().size())
			return ((Tab) tabList.get(index)).getContents();
		return null;
	}

	/**
	 * @param index
	 * @param contents
	 */
	public void setContents(int index, Object contents) {
		if (index >= 0 && index < getTabList().size())
			((Tab) tabList.get(index)).setContents(contents);
	}

	/**
	 * @param listener
	 */
	public void addTabbedWindowListener(WindowFigureListener listener) {
		if (listenerList == null)
			listenerList = new ListenerList(ListenerList.IDENTITY);
		listenerList.add(listener);
	}

	/**
	 * @param listener
	 */
	public void removeTabbedWindowListener(WindowFigureListener listener) {
		if (listenerList != null)
			listenerList.remove(listener);
	}

	/**
	 * @return the listeners
	 */
	public Object[] getListeners() {
		return listenerList.getListeners();
	}

	/**
	 * @param oldIndex
	 * @param newIndex
	 */
	protected void fireTabChanged(int oldIndex, int newIndex) {
		Object l[] = listenerList.getListeners();
		for (int i = 0; i < l.length; ++i) {
			if (l[i] instanceof WindowFigureListener)
				((WindowFigureListener) l[i]).tabChanged(oldIndex, newIndex);
		}
	}

	/**
	 * @return Returns the font.
	 */
	public Font getFont() {
		if (font == null) {
			font = parent.getFont();
			if (font == null)
				font = JFaceResources.getFontRegistry().get(
						JFaceResources.DEFAULT_FONT);
		}
		return font;
	}

	/**
	 * @param font
	 *            The font to set.
	 */
	public void setFont(Font font) {
		this.font = font;
		invalidate();
	}

	/**
	 * @return Returns the insets.
	 */
	public Insets getInsets() {
		return insets;
	}

	/**
	 * @param c
	 */
	public void setTextColor(Color c) {
		textColor = c;
	}

	/**
	 * @return the text color
	 */
	public Color getTextColor() {
		return textColor;
	}

	/**
	 * @param c
	 */
	public void setBackgroundColor(Color c) {
		bgColor = c;
	}

	/**
	 * @return the background color
	 */
	public Color getBackgroundColor() {
		return bgColor;
	}

	/**
	 * @param p
	 */
	public void setPadding(Insets p) {
		padding = p;
		invalidate();
	}

	/**
	 * @return the padding insets
	 */
	public Insets getPadding() {
		return padding;
	}

	/**
	 * @param text
	 */
	public void setLabel(String text) {
		setLabel(currentTab, text);
	}

	/**
	 * @param index
	 * @param text
	 */
	public void setLabel(int index, String text) {
		if (index >= 0 && index < getTabList().size())
			((Tab) tabList.get(index)).setLabel(text);
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return getLabel(currentTab);
	}

	/**
	 * @param index
	 * @return the label
	 */
	public String getLabel(int index) {
		if (index >= 0 && index < getTabList().size())
			return ((Tab) tabList.get(index)).getLabel();
		return ""; //$NON-NLS-1$
	}

	/**
	 * @return the parent figureb
	 */
	public IFigure getParent() {
		return parent;
	}

	/**
	 * validate each of the tabs
	 */
	public void invalidate() {
		insets = null;
		for (int i = 0; i < getTabList().size(); ++i) {
			Tab t = (Tab) tabList.get(i);
			t.invalidate();
		}
	}

	/**
	 * @param f
	 * @return the text extends
	 */
	public Dimension getTextExtents(IFigure f) {
		Dimension d = new Dimension(0, 0);
		for (int i = 0; i < getTabList().size(); ++i) {
			Tab t = (Tab) tabList.get(i);
			if (d.height == 0)
				d = t.getTextExtents();
			else
				d.width += t.getTextExtents().width;
		}
		return d;
	}

	/**
	 * Sets the min/max buttons visible
	 * 
	 * @param flag -
	 *            if true, buttons are made visible.
	 */
	public void setVisible(boolean flag) {
		visible = flag;
	}

	/**
	 * Calculates and returns the Insets for this border.
	 * 
	 * @param figure
	 *            the figure on which Insets calculations are based
	 * @return the calculated Insets
	 * @since 2.0
	 */
	protected Insets calculateInsets(IFigure figure) {
		insets = new Insets(super.getInsets(figure));
		insets.top = getTextExtents(figure).height;
		return insets;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Border#getInsets(org.eclipse.draw2d.IFigure)
	 */
	public Insets getInsets(IFigure figure) {
		if (insets == null)
			calculateInsets(figure);
		return insets;
	}

	/**
	 * @see org.eclipse.draw2d.Border#paint(IFigure, Graphics, Insets)
	 */
	public void paint(IFigure figure, Graphics g, Insets in) {
		if (!visible)
			return;

		Insets is = new Insets(in);
		getInsets(figure);
		is.top += insets.top;
		super.paint(figure, g, is);
		// Insets insets = getInsets(pane);
		// Insets padding = getPadding();
		// Insets in = new Insets(figureInsets);
		// in.top += insets.top;
		// super.paint(pane, g, in);

		tempRect.setBounds(getPaintRectangle(figure, in));
		Rectangle r = tempRect;
		r.height = Math.min(r.height, getTextExtents(figure).height);

		g.clipRect(r);
		g.setBackgroundColor(getBackgroundColor());
		g.fillRectangle(r);
		int x = r.x;
		int y = r.y;

		Iterator iter = getTabList().iterator();
		while (iter.hasNext()) {
			Tab t = (Tab) iter.next();
			t.paint(g, figure, x, y);
			x += t.getTextExtents().width;
		}

		g.setBackgroundColor(getBackgroundColor());
	}

	/**
	 * @param wHint
	 * @param hHint
	 * @return the minimum size
	 */
	public Dimension getMinimumSize(int wHint, int hHint) {
		Dimension d = getTextExtents(parent);
		getInsets(parent);
		d.expand(insets.left + insets.right, insets.top + insets.bottom);
		// add enough width for the min/max buttons
		// d.width += minButton.getSize().width + maxButton.getSize().width;
		return d;
	}

	private class Tab {
		private String label = ""; //$NON-NLS-1$

		private Object contents = null;

		private Dimension textExtents;

		private boolean active;

		/**
		 * @param text
		 */
		public Tab(String text) {
			this.label = text;
		}

		/**
		 * @param contents
		 */
		public void setContents(Object contents) {
			this.contents = contents;
		}

		/**
		 * @return the contents
		 */
		public Object getContents() {
			return contents;
		}

		/**
		 * @param active
		 */
		public void setActive(boolean active) {
			this.active = active;
		}

		/**
		 * @return the active state
		 */
		public boolean isActive() {
			return active;
		}

		/**
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}

		/**
		 * @param text
		 */
		public void setLabel(String text) {
			if (text == null)
				this.label = ""; //$NON-NLS-1$
			else
				this.label = text;
			textExtents = null;
		}

		/**
		 * @return the text extents
		 */
		public Dimension calculateTextExtents() {
			textExtents = FigureUtilities.getTextExtents(label == null ? "W" //$NON-NLS-1$
					: label, getFont());
			textExtents.width += getPadding().getWidth();
			textExtents.height += getPadding().getHeight();
			return textExtents;
		}

		/**
		 * @return the text extents.  A copy is returned based on what
		 * is cached internall by calculateTextExtents
		 */
		public Dimension getTextExtents() {
			if (textExtents == null)
				calculateTextExtents();
			return textExtents.getCopy();
		}

		/**
		 * 
		 */
		public void invalidate() {
			textExtents = null;
		}

		/**
		 * @param g
		 * @param f
		 * @param x
		 * @param y
		 */
		public void paint(Graphics g, IFigure f, int x, int y) {
			if (contents instanceof Composite) {
				return;
			}
			IFigure pane = (IFigure) contents;
			getTextExtents();

			Insets p = getPadding();
			int w = textExtents.width;
			int h = textExtents.height;
			int radius = Math.max(p.getWidth(), p.getHeight()) + 2;

			// CR408950: BP Save problems
			// fixed icon label refresh problems
			if (getTabList().size() > 1) {
				// only draw tabList if there are more than 1
				if (active)
					g.setBackgroundColor(pane == null ? ColorConstants.white
							: pane.getBackgroundColor());
				else
					g.setBackgroundColor(getBackgroundColor());

				--w;
				g.setForegroundColor(active ? ColorConstants.buttonDarkest
						: ColorConstants.buttonDarker);
				// g.setForegroundColor(ColorConstants.red);
				g.drawRectangle(x, y + h / 2, w, h);
				// g.setBackgroundColor(ColorConstants.green);
				g.fillRoundRectangle(new Rectangle(x, y, w, h), radius, radius);
				// g.setForegroundColor(ColorConstants.blue);
				g.drawRoundRectangle(new Rectangle(x, y, w, h), radius, radius);
				// g.setBackgroundColor(ColorConstants.yellow);
				g.fillRectangle(x + 1, y + h / 2, w - 1, h);
			} else
				g.setBackgroundColor(getBackgroundColor());

			g.setFont(getFont());
			g.setForegroundColor(getTextColor());
			if (label != null)
				g.drawString(label, x + padding.left + 1, y + padding.top);
		}
	}
}