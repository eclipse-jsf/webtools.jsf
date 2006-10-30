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
package org.eclipse.jst.pagedesigner.tools;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.jst.pagedesigner.range.RangeUtil;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * When user press right mouse to do selection, if the selected node is inside
 * current range selection, then don't change selection. This is for better
 * context menu support.
 * 
 * @author mengbo
 * @version 1.5
 */
public class ObjectModeDragTracker extends DragEditPartsTracker {
	/**
	 * @param sourceEditPart
	 */
	public ObjectModeDragTracker(EditPart sourceEditPart) {
		super(sourceEditPart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.SelectEditPartTracker#handleButtonDown(int)
	 */
	protected boolean handleButtonDown(int button) {
		if (button == 3 && isInState(STATE_INITIAL)) {
			EditPart sourcePart = this.getSourceEditPart();
			IHTMLGraphicalViewer viewer = (IHTMLGraphicalViewer) sourcePart
					.getViewer();
			if (viewer != null && viewer.isInRangeMode()) {
				DesignRange range = viewer.getRangeSelection();
				if (range != null && range.isValid()) {
					if (RangeUtil.intersect(range, sourcePart)) {
						return true;
					}
				}
			}
		}
		return super.handleButtonDown(button);
	}
}
