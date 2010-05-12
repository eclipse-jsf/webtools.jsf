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
import org.eclipse.jst.jsf.common.internal.resource.EventResult;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler;
import org.eclipse.jst.jsf.designtime.internal.view.IViewRootHandle;

/**
 * Represents a design-time version of the JSF FacesContext for a particular web
 * application.
 * 
 * Client may not sub-class.
 * 
 * @author cbateman
 * 
 */
public final class DTFacesContext
{
    private final IExternalContextFactoryLocator _locator;
    private final IAdaptable                     _contextObject;
    private final ViewRootHolder                 _viewRootHolder;
    private LifecycleListener                    _lifecycleListener;
    private IResourceLifecycleListener           _listener;

    /**
     * @param contextObject
     * @param locator
     */
    public DTFacesContext(final IAdaptable contextObject,
            final IExternalContextFactoryLocator locator)
    {
        _contextObject = contextObject;
        _locator = locator;

        // init last -- NOTE: it is essential that the new Object be unique
        _viewRootHolder = new ViewRootHolder(this, new Object());
    }

    /**
     * @param locator
     * @deprecated Use DTFacesContext(IAdaptable,
     *             IExternalContextFactoryLocator)
     */
    @Deprecated
    public DTFacesContext(final IExternalContextFactoryLocator locator)
    {
        _locator = locator;
        _contextObject = null;

        // init last -- NOTE: it is essential that the new Object be unique
        _viewRootHolder = new ViewRootHolder(this, new Object());
    }

    /**
     * THIS IS NOT AN API METHOD. External clients must not call.
     * 
     * initilaize the faces context.
     * @param lifecycleListener 
     */
    public void initialize(final LifecycleListener lifecycleListener)
    {
        final IResource res = adaptContextObject();
        _lifecycleListener = lifecycleListener;
        
        if (res != null && _lifecycleListener != null)
        {
            _listener = new IResourceLifecycleListener()
            {
                public EventResult acceptEvent(ResourceLifecycleEvent event)
                {
                    if (res.equals(event.getAffectedResource()))
                    {
                        if (event.getEventType() == EventType.RESOURCE_INACCESSIBLE)
                        {
                            _lifecycleListener.removeResource(res);
                        }
                    }
                    return EventResult.getDefaultEventResult();
                }
            };

            _lifecycleListener.addResource(res);
            _lifecycleListener.addListener(_listener);
        }
    }

    /**
     * @param contextObject --
     *            the object corresponding to the external context. Usually an
     *            IFile point to a file containing the external context. It must
     *            be adaptable to an IFile.
     * 
     * @return the designtime external context or null if one is not defined for
     *         this contextObject
     */
    public IDTExternalContext getDTExternalContext(
            final IAdaptable contextObject)
    {
        IDTExternalContext externalContext = null;

        // if the context object is an IFile or can be adapted to one, create a
        // new externalContext for it
        if (contextObject instanceof IFile
                || (contextObject != null && contextObject
                        .getAdapter(IFile.class) != null))
        {

            final AbstractDTExternalContextFactory factory = _locator
                    .getFactory();
            externalContext = factory.create(contextObject);
        }
        return externalContext;
    }

    /**
     * <p>
     * Returns a new copy of the view root handle.
     * </p>
     * 
     * return null if the value of the view root
     * 
     * @return the cached view root. May be null.
     */
    public IViewRootHandle getViewRootHandle()
    {
        return _viewRootHolder.clone();
    }

    /**
     * @deprecated
     */
    @Deprecated
    private String _localeString = ""; //$NON-NLS-1$

    /**
     * @return the current locale string
     * @deprecated Use IDTViewHandler.calculateLocale instead
     */
    @Deprecated
    public String getLocaleString()
    {
        return _localeString;
    }

    /**
     * @param newLocaleString
     * @deprecated Use IDTViewHandler.calculateLocale instead
     */
    @Deprecated
    public void setLocaleString(final String newLocaleString)
    {
        _localeString = newLocaleString;
    }

    /**
     * @return the resource correspondinng to context object or null if it
     *         cannot be adapted to an IResource
     */
    public IResource adaptContextObject()
    {
        if (_contextObject instanceof IResource)
        {
            return (IResource) _contextObject;
        }
        else if (_contextObject != null
                && _contextObject.getAdapter(IResource.class) != null)
        {
            return (IResource) _contextObject.getAdapter(IResource.class);
        }
        return null;
    }

    IDTViewHandler getViewHandler(final IResource res)
    {
        IDTViewHandler viewHandler = null;

        if (res != null)
        {
            final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                    .getInstance(res.getProject());
            if (manager != null)
            {
                viewHandler = manager.getViewHandler();
            }
        }
        return viewHandler;
    }
}
