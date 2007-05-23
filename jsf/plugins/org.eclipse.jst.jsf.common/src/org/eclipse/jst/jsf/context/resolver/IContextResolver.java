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
