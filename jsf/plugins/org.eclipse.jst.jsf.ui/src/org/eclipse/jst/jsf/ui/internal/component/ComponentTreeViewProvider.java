/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.component;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot;

/**
 * A tree view adapter for a design time component tree rooted at a design time
 * view root.
 * 
 * @author cbateman
 * 
 */
/* package */class ComponentTreeViewProvider implements
        IStructuredContentProvider, ITreeContentProvider
{
    private final static Object[] NO_CHILDREN = new Object[0];

    public Object[] getElements(final Object inputElement)
    {
        if (inputElement instanceof DTJSFViewModel)
        {
            final DTUIViewRoot root = ((DTJSFViewModel) inputElement).getRoot();

            if (root != null)
            {
                return new Object[]
                { root };
            }
            return new Object[] {new TreePlaceHolder()};
        }
        return NO_CHILDREN;
    }

    public void dispose()
    {
        // nothing to dispose
    }

    public void inputChanged(final Viewer viewer, final Object oldInput,
            final Object newInput)
    {
        // do nothing
    }

    public Object[] getChildren(final Object parentElement)
    {
        if (parentElement instanceof ComponentInfo)
        {
            final List<Object> children = new ArrayList<Object>();
            children.addAll(((ComponentInfo) parentElement).getChildren());
//            children.addAll(((ComponentInfo) parentElement).getAllDecorators());
            return children.toArray();
        }
        return NO_CHILDREN;
    }

    public Object getParent(final Object element)
    {
        if (element instanceof ComponentInfo)
        {
            return ((ComponentInfo)element).getParent();
        }
        // no parent
        return null;
    }

    public boolean hasChildren(final Object element)
    {
        return getChildren(element).length > 0;
    }
    
    static final class TreePlaceHolder
    {
        // place holder object used when recalculation is in progress
    }

}
