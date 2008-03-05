/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.elementedit;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.ITrinidadConstants;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.validation.caret.Target;
import org.eclipse.jst.pagedesigner.viewer.DefaultDropLocationStrategy;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.IDropLocationStrategy;
import org.w3c.dom.Element;

/**
 * IElementEdit implementation for Trinidad's showDetailItem tag.
 * 
 * @author Ian Trimble - Oracle
 */
public class ShowDetailItemElementEdit extends DefaultTrinidadCoreElementEdit {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit#handleModelChange(org.w3c.dom.Element, org.eclipse.jst.pagedesigner.parts.ElementEditPart, boolean)
	 */
	@Override
	public boolean handleModelChange(
			Element ele, ElementEditPart part, boolean recursive) {
		boolean handled = false;
		EditPart parentPart = part.getParent();
		if (parentPart instanceof ElementEditPart) {
			((ElementEditPart)parentPart).refreshModelChange(recursive);
			handled = true;
		}
		return handled;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit#getDropRequestorLocationStrategy(org.eclipse.jst.jsf.common.dom.TagIdentifier, org.eclipse.gef.EditPartViewer)
	 */
	@Override
	public IDropLocationStrategy getDropRequestorLocationStrategy(
			TagIdentifier tag, EditPartViewer viewer) {
		return new ShowDetailItemDropLocationStrategy(viewer);
	}

	/**
	 * Extends DefaultDropLocationStrategy.
	 * 
	 * @author Ian Trimble - Oracle
	 */
	private static class ShowDetailItemDropLocationStrategy extends DefaultDropLocationStrategy {

		/**
		 * Instantiates an instance.
		 * 
		 * @param viewer EditPartViewer instance.
		 */
		public ShowDetailItemDropLocationStrategy(EditPartViewer viewer) {
			super(viewer);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jst.pagedesigner.viewer.DefaultDropLocationStrategy#calculateDesignPosition(org.eclipse.gef.EditPart, org.eclipse.draw2d.geometry.Point, org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator)
		 */
		@Override
		public DesignPosition calculateDesignPosition(
				EditPart host, Point p, IPositionMediator validator) {
			DesignPosition position = null;
			//check if host is editable or get nearest editable ancestor
			host = validator.getEditableContainer(new Target(host));
			if (host instanceof ElementEditPart) {
				final TagIdentifier hostTagId =
					((ElementEditPart)host).getTagIdentifier();
				if (ITrinidadConstants.TAG_IDENTIFIER_PANELTABBED.isSameTagType(hostTagId)) {
					final int showDetailItemCount = host.getChildren().size();
					position = new DesignPosition(host, showDetailItemCount);
					if (!validator.isValidPosition(position)) {
						position = null;
					}
				}
			}
			return position;
		}

	}

}
