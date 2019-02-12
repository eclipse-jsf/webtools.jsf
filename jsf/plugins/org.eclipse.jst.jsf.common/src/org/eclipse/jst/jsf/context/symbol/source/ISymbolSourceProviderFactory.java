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

package org.eclipse.jst.jsf.context.symbol.source;

import org.eclipse.core.resources.IProject;

/**
 * Implemented by extensions wishing to use symbolSourceProvider extension point
 * to contribute designtime symbols to the JSF symbol resolver framework.
 * 
 * Instance factory class must implement a zero-argument default constructor
 * 
 * <p><b>Provisional API - subject to change</b></p> 
 * 
 * @author cbateman
 *
 */
public interface ISymbolSourceProviderFactory 
{
    /**
     * @param project
     * @return a new instance of a symbol source provider for project
     */
    ISymbolSourceProvider createInstance(IProject project);
}
