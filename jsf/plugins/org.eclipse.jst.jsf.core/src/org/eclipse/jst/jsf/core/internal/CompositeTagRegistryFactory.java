package org.eclipse.jst.jsf.core.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jst.jsf.common.internal.policy.CanonicallyOrderedIteratorPolicy;
import org.eclipse.jst.jsf.common.internal.policy.IIteratorPolicy;
import org.eclipse.jst.jsf.common.internal.strategy.IIdentifiableStrategy;
import org.eclipse.jst.jsf.common.internal.strategy.IteratorPolicyBasedStrategyComposite;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory.TagRegistryFactoryException;

/**
 * Employs a policy-based strategy to construct a TagRegistry for a particular
 * content-type in a particular project.
 * 
 * @author cbateman
 * 
 */
public final class CompositeTagRegistryFactory
{
    private static CompositeTagRegistryFactory INSTANCE;

    /**
     * @return the single instance of the registry factory
     */
    public static synchronized CompositeTagRegistryFactory getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new CompositeTagRegistryFactory();
        }
        return INSTANCE;
    }

    private final Map<IContentType, Set<TagRegistryFactoryInfo>> _cachedExtensionsByType =
        new HashMap<IContentType, Set<TagRegistryFactoryInfo>>(4);

    private CompositeTagRegistryFactory()
    {
        // singleton: no external instantiation
    }

    
    /**
     * @param id
     * @return a tag registry for the id or null if there isn't one.
     */
    public final ITagRegistry getRegistry(final TagRegistryIdentifier id)
    {
        final Set<TagRegistryFactoryInfo> handlers = TagLibraryRegistryLoader
                .getAllHandlers();

        final Set<TagRegistryFactoryInfo> matchingHandlers = findMatchingExtensions(
                id, handlers);

        if (matchingHandlers.size() > 0)
        {
            // optimize: if there is only one handler, no need for strategy
            if (matchingHandlers.size() == 1)
            {
                try
                {
                    return matchingHandlers.iterator().next().getTagRegistryFactory()
                            .createTagRegistry(id.getProject());
                }
                catch (TagRegistryFactoryException e)
                {
                    JSFCorePlugin.log(e, "While trying to acquire a registry");
                }
            }
            else
            {
                // for now, we will order by the canonical name of the extension
                // id in ascending order. Later, we may make this preference
                // based.
                final TagRegistrySelectionStrategy selectionStrategy = new TagRegistrySelectionStrategy(
                        new CanonicallyOrderedIteratorPolicy<String>());

                for (final Iterator<TagRegistryFactoryInfo> it = matchingHandlers
                        .iterator(); it.hasNext();)
                {
                    selectionStrategy.addStrategy(it.next().getTagRegistryFactory());
                }
                return selectionStrategy.perform(id.getProject());
            }
        }

        return null;
    }

    /**
     * @return get all tag registry factories
     */
    public Set<TagRegistryFactoryInfo> getAllTagRegistryFactories()
    {
        return TagLibraryRegistryLoader.getAllHandlers();
    }

    private Set<TagRegistryFactoryInfo> findMatchingExtensions(
            TagRegistryIdentifier id, Set<TagRegistryFactoryInfo> handlers)
    {
        Set<TagRegistryFactoryInfo> matching = _cachedExtensionsByType.get(id
                .getContentType());

        if (matching == null)
        {
            matching = new HashSet<TagRegistryFactoryInfo>(4);

            for (final TagRegistryFactoryInfo handler : handlers)
            {
                if (handler.getContentTypes().contains(id.getContentType()))
                {
                    matching.add(handler);
                }
            }

            // if there is nothing matching, just store the empty set and
            // discard
            // the extra memory
            if (matching.size() > 0)
            {
                _cachedExtensionsByType.put(id.getContentType(), matching);
            }
            else
            {
                _cachedExtensionsByType.put(id.getContentType(),
                        Collections.EMPTY_SET);
            }
        }
        return matching;
    }

    /**
     * Identifies a content type/project context in which to request a tag
     * registry.
     * 
     * @author cbateman
     * 
     */
    public final static class TagRegistryIdentifier
    {
        private final IProject     _project;
        private final IContentType _contentType;

        /**
         * @param project
         * @param contentType
         */
        public TagRegistryIdentifier(final IProject project,
                final IContentType contentType)
        {
            _project = project;
            _contentType = contentType;
        }

        IProject getProject()
        {
            return _project;
        }

        IContentType getContentType()
        {
            return _contentType;
        }

    }

    private static class TagRegistrySelectionStrategy
            extends
            IteratorPolicyBasedStrategyComposite<IProject, ITagRegistry, String, IIdentifiableStrategy<IProject, ITagRegistry, String>>
    {
        private static final ITagRegistry NO_RESULT = null;

        protected TagRegistrySelectionStrategy(IIteratorPolicy<String> policy)
        {
            super(policy);
        }

        @Override
        public ITagRegistry getNoResult()
        {
            return NO_RESULT;
        }
    }
}
