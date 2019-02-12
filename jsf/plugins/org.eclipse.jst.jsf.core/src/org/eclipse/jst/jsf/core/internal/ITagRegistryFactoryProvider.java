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

/**
 * The factory provider interface for tag registries.
 *
 */
public interface ITagRegistryFactoryProvider
{
    /**
     * @return the set of tag registry factories to be used to construct
     * tag registries.
     */
    Set<ITagRegistryFactoryInfo>  getTagRegistryFactories();
}
