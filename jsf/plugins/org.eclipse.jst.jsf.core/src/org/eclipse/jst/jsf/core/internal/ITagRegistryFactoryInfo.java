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
package org.eclipse.jst.jsf.core.internal;

import java.util.Set;

import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory;

/**
 * @author cbateman
 * @noimplement
 */
public interface ITagRegistryFactoryInfo
{
    /**
     * @return the content types that the tag registry supports.
     */
    public abstract Set<IContentType> getContentTypes();

    /**
     * @return the tag registry factory.
     */
    public abstract TagRegistryFactory getTagRegistryFactory();

    /**
     * @return the unique id of the factory.
     */
    public abstract String getId();

    /**
     * @return the description of the tag registry factory.
     */
    public abstract String getDescription();

}
