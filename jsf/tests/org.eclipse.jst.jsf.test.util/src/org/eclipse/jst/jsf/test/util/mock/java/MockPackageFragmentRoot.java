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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IModuleDescription;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;

public class MockPackageFragmentRoot extends MockJavaElement implements
        IPackageFragmentRoot
{
    public MockPackageFragmentRoot(final IJavaProject owningProject,
            final IPath path)
    {
        super(owningProject, path);
    }

    @Override
    public final int getElementType()
    {
        return IJavaElement.PACKAGE_FRAGMENT_ROOT;
    }

    public IJavaElement[] getChildren() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public boolean hasChildren() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public void close() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public String findRecommendedLineSeparator() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public IBuffer getBuffer() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public boolean hasUnsavedChanges() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public boolean isConsistent() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public boolean isOpen()
    {
        throw new UnsupportedOperationException();
    }

    public void makeConsistent(final IProgressMonitor progress)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public void open(final IProgressMonitor progress) throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public void save(final IProgressMonitor progress, final boolean force)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public void attachSource(final IPath sourcePath, final IPath rootPath,
            final IProgressMonitor monitor) throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public void copy(final IPath destination, final int updateResourceFlags,
            final int updateModelFlags, final IClasspathEntry sibling,
            final IProgressMonitor monitor) throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public IPackageFragment createPackageFragment(final String name,
            final boolean force, final IProgressMonitor monitor)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public void delete(final int updateResourceFlags,
            final int updateModelFlags, final IProgressMonitor monitor)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public int getKind() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public Object[] getNonJavaResources() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public IPackageFragment getPackageFragment(final String packageName)
    {
        throw new UnsupportedOperationException();
    }

    public IClasspathEntry getRawClasspathEntry() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public IClasspathEntry getResolvedClasspathEntry()
            throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public IPath getSourceAttachmentPath() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public IPath getSourceAttachmentRootPath() throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

    public boolean isArchive()
    {
        return "jar".equals(getPath().getFileExtension());
    }

    public boolean isExternal()
    {
        throw new UnsupportedOperationException();
    }

    public void move(final IPath destination, final int updateResourceFlags,
            final int updateModelFlags, final IClasspathEntry sibling,
            final IProgressMonitor monitor) throws JavaModelException
    {
        throw new UnsupportedOperationException();
    }

	public IModuleDescription getModuleDescription() {
		throw new UnsupportedOperationException();
	}

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof MockPackageFragmentRoot))
        {
            return false;
        }
        MockPackageFragmentRoot other = (MockPackageFragmentRoot) o;
        return getPath().equals(other.getPath());
    }

    @Override
    public int hashCode()
    {
        return getPath().hashCode();
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        IPath path = getPath();
        if (isExternal()) {
            buffer.append(path.toOSString());
        } else if (getJavaProject().getElementName().equals(path.segment(0))) {
            if (path.segmentCount() == 1) {
                buffer.append("<project root>"); //$NON-NLS-1$
            } else {
                buffer.append(path.removeFirstSegments(1).makeRelative());
            }
        } else {
            buffer.append(path);
        }
//        if (info == null) {
//            buffer.append(" (not open)"); //$NON-NLS-1$
//        }
        return buffer.toString();
    }

}
