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

package org.eclipse.jst.jsf.context.symbol.internal.provisional.source;

import org.eclipse.core.resources.IProject;

/**
 * Default implementation of ISymbolSourceProviderFactory.  Implementors may 
 * sub-class or create their own separate factory class.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractSymbolSourceProviderFactory implements
        ISymbolSourceProviderFactory 
{
    private IProject      _project;
    
    /**
     * Default constructor used by extension manager to create this factory
     */
    public AbstractSymbolSourceProviderFactory()
    {
        // do nothing
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.source.ISymbolSourceProviderFactory#createInstance(org.eclipse.core.resources.IProject)
     */
    public final ISymbolSourceProvider createInstance(IProject project) 
    {
        _project = project;
        return create(project);
    }
    
    /**
     * Override this method to do specialization of provider creation
     * 
     * @param project
     * @return the symbol source provider created by the subclass
     */
    protected abstract ISymbolSourceProvider create(IProject project);
 
    /**
     * @return the IProject that this source provider is associated with.
     */
    protected final IProject getProject() 
    {
        return _project;
    }
}
