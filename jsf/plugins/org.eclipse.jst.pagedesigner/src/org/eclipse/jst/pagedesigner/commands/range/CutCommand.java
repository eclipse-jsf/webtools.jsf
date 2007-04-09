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
package org.eclipse.jst.pagedesigner.commands.range;

import org.eclipse.jst.pagedesigner.commands.CommandResources;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 */
public class CutCommand extends RangeModeCommand {
	/**
	 * @param viewer
	 */
	public CutCommand(IHTMLGraphicalViewer viewer) {
		super(CommandResources.getString("CutCommand.Label.Cut"), viewer); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.RangeModeCommand#doRangeExecute(org.eclipse.jst.pagedesigner.dom.DOMRange)
	 */
	protected DOMRange doRangeExecute(DOMRange selection) {
		DesignEdit edit = new CutEdit(selection, getViewer());
		if (EditModelQuery.isSame(selection)) {
			return null;
		}
        if (edit.perform()) {
        	return new DOMRange(edit.getOperationPosition(), edit
        			.getOperationPosition());
        }
        return selection;
	}
}
