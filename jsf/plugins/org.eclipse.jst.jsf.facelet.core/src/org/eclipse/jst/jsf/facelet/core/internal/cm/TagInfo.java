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
package org.eclipse.jst.jsf.facelet.core.internal.cm;

import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;

/**
 * Generic adapter needed to provide CM required metadata for a CMNode from
 * different sources.
 * 
 * @author cbateman
 *
 */
public abstract class TagInfo
{
    /**
     * @param tagName
     * @param key
     * @return the tag property in the CM model for tagName at key or null if
     *         not found.
     */
    public abstract Object getTagProperty(final String tagName, final String key);

    /**
     * @param tagName
     * @return the node map of attributes for the tag called tagName
     */
    public abstract CMNamedNodeMap getAttributes(final String tagName);

}
