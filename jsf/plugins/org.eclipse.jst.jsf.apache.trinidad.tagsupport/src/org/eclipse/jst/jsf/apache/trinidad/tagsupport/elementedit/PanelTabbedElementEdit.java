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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.ITrinidadConstants;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.TrinidadTagSupportActivator;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.editpolicies.ElementResizableEditPolicy;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.tools.ObjectModeDragTracker;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * IElementEdit implementation for Trinidad's panelTabbed tag.
 * 
 * @author Ian Trimble - Oracle
 */
public class PanelTabbedElementEdit extends DefaultTrinidadCoreElementEdit {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit#createEditPolicies(org.eclipse.jst.pagedesigner.parts.ElementEditPart)
	 */
	@Override
	public void createEditPolicies(ElementEditPart part) {
		part.installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new PanelTabbedResizePolicy());
	}

	static class PanelTabbedResizePolicy extends ElementResizableEditPolicy {

		/* (non-Javadoc)
		 * @see org.eclipse.jst.pagedesigner.editpolicies.ElementResizableEditPolicy#getSelectionDragTracker(org.eclipse.gef.requests.LocationRequest)
		 */
		@Override
		public DragTracker getSelectionDragTracker(LocationRequest request) {
			return getSelectionTracker(request);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jst.pagedesigner.editpolicies.ElementResizableEditPolicy#getSelectionTracker(org.eclipse.gef.requests.LocationRequest)
		 */
		@Override
		protected DragTracker getSelectionTracker(LocationRequest request) {
			final GraphicalEditPart part = (GraphicalEditPart)getHost();
			if (part instanceof NodeEditPart) {
				Node node = ((NodeEditPart)part).getDOMNode();
				if (getShowDetailItemCount(node) > 0) {
					ObjectModeDragTracker dragTracker = new ObjectModeDragTracker(getHost()) {
						protected boolean handleButtonDown(int button) {
							if (button == 1) {
								TrinidadTagSupportActivator.logInfo(
										"Tab clicked: index == " +
										String.valueOf(getTabIndex(getLocation())));
							}
							return super.handleButtonDown(button);
						}
					};
					return dragTracker;
				}
			}
			return new ObjectModeDragTracker(getHost());
		}

		private int getTabIndex(Point location) {
			int tabIndex = -1;
			final GraphicalEditPart part = (GraphicalEditPart)getHost();
			if (part instanceof NodeEditPart) {
				final Node node = ((NodeEditPart)part).getDOMNode();
				final IFigure figure = part.getFigure();
				Point relLocation = location.getCopy();
				figure.translateToRelative(relLocation);
				final int showDetailItemCount = getShowDetailItemCount(node);
				if (showDetailItemCount > 0) {
					final int tabWidth = figure.getBounds().width / showDetailItemCount;
					tabIndex = relLocation.x / tabWidth;
					if (tabIndex > showDetailItemCount - 1) {
						tabIndex = showDetailItemCount - 1;
					}
				}
			}
			return tabIndex;
		}

		private int getShowDetailItemCount(Node node) {
			int count = 0;
			if (node != null) {
				NodeList childNodes = node.getChildNodes();
				for (int i = 0; i < childNodes.getLength(); i++) {
					Node childNode = childNodes.item(i);
					if (childNode instanceof Element) {
						if (ITrinidadConstants.TAG_IDENTIFIER_SHOWDETAILITEM.isSameTagType(
								TagIdentifierFactory.createDocumentTagWrapper((Element)childNode))) {
							count++;
						}
					}
				}
			}
			return count;
		}
	}

}
