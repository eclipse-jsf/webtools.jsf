/*******************************************************************************
 * Copyright (c) 2010, 2022 IBM Corporation and others.
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

import java.net.URI;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.content.IContentTypeMatcher;

public class MockProject extends MockContainer implements IProject
{

    private Set<String> _natures = new HashSet<String>();

    public MockProject(final IPath path, final IMockResourceFactory resFactory)
    {
        super(IResource.PROJECT,path, resFactory);
        setProject(this);
    }

    @SuppressWarnings("rawtypes")
    public void build(int kind, String builderName, Map args,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void build(int kind, IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

	public void build(IBuildConfiguration config, int kind,
			IProgressMonitor monitor) throws CoreException
	{
        throw new UnsupportedOperationException();
	}

    public void close(IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void create(IProjectDescription description, IProgressMonitor monitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void create(IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void create(IProjectDescription description, int updateFlags,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void delete(boolean deleteContent, boolean force,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IContentTypeMatcher getContentTypeMatcher() throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IProjectDescription getDescription() throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IFolder getFolder(String name)
    {
        return getFolder(new Path(name));
    }

    public IProjectNature getNature(String natureId) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IPath getWorkingLocation(String id)
    {
        throw new UnsupportedOperationException();
    }

    public IProject[] getReferencedProjects() throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IProject[] getReferencingProjects()
    {
        throw new UnsupportedOperationException();
    }

    public void addNature(String natureId)
    {
        _natures.add(natureId);
    }
    public boolean hasNature(String natureId) throws CoreException
    {
        return _natures.contains(natureId);
    }

    public boolean isNatureEnabled(String natureId) throws CoreException
    {
        // for now, assume if we have the nature then it is enabled.
        return hasNature(natureId);
    }

    public boolean isOpen()
    {
        throw new UnsupportedOperationException();
    }

    public void loadSnapshot(int options, URI snapshotLocation,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void move(IProjectDescription description, boolean force,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void open(int updateFlags, IProgressMonitor monitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void open(IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void saveSnapshot(int options, URI snapshotLocation,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void setDescription(IProjectDescription description,
            IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void setDescription(IProjectDescription description,
            int updateFlags, IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public IContainer getParent()
    {
        return getWorkspace().getRoot();
    }

	public IBuildConfiguration getActiveBuildConfig() throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	public IBuildConfiguration getBuildConfig(String configName)
			throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	public IBuildConfiguration[] getBuildConfigs() throws CoreException {
		// TODO Auto-generated method stub
		return new IBuildConfiguration[0];
	}

	public IBuildConfiguration[] getReferencedBuildConfigs(String configName,
			boolean includeMissing) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasBuildConfig(String configName) throws CoreException {
		// TODO Auto-generated method stub
		return false;
	}

	public void clearCachedDynamicReferences() {
		// TODO Auto-generated method stub
		
	}

	public String getDefaultLineSeparator() throws CoreException {
		return "\n";
	}

}
