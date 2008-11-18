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

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.commands.range.UnapplyStyleCommand;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class NoneStyleAction extends ChangeStyleAction {
	private String[] _applyingStyleTags;

	/**
	 * @param text
	 * @param names
	 * @param image
	 * @param style
	 */
	public NoneStyleAction(String text, String[] names, ImageDescriptor image,
			int style) {
		super(text, "", image, style); //$NON-NLS-1$
		_applyingStyleTags = names;
	}

	protected boolean isApplied(DOMRange range) {
		if (range != null) {
			boolean ordered = range.isOrdered();
			IDOMPosition start = ordered ? range.getStartPosition() : range
					.getEndPosition();
			IDOMPosition end = ordered ? range.getEndPosition() : range
					.getStartPosition();
			Node common = null;
			common = EditModelQuery.getInstance().getCommonAncestor(start, end);
			if (EditModelQuery.hasAncestor(common, _applyingStyleTags, true)) {
				return false;
			}
            return true;
		}
        return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.DesignerToolBarAction#getCommand()
	 */
	protected Command getCommand() {
		UnapplyStyleCommand command = new UnapplyStyleCommand(getViewer(),
				getExpectedTag(), null, null);
		return command;
	}
}
