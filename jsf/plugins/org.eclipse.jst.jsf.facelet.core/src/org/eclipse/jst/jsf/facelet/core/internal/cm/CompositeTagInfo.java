package org.eclipse.jst.jsf.facelet.core.internal.cm;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;
import org.eclipse.jst.jsf.facelet.core.internal.cm.strategy.TagInfoStrategyComposite;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;

/**
 * A tag info that composes a number of other tag infos selected from a 
 * TagInfoStrategyComposite.
 * 
 * @author cbateman
 *
 */
public abstract class CompositeTagInfo extends TagInfo
{
    private final TagInfoStrategyComposite _compositeStrategy;
    private final String _uri;

    /**
     * @param uri 
     * @param compositeStrategy
     */
    protected CompositeTagInfo(final String uri, final TagInfoStrategyComposite compositeStrategy)
    {
        _uri = uri;
        _compositeStrategy = compositeStrategy;
    }
    @Override
    public Object getTagProperty(String tagName, String key)
    {
        final TagIdentifier tagId = TagIdentifierFactory.createJSPTagWrapper(
                _uri, tagName);
        _compositeStrategy.resetIterator();

        for (TagInfo tagInfo = getNextExternalInfo(tagId); tagInfo != _compositeStrategy
                .getNoResult(); tagInfo = getNextExternalInfo(tagId))
        {
            try
            {
                if (tagInfo != _compositeStrategy.getNoResult())
                {
                    final Object value = tagInfo.getTagProperty(tagName, key);

                    if (value != null)
                    {
                        return value;
                    }
                }

                // fall-through
            }
            catch (final Exception e)
            {
                FaceletCorePlugin.log("During meta-data strategy", e); //$NON-NLS-1$
            }
        }

        return null;

    }

    /**
     * @param tagName
     * @return a named node map of known attributes for the tag, or null if not
     *         found
     */
    @Override
    public CMNamedNodeMap getAttributes(String tagName)
    {
        final TagIdentifier tagId = TagIdentifierFactory.createJSPTagWrapper(
                _uri, tagName);
        _compositeStrategy.resetIterator();

        for (TagInfo tagInfo = getNextExternalInfo(tagId); tagInfo != _compositeStrategy
                .getNoResult(); tagInfo = getNextExternalInfo(tagId))
        {
            try
            {
                if (tagInfo != _compositeStrategy.getNoResult())
                {
                    final CMNamedNodeMap nodeMap = tagInfo
                            .getAttributes(tagName);

                    if (nodeMap != null)
                    {
                        return nodeMap;
                    }
                }

                // fall-through
            }
            catch (final Exception e)
            {
                FaceletCorePlugin.log("During meta-data strategy", e); //$NON-NLS-1$
            }
        }

        return null;
    }

    private TagInfo getNextExternalInfo(final TagIdentifier input)
    {
        return _compositeStrategy.perform(input);
    }
}
