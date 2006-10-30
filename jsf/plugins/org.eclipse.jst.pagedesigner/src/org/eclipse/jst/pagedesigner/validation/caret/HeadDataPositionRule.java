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

/**
 * Ensure the DnD for loadBundle or taglib is in head area.
 * 
 * @author mengbo
 */
public class HeadDataPositionRule extends DefaultPositionRule {

	/**
	 * @param data
	 */
	public HeadDataPositionRule(IPositionMediator mediator, ActionData data) {
		super(mediator, data);
	}
}
