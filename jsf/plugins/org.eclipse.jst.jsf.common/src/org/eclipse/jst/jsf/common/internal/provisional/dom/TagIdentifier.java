package org.eclipse.jst.jsf.common.internal.provisional.dom;

import javax.xml.namespace.QName;


/**
 * Creates an abstraction for a tag.  A tag is defined as a DOM Element whose
 * namespace uri may be defined outside of the DOM, such as in a JSP tag library
 * declaration.  This allows tags to be abstracted from actual DOM elements, which
 * is useful in situations like palette creation drops where the construction information
 * is known, but we are not ready to create and add a node to the document yet.
 * 
 * All tag TagIdentifier<i>s</i> should be considered immutable and idempotent.  
 * TagIdentifier instances may be cached by the factory.
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

    public final boolean equals(Object compareTo)
    {
        if (compareTo instanceof TagIdentifier)
        {
            return isSameTagType((TagIdentifier) compareTo);
        }
        return false;
    }
    
    public final int hashCode()
    {
        int hashCode = getTagName().hashCode();
        
        String uri = getUri();
        if (uri != null)
        {
            hashCode ^= uri.hashCode();
        }
        return hashCode;
    }
    
    public final boolean isSameTagType(TagIdentifier tagWrapper)
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
    
    /**
     * @return the QName equivalent.  Returns a new object on every invocation.
     */
    public final QName asQName()
    {
        return new QName(getUri(), getTagName());
    }
}
