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

/**
 * @author mengbo
 */
public interface IMovementMediator extends IPositionMediator {
	/**
	 * @param target
	 * @return true if mediator allows movment in
	 */
	public boolean allowsMoveIn(Target target);

	/**
	 * @param target
	 * @return true if mediator allows movement out
	 */
	public boolean allowsMoveOut(Target target);

	/**
	 * Return a closest parent part which is editable, and it can't moveout.
	 * 
	 * @param target
	 * @return if taget part is editable, then returns itself.
	 */
	public EditPart getConstainedEditableContainer(Target target);

}
