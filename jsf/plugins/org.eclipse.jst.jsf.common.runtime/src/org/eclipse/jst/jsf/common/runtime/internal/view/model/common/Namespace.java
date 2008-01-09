package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import java.util.Collection;

/**
 * Abstracts an xml namespace used to define a set of view tags
 *
 */
public abstract class Namespace 
{
    /**
     * @return the namespace uri.  MUST NOT BE NULL
     */
    public abstract String getNSUri();

    /**
     * @return the view elements in this namespace. May be
     * empty but MUST NOT BE NULL.
     */
    public abstract Collection getViewElements();

    /**
     * @return a user readable display name for this namespace or
     * null if none.
     */
    public abstract String getDisplayName();
    
    public String toString()
    {
        return getNSUri();
    }
}
