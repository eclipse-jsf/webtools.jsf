/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.resources;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.locator.AbstractLocator;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceChangeListener.JSFResourceChangedEvent;

/**
 * A locator that finds JSF resources.
 * 
 * @author cbateman
 * 
 */
public abstract class AbstractJSFResourceLocator extends
        AbstractLocator<List<IJSFResourceFragment>, IProject, String> implements
        IJSFResourceLocator
{

    /**
     * @param id
     * @param displayName
     * @param noResultValue
     * @param mutableListenerList
     */
    protected AbstractJSFResourceLocator(String id, String displayName,
            List<IJSFResourceFragment> noResultValue,
            CopyOnWriteArrayList<ILocatorChangeListener> mutableListenerList)
    {
        super(id, displayName, noResultValue, mutableListenerList);
    }

    protected abstract List<IJSFResourceFragment> doLocate(IProject context);

    /**
     * TODO: would be better to have a private interface to call fire.
     * 
     * @param event
     */
    protected void fireChangeEvent(final JSFResourceChangedEvent event)
    {
        super.fireChangeEvent(event);
    }

    public void addListener(JSFResourceChangeListener listener)
    {
        super.addListener(listener);
    }

    public void removeListener(JSFResourceChangeListener listener)
    {
        super.removeListener(listener);
    }

}
