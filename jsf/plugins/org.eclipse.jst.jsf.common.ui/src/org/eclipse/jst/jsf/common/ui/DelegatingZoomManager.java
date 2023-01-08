/*******************************************************************************
 * Copyright (c) 2004, 2023 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.ui;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.zoom.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;

/**
 * A delegating ZoomManager.
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

	public void zoomChanged(double zoom) {
		Object[] listeners = zoomListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			((ZoomListener) listeners[i]).zoomChanged(zoom);
		}
	}

	@Override
	public void addZoomListener(ZoomListener listener) {
		zoomListeners.add(listener);
	}

	@Override
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

	@Override
	public boolean canZoomIn() {
		if (null == currentZoomManager) {
			return false;
		}

		return currentZoomManager.canZoomIn();
	}

	@Override
	public boolean canZoomOut() {
		if (null == currentZoomManager) {
			return false;
		}

		return currentZoomManager.canZoomOut();
	}

	@Override
	public double getMaxZoom() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL;
		}

		return currentZoomManager.getMaxZoom();
	}

	@Override
	public double getMinZoom() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL;
		}

		return currentZoomManager.getMinZoom();
	}

	@Override
	public double getNextZoomLevel() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL;
		}

		return currentZoomManager.getNextZoomLevel();
	}

	@Override
	public double getPreviousZoomLevel() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL;
		}

		return currentZoomManager.getPreviousZoomLevel();
	}

	@Override
	public ScalableFigure getScalableFigure() {
		if (null == currentZoomManager) {
			return null;
		}

		return currentZoomManager.getScalableFigure();
	}

	@Override
	public double getUIMultiplier() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL;
		}

		return currentZoomManager.getUIMultiplier();
	}

	@Override
	public Viewport getViewport() {
		if (null == currentZoomManager) {
			return null;
		}

		return currentZoomManager.getViewport();
	}

	@Override
	public double getZoom() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL;
		}

		return currentZoomManager.getZoom();
	}

	@Override
	public String getZoomAsText() {
		if (null == currentZoomManager) {
			return DEFAULT_ZOOM_LEVEL_STRING;
		}

		return currentZoomManager.getZoomAsText();
	}

	@Override
	public double[] getZoomLevels() {
		if (null == currentZoomManager) {
			return new double[] { DEFAULT_ZOOM_LEVEL };
		}

		return currentZoomManager.getZoomLevels();
	}

	@Override
	public String[] getZoomLevelsAsText() {
		if (null == currentZoomManager) {
			return new String[] { DEFAULT_ZOOM_LEVEL_STRING };
		}

		return currentZoomManager.getZoomLevelsAsText();
	}

	@Override
	public void setUIMultiplier(double multiplier) {
		if (null == currentZoomManager) {
			return;
		}

		currentZoomManager.setUIMultiplier(multiplier);
	}

	@Override
	public void setViewLocation(Point p) {
		if (null == currentZoomManager) {
			return;
		}

		currentZoomManager.setViewLocation(p);
	}

	@Override
	public void setZoom(double zoom) {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.setZoom(zoom);
	}

	@Override
	public void setZoomAnimationStyle(int style) {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.setZoomAnimationStyle(style);
	}

	@Override
	public void setZoomAsText(String zoomString) {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.setZoomAsText(zoomString);
	}

	@Override
	public void setZoomLevels(double[] zoomLevels) {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.setZoomLevels(zoomLevels);
	}

	@Override
	public void zoomIn() {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.zoomIn();
	}

	@Override
	public void zoomOut() {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.zoomOut();
	}

	@Override
	public void zoomTo(Rectangle rect) {
		if (null == currentZoomManager) {
			return;
		}
		currentZoomManager.zoomTo(rect);
	}

}
