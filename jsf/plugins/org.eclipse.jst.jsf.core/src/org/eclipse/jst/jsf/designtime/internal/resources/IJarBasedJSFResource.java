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

import java.net.URL;

/**
 * @author cbateman
 *
 */
public interface IJarBasedJSFResource extends IJSFResource
{
    /**
     * @return the uri of the jar where the resource lives
     */
    public abstract URL getJarURL();

    /**
     * @return the zipEntry into the jar where the resource is.
     */
    public abstract String getJarEntryName();
}
