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
