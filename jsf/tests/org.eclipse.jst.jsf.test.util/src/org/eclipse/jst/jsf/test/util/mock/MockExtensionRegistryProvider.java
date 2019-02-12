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
package org.eclipse.jst.jsf.test.util.mock;

import java.io.File;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.spi.IRegistryProvider;
import org.eclipse.core.runtime.spi.RegistryStrategy;

public class MockExtensionRegistryProvider implements IRegistryProvider
{

    public IExtensionRegistry getRegistry()
    {
        return new ExtensionRegistry(new RegistryStrategy(new File[0], new boolean[0]), new Object(), new Object());
    }

}
