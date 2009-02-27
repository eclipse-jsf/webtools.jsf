/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.css2.layout;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.border.CSSBorder;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.property.VisibilityMeta;
import org.eclipse.jst.pagedesigner.css2.style.DefaultStyle;
import org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo;
import org.eclipse.jst.pagedesigner.css2.widget.BorderUtil;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;

/**
 * Normally a CSSFigure is a container. It's layout will be driven by different
 * display type information from the style.
 * 
 * Each CSSFigure will be driven by ICSSStyle, the display type of the ICSSStyle
 * will decide the layout to be used for the figure.
 * 
 * @author mengbo
 */
public class CSSFigure extends FlowFigure implements ICSSFigure {
	private static Logger _log = PDPlugin.getLogger(CSSFigure.class);

	private static final Rectangle PRIVATE_RECT = new Rectangle();

	private ICSSStyle _style;

	// if this field is set, then regetLayout() will still return this layout,
	// without going through the CSS resolution
	private CSSLayout _fixedLayout;

	/**
	 * Default constructor 
	 * Equivalent to CSSFigure(DefaultStyle.getInstance())
	 */
	public CSSFigure() {
		this(DefaultStyle.getInstance());
	}

	/**
	 * @param style
	 */
	public CSSFigure(ICSSStyle style) {
		_style = style;
		invalidateCSS();
	}

	public ICSSStyle getCSSStyle() {
		return _style;
	}

	/**
	 * @param style
	 */
	public void setCSSStyle(ICSSStyle style) {
		_style = style;
		invalidateCSS();
	}

	public void revalidate() {
		CSSLayout layout = (CSSLayout) getLayoutManager();
		layout.figureRevalidate();
		super.revalidate();
	}

	/**
	 * this method is called when the css source noticed style change. So tell
	 * the figure should invalidate its cached data.
	 */
	public void invalidateCSS() {
		// maybe we changed from inline to block or block to inline
		// XXX: or even to table?
		CSSLayout layout = regetLayout(getLayoutManager());
		this.setLayoutManager(layout);
	}

	/**
	 * @param layout
	 */
	public void setFixedLayoutManager(CSSLayout layout) {
		this._fixedLayout = layout;
		this.setLayoutManager(regetLayout(getLayoutManager()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#setLayoutManager(org.eclipse.draw2d.LayoutManager)
	 */
	public void setLayoutManager(LayoutManager manager) {
		LayoutManager old = getLayoutManager();
		if (old != manager) {
			FlowContext context = null;
			if (old instanceof FlowFigureLayout) {
				context = ((FlowFigureLayout) old).getOriginalFlowContext();
			}
			if (manager instanceof FlowFigureLayout) {
				((FlowFigureLayout) manager).setOriginalFlowContext(context);
			}

			if (manager instanceof FlowContext) {
				List list = getChildren();
				for (int i = 0, size = list.size(); i < size; i++) {
					try {
						((FlowFigure) list.get(i))
								.setOriginalFlowContext((FlowContext) manager);
					} catch (ClassCastException classcastexception) {
						// Error in flowContext setting.
						_log.error("Error.CSSFigure.0", classcastexception); //$NON-NLS-1$
					}
				}
			}
		}
		super.setLayoutManager(manager);
	}

	/**
	 * @param old
	 * @return the layout
	 */
	protected CSSLayout regetLayout(LayoutManager old) {
		if (_fixedLayout != null) {
			return _fixedLayout;
		}
		CSSLayout layout = DisplayToLayout.displayToLayout(this, getCSSStyle()
				.getDisplay(), old);
		if (layout != null) {
			return layout;
		}
        return new CSSInlineFlowLayout(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#containsPoint(int, int)
	 */
	public boolean containsPoint(int x, int y) {
		// check whether any float figure contains it.
		// FIXME: need check floating figure here!!!
		if (!super.containsPoint(x, y)) {
			return false;
		}
		List frags = getFragmentsForRead();
		// Here we should not get void pointer.
		if (frags != null) {
			for (int i = 0; i < frags.size(); i++) {
				FlowBox box = (FlowBox) frags.get(i);
				if (box != null && box.containsPoint(x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.ICSSFigure#getFragmentsForRead()
	 */
	public List getFragmentsForRead() {
		CSSLayout layout = (CSSLayout) getLayoutManager();
		return layout.getFragmentsForRead();
	}

	/**
	 * this method is a shortcut to getFragmentsForRead
	 * 
	 * @return fragment bounds
	 */
	public Rectangle[] getFragmentsBounds() {
		List list = getFragmentsForRead();
		if (list == null || list.size() == 0) {
			// should not happen. but still handle it.
			return new Rectangle[] { getBounds() };
		}
        Rectangle[] ret = new Rectangle[list.size()];
        for (int i = 0, size = list.size(); i < size; i++) {
        	FlowBox box = (FlowBox) list.get(i);
        	ret[i] = new Rectangle(box._x, box._y, box.getWidth(), box
        			.getHeight());
        }
        return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.IFigure#setBounds(org.eclipse.draw2d.geometry.Rectangle)
	 */
	public void setBounds(Rectangle r) {
		if (getBounds().equals(r)) {
			return;
		}
		boolean invalidate = getBounds().width != r.width
				|| getBounds().height != r.height;
		super.setBounds(r);

		CSSLayout layout = (CSSLayout) this.getLayoutManager();
		layout.setBoundsCalled(r, invalidate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigure#postValidate()
	 */
	public void postValidate() {
		CSSLayout layout = (CSSLayout) getLayoutManager();
		layout.postValidateForAbsolute();
		layout.postValidate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.IFigure#validate()
	 */
	public void validate() {
		super.validate();
		// should not call this.postValidate() here. PostValidate() should
		// only be started from the FlowPage. Otherwise it will be called
		// multiple times on a figure.
		// this.postValidate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#useLocalCoordinates()
	 */
	protected boolean useLocalCoordinates() {
		CSSLayout layout = (CSSLayout) getLayoutManager();
		if (layout == null) {
			return false;
		}
        return layout.useLocalCoordinates();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.IFigure#paint(org.eclipse.draw2d.Graphics)
	 */
	public void paint(Graphics graphics) {
		ICSSStyle style = getCSSStyle();
		if (style != null) {
			Object visibility = style
					.getStyleProperty(ICSSPropertyID.ATTR_VISIBILITY);
			// handle visibility: hidden here.
			// TODO: "collapse" is not supported yet!
			if (VisibilityMeta.HIDDEN.equals(visibility)) {
				return;
			}
		}

		CSSLayout layout = (CSSLayout) this.getLayoutManager();
		graphics.pushState();
		try {
			paintFigure(graphics);
			graphics.restoreState();
			paintClientArea(graphics);
			if (layout instanceof ICSSPainter2) {
				if (useLocalCoordinates()) {
					graphics.translate(getBounds().x + getInsets().left,
							getBounds().y + getInsets().top);
					((ICSSPainter2) layout).paintFigurePostClientArea(graphics);
					graphics.restoreState();
				} else {
					((ICSSPainter2) layout).paintFigurePostClientArea(graphics);
				}
			}
			paintBorder(graphics);
		} finally {
			graphics.popState();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	protected void paintFigure(Graphics g) {
		Color rgbColor = null;
		boolean fillArea = false;
		Object bg = getCSSStyle().getBackgroundColor();
		if (bg instanceof RGB) {
			rgbColor = new Color(null, (RGB) bg);
			g.setBackgroundColor(rgbColor);
			fillArea = true;
		} else if (bg instanceof Color) {
			g.setBackgroundColor((Color) bg);
			fillArea = true;
		}
		if (fillArea) {
			List fragments = getFragmentsForRead();

			for (int i = 0, n = fragments.size(); i < n; i++) {
				Object obj = fragments.get(i);
				if (obj instanceof FlowBox) {
					FlowBox box = (FlowBox) obj;
					g.fillRectangle(box._x, box._y, box.getWidth(), box
							.getHeight());
				}
			}
		}
		if (rgbColor != null) {
			rgbColor.dispose();
		}
		g.restoreState();

		//handle background-image
		Object bgImage = getCSSStyle().getStyleProperty(ICSSPropertyID.ATTR_BACKGROUND_IMAGE);
		if (bgImage instanceof Image) {
			g.setClip(bounds);
			g.drawImage((Image)bgImage, bounds.x, bounds.y);
			g.restoreState();
		}

		LayoutManager layout = getLayoutManager();
		if (layout instanceof ICSSPainter) {
			if (useLocalCoordinates()) {
				g.translate(getBounds().x + getInsets().left, getBounds().y
						+ getInsets().top);
				((ICSSPainter) layout).paintFigure(g);
				g.restoreState();
			} else {
				((ICSSPainter) layout).paintFigure(g);
			}
		}

		// paint selected mode here.
		paintSelection(g);

		if (Debug.DEBUG_BOX) {
			// draw two levels of boxes. Since normally each figure will only
			// have two levels of boxes.
			List fragments = this.getFragmentsForRead();
			for (int i = 0, size = fragments.size(); i < size; i++) {
				FlowBox box = (FlowBox) fragments.get(i);
				BoxUtil.drawBox(g, box);
				if (box instanceof BlockBox) {
					BlockBox compositeBox = (BlockBox) box;
					List list = compositeBox.getFragments();
					for (int j = 0; j < list.size(); j++) {
						g.translate(this.getInsets().left,
								this.getInsets().right);
						BoxUtil.drawBox(g, (FlowBox) list.get(j));
						g.restoreState();
					}
				}
			}
		}
		if (Debug.DEBUG_BASELINE) {
			List fragments = this.getFragmentsForRead();
			for (int i = 0, size = fragments.size(); i < size; i++) {
				Object obj = fragments.get(i);
				if (obj instanceof LineBox) {
					LineBox linebox = (LineBox) obj;
					g.setForegroundColor(ColorConstants.red);
					g.drawLine(linebox._x, linebox._y + linebox.getAscent(),
							linebox._x + linebox.getWidth(), linebox._y
									+ linebox.getAscent());
				}
			}
		}

		if (Debug.DEBUG_BORDERPADDING) {
			if (this.getLayoutManager() instanceof CSSBlockFlowLayout) {
				g.setLineWidth(1);
				Rectangle rect = getBounds().getCopy().crop(getInsets());
				g.setForegroundColor(ColorConstants.green);
				g.drawRectangle(rect);
				g.setForegroundColor(ColorConstants.red);
				g.drawRectangle(getBounds());
			}
		}

		if (Debug.DEBUG_BOX) {
			CSSLayout csslayout = (CSSLayout) this.getLayoutManager();
			if (csslayout.getAbsoluteContext() != null) {
				BlockBox blockbox = csslayout.getAbsoluteContext()._blockBox;
				g.setLineWidth(1);
				g.setForegroundColor(ColorConstants.green);
				g.drawRectangle(blockbox._x, blockbox._y, blockbox.getWidth(),
						blockbox.getHeight());
			}
		}
	}

	/**
	 * Paints this Figure's client area. The client area is typically defined as
	 * the anything inside the Figure's {@link org.eclipse.draw2d.Border} or {@link org.eclipse.draw2d.geometry.Insets}, and
	 * by default includes the children of this Figure. On return, this method
	 * must leave the given Graphics in its initial state.
	 * 
	 * @param graphics
	 *            The Graphics used to paint
	 * @since 2.0
	 */
	protected void paintClientArea(Graphics graphics) {
		if (this.getChildren().isEmpty()) {
			return;
		}

		Object overflow = ICSSPropertyID.VAL_VISIBLE;
		ICSSStyle style = this.getCSSStyle();
		if (style != null) {
			overflow = style.getStyleProperty(ICSSPropertyID.ATTR_OVERFLOW);
		}

		boolean optimizeClip = ICSSPropertyID.VAL_VISIBLE.equals(overflow);

		if (useLocalCoordinates()) {
			graphics.translate(getBounds().x + getInsets().left, getBounds().y
					+ getInsets().top);
			if (!optimizeClip) {
				graphics.clipRect(getClientArea(PRIVATE_RECT));
			}
			graphics.pushState();
			paintChildren(graphics);
			graphics.popState();
			graphics.restoreState();
		} else {
			if (optimizeClip) {
				paintChildren(graphics);
			} else {
				graphics.clipRect(getClientArea(PRIVATE_RECT));
				graphics.pushState();
				paintChildren(graphics);
				graphics.popState();
				graphics.restoreState();
			}
		}
	}

	/**
	 * @param g
	 */
	protected void paintSelection(Graphics g) {
		ICSSStyle style = this.getCSSStyle();
		if (style != null) {
			if (style.isInSelection()) {
				ITagEditInfo editInfo = (ITagEditInfo) style
						.getAdapter(ITagEditInfo.class);
				if (editInfo != null && editInfo.isWidget()) {
					BorderUtil.maskFigure(this, g);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#paintBorder(org.eclipse.draw2d.Graphics)
	 */
	protected void paintBorder(Graphics graphics) {
		CSSLayout layout = (CSSLayout) getLayoutManager();
		if (layout != null && !layout.handlingBorderForBlock()) {
			return;
		}

		ICSSStyle style = this.getCSSStyle();
		if (style != null) {
			CSSBorder border1 = new CSSBorder(this.getCSSStyle());
			border1.paint(this, graphics, NO_INSETS);

			// draw a border for those special elements like <h:form>, etc.
			ITagEditInfo editInfo = (ITagEditInfo) style
					.getAdapter(ITagEditInfo.class);
			if (editInfo != null && editInfo.needBorderDecorator()) {
				BorderUtil.drawBorderDecorator(this, graphics);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.IFigure#getInsets()
	 */
	public Insets getInsets() {
		CSSLayout layout = (CSSLayout) getLayoutManager();
		if (layout != null && !layout.handlingBorderForBlock()) {
			return new Insets();
		}
		ICSSStyle style = this.getCSSStyle();
		if (style != null) {
			return style.getBorderInsets().getAdded(style.getPaddingInsets());
		}
		return new Insets();
	}

	/**
	 * FIXME: need trace the implementation of Figure.invalidate() We want to
	 * just mark this figure as invalid, but don't want to the layout get
	 * invalidated.
	 * 
	 */
	public void invalidate2() {
		if (!isValid())
			return;
		// if (getLayoutManager() != null)
		// getLayoutManager().invalidate();
		setValid(false);

	}

}
