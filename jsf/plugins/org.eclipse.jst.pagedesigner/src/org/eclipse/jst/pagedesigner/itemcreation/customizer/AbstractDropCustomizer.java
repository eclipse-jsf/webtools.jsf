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
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;

/**
 * Clients should extend to implement their own IDropCustomizer.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author cbateman
 *
 */

public abstract class AbstractDropCustomizer implements IDropCustomizer 
{
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.customizer.IDropCustomizer#getDropCustomizationData()
     */
    public IAdaptable getDropCustomizationData() 
    {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.customizer.IDropCustomizer#runCustomizer()
     */
    public IStatus runCustomizer() 
    {
        return Status.OK_STATUS;
    }
    
    public IStatus runCustomizer(final IFile file, final IDOMPosition position)
    {
        // backward compatibility: call the deprecated method to ensure that
        // existing users are not broken
        return runCustomizer();
    }
}
