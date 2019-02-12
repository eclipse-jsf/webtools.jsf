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

import java.io.Serializable;
import java.util.List;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ActionListenerDecorator;

/**
 * A design time analog for the runtime ActionSource interface.
 * 
 * @author cbateman
 *
 */
public interface IActionSourceInfo extends Serializable
{
    /**
     * @return true if action listener associated with this action source
     * should be executed immediately after the apply request values phase.
     * Default is false.
     */
    boolean isImmediate();
    
    
    /**
     * Add the action listener to the list
     * 
     * @param actionListener
     */
    void addActionListener(ActionListenerDecorator  actionListener);
    
    /**
     * @return all action listeners registered.  List should be considered
     * immutable and may throw exceptions if modified.
     */
    List/*<ActionListenerDecorator>*/ getActionListeners();
    
    /**
     * <b> NOTE: this method is deprecated in the runtime spec and exists for
     * backward compatibility with JSF 1.1. You should avoid using it except in
     * conjunction with JSF 1.1 tooling support. This method will be deprecated
     * once the runtime spec removes the underlying API</b>

     * @return a method binding expression describing an action handler
     */
    String  getAction();
    
    /**
     * <b> NOTE: this method is deprecated in the runtime spec and exists for
     * backward compatibility with JSF 1.1. You should avoid using it except in
     * conjunction with JSF 1.1 tooling support. This method will be deprecated
     * once the runtime spec removes the underlying API</b>
     * 
     * @return a method binding expression describing an action listener
     */
    String  getActionListener();
}
