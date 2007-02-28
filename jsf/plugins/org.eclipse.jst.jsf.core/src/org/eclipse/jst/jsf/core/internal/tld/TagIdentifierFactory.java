package org.eclipse.jst.jsf.core.internal.tld;

import org.eclipse.jst.jsf.common.internal.provisional.dom.TagIdentifier;
import org.w3c.dom.Element;

/**
 * Factory creating tag identifiers
 * 
 * @author cbateman
 *
 */
public final class TagIdentifierFactory 
{
    /**
     * Create a tag identifier based on a uri and tagName
     * 
     * @param uri
     * @param tagName
     * @return
     */
    public static TagIdentifier createJSPTagWrapper(final String uri, final String tagName)
    {
        return new JSPTagIdentifier(uri, tagName);
    }
    
    /**
     * @param element
     * @return a tag identifier based on a DOM element
     */
    public static TagIdentifier createDocumentTagWrapper(final Element element)
    {
        return new DocumentTagIdentifier(element);
    }
    
    private TagIdentifierFactory()
    {
        // static class, no external instantiation
    }
}

