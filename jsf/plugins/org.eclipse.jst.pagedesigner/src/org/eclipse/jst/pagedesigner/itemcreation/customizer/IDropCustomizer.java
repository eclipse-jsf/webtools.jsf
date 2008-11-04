/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.itemcreation.customizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;

/**
 * Interface used to declare an object that customizes tag creation data prior
 * to a drop.  The drop customizer should be used to ellicit data on per-drop basis.
 * 
 * Clients should not implement this interface.  Extend AbstractDropCustomizer instead.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @author cbateman
 *
 */
public interface IDropCustomizer 
{
    /**
     * This is deprecated and is no longer called directly.  AbstractDropCustomizer
     * will call this from runCustomizer(IFile) in the default case.
     * 
     * @return the status condition of the customizer
     * @deprecated use runCustomizer(IFile, IDOMPosition) instead.
     */
    public IStatus runCustomizer();

    /**
     * Executed when the user performs a drop gesture for a tag, but before the
     * command is dispatched to create the tag in the target document.  Implementers
     * may do calculations or raise customization UI.  The return value is used
     * to decide whether to continue.  Values other than OK and CANCEL are currently
     * ignored.  If the return value has a severity of CANCEL, then the drop will
     * be aborted.  If the return severity is OK, then getDropCustomizationData will
     * be called and it's value will be passed to the ITagCreator framework through
     * the CreationData object.
     * 
     * NOTE: This method may be called on the UI thread, although this is not guaranteed.
     * Implementer should ensure that anything that is long-running (such as a dialog)
     * is user-cancellable and that any UI code is run on the display thread.
     * @param file 
     * @param position the position in the target file where the new tag will be dropped
     * 
     * @return the status condition of the customizer
     */

    public IStatus runCustomizer(final IFile file, final IDOMPosition position);

    /**
     * @return the customization data.  This method will only be called after runCustomizer
     * and only if runCustomizer returns IStatus.OK in its severity.  The adaptable will
     * be passed to the ITagCreator framework through the CreationData value object.  You
     * may return null even if runCustomizer() returns OK.
     */
    public IAdaptable getDropCustomizationData();
}
