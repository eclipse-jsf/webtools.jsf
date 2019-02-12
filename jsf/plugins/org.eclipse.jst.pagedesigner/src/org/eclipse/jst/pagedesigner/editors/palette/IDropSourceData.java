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
 * Data describing a palette drop.
 * 
 * @author cbateman
 *
 */
public interface IDropSourceData
{
    /**
     * @return uri of the tag's library. MUST NOT BE NULL.
     */
    public String getNamespace();
    /**
     * @return a unique id for a tag creation provider that may be different
     * from the uri. MUST NOT BE NULL.
     */
    public String getId();
}
