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

import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.StalenessListener;

/**
 * A handle to a view root.
 * 
 * @author cbateman
 * 
 */
public interface IViewRootHandle
{
    /**
     * <p>
     * This returns the copy of the view root cached by the faces context. If
     * updateViewRoot has never been called (or if it was called but never
     * succeeded) it may return null.
     * </p>
     * 
     * <p>
     * This method should be cheap and avoid blocking
     * </p>
     * 
     * return null if the value of the view root
     * 
     * @return the cached view root. May be null.
     */
    DTUIViewRoot getCachedViewRoot();

    /**
     * NOTE: this method may be long running. If you can tolerate a possibly
     * stale value more quickly, use getCachedViewRoot(). NEVER run this method
     * on the main UI thread.
     * 
     * A good strategy for invoking is as follows:
     * 
     * DTUIViewRoot viewRoot = handle.getCachedViewRoot();
     * 
     * <pre>
     * if (viewRoot == null || viewRoot.isStale())
     * {
     *      spawnTread that calls updateViewRoot
     * }
     * 
     * </pre>
     * 
     * @return the view root for the associated context object or null if there
     *         is no context object.
     */
    DTUIViewRoot updateViewRoot();

    /**
     * Adds a staleness listener to the currently cached view root.  If there
     * currently is no cached view root, then the listener will be stored
     * until a updateViewRoot is successfully called.
     * @param listener
     */
    void addListener(final StalenessListener listener);
    
    /**
     * Removes a staleness listener from the currently cached view root.  If
     * there  is no currently cached root, then the listener will be stored
     * until one is found.  The call will then attempt to remove it.
     * @param listener
     */
    void removeListener(final StalenessListener listener);
}
