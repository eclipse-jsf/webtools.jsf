package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

/**
 * Super-interface of all JSF tag elements.
 * 
 * @author cbateman
 *
 */
public interface ITagElement {

    /**
     * @return the name of the tag
     */
    public abstract String getName();

    /**
     * @return the fully qualified class name in dot separated format
     * (i.e. javax.faces.webapp.ConverterTag)
     */
    public abstract String getTagHandlerClassName();
}