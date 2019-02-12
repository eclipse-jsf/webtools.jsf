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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderListener;

public class MockModelProvider implements IModelProvider
{
    private final Object _modelObject;

    public MockModelProvider(final Object modelObject)
    {
        _modelObject = modelObject;
    }
    public Object getModelObject()
    {
        return _modelObject;
    }

    public Object getModelObject(IPath modelPath)
    {
        throw new UnsupportedOperationException();
    }

    public void modify(Runnable runnable, IPath modelPath)
    {
        throw new UnsupportedOperationException();
    }

    public IStatus validateEdit(IPath modelPath, Object context)
    {
        throw new UnsupportedOperationException();
    }

    public void addListener(IModelProviderListener listener)
    {
       throw new UnsupportedOperationException();
    }

    public void removeListener(IModelProviderListener listener)
    {
        throw new UnsupportedOperationException();
    }

}
