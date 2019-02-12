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

import java.util.List;

import org.eclipse.jst.jsf.context.resolver.IDocumentContextResolver;

/**
 * Resolves meta-data for a particular context
 * 
 * This interface may sub-classed or implemented by clients
 * 
 * @author cbateman
 *
 */
public interface IMetadataContextResolver extends IDocumentContextResolver 
{
    /**
     * @param key 
     * @return a list of one or more String values associated with key
     * for the current context location. 
     */
    List getPropertyValue(String key);
}
