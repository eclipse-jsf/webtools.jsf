/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.validation.caret;

/**
 * @author mengbo
 */
public interface IMovementRule extends IValidationRule {
	/**
	 * @param target
	 * @return true if allows move in
	 */
	public boolean allowsMoveIn(Target target);

	/**
	 * @param target
	 * @return true allows move out
	 */
	public boolean allowsMoveOut(Target target);
}
