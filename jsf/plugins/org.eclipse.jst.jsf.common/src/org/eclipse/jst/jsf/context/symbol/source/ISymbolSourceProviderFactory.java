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

package org.eclipse.jst.jsf.context.symbol.source;

import org.eclipse.core.resources.IProject;

/**
 * Implemented by extensions wishing to use symbolSourceProvider extension point
 * to contribute designtime symbols to the JSF symbol resolver framework.
 * 
 * Instance factory class must implement a zero-argument default constructor
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
