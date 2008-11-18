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

import java.util.Iterator;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.FreeformListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * The figure for Compound nodes
 */
/*package*/ class CompoundNodeFigure extends BaseNodeFigure implements
		FreeformFigure, IContainerFigure {
	
	private static final String RESTORE_HILITE_IMAGE_FILE = "restore_hilite.gif"; //$NON-NLS-1$

	private static final String RESTORE_IMAGE_FILE = "restore.gif"; //$NON-NLS-1$

	private static final String MAXIMIZE_HILITE_IMAGE_FILE = "maximize_hilite.gif"; //$NON-NLS-1$

	private static final String MAXIMIZE_IMAGE_FILE = "maximize.gif"; //$NON-NLS-1$

	private static final String MINIMIZE_HILITE_IMAGE_FILE = "minimize_hilite.gif"; //$NON-NLS-1$

	private static final String MINIMIZE_IMAGE_FILE = "minimize.gif"; //$NON-NLS-1$

	private final static int MINIMIZED = 1;

	final static int RESTORED = 2;

	private final static int MAXIMIZED = 3;

	private int state = RESTORED;

	private TitleBarButton minButton;

	private TitleBarButton maxButton;

	private IconFigure iconFigure;

	private WindowFigure windowFigure;

	private static String DEFAULT_NAME = "CompoundNode"; //$NON-NLS-1$

	private static String DEFAULT_ICON = "editor/CompoundNode.gif"; //$NON-NLS-1$

	private static Image minimizeImage = EditorPlugin.getDefault().getImage(
			MINIMIZE_IMAGE_FILE);

	private static Image minimizeHiliteImage = EditorPlugin.getDefault()
			.getImage(MINIMIZE_HILITE_IMAGE_FILE);

	private static Image maximizeImage = EditorPlugin.getDefault().getImage(
			MAXIMIZE_IMAGE_FILE);

	private static Image maximizeHiliteImage = EditorPlugin.getDefault()
			.getImage(MAXIMIZE_HILITE_IMAGE_FILE);

	private static Image restoreImage = EditorPlugin.getDefault().getImage(
			RESTORE_IMAGE_FILE);

	private static Image restoreHiliteImage = EditorPlugin.getDefault()
			.getImage(RESTORE_HILITE_IMAGE_FILE);

	/**
	 * Default constructor
	 */
	public CompoundNodeFigure() {
		// create all of the figures and adornments:
		// the icon figure
		super(new IconFigure(DEFAULT_NAME, EditorPlugin.getDefault().getImage(
				DEFAULT_ICON)));
		iconFigure = (IconFigure) getBaseFigure();
		// and the window figure
		windowFigure = new WindowFigure();
		windowFigure.setForegroundColor(IconFigure.defaultForegroundColor);
		windowFigure.setFont(IconFigure.defaultFont);

		// then the minimize/maximize buttons.
		// Note that the maxButton is also used for the "Restore" action
		// TODO:
		// The reason for putting these buttons in CompoundNodeFigure instead of
		// WindowFigure where they really belong, is that the TitleBarButton is
		// a
		// draw2d Button (which subclasses Figure) and these must be placed in
		// the
		// titlebar (i.e. a SchemeBorder) because that's where they belong. The
		// problem
		// with this is that the Figure.paint() method first draws all of its
		// children
		// figures, then the border which means that the buttons would be
		// obscured.
		// This should be fixed as time allows and the WindowFigureListener
		// interface
		// should then also be modified to include a buttonPressed()
		// notification
		// for which CompoundNodeFigure can be a listener.
		minButton = new TitleBarButton(minimizeImage, minimizeHiliteImage,
				null, null);
		maxButton = new TitleBarButton(maximizeImage, maximizeHiliteImage,
				restoreImage, restoreHiliteImage);

		add(minButton);
		add(maxButton);

		minButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setState(CompoundNodeFigure.MINIMIZED);
			}
		});
		maxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setState(maxButton.getState() ? CompoundNodeFigure.MAXIMIZED
						: CompoundNodeFigure.RESTORED);
			}
		});

		// show as a normal window initially
		showIcon();

		// testing BaseFigureDecorators:
		// BaseFigureDecorator decorator;
		// decorator = new
		// BaseFigureDecorator(EditorPlugin.getDefault().getGEMImage("delete.gif"),
		// "top right",
		// PositionConstants.NORTH_EAST);
		// addDecorator(decorator);
		// decorator = new
		// BaseFigureDecorator(EditorPlugin.getDefault().getGEMImage("delete.gif"),
		// "top left",
		// PositionConstants.NORTH_WEST);
		// addDecorator(decorator);
		// decorator = new
		// BaseFigureDecorator(EditorPlugin.getDefault().getGEMImage("delete.gif"),
		// "bottom right",
		// PositionConstants.SOUTH_EAST);
		// addDecorator(decorator);
		// decorator = new
		// BaseFigureDecorator(EditorPlugin.getDefault().getGEMImage("delete.gif"),
		// "bottom left",
		// PositionConstants.SOUTH_WEST);
		// addDecorator(decorator);
	}

	public void setOpaque(boolean flag) {
		windowFigure.setOpaque(flag);
	}

	/*
	 * Remove the window figure and its buttons and replace them with the icon
	 * figure.
	 */
	private void showIcon() {
		// CR400779: GEM minimizing a complex activity sometimes leaves junk on
		// the screen
		setVisible(false);
		minButton.setVisible(false);
		maxButton.setVisible(false);
		super.setBaseFigure(iconFigure);

		setAnchorsVisible(true);
		// CR400779: GEM minimizing a complex activity sometimes leaves junk on
		// the screen
		setVisible(true);
	}

	/*
	 * Remove the icon figure and replace with the window figure. Make sure the
	 * buttons are reset to their correct states.
	 */
	private void showWindow() {
		// CR400779: GEM minimizing a complex activity sometimes leaves junk on
		// the screen
		setVisible(false);
		if (state == RESTORED) {
			super.setBaseFigure(windowFigure);

			minButton.setVisible(true);
			minButton.setState(false);
			minButton.setToolTip(new Label(PreferenceMessages.CompoundNodeFigure_MinimizeLabel));

			maxButton.setVisible(true);
			maxButton.setState(false);
			maxButton.setToolTip(new Label(PreferenceMessages.CompoundNodeFigure_MaximizeLabel));

			setAnchorsVisible(true);
		} else if (state == MAXIMIZED) {
			setAnchorsVisible(false);

			Rectangle constraint = new Rectangle(0, 0, -1, -1);
			IFigure parent = getParent();
			if (parent != null) {
				constraint = parent.getBounds().getCopy();
				constraint.width += constraint.x;
				constraint.height += constraint.y;
				constraint.x = 0;
				constraint.y = 0;
			}

			super.setBaseFigure(windowFigure, constraint);
			if (parent != null && parent.getLayoutManager() != null) {
				// System.out.println("CompoundNode.showWindow: maximize
				// "+constraint);
				parent.getLayoutManager().setConstraint(this, constraint);
			}

			// the maximize button is used for both Maximize and Restore actions
			minButton.setVisible(false);

			maxButton.setVisible(true);
			maxButton.setState(true);
			maxButton.setToolTip(new Label(PreferenceMessages.CompoundNodeFigure_RestoreLabel));
		}
		// CR400779: GEM minimizing a complex activity sometimes leaves junk on
		// the screen
		setVisible(true);
	}

	public void addDecorator(BaseFigureDecorator decorator) {
		iconFigure.addDecorator(decorator);
	}

	public void removeDecorator() {
		iconFigure.removeDecorator();
	}

	public void removeDecorator(int position) {
		iconFigure.removeDecorator(position);
	}

	/**
	 * @param flag
	 */
	public void setHilight(boolean flag) {
		windowFigure.setHighlight(flag);
		if (flag)
			moveToTop();
	}

	public void setText(String name) {
		iconFigure.setText(name);
		windowFigure.setText(name);
	}

	public void setIcon(Image image) {
		iconFigure.setIcon(image);
	}

	public void setToolTipText(String text) {
		iconFigure.setToolTipText(text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#layout()
	 */
	public void validate() {
		LayoutManager layout = getLayoutManager();
		if (state == MINIMIZED) {
			// iconFigure.validate();
		} else if (state == RESTORED) {
			Rectangle r = windowFigure.getBounds().getCopy();
			Insets i = windowFigure.getInsets();
			Dimension dm = maxButton.getSize();
			Insets p = windowFigure.getTabbedTitleBarBorder().getPadding();
			layout.setConstraint(maxButton, new Rectangle(r.x + r.width
					- dm.width - p.right - i.right, r.y + p.top + i.top
					- dm.height - i.bottom, -1, -1));
			layout.setConstraint(minButton, new Rectangle(r.x + r.width - 2
					* dm.width - 2 * p.right - i.right, r.y + p.top + i.top
					- dm.height - i.bottom, -1, -1));
		} else if (state == MAXIMIZED) {
			// CR387660: restore icon on complex activity bounces around
			// we need to use the parent's bounds when maximized instead of
			// current window bounds
			if (getParent() == null)
				return;
			Rectangle r = getParent().getBounds().getCopy();
			Insets i = windowFigure.getInsets();
			Dimension dm = maxButton.getSize();
			Insets p = windowFigure.getTabbedTitleBarBorder().getPadding();
			layout.setConstraint(maxButton, new Rectangle(r.x + r.width
					- dm.width - p.right - i.right, r.y + p.top, -1, -1));
		}
		super.validate();
	}
	/**
	 * @return the window figure
	 */
	public WindowFigure getWindowFigure() {
		return windowFigure;
	}

	public void setFont(Font f) {
		iconFigure.setFont(f);
		windowFigure.setFont(f);
	}

	public void setForegroundColor(Color c) {
		iconFigure.setForegroundColor(c);
		windowFigure.setForegroundColor(c);
	}

	public void setBackgroundColor(Color c) {
		iconFigure.setBackgroundColor(c);
		windowFigure.setBackgroundColor(c);
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param newState
	 */
	public void setState(int newState) {
		int oldState = state;
		state = newState;

		if (state == CompoundNodeFigure.MINIMIZED) {
			if (getParent() != null && getParent().getLayoutManager() != null) {
				Rectangle constraint = (Rectangle) getParent()
						.getLayoutManager().getConstraint(this);
				constraint.width = -1;
				constraint.height = -1;
			}
			showIcon();
		} else if (state == CompoundNodeFigure.MAXIMIZED) {
			if (getParent() != null && getParent().getLayoutManager() != null) {
				Rectangle constraint = (Rectangle) getParent()
						.getLayoutManager().getConstraint(this);
				constraint.x = 0;
				constraint.y = 0;
				constraint.width = -1;
				constraint.height = -1;
			}
			showWindow();
		} else if (state == CompoundNodeFigure.RESTORED) {
			if (getParent() != null && getParent().getLayoutManager() != null) {
				Rectangle constraint = (Rectangle) getParent()
						.getLayoutManager().getConstraint(this);
				constraint.setSize(windowFigure.getBounds().getSize());
			}
			showWindow();
		}
		fireStateChanged(oldState, newState);
	}

	private void fireStateChanged(int oldState, int newState) {
		Object l[] = windowFigure.getTabbedTitleBarBorder().getListeners();
		for (int i = 0; i < l.length; ++i) {
			if (l[i] instanceof CompoundFigureListener)
				((CompoundFigureListener) l[i])
						.stateChanged(oldState, newState);
		}
	}

	public Dimension getMinimumSize(int wHint, int hHint) {
		if (getState() == CompoundNodeFigure.RESTORED) {
			Dimension d = windowFigure.getMinimumSize(wHint, hHint);
			d.width += minButton.getSize().width
					+ maxButton.getSize().width
					+ 2
					* windowFigure.getTabbedTitleBarBorder().getPadding()
							.getWidth();
			return d;
		}
		if (getState() == CompoundNodeFigure.MINIMIZED)
			return iconFigure.getMinimumSize(wHint, hHint);
		return super.getMinimumSize(wHint, hHint);
	}

	// //////////////////////////////////////////////////////////////////////
	// FreeformFigure methods
	// //////////////////////////////////////////////////////////////////////

	private WindowFreeformHelper helper = new WindowFreeformHelper(this);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.FreeformFigure#addFreeformListener(org.eclipse.draw2d.FreeformListener)
	 */
	public void addFreeformListener(FreeformListener listener) {
		addListener(FreeformListener.class, listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.FreeformFigure#fireExtentChanged()
	 */
	public void fireExtentChanged() {
		// CR389495: Working with nested complex activities causes in the BP
		// editor causes lockup
		// not specifically related to this CR, but caused a problem when
		// compound node
		// was moved beyond viewport's client area and de-selected - this would
		// resize
		// the viewport so that compound node no longer participated in bounds
		// calculation.
		if (state == MAXIMIZED) {
			Iterator iter = getListeners(FreeformListener.class);
			while (iter.hasNext())
				((FreeformListener) iter.next()).notifyFreeformExtentChanged();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.FreeformFigure#getFreeformExtent()
	 */
	public Rectangle getFreeformExtent() {
		// CR389495: Working with nested complex activities causes in the BP
		// editor causes lockup
		// same as above
		if (state == MAXIMIZED)
			return helper.getFreeformExtent();
		return getBounds();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.FreeformFigure#removeFreeformListener(org.eclipse.draw2d.FreeformListener)
	 */
	public void removeFreeformListener(FreeformListener listener) {
		removeListener(FreeformListener.class, listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.FreeformFigure#setFreeformBounds(org.eclipse.draw2d.geometry.Rectangle)
	 */
	public void setFreeformBounds(Rectangle bounds) {
		if (getState() == MAXIMIZED)
			helper.setFreeformBounds(bounds);
	}

	/*
	 * CR389070: Figures are abbreviating rule figures names and making them
	 * unreadable New Method on IContainerFigure
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IContainerFigure#getLayer(java.lang.Object)
	 */
	public Layer getLayer(Object key) {
		if (windowFigure != null && windowFigure.isVisible())
			return windowFigure.getLayer(key);
		return null;
	}
}