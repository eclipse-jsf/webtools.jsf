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
package org.eclipse.jst.pagedesigner.viewer;

import org.eclipse.draw2d.Viewport;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;

/**
 * @author mengbo
 */
public interface IHTMLGraphicalViewer extends GraphicalViewer {
	public IDOMModel getModel();

	public void ensureRangeSelectionMode();

	public void ensureObjectSelectionMode();

	/**
	 * @return
	 */
	public boolean isInRangeMode();

	public DesignRange getRangeSelection();

	/**
	 * @param position
	 * @param position2
	 */
	public void setRange(DesignPosition position, DesignPosition position2);

	/**
	 * @param position
	 */
	public void setRangeEndPosition(DesignPosition position);

	public Caret getCaret();

	/**
	 * indicate a batch of operations is began, and may result in selection
	 * change. This viewer will only fire a single selection changed event when
	 * this batch of operations finish.
	 */
	public void startSelectionChange();

	/**
	 * batch operation that change the selection finished.
	 * 
	 */
	public void selectionChanged();

	public Viewport getViewport();
}
