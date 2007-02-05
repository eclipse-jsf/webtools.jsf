package org.eclipse.jst.pagedesigner.dom;

/**
 * A tag wrapper for a JSP tag.
 * 
 * @author cbateman
 *
 */
/*package*/ class JSPTagIdentifier extends TagIdentifier 
{
    private final String _uri;
    private final String _tagName;
    
    public JSPTagIdentifier(final String uri, final String tagName)
    {
        _uri = uri;
        _tagName = tagName;
    }
    public String getTagName() {
        return _tagName;
    }

    public String getUri() {
        return _uri;
    }

    public boolean isJSPTag() {
        return true;
    }
}
