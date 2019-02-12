/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.internal.resource;

import java.util.EventObject;

/**
 * @author cbateman
 *
 * @param <EVENTTYPE>
 */
public interface ILifecycleListener<EVENTTYPE extends EventObject>
{
    /**
     * Listener accepts the resource lifecycle event
     * 
     * @param event
     * @return the result of accepting the event
     */
    EventResult acceptEvent(EVENTTYPE event);
}
