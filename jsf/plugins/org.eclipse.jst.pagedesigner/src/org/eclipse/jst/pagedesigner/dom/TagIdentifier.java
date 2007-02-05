package org.eclipse.jst.pagedesigner.dom;


/**
 * Creates an abstraction for a tag.  A tag is defined is dom Element whose
 * namespace uri may be defined outside of the DOM, such as in a JSP tag library
 * declaration.  This allows tags to abstracted from actual DOM elements, which
 * is useful in situations like palette creation drops where the construction information
 * is known, but we are not ready to create and add a node to the document yet.
 * 
 * All tag wrappers should be considered immutable and idempotent.  TagWrapper instances
 * may be cached by the factory.
 * 
 * @author cbateman
 *
 */
public abstract class TagIdentifier 
{
    /**
     * @return the uri that uniquely identifies the tag.  
     * 
     * i.e.
     * 
     * If the tag is defined by an XML namespace, then that uri string will be returned.
     * If the tag is defined by a JSP tag library, then the tag library uri should
     * be returned.
     */
    public abstract String getUri();
    
    /**
     * @return the local name of the tag (without namespace prefix)
     */
    public abstract String getTagName();
    
    /**
     * @return true if this tag is a JSP tag
     */
    public abstract boolean isJSPTag();

    public boolean isSameTagType(TagIdentifier tagWrapper)
    {
        if (tagWrapper == this)
        {
            return true;
        }
        
        final String uri = tagWrapper.getUri();
        
        if (uri == null && getUri() != null)
        {
            return false;
        }
        else if (uri.equals(getUri()))
        {
            final String tagName = tagWrapper.getTagName();
            
            if (tagName == null && getTagName() != null)
            {
                return false;
            }

            // uri and tag name must both the same for it to be the same type
            // TODO: the ignore case thing is dependent on the type of container document
            if (tagName.equalsIgnoreCase((getTagName())))
            {
                return true;
            }
        }

        // fall-through, not same
        return false;
    }
}
