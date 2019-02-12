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

import org.eclipse.core.resources.IFile;

/**
 * A callback interface used declare library changes to a client.
 * 
 * @author cbateman
 *
 */
public interface ILibraryChangeHandler
{

    /**
     * Signal that the library defined in file with namespace uri has changed.
     * 
     * @param uri
     * @param file
     */
    public abstract void changed(final String uri, final IFile file);

    /**
     * Signal that the library defined in file with namespace uri has been
     * removed as a library.  This may or may not mean that file still exists.
     * @param uri
     * @param file
     */
    public abstract void removed(final String uri, final IFile file);

    /**
     * A new tag library defined in file has been added to the system.
     * 
     * @param file
     */
    public abstract void added(final IFile file);

}
