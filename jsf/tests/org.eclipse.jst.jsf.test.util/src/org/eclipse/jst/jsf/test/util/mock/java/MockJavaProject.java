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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IModuleDescription;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IRegion;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.WorkingCopyOwner;
import org.eclipse.jdt.core.eval.IEvaluationContext;

public class MockJavaProject extends MockJavaElement implements IJavaProject
{
    private final IProject _project;
    private final List<IPackageFragmentRoot> _packageFragRoots;


    public MockJavaProject(final IProject project, final List<IPackageFragmentRoot> packageFragRoots)
    {
        super(null, project.getFullPath());
        _project = project;
        _packageFragRoots = new ArrayList<IPackageFragmentRoot>(packageFragRoots);
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

    public IClasspathEntry decodeClasspathEntry(final String encodedEntry)
    {
        throw new UnsupportedOperationException();

    }

    public String encodeClasspathEntry(final IClasspathEntry classpathEntry)
    {
        throw new UnsupportedOperationException();

    }

    public IJavaElement findElement(final IPath path) throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IJavaElement findElement(final IPath path, final WorkingCopyOwner owner)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IJavaElement findElement(final String bindingKey, final WorkingCopyOwner owner)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IPackageFragment findPackageFragment(final IPath path)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IPackageFragmentRoot findPackageFragmentRoot(final IPath path)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IPackageFragmentRoot[] findPackageFragmentRoots(final IClasspathEntry entry)
    {
        throw new UnsupportedOperationException();

    }

    // TODO: implement this?
    public IPackageFragmentRoot[] findUnfilteredPackageFragmentRoots(final IClasspathEntry entry)
    {
        throw new UnsupportedOperationException();

    }

    // TODO: implement this?
    public IClasspathEntry getClasspathEntryFor(final IPath path)
    {
    	throw new UnsupportedOperationException();
    }
    
    // TODO: implement this?
    public Set<String> determineModulesOfProjectsWithNonEmptyClasspath()
    {
    	throw new UnsupportedOperationException();
    }
    
    public IType findType(final String fullyQualifiedName) throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IType findType(final String fullyQualifiedName,
            final IProgressMonitor progressMonitor) throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IType findType(final String fullyQualifiedName, final WorkingCopyOwner owner)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IType findType(final String fullyQualifiedName, final WorkingCopyOwner owner,
            final IProgressMonitor progressMonitor) throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IType findType(final String packageName, final String typeQualifiedName)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IType findType(final String packageName, final String typeQualifiedName,
            final IProgressMonitor progressMonitor) throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IType findType(final String packageName, final String typeQualifiedName,
            final WorkingCopyOwner owner) throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IType findType(final String packageName, final String typeQualifiedName,
            final WorkingCopyOwner owner, final IProgressMonitor progressMonitor)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IPackageFragmentRoot[] getAllPackageFragmentRoots()
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public Object[] getNonJavaResources() throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public String getOption(final String optionName, final boolean inheritJavaCoreOptions)
    {
        throw new UnsupportedOperationException();

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getOptions(final boolean inheritJavaCoreOptions)
    {
        throw new UnsupportedOperationException();

    }

    public IPath getOutputLocation() throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IPackageFragmentRoot getPackageFragmentRoot(
            final String externalLibraryPath)
    {
        throw new UnsupportedOperationException();

    }

    public IPackageFragmentRoot getPackageFragmentRoot(final IResource resource)
    {
        throw new UnsupportedOperationException();

    }

    public IPackageFragmentRoot[] getPackageFragmentRoots()
            throws JavaModelException
    {
        return _packageFragRoots.toArray(new IPackageFragmentRoot[0]);

    }

    public IPackageFragmentRoot[] getPackageFragmentRoots(final IClasspathEntry entry)
    {
        throw new UnsupportedOperationException();

    }

    public IPackageFragment[] getPackageFragments() throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IProject getProject()
    {
        return _project;
    }

    public IClasspathEntry[] getRawClasspath() throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public String[] getRequiredProjectNames() throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IClasspathEntry[] getResolvedClasspath(final boolean ignoreUnresolvedEntry)
            throws JavaModelException
    {
        return _packageFragRoots.toArray(new IClasspathEntry[0]);
    }

    public boolean hasBuildState()
    {
        throw new UnsupportedOperationException();

    }

    public boolean hasClasspathCycle(final IClasspathEntry[] entries)
    {
        throw new UnsupportedOperationException();

    }

    public boolean isOnClasspath(final IJavaElement element)
    {
        throw new UnsupportedOperationException();

    }

    public boolean isOnClasspath(final IResource resource)
    {
        throw new UnsupportedOperationException();

    }

    public IEvaluationContext newEvaluationContext()
    {
        throw new UnsupportedOperationException();

    }

    public ITypeHierarchy newTypeHierarchy(final IRegion region,
            final IProgressMonitor monitor) throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public ITypeHierarchy newTypeHierarchy(final IRegion region,
            final WorkingCopyOwner owner, final IProgressMonitor monitor)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public ITypeHierarchy newTypeHierarchy(final IType type, final IRegion region,
            final IProgressMonitor monitor) throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public ITypeHierarchy newTypeHierarchy(final IType type, final IRegion region,
            final WorkingCopyOwner owner, final IProgressMonitor monitor)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IPath readOutputLocation()
    {
        throw new UnsupportedOperationException();

    }

    public IClasspathEntry[] readRawClasspath()
    {
        throw new UnsupportedOperationException();

    }

    public void setOption(final String optionName, final String optionValue)
    {
        throw new UnsupportedOperationException();

    }

    @SuppressWarnings("rawtypes")
    public void setOptions(final Map newOptions)
    {
        throw new UnsupportedOperationException();

    }

    public void setOutputLocation(final IPath path, final IProgressMonitor monitor)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public void setRawClasspath(final IClasspathEntry[] entries,
            final IPath outputLocation, final boolean canModifyResources,
            final IProgressMonitor monitor) throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public void setRawClasspath(final IClasspathEntry[] entries,
            final boolean canModifyResources, final IProgressMonitor monitor)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public void setRawClasspath(final IClasspathEntry[] entries,
            final IClasspathEntry[] referencedEntries, final IPath outputLocation,
            final IProgressMonitor monitor) throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public IClasspathEntry[] getReferencedClasspathEntries()
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public void setRawClasspath(final IClasspathEntry[] entries,
            final IProgressMonitor monitor) throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

    public void setRawClasspath(final IClasspathEntry[] entries,
            final IPath outputLocation, final IProgressMonitor monitor)
            throws JavaModelException
    {
        throw new UnsupportedOperationException();

    }

	public IModuleDescription findModule(String moduleName, WorkingCopyOwner owner) throws JavaModelException {
		throw new UnsupportedOperationException();
	}

	public IModuleDescription getModuleDescription() throws JavaModelException {
		throw new UnsupportedOperationException();
	}

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (!(o instanceof MockJavaProject))
            return false;

        MockJavaProject other = (MockJavaProject) o;
        return this._project.equals(other.getProject());
    }

    @Override
    public int hashCode()
    {
        return this._project.hashCode();
    }

    @Override
    public String toString()
    {
        return super.toString().concat("[JavaProject]");
    }

}
