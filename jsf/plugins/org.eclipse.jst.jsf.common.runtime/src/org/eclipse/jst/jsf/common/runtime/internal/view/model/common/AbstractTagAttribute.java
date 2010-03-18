package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

/**
 * Super-class of all ITagAttribute concrete implementations.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractTagAttribute implements ITagAttribute
{
    /**
     * 
     */
    private static final long serialVersionUID = 6364594863141579928L;

    public abstract String getName();

    public abstract String getTargetNamespace();

    public abstract String getDescription();

    public abstract String getDisplayName();

    public abstract boolean isRequired();
}
