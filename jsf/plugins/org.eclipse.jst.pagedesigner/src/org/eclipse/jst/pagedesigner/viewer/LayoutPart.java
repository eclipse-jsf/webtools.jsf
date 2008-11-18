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

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.layout.CSSTextFigure;
import org.eclipse.jst.pagedesigner.css2.layout.FlowBox;
import org.eclipse.jst.pagedesigner.css2.layout.FlowUtilities;
import org.eclipse.jst.pagedesigner.css2.layout.TextFragmentBox;
import org.eclipse.jst.pagedesigner.css2.layout.TextLayoutSupport;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.parts.SubNodeEditPart;
import org.eclipse.jst.pagedesigner.parts.TextEditPart;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.validation.caret.Target;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public final  class LayoutPart {
	private final static int MAX_OFFSET_TO_EDGE = 10;

	private EditPart _part;

	private FlowBox _box;

	private final Point EMPTY_POINT = new Point(0, 0);

	private Point _point = EMPTY_POINT;

	/**
	 * If caller didn't resolve FlowBox, call this.
	 * 
	 * @param part
	 * @param point
	 */
	public LayoutPart(EditPart part, Point point) {
		Assert.isTrue(part != null);
		_part = part;
		_point = point;
	}

	/**
	 * @return Returns the _box, when it is null, generate box with part and
	 *         point.
	 */
	public FlowBox getBox() {
		if (_box == null) {
			_box = getClosestBox();
		}
		return _box;
	}

	/**
	 * @return Returns the _part.
	 */
	public EditPart getPart() {
		return _part;
	}

	/**
	 * Get closest box's bounds.
	 * 
	 * @param point
	 * @return
	 */
	private Rectangle getClosestBoxAbsoluteBounds() {
		Rectangle rect = null;
		if (getBox() != null) {
			rect = getAbsoluteBounds(getBox());
		}
		return rect;
	}

	/**
	 * Try to get the closest flowbox absolute bounds.
	 * 
	 * @return the bounding rectangle
	 */
	public Rectangle getAbsoluteBounds() {
		Rectangle bounds = null;
		if ((bounds = getClosestBoxAbsoluteBounds()) == null) {
			// This should never happens.
			bounds = EditPartPositionHelper.getAbsoluteBounds(_part);
		}
		return bounds;
	}

	/**
	 * Get box's absolute bounds.
	 * 
	 * @param box
	 * @return the box's bounding rectangle
	 */
	public Rectangle getAbsoluteBounds(FlowBox box) {
		if (box != null) {
			IFigure figure = ((GraphicalEditPart) _part).getFigure();
			Rectangle rect = new Rectangle(box.getX(), box.getY(), box.getWidth(), box
					.getHeight());
			figure.translateToAbsolute(rect);
			return rect;
		}
		return null;
	}

	/**
	 * Closest box is the part's FlowBox which y coordinate is closest to point,
	 * and then its x coordinate is more close to point than other boxs of the
	 * same line.
	 * 
	 * @param part
	 * @param point
	 * @return
	 */
	private FlowBox getClosestBox() {
		FlowBox closestBox = null;
		List fragments = getLines(_part);

		// if there is one which are at the same line with relative,
		// calculate that line first;
		for (int i = 0; i < fragments.size(); i++) {
			FlowBox box = (FlowBox) fragments.get(i);
			Rectangle boxRect = getAbsoluteBounds(box);
			if (boxRect.contains(_point.x, _point.y)) {
				closestBox = box;
				break;
			}
            if (closestBox == null) {
            	closestBox = box;
            } else {
            	// compare y.
            	int offset1 = Math.abs(CaretPositionResolver.getYDistance(
            			boxRect, _point));
            	Rectangle closestRect = getAbsoluteBounds(closestBox);
            	int offset2 = Math.abs(CaretPositionResolver.getYDistance(
            			closestRect, _point));
            	if (offset1 < offset2) {
            		closestBox = box;
            	}
            }
            // at the same line
            if (closestBox != box && boxRect.contains(boxRect.x, _point.y)) {
            	// compare x.
            	int offset1 = Math.abs(CaretPositionResolver.getXDistance(
            			boxRect, _point));
            	Rectangle closestRect = getAbsoluteBounds(closestBox);
            	int offset2 = Math.abs(CaretPositionResolver.getXDistance(
            			closestRect, _point));
            	if (offset1 < offset2) {
            		closestBox = box;
            	}
            }
		}
		return closestBox;
	}

	/**
	 * The point is whitin the bounds of the figure.
	 * 
	 * @param point
	 * @return true if point is the absolute bounds of this
	 */
	public boolean contains(Point point) {
		return getAbsoluteBounds().contains(point);
	}

	/**
	 * @return the design position
	 */
	public DesignPosition resolveTextPosition() {
		DesignPosition result = null;
		if (_part instanceof TextEditPart
				&& !EditModelQuery.isTransparentText(Target.resolveNode(_part))) {
			FlowBox flowBox = getBox();
			if (flowBox instanceof TextFragmentBox) {
				TextFragmentBox box = (TextFragmentBox) flowBox;
				if (((TextEditPart) _part).getFigure() instanceof CSSTextFigure) {
					CSSTextFigure figure = (CSSTextFigure) ((TextEditPart) _part)
							.getFigure();
					Rectangle boxRect = getAbsoluteBounds(box);
					int index = FlowUtilities.getTextInWidth(box.getTextData(),
							figure.getCSSStyle().getCSSFont().getSwtFont(),
							_point.x - boxRect.x, TextLayoutSupport
									.getAverageCharWidth(box));
					result = new DesignPosition(_part, box._offset + index);
				}
			}
		}
		return result;
	}

	/**
	 * @param validator
	 * @return resolve the design position using validator
	 */
	public DesignPosition resolvePosition(IPositionMediator validator) {
		DesignPosition result;
		if ((result = resolveTextPosition()) == null) {
			boolean atPointLeft = isBeforePoint(_point);
//			boolean atPointRight = isAfterPoint(_point);
//			if (atPointLeft == atPointRight) {
//			    // TODO: and...?
//			}
			Target target = new Target(getPart());
			if (validator.isValidPosition(new DOMRefPosition(target.getNode(),
					atPointLeft))) {
				result = new DesignRefPosition(_part, atPointLeft);
			} else if (validator.isValidPosition(new DOMRefPosition(target
					.getNode(), !atPointLeft))) {
				result = new DesignRefPosition(_part, !atPointLeft);
			} else if (validator.isEditable(target)) {
				if (atPointLeft) {
					result = new DesignPosition(_part, 0);
				} else {
					result = new DesignPosition(_part, _part.getChildren()
							.size());
				}
			}
		}
		return result;
	}

    // TODO: dead?
//	private IFigure getFigure() {
//		return ((GraphicalEditPart) _part).getFigure();
//	}

	private boolean isAfterPoint(Point point) {
		boolean result = false;
		FlowBox flowBox = getLine(0);
		if (IHTMLConstants.TAG_BR.equalsIgnoreCase(Target.resolveNode(_part)
				.getNodeName())) {
			if (flowBox != null) {
				Rectangle boxRect = getAbsoluteBounds(flowBox);
				result = CaretPositionResolver.getYDistance(boxRect, point) == 0;
			}
		} else {

			if (flowBox != null) {
				Rectangle boxRect = getAbsoluteBounds(flowBox);
				if (CaretPositionResolver.getXDistance(boxRect, point) != 0) {
					result = CaretPositionResolver.getXDistance(boxRect, point) < 0
							&& // 
							CaretPositionResolver.getYDistance(boxRect, point) == 0;
				}
			}
		}
		result |= isUnderCaret();
		// if (isWidget() && flowBox != null)
		// {
		// result |= contains(point) &&
		// CaretPositionResolver.toXMiddle(getAbsoluteBounds(flowBox), point) <
		// 0;
		// }
		return result;

	}

	/*package*/ boolean isBeforePoint(Point point) {
		boolean result = false;
		FlowBox flowBox = getLastLine();
		if (flowBox != null) {
			Rectangle boxRect = getAbsoluteBounds(flowBox);
			if (IHTMLConstants.TAG_BR.equalsIgnoreCase(Target
					.resolveNode(_part).getNodeName())) {
				return CaretPositionResolver.getYDistance(boxRect, point) == 0;
			} else if (CaretPositionResolver.getXDistance(boxRect, point) != 0) {
				result = CaretPositionResolver.getXDistance(boxRect, point) > 0
						&& // 
						CaretPositionResolver.getYDistance(boxRect, point) == 0;
			}
		}
		result |= isAboveCaret();
		// if (isWidget() && flowBox != null)
		// {
		// result |= contains(point) &&
		// CaretPositionResolver.toXMiddle(getAbsoluteBounds(flowBox), point) >
		// 0;
		// }
		return result;
		// return !isAfterPoint(point);
	}

	/*package*/ boolean isBeforePoint() {
		return isBeforePoint(_point);
	}

	/*package*/ boolean atLeftPart(Point point) {
		FlowBox flowBox = getBox();
		if (flowBox != null) {
			Rectangle boxRect = getAbsoluteBounds(flowBox);
			return CaretPositionResolver.toXMiddle(boxRect, point) < 0;
		}
		return true;
	}

	/*package*/ boolean isAfterPoint() {
		return isAfterPoint(_point);
	}

	// TODO: dead but possibly useful?
//	private boolean atSameLine(Point point) {
//		Rectangle bounds = getAbsoluteBounds();
//		return bounds.contains(bounds.getTop().x, point.y);
//	}

	/*package*/ boolean atSameRow(Point point) {
		Rectangle bounds = getAbsoluteBounds();
		return bounds.contains(point.x, bounds.getRight().y);
	}

	/*package*/ static Rectangle getBounds(FlowBox box) {
		return new Rectangle(box.getX(), box.getY(), box.getWidth(), box.getHeight());
	}

	/**
	 * @return Returns the _point.
	 */
	public Point getPoint() {
		return _point;
	}

	FlowBox getLine(int index) {
		FlowBox result = null;
		List lines = getLines(_part);
		if (lines.size() > 0 && index >= 0 && index <= lines.size() - 1) {
			result = (FlowBox) lines.get(index);
		}
		return result;
	}

	FlowBox getLastLine() {
		FlowBox result = null;
		List lines = getLines(_part);
		if (lines.size() > 0) {
			result = (FlowBox) lines.get(lines.size() - 1);
		}
		return result;
	}

	/**
	 * @param part
	 * @return
	 */
	List getLines(EditPart part) {
		List fragments = new ArrayList();
		if (part instanceof SubNodeEditPart) {
			IFigure figure = ((GraphicalEditPart) part).getFigure();

			if (figure instanceof CSSTextFigure) {
				fragments = ((CSSTextFigure) figure).getFragments();
				((CSSTextFigure) figure).getCSSStyle();
			} else if (figure instanceof CSSFigure) {
				fragments = ((CSSFigure) figure).getFragmentsForRead();
				((CSSFigure) figure).getCSSStyle();
			}
		}
		return fragments;
	}

	/**
	 * To search for none empty string, this is not final.
	 * @param part 
	 * @return the edit part
	 * 
	 */
	/*package*/ static EditPart getConcretePart(EditPart part) {
		if (part != null) {
			Node node = Target.resolveNode(part);
			Node child = node.getFirstChild();
			EditPart result;
			while (child != null) {
				if (!EditModelQuery.isTransparentText(child)
						&& (result = Target.resolvePart(child)) != null) {
					return result;
				}
				child = child.getNextSibling();
			}
		}
		return null;
	}

	/**
	 * To search for none empty string, this is not final.
	 * Equivalent to getConcretePart(getPart())
	 * 
	 * @return the edit part
	 * 
	 */
	public EditPart getConcretePart() {
		return getConcretePart(_part);
	}

	/**
	 * @param node
	 * @return the node
	 */
	public static Node getConcreteNode(Node node) {
		if (node != null) {
			Node child = node.getFirstChild();
			while (child != null) {
				if (!EditModelQuery.isTransparentText(child)) {
					return node;
				}
				child = child.getNextSibling();
			}
		}
		return null;
	}

	/**
	 * @return true if is close to edge
	 */
	public boolean isCloseToEdgeFromOutSide() {
		boolean result = false;
		if (EditModelQuery.isBlockNode(Target.resolveNode(_part))) {
			result = Math.abs(getAbsoluteBounds().getLeft().x - _point.x) <= MAX_OFFSET_TO_EDGE;
			if (!result) {
				result = Math.abs(getAbsoluteBounds().getRight().x - _point.x) <= MAX_OFFSET_TO_EDGE;
			}
		}
		return result;
	}

	private boolean isAboveCaret() {
		return getAbsoluteBounds().getBottom().y <= _point.y;
	}

	private boolean isUnderCaret() {
		return getAbsoluteBounds().getTop().y >= _point.y;
	}

	/**
	 * @return tru if getPart() is considered inline
	 */
	/*package*/ boolean isInline() {
		return EditModelQuery.isInline(Target.resolveNode(_part));
	}

    // TODO: dead?
//	private boolean isWidget() {
//		return EditModelQuery.isWidget(_part);
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("editPart:" + _part + ", --- box: " + getBox()); //$NON-NLS-1$ //$NON-NLS-2$
		return sb.toString();
	}
}
