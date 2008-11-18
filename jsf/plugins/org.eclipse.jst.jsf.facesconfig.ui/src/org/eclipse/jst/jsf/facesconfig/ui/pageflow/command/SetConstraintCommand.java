/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.command;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;

/**
 * This is the SetConstraint command for XY layout edit policy
 * 
 */
public class SetConstraintCommand extends Command {
	/** location command label */
	private static final String COMMAND_LABEL_LOCATION = PageflowMessages.SetConstraintCommand_Location;

	/** resize command label */
	private static final String COMMAND_LABEL_RESIZE = PageflowMessages.SetConstraintCommand_Resize;

	/** new position */
	private Point newPos;

	/** new size */
	private Dimension newSize;

	/** olde position */
	private Point oldPos;

	/** old size */
	private Dimension oldSize;

	/** pageflow element */
	private PageflowElement part;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#execute()
	 */
	public void execute() {
		oldSize = new Dimension(part.getWidth(), part.getHeight());
		oldPos = new Point(part.getX(), part.getY());
		part.setX(newPos.x);
		part.setY(newPos.y);
		part.setHeight(newSize.height);
		part.setWidth(newSize.width);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#redo()
	 */
	public void redo() {
		part.setX(newPos.x);
		part.setY(newPos.y);
		part.setHeight(newSize.height);
		part.setWidth(newSize.width);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#undo()
	 */
	public void undo() {
		part.setX(oldPos.x);
		part.setY(oldPos.y);
		part.setHeight(oldSize.height);
		part.setWidth(oldSize.width);
	}

	/**
	 * return the label of the constraints
	 */
	public String getLabel() {
		if (oldSize.equals(newSize)) {
			return COMMAND_LABEL_LOCATION;
		}
		return COMMAND_LABEL_RESIZE;
	}

	/**
	 * set the new location using rectangle
	 * 
	 * @param r -
	 *            new location
	 */
	public void setLocation(Rectangle r) {
		setLocation(r.getLocation());
		setSize(r.getSize());
	}

	/**
	 * set the new location using point
	 * 
	 * @param p -
	 *            new location point
	 */
	public void setLocation(Point p) {
		newPos = p;
	}

	/**
	 * set the pageflow element
	 * 
	 * @param part -
	 *            pageflow element
	 */
	public void setPart(PageflowElement part) {
		this.part = part;
	}

	/**
	 * set the new size
	 * 
	 * @param p -
	 *            new size
	 */
	public void setSize(Dimension p) {
		newSize = p;
	}

}
