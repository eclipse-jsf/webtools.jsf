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

import org.eclipse.core.resources.IResource;

/**
 * A JSF fragment whose design time object(s) of interest are in the workspace
 * @author cbateman
 *
 */
public interface IWorkspaceJSFResourceFragment extends IJSFResourceFragment
{

    /**
     * @return the corresponding design time workspace resource.
     */
    public IResource getResource();

}
