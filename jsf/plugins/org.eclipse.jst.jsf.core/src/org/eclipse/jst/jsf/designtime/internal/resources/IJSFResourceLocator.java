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
package org.eclipse.jst.jsf.designtime.internal.resources;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.locator.ILocator;

/**
 * A JSF resource locator.
 * 
 * @author cbateman
 *
 */
public interface IJSFResourceLocator extends ILocator<List<IJSFResourceFragment>, IProject, String>
{
    /**
     * @param listener
     */
    public void addListener(final JSFResourceChangeListener listener);
    /**
     * @param listener
     */
    public void removeListener(final JSFResourceChangeListener listener);
}
