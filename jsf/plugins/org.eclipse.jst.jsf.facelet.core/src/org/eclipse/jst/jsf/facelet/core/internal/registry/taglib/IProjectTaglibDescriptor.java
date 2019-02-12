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

import java.util.Collection;

import org.eclipse.jst.jsf.common.internal.managedobject.IManagedObject;

/**
 * A descriptor for all facelet taglibs in a project.
 * 
 * @author cbateman
 *
 */
public interface IProjectTaglibDescriptor extends IManagedObject
{
    /**
     * @return get the tag library records
     */
    public Collection<? extends IFaceletTagRecord> getTagLibraries();

    /**
     * @param uri
     * @return get the tag library for uri
     */
    public IFaceletTagRecord getTagLibrary(final String uri);
    
    /**
     * @param listener
     */
    public void addListener(final Listener  listener);
    
    /**
     * @param listener
     */
    public void removeListener(final Listener listener);
}
