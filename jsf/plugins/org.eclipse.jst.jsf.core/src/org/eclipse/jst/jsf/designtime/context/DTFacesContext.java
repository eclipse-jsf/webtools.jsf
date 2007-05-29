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

package org.eclipse.jst.jsf.designtime.context;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;

/**
 * Represents a design-time version of the JSF FacesContext for a particular
 * web application.
 * 
 * Client may not sub-class.
 * 
 * @author cbateman
 *
 */
public final class DTFacesContext 
{
    private final IExternalContextFactoryLocator   _locator;
    
    /**
     * @param locator
     */
    public DTFacesContext(final IExternalContextFactoryLocator locator)
    {
        _locator = locator;
    }
    
    /**
     * @param contextObject -- the object corresponding to the external context.
     * Usually an IFile point to a file containing the external context.  It must
     * be adaptable to an IFile.
     * 
     * @return the designtime external context or null if one is not defined
     * for this contextObject
     */
    public IDTExternalContext getDTExternalContext(final IAdaptable contextObject)
    {
        
        IDTExternalContext externalContext = null;
        
        // if the context object is an IFile or can be adapted to one, create a
        // new externalContext for it
        if (contextObject instanceof IFile
                || (contextObject != null && contextObject.getAdapter(IFile.class)!=null))
        {
                
            AbstractDTExternalContextFactory factory = _locator.getFactory();
            externalContext = factory.create(contextObject);
        }
        
        return externalContext;
    }
    
    private String _localeString = ""; //$NON-NLS-1$
    
    /**
     * @return the current locale string
     */
    public String getLocaleString()
    {
        return _localeString;
    }
    
    /**
     * @param newLocaleString
     */
    public void setLocaleString(final String newLocaleString)
    {
        _localeString = newLocaleString;
    }
}
