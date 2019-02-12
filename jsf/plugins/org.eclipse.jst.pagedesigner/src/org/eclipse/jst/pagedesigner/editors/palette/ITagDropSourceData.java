/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
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
package org.eclipse.jst.pagedesigner.editors.palette;


/**
 * Provides generic information for tag creation from a palette drop.
 * 
 * @author cbateman
 * 
 */
public interface ITagDropSourceData extends IDropSourceData
{
    /**
     * @return tagName
     */
    public String getTagName();

    /**
     * Convenience method returning the tag libraries default prefix, if
     * applicable
     * 
     * @return default prefix
     */
    public String getDefaultPrefix();
}
