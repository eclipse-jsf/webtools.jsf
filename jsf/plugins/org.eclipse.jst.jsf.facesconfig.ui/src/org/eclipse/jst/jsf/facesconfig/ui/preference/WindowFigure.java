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
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.AncestorListener;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.FreeformListener;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.GridLayer;
import org.eclipse.gef.editparts.GuideLayer;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class WindowFigure extends ScrollPane implements IContainerFigure,
		LayerConstants, FreeformFigure {
	private TabbedWindowBorder windowBorder;

	private TabbedTitleBarBorder titleBarBorder;

	private FreeformViewport myViewport;

	// private FreeformLayeredPane layeredPane;

	private ScalableFreeformLayeredPane scaledLayers;

	private LayeredPane innerLayers;

	private LayeredPane printableLayers;

	private FreeformLayer primaryLayer;

	public static Font defaultFont = JFaceResources.getFontRegistry().get(
			JFaceResources.DEFAULT_FONT);

	public static Color defaultForegroundColor = ColorConstants.black;

	public static Color defaultBackgroundColor = ColorConstants.white;

	public static Color defaultGridColor = ColorConstants.lightGray;

	public static Dimension defaultGridSpacing = null;

	public static boolean defaultGridEnabled = false;

	// Line Routing is not used within the WindowFigure class; it just serves as
	// a storage
	// location for communicating between the GEMPreferences and EditParts that
	// actually
	// create the line routers.
	public static int LINE_ROUTING_MANUAL = 0;

	public static int LINE_ROUTING_MANHATTAN = 1;

	public static int defaultLineRoutingStyle = LINE_ROUTING_MANUAL;

	private class MyGridLayer extends GridLayer {
		public Point getOrigin() {
			return origin.getCopy();
		}
	}

	private class MyFeedbackLayer extends FreeformLayer {
		MyFeedbackLayer() {
			setEnabled(false);
		}
	}

	public WindowFigure() {
		super();

		windowBorder = new TabbedWindowBorder(this);
		titleBarBorder = (TabbedTitleBarBorder) windowBorder.getInnerBorder();
		setBorder(windowBorder);
		setFont(defaultFont);
		setForegroundColor(defaultForegroundColor);
		setBackgroundColor(defaultBackgroundColor);

		innerLayers = new FreeformLayeredPane();
		createLayers(innerLayers);

		myViewport = new FreeformViewport();
		myViewport.setContents(innerLayers);

		setViewport(myViewport);

		// CR389495: Working with nested complex activities causes in the BP
		// editor causes lockup
		// not related to this CR but discovered while working on it:
		// make sure we observe grid visibility from preferences
		getGridLayer().setVisible(defaultGridEnabled);
		getGridLayer().setSpacing(defaultGridSpacing);
		getGridLayer().setForegroundColor(defaultGridColor);

		setText("Window");

		addTabbedWindowListener(new WindowFigureListener() {
			public void tabChanged(int oldIndex, int newIndex) {
				internalSetCurrentTab(newIndex);
				validate();
			}
		});
		addAncestorListener(new AncestorListener() {

			public void ancestorAdded(IFigure ancestor) {
				setVisible(true);
			}

			public void ancestorMoved(IFigure ancestor) {
				// validateComposite();
			}

			public void ancestorRemoved(IFigure ancestor) {
				// TODO: fix this - shouldn't be throwing an exception
				try {
					setVisible(false);
				} catch (Exception e) {
                    EditorPlugin.getDefault().getLog().log(
                       new Status(IStatus.ERROR, EditorPlugin.getPluginId(), 0, "Error setting visible", e));
				}
			}
		});
	}

	// //////////////////////////////////////////////////////////////////////////
	// Layer management
	// //////////////////////////////////////////////////////////////////////////

	protected void createLayers(LayeredPane layeredPane) {
		layeredPane.add(getScaledLayers(), SCALABLE_LAYERS);
		layeredPane.add(new FreeformLayer(), HANDLE_LAYER);
		// CR377650: Horizontal scrolling needs to be improved
		// moved the feedback layer into printable layers so it shows up in
		// content outline
		// layeredPane.add(new MyFeedbackLayer(), FEEDBACK_LAYER);
		layeredPane.add(new GuideLayer(), GUIDE_LAYER);
	}

	protected ScalableFreeformLayeredPane createScaledLayers() {
		ScalableFreeformLayeredPane layers = new ScalableFreeformLayeredPane();
		layers.add(createGridLayer(), GRID_LAYER);
		layers.add(getPrintableLayers(), PRINTABLE_LAYERS);
		// CR389070: Figures are abbreviating rule figures names and making them
		// unreadable
		// not needed (?)
		// layers.add(new MyFeedbackLayer(), SCALED_FEEDBACK_LAYER);
		return layers;
	}

	protected LayeredPane getScaledLayers() {
		if (scaledLayers == null)
			scaledLayers = createScaledLayers();
		return scaledLayers;
	}

	protected LayeredPane createPrintableLayers() {
		FreeformLayeredPane layeredPane = new FreeformLayeredPane();

		PrintedPageLayer pageTileLayer = new PrintedPageLayer(this);
		// TODO: get the printer page size from printer preferences
		// and set into the PageTileLayer.
		pageTileLayer.setPageSize(new Dimension(0, 0));
		layeredPane.add(pageTileLayer, PrintedPageLayer.PRINTED_PAGE_LAYER, -1);

		// CR400208: Lines bleed from the bp canvas through the Fault handler
		// window
		// changed z-order of connection layer so that it is below figures
		layeredPane.add(new ConnectionLayer(), CONNECTION_LAYER, -1);
		primaryLayer = new FreeformLayer();
		layeredPane.add(primaryLayer, PRIMARY_LAYER, -1);
		primaryLayer.setLayoutManager(new FreeformLayout());

		// CR377650: Horizontal scrolling needs to be improved
		// let's put the feedback layer in with the printable layers so
		// we can see the feedback figures in the content outline viewer
		layeredPane.add(new MyFeedbackLayer(), FEEDBACK_LAYER);
		return layeredPane;
	}

	protected LayeredPane getPrintableLayers() {
		if (printableLayers == null)
			printableLayers = createPrintableLayers();
		return printableLayers;
	}

	protected GridLayer createGridLayer() {
		return new MyGridLayer();
	}

	public GridLayer getGridLayer() {
		return (GridLayer) getLayer(GRID_LAYER);
	}

	public Layer getLayer(Object key) {
		// ScalableFreeformRootEditPart:
		Layer layer = null;
		if (scaledLayers != null)
			layer = scaledLayers.getLayer(key);
		if (layer != null)
			return layer;
		// FreeformGraphicalRootEditPart:
		if (innerLayers == null)
			return null;
		layer = innerLayers.getLayer(key);
		if (layer != null)
			return layer;
		if (printableLayers == null)
			return null;
		return printableLayers.getLayer(key);
	}

	// //////////////////////////////////////////////////////////////////////////
	// Properties
	// //////////////////////////////////////////////////////////////////////////

	public Point getScrollPosition() {
		int x = getHorizontalScrollBar().getRangeModel().getValue();
		int y = getVerticalScrollBar().getRangeModel().getValue();
		return new Point(x, y);
	}

	public TabbedTitleBarBorder getTabbedTitleBarBorder() {
		return titleBarBorder;
	}

	public TabbedWindowBorder getTabbedWindowBorder() {
		return windowBorder;
	}

	public void addTabbedWindowListener(WindowFigureListener listener) {
		titleBarBorder.addTabbedWindowListener(listener);
	}

	public void removeTabbedWindowListener(WindowFigureListener listener) {
		titleBarBorder.removeTabbedWindowListener(listener);
	}

	public Dimension getMinimumSize(int wHint, int hHint) {
		Dimension d = titleBarBorder.getMinimumSize(wHint, hHint);
		d.expand(getInsets().getWidth(), getInsets().getHeight());
		return d;
	}

	public int addTab(String s) {
		return titleBarBorder.addTab(s);
	}

	public void removeTab(int index) {
		titleBarBorder.removeTab(index);
		internalSetCurrentTab(titleBarBorder.getCurrentTab());
	}

	private void internalSetCurrentTab(int index) {
		// Object contents = titleBarBorder.getContents(index);
		// if (contents instanceof IFigure)
		// setContents((IFigure) contents);
	}

	public void setCurrentTab(int index) {
		internalSetCurrentTab(index);
		titleBarBorder.setCurrentTab(index);
	}

	public int getCurrentTab() {
		return titleBarBorder.getCurrentTab();
	}

	public void setContents(IFigure figure) {
		titleBarBorder.setContents(0, figure);
		super.setContents(figure);
	}

	public void setContents(int index, Object contents) {
		if (contents instanceof IFigure)
			setContents((IFigure) contents);
		titleBarBorder.setContents(index, contents);
	}

	public Object getContents(int index) {
		return titleBarBorder.getContents(index);
	}

	public void setVisible(boolean flag) {
		super.setVisible(flag);
		windowBorder.setVisible(flag);
		// hide/show the window contents if it's a SWT composite
		Object contents = getContents();
		if (contents instanceof Composite)
			((Composite) contents).setVisible(flag);
	}

	public void setHighlight(boolean flag) {
		windowBorder.setHighlight(flag);
	}

	public void setFont(Font f) {
		titleBarBorder.setFont(f);
	}

	public void setForegroundColor(Color c) {
		titleBarBorder.setTextColor(c);
	}

	public void setLineRoutingStyle(int style) {
		defaultLineRoutingStyle = style;
		revalidate();
	}

	public int getLineRoutingStyle() {
		return defaultLineRoutingStyle;
	}

	public Dimension getPreferredSize(int wHint, int hHint) {
		if (prefSize == null) {
			IFigure parent = getParent();
			prefSize = parent.getSize();
			this.getLayoutManager().invalidate();
			Dimension m = super.getPreferredSize(wHint, hHint);
			if (getParent() instanceof CompoundNodeFigure) {
				if (((CompoundNodeFigure) parent).getState() == CompoundNodeFigure.RESTORED) {
					Insets in = ((CompoundNodeFigure) getParent())
							.getAnchorInsets();
					prefSize.width -= in.getWidth();
					prefSize.height -= in.getHeight();
				}
			} else {
				if (m.width > prefSize.width)
					prefSize.width = m.width;
				if (m.height > prefSize.height)
					prefSize.height = m.height;
			}
		}
		return prefSize;
	}

	protected boolean isValidationRoot() {
		return true;
	}

	// private void validateComposite() {
	// Object contents = getContents();
	// // System.out.println("validate: "+titleBarBorder.getLabel(0)+"
	// // "+contents);
	// if (contents instanceof Composite) {
	// if (getParent() == null) {
	// System.err.println("TabbedWindowFigure.validate() - ERROR");
	// return;
	// }
	// Rectangle r = getBounds().getCopy();
	// r.crop(getInsets());
	// Dimension ps = getParent().getParent().getPreferredSize();
	// Rectangle pr = getParent().getParent().getBounds();
	// titleBarBorder.invalidate();
	// titleBarBorder.getInsets(this);
	// Insets in = getParent().getParent().getInsets();
	// Composite comp = (Composite) getContents();
	// comp.setBounds(pr.x + r.x + in.left, pr.y + r.y, r.width, r.height);
	// comp.moveAbove(null);
	// }
	// }

	public void invalidate() {
		prefSize = null;
		super.invalidate();
	}

	// //////////////////////////////////////////////////////////////////////
	// IBaseFigure methods
	// //////////////////////////////////////////////////////////////////////

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#setText(String
	 *      text)
	 */
	public void setText(String name) {
		titleBarBorder.setLabel(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.basic.ITabbedWindow#setName(int,
	 *      java.lang.String)
	 */
	public void setText(int index, String name) {
		titleBarBorder.setLabel(index, name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getText()
	 */
	public String getText() {
		if (titleBarBorder == null)
			return "??";
		return titleBarBorder.getLabel();
	}

	/*
	 * CR374981: Long activity labels do not get wrapped or truncated This
	 * method was added to the IBaseFigure interface to support direct edit of
	 * figure labels on the canvas.
	 */
	public Rectangle getTextBounds() {
		Rectangle r = getClientArea().getCopy();
		r.height = titleBarBorder.getTextExtents(this).height;
		r.x += getInsets().left;
		r.y -= getInsets().top;
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getIcon()
	 */
	public Image getIcon() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#setIcon(org.eclipse.swt.graphics.Image)
	 */
	public void setIcon(Image image) {
        // do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#setToolTipText(java.lang.String)
	 */
	public void setToolTipText(String text) {
        // do nothing
	}

	public String getToolTipText() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#addDecorator(com.sybase.stf.gem.diagram.editor.figures.BaseFigureDecorator)
	 */
	public void addDecorator(BaseFigureDecorator decorator) {
        // do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#removeDecorator()
	 */
	public void removeDecorator() {
        // do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#removeDecorator(int)
	 */
	public void removeDecorator(int position) {
        // do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getDecorators()
	 */
	public List getDecorators() {
		return null;
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
		Iterator iter = getListeners(FreeformListener.class);
		while (iter.hasNext())
			((FreeformListener) iter.next()).notifyFreeformExtentChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.FreeformFigure#getFreeformExtent()
	 */
	public Rectangle getFreeformExtent() {
		Rectangle r = helper.getFreeformExtent();
		r.x = 0;
		r.y = 0;
		return r;
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
		helper.setFreeformBounds(bounds);
	}
}