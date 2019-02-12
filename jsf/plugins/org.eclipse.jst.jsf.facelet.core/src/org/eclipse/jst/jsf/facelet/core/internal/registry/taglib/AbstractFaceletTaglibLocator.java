/*******************************************************************************
 * Copyright (c) 2008, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.locator.AbstractLocator;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.Listener.TaglibChangedEvent;

/**
 * Parent of all locators of facelet taglibs.
 * 
 * @author cbateman
 * 
 */
public abstract class AbstractFaceletTaglibLocator
        extends
        AbstractLocator<Map<String, ? extends IFaceletTagRecord>, IProject, String>
        implements IFaceletTaglibLocator
{
    /**
     * @param id
     * @param displayName
     */
    public AbstractFaceletTaglibLocator(final String id,
            final String displayName)
    {
        super(id, displayName);
        // new MapMergingCompositionStrategy
        // <IProject, Map<String, IFaceletTagRecord>, Map<String,
        // IFaceletTagRecord>,
        // ILocator<Map<String, IFaceletTagRecord>, IProject, String>>
        // (new HashMap<String, IFaceletTagRecord>(), Collections.EMPTY_MAP)
    }

    /**
     * @return a list of all tag libraries known to this locator
     */
    @Override
    protected abstract Map<String, ? extends IFaceletTagRecord> doLocate(
            IProject context);

    /**
     * Listener argument must be of type Listener.
     * 
     * @see org.eclipse.jst.jsf.common.internal.locator.AbstractLocator#addListener(org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener)
     */
    @Override
    public void addListener(final ILocatorChangeListener listener)
    {
        if (!(listener instanceof Listener))
        {
            throw new IllegalArgumentException();
        }
        super.addListener(listener);
    }

    /**
     * @param listener
     */
    public void addListener(final Listener listener)
    {
        super.addListener(listener);
    }

    /**
     * @param taglibChangedEvent
     */
    protected void fireChangeEvent(final TaglibChangedEvent taglibChangedEvent)
    {
        super.fireChangeEvent(taglibChangedEvent);
    }

}
