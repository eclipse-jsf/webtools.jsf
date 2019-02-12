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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * A fragment identifier for a library folder.
 * 
 * @author cbateman
 *
 */
public class LibraryResourceFragmentIdentifier extends
        ResourceFragmentIdentifier
{
    private final String _libraryName;

    /**
     * @param libraryName
     */
    public LibraryResourceFragmentIdentifier(final String libraryName)
    {
        super();
        _libraryName = libraryName;
    }

    @Override
    public String getLibraryName()
    {
        return _libraryName;
    }
    @Override
    public IStatus validate()
    {
        return Status.OK_STATUS;
    }
}
