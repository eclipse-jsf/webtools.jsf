package org.eclipse.jst.jsf.designtime.internal.resources;

/**
 * A container for JSFResource's.  This is a fragment in that it's identifier
 * may not point to a valid JSFResource but rather a portion of that nonetheless
 * maps to some interesting underlying contain object such as an IFolder.
 * 
 * @author cbateman
 *
 */
public abstract class JSFResourceContainer extends JSFResourceFragment implements IJSFResourceContainer
{

    /**
     * @param id
     */
    public JSFResourceContainer(ResourceFragmentIdentifier id)
    {
        super(id, Type.CONTAINER);
    }

    @Override
    public abstract boolean isAccessible();

}
