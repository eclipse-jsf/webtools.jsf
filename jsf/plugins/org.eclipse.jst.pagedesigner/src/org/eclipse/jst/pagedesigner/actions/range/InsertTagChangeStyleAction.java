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
package org.eclipse.jst.pagedesigner.actions.range;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editors.actions.ChangeStyleAction;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class InsertTagChangeStyleAction extends ChangeStyleAction {
	/**
	 * @param text
	 * @param tag 
	 * @param image
	 * @param style
	 */
	public InsertTagChangeStyleAction(String text, String tag,
			ImageDescriptor image, int style) {
		super(text, tag, image, style);
	}

	protected String getExpectedCSSProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	protected String getExpectedCSSPropertyValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    protected void updateState() 
	{
        setEnabled(getDesignRange());
    }

	/**
	 * @param range
	 */
	public void setEnabled(DesignRange range) {
		DOMRange domRange = null;
		// We didn't deal with undo, so only enable is set.
		domRange = new DOMRange(DOMPositionHelper.toDOMPosition(range
				.getStartPosition()), DOMPositionHelper.toDOMPosition(range
				.getEndPosition()));
		if (canRun(domRange)) {
			this.setEnabled(true);
		} else {
			this.setEnabled(false);
		}
	}

	private boolean canRun(DOMRange range) {
		if (range != null) {
			if (EditModelQuery.isSame(range)) {
				return false;
			}
			boolean ordered = range.isOrdered();
			IDOMPosition start = ordered ? range.getStartPosition() : range
					.getEndPosition();
			IDOMPosition end = ordered ? range.getEndPosition() : range
					.getStartPosition();
			Node common = null;
			common = EditModelQuery.getInstance().getCommonAncestor(start, end);
			if (getExpectedTag() == null
					|| EditModelQuery.hasAncestor(common, getExpectedTag(),
							true)) {
				return false;
			}
            return true;
		}
        return false;
	}
}
