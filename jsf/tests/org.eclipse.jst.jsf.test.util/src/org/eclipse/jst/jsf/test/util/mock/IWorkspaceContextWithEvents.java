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
package org.eclipse.jst.jsf.test.util.mock;

import java.util.List;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;

/**
 * An IWorkspaceContext object that has the ability to simulate workspace change
 * events at test time.
 * 
 * @author cbateman
 *
 */
public interface IWorkspaceContextWithEvents extends IWorkspaceContext
{
    public abstract void fireWorkspaceEvent(final IResourceChangeEvent event);
    public abstract List<IResourceChangeListener> getListeners();
    public abstract List<IResourceChangeListener> getListeners(List<Class<? extends IResourceChangeListener>> includeTypes);
}
