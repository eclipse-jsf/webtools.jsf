/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.runtime.internal.model.behavioural;

/**
 * Design-time analog for the ActionSource2 interface.
 * 
 * @author cbateman
 *
 */
public interface IActionSource2Info extends IActionSourceInfo 
{
    /**
     * TODO: this method is similar to getAction on ActionSource from
     * our perspective since the actual string rep of the EL is basically
     * the same...
     * 
     * @return an EL method expression that represents a call to 
     * an action method.
     */
    String getActionExpression();
}
