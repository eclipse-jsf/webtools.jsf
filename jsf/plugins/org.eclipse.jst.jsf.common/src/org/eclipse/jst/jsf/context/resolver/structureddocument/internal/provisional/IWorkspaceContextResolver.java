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

package org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.context.resolver.internal.provisional.IDocumentContextResolver;

/**
 * A resolver to determine the current context within the Eclipse
 * workspace.
 * 
 * This interface may be sub-classed or implemented by clients
 * 
 * @author cbateman
 *
 */
public interface IWorkspaceContextResolver extends IDocumentContextResolver
{
	/**
	 * @return the project that contains the document context
	 * or null if it cannot be determined
	 */
	IProject getProject();
	
	/**
	 * @return the resource that contains the document context 
	 * or null if it cannot be determined.
	 * This should normally be an IFile.
	 */
	IResource getResource();
}
