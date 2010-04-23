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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.layout.CSSTextFigure;
import org.eclipse.jst.pagedesigner.css2.layout.FlowBox;
import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.parts.TextEditPart;
import org.eclipse.jst.pagedesigner.validation.caret.IMovementMediator;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.validation.caret.Target;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class EditPartPositionHelper {
//	private final static Logger _log = PDPlugin
//			.getLogger(EditPartPositionHelper.class);

	/**
	 * Add something to current
	 * 
	 * @param lineBox
	 * @param host
	 * @param point
	 * @param validator
	 */
	private static void addToCurrentLine(FlowBoxLine lineBox, EditPart host,
			Point point, IPositionMediator validator) {
		Node node = Target.resolveNode(host);
		if (!(node == null || EditModelQuery.isDocument(node))) {
			// Either it is referencable or is editable.
			if (validator.isValidPosition(new DOMRefPosition(node, true))
					|| //
					validator
							.isValidPosition((new DOMRefPosition(node, false)))
					|| //
					validator.isValidPosition(new DOMPosition(node, 0))) {
				lineBox.addLayoutPart(host, point);
			}
		}
	}

	/**
	 * @param position 
	 * @return null means failed to convert to rect.
	 */
	public static Rectangle convertToAbsoluteCaretRect(DesignPosition position) {
		Rectangle ret = null;
		try {
			final int CARET_OFFSET = 1;
			if (position == null || !position.isValid()) {
				return null;
			}
			EditPart containerEditPart = position.getContainerPart();
			if (containerEditPart instanceof TextEditPart) {
				CSSTextFigure figure = (CSSTextFigure) ((TextEditPart) containerEditPart)
						.getFigure();
				ret = figure.calculateCaretPosition(position.getOffset());
				figure.translateToAbsolute(ret);
				ret.width = CaretUpdater.CARET_WIDTH;
			} else {
				int offset = position.getOffset();
				// there is no child
				if (containerEditPart.getChildren().isEmpty()
						|| LayoutPart.getConcretePart(containerEditPart) == null) {
					IFigure figure = ((GraphicalEditPart) containerEditPart)
							.getFigure();
					Rectangle bounds = figure.getBounds();
					if (figure instanceof CSSFigure) {
						List fragments = ((CSSFigure) figure)
								.getFragmentsForRead();
						if (fragments.size() > 0) {
							FlowBox box = (FlowBox) fragments.get(fragments
									.size() - 1);
							bounds = LayoutPart.getBounds(box);
						}
					}

					ret = new Rectangle(bounds.x + CARET_OFFSET, bounds.y,
							CaretUpdater.CARET_WIDTH, bounds.height);

					figure.translateToAbsolute(ret);
				} else if (offset >= 0
						&& offset <= containerEditPart.getChildren().size()) {
					ret = getRefRect(position);
				}
			}
		} catch (Exception e) {
			// This should never happen, we catch here for later analysis.
			// _log.debug("Error in caret rect resolving", e);
			ret = new Rectangle(0, 0, 0, 0);
		}
		if (ret == null) {
			ret = new Rectangle(0, 0, 0, 0);
		}
		return ret;
	}

	/**
	 * This method will create FlowBoxLine to calculate the accurate parts.
	 * 
	 * @param host
	 * @param p
	 * @param validator 
	 * @return the design position
	 */
	public static DesignPosition findEditPartPosition(EditPart host, Point p,
			IPositionMediator validator) {
		try {
			host = validator.getEditableContainer(new Target(host));
			FlowBoxLine boxLine = new FlowBoxLine(
					new Rectangle(p.x, p.y, 0, 0), validator, p);
			DesignPosition position = innerFindEditPartPosition(host, host, p,
					boxLine, validator);
			if (position == null) {
				position = innerFindEditPartPosition(host, host, p, boxLine,
						validator);
				if (position == null) {
					EditPart part = boxLine.getClosestPart();
					if (part != null) {
						LayoutPart layoutPart = new LayoutPart(part, p);
						position = layoutPart.resolvePosition(validator);
					}
				}
			}
			return position;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * This function finds the position, if there is one which is widget or text
	 * and it contains p, or there is not such widget, then boxLine will return
	 * the widget that are in a same line which contains p;
	 * @param rootHost 
	 * @param host 
	 * 
	 * @param p
	 * @param boxLine 
	 * @param validator 
	 * @return the design position
	 */
    //TODO: needs refactoring
	public static DesignPosition innerFindEditPartPosition(EditPart rootHost,
			EditPart host, Point p, FlowBoxLine boxLine,
			IPositionMediator validator) {
		Target target = new Target(host);
		LayoutPart lPart = new LayoutPart(host, p);
		// text
		if (host instanceof TextEditPart) {
			if (lPart.contains(p)) {
				DesignPosition position = null;
				// see if the point is within string.
				position = findTextEditPartPosition((TextEditPart) host, p);
				if (position == null) {
					addToCurrentLine(boxLine, host, p, validator);
				}
				// found?!!
				return position;
			}
            addToCurrentLine(boxLine, host, p, validator);
            return null;
		}
		// widget
		else if (isWidget(host)) {
			if (lPart.contains(p)
					&& (validator.isValidPosition(new DOMRefPosition(target
							.getNode(), true)) || //
					validator.isValidPosition((new DOMRefPosition(target
							.getNode(), false))))) {
				if (IHTMLConstants.TAG_BR.equalsIgnoreCase(Target.resolveNode(
						host).getNodeName())) {
					return new DesignRefPosition(host, lPart.isBeforePoint(p));
				}
                return new DesignRefPosition(host, lPart.isBeforePoint(p)
                		|| !lPart.atLeftPart(p));
			}
            addToCurrentLine(boxLine, host, p, validator);
		} else {
			// root host. we always support it has editable area.
			if (host == rootHost) {
				if (host.getChildren().size() > 0) {
					List children = host.getChildren();
					for (int i = 0, size = children.size(); i < size; i++) {
						GraphicalEditPart child = (GraphicalEditPart) children
								.get(i);
						DesignPosition position = innerFindEditPartPosition(
								rootHost, child, p, boxLine, validator);
						if (position != null) {
							return position;
						}
					}
				}
				if (boxLine.getPartsList().size() == 0) {
					if (lPart.contains(p)) {
						// found!!!
						return new DesignPosition(host, 0);
					}
					addToCurrentLine(boxLine, host, p, validator);
				}
			}
			// container
			else {
				// can't edit it.
				if (!validator.hasEditableArea(target)) {
					if (lPart.contains(p) && //
							(validator.isValidPosition(new DesignRefPosition(
									target.getPart(), true)) || //
							validator.isValidPosition(new DesignRefPosition(
									target.getPart(), false)))) {
						return new DesignRefPosition(host, lPart
								.isBeforePoint(p)
								|| !lPart.atLeftPart(p));
					}
                    addToCurrentLine(boxLine, host, p, validator);
				}
				// can edit
				else {
					// contains p
					if (lPart.contains(p) || //
							(!validator.isValidPosition(new DesignRefPosition(
									target.getPart(), true)) && //
							!validator.isValidPosition(new DesignRefPosition(
									target.getPart(), false)))) {
						if (host.getChildren().size() > 0) {
							List children = host.getChildren();
							for (int i = 0, size = children.size(); i < size; i++) {
								GraphicalEditPart child = (GraphicalEditPart) children
										.get(i);
								DesignPosition position = innerFindEditPartPosition(
										rootHost, child, p, boxLine, validator);
								if (position != null) {
									return position;
								}
							}
						} else {
							// we put the container which is empty here.
							if (lPart.contains(p)) {
								// found!!!
								return new DesignPosition(host, 0);
							}
                            addToCurrentLine(boxLine, host, p, validator);
						}
					}
					// not contains p
					else {
						addToCurrentLine(boxLine, host, p, validator);
					}
				}
			}
		}
		return null;
	}

	/**
	 * Similar to findEditPartPositionConstrained, this method is used to
	 * vertically move caret.
	 * 
	 * @param host
	 * @param p
	 * @param validator 
	 * @return the design position
	 */
	public static DesignPosition findEditPartPositionConstrained(EditPart host,
			Point p, IMovementMediator validator) {
		try {
			FlowBoxLine boxLine = new FlowBoxLine(
					new Rectangle(p.x, p.y, 0, 0), validator, p);
			DesignPosition position = innerFindEditPartPositionConstrained(
					host, host, p, boxLine, validator);
			if (position == null) {
				position = innerFindEditPartPositionConstrained(host, host, p,
						boxLine, validator);
				if (position == null) {
					EditPart part = boxLine.getClosestPart();
					if (part != null) {
						LayoutPart layoutPart = new LayoutPart(part, p);
						position = layoutPart.resolvePosition(validator);
					}
				}
			}
			return position;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * This method is used for move up/down, except for using tactics to deal
	 * with container, this method is similar to findEditPartPosition.
	 * 
	 * @param rootHost 
	 * @param host 
	 * @param p
	 * @param boxLine 
	 * @param validator 
	 * @return the design position
	 */
    // TODO: needs refactoring
	public static DesignPosition innerFindEditPartPositionConstrained(
			EditPart rootHost, EditPart host, Point p, FlowBoxLine boxLine,
			IMovementMediator validator) {
		Target target = new Target(host);
		LayoutPart lPart = new LayoutPart(host, p);
		// text
		if (host instanceof TextEditPart) {
			if (lPart.contains(p)) {
				DesignPosition position = null;
				// see if the point is within string.
				position = findTextEditPartPosition((TextEditPart) host, p);
				if (position == null) {
					addToCurrentLine(boxLine, host, p, validator);
				}
				// found?!!
				return position;
			}
            addToCurrentLine(boxLine, host, p, validator);
            return null;
		}
		// widget
		else if (isWidget(host)) {
			if (lPart.contains(p)) {
				// found!!!
				if (IHTMLConstants.TAG_BR.equalsIgnoreCase(Target.resolveNode(
						host).getNodeName())) {
					return new DesignRefPosition(host, lPart.isBeforePoint(p));
				}
                return new DesignRefPosition(host, lPart.isBeforePoint(p)
                		|| !lPart.atLeftPart(p));
			}
            addToCurrentLine(boxLine, host, p, validator);
		} else {
			// root host. we always support it has editable area.
			if (host == rootHost) {
				if (host.getChildren().size() > 0) {
					List children = host.getChildren();
					for (int i = 0, size = children.size(); i < size; i++) {
						GraphicalEditPart child = (GraphicalEditPart) children
								.get(i);
						DesignPosition position = innerFindEditPartPositionConstrained(
								rootHost, child, p, boxLine, validator);
						if (position != null) {
							return position;
						}
					}
				} else {
					if (lPart.contains(p)) {
						// found!!!
						return new DesignPosition(host, 0);
					}
					addToCurrentLine(boxLine, host, p, validator);
				}
			}
			// container
			else {
				// can't edit it.
				if (!validator.hasEditableArea(target)
						|| !validator.allowsMoveIn(target)) {
					if (validator.canReference(target, true)
							|| validator.canReference(target, false)) {
						if (lPart.contains(p)) {
							return new DesignRefPosition(host, lPart
									.isBeforePoint(p)
									|| !lPart.atLeftPart(p));
						}
                        addToCurrentLine(boxLine, host, p, validator);
					}
				}
				// can edit
				else {
					// contains p
					if (lPart.contains(p)) {
						if (host.getChildren().size() > 0) {
							List children = host.getChildren();
							for (int i = 0, size = children.size(); i < size; i++) {
								GraphicalEditPart child = (GraphicalEditPart) children
										.get(i);
								DesignPosition position = innerFindEditPartPositionConstrained(
										rootHost, child, p, boxLine, validator);
								if (position != null) {
									return position;
								}
							}
						} else {
							// we put the container which is empty here.
							if (lPart.contains(p)) {
								// found!!!
								return new DesignPosition(host, 0);
							}
                            addToCurrentLine(boxLine, host, p, validator);
						}
					}
					// not contains p
					else {
						addToCurrentLine(boxLine, host, p, validator);
					}
				}
			}
		}
		return null;

	}

	// /**
	// * @param host
	// * @param p
	// * @return
	// */
	// private static DesignPosition
	// findTextEditPartPositionAdjacent(TextEditPart host, Point p)
	// {
	// if (host.getFigure() instanceof CSSTextFigure)
	// {
	// CSSTextFigure figure = (CSSTextFigure) host.getFigure();
	// // make a copy to not destroy the original o
	// p = p.getCopy();
	// figure.translateToRelative(p);
	// int offset = figure.getNewInsertionOffset(p);
	// if (offset >= 0)
	// {
	// return new DesignPosition(host, offset);
	// }
	// else
	// {
	// return null;
	// }
	// }
	// else
	// {
	// // should not happen.
	// return new DesignPosition(host, 0);
	// }
	// }

	/**
	 * @param host
	 * @param p
	 * @return
	 */
	private static DesignPosition findTextEditPartPosition(TextEditPart host,
			Point p) {
		if (host.getFigure() instanceof CSSTextFigure) {
			CSSTextFigure figure = (CSSTextFigure) host.getFigure();
			// make a copy to not destroy the original o
			p = p.getCopy();
			figure.translateToRelative(p);
			int offset = figure.getInsertionOffset(p);
			if (offset >= 0) {
				return new DesignPosition(host, offset);
			}
            return null;
		}
        // should not happen.
        return new DesignPosition(host, 0);
	}

	/**
	 * @param figure
	 * @param box
	 * @return
	 */
//	public static Rectangle getBoxBounds(IFigure figure, FlowBox box) {
//		Rectangle r = new Rectangle(box._x, box._y, box.getWidth(), box
//				.getHeight());
//		figure.translateToAbsolute(r);
//		return r;
//	}

	/**
	 * If child is a GraphicalEditPart, a new copy of its bounding rectangle
	 * will be returned translated to absolute bounds. If child is not a GraphicalEditPart
	 * then the empty rectangle (0,0,0,0) is returned.
	 * 
	 * @param child
	 * @return the bounding rectangle or (0,0,0,0) if none.
	 */
	public static Rectangle getAbsoluteBounds(EditPart child) {
		if (child instanceof GraphicalEditPart) {
			Rectangle bounds = ((GraphicalEditPart) child).getFigure()
					.getBounds().getCopy();
			((GraphicalEditPart) child).getFigure().translateToAbsolute(bounds);
			return bounds;
		}
        return new Rectangle(0, 0, 0, 0);
	}

	/**
	 * @param host
	 * @param tagName
	 * @return
	 */
	private static boolean isWidget(EditPart host) {
		if (host instanceof NodeEditPart) {
			return ((NodeEditPart) host).isWidget();
		}
        return false;
	}

	/**
	 * Is Caret at right?
	 * 
	 * @param position
	 * @param caretRefResult
	 * @return
	 */
	private static EditPart tryTwoWays(DesignPosition position,
			List<Boolean> caretRefResult) {
		EditPart result = null;
		// Sibling first:
		Node node = EditModelQuery.getInstance().getSibling(
				DOMPositionHelper.toDOMPosition(position), true);
		if (node != null && !EditModelQuery.isTransparentText(node)) {
			result = Target.resolvePart(node);
			caretRefResult.add(Boolean.FALSE);
		} else {
			node = EditModelQuery.getInstance().getSibling(
					DOMPositionHelper.toDOMPosition(position), false);
			if (node != null && !EditModelQuery.isTransparentText(node)) {
				result = Target.resolvePart(node);
				caretRefResult.add(Boolean.TRUE);
			}
		}
		if (result == null) {
			if (getConcretePart(position, false) != null) {
				result = getConcretePart(position, false);
				caretRefResult.add(Boolean.TRUE);
			} else if (getConcretePart(position, true) != null) {
				result = getConcretePart(position, true);
				caretRefResult.add(Boolean.FALSE);
			}
		}
		return result;
	}

	/*
	 * Here we are doing something to avoid reference whitespace tag. Since we
	 * still need to improve whitespace tags's layout further more.
	 */
	private static EditPart getNextConcretPart(DesignPosition position,
			List<Boolean> caretIsAtRightTest) {
		EditPart result = null;
		boolean caretIsAtRight = true;
		if (position instanceof DesignRefPosition) {
			caretIsAtRight = ((DesignRefPosition) position).caretIsAtRight();
			result = ((DesignRefPosition) position).getRefPart();
			caretIsAtRightTest.add(Boolean.valueOf(caretIsAtRight));
		}
		if (result == null
				|| EditModelQuery.isTransparentText(Target.resolveNode(result))) {
			caretIsAtRightTest.clear();
			result = tryTwoWays(position, caretIsAtRightTest);
		}
		return result;
	}

	/**
	 * Avoid whitespaces
	 * 
	 * @param position
	 * @param forward
	 * @return the edit part at position which is non-whitespace ? TODO:
	 */
	public static EditPart getConcretePart(DesignPosition position,
			boolean forward) {
		EditPart result = null;
		Node node = EditModelQuery.getInstance().getSibling(
				DOMPositionHelper.toDOMPosition(position), forward);
		while (node != null && EditModelQuery.isTransparentText(node)) {
			node = EditModelQuery.getInstance().getSibling(node, forward);
		}
		if (node != null) {
			result = Target.resolvePart(node);
		}
		return result;
	}

	/**
	 * @param position
	 * @param forward
	 * @return the next concrete part.
	 */
	public static EditPart getNextConcretPart(DesignPosition position,
			boolean forward) {
		Node node;
		EditPart result = null;
		node = EditModelQuery.getInstance().getSibling(
				DOMPositionHelper.toDOMPosition(position), forward);
		if (node != null) {
			if (forward) {
				while (node != null) {
					if (!EditModelQuery.isTransparentText(node)
							&& (result = Target.resolvePart(node)) != null) {
						result = Target.resolvePart(node);
						break;
					}
					node = node.getNextSibling();
				}
			} else {
				while (node != null) {
					if (!EditModelQuery.isTransparentText(node)
							&& (result = Target.resolvePart(node)) != null) {
						result = Target.resolvePart(node);
						break;
					}
					node = node.getPreviousSibling();
				}
			}
		}
		return result;
	}

	private static Rectangle getRefRect(DesignPosition position) {
		List<Boolean> caretLocation = new ArrayList<Boolean>();
		EditPart part = getNextConcretPart(position, caretLocation);
		LayoutPart layoutPart;
		Rectangle rect = null;
		if (part != null) {
			layoutPart = new LayoutPart(part, null);
			boolean caretIsAtRight = caretLocation.get(0)
					.booleanValue();
			final int CARET_OFFSET = 1;
			Rectangle bounds = null;
			IFigure figure = ((GraphicalEditPart) part).getFigure();
			if (!caretIsAtRight) {
				FlowBox box;
				if ((box = layoutPart.getLine(0)) != null) {
					bounds = LayoutPart.getBounds(box);
				}
			} else {
				FlowBox box;
				if ((box = layoutPart.getLastLine()) != null) {
					bounds = LayoutPart.getBounds(box);
				}
			}
			if (bounds == null) {
				bounds = figure.getBounds();
			}
			if (!caretIsAtRight) {
				rect = new Rectangle(bounds.x - CARET_OFFSET, bounds.y,
						CaretUpdater.CARET_WIDTH, bounds.height);// new
			} else {
				rect = new Rectangle(bounds.getRight().x + CARET_OFFSET,
						bounds.y, CaretUpdater.CARET_WIDTH, bounds.height);// new
			}
			figure.translateToAbsolute(rect);
		} else {
			System.out.println("No concrete part?"); //$NON-NLS-1$
		}
		return rect;
	}
}
