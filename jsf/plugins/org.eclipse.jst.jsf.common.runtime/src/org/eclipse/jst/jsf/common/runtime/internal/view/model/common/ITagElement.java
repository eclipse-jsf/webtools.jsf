package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import java.io.Serializable;

/**
 * Super-interface of all JSF tag elements.
 * 
 * @author cbateman
 *
 */
public interface ITagElement extends Serializable
{

    /**
     * @return the name of the tag
     */
    public abstract String getName();

    /**
     * @return the namespace uri for this tag
     */
    public abstract String getUri();
    
    /**
     * @return the fully qualified class name in dot separated format
     * (i.e. javax.faces.webapp.ConverterTag)
     */
    public abstract String getTagHandlerClassName();
    
    /**
     * Signals that the tag element should  make any mutable data immutable
     * and throw exceptions if attempts are made to implement.  Flag must
     * latch and become irrevocable.
     */
    public abstract void setLocked();
    
    /**
     * @return true if setLocked has been called.
     */
    public abstract boolean isLocked();
}