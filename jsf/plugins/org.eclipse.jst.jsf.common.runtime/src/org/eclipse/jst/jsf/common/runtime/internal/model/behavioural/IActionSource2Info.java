/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
