/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.preference.GEMPreferences;

/**
 * Customize the endpoint edit policy for pageflow links
 *
 */
public class PFLinkEndpointEditPolicy extends ConnectionEndpointEditPolicy {
	/**
	 * 
	 */
	public PFLinkEndpointEditPolicy() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ConnectionEndpointEditPolicy#addSelectionHandles
	 */
	protected void addSelectionHandles() {
		super.addSelectionHandles();
		IPreferenceStore store = EditorPlugin.getDefault().getPreferenceStore();
		int connectionWidth = store.getInt(GEMPreferences.LINE_WIDTH);
		getConnectionFigure().setLineWidth(connectionWidth + 1);
	}

	private PolylineConnection getConnectionFigure() {
		return (PolylineConnection) ((GraphicalEditPart) getHost()).getFigure();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ConnectionEndpointEditPolicy#removeSelectionHandles
	 */
	protected void removeSelectionHandles() {
		super.removeSelectionHandles();
		IPreferenceStore store = EditorPlugin.getDefault().getPreferenceStore();
		int connectionWidth = store.getInt(GEMPreferences.LINE_WIDTH);
		getConnectionFigure().setLineWidth(connectionWidth);
	}
}
