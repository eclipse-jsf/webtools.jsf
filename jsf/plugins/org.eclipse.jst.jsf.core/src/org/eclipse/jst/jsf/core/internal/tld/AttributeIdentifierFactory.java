package org.eclipse.jst.jsf.core.internal.tld;

import org.eclipse.jst.jsf.common.dom.AttributeIdentifier;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;

/**
 * A factory for creating instances of AttributeIdentifier. Some results may be
 * cached or otherwise based on shared instances.
 * 
 * @author cbateman
 * 
 */
public final class AttributeIdentifierFactory
{
    /**
     * @param tagId
     * @param attributeName
     * @return an attribute id based on the tagid and attribute name provided.
     */
    public static AttributeIdentifier createAttributeIdentifier(
            final TagIdentifier tagId, final String attributeName)
    {
        return new SimpleAttributeIdentifier(tagId, attributeName);
    }

    private final static class SimpleAttributeIdentifier extends
            AttributeIdentifier
    {
        private final TagIdentifier _tagId;
        private final String        _attributeName;

        public SimpleAttributeIdentifier(final TagIdentifier tagId,
                String attributeName)
        {
            _tagId = tagId;
            _attributeName = attributeName;
        }

        @Override
        public String getName()
        {
            return _attributeName;
        }

        @Override
        public TagIdentifier getTagIdentifier()
        {
            return _tagId;
        }
    }
}
