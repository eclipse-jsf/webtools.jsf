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
package org.eclipse.jst.jsf.test.util.mock.java;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.JavaModelException;

public class MockJavaElement implements IJavaElement
{
    private boolean _exists;
    private final IJavaProject _owningProject;
    private String _name;
    private final IPath  _path;

    public MockJavaElement(final IJavaProject owningProject,
            final IPath path)
    {
        _owningProject = owningProject;
        _name = path.toString();
        _path = path;
    }

    @SuppressWarnings("rawtypes")
    public Object getAdapter(final Class adapter)
    {
        throw new UnsupportedOperationException();
    }

    public boolean exists()
    {
        return _exists;
    }

    public final void setExists(final boolean exists)
    {
        _exists = exists;
    }

    public IJavaElement getAncestor(final int ancestorType)
    {
        throw new UnsupportedOperationException();
    }

    public String getAttachedJavadoc(final IProgressMonitor monitor)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public IResource getCorrespondingResource() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public String getElementName()
    {
        return _name;
    }

    public int getElementType()
    {
        throw new UnsupportedOperationException();
    }

    public String getHandleIdentifier()
    {
        throw new UnsupportedOperationException();
    }

    public IJavaModel getJavaModel()
    {
        throw new UnsupportedOperationException();
    }

    public IJavaProject getJavaProject()
    {
        return _owningProject;
    }

    public IOpenable getOpenable()
    {
        throw new UnsupportedOperationException();
    }

    public IJavaElement getParent()
    {
        throw new UnsupportedOperationException();
    }

    public IPath getPath()
    {
        return _path;
    }

    public IJavaElement getPrimaryElement()
    {
        throw new UnsupportedOperationException();
    }

    public IResource getResource()
    {
        throw new UnsupportedOperationException();
    }

    public ISchedulingRule getSchedulingRule()
    {
        throw new UnsupportedOperationException();
    }

    public IResource getUnderlyingResource() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public boolean isReadOnly()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isStructureKnown() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        // Java model parent is null
        // if (this.parent == null) return super.equals(o);
        // assume instanceof check is done in subclass
        final MockJavaElement other = (MockJavaElement) o;
        return getElementName().equals(other.getElementName())/*
                                                               * &&
                                                               * this.parent.equals
                                                               * (other.parent)
                                                               */;
    }

    @Override
    public int hashCode()
    {
        return _name.hashCode();
    }

    @Override
    public String toString()
    {
        return String.format(
                "MockJavaElement: name=%s, owningProject=%s, exists=%s", _name,
                _owningProject, _exists);
    }
}
