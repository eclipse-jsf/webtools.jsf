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
package org.eclipse.jst.pagedesigner.validation.caret;

import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;

/**
 * @author mengbo
 */
public interface IPositionMediator {
	/**
	 * @return this mediator's action data
	 */
	public ActionData getActionData();

	/**
	 * To see if the node itself or its descendent is editable.
	 * 
	 * @param target
	 * @return true if the target has editable area
	 */
	public boolean hasEditableArea(Target target);

	/**
	 * To see if the position is valid.
	 * 
	 * @param position
	 * @return true if position is  valid
	 */
	public boolean isValidPosition(IDOMPosition position);

	/**
	 * @param position
	 * @return true if position is valid
	 */
	public boolean isValidPosition(DesignPosition position);

	/**
	 * To see if the node is editable.
	 * 
	 * @param target
	 * @return true if target is editable
	 */
	public boolean isEditable(Target target);

	/**
	 * @param target
	 * @param atRight
	 * @return true if can reference target
	 */
	public boolean canReference(Target target, boolean atRight);

	/**
	 * Return a node which contains 'node', and it has editable area.
	 * 
	 * @param target
	 * @return if part is editable, then itself is returned, otherwise a parent
	 *         is returned.
	 */
	public EditPart getEditableContainer(Target target);
}
