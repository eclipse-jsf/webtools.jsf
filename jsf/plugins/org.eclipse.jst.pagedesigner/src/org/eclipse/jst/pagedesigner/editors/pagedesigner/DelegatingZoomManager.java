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
package org.eclipse.jst.pagedesigner.editors.pagedesigner;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;

/**
 * A delegating ZoomManager.
 */
public class DelegatingZoomManager extends ZoomManager implements ZoomListener {
	/** the current ZoomManager all work is delegated to */
	private final static int DEFAULT_ZOOM = 1;

	private final static String ZOOM_AS_TEXT = "100%"; //$NON-NLS-1$

	private final static String ZOOM_LEVEL_AS_TEXT = "100%"; //$NON-NLS-1$

	private ZoomManager _currentZoomManager;

	/** listeners */
	private ListenerList _zoomListeners = new ListenerList(ListenerList.IDENTITY);

	/**
	 * Creates a new DelegatingZoomManager instance.
	 */
	public DelegatingZoomManager() {
		super((ScalableFigure) null, (Viewport) null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomListener#zoomChanged(double)
	 */
	public void zoomChanged(double zoom) {
		Object[] listeners = _zoomListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			((ZoomListener) listeners[i]).zoomChanged(zoom);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#addZoomListener(org.eclipse.gef.editparts.ZoomListener)
	 */
	public void addZoomListener(ZoomListener listener) {
		_zoomListeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#removeZoomListener(org.eclipse.gef.editparts.ZoomListener)
	 */
	public void removeZoomListener(ZoomListener listener) {
		_zoomListeners.remove(listener);
	}

	/**
	 * Sets the ZoomManager all work should be delegated to.
	 * 
	 * @param zoomManager
	 */
	public void setCurrentZoomManager(ZoomManager zoomManager) {
		if (null != _currentZoomManager) {
			_currentZoomManager.removeZoomListener(this);
		}

		_currentZoomManager = zoomManager;
		if (null != _currentZoomManager) {
			_currentZoomManager.addZoomListener(this);
			zoomChanged(_currentZoomManager.getZoom());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#canZoomIn()
	 */
	public boolean canZoomIn() {
		if (null == _currentZoomManager) {
			return false;
		}
		return _currentZoomManager.canZoomIn();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#canZoomOut()
	 */
	public boolean canZoomOut() {
		if (null == _currentZoomManager) {
			return false;
		}
		return _currentZoomManager.canZoomOut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#getMaxZoom()
	 */
	public double getMaxZoom() {
		if (null == _currentZoomManager) {
			return DEFAULT_ZOOM;
		}

		return _currentZoomManager.getMaxZoom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#getMinZoom()
	 */
	public double getMinZoom() {
		if (null == _currentZoomManager) {
			return DEFAULT_ZOOM;
		}
		return _currentZoomManager.getMinZoom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#getNextZoomLevel()
	 */
	public double getNextZoomLevel() {
		if (null == _currentZoomManager) {
			return DEFAULT_ZOOM;
		}
		return _currentZoomManager.getNextZoomLevel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#getPreviousZoomLevel()
	 */
	public double getPreviousZoomLevel() {
		if (null == _currentZoomManager) {
			return DEFAULT_ZOOM;
		}
		return _currentZoomManager.getPreviousZoomLevel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#getScalableFigure()
	 */
	public ScalableFigure getScalableFigure() {
		if (null == _currentZoomManager) {
			return null;
		}

		return _currentZoomManager.getScalableFigure();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#getUIMultiplier()
	 */
	public double getUIMultiplier() {
		if (null == _currentZoomManager) {
			return DEFAULT_ZOOM;
		}

		return _currentZoomManager.getUIMultiplier();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#getViewport()
	 */
	public Viewport getViewport() {
		if (null == _currentZoomManager) {
			return null;
		}

		return _currentZoomManager.getViewport();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#getZoom()
	 */
	public double getZoom() {
		if (null == _currentZoomManager) {
			return DEFAULT_ZOOM;
		}

		return _currentZoomManager.getZoom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#getZoomAsText()
	 */
	public String getZoomAsText() {
		if (null == _currentZoomManager) {
			return ZOOM_AS_TEXT;
		}
		//$NON-NLS-1$

		return _currentZoomManager.getZoomAsText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#getZoomLevels()
	 */
	public double[] getZoomLevels() {
		if (null == _currentZoomManager) {
			return new double[] { DEFAULT_ZOOM };
		}

		return _currentZoomManager.getZoomLevels();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#getZoomLevelsAsText()
	 */
	public String[] getZoomLevelsAsText() {
		if (null == _currentZoomManager) {
			return new String[] { ZOOM_LEVEL_AS_TEXT };
		}

		return _currentZoomManager.getZoomLevelsAsText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#setUIMultiplier(double)
	 */
	public void setUIMultiplier(double multiplier) {
		if (null == _currentZoomManager) {
			return;
		}

		_currentZoomManager.setUIMultiplier(multiplier);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#setViewLocation(org.eclipse.draw2d.geometry.Point)
	 */
	public void setViewLocation(Point p) {
		if (null == _currentZoomManager) {
			return;
		}

		_currentZoomManager.setViewLocation(p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#setZoom(double)
	 */
	public void setZoom(double zoom) {
		if (null == _currentZoomManager) {
			return;
		}

		_currentZoomManager.setZoom(zoom);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#setZoomAnimationStyle(int)
	 */
	public void setZoomAnimationStyle(int style) {
		if (null == _currentZoomManager) {
			return;
		}

		_currentZoomManager.setZoomAnimationStyle(style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#setZoomAsText(java.lang.String)
	 */
	public void setZoomAsText(String zoomString) {
		if (null == _currentZoomManager) {
			return;
		}

		_currentZoomManager.setZoomAsText(zoomString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#setZoomLevels(double[])
	 */
	public void setZoomLevels(double[] zoomLevels) {
		if (null == _currentZoomManager) {
			return;
		}

		_currentZoomManager.setZoomLevels(zoomLevels);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#zoomIn()
	 */
	public void zoomIn() {
		if (null == _currentZoomManager) {
			return;
		}

		_currentZoomManager.zoomIn();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#zoomOut()
	 */
	public void zoomOut() {
		if (null == _currentZoomManager) {
			return;
		}

		_currentZoomManager.zoomOut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.ZoomManager#zoomTo(org.eclipse.draw2d.geometry.Rectangle)
	 */
	public void zoomTo(Rectangle rect) {
		if (null == _currentZoomManager) {
			return;
		}

		_currentZoomManager.zoomTo(rect);
	}

}
