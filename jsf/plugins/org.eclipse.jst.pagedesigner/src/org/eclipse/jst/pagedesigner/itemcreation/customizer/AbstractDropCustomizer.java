package org.eclipse.jst.pagedesigner.itemcreation.customizer;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

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
}
