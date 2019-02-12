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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

public class MockVirtualComponentQuery extends
        AbstractVirtualComponentQuery
{

    private final IContainer _webContentFolder;

    /**
     * @param webContentFolder
     */
    public MockVirtualComponentQuery(final IContainer webContentFolder)
    {
        _webContentFolder = webContentFolder;
    }

    @Override
    public IVirtualFolder getWebContentFolder(IProject project)
    {
        return new MockVirtualFolder(_webContentFolder);
    }
}