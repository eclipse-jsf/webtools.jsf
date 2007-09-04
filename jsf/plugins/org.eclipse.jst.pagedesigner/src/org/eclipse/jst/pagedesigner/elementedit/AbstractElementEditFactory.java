package org.eclipse.jst.pagedesigner.elementedit;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;

/**
 * @author cbateman
 *
 */
public abstract class AbstractElementEditFactory implements IElementEditFactory 
{
    private String _supportedUri;
    
    /**
     * @param uri
     */
    protected AbstractElementEditFactory(final String uri)
    {
        _supportedUri = uri;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEditFactory#createElementEdit(org.eclipse.jst.jsf.common.dom.TagIdentifier)
     */
    public abstract IElementEdit createElementEdit(TagIdentifier tag); 

    public String getSupportedURI() 
    {
        return _supportedUri;
    }

}
