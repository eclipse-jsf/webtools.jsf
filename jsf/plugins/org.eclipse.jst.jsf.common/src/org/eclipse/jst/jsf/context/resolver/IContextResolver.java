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
package org.eclipse.jst.jsf.context.resolver;

import org.eclipse.jst.jsf.context.IModelContext;

/**
 * Super type of all context resolvers.  
 * 
 * May be sub-classed but should not be implemented directly.
 * Use AbstractContextResolver to implement.
 * 
 * @author cbateman
 *
 */
public interface IContextResolver 
{
	/**
	 * @param modelContext
	 * @return true if this resolver can resolve context information
	 * based on this context
	 */
	boolean canResolveContext(IModelContext  modelContext);
}
