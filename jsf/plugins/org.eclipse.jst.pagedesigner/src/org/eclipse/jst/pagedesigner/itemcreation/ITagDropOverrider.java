package org.eclipse.jst.pagedesigner.itemcreation;

/**
 * Used to override the data used in a tag drop in the CreateItemCommand
 * <p>
 * This is NOT API.  Clients should not use this interface as it may change in the future.
 */
public interface ITagDropOverrider
{

    /**
     * The default overrider
     *
     */
    static class DefaultTagDropOverrider implements ITagDropOverrider
    {
        private final String _tagNameOverrride;
        private final String _uriOverride;
        private final String _defaultPrefixOverride;
        
        public DefaultTagDropOverrider(final String uriOverride, final String tagNameOverride, final String defaultPrefixOverride)
        {
            super();
            _tagNameOverrride = tagNameOverride;
            _uriOverride = uriOverride;
            _defaultPrefixOverride = defaultPrefixOverride;
        }

        public String getTagNameOverride()
        {
            return _tagNameOverrride;
        }

        public String getUriOverride()
        {
            return _uriOverride;
        }
        
        public String getDefaultPrefixOverride()
        {
            return _defaultPrefixOverride;
        }
        
    }
    /**
     * @return the new uri to use or null if shouldn't override
     */
    String getUriOverride();

    /**
     * @return the new tag name or null if shouldn't override
     */
    String getTagNameOverride();
    
    /**
     * @return the new default prefix or null if shouldn't override
     */
    String getDefaultPrefixOverride();
}
