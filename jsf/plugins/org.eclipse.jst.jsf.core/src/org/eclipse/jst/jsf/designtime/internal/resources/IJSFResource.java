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
 * Represents a JSF loadable resource (spec 2.6).
 * 
 * @author cbateman
 *
 */
public interface IJSFResource extends IJSFResourceFragment
{
    /**
     * @param contentTypeName
     * @return true if the resources matches the content type indicated by the
     *         contentTypeName.
     */
    public abstract boolean isContentType(final String contentTypeName);

}
