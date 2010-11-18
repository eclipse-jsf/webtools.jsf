/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context.resolver.structureddocument.internal.impl;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;

/**
 * 
 * @author cbateman
 *
 */
/*package*/ class WorkspaceContextResolver implements IWorkspaceContextResolver 
{
	private final IStructuredDocumentContext		_context;
	
	/*package*/WorkspaceContextResolver(IStructuredDocumentContext context)
	{
		_context = context;
	}
	
	/**
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver#getProject()
	 */
	public IProject getProject() 
	{
		// copied from ModelManagerImpl (with some rework by C.B.)
		final String path = getPath();
		
		if (path == null)
		{
			return null;
		}

		return getProject(path);
	}

	private IProject getProject(String path)
	{
		// TOODO needs rework for linked resources
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IPath iPath = new Path(path);
		if (iPath.isAbsolute())
		{
			String  projectName = iPath.segment(0);
			
			IProject projects[] =  root.getProjects();
			
			for (int i = 0; i < projects.length; i++)
			{
				IProject project = projects[i];
				
				if (project.isOpen()
						&& projectName.equals(project.getFullPath().segment(0)))
				{
					return project;  //
				}
			}
		}

		return null;
	}

	public IResource getResource() 
	{
		final String path = getPath();
		
		if (path != null)
		{
			IProject project = getProject(path);
			
			if (project != null)
			{
				final IPath iPath = new Path(path);
				if (iPath.isAbsolute())
				{
					return project.getFile(iPath.removeFirstSegments(1));
				}
			}
		}
		return null;
	}
	
	private String getPath()
	{
		IStructuredModel model = null;
		
		try
		{
			model = StructuredModelManager.getModelManager().getExistingModelForRead(_context.getStructuredDocument());
			
			if (model == null)
				return null;
			String path = model.getBaseLocation();
			if (path == null || path.length() == 0) 
			{
				Object id = model.getId();
				if (id == null)
					return null;
				path = id.toString();
			}
			
			return path;
		}
		finally
		{
			if (model != null)
			{
				model.releaseFromRead();
			}
		}
	}

	/**
	 * @see org.eclipse.jst.jsf.context.resolver.IContextResolver#canResolveContext(org.eclipse.jst.jsf.context.IModelContext)
	 */
	public boolean canResolveContext(IModelContext modelContext) 
	{
		return (modelContext.getAdapter(IStructuredDocumentContext.class) != null);
	}
}
