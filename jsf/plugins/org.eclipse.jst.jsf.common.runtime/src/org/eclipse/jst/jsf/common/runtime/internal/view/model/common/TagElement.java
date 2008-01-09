package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

/**
 * Abstract a tag element used to construct a view element
 * 
 * @author cbateman
 *
 */
public abstract class TagElement implements ITagElement 
{
    /* (non-Javadoc)
     * @see viewhandlerprototype.model.ITagElement#getName()
     */
    public abstract String getName();

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement#getTagHandlerClassName()
     */
    public abstract String getTagHandlerClassName();
}
