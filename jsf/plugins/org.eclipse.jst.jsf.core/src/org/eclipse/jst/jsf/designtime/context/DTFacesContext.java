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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler.ViewHandlerException;

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
    private IAdaptable                             _contextObject;
    private DTUIViewRoot                          _viewRoot;

    /**
     * @param contextObject
     * @param locator
     */
    public DTFacesContext(final IAdaptable contextObject, final IExternalContextFactoryLocator locator)
    {
        _contextObject = contextObject;
        _locator = locator;
    }

    /**
     * @param locator
     * @deprecated Use DTFacesContext(IAdaptable, IExternalContextFactoryLocator)
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

    /**
     * @return the view root for the associated context object or null
     * if there is no context object.
     */
    public DTUIViewRoot getViewRoot()
    {
        IResource contextResource = adaptContextObject();
        // need to check for context object because of deprecated
        // constructor.
        if (contextResource != null)
        {
            if (_viewRoot == null)
            {
                // if the view root hasn't been created, then do so
                // and populate it
                IDTViewHandler  viewHandler = getViewHandler();
                
                if  (viewHandler != null)
                {
                    String viewId = viewHandler.getViewId(this, contextResource);
                    
                    try {
                        _viewRoot = viewHandler.createView(this, viewId);
                        
                        // per the createView contract, the framework
                        // will set the view id to the default value
                        // if the viewHandler doesn't set it.
                        if (_viewRoot.getViewId() == null)
                        {
                            _viewRoot.setViewId(viewId);
                        }
                    } catch (ViewHandlerException e) {
                        JSFCorePlugin.log(e, "While creating dt viewroot for viewId: "+viewId);
                    }
                }
            }
        }
        
        return _viewRoot;
    }
    
    private String _localeString = ""; //$NON-NLS-1$
    
    /**
     * @return the current locale string
     * @deprecated Use IDTViewHandler.calculateLocale instead
     */
    public String getLocaleString()
    {
        return _localeString;
    }
    
    /**
     * @param newLocaleString
     * @deprecated Use IDTViewHandler.calculateLocale instead
     */
    public void setLocaleString(final String newLocaleString)
    {
        _localeString = newLocaleString;
    }
    
    private IDTViewHandler getViewHandler()
    {
        IDTViewHandler viewHandler = null;
        IResource res = adaptContextObject();

        if (res != null)
        {
            final DesignTimeApplicationManager manager =
                DesignTimeApplicationManager.getInstance(res.getProject());
            if (manager != null)
            {
                viewHandler = manager.getViewHandler();
            }
        }

        return viewHandler;
    }
    
    /**
     * @return the resource correspondinng to context object or null
     * if it cannot be adapted to an IResource
     */
    public IResource adaptContextObject()
    {
        if (_contextObject instanceof IResource)
        {
            return (IResource) _contextObject;
        }
        else if  (_contextObject != null && 
                    _contextObject.getAdapter(IResource.class) != null)
        {
            return (IResource) _contextObject.getAdapter(IResource.class);
        }
        
        return null;
    }
}
