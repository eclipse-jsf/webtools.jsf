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

import org.eclipse.jst.pagedesigner.dom.IDOMPosition;

/**
 * @author mengbo
 */
public interface IPositionRule extends IValidationRule {
	/**
	 * To see if the node is editable. For inline editing, this means whether
	 * can we input something into the target. For DnD, this means whether can
	 * we drag and drop something into it.
	 * 
	 * @param target
	 * @return true if target is editable
	 */
	public boolean isEditable(Target target);

	/**
	 * To see if the node itself or its descendent is editable.
	 * 
	 * @param target
	 * @return true if target is editable
	 */
	public boolean hasEditableArea(Target target);

	/**
	 * Whether can we place caret against this part's border. Some container we
	 * are consider white box, and whitespace may not be suitable for caret
	 * reference.
	 * 
	 * @param target
	 * @param atRight
	 *            TODO
	 * @return true if can reference target
	 */
	public boolean canReference(Target target, boolean atRight);

	/**
	 * To see if the position is valid. Please note, the container is editable
	 * means there is a child area whithin it is editable, the position may be
	 * in an inEditable place. So this method is different from hasEditableArea
	 * and isEditable.
	 * 
	 * @param position
	 * @return true if position is valid
	 */
	public boolean isValidPosition(IDOMPosition position);
}
