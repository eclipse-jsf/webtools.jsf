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
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.locator.ILocator;

/**
 * A locator that returns a name-keyed map of facelet tag records for a particular
 * project.
 * 
 * @author cbateman
 *
 */
public interface IFaceletTaglibLocator extends ILocator<Map<String, ? extends IFaceletTagRecord>, IProject, String>
{
    /**
     * @param listener
     */
    public abstract void addListener(final Listener listener);
}