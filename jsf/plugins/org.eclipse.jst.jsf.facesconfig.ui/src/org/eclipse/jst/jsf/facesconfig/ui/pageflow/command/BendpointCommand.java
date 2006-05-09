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
import org.eclipse.gef.commands.Command;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;

/**
 * base class for bend point commands.
 * 
 */
public abstract class BendpointCommand extends Command {
	/** the index of the bend point */
	protected int index;

	/** the location of the bendpoint */
	protected Point location;

	/** the parent link */
	protected PageflowLink link;

	/** relative dimension between the bendpoint with start point of the pflink */
	private Dimension dimStart;

	/** relative dimension between the bendpoint with end point of the pflink */
	private Dimension dimEnd;

	public BendpointCommand(String label) {
		super(label);
	}

	/**
	 * get the dimension between the bendpoint and start point
	 * 
	 * @return - the start dimension
	 */
	protected Dimension getFirstRelativeDimension() {
		return dimStart;
	}

	/**
	 * get the dimension between the bendpoint and end point
	 * 
	 * @return - the end dimension
	 */
	protected Dimension getSecondRelativeDimension() {
		return dimEnd;
	}

	/**
	 * get the index of the bend point
	 * 
	 * @return - the index
	 */
	protected int getIndex() {
		return index;
	}

	/**
	 * get the location of the bend point
	 * 
	 * @return - the index
	 */
	protected Point getLocation() {
		return location;
	}

	/**
	 * get the parent link
	 * 
	 * @return - parent link
	 */
	protected PageflowLink getPFLink() {
		return link;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#redo()
	 */
	public void redo() {
		execute();
	}

	/**
	 * set the relative dimensions of the bendpoint
	 * 
	 * @param dim1 -
	 *            the dimension between the bendpoint and start point
	 * @param dim2 -
	 *            the dimension between the bendpoint and end point
	 */
	public void setRelativeDimensions(Dimension dim1, Dimension dim2) {
		dimStart = dim1;
		dimEnd = dim2;
	}

	/**
	 * set the index of the bendpoint in the bendpoint list
	 * 
	 * @param i -
	 *            index
	 */
	public void setIndex(int i) {
		index = i;
	}

	/**
	 * set the location of the bendpoing
	 * 
	 * @param p -
	 *            new location
	 */
	public void setLocation(Point p) {
		location = p;
	}

	/**
	 * set the parent link
	 * 
	 * @param newLink -
	 *            new parent pflink
	 */
	public void setPFLink(PageflowLink newLink) {
		link = newLink;
	}
}
