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

/**
 * The super-interface for all context resolvers that are related
 * to IDocumentContext's.
 * 
 * This interface may NOT be implemented by clients directly but may be sub-classed.
 * Use AbstractDocumentContextResolver for implementation.
 * 
 * @author cbateman
 *
 */
public interface IDocumentContextResolver extends IContextResolver 
{
	// currently empty
}
