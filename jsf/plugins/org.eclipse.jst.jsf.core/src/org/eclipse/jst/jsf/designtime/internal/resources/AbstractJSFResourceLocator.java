package org.eclipse.jst.jsf.designtime.internal.resources;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.locator.AbstractLocator;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;

/**
 * A locator that finds JSF resources.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractJSFResourceLocator extends
        AbstractLocator<List<JSFResource>, IProject, String> implements IJSFResourceLocator
{

    /**
     * @param id
     * @param displayName
     * @param noResultValue
     * @param mutableListenerList
     */
    protected AbstractJSFResourceLocator(String id, String displayName,
            List<JSFResource> noResultValue,
            CopyOnWriteArrayList<ILocatorChangeListener> mutableListenerList)
    {
        super(id, displayName, noResultValue, mutableListenerList);
    }

    protected abstract List<JSFResource> doLocate(IProject context);

}
