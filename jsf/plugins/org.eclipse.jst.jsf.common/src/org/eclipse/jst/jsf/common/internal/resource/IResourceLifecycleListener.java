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
