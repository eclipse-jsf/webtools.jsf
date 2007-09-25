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

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.validation.caret.IMovementMediator;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.validation.caret.Target;
import org.w3c.dom.Node;

/**
 * @author mengbo
 * @version 1.5
 */
public class CaretPositionResolver {
	private IPositionMediator _validator;

	private Point _point;

	private static CaretPositionResolver _instance;

	/**
	 * @param validator
	 * @param point
	 * @return the singleton instance
	 */
	public static CaretPositionResolver getInstance(
			IPositionMediator validator, Point point) {
		if (_instance == null) {
			_instance = new CaretPositionResolver();
		}
		_instance.setPoint(point);
		_instance.setValidator(validator);
		return _instance;
	}

	/**
	 * @param _point
	 *            The _point to set.
	 */
	private void setPoint(Point _point) {
		this._point = _point;
	}

	/**
	 * @param _validator
	 *            The _validator to set.
	 */
	private void setValidator(IPositionMediator _validator) {
		this._validator = _validator;
	}

	/**
	 * Calculate the two part's distance to point, the shorter one will be
	 * return. Distance is calculated based on: if there is one box contains
	 * point.y, then calculate that box, or if there is no any such one box,
	 * then calculate the y distance.
	 * 
	 * @param part1
	 * @param part2
	 * @param point
	 * @return
	 */
	static LayoutPart getCloserPart(LayoutPart part1, LayoutPart part2,
			Point _point) {
		if (part1 == null
				|| EditModelQuery.isTransparentText(Target.resolveNode(part1
						.getPart()))) {
			return part2;
		} else if (part2 == null
				|| EditModelQuery.isTransparentText(Target.resolveNode(part2
						.getPart()))) {
			return part1;
		}
		Rectangle rect1 = part1.getAbsoluteBounds();
		Rectangle rect2 = part2.getAbsoluteBounds();
		Node n1 = Target.resolveNode(part1.getPart());
		Node n2 = Target.resolveNode(part2.getPart());
		// Within same.
		if (EditModelQuery.isChild(n1, n2)
				&& (CaretPositionResolver.getXDistance(rect2, _point) == 0)
				&& !part1.isCloseToEdgeFromOutSide()) {
			return part2;
		} else if (EditModelQuery.isChild(n2, n1)
				&& (CaretPositionResolver.getXDistance(rect1, _point) == 0 && !part2
						.isCloseToEdgeFromOutSide())
				&& !part2.isCloseToEdgeFromOutSide()) {
			return part1;
		}
		if (rect1.intersect(new Rectangle(rect1.x, rect2.y, rect1.width,
				rect2.height)).height == 0) {
			return heightFirst(part1, part2, _point);
		}
        return widthFirst(part1, part2, _point);
	}

	private static LayoutPart heightFirst(LayoutPart part1, LayoutPart part2,
			Point _point) {
		Rectangle rect1 = part1.getAbsoluteBounds();
		Rectangle rect2 = part2.getAbsoluteBounds();
		int offset1 = Math.abs(CaretPositionResolver
				.getYDistance(rect1, _point));
		int offset2 = Math.abs(CaretPositionResolver
				.getYDistance(rect2, _point));
		if (offset1 > offset2) {
			return part2;
		} else if (offset1 < offset2) {
			return part1;
		} else {
			offset1 = Math.abs(CaretPositionResolver
					.getXDistance(rect1, _point));
			offset2 = Math.abs(CaretPositionResolver
					.getXDistance(rect2, _point));
			if (offset1 >= offset2) {
				return part2;
			}
            return part1;
		}
	}

	private static LayoutPart widthFirst(LayoutPart part1, LayoutPart part2,
			Point _point) {
		Rectangle rect1 = part1.getAbsoluteBounds();
		Rectangle rect2 = part2.getAbsoluteBounds();
		int offset1 = Math.abs(CaretPositionResolver
				.getXDistance(rect1, _point));
		int offset2 = Math.abs(CaretPositionResolver
				.getXDistance(rect2, _point));
		if (offset1 > offset2) {
			return part2;
		} else if (offset1 < offset2) {
			return part1;
		} else {
			offset1 = Math.abs(CaretPositionResolver
					.getYDistance(rect1, _point));
			offset2 = Math.abs(CaretPositionResolver
					.getYDistance(rect2, _point));
			if (offset1 >= offset2) {
				return part2;
			}
            return part1;
		}
	}

	/**
	 * Return a descendent part under parent that is one of the closest part to
	 * point.
	 * 
	 * @param parent
	 * @return
	 */
	private LayoutPart getClosestChildPart(LayoutPart parent) {
		LayoutPart result = null;
		if (parent != null) {
			List children = null;
			if ((children = parent.getPart().getChildren()).size() > 0) // &&
			// _validator.hasEditableArea(new
			// Target(parent.getPart())))
			{
				// iterate its children, we know the part doesn't contain p. so
				// we only see if its children can be
				// referenced.
				for (int i = 0, n = children.size(); i < n; i++) {
					LayoutPart nextPart = new LayoutPart((EditPart) children
							.get(i), _point);
					Target target = new Target(nextPart.getPart());
					if (_validator.isValidPosition(new DesignRefPosition(target
							.getPart(), false))) {
						result = getCloserPart(result, nextPart, _point);
					} else if (_validator.hasEditableArea(target)) {
						LayoutPart temp = getClosestChildPart(nextPart);
						if (temp == null) {
							temp = nextPart;
						}
						result = getCloserPart(result, temp, _point);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Use by vertical movement, we need to see whther the par
	 * 
	 * @param closestPart
	 * @param target
	 * @return
	 */
	LayoutPart resolveClosestPartFrom(LayoutPart closestPart) {
		Target target = new Target(closestPart.getPart());
		LayoutPart finalPart = null;
		if (EditModelQuery.isInline(Target.resolveNode(closestPart.getPart()))) {

			if (closestPart.isAfterPoint() || closestPart.isBeforePoint()) {
				finalPart = closestPart;
			} else {
				if (_validator.hasEditableArea(target)
						&& (_validator instanceof IMovementMediator
								&& ((IMovementMediator) _validator)
										.allowsMoveIn(target) || !(_validator instanceof IMovementMediator))) {
					finalPart = getClosestChildPartOrPart(closestPart);
				}
			}
		}
		// block
		else {
			if (closestPart.contains(_point)) {
				if (_validator.hasEditableArea(target) && //
						(_validator instanceof IMovementMediator
								&& ((IMovementMediator) _validator)
										.allowsMoveIn(target) || !(_validator instanceof IMovementMediator))) {
					finalPart = getClosestChildPartOrPart(closestPart);
				}
			}
			// outside of bounds
			else {
				if (_validator.hasEditableArea(target)
						&& !IHTMLConstants.TAG_TABLE.equalsIgnoreCase(target
								.getNode().getNodeName())
						&& (_validator instanceof IMovementMediator
								&& ((IMovementMediator) _validator)
										.allowsMoveIn(target) || !(_validator instanceof IMovementMediator))) {
					if (closestPart.atSameRow(_point)) {
						finalPart = getClosestChildPartOrPart(closestPart);
					} else if (!_validator
							.isValidPosition(new DesignRefPosition(target
									.getPart(), true))) {
						finalPart = getClosestChildPartOrPart(closestPart);
					}
				}
			}
		}
		if (finalPart == null && //
				(_validator.isValidPosition(new DesignRefPosition(target
						.getPart(), true)) || // 
				_validator.isValidPosition(new DesignRefPosition(target
						.getPart(), false)))) {
			finalPart = closestPart;
		}
		return finalPart;
	}

	private LayoutPart getClosestChildPartOrPart(LayoutPart closestPart) {
		LayoutPart result = getClosestChildPart(closestPart);
		if (result != null) {
			result = resolveClosestPartFrom(result);
		} else {
			if (closestPart.getConcretePart() == null) {
				result = closestPart;
			}
		}
		return result;
	}

	/**
	 * Get the distance from rect's edge to point.x.
	 * 
	 * @param rect
	 * @param point
	 * @return the X distance
	 */
	public static int getXDistance(Rectangle rect, Point point) {
		if (rect.getRight().x <= point.x) {
			return point.x - (rect.getRight().x);
		} else if (rect.x >= point.x) {
			return point.x - rect.x;
		} else if (rect.x <= point.x && point.x <= rect.getRight().x) {
			return 0;
		}
		return -1;
	}

	/**
	 * from point to middle's distance. If the result is nagative, point is at
	 * left part of rect, if it is positive, the point is at the right part.
	 * 
	 * @param rect
	 * @param point
	 * @return the X distance
	 */
	public static int toXMiddle(Rectangle rect, Point point) {
		return (point.x - (rect.x + rect.getRight().x) / 2);
	}

	/**
	 * from point to middle's distance If the result is nagative, point is at
	 * upper part of rect, if it is positive, the point is at the lower part.
	 * 
	 * @param rect
	 * @param point
	 * @return the Y distance
	 */
	public static int toYMiddle(Rectangle rect, Point point) {
		return (point.y - (rect.y + rect.getBottom().y) / 2);
	}

	/**
	 * @param rect
	 * @param point
	 * @return the Y distance
	 */
	public static int getYDistance(Rectangle rect, Point point) {
		if (rect.y + rect.height <= point.y) {
			return point.y - (rect.y + rect.height);
		} else if (rect.y >= point.y) {
			return point.y - rect.y;
		} else if (rect.y <= point.y && point.y <= rect.y + rect.height) {
			return 0;
		}
		return -1;
	}
}
