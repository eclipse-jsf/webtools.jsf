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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.ITrinidadConstants;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.TrinidadUtils;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.editpolicies.DragMoveEditPolicy;
import org.eclipse.jst.pagedesigner.editpolicies.ElementResizableEditPolicy;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.tools.ObjectModeDragTracker;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.DefaultPositionRule;
import org.eclipse.jst.pagedesigner.validation.caret.DnDPositionValidator;
import org.eclipse.jst.pagedesigner.validation.caret.DropActionData;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.validation.caret.Target;
import org.eclipse.jst.pagedesigner.validation.caret.DropActionData.DropData;
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
		part.installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new PanelTabbedElementResizableEditPolicy());
		part.installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new PanelTabbedDragMoveEditPolicy());
	}

	/**
	 * Extends ElementResizableEditPolicy.
	 * 
	 * @author Ian Trimble - Oracle
	 */
	public static class PanelTabbedElementResizableEditPolicy extends ElementResizableEditPolicy {

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
				final Node node = ((NodeEditPart)part).getDOMNode();
				if (getShowDetailItemCount(node) > 0) {
					ObjectModeDragTracker dragTracker = new ObjectModeDragTracker(getHost()) {
						protected boolean handleButtonDown(int button) {
							if (button == 1) {
								final int tabIndex = getTabIndex(getLocation());
								final EditPart editPart = getSourceEditPart();
								if (editPart instanceof ElementEditPart) {
									final Node node = ((ElementEditPart)editPart).getDOMNode();
									if (TrinidadUtils.setCurrentChildIndex(node, tabIndex)) {
										((ElementEditPart)editPart).refresh(true);
									}
								}
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

	/**
	 * Extends DragMoveEditPolicy.
	 * 
	 * @author Ian Trimble - Oracle
	 */
	public static class PanelTabbedDragMoveEditPolicy extends DragMoveEditPolicy {

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jst.pagedesigner.editpolicies.DropEditPolicy#createDropChildValidator(org.eclipse.gef.requests.DropRequest)
		 */
		@Override
		protected IPositionMediator createDropChildValidator(DropRequest r) {
			DropData dropData = createDropData(r);
			if (dropData != null) {
				DnDPositionValidator validator = 
					new DnDPositionValidator(new DropActionData(
							ActionData.COMPONENT_MOVE, dropData));
				validator.addRule(new OnlyShowDetailItemsRule(validator.getActionData()));
				return validator;
			}
			return null;
		}

		private static class OnlyShowDetailItemsRule extends DefaultPositionRule {

			/**
			 * Instantiates an instance.
			 * 
			 * @param actionData ActionData instance.
			 */
			public OnlyShowDetailItemsRule(ActionData actionData) {
				super(actionData);
			}

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.jst.pagedesigner.validation.caret.DefaultPositionRule#isEditable(org.eclipse.jst.pagedesigner.validation.caret.Target)
			 */
			@Override
			public boolean isEditable(Target target) {
				if (ITrinidadConstants.TAG_IDENTIFIER_PANELTABBED.isSameTagType(
						target.getTagWrapper())) {
					return isDataDroppable();
				}
				return true;
			}

			private boolean isDataDroppable() {
				ActionData actionData = getActionData();
				if (actionData instanceof DropActionData) {
					DropActionData dropActionData = (DropActionData)actionData;
					TagIdentifier tagIdentifier = 
						(TagIdentifier)dropActionData.getDropData().getTagIdentifiers().get(0);
					if (ITrinidadConstants.TAG_IDENTIFIER_SHOWDETAILITEM.isSameTagType(
							tagIdentifier)) {
						return true;
					}
				}
				return false;
			}
		}
	}

}
