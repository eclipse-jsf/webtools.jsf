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

/**
 * A resource identifier for a library.
 * @author cbateman
 *
 */
public class LibraryResourceIdentifier extends ResourceIdentifier
{
    private final String _libraryName;

    /**
     * @param resName 
     * @param libraryName
     */
    public LibraryResourceIdentifier(final String resName, final String libraryName)
    {
        super(resName);
        _libraryName = libraryName;
    }

    @Override
    public String getLibraryName()
    {
        return _libraryName;
    }
}
