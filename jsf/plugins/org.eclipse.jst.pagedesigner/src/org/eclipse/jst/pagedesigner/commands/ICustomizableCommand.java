package org.eclipse.jst.pagedesigner.commands;

import org.eclipse.core.runtime.IAdaptable;

/**
 * A command that can be customized with drop customizable command
 * 
 * This is not public API.  Clients should not use.
 * 
 * @author cbateman
 *
 */
public interface ICustomizableCommand
{
    /**
     * Sets the customization data
     * 
     * @param customizationData
     */
    public void setCustomizationData(IAdaptable customizationData);
}
