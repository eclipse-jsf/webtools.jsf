/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.view;

import org.eclipse.jst.jsf.designtime.context.DTFacesContext;

/**
 * <p>Interface for a factory that creates view definition adapters.</p>
 * 
 * @author cbateman
 *
 */
public interface IViewDefnAdapterFactory 
{
    /**
     * @param context
     * @param viewId
     * @return a view adapter for the viewid or null if this factory
     * cannot produce an adapter for the underlying view definition.
     */
    IViewDefnAdapter<?,?> createAdapter(DTFacesContext context, String viewId); 
}