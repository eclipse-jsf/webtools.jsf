/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.common.internal.resource;


/**
 * Listener can register for events ona particular LifecycleListener
 * 
 * @author cbateman
 *
 */
public interface IResourceLifecycleListener extends ILifecycleListener<ResourceLifecycleEvent>
{
    /**
     * Listener accepts the classpath lifecycle event
     * 
     * @param event
     */
    EventResult acceptEvent(ResourceLifecycleEvent event);
    
}
