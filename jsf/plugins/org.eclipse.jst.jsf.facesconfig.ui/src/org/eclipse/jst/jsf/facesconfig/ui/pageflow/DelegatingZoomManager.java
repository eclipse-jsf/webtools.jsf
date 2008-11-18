/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow;

// import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;

/**
 * A delegating ZoomManager.
 * 
 */
public class DelegatingZoomManager extends ZoomManager implements ZoomListener {
	/** Default string or double value of zoom level */
	private static final String DEFAULT_ZOOM_LEVEL_STRING = "100%"; //$NON-NLS-1$

	private static final double DEFAULT_ZOOM_LEVEL = 1;

	/** the current ZoomManager all work is delegated to */
	private ZoomManager currentZoomManager = null;

	/** listeners of zoom */
	private ListenerList zoomListeners = new ListenerList(
			ListenerList.IDENTITY);

	/**
	 * Creates a new DelegatingZoomManager instance.
	 */
	public DelegatingZoomManager() {
		super((ScalableFigure) null, (Viewport) null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomListener#zoomChanged(double)
	 */
	public void zoomChanged(double zoom) {
		Object[] listeners = zoomListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			((ZoomListener) listeners[i]).zoomChanged(zoom);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#addZoomListener(ZoomListener)
	 */
	public void addZoomListener(ZoomListener listener) {
		zoomListeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#removeZoomListener(oZoomListener)
	 */
	public void removeZoomListener(ZoomListener listener) {
		zoomListeners.remove(listener);
	}

	/**
	 * Sets the ZoomManager all work should be delegated to.
	 * 
	 * @param zoomManager
	 */
	public void setCurrentZoomManager(ZoomManager zoomManager) {
		if (null != currentZoomManager) {
			currentZoomManager.removeZoomListener(this);
		}

		currentZoomManager = zoomManager;
		if (null != currentZoomManager) {
			currentZoomManager.addZoomListener(this);
			zoomChanged(currentZoomManager.getZoom());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#canZoomIn()
	 */
	public boolean canZoomIn() {
		if (null == currentZoomManager) {
			return false;
		}

		return currentZoomManager.canZoomIn();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#canZoomOut()
	 */
	public boolean canZoomOut() {
		if (null == currentZoomManager) {
			return false;
		}

		return currentZoomManager.canZoomOut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#getMaxZoom()
	 */
	public double getMaxZoom() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL;
		}

		return currentZoomManager.getMaxZoom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#getMinZoom()
	 */
	public double getMinZoom() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL;
		}

		return currentZoomManager.getMinZoom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#getNextZoomLevel()
	 */
	public double getNextZoomLevel() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL;
		}

		return currentZoomManager.getNextZoomLevel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#getPreviousZoomLevel()
	 */
	public double getPreviousZoomLevel() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL;
		}

		return currentZoomManager.getPreviousZoomLevel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#getScalableFigure()
	 */
	public ScalableFigure getScalableFigure() {
		if (null == currentZoomManager) {
			return null;
		}

		return currentZoomManager.getScalableFigure();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#getUIMultiplier()
	 */
	public double getUIMultiplier() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL;
		}

		return currentZoomManager.getUIMultiplier();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#getViewport()
	 */
	public Viewport getViewport() {
		if (null == currentZoomManager) {
			return null;
		}

		return currentZoomManager.getViewport();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#getZoom()
	 */
	public double getZoom() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL;
		}

		return currentZoomManager.getZoom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#getZoomAsText()
	 */
	public String getZoomAsText() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL_STRING;
		}

		return currentZoomManager.getZoomAsText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#getZoomLevels()
	 */
	public double[] getZoomLevels() {
		if (null == currentZoomManager) {
			return new double[] { DEFAULT_ZOOM_LEVEL };
		}

		return currentZoomManager.getZoomLevels();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#getZoomLevelsAsText()
	 */
	public String[] getZoomLevelsAsText() {
		if (null == currentZoomManager) {
			return new String[] { DEFAULT_ZOOM_LEVEL_STRING };
		}

		return currentZoomManager.getZoomLevelsAsText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#setUIMultiplier(double)
	 */
	public void setUIMultiplier(double multiplier) {
		if (null == currentZoomManager) {
			return;
		}

		currentZoomManager.setUIMultiplier(multiplier);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#setViewLocation(Point)
	 */
	public void setViewLocation(Point p) {
		if (null == currentZoomManager) {
			return;
		}

		currentZoomManager.setViewLocation(p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#setZoom(double)
	 */
	public void setZoom(double zoom) {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.setZoom(zoom);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#setZoomAnimationStyle(int)
	 */
	public void setZoomAnimationStyle(int style) {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.setZoomAnimationStyle(style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#setZoomAsText(String)
	 */
	public void setZoomAsText(String zoomString) {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.setZoomAsText(zoomString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#setZoomLevels(double[])
	 */
	public void setZoomLevels(double[] zoomLevels) {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.setZoomLevels(zoomLevels);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#zoomIn()
	 */
	public void zoomIn() {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.zoomIn();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#zoomOut()
	 */
	public void zoomOut() {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.zoomOut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ZoomManager#zoomTo(Rectangle)
	 */
	public void zoomTo(Rectangle rect) {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.zoomTo(rect);
	}

}
