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
			if (getTabIndex(request.getLocation()) > -1) {
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
			return super.getSelectionTracker(request);
		}

		private int getTabIndex(Point location) {
			int tabIndex = -1;

			final GraphicalEditPart part = (GraphicalEditPart)getHost();

			//determine if tabs are visible above and/or below
			boolean tabsAbove = false;
			boolean tabsBelow = false;
			if (part instanceof NodeEditPart) {
				Node node = ((NodeEditPart)part).getDOMNode();
				int showDetailItemCount = getShowDetailItemCount(node);
				if (showDetailItemCount > 0) {
					if (node instanceof Element) {
						Element element = (Element)node;
						String attrPosition = element.getAttribute("position"); //$NON-NLS-1$
						if (attrPosition != null) {
							if (attrPosition.equalsIgnoreCase("above")) { //$NON-NLS-1$
								tabsAbove = true;
							} else if (attrPosition.equalsIgnoreCase("below")) { //$NON-NLS-1$
								tabsBelow = true;
							} else {
								tabsAbove = true;
								tabsBelow = true;
							}
						} else {
							tabsAbove = true;
							tabsBelow = true;
						}
					}

					//determine which tab was clicked (if any)
					final IFigure figure = part.getFigure();
					Point relLocation = location.getCopy();
					figure.translateToRelative(relLocation);
					if (
							(tabsAbove && relLocation.y <= 10) ||
							(tabsBelow && figure.getBounds().height - relLocation.y <= 10)) {
						//a tab was clicked - now determine which one
						int tabWidth = figure.getBounds().width / showDetailItemCount;
						tabIndex = relLocation.x / tabWidth;
						if (tabIndex > showDetailItemCount - 1) {
							tabIndex = showDetailItemCount - 1;
						}
					}

				}
			}

			return tabIndex;
		}

		private int getShowDetailItemCount(Node panelTabbedNode) {
			int count = 0;
			if (panelTabbedNode != null) {
				NodeList childNodes = panelTabbedNode.getChildNodes();
				for (int i = 0; i < childNodes.getLength(); i++) {
					Node childNode = childNodes.item(i);
					if (childNode instanceof Element) {
						Element childElement = (Element)childNode;
						if (
								childElement.getNamespaceURI().equalsIgnoreCase(ITrinidadConstants.TLD_CORE_URI) &&
								childElement.getLocalName().equalsIgnoreCase(ITrinidadConstants.TAG_SHOWDETAILITEM)) {
							count++;
						}
					}
				}
			}
			return count;
		}
	}

}
