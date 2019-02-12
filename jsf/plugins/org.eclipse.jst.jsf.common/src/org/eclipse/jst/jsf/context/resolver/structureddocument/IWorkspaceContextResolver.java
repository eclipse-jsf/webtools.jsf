/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context.resolver.structureddocument;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.context.resolver.IDocumentContextResolver;

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
