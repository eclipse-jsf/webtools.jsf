package org.eclipse.jst.jsf.designtime.internal.resources;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.locator.ILocator;

/**
 * A JSF resource locator.
 * 
 * @author cbateman
 *
 */
public interface IJSFResourceLocator extends ILocator<List<IJSFResourceFragment>, IProject, String>
{
    /**
     * @param listener
     */
    public void addListener(final JSFResourceChangeListener listener);
    /**
     * @param listener
     */
    public void removeListener(final JSFResourceChangeListener listener);
}
