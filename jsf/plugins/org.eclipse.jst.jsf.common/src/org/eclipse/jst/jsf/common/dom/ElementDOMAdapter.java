package org.eclipse.jst.jsf.common.dom;

import java.util.Map;

import org.w3c.dom.Node;

/**
 * T
 * @author cbateman
 * 
 * <p><b>Provisional API - subject to change</b></p>
 *
 */
public abstract class ElementDOMAdapter extends DOMAdapter
{
    private TagIdentifier       _tagIdentifier; // lazily initialized
    
    /**
     * Map should be considered immutable and may throw exceptions if
     * mutations are attempted.
     * 
     * @return a nodeName keyed map of attributes belonging to this element
     */
    public abstract Map<String, ? extends AttrDOMAdapter> getAttributes();

    @Override
    public final short getNodeType()
    {
        return  Node.ELEMENT_NODE;
    }

    /**
     * @return the namespace uri for element or null if not applicable (i.e. HTML).
     */
    public abstract String getNamespace();
    
    /**
     * @return the tag identifier for this attribute
     */
    public final TagIdentifier getTagId()
    {
        synchronized(this)
        {
            if (_tagIdentifier == null)
            {
                _tagIdentifier = new MyTagIdentifier();
            }
        }
        return _tagIdentifier; 
    }
    
    private final class MyTagIdentifier extends TagIdentifier
    {
        @Override
        public String getTagName()
        {
            return getLocalName();
        }

        @Override
        public String getUri()
        {
            return getNamespace();
        }

        @Override
        public boolean isJSPTag()
        {
            // shouldn't be called
            throw new UnsupportedOperationException("jsp flag not supported on this identifier");
        }
        
    }
}
