package org.eclipse.jst.jsf.common.dom;

import org.w3c.dom.Node;

/**
 * Generic adapter for DOM attribute structures.
 * 
 * @author cbateman
 * 
 * <p>
 * <b>Provisional API - subject to change</b>
 * </p>
 */
public abstract class AttrDOMAdapter extends DOMAdapter
{

    private AttributeIdentifier     _attributeId; // lazily instantiated.
    private final ElementDOMAdapter _owner;

    /**
     * @param owner
     */
    protected AttrDOMAdapter(final ElementDOMAdapter owner)
    {
        _owner = owner;
    }

    /**
     * The default implementation always returns the owner specified in the
     * constructor.
     * 
     * @return the element that owns this attribute
     */
    public ElementDOMAdapter getOwningElement()
    {
        return _owner;
    }

    /**
     * @return the value of the attribute. May return null if the attribute has
     *         no value.
     */
    public abstract String getValue();

    @Override
    public final short getNodeType()
    {
        return Node.ATTRIBUTE_NODE;
    }

    /**
     * @return the attribute identifier
     */
    public final AttributeIdentifier getAttributeIdentifier()
    {
        synchronized (this)
        {
            if (_attributeId == null)
            {
                _attributeId = new MyAttributeIdentifier();
            }
        }
        return _attributeId;
    }

    private class MyAttributeIdentifier extends AttributeIdentifier
    {

        @Override
        public String getName()
        {
            return AttrDOMAdapter.this.getLocalName();
        }

        @Override
        public TagIdentifier getTagIdentifier()
        {
            return getOwningElement().getTagId();
        }

    }
}
