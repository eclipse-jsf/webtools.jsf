package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

/**
 * Abstract a tag element used to construct a view element
 * 
 * @author cbateman
 *
 */
public abstract class TagElement implements ITagElement 
{
    /**
     * 
     */
    private static final long serialVersionUID = 7885641652240047924L;

    /* (non-Javadoc)
     * @see viewhandlerprototype.model.ITagElement#getName()
     */
    public abstract String getName();

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement#getUri()
     */
    public abstract String getUri();

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement#getTagHandlerClassName()
     */
    public abstract String getTagHandlerClassName();
}
