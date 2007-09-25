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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;

/**
 * FlowBoxLine collects EditParts in a line that cover the x or y coordinate of
 * design view caret. An EditPart may be a widget that can't contains children,
 * a TextEditPart which contains a set of flowbox, or a widgets container which
 * contains some other editparts. For container, there are two types: white box
 * to visitor, that is the container * visitor should consider its content, like
 * <A>, <B>. etc, or black box to visitor, like <TABLE>. Black box means the
 * container will be considered as a whole from outside. For non-container
 * widget we only see TextEditPart can be broken at line end. For black box,
 * only start box or latest box are used for final reference, for white box, we
 * will process its content for reference <@see
 * EditPartPositionHelper.findEditPartPosition>. For Text, the char that is
 * closest to caret will be referenced. In this line class, tree types of
 * EditPart are collected: TextEditPart, Widget, BlackBox container.
 * 
 * @author mengbo
 */
public class FlowBoxLine {
	private int _x;

	private int _y;

	private int _height;

	private int _width;

	private HashMap _parts = new HashMap();

	private IPositionMediator _validator;

	private Point _point;

	/**
	 * @param rect
	 * @param validator
	 * @param point
	 */
	public FlowBoxLine(Rectangle rect, IPositionMediator validator, Point point) {
		_x = rect.x;
		_y = rect.y;
		_width = rect.width;
		_height = rect.height;
		_validator = validator;
		_point = point;
	}

	/**
	 * @return Returns the _height.
	 */
	public int getHeight() {
		return _height;
	}

	/**
	 * @return Returns the _width.
	 */
	public int getWidth() {
		return _width;
	}

	/**
	 * @return Returns the _x.
	 */
	public int getX() {
		return _x;
	}

	/**
	 * @return Returns the _y.
	 */
	public int getY() {
		return _y;
	}

	/**
	 * @return the part list
	 */
	public Map getPartsList() {
		return _parts;
	}

	/**
	 * @return the right bottom coordiate
	 */
	public Point getRightBottom() {
		return new Point(_x + _width, _y + _height);
	}

	/**
	 * @param part
	 * @param point
	 * @return layout part added
	 */
	public boolean addLayoutPart(EditPart part, Point point) {
		Assert.isTrue(part != null && point != null);
		Rectangle rect = null;
		LayoutPart lPart = new LayoutPart(part, point);
		if (_parts.size() == 0) {
			resetBounds(lPart);
			return true;
		}
		if (!interact(lPart)) {
			if (closer(lPart)) {
				resetBounds(lPart);
				return true;
			}
            return false;
		}
        rect = lPart.getAbsoluteBounds();
		int xx = Math.min(rect.x, _x);
		int width = Math.max(rect.getRight().x, getRightBottom().x) - xx;
		int yy = Math.min(rect.y, _y);
		int height = Math.max(rect.getBottom().y, getRightBottom().y) - yy;
		_x = xx;
		_y = yy;
		_width = width;
		_height = height;
		_parts.put(part, lPart);
		return true;
	}

	/**
	 * @param lPart
	 * @return true if layout part is within the right bottom corner of the line
	 */
	public boolean interact(LayoutPart lPart) {
		Rectangle rect = lPart.getAbsoluteBounds();
		return !(rect.getBottom().y <= _y || getRightBottom().y <= rect.y);
	}

	/**
	 * @param part
	 * @return true if the line contains part
	 */
	public boolean contains(EditPart part) {
		return _parts.containsKey(part);
	}

	/**
	 * @param part
	 * @return true if the line contains part
	 */
	public boolean contains(LayoutPart part) {
		return _parts.containsValue(part);
	}

	/**
	 * @param part
	 * @return the layout part for part
	 */
	public LayoutPart getLayoutPart(EditPart part) {
		return (LayoutPart) _parts.get(part);
	}

	// 
	/**
	 * For vertical movement, we need to see if there is part cover p.x.
	 * 
	 * @return the closest edit part
	 */
	public EditPart getClosestPart() {
		if (_parts.isEmpty()) {
			return null;
		}
		Collection parts = _parts.values();
		Iterator iterator = parts.iterator();
		LayoutPart closestPart = (LayoutPart) iterator.next();
		if (iterator.hasNext()) {
			while (iterator.hasNext()) {
				LayoutPart nextPart = (LayoutPart) iterator.next();
				closestPart = CaretPositionResolver.getCloserPart(closestPart,
						nextPart, _point);
			}
		}
		// for children.
		LayoutPart result = null;
		if (_validator.getActionData().getActionType() == ActionData.KEYBOARD_NAVAGATION
				|| //
				closestPart.isInline()) {
			result = CaretPositionResolver.getInstance(_validator, _point)
					.resolveClosestPartFrom(closestPart);
		} else {
			result = closestPart;
		}
		if (result != null) {
			return result.getPart();
		}
        return null;
	}

	/**
	 * See how close the part,box is closer to point, if it is closer than
	 * current FlowBoxLine. return true;
	 */
	private boolean closer(LayoutPart lPart) {
		int lineYOffset = Math.abs(CaretPositionResolver.getYDistance(
				getBounds(), _point));
		int partYOffset = Math.abs(CaretPositionResolver.getYDistance(lPart
				.getAbsoluteBounds(), _point));
		return lineYOffset > partYOffset;
	}

	/**
	 * @return the bounding rectangle of the line
	 */
	public Rectangle getBounds() {
		return new Rectangle(_x, _y, _width, _height);
	}

	private void resetBounds(Rectangle rect) {
		_x = rect.x;
		_y = rect.y;
		_width = rect.width;
		_height = rect.height;
	}

	private void resetBounds(LayoutPart lPart) {
		EditPart part = lPart.getPart();
		Rectangle rect = lPart.getAbsoluteBounds();
		resetBounds(rect);
		_parts.clear();
		_parts.put(part, lPart);
	}
}
