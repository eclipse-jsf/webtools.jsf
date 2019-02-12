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

import org.eclipse.core.resources.IProject;

/**
 * The base class for all impls of IProjectTaglibDescriptorFactory.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractProjectTaglibDescriptorFactory implements IProjectTaglibDescriptorFactory 
{

    public abstract IProjectTaglibDescriptor create(IProject project,
            TagRecordFactory factory);
}
